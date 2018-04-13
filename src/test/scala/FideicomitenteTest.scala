import org.scalatest.FunSuite
import dominio.Ciudad

class FideicomitenteTest  extends FunSuite {
  test("CiudadTest") {
    assert(Ciudad.apply("dellin") === None)
    assert(Ciudad.apply("Mdellin") === Ciudad("Mdellin"))

  }
}
