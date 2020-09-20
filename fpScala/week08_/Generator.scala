package week08_

trait Generator[+T] {
  self => 

  def generate: T

  def map[S](f: T => S): Generator[S] = new Generator[S] {
    def generate = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    def generate: S = f(self.generate).generate
  }
}

trait Tree
case class Inner(left: Tree, right: Tree) extends Tree
case class Leaf(x: Int) extends Tree

object Generator {
  val integers = new Generator[Int] {
    val rand = new java.util.Random
    def generate = rand.nextInt()
  }

  val booleans = integers map (_ > 0)

  def leafs: Generator[Leaf] = for {
    v <- integers
  } yield Leaf(v)

  def inners: Generator[Inner] = for {
    l <- trees
    r <- trees
  } yield Inner(l, r)

  def trees: Generator[Tree] = for {
      isLeaf <- booleans
      tree <- if(isLeaf) leafs else inners
    } yield tree
}
