# 📌 数据结构：哈希表（Hash Table）

---

## 1️⃣ 本质理解

哈希表的本质不是“用 key 找 value”，而是：

👉 **数组 + 哈希函数 + 冲突处理机制**

核心公式：

```text
index = hash(key) % table.length
```

也就是说：

- 先通过 `hash(key)` 把 key 转成一个整数
- 再通过取模映射到数组下标
- 最后把数据存到这个下标位置

所以哈希表查询快的根本原因是：

👉 **把 key 的查找问题，转换成了数组下标访问问题**

---

## 2️⃣ 深度对比

### 📊 哈希表 vs 数组

| 维度     | 数组                 | 哈希表        |
| -------- | -------------------- | ------------- |
| 访问方式 | 通过下标访问         | 通过 key 访问 |
| 查询     | O(1)                 | 平均 O(1)     |
| 插入     | 末尾 O(1)，中间 O(n) | 平均 O(1)     |
| 删除     | O(n)                 | 平均 O(1)     |
| 是否有序 | 有下标顺序           | 通常无序      |
| 核心依赖 | 连续内存             | 哈希函数      |

👉 关键：

- 数组适合通过 index 查找
- 哈希表适合通过 key 查找

---

### 📊 哈希表 vs 链表

| 维度         | 链表             | 哈希表               |
| ------------ | ---------------- | -------------------- |
| 查询         | O(n)             | 平均 O(1)            |
| 插入         | O(1)（已知节点） | 平均 O(1)            |
| 删除         | O(1)（已知节点） | 平均 O(1)            |
| 内存结构     | 节点分散         | 数组 + 链表 / 红黑树 |
| 是否适合搜索 | ❌                | ✅                    |

👉 关键点：

- 链表查询慢，因为必须从头遍历
- 哈希表查询快，因为可以通过哈希函数定位桶位置
- Java HashMap 冲突严重时，会从链表优化为红黑树

---

### 📊 HashMap vs Hashtable

| 维度                | HashMap    | Hashtable    |
| ------------------- | ---------- | ------------ |
| 是否线程安全        | ❌          | ✅            |
| 是否允许 null key   | ✅ 允许一个 | ❌ 不允许     |
| 是否允许 null value | ✅ 允许多个 | ❌ 不允许     |
| 性能                | 更高       | 较低         |
| 推荐使用            | ✅ 常用     | ❌ 已较少使用 |

👉 关键：

- `Hashtable` 是早期线程安全容器
- `HashMap` 是现在更常用的哈希表实现
- 并发场景通常使用 `ConcurrentHashMap`

---

### 📊 HashMap vs TreeMap

| 维度     | HashMap   | TreeMap     |
| -------- | --------- | ----------- |
| 底层结构 | 哈希表    | 红黑树      |
| 查询     | 平均 O(1) | O(log n)    |
| 是否有序 | 无序      | 按 key 排序 |
| 适合场景 | 快速查找  | 有序遍历    |

👉 关键：

- 只追求查询速度：优先 HashMap
- 需要 key 有序：使用 TreeMap

---

## 3️⃣ Java 哈希表实现

下面是一个简化版哈希表，使用 **数组 + 链表法** 解决哈希冲突。

```java
public class MyHashMap<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    public void put(K key, V value) {
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key, table.length);
        Node<K, V> head = table[index];

        Node<K, V> cur = head;
        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                cur.value = value;
                return;
            }
            cur = cur.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        table[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = getIndex(key, table.length);
        Node<K, V> cur = table[index];

        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                return cur.value;
            }
            cur = cur.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = getIndex(key, table.length);
        Node<K, V> cur = table[index];
        Node<K, V> prev = null;

        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                if (prev == null) {
                    table[index] = cur.next;
                } else {
                    prev.next = cur.next;
                }
                size--;
                return cur.value;
            }
            prev = cur;
            cur = cur.next;
        }

        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    private int getIndex(K key, int length) {
        int hash = key == null ? 0 : key.hashCode();
        return (hash & 0x7fffffff) % length;
    }

    private boolean equalsKey(K k1, K k2) {
        if (k1 == null) return k2 == null;
        return k1.equals(k2);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldTable = table;
        Node<K, V>[] newTable = new Node[oldTable.length * 2];

        for (Node<K, V> head : oldTable) {
            Node<K, V> cur = head;
            while (cur != null) {
                Node<K, V> next = cur.next;

                int newIndex = getIndex(cur.key, newTable.length);
                cur.next = newTable[newIndex];
                newTable[newIndex] = cur;

                cur = next;
            }
        }

        table = newTable;
    }
}
```

---

## 4️⃣ 核心问题深度解析

### 🎯 问题1：哈希表为什么平均是 O(1)？

👉 因为哈希函数可以把 key 直接映射到数组下标。

查询流程：

```text
key → hashCode → index → table[index]
```

理想情况下，每个桶中只有一个元素，所以查询就是数组访问。

---

### 🎯 问题2：为什么说哈希表不是绝对 O(1)？

因为可能发生哈希冲突。

例如：

```text
hash("A") % 16 = 3
hash("B") % 16 = 3
```

不同 key 被映射到了同一个数组下标，这就是哈希冲突。

冲突严重时，查询会退化：

| 冲突结构 | 查询复杂度 |
| -------- | ---------- |
| 链表     | O(n)       |
| 红黑树   | O(log n)   |

👉 所以 HashMap 的查询复杂度通常说：

> 平均 O(1)，最坏 O(n) 或 O(log n)

---

### 🎯 问题3：什么是哈希冲突？

哈希冲突就是：

👉 **不同的 key 经过哈希计算后，落到了同一个数组位置。**

常见解决方式：

1. 链地址法
2. 开放寻址法
3. 再哈希法

Java HashMap 使用的是：

👉 **数组 + 链表 + 红黑树**

---

### 🎯 问题4：为什么需要扩容？

如果元素越来越多，桶中的链表会越来越长。

这会导致：

```text
查询 O(1) → 查询 O(n)
```

所以哈希表需要在元素达到一定比例时扩容。

这个比例叫：

👉 **负载因子 Load Factor**

Java HashMap 默认负载因子是：

```text
0.75
```

也就是说：

```text
元素数量 >= 数组长度 * 0.75 时触发扩容
```

---

### 🎯 问题5：为什么 HashMap 扩容比较耗性能？

因为扩容不是简单地把数组变大。

它需要：

1. 创建更大的数组
2. 重新计算每个 key 的位置
3. 把所有节点迁移到新数组中

这个过程叫：

👉 **rehash**

所以扩容本质上是一次 O(n) 操作。

---

### 🎯 问题6：为什么 HashMap 的容量通常是 2 的幂？

因为可以用位运算替代取模运算。

普通写法：

```java
index = hash % length;
```

优化写法：

```java
index = hash & (length - 1);
```

前提是：

```text
length 必须是 2 的幂
```

👉 位运算比取模更快。

---

### 🎯 问题7：HashMap 中 equals 和 hashCode 的关系？

必须记住一句话：

> 如果两个对象 equals 相等，那么它们的 hashCode 必须相等。

否则 HashMap 可能找不到正确的数据。

错误示例：

```java
class User {
    private int id;
    private String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.id == other.id;
    }

    // ❌ 没有重写 hashCode
}
```

正确示例：

```java
import java.util.Objects;

class User {
    private int id;
    private String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```

---

## 5️⃣ 实战案例

### 🧩 两数之和

```java
import java.util.HashMap;
import java.util.Map;

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

- key 存数字
- value 存下标
- 用哈希表把查找目标值从 O(n) 降到 O(1)

复杂度：

```text
时间复杂度：O(n)
空间复杂度：O(n)
```

---

### 🧩 判断是否存在重复元素

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }

        return false;
    }
}
```

👉 核心思想：

- HashSet 底层也是哈希表
- 用哈希表快速判断元素是否出现过

---

### 🧩 字母异位词分组

```java
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
```

👉 核心思想：

- 异位词排序后结果相同
- 排序后的字符串作为 key
- 相同 key 的字符串放到同一个 List 中

---

### 🧩 第一个只出现一次的字符

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> countMap = new HashMap<>();

        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (countMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }
}
```

👉 核心思想：

- 第一次遍历统计频率
- 第二次遍历找第一个次数为 1 的字符

---

## 6️⃣ 高频技巧总结

### 🚀 技巧1：快速查找

适合问题：

- 两数之和
- 是否存在重复元素
- 是否出现过某个值

核心套路：

```java
Map<Integer, Integer> map = new HashMap<>();
```

---

### 🚀 技巧2：频率统计

适合问题：

- 字符出现次数
- 数组元素出现次数
- Top K 高频元素

核心套路：

```java
map.put(x, map.getOrDefault(x, 0) + 1);
```

---

### 🚀 技巧3：分组归类

适合问题：

- 字母异位词分组
- 按某种规则聚合数据

核心套路：

```java
map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
```

---

### 🚀 技巧4：前缀和 + 哈希表

适合问题：

- 和为 K 的子数组
- 区间和统计

核心思想：

```text
prefix[j] - prefix[i] = k
=> prefix[i] = prefix[j] - k
```

实战代码：

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int prefix = 0;
        int count = 0;

        for (int num : nums) {
            prefix += num;

            if (map.containsKey(prefix - k)) {
                count += map.get(prefix - k);
            }

            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }

        return count;
    }
}
```

👉 关键点：

- key：前缀和
- value：该前缀和出现次数

---

## 7️⃣ 总结

👉 一句话总结哈希表：

> 哈希表是基于数组和哈希函数实现的 key-value 查询结构，平均查询、插入、删除都是 O(1)，但需要处理哈希冲突和扩容问题。

---

## 8️⃣ 你必须真正掌握的点

✔ 哈希表的底层是数组

✔ hashCode 如何映射成数组下标

✔ 什么是哈希冲突

✔ 链地址法如何解决冲突

✔ 为什么 HashMap 平均 O(1)

✔ 为什么最坏会退化

✔ 为什么需要扩容和 rehash

✔ equals 和 hashCode 的关系

✔ HashMap、Hashtable、TreeMap 的区别

✔ 哈希表在算法题中的三大套路：查找、计数、分组

---