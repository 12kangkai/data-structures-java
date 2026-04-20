# 📌 数据结构：队列（Queue）

---



## 1️⃣ 本质理解

队列的本质不是“先进先出”，而是：

👉 **受限的线性结构（只允许一端入队，一端出队）**

---



## 2️⃣ 深度对比

### 📊 队列 vs 栈

| 维度     | 队列（Queue） | 栈（Stack） |
| -------- | ------------- | ----------- |
| 数据顺序 | FIFO          | LIFO        |
| 操作端   | 两端          | 一端        |

---

### 📊 队列 vs 数组

| 维度     | 队列         | 数组       |
| -------- | ------------ | ---------- |
| 访问方式 | 顺序访问     | 随机访问   |
| 查询     | O(n)         | O(1)       |
| 插入删除 | O(1)（受限） | O(n)       |

### **📊 队列 vs 双端队列（Deque）**

| **维度** | **队列** | **双端队列** |
| -------- | -------- | ------------ |
| 入队     | 一端     | 两端         |
| 出队     | 一端     | 两端         |
| 灵活性   | 低       | 高           |

- Deque = 队列的“超级版本”

---



## 3️⃣ Java实现

### 🧩 数组实现

```java
class ArrayQueue {
    private int[] data;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        data = new int[capacity];
    }

    public void enqueue(int val) {
        if (tail == data.length) {
            throw new RuntimeException("Queue full");
        }
        data[tail++] = val;
    }

    public int dequeue() {
        if (head == tail) {
            throw new RuntimeException("Queue empty");
        }
        return data[head++];
    }
}
```

---

### 🧩 循环队列（重点）

```java
class CircularQueue {
    private int[] data;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public CircularQueue(int capacity) {
        data = new int[capacity];
    }

    public void enqueue(int val) {
        if (size == data.length) {
            throw new RuntimeException("Queue full");
        }

        data[tail] = val;
        tail = (tail + 1) % data.length;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            throw new RuntimeException("Queue empty");
        }

        int res = data[head];
        head = (head + 1) % data.length;
        size--;
        return res;
    }
}
```

---

### **🧩 链表实现（推荐）**

```java
class LinkedQueue {

    private static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    private Node head;
    private Node tail;

    public void enqueue(int val) {
        Node node = new Node(val);
        if (tail != null) {
            tail.next = node;
        }
        tail = node;

        if (head == null) {
            head = node;
        }
    }

    public int dequeue() {
        if (head == null) {
            throw new RuntimeException("Empty");
        }

        int res = head.val;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        return res;
    }
}
```

👉 优点：

- 不需要扩容
- 真正 O(1)

------



## **4️⃣ 核心问题深度解析**

### **🎯 问题1：队列为什么是 O(1)？**

👉 因为：

- 入队：直接 tail++
- 出队：直接 head++

❗ 没有元素移动

------

### **🎯 问题2：为什么数组队列需要循环？**

👉 否则会出现：

[_,_,_,x,x,x] → tail到末尾 → 但前面空了

👉 本质问题：

- **空间浪费**

---

### **🎯 问题3：循环队列如何判断满？**

两种方式：

- size == capacity
- (tail + 1) % capacity == head

### **🎯 问题4：为什么链表更适合队列？**

👉 因为：

- 不需要扩容
- 不需要搬移数据

### **🎯 问题5：队列的核心应用场景？**

✅ 任务调度

✅ 广度优先搜索（BFS）

✅ 消息队列

---



## 5️⃣ 实战案例

### 🧩 BFS**（广度优先搜索）**

```java
import java.util.*;

class Solution {
    public int bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                TreeNode node = queue.poll();

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            level++;
        }

        return level;
    }
}
```

---

### **🧩 滑动窗口最大值（Deque）**

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {

            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return res;
    }
}
```

------



## **6️⃣ 高频技巧总结**

------

### **🚀 技巧1：循环数组**

👉 避免空间浪费

------

### **🚀 技巧2：双端队列（Deque）**

👉 解决：

- 滑动窗口
- 单调队列

------

### **🚀 技巧3：队列 + BFS**

👉 图 / 树遍历核心套路

------



## **7️⃣ 总结**

👉 一句话总结队列：

队列是受限的线性结构，保证先进先出，常用于调度、缓冲和BFS场景。



## **8️⃣ 你必须真正掌握的点**

✔ 队列 vs 栈

✔ 循环队列原理

✔ 为什么不能用普通数组

✔ 链表 vs 数组实现

✔ BFS 为什么必须用队列
