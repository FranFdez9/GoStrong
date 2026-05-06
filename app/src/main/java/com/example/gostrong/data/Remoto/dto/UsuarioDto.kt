package com.example.gostrong.data.Remoto.dto

import com.example.gostrong.data.local.entity.UsuarioEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsuarioDto(
    val email: String,
    @SerialName("nombre_usuario")
    val nombreUsuario: String,
    val sexo: String,
    val altura: Int,
    val peso: Float,
    @SerialName("peso_objetivo")
    val pesoObjetivo: Float,
    @SerialName("fecha_nacimiento")
    val fechaNacimiento: Long,
    val nivel: String,
    val objetivo: String,
    @SerialName("dias_entrenamiento")
    val diasEntrenamiento: Int,
    val alergias: String
)

// Extension function to map Entity to DTO (Up to the cloud)
fun UsuarioEntity.toDto() = UsuarioDto(
    email = this.email,
    nombreUsuario = this.nombreUsuario,
    sexo = this.sexo,
    altura = this.altura,
    peso = this.peso,
    pesoObjetivo = this.pesoObjetivo,
    fechaNacimiento = this.fechaNacimeinto,
    nivel = this.nivel,
    objetivo = this.objetivo,
    diasEntrenamiento = this.diasEntrenamiento,
    alergias = this.alergias
)

// Extension function to map DTO to Entity (Down to local DB)
fun UsuarioDto.toEntity() = UsuarioEntity(
    nombreUsuario = this.nombreUsuario,
    email = this.email,
    sexo = this.sexo,
    altura = this.altura,
    peso = this.peso,
    pesoObjetivo = this.pesoObjetivo,
    fechaNacimeinto = this.fechaNacimiento,
    nivel = this.nivel,
    objetivo = this.objetivo,
    diasEntrenamiento = this.diasEntrenamiento,
    alergias = this.alergias
)
