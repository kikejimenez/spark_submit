import org.scalatest.FunSuite

class ArgsTests extends FunSuite{
  test("Arguments"){
    val args = Args(Array("master=local", "discoveryDir=discovery","refinedDir=refined","configFile=config"))
    assert(args.master.get=="local")
    assert(args.discoveryDir.get=="discovery")
    assert(args.refinedDir.get=="refined")
    assert(args.configFile.get=="config")
    assert(args.queryRefined.getOrElse("") == "")
  }

}
