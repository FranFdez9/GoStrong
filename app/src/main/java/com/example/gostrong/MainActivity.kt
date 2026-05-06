package com.example.gostrong

import android.os.Bundle

import androidx.activity.compose.setContent


import com.example.gostrong.data.local.AppDataBase
import com.example.gostrong.data.repository.DietaRepository
import com.example.gostrong.data.repository.RegistroPesoRepository
import com.example.gostrong.data.repository.RutinaRepository
import com.example.gostrong.data.repository.UsuarioRepository
import com.example.gostrong.data.util.ManejoSesion
import com.example.gostrong.ui.Navegacion.AppNavegacion
import com.example.gostrong.ui.Navegacion.Destino
import com.example.gostrong.ui.theme.GoStrongTheme
import com.example.gostrong.ui.theme.modoColorActual
import com.example.gostrong.viewmodel.DietaViewModel
import com.example.gostrong.viewmodel.RutinaViewModel
import com.example.gostrong.viewmodel.SaludViewModel
import com.example.gostrong.viewmodel.UsuarioViewModel
import androidx.fragment.app.FragmentActivity // Para poder usar el FACE ID
import com.example.gostrong.data.repository.HistorialActividadRepository
import com.example.gostrong.data.util.ThemeManager
import com.example.gostrong.viewmodel.HistorialViewModel

import com.example.gostrong.data.util.LanguageManager
import android.content.Context
import android.content.res.Configuration
import java.util.Locale

class MainActivity : FragmentActivity() {

    private lateinit var usuarioViewModel: UsuarioViewModel
    private lateinit var saludViewModel:   SaludViewModel
    private lateinit var rutinaViewModel:  RutinaViewModel
    private lateinit var dietaViewModel:   DietaViewModel
    private lateinit var manejoSesion:     ManejoSesion
    private lateinit var historialViewModel:    HistorialViewModel

    override fun attachBaseContext(newBase: Context) {
        val languageManager = LanguageManager(newBase)
        val idioma = languageManager.obtenerIdioma()
        val locale = Locale(idioma)
        Locale.setDefault(locale)
        val config = Configuration(newBase.resources.configuration)
        config.setLocale(locale)
        super.attachBaseContext(newBase.createConfigurationContext(config))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDataBase.getDatabase(this)

        val usuarioRepository      = UsuarioRepository(database.usuarioDao())
        val registroPesoRepository = RegistroPesoRepository(database.registroPesoDao())
        val rutinaRepository       = RutinaRepository(
            rutinaDao    = database.rutinaDao(),
            ejercicioDao = database.ejercicioDao()
        )
        val dietaRepository = DietaRepository(
            dietaDao  = database.dietaDao(),
            comidaDao = database.comidaDao()
        )
        val historialRepository = HistorialActividadRepository(database.historialActividadDao())

        usuarioViewModel = UsuarioViewModel(usuarioRepository)
        saludViewModel   = SaludViewModel(usuarioRepository, registroPesoRepository)
        rutinaViewModel  = RutinaViewModel(rutinaRepository)
        dietaViewModel   = DietaViewModel(dietaRepository)
        historialViewModel = HistorialViewModel(historialRepository)
        manejoSesion    = ManejoSesion(this)
        val themeManager = ThemeManager(this)
        modoColorActual  = themeManager.obtenerModo()

        val emailUsuario = manejoSesion.obtenerEmailUsuario() ?: ""

        setContent {
            GoStrongTheme(modo = modoColorActual) {
                val destinoInicial = if (manejoSesion.haySesionActiva())
                    Destino.Home.ruta else Destino.Login.ruta

                AppNavegacion(
                    usuarioViewModel = usuarioViewModel,
                    saludViewModel   = saludViewModel,
                    rutinaViewModel  = rutinaViewModel,
                    dietaViewModel   = dietaViewModel,
                    historialViewModel = historialViewModel,
                    emailUsuario     = emailUsuario,
                    startDestination = destinoInicial,
                    onCerrarSesion   = { manejoSesion.cerrarSesion() }  // ← limpia SharedPreferences
                )
            }
        }
    }
}
