import org.scalatest.FunSuite

class SourceToRefinedTests extends FunSuite{
  val sourceToRefined =  SourceToRefined
  val parentDirectory = os.pwd/"src"/"test"/"data"
  val tableName = "skill"
  val args= Array(s"master=local[*]",s"parentDirectory=${parentDirectory.toString()}",s"tableName=${tableName}")
  sourceToRefined.main(args)
  //os.remove.all(parentDirectory/"refined"/"skill")
}
