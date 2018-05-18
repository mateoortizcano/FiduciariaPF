package services

import java.util.Calendar

import dominio._

trait operacionesFideicomitentes[T <: Fideicom] {
  def crearFideicomitente(t: T): Either[MensajesDominio, Fideicomitente]
  def modificarFideicomitente = ???
  def eliminarFideicomitente = ???
}

object operacionesFideicomitentes {
  implicit object ConstrAportantOper extends operacionesFideicomitentes[Fideicomit] {
    override def crearFideicomitente(t: Fideicomit): Either[MensajesDominio, Fideicomitente] = {
      for {
        constructor <- Constructor(t.persona.nombre, t.persona.ciudad, t.persona.email,
          new DocId(TipoDoc(t.persona.tipoId), t.persona.nroId, t.persona.ciuExpedDoc),
          Empresa(
            t.nombreEmpresa, t.domicilioEmp,
            Escritura(t.nroEscritura, t.fechaOtorgam,
              Notaria(t.nroNotaria, t.circuloNotarial)
            )
          )
        )
      }yield constructor
    }
  }

  implicit object FideicomisarioOps extends operacionesFideicomitentes[Fideicomisario] {
    override def crearFideicomitente(t: Fideicomisario): Either[MensajesDominio, Fideicomitente] = {
      for {
        adher <- Adherente(t.persona.nombre, t.persona.ciudad, t.persona.email,
          new DocId(TipoDoc(t.persona.tipoId), t.persona.nroId, t.persona.ciuExpedDoc),
          t.estadoCivil, new FormaPago(t.valorEncargoFiduciario, t.pagoInmediato, t.pagoEntrega)
        )
      }yield adher
    }
  }
}

trait Fideicom
case class Persona (
                     nombre: String,
                     ciudad: String,
                     email: String,
                     //DocId
                     nroId: String,
                     tipoId: String,
                     ciuExpedDoc: String,
                   )
case class Fideicomit (
                         persona: Persona,
                         nombreEmpresa: String,
                         domicilioEmp: String,
                         nroEscritura: String,
                         fechaOtorgam: Calendar,
                         nroNotaria: String,
                         circuloNotarial: String
                       )extends Fideicom

case class Fideicomisario (
                       persona: Persona,
                       estadoCivil: EstadoCivil,
                       valorEncargoFiduciario: Double,
                       pagoInmediato: Double,
                       pagoEntrega: Double
                     )extends Fideicom
