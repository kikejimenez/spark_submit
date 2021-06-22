import org.apache.spark.sql.SparkSession

class DiscoveryToRefinedManyJobs {

  def main(cliArgs: Array[String]): Unit = {
    def getSparkSession(
                         master: Option[String],
                         appName: String
                       ): SparkSession = {
      val builder =
        SparkSession.builder
          .appName(appName)
      master match {
        case Some(m) => builder.master(m).getOrCreate
        case _ => builder.getOrCreate
      }
    }

    val args  = Args(cliArgs)
    val data = Data(args.configFile.get)
    val spark =  getSparkSession(args.master, "my spark job")

    data.dataArr.par.foreach(x =>
        x("tableName").str match {
          case "call" =>  {val call_refined = spark.sql(data.call_query(x))
            call_refined.write.mode("overwrite").parquet(x("refinedPath").str)
          }
          case "skill" =>  {val skill_refined = spark.sql(data.skill_query(x))
            skill_refined.write.mode("overwrite").parquet(x("refinedPath").str)
          }
          case _ => println("Nothing else")
        }
      )

    spark.close


  }

}