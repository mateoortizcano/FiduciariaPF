package dominio

import java.util.Calendar

case class Empresa (nombre: String, domicilio: String, numeroEscritura: Escritura)

case class Escritura (numero: String, fechaOtorgamiento: Calendar, notaria: Notaria)

case class Notaria (numero: String, circuloNotarial: String)