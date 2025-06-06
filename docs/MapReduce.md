# MapReduce: A Comprehensive Guide

## What is MapReduce?
**MapReduce** is a programming model for processing large datasets in parallel across distributed clusters. Originally developed by Google, it simplifies big data processing by abstracting complexity into two main functions: `Map` and `Reduce`.

## How It Works
### Execution Phases
| Phase       | Task                                                                 | Example (Word Count)              |
|-------------|----------------------------------------------------------------------|-----------------------------------|
| **Map**     | Processes input data into intermediate `<key, value>` pairs          | `("hello", 1), ("world", 1)`     |
| **Shuffle** | Groups all values by key across nodes                                | `("hello", [1,1]), ("world", [1])`|
| **Reduce**  | Aggregates values per key to produce final output                    | `("hello", 2), ("world", 1)`     |

```python
# Simplified Python Example
def mapper(text):
    for word in text.split():
        yield (word.lower(), 1)

def reducer(key, values):
    yield (key, sum(values))
```

### Key Characteristics
- **Distributed Processing:** Splits work across multiple machines
- **Fault Tolerance:** Automatically handles node failures
- **Data Locality:** Processes data where it's stored (minimizes network transfer)
- **Scalability:** Linear scaling with added nodes

## Advantages
- **Ease of Use**: Simplifies complex data processing tasks.
- **High Fault Tolerance**: Ensures job completion despite failures.
- **Flexibility**: Supports various data processing tasks like filtering, sorting, and aggregations.
- **Cost-Effective**: Runs on commodity hardware, reducing infrastructure costs.
- **Scalable**: Handles increasing data volumes efficiently.

## Input and Output Functions
- **Input Functions**:
  - Read data from distributed storage (HDFS, S3, etc.).
  - Convert raw data into key-value pairs in the `Map` function.
- **Output Functions**:
  - Write processed key-value pairs to storage after the `Reduce` phase.
  - Final dataset can be used for further analysis or storage.

## Key-Value Pair Functionality
- **Core Concept**:
  - The main data structure in MapReduce.
  - `Map` function emits key-value pairs.
  - `Reduce` function processes all values associated with a key.
- **Example**:
  - Counting word frequency:
    - Map emits (word, 1) for each occurrence.
    - Reduce sums all values for each word to get total count.

## Usage
### Assuming environment variables are set as follows:
export JAVA_HOME=/usr/java/default
export PATH=${JAVA_HOME}/bin:${PATH}
export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar

### Compile MaxTemperature.java and create a jar:
$ bin/hadoop com.sun.tools.javac.Main *.java
$ jar cf max.jar *.class

### Run the application:
$ hadoop jar max.jar MaxTemperature <inputDir> <OutputDir>
