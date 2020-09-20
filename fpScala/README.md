# Functional Programming Principles in Scala

查看 Scala 的基本概念可以参考 [Scala快速上手指南](https://github.com/lampepfl/progfun-wiki/blob/gh-pages/CheatSheet.md)。

## sbt 源替换

默认的 sbt 源下载依赖超级慢，换到国内 ali 
https://www.readailib.com/2018/01/05/solve-sbt-update-scala-slow/

## Huffman Tree 关键逻辑

下面是一些实现 Huffman Tree 的关键步骤和需要注意的细节

### 根据一堆文本生成 Huffman Tree：

1. 找到每个字符的频率，分别存到 Leaf 下
    * 由小到大排序
2. 合并 Leaf 成 Fork
    * 合并后，仍然需要保序

### 根据 Huffman Tree，对文本进行编码：

1. 根据每个字符，遍历 Huffman Tree 到 Leaf
    * 通过 字符匹配 进入 Left 或 Right 分支
    * 注意：生成字符串时，是 append 新字符，而不是 prepend

### 使用 Huffman Tree 解码：

1. 遍历二进制串，根据具体值选择 Left 或 Right 分支
    * 0 选 Left； 1 选 Right
    * 终止条件：到某个 Leaf

## Anagrams 问题

The problem

An anagram of a word is a rearrangement of its letters such that a word with a different meaning is formed. For example, if we rearrange the letters of the word `Elvis` we can obtain the word `lives`, which is one of its anagrams.

In a similar way, an anagram of a sentence is a rearrangement of all the characters in the sentence such that a new sentence is formed. The new sentence consists of meaningful words, the number of which may or may not correspond to the number of words in the original sentence. For example, the sentence:

```
I love you
```

is an anagram of the sentence:

```
You olive
```

**解决思路**

1. Sentence -> xs : List[(Char, Int)]
2. xs: List[(Char, Int)] -> xss: List[List[(Char, Int)]]，拿到所有子集
3. xs: List[(Char, Int)] -> xs::xss
    求出 xs 对应的单词，如果不存在，则停止递归，返回空集
    求出 xss 对应的单词组合，如果 xss 为空，则停止递归，并返回结果集
