
import org.apache.spark.sql._
import scala.language.postfixOps

object DiscoveryToRefinedOneJob {

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
    val data = Data(args)
    val spark =  getSparkSession(args.master, "my spark job")


    val refined = spark.sql(args.queryRefined.getOrElse(data.defaultQuery))

    //refined.write.saveAsTable("refined.call")
    refined.write.mode("overwrite").parquet(args.refinedDir.get)
    spark.close


  }

}