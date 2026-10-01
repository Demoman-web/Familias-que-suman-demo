package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.familiasquesuman.demo.data.FamiliasRepository
import com.familiasquesuman.demo.model.Centro
import com.familiasquesuman.demo.ui.UiState
import com.familiasquesuman.demo.ui.cargarEstado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DetalleCentroUiState(
    val centro: Centro,
    val actividades: List<ActividadItem>
)

class DetalleCentroViewModel(
    private val repo: FamiliasRepository,
    private val centroId: Int
) : ViewModel() {

    private val _estado = MutableStateFlow<UiState<DetalleCentroUiState>>(UiState.Cargando)
    val estado: StateFlow<UiState<DetalleCentroUiState>> = _estado.asStateFlow()

    init { cargar() }

    fun cargar() {
        viewModelScope.launch {
            _estado.value = UiState.Cargando
            _estado.value = cargarEstado {
                val centro = repo.obtenerCentro(centroId)
                DetalleCentroUiState(
                    centro = centro,
                    actividades = repo.obtenerActividadesDeCentro(centroId)
                        .map { ActividadItem(it, centro.nombre) }
                )
            }
        }
    }
}
