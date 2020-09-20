# Classical Distributed System

Three theoretical topics shall be discussed this week: snapshots, multicast and consensus.

## Materials from Coursera

### Goals and Objectives

+ Design an algorithm to calculate a distributed snapshot
+ Assign FIFO/Causal/Total ordering to multicast messages
+ Design a reliable multicast protocol
+ Know the working of the industry-standard protocol called Paxos
+ Know why consensus is hard to solve

### Key Phrases/Concepts

+ Global Snapshots
+ Multicast Ordering
+ Multicast Reliability
+ Paxos
+ Impossibility of Consensus

### Guiding Questions

Develop your answers to the following guiding questions while completing the readings and working on assignments throughout the week.

+ What is the difference between a safety property and a liveness property?
+ How does the Chandy-Lamport algorithm work?
+ How do you assign FIFO/Causal timestamps to multicasts in a distributed system?
+ How does Paxos use quorums to ensure safety?
+ Why is consensus is impossible to solve in asynchronous systems?

### Systems to learn

+ [Google Chubby](https://ai.google/research/pubs/pub27897)
+ Apache Zookeeper
+ Consul
+ Etcd

## Lesson 1: Snapshots

State to state movement obeys causality.

Problem to solve: Record a global snapshot (state for each process, and state for each channel)

### Chandy-Lamport global snapshot algorithm

Step 1: 对于发起者进程 Initiator Pi 

1. `records` its own state
2. create special messages called `Marker` message 
3. send one marker message to ALL other N-1 channels
4. `Start recording` incoming messages on the N-1 channels, Cji (for j = 1 to N except i)

Step 2: 对于任意进程（可以是Initiator）Pi，在 Cki上收到一条消息时

情况一：该消息是Pi收到的第一条Marker消息
  1. 记录进程状态
  2. 将 Cki 的状态标记为空数组，该channel是terminate状态
  3. Pi向所有其他 N-1 个进程发送Marker消息
  4. 初始化一个数组监听其他 N-2 个进程发送的消息(不包含 Pk 和 Pi)

情况二：该消息不是Pi收到的第一条Marker消息
  1. 停止监听 Pk，并将 Cki 收集了 Pk 发来的消息记录为状态

这里我有两个疑问：

1. 发起者 Initiator Pi 是如何确定的？如何保证只有一个Initiator？还是说允许有多个 Initiator？

2. 对于任意进程 Pi，Cxi 记录了所有进程的状态，但是并不是同时刻所有进程的状态。如何解释这个算法记录的snapshot是正确的呢？
