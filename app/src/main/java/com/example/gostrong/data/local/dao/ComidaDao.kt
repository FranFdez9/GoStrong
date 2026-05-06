package com.example.gostrong.data.local.dao

import androidx.room.*
import com.example.gostrong.data.local.entity.ComidaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComidaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarComida(comida: ComidaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarComidas(comidas: List<ComidaEntity>)

    @Delete
    suspend fun eliminarComida(comida: ComidaEntity)

    @Query("DELETE FROM comidas WHERE dietaId = :dietaId")
    suspend fun eliminarComidasDe(dietaId: Int)

    @Query("SELECT * FROM comidas WHERE dietaId = :dietaId ORDER BY dia, orden")
    fun getComidasPorDieta(dietaId: Int): Flow<List<ComidaEntity>>

    @Query("SELECT * FROM comidas WHERE dietaId = :dietaId AND dia = :dia ORDER BY orden")
    fun getComidasPorDia(dietaId: Int, dia: String): Flow<List<ComidaEntity>>
}
