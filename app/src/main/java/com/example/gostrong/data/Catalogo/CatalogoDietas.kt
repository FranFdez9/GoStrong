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
                ComidaEntity(dietaId=0, dia="Lunes", momento="Desayuno",      descripcion="Avena con leche desnatada, plátano y canela",                    calorias=320, proteinas=12, carbohidratos=55, grasas=5,  orden=1),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Media mañana",  descripcion="Manzana + 10 almendras",                                         calorias=150, proteinas=3,  carbohidratos=20, grasas=7,  orden=2),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Comida",        descripcion="Pechuga de pollo a la plancha con arroz integral y ensalada",    calorias=480, proteinas=42, carbohidratos=45, grasas=8,  orden=3),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Merienda",      descripcion="Yogur desnatado con fresas",                                     calorias=120, proteinas=8,  carbohidratos=16, grasas=1,  orden=4),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Cena",          descripcion="Merluza al horno con brócoli al vapor y patata cocida",          calorias=380, proteinas=38, carbohidratos=30, grasas=6,  orden=5),
                // MARTES
                ComidaEntity(dietaId=0, dia="Martes", momento="Desayuno",     descripcion="Tostadas de pan integral con tomate y aceite de oliva",          calorias=280, proteinas=8,  carbohidratos=42, grasas=9,  orden=1),
                ComidaEntity(dietaId=0, dia="Martes", momento="Media mañana", descripcion="Pera + infusión sin azúcar",                                     calorias=80,  proteinas=1,  carbohidratos=18, grasas=0,  orden=2),
                ComidaEntity(dietaId=0, dia="Martes", momento="Comida",       descripcion="Lentejas con verduras y zanahoria",                              calorias=420, proteinas=22, carbohidratos=58, grasas=5,  orden=3),
                ComidaEntity(dietaId=0, dia="Martes", momento="Merienda",     descripcion="Queso fresco 0% con pepino",                                     calorias=90,  proteinas=10, carbohidratos=4,  grasas=1,  orden=4),
                ComidaEntity(dietaId=0, dia="Martes", momento="Cena",         descripcion="Tortilla francesa de 2 huevos con espárragos trigueros",         calorias=280, proteinas=18, carbohidratos=6,  grasas=14, orden=5),
                // MIÉRCOLES
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Desayuno",     descripcion="Batido de proteínas con leche y plátano",                     calorias=310, proteinas=28, carbohidratos=38, grasas=4,  orden=1),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Media mañana", descripcion="Naranja + té verde",                                          calorias=70,  proteinas=1,  carbohidratos=16, grasas=0,  orden=2),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Comida",       descripcion="Salmón a la plancha con quinoa y espinacas salteadas",        calorias=490, proteinas=40, carbohidratos=35, grasas=16, orden=3),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Merienda",     descripcion="Zanahoria y apio con hummus (2 cdas)",                        calorias=120, proteinas=4,  carbohidratos=14, grasas=5,  orden=4),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Cena",         descripcion="Sopa de verduras con pollo desmenuzado",                      calorias=300, proteinas=28, carbohidratos=24, grasas=5,  orden=5),
                // JUEVES
                ComidaEntity(dietaId=0, dia="Jueves", momento="Desayuno",     descripcion="Yogur griego 0% con granola y kiwi",                            calorias=290, proteinas=15, carbohidratos=42, grasas=5,  orden=1),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Media mañana", descripcion="Puñado de nueces (20g)",                                         calorias=130, proteinas=3,  carbohidratos=3,  grasas=13, orden=2),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Comida",       descripcion="Garbanzos con espinacas y huevo duro",                           calorias=440, proteinas=24, carbohidratos=50, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Merienda",     descripcion="Manzana + infusión",                                             calorias=80,  proteinas=0,  carbohidratos=20, grasas=0,  orden=4),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Cena",         descripcion="Pavo a la plancha con judías verdes y tomate cherry",            calorias=320, proteinas=36, carbohidratos=12, grasas=7,  orden=5),
                // VIERNES
                ComidaEntity(dietaId=0, dia="Viernes", momento="Desayuno",    descripcion="Tostada integral con aguacate y huevo revuelto",                 calorias=350, proteinas=16, carbohidratos=30, grasas=16, orden=1),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Media mañana",descripcion="Yogur desnatado",                                                calorias=80,  proteinas=8,  carbohidratos=10, grasas=0,  orden=2),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Comida",      descripcion="Pasta integral con atún y tomate natural",                       calorias=460, proteinas=30, carbohidratos=58, grasas=8,  orden=3),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Merienda",    descripcion="Pieza de fruta + té",                                            calorias=80,  proteinas=1,  carbohidratos=18, grasas=0,  orden=4),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Cena",        descripcion="Ensalada templada de gambas con aguacate y rúcula",              calorias=340, proteinas=28, carbohidratos=10, grasas=18, orden=5),
                // SÁBADO
                ComidaEntity(dietaId=0, dia="Sábado", momento="Desayuno",     descripcion="Porridge de avena con frutos rojos y semillas de chía",          calorias=340, proteinas=12, carbohidratos=52, grasas=8,  orden=1),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Comida",       descripcion="Pollo al curry con arroz basmati y verduras",                    calorias=480, proteinas=38, carbohidratos=48, grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Cena",         descripcion="Revuelto de champiñones, espárragos y jamón serrano",            calorias=280, proteinas=22, carbohidratos=8,  grasas=14, orden=3),
                // DOMINGO
                ComidaEntity(dietaId=0, dia="Domingo", momento="Desayuno",    descripcion="Tortitas de avena con miel y plátano",                           calorias=360, proteinas=14, carbohidratos=60, grasas=6,  orden=1),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Comida",      descripcion="Merluza al horno con patatas y pisto de verduras",               calorias=420, proteinas=36, carbohidratos=38, grasas=8,  orden=2),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Cena",        descripcion="Crema de calabaza con tostadas integrales",                      calorias=280, proteinas=8,  carbohidratos=40, grasas=7,  orden=3)
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
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",      descripcion="Batido verde: espinacas, plátano, leche de avena y semillas de lino", calorias=280, proteinas=8,  carbohidratos=48, grasas=6,  orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana",  descripcion="Manzana con mantequilla de cacahuete (1 cda)",                        calorias=160, proteinas=4,  carbohidratos=22, grasas=7,  orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",        descripcion="Bowl de quinoa con garbanzos, aguacate, tomate y pepino",             calorias=480, proteinas=20, carbohidratos=58, grasas=16, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",      descripcion="Edamame cocido (100g)",                                               calorias=120, proteinas=11, carbohidratos=8,  grasas=5,  orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",          descripcion="Wok de tofu con brócoli, pimiento y salsa de soja baja en sodio",     calorias=360, proteinas=22, carbohidratos=28, grasas=14, orden=5)
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
                ComidaEntity(dietaId=0, dia="Lunes", momento="Desayuno",     descripcion="Tortilla de 4 huevos con avena, plátano y leche",                 calorias=620, proteinas=40, carbohidratos=68, grasas=16, orden=1),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Media mañana", descripcion="Batido de proteínas con leche entera y plátano",                  calorias=380, proteinas=35, carbohidratos=42, grasas=7,  orden=2),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Comida",       descripcion="Arroz blanco con pechuga de pollo (200g) y aceite de oliva",      calorias=680, proteinas=52, carbohidratos=72, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Merienda",     descripcion="Pan integral con pavo y queso",                                   calorias=340, proteinas=22, carbohidratos=38, grasas=10, orden=4),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Cena",         descripcion="Salmón (200g) con patata cocida y brócoli",                       calorias=580, proteinas=48, carbohidratos=42, grasas=18, orden=5),
                // MARTES
                ComidaEntity(dietaId=0, dia="Martes", momento="Desayuno",    descripcion="Porridge de avena con proteína en polvo, nueces y miel",          calorias=580, proteinas=38, carbohidratos=70, grasas=14, orden=1),
                ComidaEntity(dietaId=0, dia="Martes", momento="Media mañana",descripcion="Requesón con frutos rojos y almendras",                           calorias=320, proteinas=28, carbohidratos=22, grasas=12, orden=2),
                ComidaEntity(dietaId=0, dia="Martes", momento="Comida",      descripcion="Pasta integral con ternera picada y salsa de tomate natural",     calorias=700, proteinas=50, carbohidratos=78, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="Martes", momento="Merienda",    descripcion="Batido post-entreno: whey + leche + plátano",                     calorias=360, proteinas=32, carbohidratos=44, grasas=5,  orden=4),
                ComidaEntity(dietaId=0, dia="Martes", momento="Cena",        descripcion="Pechuga de pavo (200g) con arroz y espinacas",                    calorias=520, proteinas=50, carbohidratos=46, grasas=8,  orden=5),
                // MIÉRCOLES
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Desayuno",    descripcion="Huevos revueltos (4) con tostadas integrales y aguacate",      calorias=600, proteinas=36, carbohidratos=44, grasas=28, orden=1),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Media mañana",descripcion="Yogur griego con granola y plátano",                           calorias=380, proteinas=20, carbohidratos=52, grasas=8,  orden=2),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Comida",      descripcion="Arroz con atún (2 latas), pimiento y maíz",                    calorias=620, proteinas=48, carbohidratos=70, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Merienda",    descripcion="Sándwich de pollo con queso y lechuga",                        calorias=380, proteinas=28, carbohidratos=40, grasas=12, orden=4),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Cena",        descripcion="Merluza (200g) con quinoa y verduras asadas",                  calorias=520, proteinas=46, carbohidratos=48, grasas=10, orden=5),
                // JUEVES-DOMINGO (días simplificados)
                ComidaEntity(dietaId=0, dia="Jueves", momento="Desayuno",    descripcion="Avena con leche, proteína y fruta",                               calorias=560, proteinas=36, carbohidratos=65, grasas=10, orden=1),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Comida",      descripcion="Pollo (200g) con patatas asadas y ensalada",                      calorias=680, proteinas=52, carbohidratos=58, grasas=14, orden=2),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Cena",        descripcion="Ternera (180g) con arroz integral y brócoli",                     calorias=620, proteinas=50, carbohidratos=52, grasas=16, orden=3),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Desayuno",   descripcion="Tortilla de 3 huevos con queso y tostadas",                       calorias=540, proteinas=34, carbohidratos=42, grasas=20, orden=1),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Comida",     descripcion="Pasta con pollo, brócoli y queso parmesano",                      calorias=720, proteinas=54, carbohidratos=76, grasas=16, orden=2),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Cena",       descripcion="Salmón (200g) con patatas y espárragos",                          calorias=600, proteinas=50, carbohidratos=44, grasas=20, orden=3),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Desayuno",    descripcion="Pancakes proteicos con sirope y fruta",                           calorias=580, proteinas=40, carbohidratos=64, grasas=12, orden=1),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Comida",      descripcion="Arroz con costillas de cerdo magras y ensalada",                  calorias=700, proteinas=50, carbohidratos=68, grasas=18, orden=2),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Cena",        descripcion="Pechuga de pollo con quinoa y verduras",                          calorias=540, proteinas=48, carbohidratos=46, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Desayuno",   descripcion="Batido de proteínas con avena y frutos secos",                    calorias=560, proteinas=38, carbohidratos=58, grasas=14, orden=1),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Comida",     descripcion="Pollo asado con patatas y pisto",                                 calorias=680, proteinas=52, carbohidratos=56, grasas=16, orden=2),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Cena",       descripcion="Tortilla de 3 huevos con champiñones y jamón",                   calorias=420, proteinas=36, carbohidratos=8,  grasas=22, orden=3)
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
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion="Tortilla de 4 huevos con arroz inflado y plátano",                     calorias=580, proteinas=38, carbohidratos=60, grasas=16, orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana", descripcion="Batido de proteínas (sin gluten) con leche y frutos rojos",            calorias=340, proteinas=32, carbohidratos=34, grasas=6,  orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion="Arroz blanco con pollo (200g), aceite de oliva y brócoli",             calorias=680, proteinas=52, carbohidratos=70, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion="Yogur griego con nueces y miel",                                       calorias=320, proteinas=18, carbohidratos=28, grasas=14, orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion="Salmón (200g) con quinoa y espárragos a la plancha",                   calorias=580, proteinas=50, carbohidratos=42, grasas=20, orden=5)
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
                ComidaEntity(dietaId=0, dia="Lunes", momento="Desayuno",     descripcion="Avena con leche de almendras, plátano y semillas de chía",        calorias=380, proteinas=12, carbohidratos=58, grasas=10, orden=1),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Media mañana", descripcion="Puñado de frutos secos mixtos",                                   calorias=180, proteinas=5,  carbohidratos=8,  grasas=15, orden=2),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Comida",       descripcion="Pollo al horno con arroz y verduras salteadas en aceite de coco", calorias=580, proteinas=46, carbohidratos=54, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Merienda",     descripcion="Manzana con mantequilla de almendra",                             calorias=200, proteinas=4,  carbohidratos=24, grasas=10, orden=4),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Cena",         descripcion="Merluza al horno con patatas y pisto de verduras",                calorias=420, proteinas=38, carbohidratos=36, grasas=8,  orden=5),
                ComidaEntity(dietaId=0, dia="Martes", momento="Desayuno",    descripcion="Tostadas de pan integral con aguacate y huevo poché",             calorias=400, proteinas=16, carbohidratos=38, grasas=20, orden=1),
                ComidaEntity(dietaId=0, dia="Martes", momento="Comida",      descripcion="Lentejas con verduras, pimentón y aceite de oliva",               calorias=480, proteinas=24, carbohidratos=62, grasas=8,  orden=2),
                ComidaEntity(dietaId=0, dia="Martes", momento="Cena",        descripcion="Pavo a la plancha con quinoa y espinacas",                        calorias=460, proteinas=44, carbohidratos=40, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Desayuno", descripcion="Smoothie bowl de mango con granola sin lácteos y coco",           calorias=420, proteinas=10, carbohidratos=68, grasas=12, orden=1),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Comida",   descripcion="Salmón con arroz basmati y brócoli al vapor",                     calorias=560, proteinas=46, carbohidratos=48, grasas=18, orden=2),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Cena",     descripcion="Revuelto de tofu con champiñones, pimiento y cebolla",            calorias=360, proteinas=20, carbohidratos=18, grasas=18, orden=3),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Desayuno",    descripcion="Porridge de avena con leche de coco y frutos rojos",              calorias=400, proteinas=10, carbohidratos=60, grasas=12, orden=1),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Comida",      descripcion="Pollo salteado con pasta integral y tomate natural",              calorias=580, proteinas=44, carbohidratos=62, grasas=12, orden=2),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Cena",        descripcion="Ensalada de atún con garbanzos, pepino y aceitunas",              calorias=400, proteinas=30, carbohidratos=32, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Desayuno",   descripcion="Tostadas con hummus, tomate y aguacate",                          calorias=380, proteinas=12, carbohidratos=44, grasas=16, orden=1),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Comida",     descripcion="Arroz con gambas, ajo y perejil",                                 calorias=540, proteinas=36, carbohidratos=62, grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Cena",       descripcion="Pechuga de pollo con boniato asado y ensalada verde",             calorias=480, proteinas=42, carbohidratos=42, grasas=10, orden=3),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Desayuno",    descripcion="Tortitas de avena con leche de avena y miel",                     calorias=420, proteinas=14, carbohidratos=66, grasas=8,  orden=1),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Comida",      descripcion="Paella de verduras con aceite de oliva",                          calorias=560, proteinas=14, carbohidratos=80, grasas=16, orden=2),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Cena",        descripcion="Salmón a la plancha con ensalada templada de quinoa",             calorias=520, proteinas=44, carbohidratos=38, grasas=18, orden=3),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Desayuno",   descripcion="Batido de plátano, espinacas y leche de avena",                   calorias=320, proteinas=8,  carbohidratos=56, grasas=6,  orden=1),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Comida",     descripcion="Pollo asado con patatas y pimientos",                             calorias=600, proteinas=48, carbohidratos=52, grasas=16, orden=2),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Cena",       descripcion="Crema de calabacín con tostadas de pan integral",                 calorias=300, proteinas=8,  carbohidratos=44, grasas=8,  orden=3)
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
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion="Tostadas con aceite, tomate y jamón serrano + café con leche",         calorias=380, proteinas=18, carbohidratos=42, grasas=14, orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana", descripcion="Yogur con nueces y una pieza de fruta",                                calorias=220, proteinas=8,  carbohidratos=26, grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion="Lentejas estofadas con chorizo, zanahoria y patata",                   calorias=560, proteinas=28, carbohidratos=62, grasas=14, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion="Pan integral con queso fresco y tomate",                               calorias=200, proteinas=10, carbohidratos=24, grasas=6,  orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion="Dorada al horno con patatas panadera y ensalada mixta",                calorias=420, proteinas=36, carbohidratos=34, grasas=12, orden=5)
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
                ComidaEntity(dietaId=0, dia="Lunes", momento="Desayuno",     descripcion="Claras de huevo revueltas (6) con espinacas y tomate",            calorias=220, proteinas=30, carbohidratos=8,  grasas=4,  orden=1),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Media mañana", descripcion="Batido de proteínas con agua y almendras",                        calorias=240, proteinas=28, carbohidratos=8,  grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Comida",       descripcion="Pechuga de pollo (200g) con arroz integral (60g seco) y brócoli", calorias=480, proteinas=52, carbohidratos=42, grasas=6,  orden=3),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Merienda",     descripcion="Atún al natural con pepino y zanahoria",                          calorias=140, proteinas=22, carbohidratos=6,  grasas=2,  orden=4),
                ComidaEntity(dietaId=0, dia="Lunes", momento="Cena",         descripcion="Merluza (200g) con judías verdes y espárragos al vapor",          calorias=280, proteinas=42, carbohidratos=10, grasas=6,  orden=5),
                ComidaEntity(dietaId=0, dia="Martes", momento="Desayuno",    descripcion="Yogur griego 0% con proteína en polvo y fresas",                  calorias=240, proteinas=30, carbohidratos=16, grasas=2,  orden=1),
                ComidaEntity(dietaId=0, dia="Martes", momento="Comida",      descripcion="Ternera magra (180g) con quinoa y espinacas",                     calorias=480, proteinas=50, carbohidratos=38, grasas=10, orden=2),
                ComidaEntity(dietaId=0, dia="Martes", momento="Cena",        descripcion="Salmón (150g) con ensalada verde y aguacate",                     calorias=380, proteinas=36, carbohidratos=8,  grasas=22, orden=3),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Desayuno", descripcion="Tortilla de 2 huevos + 4 claras con champiñones",                 calorias=240, proteinas=28, carbohidratos=4,  grasas=10, orden=1),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Comida",   descripcion="Pavo (200g) con boniato (150g) y brócoli",                        calorias=460, proteinas=50, carbohidratos=38, grasas=6,  orden=2),
                ComidaEntity(dietaId=0, dia="Miércoles", momento="Cena",     descripcion="Gambas a la plancha con ensalada de rúcula y tomate",             calorias=280, proteinas=32, carbohidratos=6,  grasas=12, orden=3),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Desayuno",    descripcion="Claras revueltas con avena y canela",                             calorias=280, proteinas=26, carbohidratos=28, grasas=4,  orden=1),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Comida",      descripcion="Atún (2 latas) con arroz integral y tomate",                     calorias=440, proteinas=44, carbohidratos=44, grasas=6,  orden=2),
                ComidaEntity(dietaId=0, dia="Jueves", momento="Cena",        descripcion="Pollo (180g) con ensalada variada y aceite de oliva (1 cda)",     calorias=320, proteinas=42, carbohidratos=8,  grasas=12, orden=3),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Desayuno",   descripcion="Batido proteico con leche desnatada y fresas",                    calorias=260, proteinas=30, carbohidratos=22, grasas=3,  orden=1),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Comida",     descripcion="Pechuga de pollo con pasta integral (60g seco) y albahaca",      calorias=480, proteinas=50, carbohidratos=46, grasas=6,  orden=2),
                ComidaEntity(dietaId=0, dia="Viernes", momento="Cena",       descripcion="Bacalao al horno con pisto de verduras",                          calorias=300, proteinas=40, carbohidratos=14, grasas=6,  orden=3),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Desayuno",    descripcion="Avena con proteína, canela y arándanos",                          calorias=320, proteinas=28, carbohidratos=36, grasas=5,  orden=1),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Comida",      descripcion="Ternera (180g) con arroz y judías verdes",                        calorias=500, proteinas=50, carbohidratos=44, grasas=12, orden=2),
                ComidaEntity(dietaId=0, dia="Sábado", momento="Cena",        descripcion="Ensalada de pollo, aguacate y tomate cherry",                     calorias=360, proteinas=36, carbohidratos=10, grasas=18, orden=3),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Desayuno",   descripcion="Tortilla de 3 huevos con pavo y tomate",                          calorias=280, proteinas=28, carbohidratos=4,  grasas=14, orden=1),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Comida",     descripcion="Salmón (200g) con quinoa y espárragos",                           calorias=520, proteinas=48, carbohidratos=38, grasas=18, orden=2),
                ComidaEntity(dietaId=0, dia="Domingo", momento="Cena",       descripcion="Pechuga de pavo con brócoli y champiñones salteados",             calorias=300, proteinas=44, carbohidratos=8,  grasas=8,  orden=3)
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
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion="Batido de proteína vegana con leche de avena, espinacas y plátano",    calorias=320, proteinas=28, carbohidratos=40, grasas=5,  orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana", descripcion="Edamame (100g) con sal marina",                                        calorias=120, proteinas=11, carbohidratos=8,  grasas=5,  orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion="Tofu firme (200g) salteado con brócoli, pimiento y salsa tamari",      calorias=400, proteinas=30, carbohidratos=20, grasas=18, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion="Hummus (50g) con palitos de zanahoria y apio",                         calorias=140, proteinas=6,  carbohidratos=14, grasas=7,  orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion="Tempeh (150g) con quinoa y espinacas salteadas con ajo",               calorias=460, proteinas=36, carbohidratos=40, grasas=14, orden=5)
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
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion="Huevos revueltos con aguacate y lonchas de pavo", calorias=450, proteinas=22, carbohidratos=4, grasas=38, orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion="Salmón al horno con espárragos trigueros a la mantequilla", calorias=550, proteinas=42, carbohidratos=5, grasas=40, orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion="Puñado de nueces de macadamia o almendras tostadas", calorias=200, proteinas=3, carbohidratos=2, grasas=21, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion="Ensalada de espinacas, pechuga de pollo y queso feta con aceite de oliva", calorias=400, proteinas=35, carbohidratos=6, grasas=26, orden=4)
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
                ComidaEntity(dietaId=0, dia="", momento="Desayuno",     descripcion="5 huevos enteros, 100g de avena, 1 plátano y un vaso de leche entera", calorias=850, proteinas=45, carbohidratos=80, grasas=40, orden=1),
                ComidaEntity(dietaId=0, dia="", momento="Media mañana", descripcion="Sándwich doble de crema de cacahuete y mermelada", calorias=450, proteinas=15, carbohidratos=55, grasas=22, orden=2),
                ComidaEntity(dietaId=0, dia="", momento="Comida",       descripcion="250g de macarrones con 200g de carne picada vacuna y queso rallado", calorias=900, proteinas=60, carbohidratos=95, grasas=30, orden=3),
                ComidaEntity(dietaId=0, dia="", momento="Merienda",     descripcion="Batido de proteínas Mass Gainer + puñado de almendras", calorias=450, proteinas=35, carbohidratos=45, grasas=15, orden=4),
                ComidaEntity(dietaId=0, dia="", momento="Cena",         descripcion="Medio pollo asado con 2 patatas medianas asadas al horno", calorias=750, proteinas=55, carbohidratos=60, grasas=25, orden=5)
            )
        )
    )

    fun porObjetivo(objetivo: String, context: Context)     = todas(context).filter { it.objetivo == objetivo }
    fun porRestriccion(r: String, context: Context)         = todas(context).filter { it.restriccion == r }
    fun objetivosDisponibles(context: Context)            = todas(context).map { it.objetivo }.distinct()
    fun restriccionesDisponibles(context: Context)        = todas(context).map { it.restriccion }.distinct()
}
