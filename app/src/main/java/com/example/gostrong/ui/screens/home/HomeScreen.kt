package com.example.gostrong.ui.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.example.gostrong.R
import com.example.gostrong.viewmodel.UsuarioViewModel
import java.util.Calendar

@Composable
fun HomeScreen(
    usuarioViewModel: UsuarioViewModel,
    emailUsuario: String,
    onIrARutinas: () -> Unit,
    onIrADietas: () -> Unit,
    onIrASalud: () -> Unit,
    onIrAPerfil: () -> Unit,
    onIrAAjustes: () -> Unit
) {
    val context = LocalContext.current

    // Cargamos el usuario para obtener el nombre
    var nombreUsuario by remember { mutableStateOf("") }
    LaunchedEffect(emailUsuario) {
        if (emailUsuario.isNotBlank()) {
            usuarioViewModel.obtenerUsuarioPorEmail(emailUsuario) { usuario ->
                nombreUsuario = usuario?.nombreUsuario ?: ""
            }
        }
    }

    val sBuenosDias = stringResource(id = R.string.home_buenos_dias)
    val sBuenasTardes = stringResource(id = R.string.home_buenas_tardes)
    val sBuenasNoches = stringResource(id = R.string.home_buenas_noches)

    // Saludo según la hora
    val saludoTexto = remember {
        val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        when {
            hora < 13 -> sBuenosDias
            hora < 20 -> sBuenasTardes
            else      -> sBuenasNoches
        }
    }

    // Frase del día según el día de la semana
    val arrFrases = stringArrayResource(id = R.array.home_frases)
    val arrFrasesSub = stringArrayResource(id = R.array.home_frases_sub)
    val (frase, subtitulo) = remember(arrFrases, arrFrasesSub) {
        val diaSemana = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1
        val arrIdx = diaSemana % arrFrases.size
        arrFrases[arrIdx] to arrFrasesSub[arrIdx]
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // ── HEADER CON GRADIENTE ─────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.primary)
                    )
                )
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Column {
                // Saludo
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (nombreUsuario.isNotBlank())
                                nombreUsuario.first().uppercaseChar().toString()
                            else "G",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "$saludoTexto${if (nombreUsuario.isNotBlank()) ", $nombreUsuario" else ""}!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = stringResource(id = R.string.home_listo_entrenar),
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.75f)
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Frase motivacional
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.15f)
                    ),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(id = R.string.home_frase_del_dia),
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = frase,
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 20.sp
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = subtitulo,
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // ── ACCESOS DIRECTOS ─────────────────────────────────
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {

            Text(
                text = stringResource(id = R.string.home_accesos_rapidos),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 14.dp)
            )

            // Fila 1: Rutinas y Dietas (más grandes)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AccesoCard(
                    modifier    = Modifier.weight(1f),
                    emoji       = "🏋️",
                    titulo      = stringResource(id = R.string.home_rutinas),
                    descripcion = stringResource(id = R.string.home_planes_entrenamiento),
                    gradient    = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)),
                    onClick     = onIrARutinas
                )
                AccesoCard(
                    modifier    = Modifier.weight(1f),
                    emoji       = "🍽️",
                    titulo      = stringResource(id = R.string.home_dietas),
                    descripcion = stringResource(id = R.string.home_planes_alimentacion),
                    gradient    = listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)),
                    onClick     = onIrADietas
                )
            }

            Spacer(Modifier.height(12.dp))

            // Fila 2: Salud, Perfil y Ajustes (más pequeñas)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AccesoCardSmall(
                    modifier = Modifier.weight(1f),
                    emoji    = "❤️",
                    titulo   = stringResource(id = R.string.home_salud),
                    gradient = listOf(MaterialTheme.colorScheme.error, MaterialTheme.colorScheme.error.copy(alpha = 0.7f)),
                    onClick  = onIrASalud
                )
                AccesoCardSmall(
                    modifier = Modifier.weight(1f),
                    emoji    = "👤",
                    titulo   = stringResource(id = R.string.home_perfil),
                    gradient = listOf(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.secondaryContainer),
                    onClick  = onIrAPerfil
                )
                AccesoCardSmall(
                    modifier = Modifier.weight(1f),
                    emoji    = "⚙️",
                    titulo   = stringResource(id = R.string.home_ajustes),
                    gradient = listOf(MaterialTheme.colorScheme.outline, MaterialTheme.colorScheme.outlineVariant),
                    onClick  = onIrAAjustes
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // ── BANNER GOSTRONG ──────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("💪", fontSize = 40.sp)
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "GoStrong",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = stringResource(id = R.string.home_gostrong_desc),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}

// ── CARD GRANDE DE ACCESO ─────────────────────────────────────
@Composable
fun AccesoCard(
    modifier: Modifier,
    emoji: String,
    titulo: String,
    descripcion: String,
    gradient: List<Color>,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(130.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(gradient))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                Text(emoji, fontSize = 30.sp)
                Column {
                    Text(titulo, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.White)
                    Text(descripcion, fontSize = 11.sp, color = Color.White.copy(alpha = 0.8f), lineHeight = 14.sp)
                }
            }
        }
    }
}

// ── CARD PEQUEÑA DE ACCESO ────────────────────────────────────
@Composable
fun AccesoCardSmall(
    modifier: Modifier,
    emoji: String,
    titulo: String,
    gradient: List<Color>,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(90.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(gradient)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(emoji, fontSize = 24.sp)
                Spacer(Modifier.height(4.dp))
                Text(titulo, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = Color.White)
            }
        }
    }
}
