package com.intsets

import org.scalatest.FunSuite

class IntSetTest extends FunSuite {
  test("IntSetTest.contain") {
    val s = new NonEmpty(5, Empty, Empty)
    assert(s.contains(5), "should contain 5")
    assert(!s.contains(6), "should NOT contain 6")
  }

  test("IntSetTest.union") {
    val s1 = new NonEmpty(5, Empty, Empty)
    val s2 = new NonEmpty(10, new NonEmpty(1, Empty, Empty), new NonEmpty(100, Empty, Empty))
    val s3 = s1.union(s2).union(s2)

    assert(s3.contains(1), "should contain 1")
    assert(s3.contains(5), "should contain 5")
    assert(s3.contains(10), "should contain 10")
    assert(s3.contains(100), "should contain 100")
    assert(!s3.contains(0), "should NOT contain 0")
    assert(!s3.contains(99), "should NOT contain 99")
  }
}
