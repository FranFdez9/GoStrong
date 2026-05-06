package com.example.gostrong.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gostrong.data.local.entity.HistorialActividadEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistorialActividadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistorial(historial: HistorialActividadEntity)

    // Obtener todos los historiales de un usuario (para la línea temporal general)
    @Query("SELECT * FROM historial_actividad WHERE emailUsuario = :email ORDER BY fecha DESC")
    fun getHistorialPorUsuario(email: String): Flow<List<HistorialActividadEntity>>

    // Filtrar sólo por rutinas
    @Query("SELECT * FROM historial_actividad WHERE emailUsuario = :email AND tipo = 'RUTINA' ORDER BY fecha DESC")
    fun getHistorialRutinas(email: String): Flow<List<HistorialActividadEntity>>

    // Filtrar sólo por dietas
    @Query("SELECT * FROM historial_actividad WHERE emailUsuario = :email AND tipo = 'DIETA' ORDER BY fecha DESC")
    fun getHistorialDietas(email: String): Flow<List<HistorialActividadEntity>>
    
    // Obtener historiales en un rango de fechas (ej: rachas semanales)
    @Query("SELECT * FROM historial_actividad WHERE emailUsuario = :email AND fecha BETWEEN :inicio AND :fin ORDER BY fecha ASC")
    fun getHistorialRangoFechas(email: String, inicio: Long, fin: Long): Flow<List<HistorialActividadEntity>>
}
