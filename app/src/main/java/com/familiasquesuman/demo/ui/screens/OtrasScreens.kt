@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.familiasquesuman.demo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessible
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Soap
import androidx.compose.material.icons.filled.Toys
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.familiasquesuman.demo.data.AvisoPrivacidad
import com.familiasquesuman.demo.model.Categoria
import com.familiasquesuman.demo.model.CategoriaDonacion
import com.familiasquesuman.demo.model.Centro
import com.familiasquesuman.demo.model.Notificacion
import com.familiasquesuman.demo.ui.UiState
import com.familiasquesuman.demo.ui.comoLlegar
import com.familiasquesuman.demo.ui.correo
import com.familiasquesuman.demo.ui.llamar
import com.familiasquesuman.demo.ui.whatsapp
import com.familiasquesuman.demo.ui.components.BarraSuperior
import com.familiasquesuman.demo.ui.components.Chip
import com.familiasquesuman.demo.ui.components.ContenedorEstado
import com.familiasquesuman.demo.ui.components.ImagenPlaceholder
import com.familiasquesuman.demo.ui.components.LogoCentro
import com.familiasquesuman.demo.ui.components.Seccion
import com.familiasquesuman.demo.ui.components.SelloVerificado
import com.familiasquesuman.demo.ui.components.TarjetaActividad
import com.familiasquesuman.demo.ui.components.TarjetaCentro
import com.familiasquesuman.demo.ui.components.color
import com.familiasquesuman.demo.ui.components.icono
import com.familiasquesuman.demo.ui.theme.Amarillo
import com.familiasquesuman.demo.ui.theme.Azul
import com.familiasquesuman.demo.ui.theme.AzulBoton
import com.familiasquesuman.demo.ui.theme.AzulOscuro
import com.familiasquesuman.demo.ui.theme.Verde
import com.familiasquesuman.demo.ui.theme.AzulClaro
import com.familiasquesuman.demo.ui.viewmodel.DetalleCentroUiState
import com.familiasquesuman.demo.ui.viewmodel.DetalleCentroViewModel
import com.familiasquesuman.demo.ui.viewmodel.DetalleProyectoViewModel
import com.familiasquesuman.demo.ui.viewmodel.DirectorioViewModel
import com.familiasquesuman.demo.ui.viewmodel.DonarViewModel
import com.familiasquesuman.demo.ui.viewmodel.AvisoPrivacidadViewModel
import com.familiasquesuman.demo.ui.viewmodel.CampanaItem
import com.familiasquesuman.demo.ui.viewmodel.ModoDonar
import com.familiasquesuman.demo.ui.viewmodel.GaleriaViewModel
import com.familiasquesuman.demo.ui.viewmodel.NotificacionesViewModel
import com.familiasquesuman.demo.ui.viewmodel.PerfilUiState
import com.familiasquesuman.demo.ui.viewmodel.PerfilViewModel
import com.familiasquesuman.demo.ui.viewmodel.ProyectoItem
import com.familiasquesuman.demo.ui.viewmodel.ProyectosViewModel

@Composable
fun DirectorioScreen(vm: DirectorioViewModel, onBack: () -> Unit, onCentro: (Int) -> Unit) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    val categoria by vm.categoria.collectAsStateWithLifecycle()
    DirectorioContenido(
        estado = estado,
        categoria = categoria,
        onCategoria = vm::cambiarCategoria,
        onReintentar = { vm.cargar() },
        onBack = onBack,
        onCentro = onCentro
    )
}

@Composable
fun DirectorioContenido(
    estado: UiState<List<Centro>>,
    categoria: Categoria,
    onCategoria: (Categoria) -> Unit,
    onReintentar: () -> Unit,
    onBack: () -> Unit,
    onCentro: (Int) -> Unit
) {
    Scaffold(topBar = { BarraSuperior("Directorio de visiteo", onBack) }) { padding ->
        Column(Modifier.padding(padding)) {
            Text(
                "Centros y espacios verificados para visitar y ayudar en familia.",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
            FilaCategorias(seleccionada = categoria, onSeleccionar = onCategoria)
            ContenedorEstado(estado = estado, onReintentar = onReintentar, mensajeVacio = "No hay centros en esa categoría.") { lista ->
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(lista, key = { it.id }) { c -> TarjetaCentro(c) { onCentro(c.id) } }
                }
            }
        }
    }
}

@Composable
fun DetalleCentroScreen(
    vm: DetalleCentroViewModel,
    onBack: () -> Unit,
    onActividad: (Int) -> Unit,
    onDonar: () -> Unit
) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    DetalleCentroContenido(estado, { vm.cargar() }, onBack, onActividad, onDonar)
}

@Composable
fun DetalleCentroContenido(
    estado: UiState<DetalleCentroUiState>,
    onReintentar: () -> Unit,
    onBack: () -> Unit,
    onActividad: (Int) -> Unit,
    onDonar: () -> Unit
) {
    Scaffold(topBar = { BarraSuperior("Centro", onBack) }) { padding ->
        ContenedorEstado(estado = estado, onReintentar = onReintentar, modifier = Modifier.padding(padding)) { datos ->
            val centro = datos.centro
            LazyColumn(Modifier.padding(padding), contentPadding = PaddingValues(bottom = 24.dp)) {
                item { ImagenPlaceholder(centro.categoria, alto = 180, imagen = centro.imagen) }
                item {
                    Column(Modifier.padding(16.dp)) {
                        Chip(centro.categoria.etiqueta)
                        Spacer(Modifier.height(8.dp))
                        Text(centro.nombre, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        if (centro.verificado) SelloVerificado()
                        Spacer(Modifier.height(12.dp))
                        FilaInfo(Icons.Default.Place, centro.direccion)
                        FilaInfo(Icons.Default.Schedule, "Visitas: ${centro.horarioVisitas}")

                        Seccion("Sobre el centro")
                        Text(centro.descripcion)

                        Seccion("¿Qué necesitan?")
                        centro.queNecesitan.forEach {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 2.dp)) {
                                Icon(Icons.Default.Check, null, tint = Verde, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(8.dp))
                                Text(it)
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        OutlinedButton(onClick = onDonar, modifier = Modifier.fillMaxWidth()) { Text("Quiero donar a este centro") }

                        if (datos.actividades.isNotEmpty()) Seccion("Actividades de este centro")
                    }
                }
                items(datos.actividades, key = { it.actividad.id }) { item ->
                    Box(Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                        TarjetaActividad(item.actividad, item.nombreCentro) { onActividad(item.actividad.id) }
                    }
                }
            }
        }
    }
}

@Composable
fun DonarScreen(vm: DonarViewModel, onInicio: () -> Unit, onCentro: (Int) -> Unit) {
    val modo by vm.modo.collectAsStateWithLifecycle()
    val categoria by vm.categoria.collectAsStateWithLifecycle()
    val campanas by vm.campanas.collectAsStateWithLifecycle()
    val centros by vm.centros.collectAsStateWithLifecycle()
    val enviandoId by vm.enviandoId.collectAsStateWithLifecycle()
    val mensaje by vm.mensaje.collectAsStateWithLifecycle()

    val snackbar = remember { SnackbarHostState() }
    LaunchedEffect(mensaje) {
        val m = mensaje ?: return@LaunchedEffect
        snackbar.showSnackbar(m)
        vm.mensajeMostrado()
    }

    DonarContenido(
        modo = modo,
        categoria = categoria,
        campanas = campanas,
        centros = centros,
        enviandoId = enviandoId,
        snackbar = snackbar,
        onModo = vm::cambiarModo,
        onCategoria = vm::cambiarCategoria,
        onAyudar = vm::ayudar,
        onReintentar = { vm.cargar() },
        onInicio = onInicio,
        onCentro = onCentro
    )
}

@Composable
fun DonarContenido(
    modo: ModoDonar,
    categoria: CategoriaDonacion,
    campanas: UiState<List<CampanaItem>>,
    centros: UiState<List<Centro>>,
    enviandoId: Int?,
    snackbar: SnackbarHostState,
    onModo: (ModoDonar) -> Unit,
    onCategoria: (CategoriaDonacion) -> Unit,
    onAyudar: (Int) -> Unit,
    onReintentar: () -> Unit,
    onInicio: () -> Unit,
    onCentro: (Int) -> Unit
) {
    Scaffold(
        topBar = { BarraSuperior("Quiero donar", onInicio) },
        snackbarHost = { SnackbarHost(snackbar) }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            Text(
                "Tu generosidad hace la diferencia en la vida de muchos.",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(12.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BotonModo(
                    texto = "Donar a campaña",
                    icono = Icons.Default.Campaign,
                    activo = modo == ModoDonar.CAMPANA,
                    colorActivo = Amarillo,
                    textoActivo = Color(0xFF3A2E00),
                    modifier = Modifier.weight(1f)
                ) { onModo(ModoDonar.CAMPANA) }
                BotonModo(
                    texto = "Tengo algo para donar",
                    icono = Icons.Default.VolunteerActivism,
                    activo = modo == ModoDonar.ESPECIE,
                    colorActivo = AzulOscuro,
                    textoActivo = Color.White,
                    modifier = Modifier.weight(1f)
                ) { onModo(ModoDonar.ESPECIE) }
            }
            Spacer(Modifier.height(8.dp))

            when (modo) {
                ModoDonar.CAMPANA -> ListaCampanas(campanas, enviandoId, onAyudar, onReintentar, onCentro)
                ModoDonar.ESPECIE -> ListaCentrosDonacion(centros, categoria, onCategoria, onReintentar, onCentro)
            }
        }
    }
}

@Composable
private fun BotonModo(
    texto: String,
    icono: ImageVector,
    activo: Boolean,
    colorActivo: Color,
    textoActivo: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    if (activo) {
        Button(
            onClick = onClick,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(containerColor = colorActivo, contentColor = textoActivo),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Icon(icono, null, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Text(texto, style = MaterialTheme.typography.labelLarge, maxLines = 1)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Icon(icono, null, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Text(texto, style = MaterialTheme.typography.labelLarge, maxLines = 1)
        }
    }
}

@Composable
private fun ListaCampanas(
    estado: UiState<List<CampanaItem>>,
    enviandoId: Int?,
    onAyudar: (Int) -> Unit,
    onReintentar: () -> Unit,
    onCentro: (Int) -> Unit
) {
    ContenedorEstado(estado = estado, onReintentar = onReintentar, mensajeVacio = "No hay campañas activas en tu ciudad.") { lista ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(lista, key = { it.campana.id }) { item ->
                val c = item.campana
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column {
                        ImagenPlaceholder(item.centro.categoria, alto = 120, imagen = c.imagen ?: item.centro.imagen)
                        Column(Modifier.padding(14.dp)) {
                            Chip("Campaña", fondo = Color(0xFFFFF1C7), textoColor = Color(0xFF7A5A00))
                            Spacer(Modifier.height(6.dp))
                            Text(c.titulo, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text(item.centro.nombre, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(Modifier.height(6.dp))
                            Text(c.descripcion, style = MaterialTheme.typography.bodyMedium)
                            if (c.hasta != null) {
                                Spacer(Modifier.height(6.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Schedule, null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                    Spacer(Modifier.width(4.dp))
                                    Text("Hasta: ${c.hasta}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                            Spacer(Modifier.height(12.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(
                                    onClick = { onAyudar(c.id) },
                                    enabled = !item.yaApoyada && enviandoId == null,
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(containerColor = Verde)
                                ) {
                                    when {
                                        enviandoId == c.id -> CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp, color = Color.White)
                                        item.yaApoyada -> {
                                            Icon(Icons.Default.Check, null, modifier = Modifier.size(16.dp))
                                            Spacer(Modifier.width(6.dp))
                                            Text("Apoyo registrado")
                                        }
                                        else -> Text("Quiero ayudar")
                                    }
                                }
                                OutlinedButton(onClick = { onCentro(c.centroId) }) { Text("Ver más") }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ListaCentrosDonacion(
    estado: UiState<List<Centro>>,
    categoria: CategoriaDonacion,
    onCategoria: (CategoriaDonacion) -> Unit,
    onReintentar: () -> Unit,
    onCentro: (Int) -> Unit
) {
    val context = LocalContext.current
    Column {
        Text(
            "¿Qué quieres donar?",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(CategoriaDonacion.entries) { cat ->
                FilterChip(
                    selected = categoria == cat,
                    onClick = { onCategoria(cat) },
                    label = { Text(cat.etiqueta) },
                    leadingIcon = { Icon(cat.icono(), null, modifier = Modifier.size(16.dp)) }
                )
            }
        }
        ContenedorEstado(estado = estado, onReintentar = onReintentar, mensajeVacio = "Ningún centro recibe eso por ahora.") { lista ->
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(lista, key = { it.id }) { c ->
                    Card(
                        onClick = { onCentro(c.id) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.Top) {
                                LogoCentro(c.imagen, c.categoria, tamano = 52)
                                Spacer(Modifier.width(12.dp))
                                Column(Modifier.weight(1f)) {
                                    if (c.verificado) SelloVerificado()
                                    Text(c.nombre, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    Text(c.tipo, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(c.descripcion, style = MaterialTheme.typography.bodyMedium, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            Spacer(Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Place, null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                Spacer(Modifier.width(4.dp))
                                Text(c.direccion, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Spacer(Modifier.height(8.dp))
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                items(c.acepta) { cat ->
                                    Chip(cat.etiqueta, fondo = Color(0xFFEEF1F5), textoColor = AzulOscuro)
                                }
                            }
                            Spacer(Modifier.height(12.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                if (c.whatsapp != null) {
                                    Button(
                                        onClick = { context.whatsapp(c.whatsapp) },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(containerColor = Verde),
                                        contentPadding = PaddingValues(horizontal = 6.dp)
                                    ) {
                                        Icon(Icons.Default.Chat, null, modifier = Modifier.size(16.dp))
                                        Spacer(Modifier.width(4.dp))
                                        Text("WhatsApp", maxLines = 1)
                                    }
                                }
                                if (c.telefono != null) {
                                    OutlinedButton(
                                        onClick = { context.llamar(c.telefono) },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Verde),
                                        contentPadding = PaddingValues(horizontal = 6.dp)
                                    ) {
                                        Icon(Icons.Default.Call, null, modifier = Modifier.size(16.dp))
                                        Spacer(Modifier.width(4.dp))
                                        Text("Llamar", maxLines = 1)
                                    }
                                }
                                Button(
                                    onClick = { context.comoLlegar(c.direccion) },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(containerColor = AzulBoton),
                                    contentPadding = PaddingValues(horizontal = 6.dp)
                                ) {
                                    Icon(Icons.Default.NearMe, null, modifier = Modifier.size(16.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text("Cómo llegar", maxLines = 1)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun CategoriaDonacion.icono(): ImageVector = when (this) {
    CategoriaDonacion.TODOS -> Icons.Default.Inventory2
    CategoriaDonacion.JUGUETES -> Icons.Default.Toys
    CategoriaDonacion.ROPA -> Icons.Default.Checkroom
    CategoriaDonacion.ALIMENTOS -> Icons.Default.Restaurant
    CategoriaDonacion.HIGIENE -> Icons.Default.Soap
    CategoriaDonacion.SILLAS_RUEDAS -> Icons.Default.Accessible
    CategoriaDonacion.UTILES -> Icons.Default.School
    CategoriaDonacion.COBIJAS -> Icons.Default.Bed
    CategoriaDonacion.CROQUETAS -> Icons.Default.Pets
}

@Composable
fun AvisoPrivacidadScreen(vm: AvisoPrivacidadViewModel, onBack: () -> Unit, onCuentaCerrada: () -> Unit) {
    val context = LocalContext.current
    val correoContacto = "hola@familiasquesuman.com"
    val familia = vm.familia
    var confirmarCierre by rememberSaveable { mutableStateOf(false) }

    if (confirmarCierre) {
        AlertDialog(
            onDismissRequest = { confirmarCierre = false },
            icon = { Icon(Icons.Default.DeleteForever, null, tint = MaterialTheme.colorScheme.error) },
            title = { Text("¿Cerrar la cuenta de ${familia.nombre}?") },
            text = {
                Text(
                    "Se cerrará tu sesión y se enviará a Familias que Suman la solicitud para eliminar " +
                        "todos los datos de tu familia, incluidos los de tus hijos. Esta acción no se puede deshacer."
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    confirmarCierre = false
                    context.correo(
                        destinatario = correoContacto,
                        asunto = "Solicitud de eliminación de cuenta y datos",
                        cuerpo = "Solicito la cancelación de la cuenta de ${familia.nombre} (${familia.correo}) " +
                            "y la eliminación de todos los datos personales asociados, conforme al Aviso de Privacidad."
                    )
                    vm.cerrarCuenta()
                    onCuentaCerrada()
                }) { Text("Cerrar cuenta", color = MaterialTheme.colorScheme.error) }
            },
            dismissButton = { TextButton(onClick = { confirmarCierre = false }) { Text("Cancelar") } }
        )
    }

    AvisoPrivacidadContenido(
        onBack = onBack,
        onSolicitarDatos = {
            context.correo(
                destinatario = correoContacto,
                asunto = "Solicitud de derechos ARCO",
                cuerpo = "Hola, soy ${familia.nombre} (${familia.correo}). Quiero ejercer mi derecho de: " +
                    "[Acceso / Rectificación / Cancelación / Oposición]. Descripción: "
            )
        },
        onCerrarCuenta = { confirmarCierre = true }
    )
}

@Composable
fun AvisoPrivacidadContenido(onBack: () -> Unit, onSolicitarDatos: () -> Unit, onCerrarCuenta: () -> Unit) {
    Scaffold(topBar = { BarraSuperior("Aviso de privacidad", onBack) }) { padding ->
        LazyColumn(
            Modifier.padding(padding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text("Aviso de Privacidad Integral", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(AvisoPrivacidad.ACTUALIZACION, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            items(AvisoPrivacidad.secciones) { seccion ->
                Column {
                    if (seccion.titulo != null) {
                        Spacer(Modifier.height(8.dp))
                        Text(seccion.titulo, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Azul)
                        Spacer(Modifier.height(4.dp))
                    }
                    seccion.parrafos.forEach { parrafo ->
                        Text(parrafo, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp))
                    }
                }
            }
            item {
                Divider(Modifier.padding(vertical = 8.dp))
                Text("Tus datos, tu decisión", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Azul)
                Spacer(Modifier.height(4.dp))
                Text(
                    "Puedes pedir en cualquier momento ver, corregir o borrar lo que guardamos de tu familia.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(12.dp))
                OutlinedButton(onClick = onSolicitarDatos, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.Mail, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Solicitar acceso, corrección o eliminación de datos")
                }
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = onCerrarCuenta,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(Icons.Default.DeleteForever, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Cerrar mi cuenta y eliminar mis datos")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun ProyectosScreen(vm: ProyectosViewModel, onBack: () -> Unit, onProyecto: (Int) -> Unit) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    ProyectosContenido(estado, { vm.cargar() }, onBack, onProyecto)
}

@Composable
fun ProyectosContenido(
    estado: UiState<List<ProyectoItem>>,
    onReintentar: () -> Unit,
    onBack: () -> Unit,
    onProyecto: (Int) -> Unit
) {
    Scaffold(topBar = { BarraSuperior("Proyectos", onBack) }) { padding ->
        ContenedorEstado(estado = estado, onReintentar = onReintentar, modifier = Modifier.padding(padding)) { lista ->
            LazyColumn(
                Modifier.padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text("Proyectos con causas y objetivos específicos.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                items(lista, key = { it.proyecto.id }) { item ->
                    val p = item.proyecto
                    Card(
                        onClick = { onProyecto(p.id) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                LogoCentro(p.imagen ?: item.centro.imagen, item.centro.categoria)
                                Spacer(Modifier.width(12.dp))
                                Column(Modifier.weight(1f)) {
                                    Chip(p.causa, fondo = Color(0xFFFFF1C7), textoColor = Color(0xFF7A5A00))
                                    Spacer(Modifier.height(4.dp))
                                    Text(p.titulo, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    Text(item.centro.nombre, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                            Spacer(Modifier.height(10.dp))
                            BarraAvance(p.avance, alto = 8)
                            Spacer(Modifier.height(4.dp))
                            Text("${(p.avance * 100).toInt()} % · Meta: ${p.objetivo}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BarraAvance(avance: Float, alto: Int) {
    LinearProgressIndicator(
        progress = { avance },
        modifier = Modifier
            .fillMaxWidth()
            .height(alto.dp)
            .clip(RoundedCornerShape((alto / 2).dp)),
        color = Amarillo,
        trackColor = Color(0xFFEEEEEE)
    )
}

@Composable
fun DetalleProyectoScreen(vm: DetalleProyectoViewModel, onBack: () -> Unit, onDonar: () -> Unit) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    DetalleProyectoContenido(estado, { vm.cargar() }, onBack, onDonar)
}

@Composable
fun DetalleProyectoContenido(
    estado: UiState<ProyectoItem>,
    onReintentar: () -> Unit,
    onBack: () -> Unit,
    onDonar: () -> Unit
) {
    Scaffold(
        topBar = { BarraSuperior("Proyecto", onBack) },
        bottomBar = {
            if (estado is UiState.Datos) {
                Box(Modifier.padding(16.dp)) {
                    Button(onClick = onDonar, modifier = Modifier.fillMaxWidth()) { Text("Sumar a este proyecto") }
                }
            }
        }
    ) { padding ->
        ContenedorEstado(estado = estado, onReintentar = onReintentar, modifier = Modifier.padding(padding)) { item ->
            val p = item.proyecto
            Column(
                Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                ImagenPlaceholder(item.centro.categoria, alto = 180, imagen = p.imagen ?: item.centro.imagen)
                Column(Modifier.padding(16.dp)) {
                    Chip(p.causa, fondo = Color(0xFFFFF1C7), textoColor = Color(0xFF7A5A00))
                    Spacer(Modifier.height(8.dp))
                    Text(p.titulo, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text(item.centro.nombre, color = Azul, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(16.dp))
                    BarraAvance(p.avance, alto = 10)
                    Spacer(Modifier.height(6.dp))
                    Text("${(p.avance * 100).toInt()} % de la meta", fontWeight = FontWeight.SemiBold)
                    Seccion("Objetivo")
                    Text(p.objetivo)
                    Seccion("Descripción")
                    Text(p.descripcion)
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun PerfilScreen(
    vm: PerfilViewModel,
    onGaleria: () -> Unit,
    onNotificaciones: () -> Unit,
    onAviso: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    PerfilContenido(
        estado = estado,
        onSimularError = vm::cambiarSimularError,
        onGaleria = onGaleria,
        onNotificaciones = onNotificaciones,
        onAviso = onAviso,
        onCerrarSesion = {
            vm.cerrarSesion()
            onCerrarSesion()
        }
    )
}

@Composable
fun PerfilContenido(
    estado: PerfilUiState,
    onSimularError: (Boolean) -> Unit,
    onGaleria: () -> Unit,
    onNotificaciones: () -> Unit,
    onAviso: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val f = estado.familia
    Scaffold(topBar = { BarraSuperior("Mi familia") }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Amarillo),
                    contentAlignment = Alignment.Center
                ) {
                    Text(f.nombre.substringAfter(" ").first().toString(), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(f.nombre, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(f.correo, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(f.zona, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Estadistica("${estado.completadas}", "completadas", Modifier.weight(1f))
                Estadistica("${estado.proximas}", "próximas", Modifier.weight(1f))
                Estadistica("${estado.donaciones}", "donaciones", Modifier.weight(1f))
            }

            Seccion("Integrantes")
            f.integrantes.forEach {
                ListItem(
                    headlineContent = { Text(it.nombre) },
                    supportingContent = { Text("${it.edad} años") },
                    leadingContent = {
                        Box(
                            Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(AzulClaro),
                            contentAlignment = Alignment.Center
                        ) { Text(it.nombre.first().toString(), fontWeight = FontWeight.Bold, color = Azul) }
                    }
                )
            }
            TextButton(onClick = {}) { Text("+ Agregar integrante") }

            Seccion("Más")
            val subtituloGaleria: (@Composable () -> Unit)? =
                if (estado.fotosPendientes > 0) {
                    { Text("${estado.fotosPendientes} foto(s) pendientes de revisión") }
                } else null
            ListItem(
                headlineContent = { Text("Galería de mi familia") },
                supportingContent = subtituloGaleria,
                leadingContent = { Icon(Icons.Default.PhotoLibrary, null) },
                modifier = Modifier.clickable(onClick = onGaleria)
            )
            ListItem(
                headlineContent = { Text("Notificaciones") },
                leadingContent = { Icon(Icons.Default.Notifications, null) },
                modifier = Modifier.clickable(onClick = onNotificaciones)
            )
            ListItem(
                headlineContent = { Text("Aviso de privacidad") },
                leadingContent = { Icon(Icons.Default.PrivacyTip, null) },
                modifier = Modifier.clickable(onClick = onAviso)
            )

            Seccion("Modo demo")
            ListItem(
                headlineContent = { Text("Simular error de red") },
                supportingContent = { Text("Las siguientes cargas fallarán para mostrar el estado de error.") },
                leadingContent = { Icon(Icons.Default.BugReport, null) },
                trailingContent = { Switch(checked = estado.simularError, onCheckedChange = onSimularError) }
            )

            Divider(Modifier.padding(vertical = 8.dp))
            ListItem(
                headlineContent = { Text("Cerrar sesión", color = Color(0xFFB71C1C)) },
                leadingContent = { Icon(Icons.Default.Logout, null, tint = Color(0xFFB71C1C)) },
                modifier = Modifier.clickable(onClick = onCerrarSesion)
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun Estadistica(valor: String, etiqueta: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = AzulClaro)) {
        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(valor, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Azul)
            Text(etiqueta, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun GaleriaScreen(vm: GaleriaViewModel, onBack: () -> Unit) {
    val fotos by vm.fotosPendientes.collectAsStateWithLifecycle()
    GaleriaContenido(fotos = fotos, onTomarFoto = vm::tomarFoto, onBack = onBack)
}

@Composable
fun GaleriaContenido(fotos: Int, onTomarFoto: () -> Unit, onBack: () -> Unit) {
    Scaffold(topBar = { BarraSuperior("Galería de mi familia", onBack) }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                "Comparte fotos de sus actividades. El equipo las revisa antes de publicarlas en la sección 'Nuestras familias'.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = onTomarFoto, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.AddAPhoto, null)
                Spacer(Modifier.width(8.dp))
                Text("Tomar foto (demo)")
            }
            Spacer(Modifier.height(16.dp))
            if (fotos == 0) {
                Text("Aún no has subido fotos.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            } else {
                (1..fotos).chunked(3).forEach { fila ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(bottom = 8.dp)) {
                        fila.forEach { _ ->
                            Box(
                                Modifier
                                    .weight(1f)
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(AzulClaro),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(Icons.Default.PhotoLibrary, null, tint = Azul)
                                    Text("Pendiente", style = MaterialTheme.typography.labelSmall)
                                }
                            }
                        }
                        repeat(3 - fila.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
            }
        }
    }
}

@Composable
fun NotificacionesScreen(vm: NotificacionesViewModel, onBack: () -> Unit) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    NotificacionesContenido(estado, { vm.cargar() }, onBack)
}

@Composable
fun NotificacionesContenido(estado: UiState<List<Notificacion>>, onReintentar: () -> Unit, onBack: () -> Unit) {
    Scaffold(topBar = { BarraSuperior("Notificaciones", onBack) }) { padding ->
        ContenedorEstado(
            estado = estado,
            onReintentar = onReintentar,
            modifier = Modifier.padding(padding),
            mensajeVacio = "No tienes notificaciones."
        ) { lista ->
            LazyColumn(Modifier.padding(padding), contentPadding = PaddingValues(vertical = 8.dp)) {
                items(lista, key = { it.id }) { n ->
                    ListItem(
                        headlineContent = { Text(n.titulo, fontWeight = FontWeight.SemiBold) },
                        supportingContent = { Text(n.cuerpo) },
                        trailingContent = { Text(n.hace, style = MaterialTheme.typography.labelSmall) },
                        leadingContent = {
                            Box(
                                Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(Amarillo)
                            )
                        }
                    )
                    Divider()
                }
            }
        }
    }
}
