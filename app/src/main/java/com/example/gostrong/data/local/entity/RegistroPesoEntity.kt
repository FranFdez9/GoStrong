package com.example.gostrong.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa un registro de peso en un momento concreto.
 * Cada vez que el usuario actualiza su peso se guarda una entrada aquí,
 * permitiendo mostrar la evolución en la gráfica.
 */
@Entity(tableName = "registros_peso")
data class RegistroPesoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val emailUsuario: String,   // Relaciona el registro con el usuario logueado
    val peso: Float,            // Peso en kg
    val fecha: Long             // Fecha en milisegundos (System.currentTimeMillis())
)