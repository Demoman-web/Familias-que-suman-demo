package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.familiasquesuman.demo.data.FamiliasRepository
import com.familiasquesuman.demo.model.Ciudad
import com.familiasquesuman.demo.ui.UiState
import com.familiasquesuman.demo.ui.cargarEstado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

data class HomeUiState(
    val nombreFamilia: String,
    val ciudad: Ciudad,
    val proximas: List<ActividadItem>
)

class HomeViewModel(private val repo: FamiliasRepository) : ViewModel() {

    private val _estado = MutableStateFlow<UiState<HomeUiState>>(UiState.Cargando)
    val estado: StateFlow<UiState<HomeUiState>> = _estado.asStateFlow()

    init {

        viewModelScope.launch { repo.ciudad.collect { cargar() } }

        viewModelScope.launch { repo.inscripciones.drop(1).collect { cargar(mostrarCarga = false) } }
    }

    fun cargar(mostrarCarga: Boolean = true) {
        viewModelScope.launch {
            if (mostrarCarga) _estado.value = UiState.Cargando
            _estado.value = cargarEstado {
                val ciudad = repo.ciudad.value
                HomeUiState(
                    nombreFamilia = repo.familia.nombre,
                    ciudad = ciudad,
                    proximas = repo.obtenerActividades(ciudad).take(4)
                        .map { ActividadItem(it, repo.centro(it.centroId).nombre) }
                )
            }
        }
    }
}
