package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.familiasquesuman.demo.data.FamiliasRepository
import com.familiasquesuman.demo.model.Centro
import com.familiasquesuman.demo.model.Proyecto
import com.familiasquesuman.demo.ui.UiState
import com.familiasquesuman.demo.ui.cargarEstado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProyectoItem(val proyecto: Proyecto, val centro: Centro)

class ProyectosViewModel(private val repo: FamiliasRepository) : ViewModel() {

    private val _estado = MutableStateFlow<UiState<List<ProyectoItem>>>(UiState.Cargando)
    val estado: StateFlow<UiState<List<ProyectoItem>>> = _estado.asStateFlow()

    init { cargar() }

    fun cargar() {
        viewModelScope.launch {
            _estado.value = UiState.Cargando
            _estado.value = cargarEstado(esVacio = { it.isEmpty() }) {
                repo.obtenerProyectos().map { ProyectoItem(it, repo.centro(it.centroId)) }
            }
        }
    }
}

class DetalleProyectoViewModel(
    private val repo: FamiliasRepository,
    private val proyectoId: Int
) : ViewModel() {

    private val _estado = MutableStateFlow<UiState<ProyectoItem>>(UiState.Cargando)
    val estado: StateFlow<UiState<ProyectoItem>> = _estado.asStateFlow()

    init { cargar() }

    fun cargar() {
        viewModelScope.launch {
            _estado.value = UiState.Cargando
            _estado.value = cargarEstado {
                val p = repo.obtenerProyecto(proyectoId)
                ProyectoItem(p, repo.centro(p.centroId))
            }
        }
    }
}
