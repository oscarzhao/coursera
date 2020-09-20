# RDD — Description of Distributed Computation

RDD is a description of a fault-tolerant and resilient computation over a possibly distributed collection of records (of type T).

## 一、RDD 约定

在 Spark 代码里，RDD是一个抽象类，定义了一组方法，但没有定义实现。所有具体RDD实现时，都要继承 RDD 类。RDD 类的定义如下：

```scala
abstract class RDD[T: ClassTag](
    @transient private var _sc: SparkContext,
    @transient private var deps: Seq[Dependency[_]]
  ) extends Serializable with Logging {
    // ... 省略
  }
```

1.1 分区计算 (TaskContext)

方法定义：

```
/**
  * :: DeveloperApi ::
  * Implemented by subclasses to compute a given partition.
  */
@DeveloperApi
def compute(split: Partition, context: TaskContext): Iterator[T]
```

`compute` 方法在 TaskContext 里对 `split` 分区执行计算，产出一个数据集，类型为 T。

Spark中任何类型的 RDD 都要实现 `compute` 方法，每次该RDD中的数据被请求时，都会被触发一次计算。特殊情况下，可以从外部存储读取，比如：
1. RDD 已经被 cache （需要指定 StorageLevel)
2. RDD 已经被 checkpoint

值得注意的是，`compute` 在 Spark driver 端执行，在 RDD 被请求去 `computeOrReadCheckpoint` 时被触发。

todo： computeOrReadCheckpoint 和真正执行时，有关系吗？如何联动的？

1.2 Partitions

```  
/**
  * Implemented by subclasses to return the set of partitions in this RDD. This method will only
  * be called once, so it is safe to implement a time-consuming computation in it.
  *
  * The partitions in this array must satisfy the following property:
  *   `rdd.partitions.zipWithIndex.forall { case (partition, index) => partition.index == index }`
  */
protected def getPartitions: Array[Partition]
  ```

1.3 Dependencies

```scala
/**
  * Implemented by subclasses to return how this RDD depends on parent RDDs. This method will only
  * be called once, so it is safe to implement a time-consuming computation in it.
  */
protected def getDependencies: Seq[Dependency[_]] = deps
```

1.4 Preferred Locations (Placement Preferences)

```scala
/**
  * Optionally overridden by subclasses to specify placement preferences.
  */
protected def getPreferredLocations(split: Partition): Seq[String] = Nil
```

1.5 Partitioner

```scala
/** Optionally overridden by subclasses to specify how they are partitioned. */
@transient val partitioner: Option[Partitioner] = None
```

## 二、常用的 RDD

* CoGroupedRDD: 将 parent rdds 按照key分组，value 是一个数组，包含了parent rdd 中对应 key 的值；
* CoalescedRDD: 用来支持 `repartition` 和 `coalesce` 运算；
* FileScanRDD:  扫描文件分区的rdd；
* HadoopRDD: 借助于 旧MapReduce API(`org.apache.hadoop.mapred`)读取 Hadoop 里的数据，比如 HDFS文件、HBase source 或 S3；
* MapPartitionsRDD: 用来支持map类操作的RDD，比如 `map`, `flatMap`, `filter`, `mapPartitions` 等；
* NewHadoopRDD: 借助于 新MapReduce API(`org.apache.hadoop.mapreduce`) 读取 Hadoop 里的数据，比如 HDFS文件、HBase source 或 S3；
* ParallelCollectionRDD: 将 Scala Collection 转化成一个 RDD，比如 `parallelize` 和 `makeRDD` 
* ShuffledRDD:  执行shuffle类操作后的结果，比如 `repartition`, `coalesce` （shuffle=True时）, `aggregateByKey`, `reduceByKey`, `groupByKey` 等

## 三、创建RDD实例

RDD 创建需要两个变量：
* SparkContext
* Parent RDDs，在触发当前RDD的计算之前，所有的 parent RDD必须计算完成。
