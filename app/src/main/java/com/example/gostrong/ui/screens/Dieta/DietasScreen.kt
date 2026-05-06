package com.example.gostrong.ui.screens.dietas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gostrong.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalContext
import com.example.gostrong.data.catalog.CatalogoDietas
import com.example.gostrong.data.catalog.DietaTemplate
import com.example.gostrong.data.local.entity.DietaEntity
import com.example.gostrong.data.local.entity.TipoDieta
import com.example.gostrong.viewmodel.DietaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietasScreen(
    dietaViewModel: DietaViewModel,
    emailUsuario: String,
    onVerDetalle: (DietaEntity) -> Unit
) {
    val dietas by dietaViewModel
        .getDietasPorUsuario(emailUsuario)
        .collectAsState(initial = emptyList())

    val operacionExitosa by dietaViewModel.operacionExitosa.collectAsState()

    val contexto = LocalContext.current
    val strTodos = stringResource(id = R.string.dieta_todos)
    var mostrarCatalogo  by remember { mutableStateOf(false) }
    var filtroObjetivo   by remember { mutableStateOf(strTodos) }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(operacionExitosa) {
        if (operacionExitosa == true) {
            val msj = contexto.getString(R.string.dieta_anadida)
            snackbarHostState.showSnackbar(msj)
            dietaViewModel.resetOperacion()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrarCatalogo = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.dieta_anadir_sr), tint = Color.White)
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Text(stringResource(id = R.string.dieta_mis_dietas), fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text(
                    stringResource(id = R.string.dieta_tus_planes_alimentacion),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Spacer(Modifier.height(4.dp))
            }

            if (dietas.isEmpty()) {
                item { EmptyDietasCard { mostrarCatalogo = true } }
            }

            items(dietas) { dieta ->
                DietaCard(
                    dieta     = dieta,
                    onClick   = { dietaViewModel.seleccionarDieta(dieta); onVerDetalle(dieta) },
                    onEliminar = { dietaViewModel.eliminarDieta(dieta) }
                )
            }
        }
    }

    if (mostrarCatalogo) {
        CatalogoDietasBottomSheet(
            filtroObjetivo  = filtroObjetivo,
            onFiltroChange  = { filtroObjetivo = it },
            onSeleccionar   = { template ->
                dietaViewModel.guardarDietaDeCatalogo(template, emailUsuario)
                mostrarCatalogo = false
            },
            onDismiss = { mostrarCatalogo = false }
        )
    }
}

// ── CARD DIETA GUARDADA ───────────────────────────────────────
@Composable
fun DietaCard(dieta: DietaEntity, onClick: () -> Unit, onEliminar: () -> Unit) {
    val gradient = objetivoAGradient(dieta.objetivo)
    val emoji    = objetivoAEmoji(dieta.objetivo)

    Card(
        modifier  = Modifier.fillMaxWidth().clickable { onClick() },
        shape     = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.horizontalGradient(gradient))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(emoji, fontSize = 36.sp)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(dieta.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    Spacer(Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        DietaChip(dieta.objetivo)
                        DietaChip(stringResource(id = R.string.dieta_kcal, dieta.calorias))
                        if (dieta.restriccion != "Ninguna") DietaChip(dieta.restriccion)
                    }
                    Spacer(Modifier.height(4.dp))
                    DietaChip(if (dieta.tipo == TipoDieta.SEMANAL.name) stringResource(id = R.string.dieta_plan_semanal) else stringResource(id = R.string.dieta_un_dia))
                }
                IconButton(onClick = onEliminar) {
                    Icon(Icons.Default.Delete, contentDescription = stringResource(id = R.string.rutina_eliminar), tint = Color.White.copy(alpha = 0.8f))
                }
            }
        }
    }
}

@Composable
fun DietaChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.2f))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(text, fontSize = 11.sp, color = Color.White, fontWeight = FontWeight.Medium)
    }
}

// ── ESTADO VACÍO ──────────────────────────────────────────────
@Composable
fun EmptyDietasCard(onAñadir: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(16.dp),
        colors   = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Edit, null, modifier = Modifier.size(56.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(12.dp))
            Text(stringResource(id = R.string.dieta_aun_no_tienes), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Spacer(Modifier.height(4.dp))
            Text(stringResource(id = R.string.dieta_elige_catalogo), fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(16.dp))
            Button(onClick = onAñadir) {
                Icon(Icons.Default.Add, null)
                Spacer(Modifier.width(6.dp))
                Text(stringResource(id = R.string.rutina_ver_catalogo))
            }
        }
    }
}

// ── BOTTOM SHEET CATÁLOGO ─────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoDietasBottomSheet(
    filtroObjetivo: String,
    onFiltroChange: (String) -> Unit,
    onSeleccionar: (DietaTemplate) -> Unit,
    onDismiss: () -> Unit
) {
    val strTodos = stringResource(id = R.string.dieta_todos)
    val context = androidx.compose.ui.platform.LocalContext.current
    val objetivos = listOf(strTodos) + CatalogoDietas.objetivosDisponibles(context)
    val lista     = if (filtroObjetivo == "Todos" || filtroObjetivo == strTodos) CatalogoDietas.todas(context)
                    else CatalogoDietas.porObjetivo(filtroObjetivo, context)

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.padding(bottom = 32.dp)) {
            Text(
                stringResource(id = R.string.dieta_catalogo_dietas),
                fontWeight = FontWeight.Bold,
                fontSize   = 20.sp,
                modifier   = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(objetivos) { objetivo ->
                    FilterChip(
                        selected = filtroObjetivo == objetivo,
                        onClick  = { onFiltroChange(objetivo) },
                        label    = { Text(objetivo) }
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(lista) { template ->
                    CatalogoDietaCard(template = template, onSeleccionar = { onSeleccionar(template) })
                }
            }
        }
    }
}

// ── CARD EN EL CATÁLOGO ───────────────────────────────────────
@Composable
fun CatalogoDietaCard(template: DietaTemplate, onSeleccionar: () -> Unit) {
    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(template.iconoEmoji, fontSize = 32.sp)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(template.nombre, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                Text(
                    template.descripcion,
                    fontSize = 12.sp,
                    color    = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
                Spacer(Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    AssistChip(onClick = {}, label = { Text(template.objetivo,    fontSize = 11.sp) })
                    AssistChip(onClick = {}, label = { Text(stringResource(id = R.string.dieta_kcal, template.calorias), fontSize = 11.sp) })
                    if (template.restriccion != "Ninguna") {
                        AssistChip(onClick = {}, label = { Text(template.restriccion, fontSize = 11.sp) })
                    }
                }
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = onSeleccionar,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(stringResource(id = R.string.rutina_anadir), fontSize = 13.sp)
            }
        }
    }
}

// ── HELPERS ───────────────────────────────────────────────────
fun objetivoAGradient(objetivo: String): List<Color> = when (objetivo) {
    "Perder peso"    -> listOf(Color(0xFF1565C0), Color(0xFF42A5F5))
    "Ganar músculo"  -> listOf(Color(0xFFB71C1C), Color(0xFFEF5350))
    "Mantenimiento"  -> listOf(Color(0xFF2E7D32), Color(0xFF66BB6A))
    "Definición"     -> listOf(Color(0xFFE65100), Color(0xFFFF8A65))
    else             -> listOf(Color(0xFF4A148C), Color(0xFFAB47BC))
}

fun objetivoAEmoji(objetivo: String): String = when (objetivo) {
    "Perder peso"   -> "🥗"
    "Ganar músculo" -> "💪"
    "Mantenimiento" -> "⚖️"
    "Definición"    -> "🔥"
    else            -> "🍽️"
}
