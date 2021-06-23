# spark_submit_cloudera

## Command 

```shell
spark-submit --class "SourceToRefinedManyJobs" \
             --master "yarn" \
             --conf "spark.yarn.access.hadoopFileSystems=s3a://sibatel/cdp-data/warehouse" \
             --files "/etc/hadoop/conf/core-site.xml,/etc/hive/conf/hive-site.xml" \
             --conf "spark.sql.warehouse.dir=s3a://sibatel/cdp-data/warehouse" \
             --conf "spark.sql.catalogImplementation=hive" \
             --deploy-mode cluster discoveryToRefined.jar \
               configFile=s3a://sibatel/cdp-data/resources/enrique/dynamic/spark/many_jobs_config.json
```
