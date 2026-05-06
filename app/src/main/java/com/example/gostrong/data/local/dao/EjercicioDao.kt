package com.example.gostrong.data.local.dao

import androidx.room.*
import com.example.gostrong.data.local.entity.EjercicioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EjercicioDao {

    // ── INSERT ──────────────────────────────────────────────
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEjercicio(ejercicio: EjercicioEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEjercicios(ejercicios: List<EjercicioEntity>)  // Insert en bloque

    // ── UPDATE ──────────────────────────────────────────────
    @Update
    suspend fun actualizarEjercicio(ejercicio: EjercicioEntity)

    // ── DELETE ──────────────────────────────────────────────
    @Delete
    suspend fun eliminarEjercicio(ejercicio: EjercicioEntity)

    @Query("DELETE FROM ejercicios WHERE rutinaId = :rutinaId")
    suspend fun eliminarEjerciciosDe(rutinaId: Int)  // Borrar todos los de una rutina

    // ── QUERIES ─────────────────────────────────────────────

    // Todos los ejercicios de una rutina ordenados por día y orden
    @Query("SELECT * FROM ejercicios WHERE rutinaId = :rutinaId ORDER BY dia, orden")
    fun getEjerciciosPorRutina(rutinaId: Int): Flow<List<EjercicioEntity>>

    // Ejercicios de un día concreto (para rutinas semanales)
    @Query("SELECT * FROM ejercicios WHERE rutinaId = :rutinaId AND dia = :dia ORDER BY orden")
    fun getEjerciciosPorDia(rutinaId: Int, dia: String): Flow<List<EjercicioEntity>>
}