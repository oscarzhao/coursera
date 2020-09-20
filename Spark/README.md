# Apache Spark

## 常见问题

1. partition个数少，导致并法度不够，任务执行慢
2. 内存占用过高(涉及到排序和大key，大key可能导致OOM)
  2.1 shuffle to hdfs
  2.2 增加reduce机器内存
3. shuffle 的数据量过大
4. 标准库


## Partition tuning

1. 并法度过低
2. 容易导致数据倾斜
3. Increased memory pressure for groupBy, reduceBy,sortByKey, etc


## 内存问题

### 诊断：

- set spark.executor.extraJavaOptions，包含：
  1. -XX:+PrintGCDetails
  2. -XX:+HeapDumpOnOutOfMemoryError
- Check dmesg for oom-killer logs

### 解决方法

- 更改一些参数

```
set spark.shuffle.hdfs.enabled=true;
set mapreduce.job.reducer.unconditional-preempt.delay.sec=-1;
set mapreduce.reduce.memory.mb=4096;
set mapreduce.job.reduces=2048;
set spark.sql.adaptive.maxNumPostShufflePartitions=10000;
set spark.executor.memory=12G;
set spark.sql.adaptiveBroadcastJoinThreshold=1073741824;
```