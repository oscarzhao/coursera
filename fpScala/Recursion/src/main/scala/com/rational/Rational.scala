package com.rational

object Rational {
  def main(args: Array[String]): Unit = {
    val x = new Rational(1, 2)
    val y = new Rational(2, 3)

    println(x.add(y)) // expect 7/6
    println(x.subtract(y)) // expect -1/6
    println(x.multiply(y)) // expect 1/3(not 2/6)
    println(x.divide(y)) // expect 3/4
  }
}

class Rational(x: Int, y:Int) {
  private def gcd(a: Int, b: Int): Int = if(b==0) a else gcd(b, a%b)
  private val g = gcd(x, y)
  def numer: Int = x/g
  def denom: Int = y/g

  // 构造函数重载
  def this(x: Int) = this(x, 1)

  def add(that: Rational): Rational = {
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  }

  def subtract(that: Rational): Rational = {
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )
  }

  def multiply(that: Rational): Rational = {
    new Rational(
      numer * that.numer,
      denom * that.denom
    )
  }

  def divide(that: Rational): Rational = {
    new Rational(
      numer * that.denom,
      denom * that.numer
    )
  }

  def neg(): Rational = new Rational(-numer, denom)

  override def toString: String = numer + "/" + denom
}