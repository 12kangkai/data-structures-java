# 📌 数据结构：链表（Linked List）

---

## 1️⃣ 本质理解

链表的本质不是“节点一个接一个”，而是：

👉 **一组分散的节点 + 每个节点保存数据和下一个节点的引用**

也就是说：
- 链表节点在内存中**通常不是连续存放**的
- 节点之间靠 **引用（指针）** 串起来
- 想访问某个位置，必须从头节点一路往后找             

单链表节点结构可以理解成：

```text
[data | next]
```

例如：

```text
1 -> 3 -> 5 -> 8 -> null
```

这也是链表查询慢、插入删除灵活的根本原因。

---

## 2️⃣ 深度对比

### 📊 链表 vs 数组

| 维度     | 链表                    | 数组     |
| -------- | ----------------------- | -------- |
| 内存     | 不连续                  | 连续     |
| 查询     | O(n)                    | O(1)     |
| 插入     | O(1)（已知前驱）        | O(n)     |
| 删除     | O(1)（已知前驱）        | O(n)     |
| 缓存友好 | ❌                       | ✅        |
| 扩容     | 不需要整体扩容          | 可能需要 |


👉 面试关键点：

- 链表强在“**插入删除灵活**”
- 数组强在“**随机访问快 + 缓存命中高**”

---

### 📊 单链表 vs 双向链表

| 维度     | 单链表                    | 双向链表                      |
| -------- | ------------------------- | ----------------------------- |
| 节点结构 | data + next               | data + prev + next            |
| 向前访问 | ❌                         | ✅                             |
| 向后访问 | ✅                         | ✅                             |
| 删除节点 | 需要前驱节点              | 已知节点时更方便              |
| 空间占用 | 更小                      | 更大                          |

👉 关键：

- **单链表省空间**
- **双向链表操作更灵活**

---

### 📊 链表 vs ArrayList

| 维度     | 链表                         | ArrayList         |
| -------- | ---------------------------- | ----------------- |
| 底层结构 | 节点连接                     | 动态数组          |
| 查询     | O(n)                         | O(1)              |
| 尾部追加 | O(1)（维护尾指针时）         | 均摊 O(1)         |
| 中间插入 | O(1)（定位后）               | O(n)              |
| 内存占用 | 更高（节点对象 + 引用）      | 更紧凑            |
|
👉 关键：
- Java 中很多场景下 **ArrayList 比 LinkedList 更常用**
- 因为现实开发里“查得多、CPU缓存影响大”，链表并不一定更快

---

## 3️⃣ Java链表实现

下面给你一个适合教学的 **单链表手写实现**。

```java
class MyLinkedList {

    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addLast(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void addFirst(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = node;
        }
        size++;
    }

    public void add(int index, int val) {
        checkPositionIndex(index);

        if (index == 0) {
            addFirst(val);
            return;
        }
        if (index == size) {
            addLast(val);
            return;
        }

        Node prev = findNode(index - 1);
        Node node = new Node(val);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public int get(int index) {
        checkElementIndex(index);
        return findNode(index).val;
    }

    public void set(int index, int val) {
        checkElementIndex(index);
        findNode(index).val = val;
    }

    public int removeFirst() {
        if (head == null) {
            throw new RuntimeException("LinkedList is empty");
        }

        int res = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return res;
    }

    public int remove(int index) {
        checkElementIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        Node prev = findNode(index - 1);
        Node deleted = prev.next;
        prev.next = deleted.next;

        if (deleted == tail) {
            tail = prev;
        }

        size--;
        return deleted.val;
    }

    public int size() {
        return size;
    }

    private Node findNode(int index) {
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Index error");
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("Index error");
        }
    }
}
```

---

## 4️⃣ 核心问题深度解析

### 🎯 问题1：链表为什么查询是 O(n)？

👉 本质：
链表节点不是连续存储，不能像数组那样通过公式直接定位。

访问第 `index` 个节点时，只能：
- 从 `head` 开始
- 一个节点一个节点向后走
- 直到走到目标位置

所以最坏情况要遍历整个链表。

---

### 🎯 问题2：为什么链表插入删除可以更快？

👉 因为：
- 不需要整体搬移元素
- 只需要修改节点之间的引用关系

例如在节点 `prev` 后插入新节点：

```text
prev -> next
变成
prev -> newNode -> next
```

如果你已经拿到了前驱节点 `prev`，那么修改引用就是 O(1)。

---

### 🎯 问题3：为什么“链表插入 O(1)”这句话不完整？

👉 这是面试里非常高频的坑。

正确说法应该是：

> **链表在“已知插入位置前驱节点”的情况下，插入是 O(1)。**

如果你还需要先查找到这个位置，那么：
- 查找要 O(n)
- 插入本身 O(1)
- 总体还是 O(n)

所以别机械背结论。

---

### 🎯 问题4：为什么链表在实际开发中不一定比数组快？

👉 因为链表有这些问题：
1. 节点分散，**CPU缓存不友好**
2. 每个节点都有额外引用，**更耗内存**
3. 遍历时有大量 **指针跳转**

所以很多真实业务里：
- 理论上链表适合插入删除
- 但工程上 ArrayList 往往更快、更省内存

---

### 🎯 问题5：链表最典型的使用场景是什么？

✅ 频繁头插、头删  

✅ 不要求随机访问  

✅ 需要基于节点做结构调整  

✅ 作为其他结构的基础（如哈希桶、邻接表、LRU）  

---

## 5️⃣ 实战案例

### 🧩 反转链表

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }
}
```

👉 核心思想：
- 保存下一个节点
- 当前节点指针反转
- 三指针滚动推进

---

### 🧩 删除链表倒数第 N 个节点

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}
```

👉 核心思想：
- 快慢指针
- 让 fast 先走 `n+1` 步
- slow 停在待删除节点前一个位置

---

### 🧩 判断链表是否有环

```java
class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
```

👉 核心思想：
- Floyd 判圈法
- 慢指针一次一步，快指针一次两步
- 相遇则有环

---

## 6️⃣ 高频技巧总结

### 🚀 技巧1：虚拟头节点（dummy）
- 统一头节点删除/插入逻辑
- 避免大量边界判断

---

### 🚀 技巧2：快慢指针
- 找中点
- 找倒数第 K 个节点
- 判环

---

### 🚀 技巧3：前驱节点思维
- 删除节点时，本质常常不是“删当前”
- 而是“让前驱跳过当前”

---

## 7️⃣ 总结

👉 一句话总结链表：

> 链表是基于节点引用连接起来的线性结构，查询 O(n)，已知前驱时插入删除 O(1)，适用于结构调整频繁、随机访问要求不高的场景。

---

## 8️⃣ 你必须真正掌握的点

✔ 为什么链表查询是 O(n)  

✔ 为什么“插入 O(1)”有前提  

✔ 单链表和双向链表的区别  

✔ 为什么链表不一定比数组快  

✔ dummy、快慢指针、判环、反转这些经典套路  

---
