package com.example.gostrong.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Representa un ejercicio dentro de una rutina.
 *
 * - En rutinas SEMANALES: el campo [dia] indica el día ("Lunes", "Martes"...)
 * - En rutinas por SESION: el campo [dia] es null o vacío
 *
 * Tiene FK hacia RutinaEntity con CASCADE para que al borrar
 * una rutina se borren automáticamente sus ejercicios.
 */
@Entity(
    tableName = "ejercicios",
    foreignKeys = [
        ForeignKey(
            entity = RutinaEntity::class,
            parentColumns = ["id"],
            childColumns = ["rutinaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["rutinaId"])]
)
data class EjercicioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val rutinaId: Int,              // FK hacia RutinaEntity
    val nombre: String,             // Ej: "Jab-Cross", "Flexiones", "Sentadillas"
    val series: Int,                // Número de series
    val repeticiones: String,       // Ej: "12", "3min", "hasta el fallo"
    val descanso: String,           // Ej: "60s", "2min"
    val dia: String = "",           // "Lunes", "Martes"... (vacío en rutinas por sesión)
    val orden: Int = 0,             // Orden dentro del día o sesión
    val notas: String = ""          // Notas editables por el usuario
)
