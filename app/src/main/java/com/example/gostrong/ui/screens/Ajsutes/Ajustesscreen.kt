package com.example.gostrong.ui.screens.ajustes

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.annotation.StringRes
import com.example.gostrong.R
import com.example.gostrong.data.util.ThemeManager
import com.example.gostrong.data.util.LanguageManager
import android.app.Activity


import com.example.gostrong.ui.theme.modoColorActual

/**
 * Pantalla de Ajustes.
 * Permite al usuario seleccionar su modo de visualización de color.
 * El cambio se aplica en tiempo real a toda la app y se guarda en SharedPreferences.
 */

// Datos de cada modo de color para mostrar en la UI
data class ModoColor(
    val id: String,
    @StringRes val nombreRes: Int,
    @StringRes val descripcionRes: Int,
    val colores: List<Color>  // Muestra de los colores del tema
)

val modosList = listOf(
    ModoColor(
        id = ThemeManager.NORMAL,
        nombreRes = R.string.modo_normal,
        descripcionRes = R.string.modo_normal_desc,
        colores = listOf(
            Color(0xFF1565C0),
            Color(0xFF26A69A),
            Color(0xFFB00020),
            Color(0xFFF5F5F5)
        )
    ),
    ModoColor(
        id = ThemeManager.DEUTERANOPIA,
        nombreRes = R.string.modo_deuteranopia,
        descripcionRes = R.string.modo_deuteranopia_desc,
        colores = listOf(
            Color(0xFF0072B2),
            Color(0xFFE69F00),
            Color(0xFFD55E00),
            Color(0xFFF5F5F5)
        )
    ),
    ModoColor(
        id = ThemeManager.PROTANOPIA,
        nombreRes = R.string.modo_protanopia,
        descripcionRes = R.string.modo_protanopia_desc,
        colores = listOf(
            Color(0xFF0077BB),
            Color(0xFFFFCC00),
            Color(0xFF994F00),
            Color(0xFFF5F5F5)
        )
    ),
    ModoColor(
        id = ThemeManager.TRITANOPIA,
        nombreRes = R.string.modo_tritanopia,
        descripcionRes = R.string.modo_tritanopia_desc,
        colores = listOf(
            Color(0xFFCC3399),
            Color(0xFF44AA99),
            Color(0xFFAA4400),
            Color(0xFFF5F5F5)
        )
    )
)

@Composable
fun AjustesScreen() {

    val context = LocalContext.current
    val themeManager = remember { ThemeManager(context) }
    val languageManager = remember { LanguageManager(context) }

    // Modo actualmente seleccionado (empieza con el guardado)
    var modoSeleccionado by remember { mutableStateOf(themeManager.obtenerModo()) }
    var idiomaSeleccionado by remember { mutableStateOf(languageManager.obtenerIdioma()) }

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
            text = stringResource(id = R.string.ajustes_titulo),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(id = R.string.ajustes_accesibilidad_visual),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = stringResource(id = R.string.ajustes_desc_accesibilidad),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

        // -----------------------------
        // TARJETAS DE SELECCIÓN
        // -----------------------------
        modosList.forEach { modo ->
            TarjetaModoColor(
                modo = modo,
                seleccionado = modoSeleccionado == modo.id,
                onClick = {
                    // Aplica el cambio en tiempo real
                    modoSeleccionado = modo.id
                    modoColorActual = modo.id
                    themeManager.guardarModo(modo.id)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // -----------------------------
        // SELECCIÓN DE IDIOMA
        // -----------------------------
        Text(
            text = stringResource(id = R.string.ajustes_idioma_app),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TarjetaIdioma(
                nombre = "Español",
                seleccionado = idiomaSeleccionado == "es",
                onClick = {
                    if (idiomaSeleccionado != "es") {
                        languageManager.guardarIdioma("es")
                        idiomaSeleccionado = "es"
                        val activity = context as? Activity
                        activity?.recreate()
                    }
                },
                modifier = Modifier.weight(1f)
            )
            TarjetaIdioma(
                nombre = "English",
                seleccionado = idiomaSeleccionado == "en",
                onClick = {
                    if (idiomaSeleccionado != "en") {
                        languageManager.guardarIdioma("en")
                        idiomaSeleccionado = "en"
                        val activity = context as? Activity
                        activity?.recreate()
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // -----------------------------
        // NOTA INFORMATIVA
        // -----------------------------
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = R.string.ajustes_info_modos_titulo),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = stringResource(id = R.string.ajustes_info_modos_desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }
    }
}

// -----------------------------
// COMPONENTE: Tarjeta de modo de color
// -----------------------------
@Composable
fun TarjetaModoColor(
    modo: ModoColor,
    seleccionado: Boolean,
    onClick: () -> Unit
) {
    val borderColor by animateColorAsState(
        targetValue = if (seleccionado) MaterialTheme.colorScheme.primary else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "borderColor"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (seleccionado)
            MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
        else
            MaterialTheme.colorScheme.surface,
        animationSpec = tween(durationMillis = 300),
        label = "backgroundColor"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (seleccionado) 4.dp else 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Muestra de colores del tema
            MuestraColores(colores = modo.colores)

            // Texto
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = modo.nombreRes),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(id = modo.descripcionRes),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            // Icono de seleccionado
            if (seleccionado) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Seleccionado",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// -----------------------------
// COMPONENTE: Muestra visual de colores
// -----------------------------
@Composable
fun MuestraColores(colores: List<Color>) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        // Cuadrícula 2x2 con los 4 colores del tema
        Column {
            Row(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(colores.getOrElse(0) { Color.Gray })
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(colores.getOrElse(1) { Color.LightGray })
                )
            }
            Row(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(colores.getOrElse(2) { Color.DarkGray })
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(colores.getOrElse(3) { Color.White })
                )
            }
        }
    }
}

// -----------------------------
// COMPONENTE: Tarjeta de Idioma
// -----------------------------
@Composable
fun TarjetaIdioma(
    nombre: String,
    seleccionado: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor by animateColorAsState(
        targetValue = if (seleccionado) MaterialTheme.colorScheme.primary else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "borderColor"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (seleccionado)
            MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
        else
            MaterialTheme.colorScheme.surface,
        animationSpec = tween(durationMillis = 300),
        label = "backgroundColor"
    )

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (seleccionado) 4.dp else 1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}