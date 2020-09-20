# Shuffling

Shuffle 是指Spark内数据分布的重新划分，在数据跨分区进行分组时发生。它涉及到数据在 executor 和 机器之间的数据拷贝，所以shuffle操作非常复杂且耗时。

## 一、背景介绍

todo 待补充，源 https://spark.apache.org/docs/3.0.0/rdd-programming-guide.html#shuffle-operations


能够触发 shuffle 的算子有：

* repartition 类：repartition, coalesce
* byKey 类：groupByKey, reduceByKey
* join 类：cogroup, join

## 二、注意事项

### 2.1 尽量避免使用 groupByKey，使用 reduceByKey/combineByKey。

`groupByKey` 的定义如下：

```scala
  /**
   * Group the values for each key in the RDD into a single sequence. Allows controlling the
   * partitioning of the resulting key-value pair RDD by passing a Partitioner.
   * The ordering of elements within each group is not guaranteed, and may even differ
   * each time the resulting RDD is evaluated.
   *
   * @note This operation may be very expensive. If you are grouping in order to perform an
   * aggregation (such as a sum or average) over each key, using `PairRDDFunctions.aggregateByKey`
   * or `PairRDDFunctions.reduceByKey` will provide much better performance.
   *
   * @note As currently implemented, groupByKey must be able to hold all the key-value pairs for any
   * key in memory. If a key has too many values, it can result in an `OutOfMemoryError`.
   */
  def groupByKey(partitioner: Partitioner): RDD[(K, Iterable[V])] = self.withScope {
    // ...
  }
```

`reduceByKey` 的定义是：

```scala
  /**
   * Merge the values for each key using an associative and commutative reduce function. This will
   * also perform the merging locally on each mapper before sending results to a reducer, similarly
   * to a "combiner" in MapReduce.
   */
  def reduceByKey(partitioner: Partitioner, func: (V, V) => V): RDD[(K, V)] = self.withScope {
    combineByKeyWithClassTag[V]((v: V) => v, func, func, partitioner)
  }
```

### 2.2 一个 join 的例子

`PairRDD` 支持 [join](http://people.apache.org/~pwendell/spark-nightly/spark-master-docs/latest/programming-guide.html#JoinLink) 操作，其定义是：

```
When called on datasets of type (K, V) and (K, W), returns a dataset of (K, (V, W)) pairs with all pairs of elements for each key.
```

我们看一个例子：

```scala
scala> val kv = (0 to 5) zip Stream.continually(5)
kv: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((0,5), (1,5), (2,5), (3,5), (4,5), (5,5))

scala> val kw  = (0 to 5) zip Stream.continually(10)
kw: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((0,10), (1,10), (2,10), (3,10), (4,10), (5,10))

scala> val kvR = sc.parallelize(kv)
kvR: org.apache.spark.rdd.RDD[(Int, Int)] = ParallelCollectionRDD[3] at parallelize at <console>:26

scala> val kwR = sc.parallelize(kw)
kwR: org.apache.spark.rdd.RDD[(Int, Int)] = ParallelCollectionRDD[4] at parallelize at <console>:26

scala> val joined = kvR join kwR
joined: org.apache.spark.rdd.RDD[(Int, (Int, Int))] = MapPartitionsRDD[10] at join at <console>:32

scala> joined.toDebugString
res7: String =
(8) MapPartitionsRDD[10] at join at <console>:32 []
 |  MapPartitionsRDD[9] at join at <console>:32 []
 |  CoGroupedRDD[8] at join at <console>:32 []
 +-(8) ParallelCollectionRDD[3] at parallelize at <console>:26 []
 +-(8) ParallelCollectionRDD[4] at parallelize at <console>:26 []
```


关于 transformation 算子的完整列表参考 [RDD Programming Guide: Transformations](http://people.apache.org/~pwendell/spark-nightly/spark-master-docs/latest/rdd-programming-guide.html#transformations)
