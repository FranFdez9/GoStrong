package com.example.gostrong.ui.Navegacion

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BarraNavegacionInferior(navController: NavController) {

    val rutaActual = navController.currentBackStackEntryAsState().value?.destination?.route

    val items = listOf(
        Destino.Home,
        Destino.Rutinas,
        Destino.Dietas,
        Destino.Salud,
        Destino.Perfil,
    )

    NavigationBar {
        items.forEach { destino ->
            NavigationBarItem(
                selected = rutaActual == destino.ruta,
                onClick = {
                    navController.navigate(destino.ruta) {
                        popUpTo(Destino.Home.ruta)
                        launchSingleTop = true
                    }
                },
                icon = {
                    destino.icono?.let {
                        Icon(imageVector = it, contentDescription = destino.tituloRes?.let { resId -> stringResource(resId) } ?: "")
                    }
                },
                label = { 
                    destino.tituloRes?.let { resId -> 
                        Text(stringResource(id = resId)) 
                    }
                }
            )
        }
    }
}