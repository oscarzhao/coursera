package com.pascaltriangle

object Main {
  def main(args: Array[String]): Unit = {
    val col = args(0).toInt
    val row = args(1).toInt
    println(pascal(col, row))
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0) 1
    else if (r == c) 1
    else pascal(c, r-1) + pascal(c-1, r-1)
  }

}

/*
  run 0 2 => 1
  run 1 2 => 2
  run 1 3 => 3  // 应该是 4
 */