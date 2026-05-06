package com.example.gostrong.data.catalog

import android.content.Context
import com.example.gostrong.R
import com.example.gostrong.data.local.entity.ComidaEntity
import com.example.gostrong.data.local.entity.TipoDieta

data class DietaTemplate(
    val nombre: String,
    val objetivo: String,
    val restriccion: String,
    val calorias: Int,
    val tipo: String,
    val descripcion: String,
    val iconoEmoji: String,
    val comidas: List<ComidaEntity>
)

object CatalogoDietas {

    fun todas(context: Context): List<DietaTemplate> = listOf(

        // ════════════════════════════════════════════════════
        // PERDER PESO · SEMANAL · SIN RESTRICCIONES
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_1_nombre),
            objetivo    = "Perder peso",
            restriccion = "Ninguna",
            calorias    = 1600,
            tipo        = TipoDieta.SEMANAL.name,
            descripcion = context.getString(R.string.cat_diet_1_desc),
            iconoEmoji  = "🥗",
            comidas     = listOf(
                // LUNES
                ComidaEntity(dietaId=0, dia="Lunes", momento="Desayuno",      descripcion=context.getString(R.string.comida_avena_con_leche_desnatada_platano_y_cane),                    calorias=320, proteinas=12, carbohidratos=55, grasas=5,  orden=1),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Media mañana",  descripcion=context.getString(R.string.comida_manzana_plus_10_almendras),                                         calorias=150, proteinas=3,  carbohidratos=20, grasas=7,  orden=2),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Comida",        descripcion=context.getString(R.string.comida_pechuga_de_pollo_a_la_plancha_con_arroz_),    calorias=480, proteinas=42, carbohidratos=45, grasas=8,  orden=3),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Merienda",      descripcion=context.getString(R.string.comida_yogur_desnatado_con_fresas),                                     calorias=120, proteinas=8,  carbohidratos=16, grasas=1,  orden=4),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Cena",          descripcion=context.getString(R.string.comida_merluza_al_horno_con_brocoli_al_vapor_y_),          calorias=380, proteinas=38, carbohidratos=30, grasas=6,  orden=5),
                // MARTES
                ComidaEntity(dietaId=0, dia="Martes", momento="Desayuno",     descripcion=context.getString(R.string.comida_tostadas_de_pan_integral_con_tomate_y_ac),          calorias=280, proteinas=8,  carbohidratos=42, grasas=9,  orden=1),
                ComidaEntity(dietaId=0, dia="Martes", momento="Media mañana", descripcion=context.getString(R.string.comida_pera_plus_infusion_sin_azucar),                                     calorias=80,  proteinas=1,  carbohidratos=18, grasas=0,  orden=2),
                ComidaEntity(dietaId=0, dia="Martes", momento="Comida",       descripcion=context.getString(R.string.comida_lentejas_con_verduras_y_zanahoria),                              calorias=420, proteinas=22, carbohidratos=58, grasas=5,  orden=3),
                ComidaEntity(dietaId=0, dia="Martes", momento="Merienda",     descripcion=context.getString(R.string.comida_queso_fresco_0pct_con_pepino),                                     calorias=90,  proteinas=10, carbohidratos=4,  grasas=1,  orden=4),
                ComidaEntity(dietaId=0, dia="Martes", momento="Cena",         descripcion=context.getString(R.string.comida_tortilla_francesa_de_2_huevos_con_esparr),         calorias=280, proteinas=18, carbohidratos=6,  grasas=14, orden=5),
                // MIÉRCOLES
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Desayuno",     descripcion=context.getString(R.string.comida_batido_de_proteinas_con_leche_y_platano),                     calorias=310, proteinas=28, carbohidratos=38, grasas=4,  orden=1),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Media mañana", descripcion=context.getString(R.string.comida_naranja_plus_te_verde),                                          calorias=70,  proteinas=1,  carbohidratos=16, grasas=0,  orden=2),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Comida",       descripcion=context.getString(R.string.comida_salmon_a_la_plancha_con_quinoa_y_espinac),        calorias=490, proteinas=40, carbohidratos=35, grasas=16, orden=3),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Merienda",     descripcion=context.getString(R.string.comida_zanahoria_y_apio_con_hummus_2_cdas),                        calorias=120, proteinas=4,  carbohidratos=14, grasas=5,  orden=4),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Cena",         descripcion=context.getString(R.string.comida_sopa_de_verduras_con_pollo_desmenuzado),                      calorias=300, proteinas=28, carbohidratos=24, grasas=5,  orden=5),
                // JUEVES
                ComidaEntity(dietaId=0, dia="Jueves", momento="Desayuno",     descripcion=context.getString(R.string.comida_yogur_griego_0pct_con_granola_y_kiwi),                            calorias=290, proteinas=15, carbohidratos=42, grasas=5,  orden=1),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Media mañana", descripcion=context.getString(R.string.comida_punado_de_nueces_20g),                                         calorias=130, proteinas=3,  carbohidratos=3,  grasas=13, orden=2),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Comida",       descripcion=context.getString(R.string.comida_garbanzos_con_espinacas_y_huevo_duro),                           calorias=440, proteinas=24, carbohidratos=50, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Merienda",     descripcion=context.getString(R.string.comida_manzana_plus_infusion),                                             calorias=80,  proteinas=0,  carbohidratos=20, grasas=0,  orden=4),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Cena",         descripcion=context.getString(R.string.comida_pavo_a_la_plancha_con_judias_verdes_y_to),            calorias=320, proteinas=36, carbohidratos=12, grasas=7,  orden=5),
                // VIERNES
                ComidaEntity(dietaId=0, dia="Viernes", momento="Desayuno",    descripcion=context.getString(R.string.comida_tostada_integral_con_aguacate_y_huevo_re),                 calorias=350, proteinas=16, carbohidratos=30, grasas=16, orden=1),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Media mañana",descripcion=context.getString(R.string.comida_yogur_desnatado),                                                calorias=80,  proteinas=8,  carbohidratos=10, grasas=0,  orden=2),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Comida",      descripcion=context.getString(R.string.comida_pasta_integral_con_atun_y_tomate_natural),                       calorias=460, proteinas=30, carbohidratos=58, grasas=8,  orden=3),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Merienda",    descripcion=context.getString(R.string.comida_pieza_de_fruta_plus_te),                                            calorias=80,  proteinas=1,  carbohidratos=18, grasas=0,  orden=4),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Cena",        descripcion=context.getString(R.string.comida_ensalada_templada_de_gambas_con_aguacate),              calorias=340, proteinas=28, carbohidratos=10, grasas=18, orden=5),
                // SÁBADO
                ComidaEntity(dietaId=0, dia="Sábado", momento="Desayuno",     descripcion=context.getString(R.string.comida_porridge_de_avena_con_frutos_rojos_y_sem),          calorias=340, proteinas=12, carbohidratos=52, grasas=8,  orden=1),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Comida",       descripcion=context.getString(R.string.comida_pollo_al_curry_con_arroz_basmati_y_verdu),                    calorias=480, proteinas=38, carbohidratos=48, grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Cena",         descripcion=context.getString(R.string.comida_revuelto_de_champinones_esparragos_y_jam),            calorias=280, proteinas=22, carbohidratos=8,  grasas=14, orden=3),
                // DOMINGO
                ComidaEntity(dietaId=0, dia="Domingo", momento="Desayuno",    descripcion=context.getString(R.string.comida_tortitas_de_avena_con_miel_y_platano),                           calorias=360, proteinas=14, carbohidratos=60, grasas=6,  orden=1),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Comida",      descripcion=context.getString(R.string.comida_merluza_al_horno_con_patatas_y_pisto_de_),               calorias=420, proteinas=36, carbohidratos=38, grasas=8,  orden=2),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Cena",        descripcion=context.getString(R.string.comida_crema_de_calabaza_con_tostadas_integrale),                      calorias=280, proteinas=8,  carbohidratos=40, grasas=7,  orden=3)
            )
        ),

        // ════════════════════════════════════════════════════
        // PERDER PESO · DÍA · VEGANA
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_2_nombre),
            objetivo    = "Perder peso",
            restriccion = "Vegana",
            calorias    = 1500,
            tipo        = TipoDieta.DIA.name,
            descripcion = context.getString(R.string.cat_diet_2_desc),
            iconoEmoji  = "🌱",
            comidas     = listOf(
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",      descripcion=context.getString(R.string.comida_batido_verde_espinacas_platano_leche_de_), calorias=280, proteinas=8,  carbohidratos=48, grasas=6,  orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana",  descripcion=context.getString(R.string.comida_manzana_con_mantequilla_de_cacahuete_1_c),                        calorias=160, proteinas=4,  carbohidratos=22, grasas=7,  orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",        descripcion=context.getString(R.string.comida_bowl_de_quinoa_con_garbanzos_aguacate_to),             calorias=480, proteinas=20, carbohidratos=58, grasas=16, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",      descripcion=context.getString(R.string.comida_edamame_cocido_100g),                                               calorias=120, proteinas=11, carbohidratos=8,  grasas=5,  orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",          descripcion=context.getString(R.string.comida_wok_de_tofu_con_brocoli_pimiento_y_salsa),     calorias=360, proteinas=22, carbohidratos=28, grasas=14, orden=5)
            )
        ),

        // ════════════════════════════════════════════════════
        // GANAR MÚSCULO · SEMANAL · SIN RESTRICCIONES
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_3_nombre),
            objetivo    = "Ganar músculo",
            restriccion = "Ninguna",
            calorias    = 2800,
            tipo        = TipoDieta.SEMANAL.name,
            descripcion = context.getString(R.string.cat_diet_3_desc),
            iconoEmoji  = "💪",
            comidas     = listOf(
                // LUNES
                ComidaEntity(dietaId=0, dia="Lunes", momento="Desayuno",     descripcion=context.getString(R.string.comida_tortilla_de_4_huevos_con_avena_platano_y),                 calorias=620, proteinas=40, carbohidratos=68, grasas=16, orden=1),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Media mañana", descripcion=context.getString(R.string.comida_batido_de_proteinas_con_leche_entera_y_p),                  calorias=380, proteinas=35, carbohidratos=42, grasas=7,  orden=2),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Comida",       descripcion=context.getString(R.string.comida_arroz_blanco_con_pechuga_de_pollo_200g_y),      calorias=680, proteinas=52, carbohidratos=72, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Merienda",     descripcion=context.getString(R.string.comida_pan_integral_con_pavo_y_queso),                                   calorias=340, proteinas=22, carbohidratos=38, grasas=10, orden=4),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Cena",         descripcion=context.getString(R.string.comida_salmon_200g_con_patata_cocida_y_brocoli),                       calorias=580, proteinas=48, carbohidratos=42, grasas=18, orden=5),
                // MARTES
                ComidaEntity(dietaId=0, dia="Martes", momento="Desayuno",    descripcion=context.getString(R.string.comida_porridge_de_avena_con_proteina_en_polvo_),          calorias=580, proteinas=38, carbohidratos=70, grasas=14, orden=1),
                ComidaEntity(dietaId=0, dia="Martes", momento="Media mañana",descripcion=context.getString(R.string.comida_requeson_con_frutos_rojos_y_almendras),                           calorias=320, proteinas=28, carbohidratos=22, grasas=12, orden=2),
                ComidaEntity(dietaId=0, dia="Martes", momento="Comida",      descripcion=context.getString(R.string.comida_pasta_integral_con_ternera_picada_y_sals),     calorias=700, proteinas=50, carbohidratos=78, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="Martes", momento="Merienda",    descripcion=context.getString(R.string.comida_batido_post_entreno_whey_plus_leche_plus),                     calorias=360, proteinas=32, carbohidratos=44, grasas=5,  orden=4),
                ComidaEntity(dietaId=0, dia="Martes", momento="Cena",        descripcion=context.getString(R.string.comida_pechuga_de_pavo_200g_con_arroz_y_espinac),                    calorias=520, proteinas=50, carbohidratos=46, grasas=8,  orden=5),
                // MIÉRCOLES
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Desayuno",    descripcion=context.getString(R.string.comida_huevos_revueltos_4_con_tostadas_integral),      calorias=600, proteinas=36, carbohidratos=44, grasas=28, orden=1),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Media mañana",descripcion=context.getString(R.string.comida_yogur_griego_con_granola_y_platano),                           calorias=380, proteinas=20, carbohidratos=52, grasas=8,  orden=2),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Comida",      descripcion=context.getString(R.string.comida_arroz_con_atun_2_latas_pimiento_y_maiz),                    calorias=620, proteinas=48, carbohidratos=70, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Merienda",    descripcion=context.getString(R.string.comida_sandwich_de_pollo_con_queso_y_lechuga),                        calorias=380, proteinas=28, carbohidratos=40, grasas=12, orden=4),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Cena",        descripcion=context.getString(R.string.comida_merluza_200g_con_quinoa_y_verduras_asada),                  calorias=520, proteinas=46, carbohidratos=48, grasas=10, orden=5),
                // JUEVES-DOMINGO (días simplificados)
                ComidaEntity(dietaId=0, dia="Jueves", momento="Desayuno",    descripcion=context.getString(R.string.comida_avena_con_leche_proteina_y_fruta),                               calorias=560, proteinas=36, carbohidratos=65, grasas=10, orden=1),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Comida",      descripcion=context.getString(R.string.comida_pollo_200g_con_patatas_asadas_y_ensalada),                      calorias=680, proteinas=52, carbohidratos=58, grasas=14, orden=2),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Cena",        descripcion=context.getString(R.string.comida_ternera_180g_con_arroz_integral_y_brocol),                     calorias=620, proteinas=50, carbohidratos=52, grasas=16, orden=3),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Desayuno",   descripcion=context.getString(R.string.comida_tortilla_de_3_huevos_con_queso_y_tostada),                       calorias=540, proteinas=34, carbohidratos=42, grasas=20, orden=1),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Comida",     descripcion=context.getString(R.string.comida_pasta_con_pollo_brocoli_y_queso_parmesan),                      calorias=720, proteinas=54, carbohidratos=76, grasas=16, orden=2),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Cena",       descripcion=context.getString(R.string.comida_salmon_200g_con_patatas_y_esparragos),                          calorias=600, proteinas=50, carbohidratos=44, grasas=20, orden=3),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Desayuno",    descripcion=context.getString(R.string.comida_pancakes_proteicos_con_sirope_y_fruta),                           calorias=580, proteinas=40, carbohidratos=64, grasas=12, orden=1),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Comida",      descripcion=context.getString(R.string.comida_arroz_con_costillas_de_cerdo_magras_y_en),                  calorias=700, proteinas=50, carbohidratos=68, grasas=18, orden=2),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Cena",        descripcion=context.getString(R.string.comida_pechuga_de_pollo_con_quinoa_y_verduras),                          calorias=540, proteinas=48, carbohidratos=46, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Desayuno",   descripcion=context.getString(R.string.comida_batido_de_proteinas_con_avena_y_frutos_s),                    calorias=560, proteinas=38, carbohidratos=58, grasas=14, orden=1),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Comida",     descripcion=context.getString(R.string.comida_pollo_asado_con_patatas_y_pisto),                                 calorias=680, proteinas=52, carbohidratos=56, grasas=16, orden=2),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Cena",       descripcion=context.getString(R.string.comida_tortilla_de_3_huevos_con_champinones_y_j),                   calorias=420, proteinas=36, carbohidratos=8,  grasas=22, orden=3)
            )
        ),

        // ════════════════════════════════════════════════════
        // GANAR MÚSCULO · DÍA · SIN GLUTEN
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_4_nombre),
            objetivo    = "Ganar músculo",
            restriccion = "Sin gluten",
            calorias    = 2600,
            tipo        = TipoDieta.DIA.name,
            descripcion = context.getString(R.string.cat_diet_4_desc),
            iconoEmoji  = "🍚",
            comidas     = listOf(
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion=context.getString(R.string.comida_tortilla_de_4_huevos_con_arroz_inflado_y),                     calorias=580, proteinas=38, carbohidratos=60, grasas=16, orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana", descripcion=context.getString(R.string.comida_batido_de_proteinas_sin_gluten_con_leche),            calorias=340, proteinas=32, carbohidratos=34, grasas=6,  orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion=context.getString(R.string.comida_arroz_blanco_con_pollo_200g_aceite_de_ol),             calorias=680, proteinas=52, carbohidratos=70, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion=context.getString(R.string.comida_yogur_griego_con_nueces_y_miel),                                       calorias=320, proteinas=18, carbohidratos=28, grasas=14, orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion=context.getString(R.string.comida_salmon_200g_con_quinoa_y_esparragos_a_la),                   calorias=580, proteinas=50, carbohidratos=42, grasas=20, orden=5)
            )
        ),

        // ════════════════════════════════════════════════════
        // MANTENIMIENTO · SEMANAL · SIN LÁCTEOS
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_5_nombre),
            objetivo    = "Mantenimiento",
            restriccion = "Sin lácteos",
            calorias    = 2100,
            tipo        = TipoDieta.SEMANAL.name,
            descripcion = context.getString(R.string.cat_diet_5_desc),
            iconoEmoji  = "⚖️",
            comidas     = listOf(
                ComidaEntity(dietaId=0, dia="Lunes", momento="Desayuno",     descripcion=context.getString(R.string.comida_avena_con_leche_de_almendras_platano_y_s),        calorias=380, proteinas=12, carbohidratos=58, grasas=10, orden=1),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Media mañana", descripcion=context.getString(R.string.comida_punado_de_frutos_secos_mixtos),                                   calorias=180, proteinas=5,  carbohidratos=8,  grasas=15, orden=2),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Comida",       descripcion=context.getString(R.string.comida_pollo_al_horno_con_arroz_y_verduras_salt), calorias=580, proteinas=46, carbohidratos=54, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Merienda",     descripcion=context.getString(R.string.comida_manzana_con_mantequilla_de_almendra),                             calorias=200, proteinas=4,  carbohidratos=24, grasas=10, orden=4),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Cena",         descripcion=context.getString(R.string.comida_merluza_al_horno_con_patatas_y_pisto_de_),                calorias=420, proteinas=38, carbohidratos=36, grasas=8,  orden=5),
                ComidaEntity(dietaId=0, dia="Martes", momento="Desayuno",    descripcion=context.getString(R.string.comida_tostadas_de_pan_integral_con_aguacate_y_),             calorias=400, proteinas=16, carbohidratos=38, grasas=20, orden=1),
                ComidaEntity(dietaId=0, dia="Martes", momento="Comida",      descripcion=context.getString(R.string.comida_lentejas_con_verduras_pimenton_y_aceite_),               calorias=480, proteinas=24, carbohidratos=62, grasas=8,  orden=2),
                ComidaEntity(dietaId=0, dia="Martes", momento="Cena",        descripcion=context.getString(R.string.comida_pavo_a_la_plancha_con_quinoa_y_espinacas),                        calorias=460, proteinas=44, carbohidratos=40, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Desayuno", descripcion=context.getString(R.string.comida_smoothie_bowl_de_mango_con_granola_sin_l),           calorias=420, proteinas=10, carbohidratos=68, grasas=12, orden=1),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Comida",   descripcion=context.getString(R.string.comida_salmon_con_arroz_basmati_y_brocoli_al_va),                     calorias=560, proteinas=46, carbohidratos=48, grasas=18, orden=2),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Cena",     descripcion=context.getString(R.string.comida_revuelto_de_tofu_con_champinones_pimient),            calorias=360, proteinas=20, carbohidratos=18, grasas=18, orden=3),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Desayuno",    descripcion=context.getString(R.string.comida_porridge_de_avena_con_leche_de_coco_y_fr),              calorias=400, proteinas=10, carbohidratos=60, grasas=12, orden=1),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Comida",      descripcion=context.getString(R.string.comida_pollo_salteado_con_pasta_integral_y_toma),              calorias=580, proteinas=44, carbohidratos=62, grasas=12, orden=2),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Cena",        descripcion=context.getString(R.string.comida_ensalada_de_atun_con_garbanzos_pepino_y_),              calorias=400, proteinas=30, carbohidratos=32, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Desayuno",   descripcion=context.getString(R.string.comida_tostadas_con_hummus_tomate_y_aguacate),                          calorias=380, proteinas=12, carbohidratos=44, grasas=16, orden=1),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Comida",     descripcion=context.getString(R.string.comida_arroz_con_gambas_ajo_y_perejil),                                 calorias=540, proteinas=36, carbohidratos=62, grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Cena",       descripcion=context.getString(R.string.comida_pechuga_de_pollo_con_boniato_asado_y_ens),             calorias=480, proteinas=42, carbohidratos=42, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Desayuno",    descripcion=context.getString(R.string.comida_tortitas_de_avena_con_leche_de_avena_y_m),                     calorias=420, proteinas=14, carbohidratos=66, grasas=8,  orden=1),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Comida",      descripcion=context.getString(R.string.comida_paella_de_verduras_con_aceite_de_oliva),                          calorias=560, proteinas=14, carbohidratos=80, grasas=16, orden=2),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Cena",        descripcion=context.getString(R.string.comida_salmon_a_la_plancha_con_ensalada_templad),             calorias=520, proteinas=44, carbohidratos=38, grasas=18, orden=3),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Desayuno",   descripcion=context.getString(R.string.comida_batido_de_platano_espinacas_y_leche_de_a),                   calorias=320, proteinas=8,  carbohidratos=56, grasas=6,  orden=1),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Comida",     descripcion=context.getString(R.string.comida_pollo_asado_con_patatas_y_pimientos),                             calorias=600, proteinas=48, carbohidratos=52, grasas=16, orden=2),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Cena",       descripcion=context.getString(R.string.comida_crema_de_calabacin_con_tostadas_de_pan_i),                 calorias=300, proteinas=8,  carbohidratos=44, grasas=8,  orden=3)
            )
        ),

        // ════════════════════════════════════════════════════
        // MANTENIMIENTO · DÍA · SIN RESTRICCIONES
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_6_nombre),
            objetivo    = "Mantenimiento",
            restriccion = "Ninguna",
            calorias    = 2000,
            tipo        = TipoDieta.DIA.name,
            descripcion = context.getString(R.string.cat_diet_6_desc),
            iconoEmoji  = "🍽️",
            comidas     = listOf(
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion=context.getString(R.string.comida_tostadas_con_aceite_tomate_y_jamon_serra),         calorias=380, proteinas=18, carbohidratos=42, grasas=14, orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana", descripcion=context.getString(R.string.comida_yogur_con_nueces_y_una_pieza_de_fruta),                                calorias=220, proteinas=8,  carbohidratos=26, grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion=context.getString(R.string.comida_lentejas_estofadas_con_chorizo_zanahoria),                   calorias=560, proteinas=28, carbohidratos=62, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion=context.getString(R.string.comida_pan_integral_con_queso_fresco_y_tomate),                               calorias=200, proteinas=10, carbohidratos=24, grasas=6,  orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion=context.getString(R.string.comida_dorada_al_horno_con_patatas_panadera_y_e),                calorias=420, proteinas=36, carbohidratos=34, grasas=12, orden=5)
            )
        ),

        // ════════════════════════════════════════════════════
        // DEFINICIÓN · SEMANAL · SIN RESTRICCIONES
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_7_nombre),
            objetivo    = "Definición",
            restriccion = "Ninguna",
            calorias    = 1900,
            tipo        = TipoDieta.SEMANAL.name,
            descripcion = context.getString(R.string.cat_diet_7_desc),
            iconoEmoji  = "🔥",
            comidas     = listOf(
                ComidaEntity(dietaId=0, dia="Lunes", momento="Desayuno",     descripcion=context.getString(R.string.comida_claras_de_huevo_revueltas_6_con_espinaca),            calorias=220, proteinas=30, carbohidratos=8,  grasas=4,  orden=1),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Media mañana", descripcion=context.getString(R.string.comida_batido_de_proteinas_con_agua_y_almendras),                        calorias=240, proteinas=28, carbohidratos=8,  grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Comida",       descripcion=context.getString(R.string.comida_pechuga_de_pollo_200g_con_arroz_integral), calorias=480, proteinas=52, carbohidratos=42, grasas=6,  orden=3),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Merienda",     descripcion=context.getString(R.string.comida_atun_al_natural_con_pepino_y_zanahoria),                          calorias=140, proteinas=22, carbohidratos=6,  grasas=2,  orden=4),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Cena",         descripcion=context.getString(R.string.comida_merluza_200g_con_judias_verdes_y_esparra),          calorias=280, proteinas=42, carbohidratos=10, grasas=6,  orden=5),
                ComidaEntity(dietaId=0, dia="Martes", momento="Desayuno",    descripcion=context.getString(R.string.comida_yogur_griego_0pct_con_proteina_en_polvo_),                  calorias=240, proteinas=30, carbohidratos=16, grasas=2,  orden=1),
                ComidaEntity(dietaId=0, dia="Martes", momento="Comida",      descripcion=context.getString(R.string.comida_ternera_magra_180g_con_quinoa_y_espinaca),                     calorias=480, proteinas=50, carbohidratos=38, grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="Martes", momento="Cena",        descripcion=context.getString(R.string.comida_salmon_150g_con_ensalada_verde_y_aguacat),                     calorias=380, proteinas=36, carbohidratos=8,  grasas=22, orden=3),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Desayuno", descripcion=context.getString(R.string.comida_tortilla_de_2_huevos_plus_4_claras_con_c),                 calorias=240, proteinas=28, carbohidratos=4,  grasas=10, orden=1),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Comida",   descripcion=context.getString(R.string.comida_pavo_200g_con_boniato_150g_y_brocoli),                        calorias=460, proteinas=50, carbohidratos=38, grasas=6,  orden=2),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Cena",     descripcion=context.getString(R.string.comida_gambas_a_la_plancha_con_ensalada_de_rucu),             calorias=280, proteinas=32, carbohidratos=6,  grasas=12, orden=3),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Desayuno",    descripcion=context.getString(R.string.comida_claras_revueltas_con_avena_y_canela),                             calorias=280, proteinas=26, carbohidratos=28, grasas=4,  orden=1),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Comida",      descripcion=context.getString(R.string.comida_atun_2_latas_con_arroz_integral_y_tomate),                     calorias=440, proteinas=44, carbohidratos=44, grasas=6,  orden=2),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Cena",        descripcion=context.getString(R.string.comida_pollo_180g_con_ensalada_variada_y_aceite),     calorias=320, proteinas=42, carbohidratos=8,  grasas=12, orden=3),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Desayuno",   descripcion=context.getString(R.string.comida_batido_proteico_con_leche_desnatada_y_fr),                    calorias=260, proteinas=30, carbohidratos=22, grasas=3,  orden=1),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Comida",     descripcion=context.getString(R.string.comida_pechuga_de_pollo_con_pasta_integral_60g_),      calorias=480, proteinas=50, carbohidratos=46, grasas=6,  orden=2),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Cena",       descripcion=context.getString(R.string.comida_bacalao_al_horno_con_pisto_de_verduras),                          calorias=300, proteinas=40, carbohidratos=14, grasas=6,  orden=3),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Desayuno",    descripcion=context.getString(R.string.comida_avena_con_proteina_canela_y_arandanos),                          calorias=320, proteinas=28, carbohidratos=36, grasas=5,  orden=1),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Comida",      descripcion=context.getString(R.string.comida_ternera_180g_con_arroz_y_judias_verdes),                        calorias=500, proteinas=50, carbohidratos=44, grasas=12, orden=2),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Cena",        descripcion=context.getString(R.string.comida_ensalada_de_pollo_aguacate_y_tomate_cher),                     calorias=360, proteinas=36, carbohidratos=10, grasas=18, orden=3),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Desayuno",   descripcion=context.getString(R.string.comida_tortilla_de_3_huevos_con_pavo_y_tomate),                          calorias=280, proteinas=28, carbohidratos=4,  grasas=14, orden=1),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Comida",     descripcion=context.getString(R.string.comida_salmon_200g_con_quinoa_y_esparragos),                           calorias=520, proteinas=48, carbohidratos=38, grasas=18, orden=2),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Cena",       descripcion=context.getString(R.string.comida_pechuga_de_pavo_con_brocoli_y_champinone),             calorias=300, proteinas=44, carbohidratos=8,  grasas=8,  orden=3)
            )
        ),

        // ════════════════════════════════════════════════════
        // DEFINICIÓN · DÍA · VEGANA
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_8_nombre),
            objetivo    = "Definición",
            restriccion = "Vegana",
            calorias    = 1700,
            tipo        = TipoDieta.DIA.name,
            descripcion = context.getString(R.string.cat_diet_8_desc),
            iconoEmoji  = "🌿",
            comidas     = listOf(
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion=context.getString(R.string.comida_batido_de_proteina_vegana_con_leche_de_a),    calorias=320, proteinas=28, carbohidratos=40, grasas=5,  orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana", descripcion=context.getString(R.string.comida_edamame_100g_con_sal_marina),                                        calorias=120, proteinas=11, carbohidratos=8,  grasas=5,  orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion=context.getString(R.string.comida_tofu_firme_200g_salteado_con_brocoli_pim),      calorias=400, proteinas=30, carbohidratos=20, grasas=18, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion=context.getString(R.string.comida_hummus_50g_con_palitos_de_zanahoria_y_ap),                         calorias=140, proteinas=6,  carbohidratos=14, grasas=7,  orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion=context.getString(R.string.comida_tempeh_150g_con_quinoa_y_espinacas_salte),               calorias=460, proteinas=36, carbohidratos=40, grasas=14, orden=5)
            )
        ),


        // ════════════════════════════════════════════════════
        // PERDER PESO · DÍA · KETO
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_9_nombre),
            objetivo    = "Perder peso",
            restriccion = "Keto",
            calorias    = 1600,
            tipo        = TipoDieta.DIA.name,
            descripcion = context.getString(R.string.cat_diet_9_desc),
            iconoEmoji  = "🥑",
            comidas     = listOf(
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion=context.getString(R.string.comida_huevos_revueltos_con_aguacate_y_lonchas_), calorias=450, proteinas=22, carbohidratos=4, grasas=38, orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion=context.getString(R.string.comida_salmon_al_horno_con_esparragos_trigueros), calorias=550, proteinas=42, carbohidratos=5, grasas=40, orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion=context.getString(R.string.comida_punado_de_nueces_de_macadamia_o_almendra), calorias=200, proteinas=3, carbohidratos=2, grasas=21, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion=context.getString(R.string.comida_ensalada_de_espinacas_pechuga_de_pollo_y), calorias=400, proteinas=35, carbohidratos=6, grasas=26, orden=4)
            )
        ),

        // ════════════════════════════════════════════════════
        // GANAR MÚSCULO · DÍA · VOLUMEN EXTREMO
        // ════════════════════════════════════════════════════
        DietaTemplate(
            nombre      = context.getString(R.string.cat_diet_10_nombre),
            objetivo    = "Ganar músculo",
            restriccion = "Ninguna",
            calorias    = 3400,
            tipo        = TipoDieta.DIA.name,
            descripcion = context.getString(R.string.cat_diet_10_desc),
            iconoEmoji  = "🦍",
            comidas     = listOf(
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion=context.getString(R.string.comida_5_huevos_enteros_100g_de_avena_1_platano), calorias=850, proteinas=45, carbohidratos=80, grasas=40, orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana", descripcion=context.getString(R.string.comida_sandwich_doble_de_crema_de_cacahuete_y_m), calorias=450, proteinas=15, carbohidratos=55, grasas=22, orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion=context.getString(R.string.comida_250g_de_macarrones_con_200g_de_carne_pic), calorias=900, proteinas=60, carbohidratos=95, grasas=30, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion=context.getString(R.string.comida_batido_de_proteinas_mass_gainer_plus_pun), calorias=450, proteinas=35, carbohidratos=45, grasas=15, orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion=context.getString(R.string.comida_medio_pollo_asado_con_2_patatas_medianas), calorias=750, proteinas=55, carbohidratos=60, grasas=25, orden=5)
            )
        )
    )

    fun porObjetivo(objetivo: String, context: Context)     = todas(context).filter { it.objetivo == objetivo }
    fun porRestriccion(r: String, context: Context)         = todas(context).filter { it.restriccion == r }
    fun objetivosDisponibles(context: Context)            = todas(context).map { it.objetivo }.distinct()
    fun restriccionesDisponibles(context: Context)        = todas(context).map { it.restriccion }.distinct()
}
