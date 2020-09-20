# Partitioner

Partitioner 是一种抽象，它定义了 `RDD[(key, value)]` 中的元素按照key分区的规则。
Partitioner 将 key 映射到 partition ID (范围是 0 到 numPartitons-1)。
Partitoner 保证了特定key的所有记录存放到同一个partition里。

可用的 Partitioner 有：

1. HashPartitioner

```scala
/**
  * A [[org.apache.spark.Partitioner]] that implements hash-based partitioning using
  * Java's `Object.hashCode`.
  *
  * Java arrays have hashCodes that are based on the arrays' identities rather than their contents,
  * so attempting to partition an RDD[Array[_]] or RDD[(Array[_], _)] using a HashPartitioner will
  * produce an unexpected or incorrect result.
  */
class HashPartitioner(partitions: Int) extends Partitioner {
  def getPartition(key: Any): Int = key match {
    case null => 0
    case _ => Utils.nonNegativeMod(key.hashCode, numPartitions)
  }

  // ...
}
```

2. RangePartitioner 

```scala
/**
 * A [[org.apache.spark.Partitioner]] that partitions sortable records by range into roughly
 * equal ranges. The ranges are determined by sampling the content of the RDD passed in.
 *
 * @note The actual number of partitions created by the RangePartitioner might not be the same
 * as the `partitions` parameter, in the case where the number of sampled records is less than
 * the value of `partitions`.
 */
class RangePartitioner[K : Ordering : ClassTag, V](
    partitions: Int,
    rdd: RDD[_ <: Product2[K, V]],
    private var ascending: Boolean = true,
    val samplePointsPerPartitionHint: Int = 20)
  extends Partitioner {
    // ...
```
