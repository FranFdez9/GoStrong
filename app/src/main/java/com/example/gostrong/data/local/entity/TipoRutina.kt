package com.example.gostrong.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tipos de rutina disponibles:
 * - SEMANAL: distribuida por días de la semana (Lunes pecho, Miércoles espalda...)
 * - SESION: una sesión concreta sin día asignado (Sesión de boxeo, Sesión de cardio...)
 */
enum class TipoRutina {
    SEMANAL,
    SESION
}

/**
 * Representa una rutina generada por IA.
 * Puede ser semanal (con días) o por sesión (sin días).
 */
@Entity(tableName = "rutinas")
data class RutinaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val emailUsuario: String,           // FK lógica al usuario
    val nombre: String,                 // Ej: "Rutina MMA Principiante"
    val deporte: String,                // Ej: "MMA", "Boxeo", "Musculación"
    val nivel: String,                  // "Principiante", "Intermedio", "Avanzado"
    val diasSemana: Int,                // Días de entrenamiento por semana (1-7)
    val tipo: String = TipoRutina.SEMANAL.name, // "SEMANAL" o "SESION"
    val fechaCreacion: Long = System.currentTimeMillis()
)




