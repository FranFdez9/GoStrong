package com.example.gostrong.ui.Navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gostrong.ui.Navegacion.BarraNavegacionInferior
import com.example.gostrong.ui.Navegacion.Destino
import com.example.gostrong.ui.screens.ajustes.AjustesScreen
import com.example.gostrong.ui.screens.dietas.DetalleDietaScreen
import com.example.gostrong.ui.screens.dietas.DietasScreen
import com.example.gostrong.ui.screens.home.HomeScreen
import com.example.gostrong.ui.screens.login.LoginScreen
import com.example.gostrong.ui.screens.perfil.PerfilScreen
import com.example.gostrong.ui.screens.register.RegisterScreen
import com.example.gostrong.ui.screens.rutinas.DetalleRutinaScreen
import com.example.gostrong.ui.screens.rutinas.RutinasScreen
import com.example.gostrong.ui.screens.salud.SaludScreen
import com.example.gostrong.viewmodel.DietaViewModel
import com.example.gostrong.viewmodel.HistorialViewModel
import com.example.gostrong.viewmodel.RutinaViewModel
import com.example.gostrong.viewmodel.SaludViewModel
import com.example.gostrong.viewmodel.UsuarioViewModel

@Composable
fun AppNavegacion(
    usuarioViewModel: UsuarioViewModel,
    saludViewModel:   SaludViewModel,
    rutinaViewModel:  RutinaViewModel,
    dietaViewModel:   DietaViewModel,
    historialViewModel: HistorialViewModel,
    emailUsuario:     String,
    startDestination: String,
    onCerrarSesion:   () -> Unit      // ← nuevo: callback hacia MainActivity
) {
    val navController = rememberNavController()

    val rutasSinBottomNav = listOf(
        Destino.Login.ruta,
        Destino.Registro.ruta,
        Destino.DetalleRutina.ruta,
        Destino.DetalleDieta.ruta
    )
    val navBackStack by navController.currentBackStackEntryAsState()
    val rutaActual   = navBackStack?.destination?.route
    val mostrarBottom = rutaActual != null && rutaActual !in rutasSinBottomNav

    Scaffold(
        bottomBar = {
            if (mostrarBottom) BarraNavegacionInferior(navController = navController)
        }
    ) { paddingValues ->

        NavHost(
            navController    = navController,
            startDestination = startDestination,
            modifier         = Modifier.padding(paddingValues)
        ) {
            // ── AUTENTICACIÓN ──────────────────────────────────────────────
            composable(Destino.Login.ruta) {
                LoginScreen(
                    usuarioViewModel = usuarioViewModel,
                    login = {
                        navController.navigate(Destino.Home.ruta) {
                            popUpTo(Destino.Login.ruta) { inclusive = true }
                        }
                    },
                    registro = { navController.navigate(Destino.Registro.ruta) }
                )
            }
            composable(Destino.Registro.ruta) {
                RegisterScreen(
                    usuarioViewModel  = usuarioViewModel,
                    onRegistroExitoso = {
                        navController.navigate(Destino.Home.ruta) {
                            popUpTo(Destino.Login.ruta) { inclusive = true }
                        }
                    }
                )
            }

            // ── HOME ───────────────────────────────────────────────────────
            composable(Destino.Home.ruta) {
                HomeScreen(
                    usuarioViewModel = usuarioViewModel,
                    emailUsuario     = emailUsuario,
                    onIrARutinas     = { navController.navigate(Destino.Rutinas.ruta) },
                    onIrADietas      = { navController.navigate(Destino.Dietas.ruta) },
                    onIrASalud       = { navController.navigate(Destino.Salud.ruta) },
                    onIrAPerfil      = { navController.navigate(Destino.Perfil.ruta) },
                    onIrAAjustes     = { navController.navigate(Destino.Ajustes.ruta) }
                )
            }

            // ── SALUD / AJUSTES ────────────────────────────────────────────
            composable(Destino.Salud.ruta) {
                SaludScreen(
                    saludViewModel = saludViewModel,
                    historialViewModel = historialViewModel
                )
            }

            composable(Destino.Ajustes.ruta) { AjustesScreen() }

            // ── RUTINAS ────────────────────────────────────────────────────
            composable(Destino.Rutinas.ruta) {
                RutinasScreen(
                    rutinaViewModel = rutinaViewModel,
                    emailUsuario    = emailUsuario,
                    onVerDetalle    = { rutina ->
                        rutinaViewModel.seleccionarRutina(rutina)
                        navController.navigate(Destino.DetalleRutina.ruta)
                    }
                )
            }
            composable(Destino.DetalleRutina.ruta) {
                val rutina by rutinaViewModel.rutinaSeleccionada.collectAsState()
                rutina?.let {
                    DetalleRutinaScreen(
                        rutinaViewModel = rutinaViewModel,
                        historialViewModel = historialViewModel,
                        emailUsuario = emailUsuario,
                        rutina = it,
                        onVolver = { navController.popBackStack() }
                    )
                }
            }


            // ── DIETAS ─────────────────────────────────────────────────────
            composable(Destino.Dietas.ruta) {
                DietasScreen(
                    dietaViewModel = dietaViewModel,
                    emailUsuario   = emailUsuario,
                    onVerDetalle   = { dieta ->
                        dietaViewModel.seleccionarDieta(dieta)
                        navController.navigate(Destino.DetalleDieta.ruta)
                    }
                )
            }
            composable(Destino.DetalleDieta.ruta) {
                val dieta by dietaViewModel.dietaSeleccionada.collectAsState()
                dieta?.let {
                    DetalleDietaScreen(
                        dietaViewModel     = dietaViewModel,
                        historialViewModel = historialViewModel,
                        emailUsuario       = emailUsuario,
                        dieta              = it,
                        onVolver           = { navController.popBackStack() }
                    )
                }
            }


            // ── PERFIL ─────────────────────────────────────────────────────
            composable(Destino.Perfil.ruta) {
                PerfilScreen(
                    usuarioViewModel = usuarioViewModel,
                    rutinaViewModel  = rutinaViewModel,
                    dietaViewModel   = dietaViewModel,
                    saludViewModel   = saludViewModel,
                    emailUsuario     = emailUsuario,
                    onIrAAjustes     = { navController.navigate(Destino.Ajustes.ruta) }, // <-- AÑADE ESTA LÍNEA
                    onCerrarSesion   = {
                        onCerrarSesion()
                        navController.navigate(Destino.Login.ruta) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

        }
    }
}
