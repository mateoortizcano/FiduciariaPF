package dominio

case class FideicomitenteConstructor (nombre: Nombre, ciudadResidencia: Ciudad,
                                      cedulaCiudadania: Cedula, ciudadExpedicion: Ciudad,
                                      empresaRepresentada: Empresa, correo: Email)

case class Nombre(value: String) extends  AnyVal

object Nombre extends {
  def apply(value: String): Option[Nombre] = {
    "^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\\s]*)+$".r.findFirstIn(value).map(new Nombre(_))
  }
}

case class Ciudad(value: String) extends AnyVal

object Ciudad {
  def apply(value: String): Option[Ciudad] =
    "^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\\s]*)+$".r.findFirstIn(value).map(new Ciudad(_))
}

case class Cedula(value: Int) extends AnyVal

object Cedula {
  def apply(value: Int): Cedula= new Cedula(value)
}

case class Email(value: String) extends AnyVal

object Email {
  def apply(value: String): Option[Email] =
    "^[\\w]+@{1}[\\w]+\\.[a-z]{2,3}$".r.findFirstIn(value).map(new Email(_))
}


