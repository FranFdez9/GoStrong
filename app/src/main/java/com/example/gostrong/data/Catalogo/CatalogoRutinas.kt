package com.example.gostrong.data.catalog

import android.content.Context
import com.example.gostrong.R
import com.example.gostrong.data.local.entity.EjercicioEntity
import com.example.gostrong.data.local.entity.RutinaEntity
import com.example.gostrong.data.local.entity.TipoRutina

data class RutinaTemplate(
    val nombre: String,
    val deporte: String,
    val nivel: String,
    val diasSemana: Int,
    val tipo: String,
    val descripcion: String,
    val iconoEmoji: String,
    val ejercicios: List<EjercicioEntity>
)

object CatalogoRutinas {

    fun todas(context: Context): List<RutinaTemplate> = listOf(

        // ── MUSCULACIÓN · SEMANAL · PRINCIPIANTE ──────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_1_nombre),
            deporte = "Musculación",
            nivel = "Principiante",
            diasSemana = 3,
            tipo = TipoRutina.SEMANAL.name,
            descripcion = context.getString(R.string.cat_rut_1_desc),
            iconoEmoji = "🏋️",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Sentadilla con barra",    series = 3, repeticiones = "10",   descanso = "90s",  dia = "Lunes",     orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Press banca",             series = 3, repeticiones = "10",   descanso = "90s",  dia = "Lunes",     orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Remo con barra",          series = 3, repeticiones = "10",   descanso = "90s",  dia = "Lunes",     orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Press militar",           series = 3, repeticiones = "10",   descanso = "60s",  dia = "Lunes",     orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Curl bíceps",             series = 3, repeticiones = "12",   descanso = "60s",  dia = "Lunes",     orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Sentadilla con barra",    series = 3, repeticiones = "10",   descanso = "90s",  dia = "Miércoles", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Press banca",             series = 3, repeticiones = "10",   descanso = "90s",  dia = "Miércoles", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Remo con barra",          series = 3, repeticiones = "10",   descanso = "90s",  dia = "Miércoles", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Peso muerto",             series = 3, repeticiones = "8",    descanso = "120s", dia = "Miércoles", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Plancha",                 series = 3, repeticiones = "45s",  descanso = "60s",  dia = "Miércoles", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Sentadilla con barra",    series = 4, repeticiones = "10",   descanso = "90s",  dia = "Viernes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Press banca",             series = 4, repeticiones = "10",   descanso = "90s",  dia = "Viernes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Dominadas (asistidas)",   series = 3, repeticiones = "8",    descanso = "90s",  dia = "Viernes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Press militar",           series = 3, repeticiones = "10",   descanso = "60s",  dia = "Viernes",   orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Extensión tríceps polea", series = 3, repeticiones = "12",   descanso = "60s",  dia = "Viernes",   orden = 5)
            )
        ),

        // ── MUSCULACIÓN · SEMANAL · INTERMEDIO (Push/Pull) ────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_2_nombre),
            deporte = "Musculación",
            nivel = "Intermedio",
            diasSemana = 4,
            tipo = TipoRutina.SEMANAL.name,
            descripcion = context.getString(R.string.cat_rut_2_desc),
            iconoEmoji = "💪",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Press banca",               series = 4, repeticiones = "8",  descanso = "120s", dia = "Lunes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Press inclinado mancuernas",series = 3, repeticiones = "10", descanso = "90s",  dia = "Lunes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Press militar barra",       series = 4, repeticiones = "8",  descanso = "120s", dia = "Lunes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Elevaciones laterales",     series = 3, repeticiones = "15", descanso = "60s",  dia = "Lunes",   orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Fondos en paralelas",       series = 3, repeticiones = "12", descanso = "60s",  dia = "Lunes",   orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Dominadas",                 series = 4, repeticiones = "8",  descanso = "120s", dia = "Martes",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Remo con barra",            series = 4, repeticiones = "8",  descanso = "120s", dia = "Martes",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Jalón al pecho",            series = 3, repeticiones = "10", descanso = "90s",  dia = "Martes",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Curl bíceps barra",         series = 4, repeticiones = "10", descanso = "60s",  dia = "Martes",  orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Curl martillo",             series = 3, repeticiones = "12", descanso = "60s",  dia = "Martes",  orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Press banca inclinado",     series = 4, repeticiones = "8",  descanso = "120s", dia = "Jueves",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Aperturas pecho",           series = 3, repeticiones = "12", descanso = "60s",  dia = "Jueves",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Elevaciones frontales",     series = 3, repeticiones = "12", descanso = "60s",  dia = "Jueves",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Extensión tríceps polea",   series = 4, repeticiones = "12", descanso = "60s",  dia = "Jueves",  orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Peso muerto",               series = 4, repeticiones = "6",  descanso = "180s", dia = "Viernes", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Remo en polea baja",        series = 3, repeticiones = "10", descanso = "90s",  dia = "Viernes", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Sentadilla búlgara",        series = 3, repeticiones = "10", descanso = "90s",  dia = "Viernes", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Curl femoral",              series = 3, repeticiones = "12", descanso = "60s",  dia = "Viernes", orden = 4)
            )
        ),

        // ── MUSCULACIÓN · SESIÓN · PIERNA ─────────────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_3_nombre),
            deporte = "Musculación",
            nivel = "Intermedio",
            diasSemana = 1,
            tipo = TipoRutina.SESION.name,
            descripcion = context.getString(R.string.cat_rut_3_desc),
            iconoEmoji = "🦵",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Sentadilla con barra",  series = 4, repeticiones = "8",  descanso = "120s", dia = "", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Prensa de piernas",     series = 4, repeticiones = "10", descanso = "90s",  dia = "", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Extensión cuádriceps",  series = 3, repeticiones = "12", descanso = "60s",  dia = "", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Curl femoral tumbado",  series = 3, repeticiones = "12", descanso = "60s",  dia = "", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Peso muerto rumano",    series = 3, repeticiones = "10", descanso = "90s",  dia = "", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Elevación de gemelos",  series = 4, repeticiones = "15", descanso = "45s",  dia = "", orden = 6)
            )
        ),

        // ── BOXEO · SEMANAL · PRINCIPIANTE ────────────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_4_nombre),
            deporte = "Boxeo",
            nivel = "Principiante",
            diasSemana = 3,
            tipo = TipoRutina.SEMANAL.name,
            descripcion = context.getString(R.string.cat_rut_4_desc),
            iconoEmoji = "🥊",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Saltar a la comba",          series = 3, repeticiones = "3min", descanso = "60s", dia = "Lunes",     orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Sombra (shadow boxing)",     series = 3, repeticiones = "3min", descanso = "60s", dia = "Lunes",     orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Jab-Cross en saco",          series = 4, repeticiones = "2min", descanso = "60s", dia = "Lunes",     orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Flexiones",                  series = 3, repeticiones = "15",   descanso = "60s", dia = "Lunes",     orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Abdominales",                series = 3, repeticiones = "20",   descanso = "45s", dia = "Lunes",     orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Calentamiento comba",        series = 3, repeticiones = "3min", descanso = "60s", dia = "Miércoles", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Combinaciones 1-2-3 en saco",series = 4, repeticiones = "2min", descanso = "60s", dia = "Miércoles", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Burpees",                    series = 3, repeticiones = "10",   descanso = "60s", dia = "Miércoles", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Mountain climbers",          series = 3, repeticiones = "30s",  descanso = "45s", dia = "Miércoles", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Plancha",                    series = 3, repeticiones = "45s",  descanso = "45s", dia = "Miércoles", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Comba calentamiento",        series = 3, repeticiones = "3min", descanso = "60s", dia = "Viernes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Sombra con esquivas",        series = 3, repeticiones = "3min", descanso = "60s", dia = "Viernes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Golpeo en manoplas",         series = 5, repeticiones = "2min", descanso = "60s", dia = "Viernes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Core: rueda abdominal",      series = 3, repeticiones = "10",   descanso = "60s", dia = "Viernes",   orden = 4)
            )
        ),

        // ── MMA · SEMANAL · INTERMEDIO ────────────────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_5_nombre),
            deporte = "MMA",
            nivel = "Intermedio",
            diasSemana = 4,
            tipo = TipoRutina.SEMANAL.name,
            descripcion = context.getString(R.string.cat_rut_5_desc),
            iconoEmoji = "🥋",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Comba",                              series = 4, repeticiones = "3min",        descanso = "60s",  dia = "Lunes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Combinaciones en saco",              series = 5, repeticiones = "3min",        descanso = "60s",  dia = "Lunes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Patadas en saco",                    series = 3, repeticiones = "3min",        descanso = "60s",  dia = "Lunes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Sombra",                             series = 3, repeticiones = "3min",        descanso = "60s",  dia = "Lunes",   orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Sentadilla",                         series = 4, repeticiones = "6",           descanso = "120s", dia = "Martes",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Press banca",                        series = 4, repeticiones = "6",           descanso = "120s", dia = "Martes",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Peso muerto",                        series = 3, repeticiones = "5",           descanso = "180s", dia = "Martes",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Dominadas",                          series = 3, repeticiones = "8",           descanso = "90s",  dia = "Martes",  orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Turkish get-up",                     series = 3, repeticiones = "5 cada lado", descanso = "90s",  dia = "Martes",  orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Calentamiento rodando",              series = 2, repeticiones = "5min",        descanso = "60s",  dia = "Jueves",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Técnica de derribo",                 series = 4, repeticiones = "10 rep",      descanso = "60s",  dia = "Jueves",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Posiciones de suelo",                series = 3, repeticiones = "5min",        descanso = "60s",  dia = "Jueves",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Sprawl + clinch",                    series = 3, repeticiones = "10",          descanso = "60s",  dia = "Jueves",  orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Circuito: Burpees + Comba + Saco",  series = 5, repeticiones = "3min",        descanso = "60s",  dia = "Sábado",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Sprints",                            series = 8, repeticiones = "30s",         descanso = "30s",  dia = "Sábado",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Core MMA (V-ups, oblicuos)",         series = 3, repeticiones = "15",          descanso = "45s",  dia = "Sábado",  orden = 3)
            )
        ),

        // ── BOXEO · SESIÓN ────────────────────────────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_6_nombre),
            deporte = "Boxeo",
            nivel = "Intermedio",
            diasSemana = 1,
            tipo = TipoRutina.SESION.name,
            descripcion = context.getString(R.string.cat_rut_6_desc),
            iconoEmoji = "🥊",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Comba calentamiento",        series = 4, repeticiones = "3min", descanso = "60s", dia = "", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Sombra",                     series = 3, repeticiones = "3min", descanso = "60s", dia = "", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Saco (combinaciones libres)", series = 6, repeticiones = "3min", descanso = "60s", dia = "", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Manoplas con compañero",     series = 4, repeticiones = "3min", descanso = "60s", dia = "", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Flexiones explosivas",       series = 3, repeticiones = "10",   descanso = "60s", dia = "", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Abdominales",                series = 3, repeticiones = "20",   descanso = "45s", dia = "", orden = 6)
            )
        ),

        // ── CARDIO · SEMANAL · PRINCIPIANTE ──────────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_7_nombre),
            deporte = "Cardio",
            nivel = "Principiante",
            diasSemana = 3,
            tipo = TipoRutina.SEMANAL.name,
            descripcion = context.getString(R.string.cat_rut_7_desc),
            iconoEmoji = "🏃",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Caminar (calentamiento)",                   series = 1, repeticiones = "5min",       descanso = "0s", dia = "Lunes",     orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Alternar: correr 1min / caminar 2min",      series = 6, repeticiones = "3min ciclo",  descanso = "0s", dia = "Lunes",     orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Caminar (enfriamiento)",                    series = 1, repeticiones = "5min",        descanso = "0s", dia = "Lunes",     orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Caminar (calentamiento)",                   series = 1, repeticiones = "5min",        descanso = "0s", dia = "Miércoles", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Alternar: correr 1.5min / caminar 2min",   series = 5, repeticiones = "3.5min ciclo", descanso = "0s", dia = "Miércoles", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Caminar (enfriamiento)",                    series = 1, repeticiones = "5min",        descanso = "0s", dia = "Miércoles", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Caminar (calentamiento)",                   series = 1, repeticiones = "5min",        descanso = "0s", dia = "Viernes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Correr continuo",                           series = 1, repeticiones = "20min",       descanso = "0s", dia = "Viernes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Caminar (enfriamiento)",                    series = 1, repeticiones = "5min",        descanso = "0s", dia = "Viernes",   orden = 3)
            )
        ),

        // ── CARDIO · SEMANAL · INTERMEDIO (HIIT) ─────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_8_nombre),
            deporte = "Cardio",
            nivel = "Intermedio",
            diasSemana = 4,
            tipo = TipoRutina.SEMANAL.name,
            descripcion = context.getString(R.string.cat_rut_8_desc),
            iconoEmoji = "🔥",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Sprints 30/30",          series = 8, repeticiones = "30s sprint / 30s descanso", descanso = "0s",  dia = "Lunes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Burpees",                series = 4, repeticiones = "10",                       descanso = "30s", dia = "Lunes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Saltos al cajón",        series = 4, repeticiones = "10",                       descanso = "45s", dia = "Lunes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Mountain climbers",      series = 3, repeticiones = "30s",                      descanso = "30s", dia = "Lunes",   orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Rodaje suave (zona 2)",  series = 1, repeticiones = "40min",                    descanso = "0s",  dia = "Martes",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Tabata 20s/10s",         series = 8, repeticiones = "4min total",               descanso = "60s", dia = "Jueves",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Saltar a la comba",      series = 3, repeticiones = "3min",                     descanso = "60s", dia = "Jueves",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Kettlebell swings",      series = 4, repeticiones = "15",                       descanso = "45s", dia = "Jueves",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Carrera continua",       series = 1, repeticiones = "50-60min",                 descanso = "0s",  dia = "Sábado",  orden = 1)
            )
        ),

        // ── CARDIO · SESIÓN · HIIT EXPRESS ───────────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_9_nombre),
            deporte = "Cardio",
            nivel = "Avanzado",
            diasSemana = 1,
            tipo = TipoRutina.SESION.name,
            descripcion = context.getString(R.string.cat_rut_9_desc),
            iconoEmoji = "⚡",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Jumping jacks (calentamiento)", series = 1, repeticiones = "2min", descanso = "0s",  dia = "", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Sprints en sitio",              series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Burpees",                       series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Saltos de tijera",              series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Mountain climbers",             series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = "Sentadillas con salto",         series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 6),
                EjercicioEntity(rutinaId = 0, nombre = "Flexiones explosivas",          series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 7),
                EjercicioEntity(rutinaId = 0, nombre = "Repetir circuito x3",           series = 3, repeticiones = "4min", descanso = "60s entre rondas", dia = "", orden = 8)
            )
        ),

        // ── FUERZA · SEMANAL · POWERLIFTING ──────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_10_nombre),
            deporte = "Musculación",
            nivel = "Avanzado",
            diasSemana = 3,
            tipo = TipoRutina.SEMANAL.name,
            descripcion = context.getString(R.string.cat_rut_10_desc),
            iconoEmoji = "🏋️‍♂️",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Sentadilla Pesada", series = 5, repeticiones = "5", descanso = "180s",  dia = "Lunes",     orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Press de Banca",    series = 5, repeticiones = "5", descanso = "180s",  dia = "Lunes",     orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Remo con Barra",    series = 5, repeticiones = "5", descanso = "180s",  dia = "Lunes",     orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Sentadilla Ligera", series = 3, repeticiones = "5", descanso = "120s",  dia = "Miércoles", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Press Militar",     series = 5, repeticiones = "5", descanso = "180s",  dia = "Miércoles", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Peso Muerto",       series = 1, repeticiones = "5", descanso = "240s",  dia = "Miércoles", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Sentadilla Pesada", series = 5, repeticiones = "5", descanso = "180s",  dia = "Viernes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Press de Banca",    series = 5, repeticiones = "5", descanso = "180s",  dia = "Viernes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Dominadas Lastre",  series = 5, repeticiones = "5", descanso = "180s",  dia = "Viernes",   orden = 3)
            )
        ),

        // ── CALISTENIA · SEMANAL · SIN MATERIAL ──────────────
        RutinaTemplate(
            nombre = context.getString(R.string.cat_rut_11_nombre),
            deporte = "Acondicionamiento",
            nivel = "Intermedio",
            diasSemana = 2,
            tipo = TipoRutina.SEMANAL.name,
            descripcion = context.getString(R.string.cat_rut_11_desc),
            iconoEmoji = "🤸",
            ejercicios = listOf(
                EjercicioEntity(rutinaId = 0, nombre = "Dominadas Estrictas",   series = 4, repeticiones = "Al fallo -1", descanso = "90s", dia = "Lunes", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Fondos en Paralelas",   series = 4, repeticiones = "12",          descanso = "90s", dia = "Lunes", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Flexiones Diamante",    series = 3, repeticiones = "15",          descanso = "60s", dia = "Lunes", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "Pistol Squats (Pistola)",series= 4, repeticiones = "6 cada lado", descanso = "90s", dia = "Lunes", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = "Muscle-Ups (o intento)",series = 4, repeticiones = "Máximas",     descanso = "120s",dia = "Jueves",orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = "Chin-Ups (Bíceps)",     series = 4, repeticiones = "10",          descanso = "90s", dia = "Jueves",orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = "Flexiones Arquero",     series = 3, repeticiones = "8 cada lado", descanso = "90s", dia = "Jueves",orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = "L-Sit",                 series = 3, repeticiones = "Mantener 15s",descanso = "60s", dia = "Jueves",orden = 4)
            )
        )

    )

    fun porDeporte(deporte: String, context: Context) = todas(context).filter { it.deporte == deporte }
    fun porNivel(nivel: String, context: Context)     = todas(context).filter { it.nivel == nivel }
    fun deportesDisponibles(context: Context)       = todas(context).map { it.deporte }.distinct()
}