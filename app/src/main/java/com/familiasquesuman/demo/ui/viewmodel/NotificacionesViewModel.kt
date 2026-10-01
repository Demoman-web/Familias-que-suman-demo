package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.familiasquesuman.demo.data.FamiliasRepository
import com.familiasquesuman.demo.model.Notificacion
import com.familiasquesuman.demo.ui.UiState
import com.familiasquesuman.demo.ui.cargarEstado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotificacionesViewModel(private val repo: FamiliasRepository) : ViewModel() {

    private val _estado = MutableStateFlow<UiState<List<Notificacion>>>(UiState.Cargando)
    val estado: StateFlow<UiState<List<Notificacion>>> = _estado.asStateFlow()

    init { cargar() }

    fun cargar() {
        viewModelScope.launch {
            _estado.value = UiState.Cargando
            _estado.value = cargarEstado(esVacio = { it.isEmpty() }) { repo.obtenerNotificaciones() }
        }
    }
}
