package dominio

trait MensajesDominio {
  val codigo: Int
  val mensaje: String

}

final case class NombreInvalido(codigo: Int =100 ,mensaje: String = "Nombre inválido") extends MensajesDominio
final case class CantidadInvalida(codigo: Int =101 ,mensaje: String = "Cantidad inválida") extends MensajesDominio
final case class PrecioInvalido(codigo: Int =102 ,mensaje: String = "Precio inválido") extends MensajesDominio
final case class PresupuestoInvalido(codigo: Int =103 ,mensaje: String = "Presupuesto inválido") extends MensajesDominio
final case class PorcentajeInvalido(codigo: Int =104 ,mensaje: String = "Porcentaje inválido") extends MensajesDominio
final case class FechaInvalida(codigo: Int =105 ,mensaje: String = "Fecha inválida") extends MensajesDominio
final case class EmailInvalido(codigo: Int =106 ,mensaje: String = "Email inválido") extends MensajesDominio
final case class ConstructorInvalido(codigo: Int =107 ,mensaje: String = "Fideicomitente constructor inválido") extends MensajesDominio
final case class AdherenteInvalido(codigo: Int =108 ,mensaje: String = "Fideicomitente adherente inválido") extends MensajesDominio
final case class CedulaInvalida(codigo: Int =109 ,mensaje: String = "Cédula inválida") extends MensajesDominio
final case class NITInvalido(codigo: Int =110 ,mensaje: String = "NIT inválido") extends MensajesDominio
final case class ExcedentesInvalidos(codigo: Int =111 ,mensaje: String = "La suma de los porcentajes de los excedentes es inválida") extends MensajesDominio
