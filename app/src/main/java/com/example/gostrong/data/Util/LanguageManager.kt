package com.example.gostrong.data.util

import android.content.Context

class LanguageManager(context: Context) {

    private val preferencias = context.getSharedPreferences(
        "preferencias_idioma",
        Context.MODE_PRIVATE
    )

    fun guardarIdioma(codigoIdioma: String) {
        preferencias.edit().putString("codigo_idioma", codigoIdioma).apply()
    }

    fun obtenerIdioma(): String {
        // "es" por defecto (español)
        return preferencias.getString("codigo_idioma", "es") ?: "es"
    }
}
