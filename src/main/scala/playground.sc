
import ujson.Value.Value

import scala.collection.mutable.ArrayBuffer
import scala.language.postfixOps
import scala.sys.process._

case class Data(configFile: String) {

  var jsonString =""
  try {
    jsonString = s"hdfs dfs -cat ${configFile}" !!;
  } catch{
    case _:
      Throwable => jsonString = s"cat ${configFile}" !!;
  }
  val data: Value = ujson.read(jsonString)
  val dataArr: ArrayBuffer[Value] = data.arr

  def print(): Unit = {
    dataArr.foreach(println)
  }


  def call_query(discoveryPath: String): String = {
    s"""
      | SELECT contact_id,  master_contact_id,
      |       media_name, point_of_contact_name,
      |       ani_dial_num, skill_no, skill_name,
      |       campaign_no, campaign_name, agent_no,
      |       agent_name, team_no, team_name, sla,
      |       start_date, start_time, prequeue,
      |       inqueue, agent_time, postqueue,
      |       total_time, abandon_time, abandon,
      |       record_date, csv_filename
      |  FROM (
      |      SELECT *, max(record_date) OVER (PARTITION BY contact_id) AS max_record_date
      |        FROM  parquet.`${discoveryPath}`)s
      |  WHERE s.max_record_date = s.record_date
      |""".stripMargin
  }

  def skill_query(discoveryPath: String): String = {
    """
      | SELECT *
      |  FROM (
      |      SELECT *, max(record_date) OVER (PARTITION BY contact_id) AS max_record_date
      |        FROM  parquet.`${discoveryPath}`)s
      |  WHERE s.max_record_date = s.record_date
      |""".stripMargin
  }

  def matchNames(): Unit = {
     dataArr.foreach(x =>
       x("tableName").str match {
      case "call" => println(call_query(x("discoveryPath").str))
      case "skill" => println(skill_query(x("discoveryPath").str))
      case _ => println("Nothing else")
    }
     )
  }
}


val configFile = s"${(os.pwd/"cloudera"/"intellij"/"discoToRefCloudera"/"src"/"test"/"data").toString + "/TableTestLocal.json"}"

val data =  Data(configFile)

//data.print()

data.matchNames()