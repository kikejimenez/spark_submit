import org.scalatest.FunSuite

class DataTests extends  FunSuite{
  test("Source To Refined Data Structure"){
    val configFile = s"configFile=${(os.pwd/"src"/"test"/"data").toString + "/TableTestLocal.json"}"
    val discoveryDir = s"discoveryDir=discovery"
    val args = Args(Array(discoveryDir,configFile))
    val data = Data(args)
    assert(data.uniqueKeys == "1uniqueKeyTest, 2uniqueKeyTest")
    assert(data.nonUniqueKeys == "nonUniqueKeyTest")
  }

}
