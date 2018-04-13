package dominio

case class Empresa (nombre: Nombre, domicilio: Ciudad, numeroEscritura: Escritura) {

}

case class Escritura (numero: NumeroEscritura, fechaOtorgamiento: Fecha, notaria: Notaria)

case class Notaria (numero: NumeroNotaria, circuloNotarial: Ciudad)

case class NumeroEscritura()

case class Fecha()

case class NumeroNotaria()

//NumeroNotaria se ingreasa en letras porque enelgunos lugares es NOTARIA UNICA
