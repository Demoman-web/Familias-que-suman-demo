package com.familiasquesuman.demo.data

import com.familiasquesuman.demo.R
import com.familiasquesuman.demo.model.*

object FakeData {

    val familia = Familia(
        nombre = "Familia Ramírez",
        correo = "familia.ramirez@gmail.com",
        zona = "San Pedro Garza García",
        integrantes = listOf(
            Integrante(1, "Ana (mamá)", 41),
            Integrante(2, "Carlos (papá)", 43),
            Integrante(3, "Sofía", 12),
            Integrante(4, "Mateo", 8)
        )
    )

    val centros = listOf(
        Centro(
            id = 1,
            nombre = "VIFAC",
            tipo = "Albergue",
            categoria = Categoria.COMUNIDAD,
            descripcion = "Residencia donde atienden y capacitan a mujeres en estado vulnerable durante su embarazo. Ofrecen casa y alimento.",
            direccion = "Gral. Ignacio Zaragoza 625 Sur, La Pastora, Guadalupe",
            horarioVisitas = "Lunes a viernes 10:00–17:00",
            queNecesitan = listOf("Ropa", "Alimentos", "Higiene"),
            acepta = listOf(CategoriaDonacion.ROPA, CategoriaDonacion.ALIMENTOS, CategoriaDonacion.HIGIENE),
            telefono = "8183551100"
        ),
        Centro(
            id = 2,
            nombre = "Morada del Anciano Desvalido de Cadereyta",
            tipo = "Asilo de ancianos",
            categoria = Categoria.ADULTOS_MAYORES,
            descripcion = "Asilo donde se atiende a 47 adultos indigentes o de escasos recursos. Se les brinda casa, alimentación y atención.",
            direccion = "Blvd. José María González #1000, Cadereyta",
            horarioVisitas = "Sábados y domingos 10:00–14:00",
            queNecesitan = listOf("Alimentos", "Higiene", "Camas hospitalarias", "Sillas de ruedas", "Cobijas"),
            acepta = listOf(CategoriaDonacion.ALIMENTOS, CategoriaDonacion.HIGIENE, CategoriaDonacion.SILLAS_RUEDAS, CategoriaDonacion.COBIJAS),
            telefono = "8282840123",
            whatsapp = "8282840123"
        ),
        Centro(
            id = 3,
            nombre = "Hogar de la Misericordia",
            tipo = "Casa hogar",
            categoria = Categoria.NINOS,
            descripcion = "Casa hogar de adolescentes y adultos con parálisis cerebral. Reciben visitas familiares para convivencia y apoyo.",
            direccion = "Col. Mitras Centro, Monterrey",
            horarioVisitas = "Sábados 10:00–13:00",
            queNecesitan = listOf("Higiene", "Ropa", "Sillas de ruedas"),
            acepta = listOf(CategoriaDonacion.HIGIENE, CategoriaDonacion.ROPA, CategoriaDonacion.SILLAS_RUEDAS),
            telefono = "8183460000",
            whatsapp = "8183460000"
        ),
        Centro(
            id = 4,
            nombre = "Forma-T",
            tipo = "Asociación civil",
            categoria = Categoria.NINOS,
            descripcion = "Programas educativos y de formación para niñas, niños y jóvenes en comunidades vulnerables de Monterrey.",
            direccion = "Col. Independencia, Monterrey",
            horarioVisitas = "Sábados 9:00–12:00",
            queNecesitan = listOf("Útiles escolares", "Juguetes", "Cuentos infantiles"),
            acepta = listOf(CategoriaDonacion.UTILES, CategoriaDonacion.JUGUETES),
            telefono = "8181234504",
            whatsapp = "8181234504",
            imagen = R.drawable.logo_forma_t
        ),
        Centro(
            id = 5,
            nombre = "Escucha Corazón",
            tipo = "Asociación civil",
            categoria = Categoria.COMUNIDAD,
            descripcion = "Acompañamiento que marca una profunda diferencia: apoyo emocional y escucha para personas en situación vulnerable.",
            direccion = "Col. Del Valle, San Pedro Garza García",
            horarioVisitas = "Con cita",
            queNecesitan = listOf("Voluntarios para acompañamiento"),
            telefono = "8181234505",
            imagen = R.drawable.logo_escucha_corazon
        ),
        Centro(
            id = 6,
            nombre = "Cíclica",
            tipo = "Asociación civil",
            categoria = Categoria.MEDIO_AMBIENTE,
            descripcion = "Reciclaje y economía circular: recolección de materiales y talleres ambientales para familias.",
            direccion = "Col. Contry, Monterrey",
            horarioVisitas = "Sábados 9:00–12:00",
            queNecesitan = listOf("Materiales reciclables", "Voluntarios para talleres"),
            telefono = "8181234506",
            imagen = R.drawable.logo_ciclica
        ),
        Centro(
            id = 7,
            nombre = "Regalando Estrellas",
            tipo = "Asociación civil",
            categoria = Categoria.NINOS,
            descripcion = "Visitas al Hospital Materno Infantil llevando desayunos, regalos, juguetes y kits de higiene personal a las familias.",
            direccion = "Av. San Rafael No. 450, Guadalupe",
            horarioVisitas = "Visitas programadas",
            queNecesitan = listOf("Juguetes", "Kits de higiene", "Desayunos"),
            acepta = listOf(CategoriaDonacion.JUGUETES, CategoriaDonacion.HIGIENE, CategoriaDonacion.ALIMENTOS),
            telefono = "8181234507",
            whatsapp = "8181234507",
            imagen = R.drawable.logo_regalando_estrellas
        ),
        Centro(
            id = 8,
            nombre = "Trazo",
            tipo = "Asociación civil",
            categoria = Categoria.COMUNIDAD,
            descripcion = "Grupo de mujeres voluntarias que realiza visitas quincenales al Centro de Reinserción Social de Escobedo para acompañar a mujeres privadas de la libertad mediante pláticas y talleres.",
            direccion = "Escobedo, N.L.",
            horarioVisitas = "Visitas quincenales",
            queNecesitan = listOf("Productos de higiene", "Ropa"),
            acepta = listOf(CategoriaDonacion.HIGIENE, CategoriaDonacion.ROPA),
            telefono = "8181234508",
            imagen = R.drawable.logo_trazo
        ),
        Centro(
            id = 9,
            nombre = "DIF San Pedro",
            tipo = "Institución pública",
            categoria = Categoria.ADULTOS_MAYORES,
            descripcion = "Voluntariado DIF Te Acompaña: acompañamiento a adultos mayores y jóvenes en soledad, con discapacidad o con una red de apoyo reducida.",
            direccion = "San Pedro Garza García",
            horarioVisitas = "Lunes a viernes 9:00–16:00",
            queNecesitan = listOf("Cobijas", "Alimentos", "Voluntarios para visitas"),
            acepta = listOf(CategoriaDonacion.COBIJAS, CategoriaDonacion.ALIMENTOS),
            telefono = "8184004000",
            imagen = R.drawable.logo_dif_san_pedro
        ),
        Centro(
            id = 10,
            nombre = "Cocinando de Corazón a Corazón",
            tipo = "Comedor comunitario",
            categoria = Categoria.COMUNIDAD,
            descripcion = "Comedor comunitario diocesano que lleva alimento y esperanza a personas y familias en situación vulnerable. A través de una red de parroquias, los alimentos preparados se distribuyen en distintas zonas.",
            direccion = "Col. Centro, Monterrey",
            horarioVisitas = "Lunes a sábado 8:00–12:00",
            queNecesitan = listOf("Alimentos no perecederos", "Voluntarios para cocinar"),
            acepta = listOf(CategoriaDonacion.ALIMENTOS),
            telefono = "8181234510",
            whatsapp = "8181234510",
            imagen = R.drawable.logo_cocinando
        ),
        Centro(
            id = 11,
            nombre = "Comedor Comunitario El Sol",
            tipo = "Comedor comunitario",
            categoria = Categoria.COMUNIDAD,
            descripcion = "Comedor que sirve 200 desayunos diarios en Hermosillo.",
            direccion = "Col. Palo Verde, Hermosillo",
            horarioVisitas = "Lunes a sábado 7:00–10:00",
            queNecesitan = listOf("Arroz y frijol", "Voluntarios para servir"),
            acepta = listOf(CategoriaDonacion.ALIMENTOS),
            telefono = "6621234511",
            whatsapp = "6621234511",
            ciudad = Ciudad.HERMOSILLO
        )
    )

    val actividades = listOf(
        Actividad(
            id = 1,
            titulo = "Regalando Estrellas: visita al Materno Infantil",
            centroId = 7,
            categoria = Categoria.NINOS,
            fecha = "Sáb 3 Oct",
            hora = "09:45",
            descripcion = "Entrega de aproximadamente 200 desayunos. Entrega de regalos, juguetes y kits de higiene personal a las familias del hospital.",
            requisitos = listOf("Aportación en especie", "Llegar 15 minutos antes"),
            edadMinima = 6,
            cuposTotales = 5,
            cuposOcupados = 5,
            imagen = R.drawable.logo_regalando_estrellas
        ),
        Actividad(
            id = 2,
            titulo = "Visita a la Morada del Anciano",
            centroId = 2,
            categoria = Categoria.ADULTOS_MAYORES,
            fecha = "Dom 12 Oct",
            hora = "11:00 – 13:00",
            descripcion = "Una mañana de convivencia, lotería y pláticas con los 47 residentes del asilo. Se agradece llevar alimentos o productos de higiene.",
            requisitos = listOf("Cubrebocas", "Puntualidad"),
            edadMinima = 5,
            cuposTotales = 6,
            cuposOcupados = 2
        ),
        Actividad(
            id = 3,
            titulo = "Tarde de juegos en Hogar de la Misericordia",
            centroId = 3,
            categoria = Categoria.NINOS,
            fecha = "Sáb 18 Oct",
            hora = "10:00 – 13:00",
            descripcion = "Convivencia con los residentes de la casa hogar: juegos de mesa, música y manualidades.",
            requisitos = listOf("Llevar identificación", "Ropa cómoda"),
            edadMinima = 6,
            cuposTotales = 8,
            cuposOcupados = 5
        ),
        Actividad(
            id = 4,
            titulo = "Taller de reciclaje en familia",
            centroId = 6,
            categoria = Categoria.MEDIO_AMBIENTE,
            fecha = "Sáb 18 Oct",
            hora = "9:00 – 11:00",
            descripcion = "Aprende a separar residuos y a darles una segunda vida. Cada familia se lleva su propio contenedor.",
            requisitos = listOf("Llevar 5 envases limpios"),
            edadMinima = 5,
            cuposTotales = 15,
            cuposOcupados = 4,
            imagen = R.drawable.logo_ciclica
        ),
        Actividad(
            id = 5,
            titulo = "Servir en Cocinando de Corazón a Corazón",
            centroId = 10,
            categoria = Categoria.COMUNIDAD,
            fecha = "Vie 24 Oct",
            hora = "16:00 – 18:00",
            descripcion = "Ayuda a preparar y empacar los alimentos que se distribuyen a través de la red de parroquias.",
            requisitos = listOf("Cabello recogido", "Zapatos cerrados"),
            edadMinima = 10,
            cuposTotales = 10,
            cuposOcupados = 3,
            imagen = R.drawable.logo_cocinando
        ),
        Actividad(
            id = 6,
            titulo = "Cuentacuentos con Forma-T",
            centroId = 4,
            categoria = Categoria.NINOS,
            fecha = "Sáb 25 Oct",
            hora = "10:00 – 12:00",
            descripcion = "Lee cuentos a los más pequeños y ayuda con una manualidad. Trae tu libro favorito de la infancia.",
            requisitos = listOf("Llevar un libro infantil"),
            edadMinima = 4,
            cuposTotales = 6,
            cuposOcupados = 1,
            imagen = R.drawable.logo_forma_t
        ),
        Actividad(
            id = 7,
            titulo = "Servir desayunos",
            centroId = 11,
            categoria = Categoria.COMUNIDAD,
            fecha = "Sáb 11 Oct",
            hora = "7:00 – 10:00",
            descripcion = "Ayuda a servir y limpiar en el comedor comunitario.",
            requisitos = listOf("Cabello recogido", "Zapatos cerrados"),
            edadMinima = 10,
            cuposTotales = 8,
            cuposOcupados = 3,
            ciudad = Ciudad.HERMOSILLO
        )
    )

    val proyectos = listOf(
        Proyecto(
            id = 1,
            titulo = "Trazo… Escribiendo una nueva historia",
            causa = "Mujeres",
            objetivo = "25 mujeres acompañadas",
            descripcion = "Somos un grupo de mujeres voluntarias que realizamos visitas quincenales al Centro de Reinserción Social de Escobedo para acompañar a mujeres privadas de la libertad mediante pláticas de desarrollo humano y talleres.",
            avance = 0.5f,
            centroId = 8,
            imagen = R.drawable.logo_trazo
        ),
        Proyecto(
            id = 2,
            titulo = "Voluntariado DIF Te Acompaña",
            causa = "Adultos mayores",
            objetivo = "200 adultos mayores acompañados",
            descripcion = "Acompañar a adultos mayores o jóvenes vulnerables, en soledad, con discapacidad o con una red de apoyo reducida. Realizar visitas en familia mínimo una vez cada 15 días.",
            avance = 0.35f,
            centroId = 9,
            imagen = R.drawable.logo_dif_san_pedro
        ),
        Proyecto(
            id = 3,
            titulo = "Cocinando de Corazón a Corazón",
            causa = "Alimentación",
            objetivo = "Red de parroquias con comedor",
            descripcion = "Comedor comunitario diocesano que lleva alimento y esperanza a personas y familias en situación vulnerable. A través de una red de parroquias, los alimentos preparados se distribuyen en distintas zonas de la ciudad.",
            avance = 0.7f,
            centroId = 10,
            imagen = R.drawable.logo_cocinando
        )
    )

    val campanas = listOf(
        Campana(
            id = 1,
            titulo = "Suma a su Mesa",
            descripcion = "Recolección de alimentos y productos de limpieza para el Asilo de Ancianos Morada del Anciano Desvalido de Cadereyta. Se sirven 6,900 servicios de alimentación al mes.",
            centroId = 2,
            hasta = "30 de octubre",
            opciones = 3
        ),
        Campana(
            id = 2,
            titulo = "Bibliotecas Infantiles",
            descripcion = "Recolección de cuentos infantiles para crear bibliotecas en casas hogar y centros comunitarios.",
            centroId = 4,
            hasta = "15 de noviembre",
            opciones = 1,
            imagen = R.drawable.logo_bibliotecas
        ),
        Campana(
            id = 3,
            titulo = "Kits de higiene para el Materno Infantil",
            descripcion = "Jabón, pasta dental, toallas y pañales para las familias que Regalando Estrellas visita cada mes en el hospital.",
            centroId = 7,
            hasta = "1 de diciembre",
            opciones = 2,
            imagen = R.drawable.logo_regalando_estrellas
        ),
        Campana(
            id = 4,
            titulo = "Desayunos que Suman",
            descripcion = "Arroz, frijol y aceite para el comedor comunitario.",
            centroId = 11,
            hasta = "31 de octubre",
            ciudad = Ciudad.HERMOSILLO
        )
    )

    val notificaciones = listOf(
        Notificacion(1, "Recordatorio", "Mañana es tu actividad 'Visita a la Morada del Anciano' a las 11:00.", "hace 2 h"),
        Notificacion(2, "Cupo confirmado", "Tu familia está inscrita en 'Visita a la Morada del Anciano'.", "ayer"),
        Notificacion(3, "Nueva actividad", "Regalando Estrellas publicó 'Visita al Materno Infantil'.", "hace 3 días"),
        Notificacion(4, "Campaña", "'Bibliotecas Infantiles' ya suma 320 cuentos.", "hace 5 días")
    )

    val inscripcionesIniciales = listOf(
        Inscripcion(id = 100, actividadId = 2, integrantesIds = listOf(1, 3, 4), comentario = "", pasada = false),
        Inscripcion(id = 101, actividadId = 5, integrantesIds = listOf(1, 2), comentario = "", pasada = true)
    )

    fun centro(id: Int): Centro = centros.first { it.id == id }
    fun actividad(id: Int): Actividad = actividades.first { it.id == id }
    fun proyecto(id: Int): Proyecto = proyectos.first { it.id == id }
    fun campana(id: Int): Campana = campanas.first { it.id == id }
}
