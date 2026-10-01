package com.familiasquesuman.demo.data

import com.familiasquesuman.demo.model.Actividad
import com.familiasquesuman.demo.model.Centro
import com.familiasquesuman.demo.model.Ciudad
import com.familiasquesuman.demo.model.Donacion
import com.familiasquesuman.demo.model.Familia
import com.familiasquesuman.demo.model.Inscripcion
import com.familiasquesuman.demo.model.Integrante
import com.familiasquesuman.demo.model.Notificacion
import com.familiasquesuman.demo.model.Proyecto
import com.familiasquesuman.demo.model.Campana
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException

class FamiliasRepository {

    private val _ciudad = MutableStateFlow(Ciudad.MONTERREY)
    val ciudad: StateFlow<Ciudad> = _ciudad.asStateFlow()

    private val _sesionIniciada = MutableStateFlow(false)
    val sesionIniciada: StateFlow<Boolean> = _sesionIniciada.asStateFlow()

    val familia: Familia get() = FakeData.familia

    fun seleccionarCiudad(ciudad: Ciudad) { _ciudad.value = ciudad }
    fun iniciarSesion() { _sesionIniciada.value = true }
    fun cerrarSesion() { _sesionIniciada.value = false }

    fun cerrarCuenta() {
        _inscripciones.value = emptyList()
        _donaciones.value = emptyList()
        _fotosPendientes.value = 0
        _sesionIniciada.value = false
    }

    private val _simularError = MutableStateFlow(false)
    val simularError: StateFlow<Boolean> = _simularError.asStateFlow()
    fun cambiarSimularError(activo: Boolean) { _simularError.value = activo }

    private val _inscripciones = MutableStateFlow(FakeData.inscripcionesIniciales)
    val inscripciones: StateFlow<List<Inscripcion>> = _inscripciones.asStateFlow()

    private val _donaciones = MutableStateFlow<List<Donacion>>(emptyList())
    val donaciones: StateFlow<List<Donacion>> = _donaciones.asStateFlow()

    private val _fotosPendientes = MutableStateFlow(0)
    val fotosPendientes: StateFlow<Int> = _fotosPendientes.asStateFlow()

    private var siguienteId = 1000

    private suspend fun simularRed() {
        delay(700)
        if (_simularError.value) throw IOException("No se pudo conectar con el servidor")
    }

    private fun conCuposActualizados(actividad: Actividad): Actividad {
        val propias = _inscripciones.value.count { it.actividadId == actividad.id && !it.pasada }
        return actividad.copy(cuposOcupados = actividad.cuposOcupados + propias)
    }

    suspend fun obtenerActividades(ciudad: Ciudad): List<Actividad> {
        simularRed()
        return FakeData.actividades.filter { it.ciudad == ciudad }.map(::conCuposActualizados)
    }

    suspend fun obtenerActividad(id: Int): Actividad {
        simularRed()
        return conCuposActualizados(FakeData.actividad(id))
    }

    suspend fun obtenerActividadesDeCentro(centroId: Int): List<Actividad> {
        simularRed()
        return FakeData.actividades.filter { it.centroId == centroId }.map(::conCuposActualizados)
    }

    suspend fun obtenerCentros(ciudad: Ciudad): List<Centro> {
        simularRed()
        return FakeData.centros.filter { it.ciudad == ciudad }
    }

    suspend fun obtenerCentro(id: Int): Centro {
        simularRed()
        return FakeData.centro(id)
    }

    fun centro(id: Int): Centro = FakeData.centro(id)
    fun actividad(id: Int): Actividad = conCuposActualizados(FakeData.actividad(id))

    suspend fun obtenerProyectos(): List<Proyecto> {
        simularRed()
        return FakeData.proyectos
    }

    suspend fun obtenerProyecto(id: Int): Proyecto {
        simularRed()
        return FakeData.proyecto(id)
    }

    suspend fun obtenerNotificaciones(): List<Notificacion> {
        simularRed()
        return FakeData.notificaciones
    }

    suspend fun obtenerInscripciones(): List<Inscripcion> {
        simularRed()
        return _inscripciones.value
    }

    suspend fun obtenerCampanas(ciudad: Ciudad): List<Campana> {
        simularRed()
        return FakeData.campanas.filter { it.ciudad == ciudad }
    }

    fun actividadRealizada(actividadId: Int): Boolean =
        _inscripciones.value.any { it.actividadId == actividadId && it.pasada }

    fun yaInscrito(actividadId: Int): Boolean =
        _inscripciones.value.any { it.actividadId == actividadId && !it.pasada }

    fun integrantesElegibles(actividad: Actividad): List<Integrante> =
        familia.integrantes.filter { it.edad >= actividad.edadMinima }

    suspend fun inscribir(actividadId: Int, integrantesIds: List<Int>, comentario: String): Inscripcion {
        val actividad = conCuposActualizados(FakeData.actividad(actividadId))

        check(!actividadRealizada(actividadId)) { "Esta actividad ya se realizó" }
        check(!yaInscrito(actividadId)) { "Tu familia ya está inscrita en esta actividad" }
        check(actividad.cuposDisponibles > 0) { "Ya no hay cupos disponibles" }
        require(integrantesIds.isNotEmpty()) { "Selecciona al menos un integrante" }
        val elegibles = integrantesElegibles(actividad).map { it.id }
        require(integrantesIds.all { it in elegibles }) { "Hay integrantes que no cumplen la edad mínima" }

        simularRed()

        val nueva = Inscripcion(
            id = siguienteId++,
            actividadId = actividadId,
            integrantesIds = integrantesIds,
            comentario = comentario.trim()
        )
        _inscripciones.update { listOf(nueva) + it }
        return nueva
    }

    fun cancelarInscripcion(id: Int) {
        _inscripciones.update { lista -> lista.filter { it.id != id } }
    }

    suspend fun ayudarCampana(campanaId: Int): Donacion {
        val campana = FakeData.campana(campanaId)
        check(_donaciones.value.none { it.tipo == "Campaña" && it.detalle == campana.titulo }) {
            "Ya registraste tu apoyo a esta campaña"
        }
        simularRed()
        val nueva = Donacion(
            id = siguienteId++,
            tipo = "Campaña",
            detalle = campana.titulo,
            centroId = campana.centroId
        )
        _donaciones.update { listOf(nueva) + it }
        return nueva
    }

    fun registrarFoto() {
        _fotosPendientes.update { it + 1 }
    }
}
