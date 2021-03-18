import org.scalatest.FunSuite

class DataTests extends  FunSuite{
  test("Source To Refined Data Structure"){
    val wd = os.pwd.toString+ "/src/test/data"
    val data = Data(wd,"TableTest")
    assert(data.uniqueKeys == "1uniqueKeyTest, 2uniqueKeyTest")
    assert(data.nonUniqueKeys == "nonUniqueKeyTest")
  }

}
