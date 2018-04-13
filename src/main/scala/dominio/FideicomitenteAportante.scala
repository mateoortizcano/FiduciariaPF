package dominio

case class FideicomitenteAportante (nombre: Nombre, ciudadResidencia: Ciudad,
                               cedulaCiudadania: Cedula, ciudadExpedicion: Ciudad,
                               empresaRepresentada: Empresa, correo: Email)
//el aportante puede ser una persona natural o jurídica, podemos hacer producto o coproducto
//si es juridica hay que agregarle la empresa a la cual respresenta, si es natural no.