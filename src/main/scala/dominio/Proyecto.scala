package dominio

import java.util.{Calendar, Date}

trait Etapa
trait Preventas extends Etapa
trait Creacion extends Preventas
trait Compra extends Preventas
trait Construccion extends Etapa
trait Escrituracion extends Etapa

case class Proyecto [A <: Etapa] (nombre: String, patrimonioAutonomo: PatrimonioAutonomo,
                     puntoEquilibrio: PuntoEquilibrio, exedentes: Excedentes,
                     unidadesVivienda: Int, precioUnitario: Double, presupuesto: Double)

object Proyecto {
  def apply(nombre: String, patrimonioAutonomo: PatrimonioAutonomo,
            puntoEquilibrio: PuntoEquilibrio, exedentes: Excedentes,
            unidadesVivienda: Int, precioUnitario: Double, presupuesto: Double): Either[MensajesDominio, Proyecto[Creacion]] =
    for {
      nomb <- Nombre.validar(nombre)
      unidades <- Unidad.validar(unidadesVivienda)
      precioUnitario <- Precio.validar(precioUnitario)
      presup <- Presupuesto.validar(presupuesto)
    } yield new Proyecto[Creacion](nomb, patrimonioAutonomo, puntoEquilibrio, exedentes, unidades, precioUnitario, presup)
}

object Nombre {
  def validar(value: String): Either[MensajesDominio,String] = {
    if (value.matches("^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\\s]*)+$"))
      Right(value)
    else Left(NombreInvalido())
  }
}

case class Excedentes (porcentajeConstructores: Double, porcentajeAportantes: Double)

object Excedentes {
  def apply(porcentajeConstructores: Double, porcentajeAportantes: Double):
  Either[MensajesDominio, Excedentes] =
    for {
      p1 <- Porcentaje.validar(porcentajeConstructores)
      p2 <- Porcentaje.validar(porcentajeAportantes)
      if (p1 + p2 == 100d)
    }yield new Excedentes(p1, p2)
}

case class PuntoEquilibrio (pocentaje: Double, tiempoLimite: Calendar)

object PuntoEquilibrio {
  def apply(pocentaje: Double, tiempoLimite: Calendar): Either[MensajesDominio, PuntoEquilibrio] =
  for {
    p <- Porcentaje.validar(pocentaje)
    t <- Tiempo.validar(tiempoLimite)
  }yield new PuntoEquilibrio(p, t)
}

object Unidad {
  def validar(value: Int): Either[MensajesDominio,Int] =
    if (value <= 0)
      Left(CantidadInvalida())
    else
      Right(value)
}



object Precio {
  def validar(value: Double): Either[MensajesDominio, Double] =
    if (value <= 0)
      Left(PrecioInvalido())
    else
      Right(value)
}



object Presupuesto {
  def validar(value: Double): Either[MensajesDominio, Double] =
    if (value <= 0)
      Left(PresupuestoInvalido())
    else
      Right(value)
}


object Porcentaje {
  def validar(value: Double): Either[MensajesDominio, Double] =
    if (value <= 0)
      Left(PorcentajeInvalido())
    else
      Right(value)
}


object Tiempo {
  def validar(value: Calendar): Either[MensajesDominio, Calendar] =
    if (value.before(Calendar.getInstance()))
      Left(FechaInvalida())
    else
      Right(value)
}