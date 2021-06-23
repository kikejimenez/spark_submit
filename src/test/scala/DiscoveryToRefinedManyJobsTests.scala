import org.scalatest.FunSuite

class DiscoveryToRefinedManyJobsTests extends FunSuite{
  test("Discovery To Refined Many Jobs"){
  val discoveryToRefined =  DiscoveryToRefinedManyJobs
  val configFile = s"configFile=${(os.pwd/"src"/"test"/"data").toString + "/TableTestLocal.json"}"
  println(configFile)
   val args= Array(s"master=local[*]",configFile)
  discoveryToRefined.main(args)
   // assert(true)
  //os.remove.all(os.pwd/"src"/"test"/"data"/"refined"/"skill")
}}

