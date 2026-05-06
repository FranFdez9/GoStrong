package com.example.gostrong.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gostrong.data.local.dao.ComidaDao
import com.example.gostrong.data.local.dao.DietaDao
import com.example.gostrong.data.local.dao.EjercicioDao
import com.example.gostrong.data.local.dao.RegistroPesoDao
import com.example.gostrong.data.local.dao.RutinaDao
import com.example.gostrong.data.local.dao.UsuarioDao
import com.example.gostrong.data.local.entity.ComidaEntity
import com.example.gostrong.data.local.entity.DietaEntity
import com.example.gostrong.data.local.entity.EjercicioEntity
import com.example.gostrong.data.local.entity.RegistroPesoEntity
import com.example.gostrong.data.local.entity.RutinaEntity
import com.example.gostrong.data.local.entity.UsuarioEntity
import com.example.gostrong.data.local.dao.HistorialActividadDao
import com.example.gostrong.data.local.entity.HistorialActividadEntity
@Database(
    entities = [
        UsuarioEntity::class,
        RegistroPesoEntity::class,
        RutinaEntity::class,
        EjercicioEntity::class,
        DietaEntity::class,
        ComidaEntity::class,
        HistorialActividadEntity::class
    ],
    version = 6,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun registroPesoDao(): RegistroPesoDao
    abstract fun rutinaDao(): RutinaDao
    abstract fun ejercicioDao(): EjercicioDao
    abstract fun dietaDao(): DietaDao
    abstract fun comidaDao(): ComidaDao

    abstract fun historialActividadDao(): HistorialActividadDao


    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS registros_peso (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        emailUsuario TEXT NOT NULL,
                        peso REAL NOT NULL,
                        fecha INTEGER NOT NULL
                    )
                """.trimIndent())
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS rutinas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        emailUsuario TEXT NOT NULL,
                        nombre TEXT NOT NULL,
                        deporte TEXT NOT NULL,
                        nivel TEXT NOT NULL,
                        diasSemana INTEGER NOT NULL,
                        tipo TEXT NOT NULL DEFAULT 'SEMANAL',
                        fechaCreacion INTEGER NOT NULL
                    )
                """.trimIndent())
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS ejercicios (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        rutinaId INTEGER NOT NULL,
                        nombre TEXT NOT NULL,
                        series INTEGER NOT NULL,
                        repeticiones TEXT NOT NULL,
                        descanso TEXT NOT NULL,
                        dia TEXT NOT NULL DEFAULT '',
                        orden INTEGER NOT NULL DEFAULT 0,
                        notas TEXT NOT NULL DEFAULT '',
                        FOREIGN KEY(rutinaId) REFERENCES rutinas(id) ON DELETE CASCADE
                    )
                """.trimIndent())
                database.execSQL("CREATE INDEX IF NOT EXISTS index_ejercicios_rutinaId ON ejercicios(rutinaId)")
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS dietas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        emailUsuario TEXT NOT NULL,
                        nombre TEXT NOT NULL,
                        objetivo TEXT NOT NULL,
                        restriccion TEXT NOT NULL,
                        calorias INTEGER NOT NULL,
                        tipo TEXT NOT NULL DEFAULT 'SEMANAL',
                        fechaCreacion INTEGER NOT NULL
                    )
                """.trimIndent())
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS comidas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        dietaId INTEGER NOT NULL,
                        dia TEXT NOT NULL DEFAULT '',
                        momento TEXT NOT NULL,
                        descripcion TEXT NOT NULL,
                        calorias INTEGER NOT NULL,
                        proteinas INTEGER NOT NULL,
                        carbohidratos INTEGER NOT NULL,
                        grasas INTEGER NOT NULL,
                        orden INTEGER NOT NULL DEFAULT 0,
                        FOREIGN KEY(dietaId) REFERENCES dietas(id) ON DELETE CASCADE
                    )
                """.trimIndent())
                database.execSQL("CREATE INDEX IF NOT EXISTS index_comidas_dietaId ON comidas(dietaId)")
            }
        }

        private val MIGRATION_4_5 = object : Migration(4,5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS historial_actividad (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        emailUsuario TEXT NOT NULL,
                        fecha INTEGER NOT NULL,
                        tipo TEXT NOT NULL,
                        referenciaId INTEGER NOT NULL,
                        titulo TEXT NOT NULL,
                        estado TEXT NOT NULL,
                        notas TEXT NOT NULL
                    )
                """.trimIndent())
            }
        }

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "gym_database"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4,MIGRATION_4_5)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
