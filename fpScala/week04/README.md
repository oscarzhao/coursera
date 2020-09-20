# Subtyping

## Covariance

观察 scala array 的例子，会发现 Scala Array 不是 covariant，但是 Scala List 是 covariant.

一个通用的规则是：

```{text}
Roughly speaking, a type that accepts mutations of its elements should not be covariant.

But immutable types can be covariant, if some conditions on methods are met.
```

### 如何定义 Variance

Say `C[T]` is a parameterized type and `A`,`B` are types such that `A <: B` (A is a subtype of B).

In general, there are three possible relationships between `C[A]` and `C[B]`:

1. `C[A] <: C[B]`                C is `covariant`
2. `C[A] >: C[B]`                C is `contravariant`
3. neither C[A] nor C[B] is a subtype of the other, C is `nonvariant`

### 如何声明 variance

我们可以通过给类型添加 annotations 来声明一个类型的variance:

1. `class C[+A] { ... }`       C is `covariant`
2. `class C[-A] { ... }`       C is `contravariant`
3. `class C[A] { ... }`        C is `nonvariant`

### Typing Rules for Functions

Generally, if `A2 <: A1` and `B1 <: B2`, then:
```    
A1 => B1 <: A2 => B2
```

因此，我们说函数的 参数是 contrvariant，返回值是 covariant。这也是我们这样定义 function trait:

```{scala}
package scala

trait Function1[-T, +U] {
    def apply(x: T): U
}
```

