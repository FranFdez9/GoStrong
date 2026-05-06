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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadilla_con_barra),    series = 3, repeticiones = "10",   descanso = "90s",  dia = "Lunes",     orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_banca),             series = 3, repeticiones = "10",   descanso = "90s",  dia = "Lunes",     orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_remo_con_barra),          series = 3, repeticiones = "10",   descanso = "90s",  dia = "Lunes",     orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_militar),           series = 3, repeticiones = "10",   descanso = "60s",  dia = "Lunes",     orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_curl_biceps),             series = 3, repeticiones = "12",   descanso = "60s",  dia = "Lunes",     orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadilla_con_barra),    series = 3, repeticiones = "10",   descanso = "90s",  dia = "Miércoles", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_banca),             series = 3, repeticiones = "10",   descanso = "90s",  dia = "Miércoles", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_remo_con_barra),          series = 3, repeticiones = "10",   descanso = "90s",  dia = "Miércoles", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_peso_muerto),             series = 3, repeticiones = "8",    descanso = "120s", dia = "Miércoles", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_plancha),                 series = 3, repeticiones = "45s",  descanso = "60s",  dia = "Miércoles", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadilla_con_barra),    series = 4, repeticiones = "10",   descanso = "90s",  dia = "Viernes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_banca),             series = 4, repeticiones = "10",   descanso = "90s",  dia = "Viernes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_dominadas_asistidas),   series = 3, repeticiones = "8",    descanso = "90s",  dia = "Viernes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_militar),           series = 3, repeticiones = "10",   descanso = "60s",  dia = "Viernes",   orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_extension_triceps_polea), series = 3, repeticiones = "12",   descanso = "60s",  dia = "Viernes",   orden = 5)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_banca),               series = 4, repeticiones = "8",  descanso = "120s", dia = "Lunes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_inclinado_mancuernas),series = 3, repeticiones = "10", descanso = "90s",  dia = "Lunes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_militar_barra),       series = 4, repeticiones = "8",  descanso = "120s", dia = "Lunes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_elevaciones_laterales),     series = 3, repeticiones = "15", descanso = "60s",  dia = "Lunes",   orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_fondos_en_paralelas),       series = 3, repeticiones = "12", descanso = "60s",  dia = "Lunes",   orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_dominadas),                 series = 4, repeticiones = "8",  descanso = "120s", dia = "Martes",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_remo_con_barra),            series = 4, repeticiones = "8",  descanso = "120s", dia = "Martes",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_jalon_al_pecho),            series = 3, repeticiones = "10", descanso = "90s",  dia = "Martes",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_curl_biceps_barra),         series = 4, repeticiones = "10", descanso = "60s",  dia = "Martes",  orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_curl_martillo),             series = 3, repeticiones = "12", descanso = "60s",  dia = "Martes",  orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_banca_inclinado),     series = 4, repeticiones = "8",  descanso = "120s", dia = "Jueves",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_aperturas_pecho),           series = 3, repeticiones = "12", descanso = "60s",  dia = "Jueves",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_elevaciones_frontales),     series = 3, repeticiones = "12", descanso = "60s",  dia = "Jueves",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_extension_triceps_polea),   series = 4, repeticiones = "12", descanso = "60s",  dia = "Jueves",  orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_peso_muerto),               series = 4, repeticiones = "6",  descanso = "180s", dia = "Viernes", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_remo_en_polea_baja),        series = 3, repeticiones = "10", descanso = "90s",  dia = "Viernes", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadilla_bulgara),        series = 3, repeticiones = "10", descanso = "90s",  dia = "Viernes", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_curl_femoral),              series = 3, repeticiones = "12", descanso = "60s",  dia = "Viernes", orden = 4)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadilla_con_barra),  series = 4, repeticiones = "8",  descanso = "120s", dia = "", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_prensa_de_piernas),     series = 4, repeticiones = "10", descanso = "90s",  dia = "", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_extension_cuadriceps),  series = 3, repeticiones = "12", descanso = "60s",  dia = "", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_curl_femoral_tumbado),  series = 3, repeticiones = "12", descanso = "60s",  dia = "", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_peso_muerto_rumano),    series = 3, repeticiones = "10", descanso = "90s",  dia = "", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_elevacion_de_gemelos),  series = 4, repeticiones = "15", descanso = "45s",  dia = "", orden = 6)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_saltar_a_la_comba),          series = 3, repeticiones = "3min", descanso = "60s", dia = "Lunes",     orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sombra_shadow_boxing),     series = 3, repeticiones = "3min", descanso = "60s", dia = "Lunes",     orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_jab_cross_en_saco),          series = 4, repeticiones = "2min", descanso = "60s", dia = "Lunes",     orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_flexiones),                  series = 3, repeticiones = "15",   descanso = "60s", dia = "Lunes",     orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_abdominales),                series = 3, repeticiones = "20",   descanso = "45s", dia = "Lunes",     orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_calentamiento_comba),        series = 3, repeticiones = "3min", descanso = "60s", dia = "Miércoles", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_combinaciones_1_2_3_en_saco),series = 4, repeticiones = "2min", descanso = "60s", dia = "Miércoles", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_burpees),                    series = 3, repeticiones = "10",   descanso = "60s", dia = "Miércoles", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_mountain_climbers),          series = 3, repeticiones = "30s",  descanso = "45s", dia = "Miércoles", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_plancha),                    series = 3, repeticiones = "45s",  descanso = "45s", dia = "Miércoles", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_comba_calentamiento),        series = 3, repeticiones = "3min", descanso = "60s", dia = "Viernes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sombra_con_esquivas),        series = 3, repeticiones = "3min", descanso = "60s", dia = "Viernes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_golpeo_en_manoplas),         series = 5, repeticiones = "2min", descanso = "60s", dia = "Viernes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_core_rueda_abdominal),      series = 3, repeticiones = "10",   descanso = "60s", dia = "Viernes",   orden = 4)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_comba),                              series = 4, repeticiones = "3min",        descanso = "60s",  dia = "Lunes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_combinaciones_en_saco),              series = 5, repeticiones = "3min",        descanso = "60s",  dia = "Lunes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_patadas_en_saco),                    series = 3, repeticiones = "3min",        descanso = "60s",  dia = "Lunes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sombra),                             series = 3, repeticiones = "3min",        descanso = "60s",  dia = "Lunes",   orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadilla),                         series = 4, repeticiones = "6",           descanso = "120s", dia = "Martes",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_banca),                        series = 4, repeticiones = "6",           descanso = "120s", dia = "Martes",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_peso_muerto),                        series = 3, repeticiones = "5",           descanso = "180s", dia = "Martes",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_dominadas),                          series = 3, repeticiones = "8",           descanso = "90s",  dia = "Martes",  orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_turkish_get_up),                     series = 3, repeticiones = "5 cada lado", descanso = "90s",  dia = "Martes",  orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_calentamiento_rodando),              series = 2, repeticiones = "5min",        descanso = "60s",  dia = "Jueves",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_tecnica_de_derribo),                 series = 4, repeticiones = "10 rep",      descanso = "60s",  dia = "Jueves",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_posiciones_de_suelo),                series = 3, repeticiones = "5min",        descanso = "60s",  dia = "Jueves",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sprawl_plus_clinch),                    series = 3, repeticiones = "10",          descanso = "60s",  dia = "Jueves",  orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_circuito_burpees_plus_comba_plus_saco),  series = 5, repeticiones = "3min",        descanso = "60s",  dia = "Sábado",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sprints),                            series = 8, repeticiones = "30s",         descanso = "30s",  dia = "Sábado",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_core_mma_v_ups_oblicuos),         series = 3, repeticiones = "15",          descanso = "45s",  dia = "Sábado",  orden = 3)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_comba_calentamiento),        series = 4, repeticiones = "3min", descanso = "60s", dia = "", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sombra),                     series = 3, repeticiones = "3min", descanso = "60s", dia = "", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_saco_combinaciones_libres), series = 6, repeticiones = "3min", descanso = "60s", dia = "", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_manoplas_con_companero),     series = 4, repeticiones = "3min", descanso = "60s", dia = "", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_flexiones_explosivas),       series = 3, repeticiones = "10",   descanso = "60s", dia = "", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_abdominales),                series = 3, repeticiones = "20",   descanso = "45s", dia = "", orden = 6)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_caminar_calentamiento),                   series = 1, repeticiones = "5min",       descanso = "0s", dia = "Lunes",     orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_alternar_correr_1min_per_caminar_2min),      series = 6, repeticiones = "3min ciclo",  descanso = "0s", dia = "Lunes",     orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_caminar_enfriamiento),                    series = 1, repeticiones = "5min",        descanso = "0s", dia = "Lunes",     orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_caminar_calentamiento),                   series = 1, repeticiones = "5min",        descanso = "0s", dia = "Miércoles", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_alternar_correr_1_5min_per_caminar_2min),   series = 5, repeticiones = "3.5min ciclo", descanso = "0s", dia = "Miércoles", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_caminar_enfriamiento),                    series = 1, repeticiones = "5min",        descanso = "0s", dia = "Miércoles", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_caminar_calentamiento),                   series = 1, repeticiones = "5min",        descanso = "0s", dia = "Viernes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_correr_continuo),                           series = 1, repeticiones = "20min",       descanso = "0s", dia = "Viernes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_caminar_enfriamiento),                    series = 1, repeticiones = "5min",        descanso = "0s", dia = "Viernes",   orden = 3)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sprints_30_per_30),          series = 8, repeticiones = "30s sprint / 30s descanso", descanso = "0s",  dia = "Lunes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_burpees),                series = 4, repeticiones = "10",                       descanso = "30s", dia = "Lunes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_saltos_al_cajon),        series = 4, repeticiones = "10",                       descanso = "45s", dia = "Lunes",   orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_mountain_climbers),      series = 3, repeticiones = "30s",                      descanso = "30s", dia = "Lunes",   orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_rodaje_suave_zona_2),  series = 1, repeticiones = "40min",                    descanso = "0s",  dia = "Martes",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_tabata_20s_per_10s),         series = 8, repeticiones = "4min total",               descanso = "60s", dia = "Jueves",  orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_saltar_a_la_comba),      series = 3, repeticiones = "3min",                     descanso = "60s", dia = "Jueves",  orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_kettlebell_swings),      series = 4, repeticiones = "15",                       descanso = "45s", dia = "Jueves",  orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_carrera_continua),       series = 1, repeticiones = "50-60min",                 descanso = "0s",  dia = "Sábado",  orden = 1)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_jumping_jacks_calentamiento), series = 1, repeticiones = "2min", descanso = "0s",  dia = "", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sprints_en_sitio),              series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_burpees),                       series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_saltos_de_tijera),              series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_mountain_climbers),             series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 5),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadillas_con_salto),         series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 6),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_flexiones_explosivas),          series = 1, repeticiones = "20s",  descanso = "10s", dia = "", orden = 7),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_repetir_circuito_x3),           series = 3, repeticiones = "4min", descanso = "60s entre rondas", dia = "", orden = 8)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadilla_pesada), series = 5, repeticiones = "5", descanso = "180s",  dia = "Lunes",     orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_de_banca),    series = 5, repeticiones = "5", descanso = "180s",  dia = "Lunes",     orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_remo_con_barra),    series = 5, repeticiones = "5", descanso = "180s",  dia = "Lunes",     orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadilla_ligera), series = 3, repeticiones = "5", descanso = "120s",  dia = "Miércoles", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_militar),     series = 5, repeticiones = "5", descanso = "180s",  dia = "Miércoles", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_peso_muerto),       series = 1, repeticiones = "5", descanso = "240s",  dia = "Miércoles", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_sentadilla_pesada), series = 5, repeticiones = "5", descanso = "180s",  dia = "Viernes",   orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_press_de_banca),    series = 5, repeticiones = "5", descanso = "180s",  dia = "Viernes",   orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_dominadas_lastre),  series = 5, repeticiones = "5", descanso = "180s",  dia = "Viernes",   orden = 3)
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
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_dominadas_estrictas),   series = 4, repeticiones = "Al fallo -1", descanso = "90s", dia = "Lunes", orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_fondos_en_paralelas),   series = 4, repeticiones = "12",          descanso = "90s", dia = "Lunes", orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_flexiones_diamante),    series = 3, repeticiones = "15",          descanso = "60s", dia = "Lunes", orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_pistol_squats_pistola),series= 4, repeticiones = "6 cada lado", descanso = "90s", dia = "Lunes", orden = 4),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_muscle_ups_o_intento),series = 4, repeticiones = "Máximas",     descanso = "120s",dia = "Jueves",orden = 1),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_chin_ups_biceps),     series = 4, repeticiones = "10",          descanso = "90s", dia = "Jueves",orden = 2),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_flexiones_arquero),     series = 3, repeticiones = "8 cada lado", descanso = "90s", dia = "Jueves",orden = 3),
                EjercicioEntity(rutinaId = 0, nombre = context.getString(R.string.ej_l_sit),                 series = 3, repeticiones = "Mantener 15s",descanso = "60s", dia = "Jueves",orden = 4)
            )
        )

    )

    fun porDeporte(deporte: String, context: Context) = todas(context).filter { it.deporte == deporte }
    fun porNivel(nivel: String, context: Context)     = todas(context).filter { it.nivel == nivel }
    fun deportesDisponibles(context: Context)       = todas(context).map { it.deporte }.distinct()
}