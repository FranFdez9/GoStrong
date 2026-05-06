package com.example.gostrong.data.repository

import com.example.gostrong.data.local.dao.ComidaDao
import com.example.gostrong.data.local.dao.DietaDao
import com.example.gostrong.data.local.entity.ComidaEntity
import com.example.gostrong.data.local.entity.DietaEntity
import kotlinx.coroutines.flow.Flow

class DietaRepository(
    private val dietaDao: DietaDao,
    private val comidaDao: ComidaDao
) {
    fun getDietasPorUsuario(email: String): Flow<List<DietaEntity>> =
        dietaDao.getDietasPorUsuario(email)

    suspend fun getDietaPorId(id: Int): DietaEntity? =
        dietaDao.getDietaPorId(id)

    suspend fun eliminarDieta(dieta: DietaEntity) =
        dietaDao.eliminarDieta(dieta)

    fun getComidasPorDieta(dietaId: Int): Flow<List<ComidaEntity>> =
        comidaDao.getComidasPorDieta(dietaId)

    fun getComidasPorDia(dietaId: Int, dia: String): Flow<List<ComidaEntity>> =
        comidaDao.getComidasPorDia(dietaId, dia)

    // Guarda dieta + comidas en una sola operación
    suspend fun guardarDietaCompleta(
        dieta: DietaEntity,
        comidas: List<ComidaEntity>
    ): Long {
        val nuevoId = dietaDao.insertarDieta(dieta)
        val comidasConId = comidas.map { it.copy(dietaId = nuevoId.toInt()) }
        comidaDao.insertarComidas(comidasConId)
        return nuevoId
    }
}
