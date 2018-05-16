package dominio

case class PatrimonioAutonomo (identificacion: String, inmueble: Inmueble,
                               recursos: Double)

object PatrimonioAutonomo {
  def apply(identificacion: String, inmueble: Inmueble,
            recursos: Double): Either[MensajesDominio, PatrimonioAutonomo] =
    for {
      inm <- Inmueble(inmueble.descripcionLinderos, inmueble.folioMatricula, inmueble.ciudadRegistro)
    }yield new PatrimonioAutonomo(identificacion, inm, recursos)
}

case class Inmueble (descripcionLinderos: String, folioMatricula: String,
                     ciudadRegistro: String)

object Inmueble {
  def apply(descripcionLinderos: String, folioMatricula: String,
            ciudadRegistro: String): Either[MensajesDominio, Inmueble] =
    for {
      ciudad <- Ciudad.validar(ciudadRegistro)
    }yield new Inmueble(descripcionLinderos, folioMatricula, ciudad)
}

object Ciudad {
  def validar(value: String): Either[MensajesDominio, String] =
    if (value.matches("^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\\s]*)+$"))
      Right(value)
    else
      Left(NombreInvalido())
}


