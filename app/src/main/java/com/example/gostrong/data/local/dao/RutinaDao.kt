package com.example.gostrong.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gostrong.data.local.entity.RutinaEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RutinaDao {

    // ── INSERT ──────────────────────────────────────────────
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRutina(rutina: RutinaEntity): Long  // Devuelve el ID generado

    // ── UPDATE ──────────────────────────────────────────────
    @Update
    suspend fun actualizarRutina(rutina: RutinaEntity)

    // ── DELETE ──────────────────────────────────────────────
    @Delete
    suspend fun eliminarRutina(rutina: RutinaEntity)


    // Todas las rutinas de un usuario (Flow para reactividad)
    @Query("SELECT * FROM rutinas WHERE emailUsuario = :email ORDER BY fechaCreacion DESC")
    fun getRutinasPorUsuario(email: String): Flow<List<RutinaEntity>>

    // Una rutina concreta por ID
    @Query("SELECT * FROM rutinas WHERE id = :id")
    suspend fun getRutinaPorId(id: Int): RutinaEntity?

    // Rutinas filtradas por tipo (SEMANAL o SESION)
    @Query("SELECT * FROM rutinas WHERE emailUsuario = :email AND tipo = :tipo ORDER BY fechaCreacion DESC")
    fun getRutinasPorTipo(email: String, tipo: String): Flow<List<RutinaEntity>>
}