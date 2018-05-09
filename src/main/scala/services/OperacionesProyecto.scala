package services

import java.util.Calendar

import dominio._

class OperacionesProyecto {
   def crearProyecto (infoProyecto: NuevoProyecto): Unit = {
     // para este for hacer las exceptiones en caso de que se ingresen valores que no corresponden
     // a los valores esperados o que no son validos como por ejemplo si son none
     for {
       patrimonioAutonomo <- PatrimonioAutonomo(
         "PAO001",Inmueble( infoProyecto.descripcionLinderos, "000021345",
           Ciudad(infoProyecto.ciudadRegistroInmueble)
         ), 1754566.2658
       )
       equilibrio <- PuntoEquilibrio(Porcentaje(infoProyecto.porcentajeEquilibrio),
         Tiempo(infoProyecto.tiempoalcanceEquilibrio))
       excedente <- Excedentes(Porcentaje(infoProyecto.porcentajeExcedenteConstructores),
         Porcentaje(infoProyecto.porcentajeExcedenteAportantes))
       proyecto <- Proyecto[Creacion](
         infoProyecto.nombre, patrimonioAutonomo, equilibrio, excedente, //continuar

       )
     }
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
