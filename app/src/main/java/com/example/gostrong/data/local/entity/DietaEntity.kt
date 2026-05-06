package com.example.gostrong.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Tipos de dieta disponibles:
 * - SEMANAL: distribuida por días de la semana
 * - DIA: un día concreto con todas sus comidas
 */
enum class TipoDieta {
    SEMANAL,
    DIA
}

/**
 * Representa una dieta guardada por el usuario.
 */
@Entity(tableName = "dietas")
data class DietaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val emailUsuario: String,
    val nombre: String,
    val objetivo: String,       // "Perder peso", "Ganar músculo", "Mantenimiento", "Definición"
    val restriccion: String,    // "Ninguna", "Sin gluten", "Sin lácteos", "Vegana"
    val calorias: Int,          // Calorías diarias aprox.
    val tipo: String = TipoDieta.SEMANAL.name,
    val fechaCreacion: Long = System.currentTimeMillis()
)

/**
 * Representa una comida dentro de una dieta.
 * - [dia]: "Lunes", "Martes"... (vacío si es dieta de un día)
 * - [momento]: "Desayuno", "Media mañana", "Comida", "Merienda", "Cena"
 */
@Entity(
    tableName = "comidas",
    foreignKeys = [
        ForeignKey(
            entity = DietaEntity::class,
            parentColumns = ["id"],
            childColumns = ["dietaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["dietaId"])]
)
data class ComidaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dietaId: Int,
    val dia: String = "",           // "Lunes"... o vacío en dietas de un día
    val momento: String,            // "Desayuno", "Comida", "Cena"...
    val descripcion: String,        // Ej: "Avena con frutas y nueces"
    val calorias: Int,              // Calorías de esa comida
    val proteinas: Int,             // gramos
    val carbohidratos: Int,         // gramos
    val grasas: Int,                // gramos
    val orden: Int = 0
)
