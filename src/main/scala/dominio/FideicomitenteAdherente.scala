package dominio

class FideicomitenteAdherente (nombre: Nombre, ciudadResidencia: Ciudad,
                               cedulaCiudadania: Cedula, ciudadExpedicion: Ciudad,
                               estadoCivil: EstadoCivil, formaPago: FormaPago, correo: Email)

case class FormaPago (valorEncargoFiduciario: Precio, pagoInmediato: Precio,
                      pagoEntrega: Precio)

case class EstadoCivil()

