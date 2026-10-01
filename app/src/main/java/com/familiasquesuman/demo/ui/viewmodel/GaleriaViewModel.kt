package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.familiasquesuman.demo.data.FamiliasRepository
import kotlinx.coroutines.flow.StateFlow

class GaleriaViewModel(private val repo: FamiliasRepository) : ViewModel() {
    val fotosPendientes: StateFlow<Int> = repo.fotosPendientes
    fun tomarFoto() = repo.registrarFoto()
}
