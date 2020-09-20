# 常用数据结构

## Map

**合并两个Map**

合并两个Map，将value 加起来

```scala
(m2.keySet ++ m1.keySet) map (item => (m1.getOrElse(item, 0.0) + m2.getOrElse(item, 0.0)))
```

## 可变参数列表

```scala
  def fn(bindings: (Int, Double)*) = ...
```

## class constructor 构造函数

*val* 在构造函数传参的语义：

```scala
class A(val arg1: Int) {
  // arg1 是 A 的成员变量, public
  override def toString(): String = arg1.toString()
}

class B(arg1: Int) {
  // arg1 是 A 的成员变量, private
  override def toString(): String = arg1.toString()
}

val a = new A(1)
print a.arg1  // 有效
print b.arg1  // error: value arg1 is not a member of B
```

[相关Doc](https://docs.scala-lang.org/tour/classes.html) 是：

Primary constructor parameters with `val` and `var` are public.

