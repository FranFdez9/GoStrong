package com.example.gostrong.ui.screens.perfil

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.res.stringResource
import com.example.gostrong.R
import com.example.gostrong.data.local.entity.UsuarioEntity
import com.example.gostrong.viewmodel.DietaViewModel
import com.example.gostrong.viewmodel.RutinaViewModel
import com.example.gostrong.viewmodel.SaludViewModel
import com.example.gostrong.viewmodel.UsuarioViewModel
import kotlinx.coroutines.launch

// Colores dinámicos retirados. Dejamos que el Tema los asigne.
// ─────────────────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    usuarioViewModel: UsuarioViewModel,
    rutinaViewModel:  RutinaViewModel,
    dietaViewModel:   DietaViewModel,
    saludViewModel:   SaludViewModel,
    emailUsuario:     String,
    onIrAAjustes:     () -> Unit,
    onCerrarSesion:   () -> Unit
) {
    val scope         = rememberCoroutineScope()
    var usuario       by remember { mutableStateOf<UsuarioEntity?>(null) }
    var mostrarEditor by remember { mutableStateOf(false) }
    var mostrarLogout by remember { mutableStateOf(false) }

    LaunchedEffect(emailUsuario) {
        usuarioViewModel.obtenerUsuarioPorEmail(emailUsuario) { usuario = it }
    }

    val rutinas      by rutinaViewModel.getRutinasPorUsuario(emailUsuario).collectAsState(emptyList())
    val dietas       by dietaViewModel.getDietasPorUsuario(emailUsuario).collectAsState(emptyList())
    val registros    by saludViewModel.obtenerRegistrosPeso(emailUsuario).collectAsState(emptyList())

    Box(Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // ── HEADER ──────────────────────────────────────────────────────
            HeaderPerfil(usuario)
            Spacer(Modifier.height(6.dp))

            // ── BADGES ──────────────────────────────────────────────────────
            usuario?.let { BadgesRow(it.nivel, it.objetivo) }
            Spacer(Modifier.height(18.dp))

            // ── STATS ────────────────────────────────────────────────────────
            StatsRow(rutinas.size, dietas.size, registros.size)
            Spacer(Modifier.height(20.dp))

            // ── DATOS PERSONALES ─────────────────────────────────────────────
            usuario?.let { SeccionDatosPersonales(it) }
            Spacer(Modifier.height(16.dp))

            // ── DATOS FÍSICOS ─────────────────────────────────────────────────
            usuario?.let { SeccionDatosFisicos(it) }
            Spacer(Modifier.height(28.dp))

            // ── CERRAR SESIÓN ─────────────────────────────────────────────────
            OutlinedButton(
                onClick  = { mostrarLogout = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                shape    = RoundedCornerShape(14.dp),
                border   = BorderStroke(1.5.dp, MaterialTheme.colorScheme.error),
                colors   = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) {
                Icon(Icons.Default.ExitToApp, null, Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(id = R.string.perfil_cerrar_sesion), fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            }

            Spacer(Modifier.height(100.dp))
        }

        // ── FAB EDITAR ───────────────────────────────────────────────────────
        FloatingActionButton(
            onClick        = { mostrarEditor = true },
            modifier       = Modifier.align(Alignment.BottomEnd).padding(20.dp),
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor   = Color.White,
            shape          = CircleShape,
            elevation      = FloatingActionButtonDefaults.elevation(8.dp)
        ) {
            Icon(Icons.Default.Edit, stringResource(id = R.string.perfil_editar_perfil), Modifier.size(24.dp))
        }

        // ── BOTÓN ENGRANAJE DE AJUSTES ──────────────────────────────────────
        IconButton(
            onClick = onIrAAjustes,
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        ) {
            Icon(Icons.Default.Settings, contentDescription = stringResource(id = R.string.perfil_ajustes), tint = Color.White, modifier = Modifier.size(28.dp))
        }
    }

    // ── DIALOGO EDITOR ───────────────────────────────────────────────────────
    if (mostrarEditor && usuario != null) {
        EditorPerfilDialog(
            usuario   = usuario!!,
            onGuardar = { u ->
                scope.launch {
                    usuarioViewModel.actualizarUsuario(u)
                    usuario = u
                    mostrarEditor = false
                }
            },
            onDismiss = { mostrarEditor = false }
        )
    }

    // ── DIALOGO LOGOUT ───────────────────────────────────────────────────────
    if (mostrarLogout) {
        AlertDialog(
            onDismissRequest = { mostrarLogout = false },
            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, null, modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.error) },
            title   = { Text(stringResource(id = R.string.perfil_confirmar_cerrar_sesion), fontWeight = FontWeight.Bold) },
            text    = { Text(stringResource(id = R.string.perfil_volveras_inicio), textAlign = TextAlign.Center) },
            confirmButton = {
                Button(onClick = onCerrarSesion,
                       colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                    Text(stringResource(id = R.string.perfil_salir))
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarLogout = false }) { Text(stringResource(id = R.string.perfil_cancelar)) }
            }
        )
    }
}


// ─────────────────────────────────────────────────────────────────────────────
// HEADER ÉPICO
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun HeaderPerfil(usuario: UsuarioEntity?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        // Fondo gradiente
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.primary)))
        )
        // Círculos decorativos
        Box(Modifier.size(220.dp).offset((-40).dp, (-60).dp).clip(CircleShape)
            .background(Color.White.copy(alpha = 0.05f)))
        Box(Modifier.size(160.dp).offset(240.dp, 80.dp).clip(CircleShape)
            .background(Color.White.copy(alpha = 0.06f)))
        Box(Modifier.size(90.dp).offset(290.dp, (-10).dp).clip(CircleShape)
            .background(Color.White.copy(alpha = 0.04f)))

        // Contenido centrado
        Column(
            modifier            = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(16.dp))
            AvatarPerfil(
                inicial = usuario?.nombreUsuario?.firstOrNull()?.uppercaseChar() ?: '?',
                size    = 96.dp
            )
            Spacer(Modifier.height(14.dp))
            Text(
                text          = usuario?.nombreUsuario ?: "...",
                fontWeight    = FontWeight.ExtraBold,
                fontSize      = 24.sp,
                color         = Color.White,
                letterSpacing = 0.5.sp
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text     = usuario?.email ?: "",
                fontSize = 13.sp,
                color    = Color.White.copy(alpha = 0.72f)
            )
            Spacer(Modifier.height(12.dp))
        }

        // Onda en el borde inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// AVATAR PULSANTE
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun AvatarPerfil(inicial: Char, size: Dp) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            tween(1800, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ), label = "scale"
    )

    Box(contentAlignment = Alignment.Center) {
        // Anillo exterior animado (dorado/azul)
        Box(
            modifier = Modifier
                .size(size + 14.dp)
                .scale(scale)
                .clip(CircleShape)
                .background(Brush.sweepGradient(listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)))
        )
        // Separador blanco
        Box(Modifier.size(size + 6.dp).clip(CircleShape).background(Color.White))
        // Círculo principal
        Box(
            modifier         = Modifier
                .size(size)
                .clip(CircleShape)
                .background(Brush.linearGradient(listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary))),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text       = inicial.toString(),
                fontSize   = (size.value * 0.42f).sp,
                fontWeight = FontWeight.ExtraBold,
                color      = Color.White
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// BADGES NIVEL + OBJETIVO
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun BadgesRow(nivel: String, objetivo: String) {
    Row(
        modifier              = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        BadgePill(
            texto = nivel,
            emoji = when (nivel.lowercase()) {
                "principiante" -> "🌱"; "intermedio" -> "⚡"; "avanzado" -> "🔥"; else -> "⭐"
            },
            color = when (nivel.lowercase()) {
                "principiante" -> MaterialTheme.colorScheme.secondary; "intermedio" -> MaterialTheme.colorScheme.primary
                "avanzado"     -> MaterialTheme.colorScheme.error; else -> MaterialTheme.colorScheme.primary
            }
        )
        BadgePill(
            texto = objetivo,
            emoji = when (objetivo.lowercase()) {
                "perder peso"   -> "🥗"; "ganar músculo", "ganar musculo" -> "💪"
                "mantenimiento" -> "⚖️"; "definición", "definicion" -> "🔥"; else -> "🎯"
            },
            color = when (objetivo.lowercase()) {
                "perder peso"   -> MaterialTheme.colorScheme.primary; "ganar músculo", "ganar musculo" -> MaterialTheme.colorScheme.error
                "mantenimiento" -> MaterialTheme.colorScheme.secondary; "definición", "definicion" -> MaterialTheme.colorScheme.primary; else -> MaterialTheme.colorScheme.primary
            }
        )
    }
}

@Composable
fun BadgePill(texto: String, emoji: String, color: Color) {
    Row(
        modifier          = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(color.copy(alpha = 0.11f))
            .border(1.dp, color.copy(alpha = 0.38f), RoundedCornerShape(50.dp))
            .padding(horizontal = 14.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(emoji, fontSize = 14.sp)
        Text(texto, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = color)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// STATS ROW (3 contadores)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun StatsRow(numRutinas: Int, numDietas: Int, numRegistros: Int) {
    Card(
        modifier  = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        shape     = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier              = Modifier.fillMaxWidth().padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(numRutinas.toString(),  stringResource(id = R.string.perfil_rutinas),  Icons.Default.FitnessCenter, MaterialTheme.colorScheme.primary)
            VerticalDivider(Modifier.height(50.dp), color = MaterialTheme.colorScheme.outlineVariant)
            StatItem(numDietas.toString(),   stringResource(id = R.string.perfil_dietas),   Icons.Default.Restaurant,   MaterialTheme.colorScheme.secondary)
            VerticalDivider(Modifier.height(50.dp), color = MaterialTheme.colorScheme.outlineVariant)
            StatItem(numRegistros.toString(),stringResource(id = R.string.perfil_registros),Icons.Default.Monitor,      MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun StatItem(valor: String, label: String, icono: ImageVector, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier         = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icono, null, tint = color, modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.height(6.dp))
        Text(valor, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = color)
        Text(label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SECCIONES DE DATOS
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun SeccionDatosPersonales(u: UsuarioEntity) {
    SeccionCard(stringResource(id = R.string.perfil_datos_personales), Icons.Default.Person, MaterialTheme.colorScheme.primary) {
        FilaDato(stringResource(id = R.string.perfil_nombre),      u.nombreUsuario,            Icons.Default.Badge)
        FilaDato(stringResource(id = R.string.perfil_email),       u.email,                    Icons.Default.Email)
        FilaDato(stringResource(id = R.string.perfil_sexo),        u.sexo,                     Icons.Default.People)
        FilaDato(stringResource(id = R.string.perfil_nacimiento),  formatarFecha(u.fechaNacimeinto), Icons.Default.Cake)
        if (u.alergias.isNotBlank())
            FilaDato(stringResource(id = R.string.perfil_alergias), u.alergias, Icons.Default.WarningAmber)
    }
}

@Composable
fun SeccionDatosFisicos(u: UsuarioEntity) {
    SeccionCard(stringResource(id = R.string.perfil_datos_fisicos), Icons.Default.DirectionsRun, MaterialTheme.colorScheme.secondary) {
        FilaDatoNum(stringResource(id = R.string.perfil_altura),        "${u.altura} cm",          Icons.Default.Height,        MaterialTheme.colorScheme.primary)
        FilaDatoNum(stringResource(id = R.string.perfil_peso_actual),   "${u.peso} kg",            Icons.Default.FitnessCenter, MaterialTheme.colorScheme.secondary)
        FilaDatoNum(stringResource(id = R.string.perfil_peso_objetivo), "${u.pesoObjetivo} kg",    Icons.Default.TrackChanges,  MaterialTheme.colorScheme.error)
        FilaDatoNum(stringResource(id = R.string.perfil_dias_semana),   "${u.diasEntrenamiento} días", Icons.Default.CalendarMonth, MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun SeccionCard(
    titulo:    String,
    icono:     ImageVector,
    color:     Color,
    contenido: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier  = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        shape     = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier         = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icono, null, tint = color, modifier = Modifier.size(20.dp))
                }
                Spacer(Modifier.width(10.dp))
                Text(titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            Spacer(Modifier.height(10.dp))
            contenido()
        }
    }
}

@Composable
fun FilaDato(label: String, valor: String, icono: ImageVector) {
    Row(
        modifier          = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icono, null, Modifier.size(16.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.width(8.dp))
        Text(label, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant,
             modifier = Modifier.width(110.dp))
        Text(valor, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun FilaDatoNum(label: String, valor: String, icono: ImageVector, color: Color) {
    Row(
        modifier          = Modifier.fillMaxWidth().padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier         = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.10f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icono, null, Modifier.size(15.dp), tint = color)
        }
        Spacer(Modifier.width(10.dp))
        Text(label, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant,
             modifier = Modifier.weight(1f))
        Text(valor, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = color)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// EDITOR MODAL COMPLETO
// ─────────────────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorPerfilDialog(
    usuario:   UsuarioEntity,
    onGuardar: (UsuarioEntity) -> Unit,
    onDismiss: () -> Unit
) {
    var nombre   by remember { mutableStateOf(usuario.nombreUsuario) }
    var altura   by remember { mutableStateOf(usuario.altura.toString()) }
    var peso     by remember { mutableStateOf(usuario.peso.toString()) }
    var pesoObj  by remember { mutableStateOf(usuario.pesoObjetivo.toString()) }
    var diasEnt  by remember { mutableStateOf(usuario.diasEntrenamiento.toString()) }
    var alergias by remember { mutableStateOf(usuario.alergias) }
    var objetivo by remember { mutableStateOf(usuario.objetivo) }
    var nivel    by remember { mutableStateOf(usuario.nivel) }

    val sPerderPeso = stringResource(id = R.string.registro_perder_peso)
    val sGanarMusculo = stringResource(id = R.string.registro_ganar_musculo)
    val sMantener = stringResource(id = R.string.perfil_mantenimiento)
    val sDefinicion = stringResource(id = R.string.perfil_definicion)
    val objetivos = listOf(sPerderPeso, sGanarMusculo, sMantener, sDefinicion)

    val sPrincipiante = stringResource(id = R.string.perfil_principiante)
    val sIntermedio = stringResource(id = R.string.perfil_intermedio)
    val sAvanzado = stringResource(id = R.string.registro_avanzado)
    val niveles   = listOf(sPrincipiante, sIntermedio, sAvanzado)

    Dialog(
        onDismissRequest = onDismiss,
        properties       = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.95f).fillMaxHeight(0.92f),
            shape    = RoundedCornerShape(24.dp)
        ) {
            Column(Modifier.fillMaxSize()) {

                // Cabecera gradiente
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.horizontalGradient(listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.primary)))
                        .padding(20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Edit, null, modifier = Modifier.size(22.dp), tint = Color.White)
                        Spacer(Modifier.width(10.dp))
                        Text(stringResource(id = R.string.perfil_editar_perfil_titulo), fontWeight = FontWeight.Bold,
                             fontSize = 18.sp, color = Color.White, modifier = Modifier.weight(1f))
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, null, tint = Color.White)
                        }
                    }
                }

                // Campos
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    OutlinedTextField(
                        value = nombre, onValueChange = { nombre = it },
                        label = { Text(stringResource(id = R.string.perfil_nombre_usuario)) },
                        leadingIcon = { Icon(Icons.Default.Person, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp), singleLine = true
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(
                            value = altura, onValueChange = { altura = it },
                            label = { Text(stringResource(id = R.string.perfil_altura_cm)) }, modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp), singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = peso, onValueChange = { peso = it },
                            label = { Text(stringResource(id = R.string.perfil_peso_kg)) }, modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp), singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(
                            value = pesoObj, onValueChange = { pesoObj = it },
                            label = { Text(stringResource(id = R.string.perfil_peso_objetivo_kg)) }, modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp), singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = diasEnt, onValueChange = { diasEnt = it },
                            label = { Text(stringResource(id = R.string.perfil_dias_semana)) }, modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp), singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }

                    Text(stringResource(id = R.string.perfil_objetivo), fontWeight = FontWeight.SemiBold, fontSize = 13.sp,
                         color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        objetivos.forEach { obj ->
                            FilterChip(
                                selected = objetivo == obj,
                                onClick  = { objetivo = obj },
                                label    = { Text(obj, fontSize = 12.sp) }
                            )
                        }
                    }

                    Text(stringResource(id = R.string.perfil_nivel), fontWeight = FontWeight.SemiBold, fontSize = 13.sp,
                         color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        niveles.forEach { niv ->
                            FilterChip(
                                selected = nivel == niv,
                                onClick  = { nivel = niv },
                                label    = { Text(niv, fontSize = 12.sp) }
                            )
                        }
                    }

                    OutlinedTextField(
                        value = alergias, onValueChange = { alergias = it },
                        label = { Text(stringResource(id = R.string.perfil_restricciones)) },
                        leadingIcon = { Icon(Icons.Default.WarningAmber, null) },
                        placeholder = { Text(stringResource(id = R.string.perfil_ninguna)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp), minLines = 2, maxLines = 3
                    )
                }

                // Botones
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text(stringResource(id = R.string.perfil_cancelar)) }

                    Button(
                        onClick = {
                            onGuardar(usuario.copy(
                                nombreUsuario     = nombre.trim(),
                                altura            = altura.toIntOrNull()    ?: usuario.altura,
                                peso              = peso.toFloatOrNull()    ?: usuario.peso,
                                pesoObjetivo      = pesoObj.toFloatOrNull() ?: usuario.pesoObjetivo,
                                diasEntrenamiento = diasEnt.toIntOrNull()   ?: usuario.diasEntrenamiento,
                                alergias          = alergias.trim(),
                                objetivo          = objetivo,
                                nivel             = nivel
                            ))
                        },
                        modifier = Modifier.weight(1f),
                        shape    = RoundedCornerShape(12.dp),
                        colors   = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(Icons.Default.Check, null, Modifier.size(18.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(stringResource(id = R.string.perfil_guardar))
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// HELPER
// ─────────────────────────────────────────────────────────────────────────────
private fun formatarFecha(ms: Long): String {
    if (ms == 0L) return "No indicado"
    val c = java.util.Calendar.getInstance().apply { timeInMillis = ms }
    return "%02d/%02d/%d".format(
        c.get(java.util.Calendar.DAY_OF_MONTH),
        c.get(java.util.Calendar.MONTH) + 1,
        c.get(java.util.Calendar.YEAR)
    )
}
