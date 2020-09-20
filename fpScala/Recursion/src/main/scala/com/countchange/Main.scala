package com.countchange

object Main {
  def main(args: Array[String]): Unit = {
    val money = args(0).toInt
    val coins = args(1).split(",").map(s => s.toInt).sorted.toList
    println(countChange(money, coins))
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money==0 || coins.isEmpty) 0
    else {
      val tailMap = coins.zip(coins.tails.toList.map(_.tails.toList.map(_.toList))).toMap
      def loops(money: Int, available_coins: List[Int]): Int =
        if (available_coins.isEmpty) 0
        else if (money==available_coins.head) 1
        else if (money < available_coins.head) 0
        else {
          tailMap(available_coins.head).map((items: List[Int]) => loops(money - available_coins.head, items)).sum
        }
      coins.tails.toList.map((items: List[Int]) => loops(money, items)).sum
    }
  }
}
