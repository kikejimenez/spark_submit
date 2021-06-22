name := "discoToRefCloudera"

version := "0.1"

scalaVersion := "2.11.12"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql"  %  "2.4.0" ,
  "org.apache.spark" %% "spark-core"  %  "2.4.0" ,
  "org.apache.hive" % "spark-client" % "2.3.8",
  "com.lihaoyi" %% "upickle"  % "0.7.4",
  "com.lihaoyi" %% "os-lib" % "0.2.9",
  "com.typesafe.play" %% "play-json" %   "2.7.4",
 "org.scalatest" %% "scalatest" % "2.2.4" % Test,
"com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.11.4"
)


assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  //To add Kafka as source
  case "META-INF/services/org.apache.spark.sql.sources.DataSourceRegister" =>
    MergeStrategy.concat
  case x => MergeStrategy.first
}