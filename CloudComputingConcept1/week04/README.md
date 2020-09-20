# Key-Value Stores, Time and Ordering

## Introduction

1. Lesson 1: This module motivates and teaches the design of key-value/NoSQL storage/database systems. We cover the design of two major industry systems: Apache Cassandra and HBase. We also cover the famous CAP theorem.

2. Lesson 2: Distributed systems are asynchronous, which makes clocks at different machines hard to synchronize. This module first covers various clock synchronization algorithms, and then covers ways of tagging events with causal timestamps that avoid synchronizing clocks.

These classical algorithms were invented decades ago, yet are used widely in today’s cloud systems.

## Learning Objectives

1. Identify why key-value/NoSQL systems are popular.
2. Identify which systems fall where under the CAP theorem.
3. Identify the design of and analyze Apache Cassandra.
4. Identify the design of and analyze Apache HBase.
5. Use and analyze various time synchronization algorithms.
6. Tag any given run of a distributed system with Lamport and/or vector timestamps for ordering events.

## Goals and Objectives

After you actively engage in the learning experiences in this module, you should be able to:

1. Know why key-value/NoSQL are gaining popularity
2. Know the design of Apache Cassandra
3. Know the design of Apache HBase
4. Use various time synchronization algorithms
5. Apply Lamport and vector timestamps to order events in a distributed system

### Key Phrases/Concepts

Keep your eyes open for the following key terms or phrases as you complete the readings and interact with the lectures. These topics will help you better understand the content in this module.

1. Key-value and NoSQL stores
2. Cassandra system
3. CAP theorem
4. Consistency-availability tradeoff and spectrum
5. Eventual consistency
6. HBase system
7. ACID vs. BASE
8. Time synchronization algorithms in asynchronous systems: Cristian's, NTP, and Berkeley algorithms
9. Lamport causality and timestamps
10. Vector timestamps

## Guiding Questions

Develop your answers to the following guiding questions while completing the assignments throughout the week.

1. Why are key-value/NoSQL systems popular today?
2. How does Cassandra make writes fast?
3. How does Cassandra handle failures?
4. What is the CAP theorem?
5. What is eventual consistency?
6. Goals and Objectives

After you actively engage in the learning experiences in this module, you should be able to:

+ Know why key-value/NoSQL are gaining popularity
+ Know the design of Apache Cassandra
+ Know the design of Apache HBase
+ Use various time synchronization algorithms
+ Apply Lamport and vector timestamps to order events in a distributed system

Key Phrases/Concepts

Keep your eyes open for the following key terms or phrases as you complete the readings and interact with the lectures. These topics will help you better understand the content in this module.

+ Key-value and NoSQL stores
+ Cassandra system
+ CAP theorem
+ Consistency-availability tradeoff and spectrum
+ Eventual consistency
+ HBase system
+ ACID vs. BASE
+ Time synchronization algorithms in asynchronous systems: Cristian's, NTP, and Berkeley algorithms
+ Lamport causality and timestamps
+ Vector timestamps

Guiding Questions

Develop your answers to the following guiding questions while completing the assignments throughout the week.

+ Why are key-value/NoSQL systems popular today?
+ How does Cassandra make writes fast?
+ How does Cassandra handle failures?
+ What is the CAP theorem?
+ What is eventual consistency?
+ What is a quorum?
+ What are the different consistency levels in Cassandra?
+ How do snitches work in Cassandra?
+ Why is time synchronization hard in asynchronous systems?
+ How can you reduce the error while synchronizing time across two machines over a network?
+ How does HBase ensure consistency?
+ What is Lamport causality?
+ Can you assign Lamport timestamps to a run?
+ Can you assign vector timestamps to a run?

## Readings and Resources

There are no readings required for this week, but you can look at the following documentation:

+ [Cassandra](http://www.datastax.com/documentation/cassandra/2.0/cassandra/gettingStartedCassandraIntro.html)
+ [HBase](http://hbase.apache.org/)
+ [Cassandra 2.0 paper](http://docs.datastax.com/en/articles/cassandra/cassandrathenandnow.html)
+ [Cassandra NoSQL Presentation](http://www.slideshare.net/Eweaver/cassandra-presentation-at-nosql)
+ [Cassandra 1.0 documentation at datastax.com](http://docs.datastax.com/en/archived/cassandra/1.0/docs/)
+ [Cassandra Apache wiki](http://wiki.apache.org/cassandra/ArchitectureOverview)
+ [MongoDB](https://www.mongodb.com/)
