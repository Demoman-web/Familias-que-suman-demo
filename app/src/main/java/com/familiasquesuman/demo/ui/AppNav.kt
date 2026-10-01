package com.familiasquesuman.demo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.familiasquesuman.demo.data.AppContainer
import com.familiasquesuman.demo.ui.screens.ActividadesScreen
import com.familiasquesuman.demo.ui.screens.AvisoPrivacidadScreen
import com.familiasquesuman.demo.ui.screens.CiudadScreen
import com.familiasquesuman.demo.ui.screens.ConfirmacionScreen
import com.familiasquesuman.demo.ui.screens.DetalleActividadScreen
import com.familiasquesuman.demo.ui.screens.DetalleCentroScreen
import com.familiasquesuman.demo.ui.screens.DetalleProyectoScreen
import com.familiasquesuman.demo.ui.screens.DirectorioScreen
import com.familiasquesuman.demo.ui.screens.DonarScreen
import com.familiasquesuman.demo.ui.screens.GaleriaScreen
import com.familiasquesuman.demo.ui.screens.HomeAcciones
import com.familiasquesuman.demo.ui.screens.HomeScreen
import com.familiasquesuman.demo.ui.screens.InscripcionScreen
import com.familiasquesuman.demo.ui.screens.LoginScreen
import com.familiasquesuman.demo.ui.screens.MisInscripcionesScreen
import com.familiasquesuman.demo.ui.screens.NotificacionesScreen
import com.familiasquesuman.demo.ui.screens.PerfilScreen
import com.familiasquesuman.demo.ui.screens.ProyectosScreen
import com.familiasquesuman.demo.ui.screens.SplashScreen
import com.familiasquesuman.demo.ui.viewmodel.ActividadesViewModel
import com.familiasquesuman.demo.ui.viewmodel.AvisoPrivacidadViewModel
import com.familiasquesuman.demo.ui.viewmodel.ConfirmacionViewModel
import com.familiasquesuman.demo.ui.viewmodel.DetalleActividadViewModel
import com.familiasquesuman.demo.ui.viewmodel.DetalleCentroViewModel
import com.familiasquesuman.demo.ui.viewmodel.DetalleProyectoViewModel
import com.familiasquesuman.demo.ui.viewmodel.DirectorioViewModel
import com.familiasquesuman.demo.ui.viewmodel.DonarViewModel
import com.familiasquesuman.demo.ui.viewmodel.GaleriaViewModel
import com.familiasquesuman.demo.ui.viewmodel.HomeViewModel
import com.familiasquesuman.demo.ui.viewmodel.InscripcionViewModel
import com.familiasquesuman.demo.ui.viewmodel.MisInscripcionesViewModel
import com.familiasquesuman.demo.ui.viewmodel.NotificacionesViewModel
import com.familiasquesuman.demo.ui.viewmodel.PerfilViewModel
import com.familiasquesuman.demo.ui.viewmodel.ProyectosViewModel
import com.familiasquesuman.demo.ui.viewmodel.SesionViewModel
import com.familiasquesuman.demo.ui.viewmodel.fabrica

object Rutas {
    const val SPLASH = "splash"
    const val CIUDAD = "ciudad"
    const val LOGIN = "login"
    const val HOME = "home"
    const val ACTIVIDADES = "actividades"
    const val DETALLE_ACTIVIDAD = "actividad/{id}"
    const val INSCRIPCION = "inscripcion/{id}"
    const val CONFIRMACION = "confirmacion/{id}"
    const val MIS_INSCRIPCIONES = "mis_inscripciones"
    const val DIRECTORIO = "directorio"
    const val DETALLE_CENTRO = "centro/{id}"
    const val DONAR = "donar"
    const val PROYECTOS = "proyectos"
    const val DETALLE_PROYECTO = "proyecto/{id}"
    const val PERFIL = "perfil"
    const val GALERIA = "galeria"
    const val NOTIFICACIONES = "notificaciones"
    const val AVISO_PRIVACIDAD = "aviso_privacidad"

    fun actividad(id: Int) = "actividad/$id"
    fun inscripcion(id: Int) = "inscripcion/$id"
    fun confirmacion(id: Int) = "confirmacion/$id"
    fun centro(id: Int) = "centro/$id"
    fun proyecto(id: Int) = "proyecto/$id"
}

private data class Tab(val ruta: String, val etiqueta: String, val icono: ImageVector)

private val tabs = listOf(
    Tab(Rutas.HOME, "Inicio", Icons.Default.Home),
    Tab(Rutas.MIS_INSCRIPCIONES, "Actividades", Icons.Default.EventAvailable),
    Tab(Rutas.DONAR, "Donar", Icons.Default.Favorite),
    Tab(Rutas.PERFIL, "Perfil", Icons.Default.Person),
)

private val sinBarra = setOf(Rutas.SPLASH, Rutas.CIUDAD, Rutas.LOGIN, Rutas.AVISO_PRIVACIDAD)

private val argId = listOf(navArgument("id") { type = NavType.IntType })
private fun NavBackStackEntry.id(): Int = arguments!!.getInt("id")

@Composable
fun AppNav() {
    val nav = rememberNavController()
    val backStack by nav.currentBackStackEntryAsState()
    val rutaActual = backStack?.destination?.route
    val repo = AppContainer.repositorio

    Scaffold(
        bottomBar = {
            if (rutaActual != null && rutaActual !in sinBarra) {
                BarraInferior(nav, rutaActual)
            }
        }
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = Rutas.SPLASH,
            modifier = Modifier.padding(bottom = padding.calculateBottomPadding())
        ) {

            composable(Rutas.SPLASH) {
                SplashScreen { nav.navigate(Rutas.CIUDAD) { popUpTo(Rutas.SPLASH) { inclusive = true } } }
            }
            composable(Rutas.CIUDAD) {
                val vm: SesionViewModel = viewModel(factory = fabrica { SesionViewModel(repo) })
                CiudadScreen(vm) { nav.navigate(Rutas.LOGIN) }
            }
            composable(Rutas.LOGIN) {
                val vm: SesionViewModel = viewModel(factory = fabrica { SesionViewModel(repo) })
                LoginScreen(
                    vm = vm,
                    onEntrar = { nav.navigate(Rutas.HOME) { popUpTo(Rutas.CIUDAD) { inclusive = true } } },
                    onAviso = { nav.navigate(Rutas.AVISO_PRIVACIDAD) }
                )
            }

            composable(Rutas.HOME) {
                val vm: HomeViewModel = viewModel(factory = fabrica { HomeViewModel(repo) })
                HomeScreen(
                    vm = vm,
                    acciones = HomeAcciones(
                        onActividades = { nav.navigate(Rutas.ACTIVIDADES) },
                        onDonar = { nav.navigate(Rutas.DONAR) },
                        onProyectos = { nav.navigate(Rutas.PROYECTOS) },
                        onDirectorio = { nav.navigate(Rutas.DIRECTORIO) },
                        onNotificaciones = { nav.navigate(Rutas.NOTIFICACIONES) },
                        onCambiarCiudad = { nav.navigate(Rutas.CIUDAD) },
                        onActividad = { nav.navigate(Rutas.actividad(it)) }
                    )
                )
            }

            composable(Rutas.ACTIVIDADES) {
                val vm: ActividadesViewModel = viewModel(factory = fabrica { ActividadesViewModel(repo) })
                ActividadesScreen(vm, onBack = { nav.popBackStack() }, onActividad = { nav.navigate(Rutas.actividad(it)) })
            }
            composable(Rutas.DETALLE_ACTIVIDAD, arguments = argId) { entrada ->
                val id = entrada.id()
                val vm: DetalleActividadViewModel = viewModel(factory = fabrica { DetalleActividadViewModel(repo, id) })
                DetalleActividadScreen(
                    vm = vm,
                    onBack = { nav.popBackStack() },
                    onInscribir = { nav.navigate(Rutas.inscripcion(id)) },
                    onCentro = { nav.navigate(Rutas.centro(it)) }
                )
            }
            composable(Rutas.INSCRIPCION, arguments = argId) { entrada ->
                val id = entrada.id()
                val vm: InscripcionViewModel = viewModel(factory = fabrica { InscripcionViewModel(repo, id) })
                InscripcionScreen(
                    vm = vm,
                    onBack = { nav.popBackStack() },
                    onConfirmado = {

                        nav.navigate(Rutas.confirmacion(id)) {
                            popUpTo(Rutas.DETALLE_ACTIVIDAD) { inclusive = false }
                        }
                    }
                )
            }
            composable(Rutas.CONFIRMACION, arguments = argId) { entrada ->
                val id = entrada.id()
                val vm: ConfirmacionViewModel = viewModel(factory = fabrica { ConfirmacionViewModel(repo, id) })
                ConfirmacionScreen(
                    actividad = vm.actividad,
                    nombreCentro = vm.nombreCentro,
                    onVerInscripciones = {
                        nav.navigate(Rutas.MIS_INSCRIPCIONES) { popUpTo(Rutas.HOME) }
                    },
                    onInicio = { nav.navigate(Rutas.HOME) { popUpTo(Rutas.HOME) { inclusive = true } } }
                )
            }
            composable(Rutas.MIS_INSCRIPCIONES) {
                val vm: MisInscripcionesViewModel = viewModel(factory = fabrica { MisInscripcionesViewModel(repo) })
                MisInscripcionesScreen(vm, onActividad = { nav.navigate(Rutas.actividad(it)) })
            }

            composable(Rutas.DIRECTORIO) {
                val vm: DirectorioViewModel = viewModel(factory = fabrica { DirectorioViewModel(repo) })
                DirectorioScreen(vm, onBack = { nav.popBackStack() }, onCentro = { nav.navigate(Rutas.centro(it)) })
            }
            composable(Rutas.DETALLE_CENTRO, arguments = argId) { entrada ->
                val id = entrada.id()
                val vm: DetalleCentroViewModel = viewModel(factory = fabrica { DetalleCentroViewModel(repo, id) })
                DetalleCentroScreen(
                    vm = vm,
                    onBack = { nav.popBackStack() },
                    onActividad = { nav.navigate(Rutas.actividad(it)) },
                    onDonar = { nav.navigate(Rutas.DONAR) }
                )
            }

            composable(Rutas.DONAR) {
                val vm: DonarViewModel = viewModel(factory = fabrica { DonarViewModel(repo) })
                DonarScreen(
                    vm = vm,
                    onInicio = { nav.popBackStack(Rutas.HOME, inclusive = false) },
                    onCentro = { nav.navigate(Rutas.centro(it)) }
                )
            }

            composable(Rutas.PROYECTOS) {
                val vm: ProyectosViewModel = viewModel(factory = fabrica { ProyectosViewModel(repo) })
                ProyectosScreen(vm, onBack = { nav.popBackStack() }, onProyecto = { nav.navigate(Rutas.proyecto(it)) })
            }
            composable(Rutas.DETALLE_PROYECTO, arguments = argId) { entrada ->
                val id = entrada.id()
                val vm: DetalleProyectoViewModel = viewModel(factory = fabrica { DetalleProyectoViewModel(repo, id) })
                DetalleProyectoScreen(vm, onBack = { nav.popBackStack() }, onDonar = { nav.navigate(Rutas.DONAR) })
            }

            composable(Rutas.PERFIL) {
                val vm: PerfilViewModel = viewModel(factory = fabrica { PerfilViewModel(repo) })
                PerfilScreen(
                    vm = vm,
                    onGaleria = { nav.navigate(Rutas.GALERIA) },
                    onNotificaciones = { nav.navigate(Rutas.NOTIFICACIONES) },
                    onAviso = { nav.navigate(Rutas.AVISO_PRIVACIDAD) },
                    onCerrarSesion = {
                        nav.navigate(Rutas.LOGIN) { popUpTo(0) { inclusive = true } }
                    }
                )
            }
            composable(Rutas.GALERIA) {
                val vm: GaleriaViewModel = viewModel(factory = fabrica { GaleriaViewModel(repo) })
                GaleriaScreen(vm, onBack = { nav.popBackStack() })
            }
            composable(Rutas.AVISO_PRIVACIDAD) {
                val vm: AvisoPrivacidadViewModel = viewModel(factory = fabrica { AvisoPrivacidadViewModel(repo) })
                AvisoPrivacidadScreen(
                    vm = vm,
                    onBack = { nav.popBackStack() },
                    onCuentaCerrada = { nav.navigate(Rutas.LOGIN) { popUpTo(0) { inclusive = true } } }
                )
            }
            composable(Rutas.NOTIFICACIONES) {
                val vm: NotificacionesViewModel = viewModel(factory = fabrica { NotificacionesViewModel(repo) })
                NotificacionesScreen(vm, onBack = { nav.popBackStack() })
            }
        }
    }
}

@Composable
private fun BarraInferior(nav: NavHostController, rutaActual: String) {
    NavigationBar {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = rutaActual == tab.ruta,
                onClick = {
                    nav.navigate(tab.ruta) {

                        popUpTo(Rutas.HOME) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(tab.icono, contentDescription = tab.etiqueta) },
                label = { Text(tab.etiqueta) }
            )
        }
    }
}
