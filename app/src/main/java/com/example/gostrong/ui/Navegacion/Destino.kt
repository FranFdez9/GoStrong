package com.example.gostrong.ui.Navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gostrong.R

/*
 * CLASE DESTINO
 * Esta clase "sellada" (sealed class) es el núcleo de nuestro mapa de navegación.
 * Nos sirve para crear una especie de "plantilla" que dice qué pantallas existen
 * en la app y qué recursos (como un título y un icono) necesitan mostrar.
 */
sealed class Destino(
    val ruta: String,               // El identificador interno (ej. "home")
    val tituloRes: Int? = null,     // Referencia de texto en strings.xml (R.string...)
    val icono: ImageVector? = null  // El icono visual para la barra (puede ser nulo si no lleva)
) {
    // -------------------------------------------------------------------------
    // PANTALLAS DE PANTALLA COMPLETA (SIN MENÚ BOTTOM)
    // Pantallas sueltas o submenús que no tienen icono visible abajo.
    // -------------------------------------------------------------------------
    object Login         : Destino(ruta = "login")
    object Registro      : Destino(ruta = "registro")
    object DetalleRutina : Destino(ruta = "detalle_rutina")
    object DetalleDieta  : Destino(ruta = "detalle_dieta")

    // -------------------------------------------------------------------------
    // PANTALLAS DEL MENÚ INFERIOR (CON BOTTOM NAV)
    // -------------------------------------------------------------------------
    object Home    : Destino(ruta = "home",    tituloRes = R.string.nav_inicio,  icono = Icons.Default.Home)

    // MEJORA: Usamos 'List' (lista) en lugar de 'Share' para representar planes o rutinas planificadas.
    object Rutinas : Destino(ruta = "rutinas", tituloRes = R.string.nav_rutinas, icono = Icons.Default.List)

    // MEJORA: Usamos 'ShoppingCart' (cesta) en lugar de 'Edit' para representar la comida.
    object Dietas  : Destino(ruta = "dietas",  tituloRes = R.string.nav_dietas,  icono = Icons.Default.ShoppingCart)

    object Salud   : Destino(ruta = "salud",   tituloRes = R.string.nav_salud,   icono = Icons.Default.Favorite)
    object Perfil  : Destino(ruta = "perfil",  tituloRes = R.string.nav_perfil,  icono = Icons.Default.Person)

    // NOTA DE DISEÑO: Mantenemos el objeto 'Ajustes' porque la pantalla sigue existiendo en el flujo,
    // pero ya NO forma parte de la barra inferior.
    object Ajustes : Destino(ruta = "ajustes", tituloRes = R.string.nav_ajustes, icono = Icons.Default.Settings)

    // Agrupamos en una lista los elementos que Android debe obligatoriamente dibujar abajo.
    companion object {
        // MEJORA: Hemos eliminado "Ajustes" de la lista inferior. Ahora muestra 5 botones perfectos y simétricos.
        val itemsBottomNav = listOf(Home, Rutinas, Dietas, Salud, Perfil)
    }
}
