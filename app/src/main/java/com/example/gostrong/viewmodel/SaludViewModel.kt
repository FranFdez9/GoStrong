package com.example.gostrong.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gostrong.data.local.entity.RegistroPesoEntity
import com.example.gostrong.data.local.entity.UsuarioEntity
import com.example.gostrong.data.repository.RegistroPesoRepository
import com.example.gostrong.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class SaludViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val registroPesoRepository: RegistroPesoRepository
) : ViewModel() {

    private val _usuario = MutableStateFlow<UsuarioEntity?>(null)
    val usuario: StateFlow<UsuarioEntity?> = _usuario

    // Carga el usuario por email (el logueado)
    fun cargarUsuario(email: String) {
        viewModelScope.launch {
            _usuario.value = usuarioRepository.obtenerUsuarioPorEmail(email)
        }
    }

    // Devuelve el Flow de registros de peso para el usuario
    fun obtenerRegistrosPeso(email: String): Flow<List<RegistroPesoEntity>> {
        return registroPesoRepository.obtenerRegistrosPorEmail(email)
    }

    // Guarda un nuevo registro de peso
    fun registrarPeso(email: String, peso: Float) {
        viewModelScope.launch {
            registroPesoRepository.insertarRegistro(
                RegistroPesoEntity(
                    emailUsuario = email,
                    peso = peso,
                    fecha = System.currentTimeMillis()
                )
            )
            // Actualiza también el peso actual del usuario
            _usuario.value?.let { u ->
                usuarioRepository.actualizarUsuario(u.copy(peso = peso))
                _usuario.value = u.copy(peso = peso)
            }
        }
    }

    // -----------------------------
    // CÁLCULOS DE SALUD
    // -----------------------------

    // IMC = peso(kg) / altura(m)²
    fun calcularIMC(peso: Float, alturaCm: Int): Float {
        val alturaM = alturaCm / 100f
        return peso / (alturaM * alturaM)
    }

    // Categoría según OMS
    fun categoriaIMC(imc: Float): String {
        return when {
            imc < 18.5f -> "Bajo peso"
            imc < 25f   -> "Peso normal"
            imc < 30f   -> "Sobrepeso"
            imc < 35f   -> "Obesidad grado I"
            imc < 40f   -> "Obesidad grado II"
            else        -> "Obesidad grado III"
        }
    }

    // Descripción de la categoría
    fun descripcionIMC(imc: Float): String {
        return when {
            imc < 18.5f -> "Tu peso está por debajo del rango saludable. Considera aumentar tu ingesta calórica."
            imc < 25f   -> "¡Enhorabuena! Tu peso está dentro del rango saludable."
            imc < 30f   -> "Tu peso está ligeramente por encima del rango saludable."
            imc < 35f   -> "Te encuentras en obesidad grado I. Consulta con un profesional."
            imc < 40f   -> "Te encuentras en obesidad grado II. Es recomendable atención médica."
            else        -> "Te encuentras en obesidad grado III. Busca atención médica especializada."
        }
    }

    // Color indicativo de la categoría IMC (devuelve un string para usarlo en la UI)
    fun colorCategoriaIMC(imc: Float): String {
        return when {
            imc < 18.5f -> "AZUL"
            imc < 25f   -> "VERDE"
            imc < 30f   -> "NARANJA"
            else        -> "ROJO"
        }
    }

    /**
     * TMB (Tasa Metabólica Basal) — Fórmula de Mifflin-St Jeor (1990)
     * Es más precisa que la de Harris-Benedict y la más usada actualmente.
     *
     * Hombre: (10 × peso kg) + (6.25 × altura cm) − (5 × edad) + 5
     * Mujer:  (10 × peso kg) + (6.25 × altura cm) − (5 × edad) − 161
     *
     * Multiplicadores de actividad:
     * Sedentario  × 1.2
     * Normal      × 1.55
     * Avanzado    × 1.725
     */
    fun calcularTMB(usuario: UsuarioEntity): Int {
        val edad = calcularEdad(usuario.fechaNacimeinto)
        val tmb = when (usuario.sexo) {
            "Hombre" -> (10 * usuario.peso) + (6.25f * usuario.altura) - (5 * edad) + 5
            "Mujer"  -> (10 * usuario.peso) + (6.25f * usuario.altura) - (5 * edad) - 161
            else     -> (10 * usuario.peso) + (6.25f * usuario.altura) - (5 * edad) - 78 // Promedio
        }

        val multiplicador = when (usuario.nivel) {
            "Sedentario" -> 1.2f
            "Normal"     -> 1.55f
            "Avanzado"   -> 1.725f
            else         -> 1.2f
        }

        return (tmb * multiplicador).toInt()
    }

    // Calcula la edad a partir de la fecha de nacimiento en milisegundos
    private fun calcularEdad(fechaNacimientoMs: Long): Int {
        val hoy = Calendar.getInstance()
        val nacimiento = Calendar.getInstance().apply { timeInMillis = fechaNacimientoMs }
        var edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR)
        if (hoy.get(Calendar.DAY_OF_YEAR) < nacimiento.get(Calendar.DAY_OF_YEAR)) edad--
        return edad
    }
}