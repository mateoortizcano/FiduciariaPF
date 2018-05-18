package dominio

import slick.ast.JoinType

trait EstadoCivil
trait Soltero extends EstadoCivil
trait Casado extends EstadoCivil

trait Fideicomitente {val nombre: String
  val ciudad: String
  val email: String
  val docId: DocId
}

case class Constructor(
                        nombre: String,
                        ciudad: String,
                        email: String,
                        docId: DocId,
                        empresa: Empresa
                      ) extends Fideicomitente

object Constructor{
  def apply(
             nombre: String,
             ciudad: String,
             email: String,
             docId: DocId,
             empresa: Empresa
           ): Either[MensajesDominio, Constructor] =
    docId match {
      case DocId(NIT,_,_) =>
        for {
          nom <- Nombre.validar(nombre)
          ciu <- Ciudad.validar(ciudad)
          ema <- Email.validar(email)
        }yield new Constructor(nombre,ciudad,email,docId,empresa)
      case _ => Left(ConstructorInvalido())
    }
}

case class Adherente (nombre: String,
                      ciudad: String,
                      email: String,
                      docId: DocId,
                      estadoCivil: EstadoCivil,
                      formaPago: FormaPago
                     ) extends Fideicomitente

object Adherente {
  def apply(nombre: String,
            ciudad: String,
            email: String,
            docId: DocId,
            estadoCivil: EstadoCivil,
            formaPago: FormaPago
           ): Either[MensajesDominio, Adherente] =
    docId match {
      case DocId(CC, _, _) =>
        for {
          nom <- Nombre.validar(nombre)
          ciu <- Ciudad.validar(ciudad)
          ema <- Email.validar(email)
        }yield new Adherente(nombre, ciudad, email, docId, estadoCivil, formaPago)
      case _ => Left(AdherenteInvalido())
    }
}

case class Aportante (
                       nombre: String,
                       ciudad: String,
                       email: String,
                       docId: DocId,
                       empresa: Empresa
                     ) extends Fideicomitente

object Aportante {
  def apply(
             nombre: String,
             ciudad: String,
             email: String,
             docId: DocId,
             empresa: Empresa
           ): Either[MensajesDominio, Aportante] =
    for {
      nom <- Nombre.validar(nombre)
      ciu <- Ciudad.validar(ciudad)
      ema <- Email.validar(email)
    }yield new Aportante(nom, ciu, ema, docId, empresa)
}

trait TipoDoc
object CC extends TipoDoc
object NIT extends TipoDoc

object TipoDoc {
  def apply(s: String): TipoDoc =
    s.toLowerCase match{
    case "cc" => CC
    case "nit" => NIT
  }
}

case class DocId(tipoDoc: TipoDoc,numero: String, ciudad: String)

object DocId {
  def apply(tipoDoc: String, numero: String, ciudad: String): Either[MensajesDominio, DocId] = {
    TipoDoc(tipoDoc) match{
      case CC =>  if(numero.matches("^[0-9]$"))
                    for {
                      ciu <- Ciudad.validar(ciudad)
                    }yield new DocId(CC, numero, ciu)
                  else Left(CedulaInvalida())
      case NIT => if (numero.matches("^[0-9]{1-15}-[0-9]$"))
                    for {
                      ciu <- Ciudad.validar(ciudad)
                    }yield new DocId(NIT, numero, ciu)
      else Left(NITInvalido())
    }
  }
}

case class FormaPago (valorEncargoFiduciario: Double, pagoInmediato: Double,
                      pagoEntrega: Double)

object FormaPago {
  def apply(porcentajeEncargoFiduciario: Double, porcentajeInmediato: Double,
            porcentajeEntrega: Double): Either[MensajesDominio, FormaPago] =
    for {
      p1 <- Porcentaje.validar(porcentajeEncargoFiduciario)
      p2 <- Porcentaje.validar(porcentajeInmediato)
      p3 <- Porcentaje.validar(porcentajeEntrega)
      if (p1 + p2 + p3 == 100d)
    }yield new FormaPago(p1, p2, p3)
    //new FormaPago(valorEncargoFiduciario, pagoInmediato, pagoEntrega)
}

object Email {
  def validar(value: String): Either[MensajesDominio, String] =
    if (value.matches("^[\\w]+@{1}[\\w]+\\.[a-z]{2,3}$"))
      Right(value)
    else Left(EmailInvalido())
}