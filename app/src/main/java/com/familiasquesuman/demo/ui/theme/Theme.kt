package com.familiasquesuman.demo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Azul = Color(0xFF1F3A5F)
val AzulOscuro = Color(0xFF16263F)
val AzulClaro = Color(0xFFE8EEF6)
val AzulBoton = Color(0xFF2F6BE0)
val Amarillo = Color(0xFFF6B428)
val AmarilloClaro = Color(0xFFFFF3D6)
val Verde = Color(0xFF2E9E5B)
val VerdeClaro = Color(0xFFE6F5EC)
val Fondo = Color(0xFFF6F8FA)
val GrisTexto = Color(0xFF5F6B7A)

private val LightColors = lightColorScheme(
    primary = Azul,
    onPrimary = Color.White,
    primaryContainer = AzulClaro,
    onPrimaryContainer = AzulOscuro,
    secondary = Amarillo,
    onSecondary = Color(0xFF3A2E00),
    secondaryContainer = AmarilloClaro,
    tertiary = Verde,
    tertiaryContainer = VerdeClaro,
    background = Fondo,
    surface = Color.White,
    onSurfaceVariant = GrisTexto,
)

@Composable
fun FamiliasQueSumanTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
