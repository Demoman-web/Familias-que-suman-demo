package com.familiasquesuman.demo.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

private fun Context.abrir(intent: Intent) {
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "No hay una app para abrir esto", Toast.LENGTH_SHORT).show()
    }
}

fun Context.llamar(telefono: String) =
    abrir(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telefono")))

fun Context.whatsapp(numero: String) =
    abrir(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/52$numero")))

fun Context.comoLlegar(direccion: String) =
    abrir(Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + Uri.encode(direccion))))

fun Context.correo(destinatario: String, asunto: String, cuerpo: String) =
    abrir(
        Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(destinatario))
            putExtra(Intent.EXTRA_SUBJECT, asunto)
            putExtra(Intent.EXTRA_TEXT, cuerpo)
        }
    )
