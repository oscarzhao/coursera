
class Poly(val terms: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  override def toString(): String = 
    "(" + 
      (terms map {case (k, v) => k + " -> " + v} mkString ", ") + 
    ")"

  def + (other: Poly) = 
    new Poly((other.terms foldLeft terms)(addTerm))
  
  def addTerm(acc: Map[Int, Double], term: (Int, Double)) : Map[Int, Double] = {
    val (k, v) = term
    acc.updated(k, v + acc.getOrElse(k, 0.0))
  }
}
