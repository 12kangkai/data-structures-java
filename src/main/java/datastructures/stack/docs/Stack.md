# 📌 数据结构：栈（Stack）

---

## 1️⃣ 本质理解

栈的本质不是“先进来的数据先放着”，而是：

👉 **一种受限线性结构：只允许在一端进行插入和删除**

这一端通常叫做：
- **栈顶（top）**

核心原则：

> **后进先出（LIFO, Last In First Out）**

也就是说：
- 最后入栈的元素
- 最先出栈

---

## 2️⃣ 深度对比

### 📊 栈 vs 队列

| 维度     | 栈（Stack） | 队列（Queue） |
| -------- | ----------- | ------------- |
| 操作规则 | 后进先出    | 先进先出      |
| 插入位置 | 栈顶        | 队尾          |
| 删除位置 | 栈顶        | 队头          |
| 典型场景 | 递归、回退、括号匹配 | 调度、缓冲、BFS |

👉 面试关键点：
- 栈强调 **“最近访问优先处理”**
- 队列强调 **“按进入顺序处理”**

---

### 📊 栈 vs 数组

| 维度     | 栈 | 数组 |
| -------- | --- | ---- |
| 访问方式 | 只能访问栈顶 | 可按下标随机访问 |
| 查询能力 | 只能看栈顶 O(1) | 任意位置访问 O(1) |
| 插入删除 | 栈顶 O(1) | 中间位置 O(n) |
| 使用限制 | 强约束结构 | 通用顺序存储 |

👉 关键：
- **栈不是底层存储方式**，而是**一种逻辑访问规则**
- 栈通常可以用 **数组** 或 **链表** 来实现

---

### 📊 顺序栈 vs 链式栈

| 维度     | 顺序栈（数组实现） | 链式栈（链表实现） |
| -------- | ------------------ | ------------------ |
| 内存      | 连续               | 不连续             |
| push/pop | O(1)               | O(1)               |
| 扩容      | 可能需要           | 不需要             |
| 缓存友好  | ✅                  | ❌                  |
| 实现难度  | 更简单             | 稍复杂             |

👉 面试关键点：
- 高频场景里，**数组实现的栈通常更常见**
- 因为连续内存更利于 CPU Cache 命中

---

## 3️⃣ Java栈实现

```java
class MyStack {
    private int[] data;
    private int size;

    public MyStack(int capacity) {
        data = new int[Math.max(1, capacity)];
    }

    public void push(int val) {
        if (size == data.length) {
            resize();
        }
        data[size++] = val;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        int res = data[--size];
        return res;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCap = data.length + (data.length >> 1);
        if (newCap == data.length) {
            newCap = data.length + 1;
        }
        int[] newArr = new int[newCap];
        System.arraycopy(data, 0, newArr, 0, size);
        data = newArr;
    }
}
```

---

## 4️⃣ 核心问题深度解析

### 🎯 问题1：为什么栈是 LIFO？

👉 因为它只允许在同一端操作：
- push：只能从栈顶放入
- pop：只能从栈顶弹出

所以最后进去的元素，会最先被拿出来。

---

### 🎯 问题2：为什么栈的 push 和 pop 通常是 O(1)？

👉 因为：
- 不需要遍历
- 不需要移动大量元素
- 只操作栈顶位置

比如：
```text
push(10) -> top 后移一格
pop()    -> top 前移一格
```

本质上就是：
- 改一个下标
- 读写一个位置

---

### 🎯 问题3：顺序栈为什么偶尔不是严格 O(1)？

👉 因为扩容时需要复制数据。

扩容那一次操作是 O(n)，但如果看**均摊时间复杂度**：

> **push 仍然是均摊 O(1)**

这和 ArrayList 的扩容分析本质一样。

---

### 🎯 问题4：为什么函数调用本质上也是栈？

👉 因为函数调用满足“后调用先返回”：

例如：
- main 调用 A
- A 调用 B
- B 执行完先返回 A
- A 再返回 main

这就是典型的 **LIFO**。

所以：
- 函数调用栈
- 递归
- DFS

本质都和栈有关。

---

### 🎯 问题5：什么时候必须想到栈？

当题目出现下面这些信号时，优先考虑栈：

✅ 最近匹配 
✅ 括号问题 
✅ 表达式求值 
✅ 撤销/回退 
✅ 单调性维护 
✅ 深度优先处理  

---

## 5️⃣ 实战案例

### 🧩 有效括号

```java
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
```

👉 核心思想：
- 左括号入栈
- 右括号匹配栈顶
- 不匹配立即失败

---

### 🧩 用栈实现浏览器回退

```java
import java.util.ArrayDeque;
import java.util.Deque;

class BrowserHistory {
    private final Deque<String> back = new ArrayDeque<>();
    private final Deque<String> forward = new ArrayDeque<>();
    private String current;

    public BrowserHistory(String homepage) {
        this.current = homepage;
    }

    public void visit(String url) {
        back.push(current);
        current = url;
        forward.clear();
    }

    public String back(int steps) {
        while (steps > 0 && !back.isEmpty()) {
            forward.push(current);
            current = back.pop();
            steps--;
        }
        return current;
    }

    public String forward(int steps) {
        while (steps > 0 && !forward.isEmpty()) {
            back.push(current);
            current = forward.pop();
            steps--;
        }
        return current;
    }
}
```

👉 核心思想：
- 后退栈
- 前进栈
- 当前页面单独维护

---

### 🧩 单调栈：下一个更大元素

```java
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }
}
```

👉 核心思想：
- 维护一个“从栈顶到栈底单调递增/递减”的结构
- 用来快速找到“下一个更大/更小元素”

---

## 6️⃣ 高频技巧总结

### 🚀 技巧1：括号匹配栈
- 成对匹配问题
- 表达式合法性判断

---

### 🚀 技巧2：单调栈
- 下一个更大元素
- 柱状图最大矩形
- 每日温度

---

### 🚀 技巧3：辅助栈
- 最小栈
- 支持 O(1) 获取最值

---

### 🚀 技巧4：递归转非递归
- DFS
- 树遍历
- 图遍历

---

## 7️⃣ 总结

👉 一句话总结栈：

> 栈是一种后进先出的受限线性结构，只允许在栈顶进行插入和删除，适合处理最近访问、匹配、回退、递归等问题。

---

## 8️⃣ 你必须真正掌握的点

✔ 为什么是 LIFO  

✔ 为什么 push / pop 是 O(1)  

✔ 顺序栈和链式栈的区别  

✔ 为什么递归本质上是栈  

✔ 什么时候看到题目就该想到栈  

✔ 单调栈的核心使用场景  
