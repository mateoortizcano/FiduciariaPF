package dominio

import java.util.{Calendar, Date}

trait Etapa
trait Preventas extends Etapa
trait Creacion extends Preventas
trait Compra extends Preventas
trait Construccion extends Etapa
trait Escrituracion extends Etapa

case class Proyecto [A <: Etapa] (nombre: Nombre, patrimonioAutonomo: PatrimonioAutonomo,
                     puntoEquilibrio: PuntoEquilibrio, exedentes: Excedentes,
                     unidadesVivienda: Unidad, precioUnitario: Precio, presupuesto: Presupuesto)

case class Nombre(value: String)

object Nombre {
  def apply(value: String): Option[Nombre] = {
    "^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\\s]*)+$".r.findFirstIn(value).map(new Nombre(_))
  }
}

case class Excedentes (porcentajeConstructores: Option[Porcentaje], porcentajeAportantes: Option[Porcentaje])

object Excedentes {
  def apply(porcentajeConstructores: Porcentaje, porcentajeAportantes: Porcentaje):
  Option[Excedentes] ={
    for {
      constructor <- Porcentaje(porcentajeConstructores.value)
      aportantes <- Porcentaje(porcentajeAportantes.value)
      if (constructor.value + aportantes.value == 100d) //como comparo dos decimales?
    } yield Excedentes(constructor, aportantes)
  }
}

case class PuntoEquilibrio (pocentaje: Option[Porcentaje], tiempoLimite: Option[Tiempo])

case class Unidad(value: Int)

object Unidad {
  def apply(value: Int): Option[Unidad] =
    if (value <= 0)
      None
    else
      Option(new Unidad(value))
}

case class Precio(value: Double)

object Precio {
  def apply(value: Double): Option[Precio] =
    if (value <= 0)
      None
    else
      Option(new Precio(value))
}

case class Presupuesto(value: Double)

object Presupuesto {
  def apply(value: Double): Option[Presupuesto] =
    if (value <= 0)
      None
    else
      Option(new Presupuesto(value))
}

case class Porcentaje(value: Double)

object Porcentaje {
  def apply(value: Double): Option[Porcentaje] =
    if (value <= 0)
      None
    else
      Option(new Porcentaje(value))
}

case class Tiempo(value: Calendar)

object Tiempo {
  def apply(value: Calendar): Option[Tiempo] =
    if (value.before(Calendar.getInstance()))
      None
    else
      Option(new Tiempo(value))
}