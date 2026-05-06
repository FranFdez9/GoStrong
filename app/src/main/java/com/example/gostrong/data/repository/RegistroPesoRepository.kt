package com.example.gostrong.data.repository

import com.example.gostrong.data.local.dao.RegistroPesoDao
import com.example.gostrong.data.local.entity.RegistroPesoEntity
import kotlinx.coroutines.flow.Flow

class RegistroPesoRepository(private val dao: RegistroPesoDao) {

    suspend fun insertarRegistro(registro: RegistroPesoEntity) {
        dao.insertarRegistro(registro)
    }

    fun obtenerRegistrosPorEmail(email: String): Flow<List<RegistroPesoEntity>> {
        return dao.obtenerRegistrosPorEmail(email)
    }

    suspend fun obtenerUltimoRegistro(email: String): RegistroPesoEntity? {
        return dao.obtenerUltimoRegistro(email)
    }

    suspend fun eliminarTodosLosRegistros(email: String) {
        dao.eliminarTodosLosRegistros(email)
    }
}