@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.familiasquesuman.demo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.familiasquesuman.demo.R
import com.familiasquesuman.demo.model.Ciudad
import com.familiasquesuman.demo.model.Familia
import com.familiasquesuman.demo.ui.theme.Amarillo
import com.familiasquesuman.demo.ui.theme.Azul
import com.familiasquesuman.demo.ui.theme.AzulClaro
import com.familiasquesuman.demo.ui.viewmodel.SesionViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onListo: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1500)
        onListo()
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Logo(ancho = 300)
            Spacer(Modifier.height(12.dp))
            Text("Pequeñas acciones, gran impacto", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun Logo(ancho: Int) {
    Image(
        painter = painterResource(R.drawable.logo_fqs),
        contentDescription = "Familias que Suman",
        modifier = Modifier.width(ancho.dp)
    )
}

@Composable
fun CiudadScreen(vm: SesionViewModel, onContinuar: () -> Unit) {
    val ciudad by vm.ciudad.collectAsStateWithLifecycle()
    CiudadContenido(
        seleccionada = ciudad,
        onSeleccionar = vm::seleccionarCiudad,
        onContinuar = onContinuar
    )
}

@Composable
fun CiudadContenido(seleccionada: Ciudad, onSeleccionar: (Ciudad) -> Unit, onContinuar: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.LocationOn, null, tint = Azul, modifier = Modifier.size(40.dp))
        Spacer(Modifier.height(12.dp))
        Text("¿En qué ciudad quieres sumar?", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(
            "Selecciona tu ciudad para ver la información más cercana.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(24.dp))
        Ciudad.entries.forEach { ciudad ->
            val esta = seleccionada == ciudad
            Card(
                onClick = { onSeleccionar(ciudad) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (esta) AzulClaro else MaterialTheme.colorScheme.surface
                )
            ) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = esta, onClick = { onSeleccionar(ciudad) })
                    Spacer(Modifier.width(8.dp))
                    Text(ciudad.etiqueta, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = onContinuar, modifier = Modifier.fillMaxWidth()) {
            Text("Continuar")
        }
    }
}

@Composable
fun LoginScreen(vm: SesionViewModel, onEntrar: () -> Unit, onAviso: () -> Unit) {
    LoginContenido(
        familia = vm.familia,
        onEntrar = {
            vm.iniciarSesion()
            onEntrar()
        },
        onAviso = onAviso
    )
}

@Composable
fun LoginContenido(familia: Familia, onEntrar: () -> Unit, onAviso: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Logo(ancho = 300)
        Spacer(Modifier.height(16.dp))
        Text("Bienvenida, familia", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(
            "Inicia sesión para inscribirte a actividades y llevar el registro de tu familia.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(32.dp))

        OutlinedButton(
            onClick = onEntrar,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF4285F4)),
                contentAlignment = Alignment.Center
            ) { Text("G", color = Color.White, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold) }
            Spacer(Modifier.width(12.dp))
            Text("Continuar con Google")
        }

        Spacer(Modifier.height(12.dp))
        Card(
            onClick = onEntrar,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Amarillo),
                    contentAlignment = Alignment.Center
                ) { Text(familia.nombre.substringAfter(" ").first().toString(), fontWeight = FontWeight.Bold) }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(familia.nombre, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                    Text(familia.correo, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        TextButton(onClick = onAviso) {
            Text("Aviso de privacidad", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
