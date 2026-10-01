package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.familiasquesuman.demo.data.FamiliasRepository
import com.familiasquesuman.demo.model.Familia

class AvisoPrivacidadViewModel(private val repo: FamiliasRepository) : ViewModel() {
    val familia: Familia = repo.familia
    fun cerrarCuenta() = repo.cerrarCuenta()
}
