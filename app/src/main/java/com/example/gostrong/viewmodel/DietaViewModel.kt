package com.example.gostrong.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gostrong.data.catalog.CatalogoDietas
import com.example.gostrong.data.catalog.DietaTemplate
import com.example.gostrong.data.local.entity.ComidaEntity
import com.example.gostrong.data.local.entity.DietaEntity
import com.example.gostrong.data.repository.DietaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DietaViewModel(
    private val repository: DietaRepository
) : ViewModel() {

    private val _dietaSeleccionada = MutableStateFlow<DietaEntity?>(null)
    val dietaSeleccionada: StateFlow<DietaEntity?> = _dietaSeleccionada

    private val _operacionExitosa = MutableStateFlow<Boolean?>(null)
    val operacionExitosa: StateFlow<Boolean?> = _operacionExitosa

    fun getDietasPorUsuario(email: String): Flow<List<DietaEntity>> =
        repository.getDietasPorUsuario(email)

    fun getComidasPorDieta(dietaId: Int): Flow<List<ComidaEntity>> =
        repository.getComidasPorDieta(dietaId)

    fun getComidasPorDia(dietaId: Int, dia: String): Flow<List<ComidaEntity>> =
        repository.getComidasPorDia(dietaId, dia)

    fun seleccionarDieta(dieta: DietaEntity) {
        _dietaSeleccionada.value = dieta
    }

    fun guardarDietaDeCatalogo(template: DietaTemplate, emailUsuario: String) {
        viewModelScope.launch {
            try {
                val dieta = DietaEntity(
                    emailUsuario = emailUsuario,
                    nombre       = template.nombre,
                    objetivo     = template.objetivo,
                    restriccion  = template.restriccion,
                    calorias     = template.calorias,
                    tipo         = template.tipo
                )
                repository.guardarDietaCompleta(dieta, template.comidas)
                _operacionExitosa.value = true
            } catch (e: Exception) {
                _operacionExitosa.value = false
            }
        }
    }

    fun eliminarDieta(dieta: DietaEntity) {
        viewModelScope.launch { repository.eliminarDieta(dieta) }
    }

    fun resetOperacion() {
        _operacionExitosa.value = null
    }
}
