package com.example.gostrong.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gostrong.data.local.entity.RegistroPesoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistroPesoDao {
    // Inserta un nuevo registro de peso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRegistro(registro: RegistroPesoEntity)

    // Obtiene todos los registros de un usuario ordenados por fecha ascendente
    // Devuelve Flow para que la UI se actualice automáticamente al haber cambios
    @Query("SELECT * FROM registros_peso WHERE emailUsuario = :email ORDER BY fecha ASC")
    fun obtenerRegistrosPorEmail(email: String): Flow<List<RegistroPesoEntity>>

    // Obtiene el último registro de peso del usuario
    @Query("SELECT * FROM registros_peso WHERE emailUsuario = :email ORDER BY fecha DESC LIMIT 1")
    suspend fun obtenerUltimoRegistro(email: String): RegistroPesoEntity?

    // Elimina todos los registros de un usuario (por si quiere resetear su progreso)
    @Query("DELETE FROM registros_peso WHERE emailUsuario = :email")
    suspend fun eliminarTodosLosRegistros(email: String)
}