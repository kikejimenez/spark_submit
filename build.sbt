name := "discoToRefCloudera"

version := "0.1"

scalaVersion := "2.11.12"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql"  %  "2.4.0" ,
  "org.apache.spark" %% "spark-core"  %  "2.4.0" ,
  "com.lihaoyi" %% "upickle"  % "0.7.4",
  "com.lihaoyi" %% "os-lib" % "0.2.9",
 "org.scalatest" %% "scalatest" % "2.2.4" % Test
)


assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  //To add Kafka as source
  case "META-INF/services/org.apache.spark.sql.sources.DataSourceRegister" =>
    MergeStrategy.concat
  case x => MergeStrategy.first
}