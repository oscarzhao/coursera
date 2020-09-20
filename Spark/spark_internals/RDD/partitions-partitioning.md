# Partitions and Partitioning

问题列表：

```
FIXME

How does the number of partitions map to the number of tasks? How to verify it?

How does the mapping between partitions and tasks correspond to data locality if any?
```

Spark通过分区管理数据，并在此基础上支持并行计算，并保证executor之间的数据传输尽可能少。
默认情况下，Spark会从临近的executor节点读取数据生成RDD。Spark 任务里的数据也是按照分区存储的，这样可以优化 transformation 算子的性能。

如果底层数据存储在 HDFS/Cassandra/S3 这类分布式存储上，Spark 会按照 1-1 创建分区。
对于HDFS而言，分区默认大小是64MB。


延伸阅读：

1. [How Many Partitions Does an RDD Have?](https://databricks.gitbooks.io/databricks-spark-knowledge-base/content/performance_optimization/how_many_partitions_does_an_rdd_have.html)
2. [Tuning Spark](https://spark.apache.org/docs/latest/tuning.html)

