package com.example.gostrong.ui.screens.rutinas

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gostrong.R
import androidx.compose.ui.res.stringResource
import com.example.gostrong.data.local.entity.EjercicioEntity
import com.example.gostrong.data.local.entity.RutinaEntity
import com.example.gostrong.data.local.entity.TipoRutina
import com.example.gostrong.viewmodel.RutinaViewModel
import com.example.gostrong.viewmodel.HistorialViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleRutinaScreen(
    rutinaViewModel: RutinaViewModel,
    historialViewModel: HistorialViewModel, // <-- NUEVO
    emailUsuario: String,                   // <-- NUEVO
    rutina: RutinaEntity,
    onVolver: () -> Unit
) {
    val ejercicios by rutinaViewModel
        .getEjerciciosPorRutina(rutina.id)
        .collectAsState(initial = emptyList())

    val esSemanal      = rutina.tipo == TipoRutina.SEMANAL.name
    val headerColors   = deporteAGradient(rutina.deporte)
    val emoji          = deporteAEmoji(rutina.deporte)
    val context        = LocalContext.current

    // ESTADOS PARA EL DIÁLOGO DE SEGUIMIENTO
    var mostrarDialogo by remember { mutableStateOf(false) }
    var estadoSeleccionado by remember { mutableStateOf("COMPLETO") }
    var notasSesion by remember { mutableStateOf("") }
    
    val dia1 = stringResource(id = R.string.dia_lunes)
    val dia2 = stringResource(id = R.string.dia_martes)
    val dia3 = stringResource(id = R.string.dia_miercoles)
    val dia4 = stringResource(id = R.string.dia_jueves)
    val dia5 = stringResource(id = R.string.dia_viernes)
    val dia6 = stringResource(id = R.string.dia_sabado)
    val dia7 = stringResource(id = R.string.dia_domingo)
    val diasOrdenados = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    val tituloEjercicios = stringResource(id = R.string.detalle_rutina_ejercicios_sesion)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.detalle_rutina_volver), tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.background(Brush.horizontalGradient(headerColors))
            )
        },
        // BOTÓN FLOTANTE PARA MARCAR EL HÁBITO
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { mostrarDialogo = true },
                containerColor = MaterialTheme.colorScheme.secondary, // Color victoria dinámico
                contentColor = Color.White,
                icon = { Icon(Icons.Default.Check, stringResource(id = R.string.detalle_rutina_guardar_sesion)) },
                text = { Text(stringResource(id = R.string.detalle_rutina_registrar_hoy), fontWeight = FontWeight.Bold) }
            )
        }
    ) { padding ->

        // --- DIÁLOGO DE SEGUIMIENTO (Se abre al darle al botón flotante) ---
        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text(text = stringResource(id = R.string.detalle_rutina_como_ha_ido), fontWeight = FontWeight.Bold) },
                text = {
                    Column {
                        Text(stringResource(id = R.string.detalle_rutina_elige_nivel))
                        Spacer(Modifier.height(16.dp))

                        // Botones de selección de estado
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            ElevatedFilterChip(
                                selected = estadoSeleccionado == "COMPLETO",
                                onClick = { estadoSeleccionado = "COMPLETO" },
                                label = { Text(stringResource(id = R.string.detalle_rutina_100)) }
                            )
                            ElevatedFilterChip(
                                selected = estadoSeleccionado == "A MEDIAS",
                                onClick = { estadoSeleccionado = "A MEDIAS" },
                                label = { Text(stringResource(id = R.string.detalle_rutina_a_medias)) }
                            )
                            ElevatedFilterChip(
                                selected = estadoSeleccionado == "SALTADO",
                                onClick = { estadoSeleccionado = "SALTADO" },
                                label = { Text(stringResource(id = R.string.detalle_rutina_saltado)) }
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        OutlinedTextField(
                            value = notasSesion,
                            onValueChange = { notasSesion = it },
                            label = { Text(stringResource(id = R.string.detalle_rutina_notas)) },
                            placeholder = { Text(stringResource(id = R.string.detalle_rutina_ej_notas)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        // Guardar en la Base de Datos Historial
                        historialViewModel.registrarSesion(
                            emailUsuario = emailUsuario,
                            tipo = "RUTINA",
                            referenciaId = rutina.id,
                            titulo = rutina.nombre,
                            estado = estadoSeleccionado,
                            notas = notasSesion
                        )
                        mostrarDialogo = false
                        val dMsg = context.getString(R.string.detalle_rutina_habito_guardado)
                        Toast.makeText(context, dMsg, Toast.LENGTH_SHORT).show()
                    }) {
                        Text(stringResource(id = R.string.detalle_rutina_guardar_diario))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarDialogo = false }) {
                        Text(stringResource(id = R.string.salud_cancelar))
                    }
                }
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(bottom = 80.dp) // Más espacio para no tapar el botón flotante
        ) {
            // ── HEADER ──────────────────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.horizontalGradient(headerColors))
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Column {
                        Text(emoji, fontSize = 48.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(rutina.nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(Modifier.height(4.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            ChipInfo(rutina.deporte)
                            ChipInfo(rutina.nivel)
                            ChipInfo(if (esSemanal) stringResource(id = R.string.rutina_dias_sem, rutina.diasSemana) else stringResource(id = R.string.rutina_sesion_unica))
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            stringResource(id = R.string.detalle_rutina_total_ejercicios, ejercicios.size),
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.75f)
                        )
                    }
                }
            }

            // ── CONTENIDO ───────────────────────────────────
            if (esSemanal) {
                // Se asume que en Room el día está guardado igual al valor string_resource.
                // Si Room guarda "Lunes", entonces coincidirá solo en español. 
                // Dado que ya estaban guardandos en español, usaremos "Lunes" como key si es necesario.
                // Esto podría requerir ajuste si el Catalogo tiene las keys harcoded en español.
                val porDia        = ejercicios.groupBy { it.dia }
                val diasActivos   = diasOrdenados.filter { porDia.containsKey(it) }

                diasActivos.forEach { dia ->
                    val lista = porDia[dia] ?: emptyList()
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

                    item {
                        DiaHeader(dia = nombreDia, numEjercicios = lista.size)
                    }
                    items(lista) { ejercicio ->
                        EjercicioRow(ejercicio, Modifier.padding(horizontal = 16.dp))
                    }
                    item { Spacer(Modifier.height(8.dp)) }
                }

            } else {
                item {
                    Text(
                        tituloEjercicios,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    )
                }
                items(ejercicios) { ejercicio ->
                    EjercicioRow(ejercicio, Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

// ── CABECERA DE DÍA ──────────────────────────────────────────
@Composable
fun DiaHeader(dia: String, numEjercicios: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(Modifier.width(10.dp))
        Text(dia, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(8.dp))
        Text(stringResource(id = R.string.detalle_rutina_num_ejercicios, numEjercicios), fontSize = 13.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    )
}

// ── FILA DE EJERCICIO ─────────────────────────────────────────
@Composable
fun EjercicioRow(ejercicio: EjercicioEntity, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "${ejercicio.orden}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(ejercicio.nombre, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                if (ejercicio.notas.isNotBlank()) {
                    Text(ejercicio.notas, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    "${ejercicio.series} × ${ejercicio.repeticiones}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text("⏱ ${ejercicio.descanso}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
