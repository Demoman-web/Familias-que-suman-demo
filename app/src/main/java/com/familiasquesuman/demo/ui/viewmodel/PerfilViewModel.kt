package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.familiasquesuman.demo.data.FamiliasRepository
import com.familiasquesuman.demo.model.Familia
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class PerfilUiState(
    val familia: Familia,
    val completadas: Int,
    val proximas: Int,
    val donaciones: Int,
    val fotosPendientes: Int,
    val simularError: Boolean
)

class PerfilViewModel(private val repo: FamiliasRepository) : ViewModel() {

    val estado: StateFlow<PerfilUiState> = combine(
        repo.inscripciones, repo.donaciones, repo.fotosPendientes, repo.simularError
    ) { inscripciones, donaciones, fotos, simularError ->
        PerfilUiState(
            familia = repo.familia,
            completadas = inscripciones.count { it.pasada },
            proximas = inscripciones.count { !it.pasada },
            donaciones = donaciones.size,
            fotosPendientes = fotos,
            simularError = simularError
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        PerfilUiState(repo.familia, 0, 0, 0, 0, false)
    )

    fun cambiarSimularError(activo: Boolean) = repo.cambiarSimularError(activo)
    fun cerrarSesion() = repo.cerrarSesion()
}
