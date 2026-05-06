package com.example.gostrong.data.repository

import com.example.gostrong.data.local.dao.HistorialActividadDao
import com.example.gostrong.data.local.entity.HistorialActividadEntity
import kotlinx.coroutines.flow.Flow

class HistorialActividadRepository(private val historialDao: HistorialActividadDao) {

    // 1. Guardar o actualizar un registro del día
    suspend fun registrarActividad(historial: HistorialActividadEntity) {
        historialDao.insertHistorial(historial)
    }

    // 2. Obtener ABSOLUTAMENTE TODO (Para una vista de calendario o timeline)
    fun obtenerHistorialCompleto(emailUsuario: String): Flow<List<HistorialActividadEntity>> {
        return historialDao.getHistorialPorUsuario(emailUsuario)
    }

    // 3. Obtener solo cuándo hemos hecho RUTINAS
    fun obtenerHistorialRutinas(emailUsuario: String): Flow<List<HistorialActividadEntity>> {
        return historialDao.getHistorialRutinas(emailUsuario)
    }

    // 4. Obtener solo cuándo hemos seguido o saltado las DIETAS
    fun obtenerHistorialDietas(emailUsuario: String): Flow<List<HistorialActividadEntity>> {
        return historialDao.getHistorialDietas(emailUsuario)
    }
}
