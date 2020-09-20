package com.currying

import math.abs

object Currying {
  def main(args: Array[String]): Unit = {
    println(sum(x => x*x)(3, 4)) // 25
    println(product(x => x*x)(3, 4))  // 144
    println(fact(5)) // 120


    println(sqrt(0.1))
    println(sqrt(2))
    println(sqrt(4))
  }

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f)(a+1, b)
  }

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a+1, b)
  }

  def fact(n: Int): Int = product(identity)(1, n)

  def isCloseEnough(x:Double, y: Double): Boolean = {
    abs((x - y)/x/x) < 0.0001
  }

  def fixedPoint(f: Double => Double)(firstGuess: Double): Double = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  def averageDamp(f: Double => Double)(x: Double): Double = (x + f(x))/2

  def sqrt(x: Double): Double = {
    if(isCloseEnough(0, x)) x
    else fixedPoint(averageDamp((y: Double) => x / y))(1.0)
  }
}
