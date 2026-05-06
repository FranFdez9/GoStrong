package com.example.gostrong.data.repository

import com.example.gostrong.data.local.dao.EjercicioDao
import com.example.gostrong.data.local.dao.RutinaDao
import com.example.gostrong.data.local.entity.EjercicioEntity
import com.example.gostrong.data.local.entity.RutinaEntity
import kotlinx.coroutines.flow.Flow

class RutinaRepository(
    private val rutinaDao: RutinaDao,
    private val ejercicioDao: EjercicioDao
) {
    fun getRutinasPorUsuario(email: String): Flow<List<RutinaEntity>> =
        rutinaDao.getRutinasPorUsuario(email)

    suspend fun getRutinaPorId(id: Int): RutinaEntity? =
        rutinaDao.getRutinaPorId(id)

    suspend fun eliminarRutina(rutina: RutinaEntity) =
        rutinaDao.eliminarRutina(rutina)

    fun getEjerciciosPorRutina(rutinaId: Int): Flow<List<EjercicioEntity>> =
        ejercicioDao.getEjerciciosPorRutina(rutinaId)

    fun getEjerciciosPorDia(rutinaId: Int, dia: String): Flow<List<EjercicioEntity>> =
        ejercicioDao.getEjerciciosPorDia(rutinaId, dia)

    // Guarda rutina + ejercicios en una sola operación
    suspend fun guardarRutinaCompleta(
        rutina: RutinaEntity,
        ejercicios: List<EjercicioEntity>
    ): Long {
        val nuevoId = rutinaDao.insertarRutina(rutina)
        val ejerciciosConId = ejercicios.map { it.copy(rutinaId = nuevoId.toInt()) }
        ejercicioDao.insertarEjercicios(ejerciciosConId)
        return nuevoId
    }
}