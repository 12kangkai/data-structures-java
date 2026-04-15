# 📌 数据结构：数组（Array）

---

## 1️⃣ 本质理解

数组的本质不是“存一堆数据”，而是：

👉 **一段连续的内存空间 + 固定步长访问规则**

核心公式：

address = base_address + index * element_size

这也是数组 O(1) 查询的根本原因。

---

## 2️⃣ 深度对比

### 📊 数组 vs 链表

| 维度 | 数组 | 链表 |
|------|------|------|
| 内存 | 连续 | 不连续 |
| 查询 | O(1) | O(n) |
| 插入 | O(n) | O(1)（已知节点） |
| 删除 | O(n) | O(1) |
| 缓存友好 | ✅ | ❌ |
| 扩容 | 需要复制 | 不需要 |

👉 面试关键点：
- 数组快在“CPU缓存命中”
- 链表慢在“指针跳转”

---

### 📊 数组 vs ArrayList

| 维度 | 数组 | ArrayList |
|------|------|-----------|
| 大小 | 固定 | 动态 |
| 扩容 | 无 | 自动扩容 |
| 类型 | 基本类型 | 对象（泛型） |
| 性能 | 更快 | 略慢 |

👉 关键：
- ArrayList = “封装后的动态数组”

---

### 📊 ArrayList 扩容机制

JDK源码核心逻辑：

```
newCapacity = oldCapacity + (oldCapacity >> 1);
```

👉 本质：
- 扩容为 1.5 倍

👉 为什么不是 2 倍？
- 2倍：减少扩容次数，但浪费空间
- 1.5倍：时间 & 空间折中

---

## 3️⃣ Java数组实现

```java
class MyArray {
    private int[] data;
    private int size;

    public MyArray(int capacity) {
        data = new int[capacity];
    }

    public void add(int val) {
        if (size == data.length) resize();
        data[size++] = val;
    }

    public int get(int index) {
        check(index);
        return data[index];
    }

    public void set(int index, int val) {
        check(index);
        data[index] = val;
    }

    public int remove(int index) {
        check(index);
        int res = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        return res;
    }

    private void resize() {
        int newCap = data.length + (data.length >> 1);
        if (newCap - data.length == 0){
            newCap = data.length + 1;
        }
        int[] newArr = new int[newCap];

        System.arraycopy(data, 0, newArr, 0, data.length);
        data = newArr;
    }

    private void check(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("Index error");
    }
}
```

---

## 4️⃣ 核心问题深度解析

### 🎯 问题1：数组为什么是 O(1)？

👉 本质：直接地址计算，不需要遍历

---

### 🎯 问题2：为什么插入是 O(n)？

👉 因为：
- 必须移动 index 后所有元素

👉 举例：
```text
[1,2,3,4] 插入 index=1 → [1,x,2,3,4]
```

---

### 🎯 问题3：为什么数组比链表快？

👉 关键点：
1. CPU缓存（Cache Line）
2. 内存连续
3. 无指针跳转

---

### 🎯 问题4：什么时候必须用数组？

✅ 高频读 

✅ 索引访问 

✅ 内存局部性要求高  

---

### 🎯 问题5：数组的性能瓶颈？

❌ 扩容复制 

❌ 插入删除  

---

## 5️⃣ 实战案例

### 🧩 两数之和

```java
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }

            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
```

👉 核心思想：
- 数组 + 哈希 = O(n)

---

### 🧩 删除重复元素（双指针）

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 0;

        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
        }

        return slow + 1;
    }
}
```

👉 核心思想：
- 快慢指针
- 原地修改

---

## 6️⃣ 高频技巧总结

### 🚀 技巧1：双指针
- 去重
- 分区

---

### 🚀 技巧2：滑动窗口
- 子数组问题
- 最长/最短区间

---

### 🚀 技巧3：前缀和
- 区间和问题

---

## 7️⃣ 总结

👉 一句话总结数组：

> 数组是基于连续内存的随机访问结构，查询 O(1)，插入删除 O(n)，适用于读多写少场景。

---

## 8️⃣ 你必须真正掌握的点

✔ 为什么 O(1) 

✔ 为什么慢在插入 

✔ 扩容机制 

✔ 和链表的本质区别  

✔ 什么时候用数组  

---

