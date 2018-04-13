package dominio

case class PatrimonioAutonomo (identificacion: Identificacion, inmueble: Inmueble,
                               recursos: Recurso)

case class Inmueble (descripcionLinderos: String, folioMatricula: NumeroFolio,
                     OficRegisInstrumentos: Ciudad)

case class Identificacion()

case class Recurso()

case class NumeroFolio()


