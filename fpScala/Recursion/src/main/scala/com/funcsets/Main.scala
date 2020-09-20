package com.funcsets

object Main {
  def main(args: Array[String]): Unit = {
    import FunSets._
    println(contains(singletonSet(1), 1))

    val s2 = union(singletonSet(1), singletonSet(2))
    println(contains(s2, 4))
  }
}
