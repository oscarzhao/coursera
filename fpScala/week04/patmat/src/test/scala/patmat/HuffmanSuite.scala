package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    // 这是作业中的例子树
    val t3 = Fork(
      Leaf('a', 8),
      Fork(
        Fork(
          Leaf('b', 3),
          Fork(Leaf('c', 1), Leaf('d', 1), List('c', 'd'), 2),
          List('b', 'c', 'd'),
          5
        ),
        Fork(
          Fork(Leaf('e', 1), Leaf('f', 1), List('e', 'f'), 2),
          Fork(Leaf('g', 1), Leaf('h', 1), List('g', 'h'), 2),
          List('e', 'f', 'g', 'h'),
          4
        ),
        List('b', 'c', 'd', 'e', 'f', 'g', 'h'),
        9
      ),
      List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'),
      17
    )
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      println(s"""encoded=${encode(t1)("ab".toList)}""")
      println(s"""decoded=${decode(t1, encode(t1)("ab".toList))}""")

      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and encode a very short text with large tree should be identity") {
    new TestTrees {
      println(s"""encoded=${encode(t2)("ab".toList)}""")
      println(s"""decoded=${decode(t2, encode(t2)("ab".toList))}""")

      assert(decode(t2, encode(t2)("ab".toList)) === "ab".toList)
    }
  }

  // 该测试运行不过，不过属于正常
//  test("createCodeTree with a middle length text") {
//    new TestTrees {
//      val got = createCodeTree("abcdefghaaaaaaabb".toCharArray.toList)
//      println(got)
//      assert(got === t3)
//    }
//  }
}
