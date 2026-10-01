package com.familiasquesuman.demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.familiasquesuman.demo.data.FamiliasRepository
import com.familiasquesuman.demo.model.Actividad

class ConfirmacionViewModel(repo: FamiliasRepository, actividadId: Int) : ViewModel() {
    val actividad: Actividad = repo.actividad(actividadId)
    val nombreCentro: String = repo.centro(actividad.centroId).nombre
}
