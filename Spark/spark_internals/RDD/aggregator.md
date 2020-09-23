# Aggregator

**Aggregator** 是一个 case class，它包含一组聚合函数，`PairRDDFunctions.combineByKeyWithClassTag`使用这个类对数据执行聚合操作。

`Aggregator[K, V, C]` 通过三个函数将一个 `RDD[(K, V)]` 写入到 `RDD[(K, C)]`：

* `createCombiner: V => C`, 创建聚合操作的初始值
* `mergeValue: (C, V) => C`, 将新值写入聚合结果
* `mergeCombiners: (C, C) => C`, 合并多个 `mergeValue` 函数的返回值

`ShuffleDependency` 和 `ExternalSorter` 的构造函数均用到了 **Aggregator**类。

1.1 `combineValuesByKey` 方法

```scala
def combineValuesByKey(
    iter: Iterator[_ <: Product2[K, V]],
    context: TaskContext,
    partIsSorted: Int = 0)
  : Iterator[(K, C)] = {
  if (partIsSorted != 0) {
    new MultiWayAppendOnlyMapIterator[K, V, C](iter,
      createCombiner, mergeValue, mergeCombiners)
  } else {
    val combiners = new ExternalAppendOnlyMap[K, V, C](createCombiner, mergeValue, mergeCombiners)
    combiners.insertAll(iter)
    updateMetrics(context, combiners)
    combiners.iterator
  }
}
```

1.2 `combineCombinersByKey` 方法

```scala
def combineCombinersByKey(
    iter: Iterator[_ <: Product2[K, C]],
    context: TaskContext): Iterator[(K, C)] = {
  val combiners = new ExternalAppendOnlyMap[K, C, C](identity, mergeCombiners, mergeCombiners)
  combiners.insertAll(iter)
  updateMetrics(context, combiners)
  combiners.iterator
}
```

1.3 更新 Task Metrics

```scala
/** Update task metrics after populating the external map. */
private def updateMetrics(context: TaskContext, map: ExternalAppendOnlyMap[_, _, _]): Unit = {
  Option(context).foreach { c =>
    c.taskMetrics().incMemoryBytesSpilled(map.memoryBytesSpilled)
    c.taskMetrics().incDiskBytesSpilled(map.diskBytesSpilled)
    c.taskMetrics().incPeakExecutionMemory(map.peakMemoryUsedBytes)
    c.taskMetrics().incShuffleSpillTime(map.spillTime)
  }
}
```
