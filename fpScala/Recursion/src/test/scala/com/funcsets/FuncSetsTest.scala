package com.funcsets

import org.scalatest.FunSuite

import FunSets._

class FuncSetsTest extends FunSuite {
  test("FunSets.contain") {
    assert(contains(singletonSet(1), 1))
    assert(!contains(singletonSet(1), 2))
  }

  test("FunSets.union") {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s12 = union(s1, s2)
    assert(contains(s1, 1))
    assert(contains(s2, 2))
    assert(contains(s12, 1))
    assert(contains(s12, 2))
    assert(!contains(s12, 3))
  }

  test("FunSets.forall") {
    val s12 = union(singletonSet(1), singletonSet(2))
    val s34 = union(singletonSet(3), singletonSet(4))
    val s1234 = union(s12, s34)

    assert(forall(s1234, (x:Int) => x < 5), "ALL of them < 5")
    assert(!forall(s1234, (x:Int) => x < 0), "NOT all of them < 0 (actually none of them)")
    assert(!forall(s1234, (x:Int) => x > 3), "NOT all of them >3")
  }

  test("FunSets.exists") {
    val s12 = union(singletonSet(1), singletonSet(2))
    val s34 = union(singletonSet(3), singletonSet(4))
    val s1234 = union(s12, s34)

    assert(exists(s1234, (x:Int) => x < 5), "some of them < 5(actually all of them)")
    assert(!exists(s1234, (x:Int) => x < 0), "none of them < 0")
    assert(exists(s1234, (x:Int) => x > 3), "some of them >3")
  }

  test("FunSets.map") {
    val s12 = union(singletonSet(1), singletonSet(2))
    val s34 = union(singletonSet(3), singletonSet(4))
    val s1234 = union(s12, s34)

    val s5678 = map(s1234, (x: Int) => x + 4)

    assert(contains(s5678, 5), "contains 5")
    assert(contains(s5678, 6), "contains 6")
    assert(contains(s5678, 7), "contains 7")
    assert(contains(s5678, 8), "contains 8")

    assert(!exists(s5678, (x: Int) => x < 5), "not contain any element < 5")
    assert(!exists(s5678, (x: Int) => x > 8), "not contain any element > 8")
  }

  test("FunSets.toString") {
    val s12 = union(singletonSet(1), singletonSet(2))
    val s34 = union(singletonSet(3), singletonSet(4))
    val s1234 = union(s12, s34)

    val s5678 = map(s1234, (x: Int) => x + 4)

    assert(FunSets.toString(s12)=="{1,2}")
    assert(FunSets.toString(s34) == "{3,4}")
    assert(FunSets.toString(s5678) == "{5,6,7,8}")
  }
}
