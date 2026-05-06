package com.example.gostrong.ui.screens.salud

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import com.example.gostrong.R
import com.example.gostrong.data.local.entity.RegistroPesoEntity
import com.example.gostrong.data.util.ManejoSesion
import com.example.gostrong.viewmodel.HistorialViewModel
import com.example.gostrong.viewmodel.SaludViewModel
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SaludScreen(saludViewModel: SaludViewModel, historialViewModel: HistorialViewModel) {

    val context = LocalContext.current
    val manejoSesion = remember { ManejoSesion(context) }
    val email = manejoSesion.obtenerEmailUsuario() ?: ""
    val historiales by historialViewModel.historialCompleto.collectAsState()
    val usuario by saludViewModel.usuario.collectAsState()
    var registros by remember { mutableStateOf<List<RegistroPesoEntity>>(emptyList()) }

    // Diálogo para registrar nuevo peso
    var mostrarDialogo by remember { mutableStateOf(false) }
    var nuevoPeso by remember { mutableStateOf("") }
    var errorPeso by remember { mutableStateOf<String?>(null) }

    // Carga inicial
    LaunchedEffect(email) {
        saludViewModel.cargarUsuario(email)
        historialViewModel.cargarHistorial(email)
        saludViewModel.obtenerRegistrosPeso(email).collectLatest {
            registros = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // -----------------------------
        // CABECERA
        // -----------------------------
        Text(
            text = stringResource(id = R.string.salud_titulo),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        usuario?.let { u ->

            val imc = saludViewModel.calcularIMC(u.peso, u.altura)
            val categoria = saludViewModel.categoriaIMC(imc)
            val descripcion = saludViewModel.descripcionIMC(imc)
            val colorCategoria = saludViewModel.colorCategoriaIMC(imc)
            val tmb = saludViewModel.calcularTMB(u)

            // -----------------------------
            // TARJETA IMC
            // -----------------------------
            TarjetaIMC(
                imc = imc,
                categoria = categoria,
                descripcion = descripcion,
                colorCategoria = colorCategoria
            )

            // -----------------------------
            // TARJETA TMB
            // -----------------------------
            TarjetaTMB(tmb = tmb, objetivo = u.objetivo)

            // -----------------------------
            // TARJETA PESO ACTUAL Y OBJETIVO
            // -----------------------------
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DatoVertical(titulo = stringResource(id = R.string.salud_peso_actual), valor = "${"%.1f".format(u.peso)} kg")
                    VerticalDivider(modifier = Modifier.height(50.dp))
                    DatoVertical(titulo = stringResource(id = R.string.salud_peso_objetivo), valor = "${"%.1f".format(u.pesoObjetivo)} kg")
                    VerticalDivider(modifier = Modifier.height(50.dp))
                    DatoVertical(titulo = stringResource(id = R.string.salud_diferencia), valor = "${"%.1f".format(u.pesoObjetivo - u.peso)} kg")
                }
            }

            // -----------------------------
            // BOTÓN REGISTRAR PESO
            // -----------------------------
            Button(
                onClick = { mostrarDialogo = true },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(stringResource(id = R.string.salud_registrar_peso_actual))
            }

            // -----------------------------
            // GRÁFICA DE EVOLUCIÓN
            // -----------------------------
            if (registros.size >= 2) {
                TarjetaGrafica(registros = registros)
            } else {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.salud_registra_minimo_dos_pesos),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                }
            }

            // -----------------------------
            // DIARIO DE ACTIVIDADES (TIMELINE)
            // -----------------------------
            Text(
                text = stringResource(id = R.string.salud_diario_actividades),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            if (historiales.isEmpty()) {
                Text(stringResource(id = R.string.salud_sin_registros), color = Color.Gray)
            } else {
                historiales.forEach { historial ->
                    // Formatear la fecha
                    val f = java.text.SimpleDateFormat("dd MMM yyyy, HH:mm", java.util.Locale.getDefault())
                    val fechaStr = f.format(java.util.Date(historial.fecha))

                    // Elegir color según si falló, lo hizo a medias o perfecto
                    val colorEstado = when(historial.estado) {
                        "COMPLETO" -> Color(0xFF4CAF50)
                        "A MEDIAS" -> Color(0xFFFF9800)
                        else -> Color(0xFFF44336)
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha=0.6f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(12.dp).background(colorEstado, androidx.compose.foundation.shape.CircleShape))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "${historial.tipo}: ${historial.titulo}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = fechaStr, fontSize = 12.sp, color = Color.Gray)

                            if (historial.notas.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "📝 \"${historial.notas}\"", fontSize = 14.sp)
                            }
                        }
                    }
                }
            }

        } ?: run {
            // Usuario cargando
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }

    // -----------------------------
    // DIÁLOGO PARA REGISTRAR PESO
    // -----------------------------
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
                nuevoPeso = ""
                errorPeso = null
            },
            title = { Text(stringResource(id = R.string.salud_registrar_peso_titulo)) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(stringResource(id = R.string.salud_introduce_peso))
                    OutlinedTextField(
                        value = nuevoPeso,
                        onValueChange = { nuevoPeso = it },
                        label = { Text(stringResource(id = R.string.salud_peso_kg)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        isError = errorPeso != null
                    )
                    errorPeso?.let {
                        Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                    }
                }
            },
            confirmButton = {
                val errorInvalido = stringResource(id = R.string.salud_error_numero_invalido)
                val errorRango = stringResource(id = R.string.salud_error_rango_peso)
                Button(onClick = {
                    val pesoFloat = nuevoPeso.replace(",", ".").toFloatOrNull()
                    when {
                        pesoFloat == null -> errorPeso = errorInvalido
                        pesoFloat < 30f || pesoFloat > 300f -> errorPeso = errorRango
                        else -> {
                            saludViewModel.registrarPeso(email, pesoFloat)
                            mostrarDialogo = false
                            nuevoPeso = ""
                            errorPeso = null
                        }
                    }
                }) {
                    Text(stringResource(id = R.string.salud_guardar))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    mostrarDialogo = false
                    nuevoPeso = ""
                    errorPeso = null
                }) {
                    Text(stringResource(id = R.string.salud_cancelar))
                }
            }
        )
    }
}

// -----------------------------
// COMPONENTE: Tarjeta IMC con barra de progreso animada
// -----------------------------
@Composable
fun TarjetaIMC(
    imc: Float,
    categoria: String,
    descripcion: String,
    colorCategoria: String
) {
    val colorIndicador = when (colorCategoria) {
        "AZUL"    -> Color(0xFF0072B2)
        "VERDE"   -> Color(0xFF44AA99)
        "NARANJA" -> Color(0xFFE69F00)
        else      -> Color(0xFFD55E00)
    }

    // Progreso en la barra: IMC 10-40 mapeado a 0-1
    val progreso by animateFloatAsState(
        targetValue = ((imc - 10f) / 30f).coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 800),
        label = "progresoIMC"
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.salud_imc_titulo),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "${"%.1f".format(imc)}",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorIndicador
                )
                Text(
                    text = stringResource(id = R.string.salud_unidad_imc),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 10.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            // Barra de progreso con gradiente de colores
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(6.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progreso)
                        .fillMaxHeight()
                        .background(
                            color = colorIndicador,
                            shape = RoundedCornerShape(6.dp)
                        )
                )
            }

            // Etiquetas de la barra
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("10", style = MaterialTheme.typography.labelSmall)
                Text("18.5", style = MaterialTheme.typography.labelSmall)
                Text("25", style = MaterialTheme.typography.labelSmall)
                Text("30", style = MaterialTheme.typography.labelSmall)
                Text("40+", style = MaterialTheme.typography.labelSmall)
            }

            HorizontalDivider()

            Text(
                text = categoria,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = colorIndicador
            )
            Text(
                text = descripcion,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

// -----------------------------
// COMPONENTE: Tarjeta TMB
// -----------------------------
@Composable
fun TarjetaTMB(tmb: Int, objetivo: String) {

    val calorias = when (objetivo) {
        "Perder peso"   -> tmb - 300
        "Ganar músculo" -> tmb + 300
        else            -> tmb
    }

    val textoObjetivo = when (objetivo) {
        "Perder peso", stringResource(id = R.string.registro_perder_peso)   -> stringResource(id = R.string.salud_para_perder_peso)
        "Ganar músculo", stringResource(id = R.string.registro_ganar_musculo) -> stringResource(id = R.string.salud_para_ganar_musculo)
        else            -> stringResource(id = R.string.salud_para_mantenimiento)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.salud_calorias_diarias),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "$calorias",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(id = R.string.salud_kcal_dia),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Text(
                text = textoObjetivo,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Text(
                text = stringResource(id = R.string.salud_tmb_base, tmb),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            )
        }
    }
}

// -----------------------------
// COMPONENTE: Gráfica de líneas de evolución del peso
// -----------------------------
@Composable
fun TarjetaGrafica(registros: List<RegistroPesoEntity>) {

    val colorLinea = MaterialTheme.colorScheme.primary
    val colorPunto = MaterialTheme.colorScheme.secondary
    val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.salud_evolucion_peso),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            // Gráfica dibujada con Canvas
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                val pesos = registros.map { it.peso }
                val minPeso = (pesos.min() - 2f).coerceAtLeast(0f)
                val maxPeso = pesos.max() + 2f
                val rango = maxPeso - minPeso

                val anchoTotal = size.width
                val altoTotal = size.height
                val pasoX = anchoTotal / (registros.size - 1).toFloat()

                // Líneas de cuadrícula horizontales
                val lineasCuadricula = 4
                for (i in 0..lineasCuadricula) {
                    val y = altoTotal * i / lineasCuadricula
                    drawLine(
                        color = Color.LightGray.copy(alpha = 0.4f),
                        start = Offset(0f, y),
                        end = Offset(anchoTotal, y),
                        strokeWidth = 1f
                    )
                }

                // Dibuja la línea de la gráfica
                val path = Path()
                registros.forEachIndexed { index, registro ->
                    val x = index * pasoX
                    val y = altoTotal - ((registro.peso - minPeso) / rango * altoTotal)
                    if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
                }
                drawPath(
                    path = path,
                    color = colorLinea,
                    style = Stroke(width = 3f)
                )

                // Dibuja los puntos
                registros.forEachIndexed { index, registro ->
                    val x = index * pasoX
                    val y = altoTotal - ((registro.peso - minPeso) / rango * altoTotal)
                    drawCircle(
                        color = colorPunto,
                        radius = 8f,
                        center = Offset(x, y)
                    )
                    drawCircle(
                        color = Color.White,
                        radius = 4f,
                        center = Offset(x, y)
                    )
                }
            }

            // Etiquetas de fechas debajo de la gráfica
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val registrosMostrar = if (registros.size > 5) {
                    listOf(registros.first(), registros.last())
                } else {
                    registros
                }
                registrosMostrar.forEach { registro ->
                    Text(
                        text = dateFormat.format(Date(registro.fecha)),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            }

            // Último registro
            Text(
                text = stringResource(
                    id = R.string.salud_ultimo_registro,
                    "%.1f".format(registros.last().peso),
                    dateFormat.format(Date(registros.last().fecha))
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// -----------------------------
// COMPONENTE: Dato vertical (título + valor)
// -----------------------------
@Composable
fun DatoVertical(titulo: String, valor: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = valor,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = titulo,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}