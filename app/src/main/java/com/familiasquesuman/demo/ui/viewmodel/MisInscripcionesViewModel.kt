package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.familiasquesuman.demo.data.FamiliasRepository
import com.familiasquesuman.demo.model.Actividad
import com.familiasquesuman.demo.model.Centro
import com.familiasquesuman.demo.model.Inscripcion
import com.familiasquesuman.demo.ui.UiState
import com.familiasquesuman.demo.ui.cargarEstado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

data class InscripcionItem(
    val inscripcion: Inscripcion,
    val actividad: Actividad,
    val centro: Centro,
    val nombres: String
)

data class MisInscripcionesUiState(
    val proximas: List<InscripcionItem>,
    val pasadas: List<InscripcionItem>
)

class MisInscripcionesViewModel(private val repo: FamiliasRepository) : ViewModel() {

    private val _estado = MutableStateFlow<UiState<MisInscripcionesUiState>>(UiState.Cargando)
    val estado: StateFlow<UiState<MisInscripcionesUiState>> = _estado.asStateFlow()

    init {
        cargar()

        viewModelScope.launch {
            repo.inscripciones.drop(1).collect { lista -> _estado.value = construir(lista) }
        }
    }

    fun cargar() {
        viewModelScope.launch {
            _estado.value = UiState.Cargando
            _estado.value = when (val r = cargarEstado { repo.obtenerInscripciones() }) {
                is UiState.Datos -> construir(r.datos)
                is UiState.Error -> r
                UiState.Vacio -> UiState.Vacio
                UiState.Cargando -> UiState.Cargando
            }
        }
    }

    fun cancelar(inscripcionId: Int) = repo.cancelarInscripcion(inscripcionId)

    private fun construir(lista: List<Inscripcion>): UiState<MisInscripcionesUiState> {
        if (lista.isEmpty()) return UiState.Vacio
        val items = lista.map { ins ->
            val actividad = repo.actividad(ins.actividadId)
            InscripcionItem(
                inscripcion = ins,
                actividad = actividad,
                centro = repo.centro(actividad.centroId),
                nombres = repo.familia.integrantes
                    .filter { it.id in ins.integrantesIds }
                    .joinToString(", ") { it.nombre.substringBefore(" (") }
            )
        }
        return UiState.Datos(
            MisInscripcionesUiState(
                proximas = items.filter { !it.inscripcion.pasada },
                pasadas = items.filter { it.inscripcion.pasada }
            )
        )
    }
}
