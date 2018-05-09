package dominio

case class PatrimonioAutonomo (identificacion: String, inmueble: Inmueble,
                               recursos: Double)

case class Inmueble (descripcionLinderos: String, folioMatricula: String,
                     ciudadRegistro: Option[Ciudad])

case class Ciudad(value: String)

object Ciudad {
  def apply(value: String): Option[Ciudad] =
    "^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\\s]*)+$".r.findFirstIn(value).map(new Ciudad(_))
}


