package com.example.gostrong.data.util

import android.content.Context

/**
 * Gestiona la preferencia del tema de accesibilidad visual del usuario.
 * Se guarda en SharedPreferences para que persista entre sesiones.
 *
 * Modos disponibles:
 *  - NORMAL       → Tema estándar
 *  - DEUTERANOPIA → Daltonismo rojo-verde (más común, ~6% hombres)
 *  - PROTANOPIA   → Sensibilidad reducida al rojo
 *  - TRITANOPIA   → Daltonismo azul-amarillo
 */
class ThemeManager(context: Context) {

    private val preferencias = context.getSharedPreferences(
        "preferencias_tema",
        Context.MODE_PRIVATE
    )

    companion object {
        const val NORMAL = "NORMAL"
        const val DEUTERANOPIA = "DEUTERANOPIA"
        const val PROTANOPIA = "PROTANOPIA"
        const val TRITANOPIA = "TRITANOPIA"
    }

    fun guardarModo(modo: String) {
        preferencias.edit().putString("modo_color", modo).apply()
    }

    fun obtenerModo(): String {
        return preferencias.getString("modo_color", NORMAL) ?: NORMAL
    }
}
