package com.example.gostrong.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DateTimeException
import java.util.Date

@Entity(tableName = "usuarios")
data class UsuarioEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombreUsuario: String,
    val email: String,          // Email del usuario
    //val password: String, // NO la uso, ya que he cambiado la manera de gestionar estos datos.

    //val edad: Int, No me sirve realmente por que como uso la fecha de nacimiento me da facilidades para calcularla,
    val sexo: String,     // M / F / Otro
    val altura: Int,      // en cm
    val peso: Float,      // en kg
    val pesoObjetivo: Float,     //Peso objetivo de bajada o de subida o de difinicion. en kg
    val fechaNacimeinto: Long, // Fecha de nacimiento en milisegundos
    val nivel: String,    // SEDENTARIO / NORMAL / AVANZADO
    val objetivo: String, // perder_peso / mantener / ganar_musculo

    val diasEntrenamiento: Int,

    val alergias: String  // Ej: "gluten,lactosa"



)
