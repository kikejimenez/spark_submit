import ujson.Value.Value

import scala.language.postfixOps
import scala.sys.process._

case class Data(args: Args) {

  var jsonString =""
  try {
    jsonString = s"hdfs dfs -cat ${args.configFile.get}" !!;
  } catch{
    case _:
      Throwable => jsonString = s"cat ${args.configFile.get}" !!;
  }
  val data: Value = ujson.read(jsonString)

  val uniqueKeys: String = data("uniqueKeys").str
  val nonUniqueKeys: String = data("nonUniqueKeys").str

  val defaultQuery: String = s"""
         SELECT ${uniqueKeys}, ${nonUniqueKeys}
         FROM (
                SELECT DISTINCT(${uniqueKeys.trim()}) AS distinct_id,
                  ${uniqueKeys}, ${nonUniqueKeys}
                  FROM  parquet.`${args.discoveryDir.get}`
         )
         """.trim();
}
