package com.intsets

object Main {
  def main(args: Array[String]): Unit = {
    val s1 = new NonEmpty(5, Empty, Empty)
    val s2 = s1.incl(1).incl(9)
    println("s2 contains 9: " + s2.contains(9))
    println("s2 doesn't contains 10: " + s2.contains(10))
  }
}
