package com.example.gostrong.data.local.dao

import androidx.room.*
import com.example.gostrong.data.local.entity.DietaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DietaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDieta(dieta: DietaEntity): Long

    @Update
    suspend fun actualizarDieta(dieta: DietaEntity)

    @Delete
    suspend fun eliminarDieta(dieta: DietaEntity)

    @Query("SELECT * FROM dietas WHERE emailUsuario = :email ORDER BY fechaCreacion DESC")
    fun getDietasPorUsuario(email: String): Flow<List<DietaEntity>>

    @Query("SELECT * FROM dietas WHERE id = :id")
    suspend fun getDietaPorId(id: Int): DietaEntity?

    @Query("SELECT * FROM dietas WHERE emailUsuario = :email AND objetivo = :objetivo ORDER BY fechaCreacion DESC")
    fun getDietasPorObjetivo(email: String, objetivo: String): Flow<List<DietaEntity>>
}
