# Scala

## Tail Recursion (尾递归)

Scala 对尾递归专门做了优化，使用的话，参考下面的用法

```{scala}
import scala.annotation.tailrec

object Hello extends App {
  @tailrec
  def gcd(a: Int, b: Int) :Int = if (b==0) a else gcd(b, a%b)

  def factorial(n: Int): Int = {
    @tailrec
    def loop(acc: Int, n: Int): Int =
      if (n==0) acc
      else loop(acc*n, n-1)
    loop(1, n)
  }

  println(gcd(16, 8))
  println(factorial(10))  // 3628800
```

## Pascal's Triangle

获取 Pascal's Triangle 在特定位置的值

```{scala}
object PascalTriangle {
  def main(args: Array[String]): Unit = {
    val col = args(0).toInt
    val row = args(1).toInt
    println(getVal(col, row))
  }

  def getVal(col: Int, row: Int): Int = {
    if (col == 0) 1
    else if (row == 0) 1
    else getVal(row-1, col) + getVal(row, col-1)
  }
}
```