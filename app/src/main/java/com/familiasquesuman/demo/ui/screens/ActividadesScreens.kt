@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.familiasquesuman.demo.ui.screens

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.familiasquesuman.demo.model.Actividad
import com.familiasquesuman.demo.model.Categoria
import com.familiasquesuman.demo.model.Centro
import com.familiasquesuman.demo.ui.UiState
import com.familiasquesuman.demo.ui.components.BarraSuperior
import com.familiasquesuman.demo.ui.components.Chip
import com.familiasquesuman.demo.ui.components.ContenedorEstado
import com.familiasquesuman.demo.ui.components.ImagenPlaceholder
import com.familiasquesuman.demo.ui.components.Seccion
import com.familiasquesuman.demo.ui.components.SelloVerificado
import com.familiasquesuman.demo.ui.components.TarjetaActividad
import com.familiasquesuman.demo.ui.theme.Azul
import com.familiasquesuman.demo.ui.theme.Verde
import com.familiasquesuman.demo.ui.theme.VerdeClaro
import com.familiasquesuman.demo.ui.theme.AzulClaro
import com.familiasquesuman.demo.ui.viewmodel.ActividadItem
import com.familiasquesuman.demo.ui.viewmodel.ActividadesViewModel
import com.familiasquesuman.demo.ui.viewmodel.DetalleActividadUiState
import com.familiasquesuman.demo.ui.viewmodel.DetalleActividadViewModel
import com.familiasquesuman.demo.ui.viewmodel.FormularioInscripcion
import com.familiasquesuman.demo.ui.viewmodel.InscripcionUiState
import com.familiasquesuman.demo.ui.viewmodel.InscripcionViewModel
import com.familiasquesuman.demo.ui.viewmodel.MisInscripcionesUiState
import com.familiasquesuman.demo.ui.viewmodel.MisInscripcionesViewModel

@Composable
fun ActividadesScreen(vm: ActividadesViewModel, onBack: () -> Unit, onActividad: (Int) -> Unit) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    val categoria by vm.categoria.collectAsStateWithLifecycle()
    val busqueda by vm.busqueda.collectAsStateWithLifecycle()
    ActividadesContenido(
        estado = estado,
        categoria = categoria,
        busqueda = busqueda,
        onCategoria = vm::cambiarCategoria,
        onBusqueda = vm::cambiarBusqueda,
        onReintentar = { vm.cargar() },
        onBack = onBack,
        onActividad = onActividad
    )
}

@Composable
fun ActividadesContenido(
    estado: UiState<List<ActividadItem>>,
    categoria: Categoria,
    busqueda: String,
    onCategoria: (Categoria) -> Unit,
    onBusqueda: (String) -> Unit,
    onReintentar: () -> Unit,
    onBack: () -> Unit,
    onActividad: (Int) -> Unit
) {
    Scaffold(topBar = { BarraSuperior("Actividades en familia", onBack) }) { padding ->
        Column(Modifier.padding(padding)) {
            OutlinedTextField(
                value = busqueda,
                onValueChange = onBusqueda,
                placeholder = { Text("Buscar actividad") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            FilaCategorias(seleccionada = categoria, onSeleccionar = onCategoria)
            ContenedorEstado(
                estado = estado,
                onReintentar = onReintentar,
                mensajeVacio = "No hay actividades con ese filtro."
            ) { lista ->
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(lista, key = { it.actividad.id }) { item ->
                        TarjetaActividad(item.actividad, item.nombreCentro) { onActividad(item.actividad.id) }
                    }
                }
            }
        }
    }
}

@Composable
fun FilaCategorias(seleccionada: Categoria, onSeleccionar: (Categoria) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(Categoria.entries) { cat ->
            FilterChip(
                selected = seleccionada == cat,
                onClick = { onSeleccionar(cat) },
                label = { Text(cat.etiqueta) }
            )
        }
    }
}

@Composable
fun DetalleActividadScreen(
    vm: DetalleActividadViewModel,
    onBack: () -> Unit,
    onInscribir: () -> Unit,
    onCentro: (Int) -> Unit
) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    DetalleActividadContenido(
        estado = estado,
        onReintentar = { vm.cargar() },
        onBack = onBack,
        onInscribir = onInscribir,
        onCentro = onCentro
    )
}

@Composable
fun DetalleActividadContenido(
    estado: UiState<DetalleActividadUiState>,
    onReintentar: () -> Unit,
    onBack: () -> Unit,
    onInscribir: () -> Unit,
    onCentro: (Int) -> Unit
) {
    Scaffold(
        topBar = { BarraSuperior("Actividad", onBack) },
        bottomBar = {
            if (estado is UiState.Datos && !estado.datos.realizada) {
                Box(Modifier.padding(16.dp)) {
                    BotonInscripcion(estado.datos, onInscribir)
                }
            }
        }
    ) { padding ->
        ContenedorEstado(estado = estado, onReintentar = onReintentar, modifier = Modifier.padding(padding)) { datos ->
            DetalleActividadCuerpo(
                actividad = datos.actividad,
                centro = datos.centro,
                lleno = datos.lleno,
                realizada = datos.realizada,
                modifier = Modifier.padding(padding),
                onCentro = onCentro
            )
        }
    }
}

@Composable
private fun BotonInscripcion(datos: DetalleActividadUiState, onInscribir: () -> Unit) {
    when {
        datos.yaInscrito -> OutlinedButton(onClick = {}, enabled = false, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.CheckCircle, null)
            Spacer(Modifier.width(8.dp))
            Text("Tu familia ya está inscrita")
        }
        datos.lleno -> Button(onClick = {}, enabled = false, modifier = Modifier.fillMaxWidth()) {
            Text("Sin cupos disponibles")
        }
        else -> Button(onClick = onInscribir, modifier = Modifier.fillMaxWidth()) {
            Text("Inscribir a mi familia")
        }
    }
}

@Composable
private fun DetalleActividadCuerpo(
    actividad: Actividad,
    centro: Centro,
    lleno: Boolean,
    realizada: Boolean,
    modifier: Modifier,
    onCentro: (Int) -> Unit
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        ImagenPlaceholder(actividad.categoria, alto = 200, imagen = actividad.imagen)
        Column(Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Chip(actividad.categoria.etiqueta)
                Chip("Edad mínima ${actividad.edadMinima} años", fondo = Color(0xFFEEEEEE), textoColor = Color.DarkGray)
                if (realizada) Chip("Completada", fondo = VerdeClaro, textoColor = Verde)
            }
            Spacer(Modifier.height(10.dp))
            Text(actividad.titulo, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

            TextButton(onClick = { onCentro(centro.id) }, contentPadding = PaddingValues(0.dp)) {
                Text(centro.nombre, color = Azul, fontWeight = FontWeight.SemiBold)
            }
            SelloVerificado()

            Spacer(Modifier.height(16.dp))
            FilaInfo(Icons.Default.CalendarMonth, "${actividad.fecha} · ${actividad.hora}")
            FilaInfo(Icons.Default.Place, centro.direccion)
            FilaInfo(
                Icons.Default.Group,
                if (lleno) "Sin cupos" else "${actividad.cuposDisponibles} de ${actividad.cuposTotales} cupos disponibles"
            )
            FilaInfo(Icons.Default.ChildCare, "Ideal para niños desde ${actividad.edadMinima} años")

            Seccion("Descripción")
            Text(actividad.descripcion, style = MaterialTheme.typography.bodyMedium)

            Seccion("Requisitos")
            actividad.requisitos.forEach {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 2.dp)) {
                    Icon(Icons.Default.Check, null, tint = Verde, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun FilaInfo(icono: ImageVector, texto: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(icono, null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(10.dp))
        Text(texto, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun InscripcionScreen(vm: InscripcionViewModel, onBack: () -> Unit, onConfirmado: () -> Unit) {
    val datos by vm.datos.collectAsStateWithLifecycle()
    val formulario by vm.formulario.collectAsStateWithLifecycle()
    val realizada by vm.inscripcionRealizada.collectAsStateWithLifecycle()

    LaunchedEffect(realizada) {
        if (realizada) onConfirmado()
    }

    InscripcionContenido(
        datos = datos,
        formulario = formulario,
        onReintentar = { vm.cargar() },
        onAlternar = vm::alternarIntegrante,
        onComentario = vm::cambiarComentario,
        onConfirmar = vm::confirmar,
        onBack = onBack
    )
}

@Composable
fun InscripcionContenido(
    datos: UiState<InscripcionUiState>,
    formulario: FormularioInscripcion,
    onReintentar: () -> Unit,
    onAlternar: (Int) -> Unit,
    onComentario: (String) -> Unit,
    onConfirmar: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { BarraSuperior("Inscripción", onBack) },
        bottomBar = {
            if (datos is UiState.Datos) {
                Column(Modifier.padding(16.dp)) {
                    if (formulario.error != null) {
                        Text(
                            formulario.error,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    Button(
                        onClick = onConfirmar,
                        enabled = formulario.puedeConfirmar,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (formulario.enviando) {
                            CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp, color = Color.White)
                            Spacer(Modifier.width(10.dp))
                            Text("Registrando…")
                        } else {
                            Text("Confirmar inscripción (${formulario.seleccionados.size})")
                        }
                    }
                }
            }
        }
    ) { padding ->
        ContenedorEstado(estado = datos, onReintentar = onReintentar, modifier = Modifier.padding(padding)) { d ->
            Column(
                Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(d.actividad.titulo, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("${d.actividad.fecha} · ${d.actividad.hora}", color = MaterialTheme.colorScheme.onSurfaceVariant)

                Seccion("¿Quiénes van?")
                d.integrantes.forEach { integrante ->
                    val elegible = integrante.id in d.elegibles
                    val marcado = integrante.id in formulario.seleccionados
                    Card(
                        onClick = { onAlternar(integrante.id) },
                        enabled = elegible,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (marcado) AzulClaro else MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = marcado,
                                enabled = elegible,
                                onCheckedChange = { onAlternar(integrante.id) }
                            )
                            Column {
                                Text(integrante.nombre, fontWeight = FontWeight.SemiBold)
                                Text(
                                    if (elegible) "${integrante.edad} años"
                                    else "${integrante.edad} años · menor a la edad mínima (${d.actividad.edadMinima})",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                Seccion("Comentario para el centro (opcional)")
                OutlinedTextField(
                    value = formulario.comentario,
                    onValueChange = onComentario,
                    placeholder = { Text("Ej. Llegaremos 10 minutos tarde") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ConfirmacionScreen(
    actividad: Actividad,
    nombreCentro: String,
    onVerInscripciones: () -> Unit,
    onInicio: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.CheckCircle, null, tint = Verde, modifier = Modifier.size(96.dp))
        Spacer(Modifier.height(16.dp))
        Text("¡Listo, familia!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(
            "Tu inscripción quedó registrada. El centro recibirá tus datos y te enviaremos un recordatorio un día antes.",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(24.dp))
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
            Column(Modifier.padding(16.dp)) {
                Text(actividad.titulo, fontWeight = FontWeight.Bold)
                Text(nombreCentro, style = MaterialTheme.typography.bodySmall)
                Text("${actividad.fecha} · ${actividad.hora}", style = MaterialTheme.typography.bodySmall)
            }
        }
        Spacer(Modifier.height(32.dp))
        Button(onClick = onVerInscripciones, modifier = Modifier.fillMaxWidth()) { Text("Ver mis actividades") }
        TextButton(onClick = onInicio) { Text("Volver al inicio") }
    }
}

@Composable
fun MisInscripcionesScreen(vm: MisInscripcionesViewModel, onActividad: (Int) -> Unit) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    MisInscripcionesContenido(
        estado = estado,
        onReintentar = { vm.cargar() },
        onCancelar = vm::cancelar,
        onActividad = onActividad
    )
}

@Composable
fun MisInscripcionesContenido(
    estado: UiState<MisInscripcionesUiState>,
    onReintentar: () -> Unit,
    onCancelar: (Int) -> Unit,
    onActividad: (Int) -> Unit
) {

    var tab by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(topBar = { BarraSuperior("Mis actividades") }) { padding ->
        ContenedorEstado(
            estado = estado,
            onReintentar = onReintentar,
            modifier = Modifier.padding(padding),
            mensajeVacio = "Aún no tienes actividades. Inscríbete a una desde Inicio."
        ) { datos ->
            val lista = if (tab == 0) datos.proximas else datos.pasadas
            Column(Modifier.padding(padding)) {
                TabRow(selectedTabIndex = tab) {
                    Tab(selected = tab == 0, onClick = { tab = 0 }, text = { Text("Próximas (${datos.proximas.size})") })
                    Tab(selected = tab == 1, onClick = { tab = 1 }, text = { Text("Pasadas (${datos.pasadas.size})") })
                }
                if (lista.isEmpty()) {
                    Box(Modifier.fillMaxSize().padding(32.dp), contentAlignment = Alignment.Center) {
                        Text("Nada por aquí todavía.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(lista, key = { it.inscripcion.id }) { item ->
                            Card(
                                onClick = { onActividad(item.actividad.id) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(Modifier.padding(14.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Column(Modifier.weight(1f)) {
                                            Text(item.actividad.titulo, fontWeight = FontWeight.SemiBold)
                                            Text(item.centro.nombre, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        }
                                        Chip(if (item.inscripcion.pasada) "Completada" else "Confirmada")
                                    }
                                    Spacer(Modifier.height(6.dp))
                                    Text("${item.actividad.fecha} · ${item.actividad.hora}", style = MaterialTheme.typography.bodySmall)
                                    Text("Van: ${item.nombres}", style = MaterialTheme.typography.bodySmall)
                                    if (!item.inscripcion.pasada) {
                                        TextButton(
                                            onClick = { onCancelar(item.inscripcion.id) },
                                            contentPadding = PaddingValues(0.dp)
                                        ) { Text("Cancelar inscripción", color = Color(0xFFB71C1C)) }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
