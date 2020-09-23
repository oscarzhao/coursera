# Spark SQL 调试

```
cd output/spark-stable
rm -rf yarn; cp -r ~/spark/libexec/yarn yarn  
rm -rf conf; cp -r ~/spark/libexec/conf conf  

export SPARK_HOME=. && ./bin/spark-shell
```


```sql
spark.sql("show tables").show()

spark.sql("create table ad_log (did STRING, age INT) using PARQUET")

spark.sql("""
insert into ad_log
select cast(did as STRING) as did, did%3 from (select array(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60) as did_list) t lateral view explode(t.did_list) did_list_t as did
""")

spark.sql("select count(*) from ad_log").show()
spark.sql("select * from ad_log").collect()
```
