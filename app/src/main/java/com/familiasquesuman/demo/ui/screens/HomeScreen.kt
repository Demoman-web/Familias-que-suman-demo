@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.familiasquesuman.demo.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.familiasquesuman.demo.ui.UiState
import com.familiasquesuman.demo.ui.components.AcentoAmarillo
import com.familiasquesuman.demo.ui.components.ContenedorEstado
import com.familiasquesuman.demo.ui.components.Seccion
import com.familiasquesuman.demo.ui.components.SelloVerificado
import com.familiasquesuman.demo.ui.components.TarjetaActividad
import com.familiasquesuman.demo.ui.theme.AzulClaro
import com.familiasquesuman.demo.ui.viewmodel.HomeUiState
import com.familiasquesuman.demo.ui.viewmodel.HomeViewModel

data class HomeAcciones(
    val onActividades: () -> Unit,
    val onDonar: () -> Unit,
    val onProyectos: () -> Unit,
    val onDirectorio: () -> Unit,
    val onNotificaciones: () -> Unit,
    val onCambiarCiudad: () -> Unit,
    val onActividad: (Int) -> Unit
)

@Composable
fun HomeScreen(vm: HomeViewModel, acciones: HomeAcciones) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    HomeContenido(estado = estado, onReintentar = { vm.cargar() }, acciones = acciones)
}

@Composable
fun HomeContenido(estado: UiState<HomeUiState>, onReintentar: () -> Unit, acciones: HomeAcciones) {
    ContenedorEstado(estado = estado, onReintentar = onReintentar) { datos ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Logo(ancho = 170)
                    Spacer(Modifier.weight(1f))
                    AssistChip(
                        onClick = acciones.onCambiarCiudad,
                        label = { Text(datos.ciudad.etiqueta, maxLines = 1) },
                        leadingIcon = { Icon(Icons.Default.LocationOn, null, modifier = Modifier.size(16.dp)) },
                        trailingIcon = { Icon(Icons.Default.ExpandMore, null, modifier = Modifier.size(16.dp)) }
                    )
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Hola, ${datos.nombreFamilia}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = acciones.onNotificaciones) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
                    }
                }
            }

            item {
                Text("¿Cómo quieres ayudar hoy?", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(6.dp))
                AcentoAmarillo()
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Pilar("Actividades", "En familia durante el año", Icons.Default.Event, Color(0xFFFFB74D), Modifier.weight(1f), acciones.onActividades)
                    Pilar("Quiero donar", "Apoyo en especie y tiempo", Icons.Default.Favorite, Color(0xFFEF9A9A), Modifier.weight(1f), acciones.onDonar)
                }
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Pilar("Proyectos", "Causas con objetivos específicos", Icons.Default.Flag, Color(0xFF9575CD), Modifier.weight(1f), acciones.onProyectos)
                    Pilar("Directorio de visiteo", "Centros para visitar en familia", Icons.Default.Map, Color(0xFF66BB6A), Modifier.weight(1f), acciones.onDirectorio)
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = AzulClaro)
                ) {
                    Column(Modifier.padding(14.dp)) {
                        SelloVerificado()
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Todos los centros del directorio están visitados y verificados por el equipo.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Seccion("Próximas actividades")
                    Spacer(Modifier.weight(1f))
                    TextButton(onClick = acciones.onActividades) { Text("Ver todas") }
                }
            }
            if (datos.proximas.isEmpty()) {
                item {
                    Text(
                        "No hay actividades próximas en ${datos.ciudad.etiqueta}.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            items(datos.proximas, key = { it.actividad.id }) { item ->
                TarjetaActividad(item.actividad, item.nombreCentro) { acciones.onActividad(item.actividad.id) }
            }
            item { Spacer(Modifier.height(8.dp)) }
        }
    }
}

@Composable
private fun Pilar(
    titulo: String,
    sub: String,
    icono: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(130.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Box(
                Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) { Icon(icono, null, tint = color) }
            Column {
                Text(titulo, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(sub, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2)
            }
        }
    }
}
