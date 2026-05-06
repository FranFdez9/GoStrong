package com.example.gostrong.ui.screens.dietas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.res.stringResource
import com.example.gostrong.R
import com.example.gostrong.data.local.entity.ComidaEntity
import com.example.gostrong.data.local.entity.DietaEntity
import com.example.gostrong.data.local.entity.TipoDieta
import com.example.gostrong.viewmodel.DietaViewModel
import com.example.gostrong.viewmodel.HistorialViewModel

val VerdeSalud = Color(0xFF2E7D32)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleDietaScreen(
    dietaViewModel: DietaViewModel,
    historialViewModel: HistorialViewModel,
    emailUsuario: String,
    dieta: DietaEntity,
    onVolver: () -> Unit
) {
    val comidas by dietaViewModel
        .getComidasPorDieta(dieta.id)
        .collectAsState(initial = emptyList())

    val esSemanal    = dieta.tipo == TipoDieta.SEMANAL.name
    val headerColors = objetivoAGradient(dieta.objetivo)
    val emoji        = objetivoAEmoji(dieta.objetivo)

    // ESTADO PARA EL FORMULARIO DE HISTORIAL
    var mostrarDialogo by remember { mutableStateOf(false) }
    var estadoSeleccionado by remember { mutableStateOf("SEGUIDA") }
    var notas by remember { mutableStateOf("") }
    
    val dia1 = stringResource(id = R.string.dia_lunes)
    val dia2 = stringResource(id = R.string.dia_martes)
    val dia3 = stringResource(id = R.string.dia_miercoles)
    val dia4 = stringResource(id = R.string.dia_jueves)
    val dia5 = stringResource(id = R.string.dia_viernes)
    val dia6 = stringResource(id = R.string.dia_sabado)
    val dia7 = stringResource(id = R.string.dia_domingo)
    val diasOrdenados = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    
    val m1 = stringResource(id = R.string.detalle_dieta_desayuno)
    val m2 = stringResource(id = R.string.detalle_dieta_media_manana)
    val m3 = stringResource(id = R.string.detalle_dieta_comida)
    val m4 = stringResource(id = R.string.detalle_dieta_merienda)
    val m5 = stringResource(id = R.string.detalle_dieta_cena)
    val momentosOrden = listOf(m1, m2, m3, m4, m5, "Desayuno", "Media mañana", "Comida", "Merienda", "Cena")
    val tituloComidas = stringResource(id = R.string.detalle_dieta_comidas)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.detalle_rutina_volver), tint = Color.White)
                    }
                },
                colors   = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.background(Brush.horizontalGradient(headerColors))
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { mostrarDialogo = true },
                containerColor = VerdeSalud,
                contentColor = Color.White,
                icon = { Icon(Icons.Default.AddTask, stringResource(id = R.string.detalle_dieta_registrar)) },
                text = { Text(stringResource(id = R.string.detalle_dieta_anotar), fontWeight = FontWeight.Bold) },
                shape = RoundedCornerShape(16.dp)
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(bottom = 80.dp) // Espacio para el botón flotante
        ) {
            // ── HEADER ──────────────────────────────────────
            item {
                Box(modifier = Modifier.fillMaxWidth().background(Brush.horizontalGradient(headerColors)).padding(horizontal = 20.dp, vertical = 16.dp)) {
                    Column {
                        Text(emoji, fontSize = 48.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(dieta.nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(Modifier.height(4.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DietaChip(dieta.objetivo)
                            DietaChip("~${dieta.calorias} kcal/día")
                            if (dieta.restriccion != "Ninguna") DietaChip(dieta.restriccion)
                        }
                        Spacer(Modifier.height(4.dp))
                        DietaChip(if (esSemanal) stringResource(id = R.string.dieta_plan_semanal) else stringResource(id = R.string.dieta_un_dia))
                        Spacer(Modifier.height(8.dp))
                        Text(stringResource(id = R.string.detalle_dieta_comidas_total, comidas.size), fontSize = 13.sp, color = Color.White.copy(alpha = 0.75f))
                    }
                }
            }

            // ── RESUMEN MACROS ───────────────────────────────
            item { MacrosResumen(calorias = dieta.calorias, modifier = Modifier.padding(16.dp)) }

            // ── CONTENIDO ───────────────────────────────────
            if (esSemanal) {
                val porDia        = comidas.groupBy { it.dia }
                val diasActivos   = diasOrdenados.filter { porDia.containsKey(it) }

                diasActivos.forEach { dia ->
                    val comidasDia = porDia[dia] ?: emptyList()
                    val calDia     = comidasDia.sumOf { it.calorias }
                    val nombreDia = when(dia) {
                        "Lunes" -> dia1
                        "Martes" -> dia2
                        "Miércoles" -> dia3
                        "Jueves" -> dia4
                        "Viernes" -> dia5
                        "Sábado" -> dia6
                        "Domingo" -> dia7
                        else -> dia
                    }

                    item { DiaDietaHeader(dia = nombreDia, calorias = calDia) }
                    items(comidasDia) { comida -> ComidaRow(comida, Modifier.padding(horizontal = 16.dp)) }
                    item { Spacer(Modifier.height(8.dp)) }
                }
            } else {
                val ordenadas     = comidas.sortedBy { momentosOrden.indexOf(it.momento) }

                item { Text(tituloComidas, fontWeight = FontWeight.Bold, fontSize = 17.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) }
                items(ordenadas) { comida -> ComidaRow(comida, Modifier.padding(horizontal = 16.dp)) }
            }
        }
    }

    // ── DIÁLOGO PARA REGISTRAR LA DIETA EN EL HISTORIAL ────────────────────
    if (mostrarDialogo) {
        val opcionesEstado = listOf("SEGUIDA", "CON TRAMPAS", "SALTADA")

        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            icon = { Icon(Icons.Default.AddTask, null, modifier = Modifier.size(36.dp), tint = VerdeSalud) },
            title = { Text(stringResource(id = R.string.detalle_dieta_registrar), fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(stringResource(id = R.string.detalle_dieta_pregunta), fontSize = 14.sp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        opcionesEstado.forEach { estado ->
                            val estadoStr = when (estado) {
                                "SEGUIDA" -> stringResource(id = R.string.detalle_dieta_seguida)
                                "CON TRAMPAS" -> stringResource(id = R.string.detalle_dieta_con_trampas)
                                else -> stringResource(id = R.string.detalle_dieta_saltada)
                            }
                            FilterChip(
                                selected = estadoSeleccionado == estado,
                                onClick = { estadoSeleccionado = estado },
                                label = { Text(estadoStr, fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = when(estado) {
                                        "SEGUIDA" -> Color(0xFF4CAF50).copy(alpha=0.3f)
                                        "SALTADA" -> Color(0xFFF44336).copy(alpha=0.3f)
                                        else -> Color(0xFFFFA000).copy(alpha=0.3f)
                                    }
                                )
                            )
                        }
                    }
                    OutlinedTextField(
                        value = notas,
                        onValueChange = { notas = it },
                        label = { Text(stringResource(id = R.string.detalle_rutina_notas)) },
                        leadingIcon = { Icon(Icons.Default.AddComment, null) },
                        placeholder = { Text(stringResource(id = R.string.detalle_dieta_ej_notas)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        minLines = 2
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        historialViewModel.registrarSesion(
                            emailUsuario = emailUsuario,
                            tipo = "DIETA",
                            referenciaId = dieta.id,
                            titulo = dieta.nombre,
                            estado = estadoSeleccionado,
                            notas = notas
                        )
                        mostrarDialogo = false
                        notas = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeSalud)
                ) { Text(stringResource(id = R.string.salud_guardar)) }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogo = false }) { Text(stringResource(id = R.string.salud_cancelar)) }
            }
        )
    }
}

// ── COMPONENTES SECUNDARIOS ───────────────
@Composable
fun MacrosResumen(calorias: Int, modifier: Modifier = Modifier) {
    val proteinas     = (calorias * 0.30 / 4).toInt()
    val carbohidratos = (calorias * 0.45 / 4).toInt()
    val grasas        = (calorias * 0.25 / 9).toInt()

    Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            MacroItem("$calorias", "kcal", MaterialTheme.colorScheme.primary)
            MacroItem("${proteinas}g", stringResource(id = R.string.detalle_dieta_prot), Color(0xFFEF5350))
            MacroItem("${carbohidratos}g", stringResource(id = R.string.detalle_dieta_carbs), Color(0xFFFF8A65))
            MacroItem("${grasas}g", stringResource(id = R.string.detalle_dieta_grasas), Color(0xFF66BB6A))
        }
    }
}

@Composable
fun MacroItem(valor: String, etiqueta: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(valor, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = color)
        Text(etiqueta, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun DiaDietaHeader(dia: String, calorias: Int) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary))
        Spacer(Modifier.width(10.dp))
        Text(dia, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(8.dp))
        Text("($calorias kcal)", fontSize = 13.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
    }
    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
}

@Composable
fun ComidaRow(comida: ComidaEntity, modifier: Modifier = Modifier) {
    val m1 = stringResource(id = R.string.detalle_dieta_desayuno)
    val m2 = stringResource(id = R.string.detalle_dieta_media_manana)
    val m3 = stringResource(id = R.string.detalle_dieta_comida)
    val m4 = stringResource(id = R.string.detalle_dieta_merienda)
    val m5 = stringResource(id = R.string.detalle_dieta_cena)
    val momentoEmoji = when (comida.momento) {
        "Desayuno", m1 -> "🌅"
        "Media mañana", m2 -> "🍎"
        "Comida", m3 -> "🍽️"
        "Merienda", m4 -> "☕"
        "Cena", m5 -> "🌙"
        else -> "🥗"
    }
    
    val txtMomento = when (comida.momento) {
        "Desayuno" -> m1
        "Media mañana" -> m2
        "Comida" -> m3
        "Merienda" -> m4
        "Cena" -> m5
        else -> comida.momento
    }

    Card(modifier = modifier.fillMaxWidth().padding(vertical = 4.dp), shape = RoundedCornerShape(10.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)), elevation = CardDefaults.cardElevation(0.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(38.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
                Text(momentoEmoji, fontSize = 18.sp)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(txtMomento, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(2.dp))
                Text(comida.descripcion, fontSize = 13.sp, lineHeight = 18.sp)
            }
            Spacer(Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.End) {
                Text("${comida.calorias} kcal", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MaterialTheme.colorScheme.primary)
                Text("P:${comida.proteinas}g C:${comida.carbohidratos}g G:${comida.grasas}g", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}