# Lazy Evaluation

惰性求值

# Infinite List (无限长度数组)

```
def from(n: Int): Stream[Int] = n #:: from(n+1)
```

## Pattern Matching

模式匹配时，`isDefinedAt` 只会检查最外层的 case 条件，并不检查里层

```
val g: PartialFunction[List[Int], String] = {
  case Nil => "one"
  case x::rest => 
    rest match {
      case Nil => "two"
    }
}

g.isDefinedAt(List(1,2,3))  // true
```

## Scala Collections 类的层级

Scala [`Seq`](https://docs.scala-lang.org/overviews/collections/seqs.html) 介绍如下：

```
Trait Seq has two subtraits LinearSeq, and IndexedSeq. These do not add any new operations, but each offers different performance characteristics: A linear sequence has efficient head and tail operations, whereas an indexed sequence has efficient apply, length, and (if mutable) update operations. Frequently used linear sequences are scala.collection.immutable.List and scala.collection.immutable.Stream. Frequently used indexed sequences are scala.Array and scala.collection.mutable.ArrayBuffer. The Vector class provides an interesting compromise between indexed and linear access. It has both effectively constant time indexing overhead and constant time linear access overhead. Because of this, vectors are a good foundation for mixed access patterns where both indexed and linear accesses are used. You’ll learn more on vectors later.
```

Scala 类的层级如下

```
Iterable {
  Seq {
    IndexedSeq
    LinearSeq
  }
  Set
  Map
}
```

## for-loop用法

for循环用法，一些时候比 map flatmap 等更简单，比如下面这个双层for-loop：

```
for {
  i <- 1 until n
  j <- 1 until i
  if isPrime(i+j)
} yield (i, j)
```

下面，我描述一下for-loop 和 map 操作的映射关系。

1. 一层 for loop 是这样的：

```{scala}
for (x <- xs) yield e2
// ==
xs.map(x => e2)
```

2.  一层 for loop + 过滤器

```{scala}
for (x <- xs if fn(x)) yield e2
// ==
xs.filter(fn).map(x => e2)
// ==
for (x <- xs.withFilter(fn(_))) yield e2
```

3. 两层 for loop

 ```{scala}
for (x <- xs; y <- ys; fn(x, y)) yield fn2(x, y)
// == 
xs.flatMap(x => 
  ys.filter(fn(x, _)).map(fn2(x, _))
)
```
