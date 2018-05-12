package dominio

trait Persona
trait Juridica extends Persona {val empresa: Empresa}
trait Natural extends Persona


trait Fideicomitente {val nombre: Nombre
  val ciudad: Ciudad
  val email: Email
  val docId: DocId[TipoDoc]
}
case class Adherente (nombre: Nombre,
                      ciudad: Ciudad,
                      email: Email,
                      docId: DocId[CC],
                      estadoCivil: EstadoCivil,
                      formaPago: FormaPago
                     ) extends Fideicomitente
case class Aportante (
                       nombre: Nombre,
                       ciudad: Ciudad,
                       email: Email,
                       docId: DocId[TipoDoc],
                     empresa: Empresa
                     ) extends Fideicomitente
case class Constructor (
                         nombre: Nombre,
                         ciudad: Ciudad,
                         email: Email,
                         docId: DocId[NIT],
                         empresa: Empresa
                       ) extends Fideicomitente

trait TipoDoc
trait CC extends TipoDoc
trait NIT extends TipoDoc

case class DocId[A <: TipoDoc](numero: String, ciudad: Ciudad)

object DocId {
  def apply(tipoDoc: TipoDoc, numero: String, ciudad: Ciudad): Option[DocId[TipoDoc]] = {
    tipoDoc match {
      case x : CC => """^[0-9]$""".r.findFirstIn(numero).map(new DocId[CC](_, ciudad))(collection.breakOut)
      case y : NIT => """^[0-9]{1-15}-[0-9]$""".r.findFirstIn(numero).map(new DocId[NIT](_, ciudad))(collection.breakOut)
    }
  }
}

trait EstadoCivil
trait Soltero extends EstadoCivil
trait Casado extends EstadoCivil

case class FormaPago (valorEncargoFiduciario: Precio, pagoInmediato: Precio,
                      pagoEntrega: Precio)
case class Email(value: String) extends AnyVal

object Email {
  def apply(value: String): Option[Email] =
    "^[\\w]+@{1}[\\w]+\\.[a-z]{2,3}$".r.findFirstIn(value).map(new Email(_))
}