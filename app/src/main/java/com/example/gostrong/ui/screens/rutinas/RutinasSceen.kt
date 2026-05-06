package com.example.gostrong.ui.screens.rutinas

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
import androidx.compose.material.icons.filled.Person

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
import com.example.gostrong.data.catalog.CatalogoRutinas
import com.example.gostrong.data.catalog.RutinaTemplate

import com.example.gostrong.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalContext
import com.example.gostrong.data.local.entity.RutinaEntity
import com.example.gostrong.data.local.entity.TipoRutina
import com.example.gostrong.viewmodel.RutinaViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutinasScreen(
    rutinaViewModel: RutinaViewModel,
    emailUsuario: String,
    onVerDetalle: (RutinaEntity) -> Unit
) {
    val rutinas by rutinaViewModel
        .getRutinasPorUsuario(emailUsuario)
        .collectAsState(initial = emptyList())

    val operacionExitosa by rutinaViewModel.operacionExitosa.collectAsState()

    val contexto = LocalContext.current
    val strTodos = stringResource(id = R.string.rutina_todos)
    var mostrarCatalogo by remember { mutableStateOf(false) }
    var filtroDeporte   by remember { mutableStateOf(strTodos) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(operacionExitosa) {
        if (operacionExitosa == true) {
            val msj = contexto.getString(R.string.rutina_anadida)
            snackbarHostState.showSnackbar(msj)
            rutinaViewModel.resetOperacion()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrarCatalogo = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.rutina_anadir_sr), tint = Color.White)
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
                Text(stringResource(id = R.string.rutina_mis_rutinas), fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text(
                    stringResource(id = R.string.rutina_tus_rutinas_guardadas),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Spacer(Modifier.height(4.dp))
            }

            if (rutinas.isEmpty()) {
                item { EmptyRutinasCard { mostrarCatalogo = true } }
            }

            items(rutinas) { rutina ->
                RutinaCard(
                    rutina    = rutina,
                    onClick   = { rutinaViewModel.seleccionarRutina(rutina); onVerDetalle(rutina) },
                    onEliminar = { rutinaViewModel.eliminarRutina(rutina) }
                )
            }
        }
    }

    if (mostrarCatalogo) {
        CatalogoBottomSheet(
            filtroDeporte    = filtroDeporte,
            onFiltroChange   = { filtroDeporte = it },
            onSeleccionar    = { template ->
                rutinaViewModel.guardarRutinaDeCatalogo(template, emailUsuario)
                mostrarCatalogo = false
            },
            onDismiss = { mostrarCatalogo = false }
        )
    }
}

// ── CARD RUTINA GUARDADA ──────────────────────────────────────
@Composable
fun RutinaCard(rutina: RutinaEntity, onClick: () -> Unit, onEliminar: () -> Unit) {
    val gradientColors = deporteAGradient(rutina.deporte)
    val emoji          = deporteAEmoji(rutina.deporte)

    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.horizontalGradient(gradientColors))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(emoji, fontSize = 36.sp)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(rutina.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    Spacer(Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        ChipInfo(rutina.nivel)
                        ChipInfo(
                            if (rutina.tipo == TipoRutina.SEMANAL.name)
                                stringResource(id = R.string.rutina_dias_sem, rutina.diasSemana)
                            else stringResource(id = R.string.rutina_sesion_unica)
                        )
                    }
                }
                IconButton(onClick = onEliminar) {
                    Icon(Icons.Default.Delete, contentDescription = stringResource(id = R.string.rutina_eliminar), tint = Color.White.copy(alpha = 0.8f))
                }
            }
        }
    }
}

@Composable
fun ChipInfo(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.2f))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(text, fontSize = 11.sp, color = Color.White, fontWeight = FontWeight.Medium)
    }
}

// ── ESTADO VACÍO ─────────────────────────────────────────────
@Composable
fun EmptyRutinasCard(onAñadir: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Person, null, modifier = Modifier.size(56.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(12.dp))
            Text(stringResource(id = R.string.rutina_aun_no_tienes), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Spacer(Modifier.height(4.dp))
            Text(stringResource(id = R.string.rutina_elige_catalogo), fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
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
fun CatalogoBottomSheet(
    filtroDeporte: String,
    onFiltroChange: (String) -> Unit,
    onSeleccionar: (RutinaTemplate) -> Unit,
    onDismiss: () -> Unit
) {
    val strTodos = stringResource(id = R.string.rutina_todos)
    val context = androidx.compose.ui.platform.LocalContext.current
    val deportes = listOf(strTodos) + CatalogoRutinas.deportesDisponibles(context)
    val lista    = if (filtroDeporte == strTodos || filtroDeporte == "Todos") CatalogoRutinas.todas(context)
    else CatalogoRutinas.porDeporte(filtroDeporte, context)

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.padding(bottom = 32.dp)) {
            Text(
                stringResource(id = R.string.rutina_catalogo_rutinas),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(deportes) { deporte ->
                    FilterChip(
                        selected = filtroDeporte == deporte,
                        onClick  = { onFiltroChange(deporte) },
                        label    = { Text(deporte) }
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(lista) { template ->
                    CatalogoRutinaCard(template = template, onSeleccionar = { onSeleccionar(template) })
                }
            }
        }
    }
}

// ── CARD EN EL CATÁLOGO ───────────────────────────────────────
@Composable
fun CatalogoRutinaCard(template: RutinaTemplate, onSeleccionar: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
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
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
                Spacer(Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    AssistChip(onClick = {}, label = { Text(template.nivel, fontSize = 11.sp) })
                    AssistChip(
                        onClick = {},
                        label   = {
                            Text(
                                if (template.tipo == TipoRutina.SEMANAL.name) stringResource(id = R.string.rutina_dias_sem, template.diasSemana) else stringResource(id = R.string.rutina_sesion_unica),
                                fontSize = 11.sp
                            )
                        }
                    )
                }
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = onSeleccionar, contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)) {
                Text(stringResource(id = R.string.rutina_anadir), fontSize = 13.sp)
            }
        }
    }
}

// ── HELPERS ───────────────────────────────────────────────────
@Composable
fun deporteAGradient(deporte: String): List<Color> {
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val error = MaterialTheme.colorScheme.error
    return when (deporte) {
        "Musculación" -> listOf(primary, primary.copy(alpha=0.7f))
        "Boxeo"       -> listOf(error, error.copy(alpha=0.7f))
        "MMA"         -> listOf(secondary, secondary.copy(alpha=0.7f))
        "Cardio"      -> listOf(error.copy(alpha=0.8f), error.copy(alpha=0.4f))
        else          -> listOf(secondary.copy(alpha=0.8f), secondary.copy(alpha=0.4f))
    }
}

fun deporteAEmoji(deporte: String): String = when (deporte) {
    "Musculación" -> "🏋️"
    "Boxeo"       -> "🥊"
    "MMA"         -> "🥋"
    "Cardio"      -> "🏃"
    else          -> "💪"
}