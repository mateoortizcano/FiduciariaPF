package services

import java.util.Calendar

import dominio._

class OperacionesProyecto {
   def crearProyecto (infoProyecto: NuevoProyecto): Option[Proyecto[Creacion]] = {
     // para este for hacer las exceptiones en caso de que se ingresen valores que no corresponden
     // a los valores esperados o que no son validos como por ejemplo si son none
     for {
       nombre <- Nombre(infoProyecto.nombre)
       patrimonioAutonomo <- PatrimonioAutonomo(
         "PAO001",Inmueble( infoProyecto.descripcionLinderos, "000021345",
           Ciudad(infoProyecto.ciudadRegistroInmueble)
         ), 1754566.2658
       )
       equilibrio <- PuntoEquilibrio(Porcentaje(infoProyecto.porcentajeEquilibrio),
         Tiempo(infoProyecto.tiempoalcanceEquilibrio))
       excedente <- Excedentes(Porcentaje(infoProyecto.porcentajeExcedenteConstructores),
         Porcentaje(infoProyecto.porcentajeExcedenteAportantes))
       unidadesVivienda <- Unidad(infoProyecto.unidadesProyecto)
       precioUnidad <- Precio(infoProyecto.precioUnitario)
       presupuesto <- Presupuesto(infoProyecto.presupuesto)
       proyecto <- Proyecto[Creacion](
         nombre, patrimonioAutonomo, equilibrio, excedente, unidadesVivienda,
         precioUnidad, presupuesto
       )
     }yield proyecto
   }
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
