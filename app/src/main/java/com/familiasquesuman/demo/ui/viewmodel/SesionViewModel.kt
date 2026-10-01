package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.familiasquesuman.demo.data.FamiliasRepository
import com.familiasquesuman.demo.model.Ciudad
import com.familiasquesuman.demo.model.Familia
import kotlinx.coroutines.flow.StateFlow

class SesionViewModel(private val repo: FamiliasRepository) : ViewModel() {
    val ciudad: StateFlow<Ciudad> = repo.ciudad
    val familia: Familia = repo.familia

    fun seleccionarCiudad(ciudad: Ciudad) = repo.seleccionarCiudad(ciudad)
    fun iniciarSesion() = repo.iniciarSesion()
}
