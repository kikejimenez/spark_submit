import org.scalatest.FunSuite

class ArgsTests extends FunSuite{
  test("Arguments"){
    val args = Args(Array("master=local", "parentDirectory=discovery","tableName=skill"))
    assert(args.master.get=="local")
    assert(args.parentDirectory.get=="discovery")
    assert(args.tableName.get=="skill")
  }

}
