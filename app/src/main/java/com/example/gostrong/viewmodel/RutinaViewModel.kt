package com.example.gostrong.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gostrong.data.catalog.CatalogoRutinas
import com.example.gostrong.data.catalog.RutinaTemplate
import com.example.gostrong.data.local.entity.EjercicioEntity
import com.example.gostrong.data.local.entity.RutinaEntity
import com.example.gostrong.data.repository.RutinaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RutinaViewModel(
    private val repository: RutinaRepository
) : ViewModel() {

    // Rutina seleccionada para ver el detalle
    private val _rutinaSeleccionada = MutableStateFlow<RutinaEntity?>(null)
    val rutinaSeleccionada: StateFlow<RutinaEntity?> = _rutinaSeleccionada

    // Feedback de operación (true = éxito, false = error, null = sin estado)
    private val _operacionExitosa = MutableStateFlow<Boolean?>(null)
    val operacionExitosa: StateFlow<Boolean?> = _operacionExitosa

    fun getRutinasPorUsuario(email: String): Flow<List<RutinaEntity>> =
        repository.getRutinasPorUsuario(email)

    fun getEjerciciosPorRutina(rutinaId: Int): Flow<List<EjercicioEntity>> =
        repository.getEjerciciosPorRutina(rutinaId)

    fun getEjerciciosPorDia(rutinaId: Int, dia: String): Flow<List<EjercicioEntity>> =
        repository.getEjerciciosPorDia(rutinaId, dia)

    fun seleccionarRutina(rutina: RutinaEntity) {
        _rutinaSeleccionada.value = rutina
    }

    fun guardarRutinaDeCatalogo(template: RutinaTemplate, emailUsuario: String) {
        viewModelScope.launch {
            try {
                val rutina = RutinaEntity(
                    emailUsuario = emailUsuario,
                    nombre       = template.nombre,
                    deporte      = template.deporte,
                    nivel        = template.nivel,
                    diasSemana   = template.diasSemana,
                    tipo         = template.tipo
                )
                repository.guardarRutinaCompleta(rutina, template.ejercicios)
                _operacionExitosa.value = true
            } catch (e: Exception) {
                _operacionExitosa.value = false
            }
        }
    }

    fun eliminarRutina(rutina: RutinaEntity) {
        viewModelScope.launch { repository.eliminarRutina(rutina) }
    }

    fun resetOperacion() {
        _operacionExitosa.value = null
    }
}