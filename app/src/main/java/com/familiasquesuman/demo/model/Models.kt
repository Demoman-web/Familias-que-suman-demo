package com.familiasquesuman.demo.model

enum class Ciudad(val etiqueta: String) {
    MONTERREY("Monterrey, N.L."),
    HERMOSILLO("Hermosillo, Son.")
}

enum class Categoria(val etiqueta: String) {
    TODAS("Todas"),
    NINOS("Niños"),
    ADULTOS_MAYORES("Adultos mayores"),
    MEDIO_AMBIENTE("Medio ambiente"),
    ANIMALES("Animales"),
    COMUNIDAD("Comunidad")
}

data class Integrante(
    val id: Int,
    val nombre: String,
    val edad: Int
)

data class Familia(
    val nombre: String,
    val correo: String,
    val zona: String,
    val integrantes: List<Integrante>
)

enum class CategoriaDonacion(val etiqueta: String) {
    TODOS("Todos"),
    JUGUETES("Juguetes"),
    ROPA("Ropa"),
    ALIMENTOS("Alimentos"),
    HIGIENE("Higiene"),
    SILLAS_RUEDAS("Sillas de ruedas"),
    UTILES("Útiles escolares"),
    COBIJAS("Cobijas"),
    CROQUETAS("Croquetas")
}

data class Centro(
    val id: Int,
    val nombre: String,
    val tipo: String,
    val categoria: Categoria,
    val descripcion: String,
    val direccion: String,
    val horarioVisitas: String,
    val queNecesitan: List<String>,
    val acepta: List<CategoriaDonacion> = emptyList(),
    val telefono: String? = null,
    val whatsapp: String? = null,
    val verificado: Boolean = true,
    val ciudad: Ciudad = Ciudad.MONTERREY,
    val imagen: Int? = null
)

data class Campana(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val centroId: Int,
    val hasta: String? = null,
    val opciones: Int = 1,
    val ciudad: Ciudad = Ciudad.MONTERREY,
    val imagen: Int? = null
)

data class Actividad(
    val id: Int,
    val titulo: String,
    val centroId: Int,
    val categoria: Categoria,
    val fecha: String,
    val hora: String,
    val descripcion: String,
    val requisitos: List<String>,
    val edadMinima: Int,
    val cuposTotales: Int,
    val cuposOcupados: Int,
    val ciudad: Ciudad = Ciudad.MONTERREY,
    val imagen: Int? = null
) {
    val cuposDisponibles: Int get() = cuposTotales - cuposOcupados
}

data class Proyecto(
    val id: Int,
    val titulo: String,
    val causa: String,
    val objetivo: String,
    val descripcion: String,
    val avance: Float,
    val centroId: Int,
    val imagen: Int? = null
)

data class Inscripcion(
    val id: Int,
    val actividadId: Int,
    val integrantesIds: List<Int>,
    val comentario: String,
    val pasada: Boolean = false
)

data class Notificacion(
    val id: Int,
    val titulo: String,
    val cuerpo: String,
    val hace: String
)

data class Donacion(
    val id: Int,
    val tipo: String,
    val detalle: String,
    val centroId: Int
)

