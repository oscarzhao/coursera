package com.listT

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Nil[T] extends List[T] {
  override def isEmpty: Boolean = true
  override def head: T = throw new NoSuchElementException("Nil.head")
  override def tail: List[T] = throw new NoSuchElementException("Nil.tail")
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false
}

object ListFun {
  def elem[T](n: Int, l: List[T]): T = {
    if (l.isEmpty || n < 0) throw new IndexOutOfBoundsException("elem, idx="+n)
    else if (n==0) l.head
    else elem(n-1, l.tail)
  }
}