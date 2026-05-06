package com.example.gostrong.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.gostrong.data.util.ThemeManager


// =============================================================================
// TEMA NORMAL (Basado en tu branding Naranja fuerte)
// =============================================================================
private val ColorSchemeNormal = lightColorScheme(
    primary         = Color(0xFFFF6D00),   // Naranja GoStrong Vibrante
    onPrimary       = Color.White,
    secondary       = Color(0xFF1565C0),   // Azul Fuerte (buen contraste para la app normal)
    onSecondary     = Color.White,
    background      = Color(0xFFFBFBFB),
    onBackground    = Color(0xFF212121),
    surface         = Color.White,
    onSurface       = Color(0xFF212121),
    error           = Color(0xFFD32F2F),
    onError         = Color.White
)

// =============================================================================
// DEUTERANOPIA — Dificultad rojo/verde
// Paleta Premium: Azul Marino Puro vs Dorado Ambar
// =============================================================================
private val ColorSchemeDeuteranopia = lightColorScheme(
    primary         = Color(0xFF0336FF),   // Azul eléctrico/marino (perfectamente visible)
    onPrimary       = Color.White,
    secondary       = Color(0xFFFFDE03),   // Amarillo Neón/Dorado (máximo contraste para deuteranopia)
    onSecondary     = Color(0xFF212121),
    background      = Color(0xFFF8F9FA),
    onBackground    = Color(0xFF212121),
    surface         = Color.White,
    onSurface       = Color(0xFF212121),
    error           = Color(0xFFE65100),   // Naranja oscuro como alerta
    onError         = Color.White
)

// =============================================================================
// PROTANOPIA — Ceguera al rojo
// Paleta Premium: Púrpura Oscuro vs Cian Neón
// =============================================================================
private val ColorSchemeProtanopia = lightColorScheme(
    primary         = Color(0xFF6200EA),   // Púrpura profundo (lo perciben como un azul distinto)
    onPrimary       = Color.White,
    secondary       = Color(0xFF00E5FF),   // Cian Neón brillante (super destacable frente al oscuro)
    onSecondary     = Color(0xFF212121),
    background      = Color(0xFFF9F5FF),   // Fondo ligerísimamente tintado para dar sensación premium
    onBackground    = Color(0xFF212121),
    surface         = Color.White,
    onSurface       = Color(0xFF212121),
    error           = Color(0xFFA1887F),   // Marrón para alertas
    onError         = Color.White
)

// =============================================================================
// TRITANOPIA — Dificultad azul/amarillo
// Paleta Premium: Carmesí Intenso vs Verde Lima
// =============================================================================
private val ColorSchemeTritanopia = lightColorScheme(
    primary         = Color(0xFFFF0266),   // Rosa/Carmesí vibrante (no les afecta la ceguera azul/amarilla)
    onPrimary       = Color.White,
    secondary       = Color(0xFF00C853),   // Verde puro y brillante
    onSecondary     = Color(0xFF212121),
    background      = Color(0xFFFFF5F8),
    onBackground    = Color(0xFF212121),
    surface         = Color.White,
    onSurface       = Color(0xFF212121),
    error           = Color(0xFF8D6E63),   
    onError         = Color.White
)

// =============================================================================
// Estado global del modo de color (observable por toda la app)
// =============================================================================
var modoColorActual by mutableStateOf(ThemeManager.NORMAL)

// =============================================================================
// Tema principal de la aplicación
// Se llama desde MainActivity y aplica automáticamente a toda la UI.
// =============================================================================
@Composable
fun GoStrongTheme(
    modo: String = modoColorActual,
    content: @Composable () -> Unit
) {
    val colorScheme = when (modo) {
        ThemeManager.DEUTERANOPIA -> ColorSchemeDeuteranopia
        ThemeManager.PROTANOPIA   -> ColorSchemeProtanopia
        ThemeManager.TRITANOPIA   -> ColorSchemeTritanopia
        else                      -> ColorSchemeNormal
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
