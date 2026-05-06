package com.example.gostrong.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historial_actividad")
data class HistorialActividadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val emailUsuario: String,
    val fecha: Long,         // Timestamp o Epoch Day para filtrar días
    val tipo: String,        // "RUTINA" o "DIETA"
    val referenciaId: Int,   // El ID de la rutina o dieta en la BD (opcional)
    val titulo: String,      // Nombre de la rutina o dieta ("Día de Pecho")
    val estado: String,      // "COMPLETO", "A MEDIAS", "SALTADO"
    val notas: String        // "Sólo hice Tábata"
)
