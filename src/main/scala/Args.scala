
case class Args(args: Array[String]) {
  val nArgs: Map[String, String] = args.map(x=>x.split("=")).map(y=>(y(0),y(1))).toMap
  val master: Option[String] = nArgs.get("master")
  val discoveryDir: Option[String] = nArgs.get("discoveryDir")
  val refinedDir: Option[String] = nArgs.get("refinedDir")
  val configFile: Option[String] = nArgs.get("configFile")
  val queryRefined: Option[String] = nArgs.get("queryRefined")
}