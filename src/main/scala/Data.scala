import ujson.Value.Value

import scala.language.postfixOps
import scala.sys.process._

case class Data(directory:String,TableName:String) {
  var jsonString =""
  try {
    jsonString = s"hdfs dfs -cat ${directory}/column_keys/${TableName}.json" !!;
  } catch{
    case _:
      Throwable => jsonString = s"cat ${directory}/column_keys/${TableName}.json" !!;
  }
  val data: Value = ujson.read(jsonString)

  val uniqueKeys: String = data("uniqueKeys").str
  val nonUniqueKeys: String = data("nonUniqueKeys").str

  val discoveryParquet: String = directory + s"/discovery/${TableName}"
  val refinedParquet: String =  directory + s"/refined/${TableName}"

  val defaultQuery: String = s"""
         SELECT ${uniqueKeys}, ${nonUniqueKeys}
         FROM (
                SELECT DISTINCT(${uniqueKeys.trim()}) AS distinct_id,
                  ${uniqueKeys}, ${nonUniqueKeys}
                  FROM  parquet.`${discoveryParquet}`
         )
         """.trim();

}
