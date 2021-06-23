# spark_submit_cloudera


### Command
```shell
spark-submit --class "SourceToRefined" \
             --master "yarn" \
             --conf "spark.yarn.access.hadoopFileSystems=s3a://sibatel/cdp-data/warehouse" \
             --files "/etc/hadoop/conf/core-site.xml,/etc/hive/conf/hive-site.xml" \
             --conf "spark.sql.warehouse.dir=s3a://sibatel/cdp-data/warehouse" \
             --conf "spark.sql.catalogImplementation=hive" \
             --deploy-mode cluster discoToRef_v_1.jar \
               discoveryDir=s3a://sibatel/cdp-data/warehouse/tablespace/managed/hive/discovery.db/call/* \
               refinedDir=s3a://sibatel/cdp-data/warehouse/tablespace/external/hive/refined.db/call \
               configFile=s3a://sibatel/cdp-data/resources/enrique/spark/column_keys/call.json 
```