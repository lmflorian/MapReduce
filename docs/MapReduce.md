# MapReduce: A Comprehensive Guide

## 🌐 What is MapReduce?
**MapReduce** is a programming model for processing large datasets in parallel across distributed clusters. Originally developed by Google, it simplifies big data processing by abstracting complexity into two main functions: `Map` and `Reduce`.

![MapReduce Flow](https://i.imgur.com/JZPUdqe.png)

## 🔧 How It Works
### 1. Execution Phases
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
