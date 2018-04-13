package dominio

case class Proyecto (nombre: Nombre, patrimonioAutonomo: PatrimonioAutonomo,
                     puntoEquilibrio: PuntoEquilibrio, exedentes: Excedentes, etapa: Etapa,
                     unidadesVivienda: Unidad, precioUnitario: Precio, presupuesto: Presupuesto)

case class Excedentes (porcentajeConstructores: Porcentaje, porcentajeAportante: Porcentaje)

case class PuntoEquilibrio (pocentaje: Porcentaje, tiempoLimite: Tiempo)

case class Unidad()

case class Etapa()

case class Presupuesto()

case class Porcentaje()

case class Tiempo()



