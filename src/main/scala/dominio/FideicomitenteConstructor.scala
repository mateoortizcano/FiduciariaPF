package dominio

case class FideicomitenteConstructor (nombre: Nombre, ciudadResidencia: Ciudad,
                                      cedulaCiudadania: Cedula, ciudadExpedicion: Ciudad,
                                      empresaRepresentada: Empresa, correo: Email)

case class Email(value: String) extends AnyVal

object Email {
  def apply(value: String): Option[Email] =
    "^[\\w]+@{1}[\\w]+\\.[a-z]{2,3}$".r.findFirstIn(value).map(new Email(_))
}


