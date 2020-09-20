# Key-Value Store

## Why Not Ralational Database

+ Data: large and unstructured
+ Lots of random reads and writes
+ Sometimes write-heavy
+ Foreign keys rarely needed
+ Joins infrequent

## Needs of Today's Workloads

+ Speed
+ Avoid Single Point of Failure (SPoF)
+ Low TCO (total cost of operation)
+ Fewer system administrators
+ Incremental Scalability
+ Scale out, not up

## Key-Value/NoSQL Data Model

The model require its implementation to support two API operations:

+ get(key)
+ put(key, value)

Tables are named differently in different systems.  "Column families" in Cassandra, "Table" in HBase, "Collection" in MongoDB.

The table data may be unstructured, perhaps doesn't support joins or don't have foreign keys.

## Column-Oriented Storage

NoSQL systems often use column-oriented storage:

RDBMSs: store an entire row together (on disk or at a server)

NoSQL systems: typically store a column together (or a group of columns).  Entries within a column are indexed and easy to locate, given a key (and vice-versa)

Why? Range searches within a column are fast since you don't need to fetch the entire database. For example, give me all blog_ids from the blog table that were updated within the past month.
