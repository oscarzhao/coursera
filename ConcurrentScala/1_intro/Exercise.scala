package main

object Exercise {

  // This method must return a function h, which is the composition of the functions f and g
  def compose[A, B, C](g: B=>C, f: A=>B): A=>C = 
    (x: A) => g(f(x))

  def fuse[A, B](a: Option[A], b: Option[B]): Option[(A, B)] = 
    a match {
      case Some(x) => 
        b match {
          case Some(y) => Some((x, y))
          case None => None
        }
      case None => None
    }

  // The method must return true if and only if the pred function returns true for all the values in xs without throwing an exception. Use the check method as follows:
  def check[T](xs: Seq[T])(pred: T => Boolean): Boolean = 
    xs.map(pred(_)).reduce((x, y) => x && y)


  def main(args: Array[String]): Unit = {
    println(compose[Int, Double, Double](_*2, _+1.1)(1))

    println(fuse[Int, Boolean](Some(1), None))
    println(fuse[Int, Boolean](Some(1), Some(true)))

    // check check
    println(check(1 until 10)(40 / _ > 0))
  }
}

