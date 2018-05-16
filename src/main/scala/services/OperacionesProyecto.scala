package services

import java.util.Calendar

import dominio._

object operacionesProyecto {
   def crearProyecto (infoProyecto: NuevoProyecto): Either[MensajesDominio, Proyecto[Creacion]] = {
     for {
       patrimonioAutonomo <- PatrimonioAutonomo(
         "PAO001",new Inmueble(infoProyecto.descripcionLinderos, "000021345",
           infoProyecto.ciudadRegistroInmueble), 1754566.2 )
       equilibrio <- PuntoEquilibrio(infoProyecto.porcentajeEquilibrio,
         infoProyecto.tiempoalcanceEquilibrio)
       excedente <- Excedentes(infoProyecto.porcentajeExcedenteConstructores,
         infoProyecto.porcentajeExcedenteAportantes)
       proyecto <- Proyecto(infoProyecto.nombre, patrimonioAutonomo, equilibrio, excedente, infoProyecto.unidadesProyecto,
         infoProyecto.precioUnitario, infoProyecto.presupuesto
       )
     }yield proyecto
   }
  def eliminarProyecto = ???
  def modificarProyecto = ???
  def agregarFideicomiententes = ???
}
case class NuevoProyecto(
                        nombre: String,
                        descripcionLinderos: String,
                        folioMatriculaInmueble: String,
                        ciudadRegistroInmueble: String,
                        porcentajeEquilibrio: Double,
                        tiempoalcanceEquilibrio: Calendar,
                        porcentajeExcedenteConstructores: Double,
                        porcentajeExcedenteAportantes: Double,
                        unidadesProyecto: Int,
                        precioUnitario: Double,
                        presupuesto: Double
                        )
