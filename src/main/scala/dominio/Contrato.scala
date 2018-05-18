package dominio

case class Contrato (proyecto: Proyecto[Creacion], constructor: Constructor,
                     aportantes: List[Aportante])