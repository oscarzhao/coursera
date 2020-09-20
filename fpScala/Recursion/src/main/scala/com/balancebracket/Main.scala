package com.balancebracket

object Main {
  def main(args: Array[String]): Unit = {
    println(balance(args(0).toList))
  }

  def balance(chars: List[Char]): Boolean = {
    def loop(counting: Int, l: List[Char]): Boolean =
      if (l.isEmpty) counting == 0
      else l.head match {
        case ')' =>
          if (counting==0) false
          else loop(counting-1, l.tail)
        case '(' => loop(counting+1, l.tail)
        case _ => loop(counting, l.tail)
      }
    loop(0, chars)
  }
}


/*
  run :-)   => false
  run ())(  => false
  run ()    => true
 */