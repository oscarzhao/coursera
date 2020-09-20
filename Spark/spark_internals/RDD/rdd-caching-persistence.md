# RDD Caching and Persistence

Caching 或 Persistence 是Spark计算中常用的优化技巧。在计算执行过程中，部分运算中间结果会被缓存起来便于后续的stage重复使用。这些中间结果作为RDD被缓存到内存或其他存储介质中。

我们可以通过 `cache` 算子缓存RDD，也可以通过 `persist` 算子持久化。

`cache` 只是 `persist` 的语法糖，`cache` 等价于 `persist(MEMORY_ONLY)` 或 `persist`，其定义是：

```scala
/**
  * Persist this RDD with the default storage level (`MEMORY_ONLY`).
  */
def persist(): this.type = persist(StorageLevel.MEMORY_ONLY)

/**
  * Persist this RDD with the default storage level (`MEMORY_ONLY`).
  */
def cache(): this.type = persist()
```

当RDD初次被标记为 persistent时，该RDD会被注册到 `ContextCleaner` 和 `SparkContext`
