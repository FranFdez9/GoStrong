package com.example.gostrong.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gostrong.data.local.entity.HistorialActividadEntity
import com.example.gostrong.data.repository.HistorialActividadRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistorialViewModel(
    private val historialRepository: HistorialActividadRepository
) : ViewModel() {

    // Variables que "escucharán" las pantallas en tiempo real
    private val _historialCompleto = MutableStateFlow<List<HistorialActividadEntity>>(emptyList())
    val historialCompleto: StateFlow<List<HistorialActividadEntity>> = _historialCompleto.asStateFlow()

    private val _historialRutinas = MutableStateFlow<List<HistorialActividadEntity>>(emptyList())
    val historialRutinas: StateFlow<List<HistorialActividadEntity>> = _historialRutinas.asStateFlow()

    // 1. Cargar el histórico entero al entrar a la app
    fun cargarHistorial(emailUsuario: String) {
        viewModelScope.launch {
            historialRepository.obtenerHistorialCompleto(emailUsuario).collect { lista ->
                _historialCompleto.value = lista
            }
        }

        viewModelScope.launch {
            historialRepository.obtenerHistorialRutinas(emailUsuario).collect { lista ->
                _historialRutinas.value = lista
            }
        }
    }

    // 2. Función clave: "Marcar hábito de hoy"
    // Los estados pueden ser: "COMPLETO", "A MEDIAS", "SALTADO"
    fun registrarSesion(
        emailUsuario: String,
        tipo: String,
        referenciaId: Int,
        titulo: String,
        estado: String,
        notas: String
    ) {
        val nuevoRegistro = HistorialActividadEntity(
            emailUsuario = emailUsuario,
            fecha = System.currentTimeMillis(), // Guarda la fecha y hora exacta
            tipo = tipo,
            referenciaId = referenciaId,
            titulo = titulo,
            estado = estado,
            notas = notas
        )

        viewModelScope.launch {
            historialRepository.registrarActividad(nuevoRegistro)
        }
    }
}
