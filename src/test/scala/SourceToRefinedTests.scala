import org.scalatest.FunSuite

class SourceToRefinedTests extends FunSuite{
  val sourceToRefined =  SourceToRefined
  val discoveryDir = s"discoveryDir=${(os.pwd/"src"/"test"/"data"/"discovery"/"skill").toString}"
  val refinedDir = s"refinedDir=${(os.pwd/"src"/"test"/"data"/"refined"/"skill").toString}"
  val configFile = s"configFile=${(os.pwd/"src"/"test"/"data").toString + "/skill.json"}"
  val args= Array(s"master=local[*]",discoveryDir,refinedDir,configFile)
  //sourceToRefined.main(args)
  //os.remove.all(parentDirectory/"refined"/"skill")
}
