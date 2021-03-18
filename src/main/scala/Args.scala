
case class Args(args: Array[String]) {
  val nArgs: Map[String, String] = args.map(x=>x.split("=")).map(y=>(y(0),y(1))).toMap
  val master: Option[String] = nArgs.get("master")
  val parentDirectory: Option[String] = nArgs.get("parentDirectory")
  val tableName: Option[String] = nArgs.get("tableName")
  val queryRefined: Option[String] = nArgs.get("queryRefined")
}