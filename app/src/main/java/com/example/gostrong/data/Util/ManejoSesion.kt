package com.example.gostrong.data.util

import android.content.Context

class ManejoSesion(context: Context) {

    private val preferencias = context.getSharedPreferences(
        "sesion_usuario",
        Context.MODE_PRIVATE
    )

    // Guarda la sesión del usuario (login correcto)
    fun guardarSesion(email: String) {
        preferencias.edit()
            .putString("email_usuario", email)
            .apply()
    }

    // Obtiene el email del usuario logueado
    fun obtenerEmailUsuario(): String? {
        return preferencias.getString("email_usuario", null)
    }

    // Comprueba si hay una sesión activa
    fun haySesionActiva(): Boolean {
        return obtenerEmailUsuario() != null
    }

    // Cierra la sesión del usuario (logout)
    fun cerrarSesion() {
        preferencias.edit().clear().apply()
    }
}
