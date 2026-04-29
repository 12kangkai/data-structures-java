# 📌 数据结构：树（Tree）

---

## 1️⃣ 本质理解

树的本质不是“长得像树的数据”，而是：

👉 **一种分层组织数据的非线性结构**

树由节点和边组成，核心特点是：

- 一个根节点
- 每个节点可以有多个子节点
- 除根节点外，每个节点只有一个父节点
- 节点之间不存在环

树最重要的思想是：

👉 **把线性查找问题，转化为分层查找问题**

比如二叉搜索树中，查找一个元素时，每次都可以根据大小关系排除一半方向。

---

## 2️⃣ 深度对比

### 📊 树 vs 数组

| 维度     | 数组         | 树                   |
| -------- | ------------ | -------------------- |
| 结构     | 线性结构     | 非线性结构           |
| 内存     | 连续         | 通常不连续           |
| 查询     | O(1) 按索引  | 依赖树结构           |
| 插入     | O(n)         | 平衡情况下 O(log n)  |
| 删除     | O(n)         | 平衡情况下 O(log n)  |
| 适合场景 | 高频索引访问 | 层级关系、搜索、排序 |

👉 关键：

- 数组强在“随机访问”
- 树强在“动态搜索和层级表达”

---

### 📊 树 vs 链表

| 维度     | 链表             | 树                   |
| -------- | ---------------- | -------------------- |
| 结构     | 一对一           | 一对多               |
| 查找     | O(n)             | 平衡情况下 O(log n)  |
| 插入     | O(1)（已知节点） | 依赖查找位置         |
| 表达能力 | 线性关系         | 层级关系             |
| 典型应用 | 队列、栈、LRU    | 文件系统、索引、搜索 |

👉 关键：

- 链表只能向前或向后走
- 树可以根据分支快速缩小范围

---

### 📊 普通二叉树 vs 二叉搜索树

| 维度     | 普通二叉树 | 二叉搜索树       |
| -------- | ---------- | ---------------- |
| 节点规则 | 无大小规则 | 左小右大         |
| 查找效率 | O(n)       | 平均 O(log n)    |
| 中序遍历 | 不一定有序 | 有序             |
| 应用     | 表达结构   | 搜索、排序、索引 |

👉 关键：

- 二叉搜索树 = 带有大小规则的二叉树
- 中序遍历二叉搜索树，可以得到升序结果

---

### 📊 二叉搜索树 vs 平衡二叉树

| 维度         | 二叉搜索树 | 平衡二叉树  |
| ------------ | ---------- | ----------- |
| 是否自动平衡 | ❌          | ✅           |
| 最坏查询     | O(n)       | O(log n)    |
| 插入删除     | 简单       | 更复杂      |
| 典型结构     | BST        | AVL、红黑树 |

👉 关键：

- 普通 BST 如果退化成链表，性能会变成 O(n)
- 平衡树通过旋转维持高度稳定

---

## 3️⃣ 树的核心概念

### 🌳 节点相关

| 概念     | 含义                     |
| -------- | ------------------------ |
| 根节点   | 没有父节点的节点         |
| 父节点   | 当前节点的上一层节点     |
| 子节点   | 当前节点的下一层节点     |
| 叶子节点 | 没有子节点的节点         |
| 子树     | 某个节点以及它的所有后代 |

---

### 🌳 高度、深度、层数

| 概念 | 含义                               |
| ---- | ---------------------------------- |
| 深度 | 从根节点到当前节点经过的边数       |
| 高度 | 从当前节点到最远叶子节点经过的边数 |
| 层数 | 通常从 1 开始，根节点是第 1 层     |

👉 面试关键点：

- 深度是从上往下看
- 高度是从下往上看

---

## 4️⃣ 常见树结构

### 🌲 二叉树

每个节点最多有两个子节点。

```text
      1
     / \
    2   3
   / \
  4   5
```

---

### 🌲 满二叉树

除了叶子节点外，每个节点都有两个子节点。

```text
      1
     / \
    2   3
   / \ / \
  4  5 6  7
```

---

### 🌲 完全二叉树

除了最后一层外，其余层都是满的，最后一层节点从左到右排列。

👉 堆通常就是完全二叉树。

---

### 🌲 二叉搜索树

核心规则：

```text
左子树 < 根节点 < 右子树
```

示例：

```text
      5
     / \
    3   7
   / \ / \
  2  4 6  8
```

---

### 🌲 平衡二叉树

平衡二叉树要求树的高度不能过度倾斜。

常见代表：

- AVL 树
- 红黑树

👉 Java 中的 TreeMap、TreeSet 底层就是红黑树。

---

## 5️⃣ Java 二叉搜索树实现

```java
public class MyBinaryTree<T> {

    public static class Node<T>{
        T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    private Node root;

    public MyBinaryTree(T root) {
        this.root = new Node<T>(root);
    }

    public Node<T> getRoot(){
        return root;
    }

    // ================= 增 =================

    public Node addLeft(Node parent, T value){
        if (parent == null || parent.left != null) return null;

        Node<T> left = new Node<>(value);
        parent.left = left;
        return left;
    }

    public Node addRight(Node parent, T value){
        if (parent == null || parent.right != null) return null;

        Node<T> right = new Node<>(value);
        parent.right  = right;
        return right;
    }

    // ================= 查 =================

    public Node<T> dfsFind(T value){
        return dfsFind(root, value);
    }

    private Node<T> dfsFind(Node node, T value) {
        if (node == null) return null;

        if (Objects.equals(node.value, value)) return node;

        Node<T> left = dfsFind(node.left, value);
        if (left != null) return left;

        return dfsFind(node.right, value);
    }

    public Node<T> bfsFind(T value){
        if (root == null) return null;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<T> cur = queue.poll();

            if (Objects.equals(cur.value, value)) return cur;

            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
        return null;
    }


    // ================= 改 =================

    public boolean update(Node node, T newValue){
        if (node == null) return false;

        node.value = newValue;
        return true;
    }

    // ================= 删 =================

    public boolean remove(Node target){
        if (target == null || root == null) return false;

        if (target == root){
            root = null;
            return true;
        }
        return remove(root, target);
    }

    private boolean remove(Node parent, Node target){
        if (parent.left != null){
            if (parent.left == target){
                parent.left = null;
                return true;
            }
            if (remove(parent.left,target)) return true;
        }

        if (parent.right != null){
            if (parent.right == target){
                parent.right = null;
                return true;
            }
            if (remove(parent.right, target)) return true;
        }

        return false;
    }


    // ================= 遍历 =================

    public List<T> preorder(){
        ArrayList<T> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(Node node, List<T> res) {
        if (node == null) return;

        res.add((T) node.value);
        preorder(node.left,res);
        preorder(node.right,res);
    }

    public List<T> inorder(){
        ArrayList<T> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(Node node, List<T> res){
        if (node == null) return;

        inorder(node.left, res);
        res.add((T) node.value);
        inorder(node.right, res);
    }

    public List<T> postorder(){
        ArrayList<T> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(Node node, List<T> res){
        if (node == null) return;

        postorder(node.left,res);
        postorder(node.right,res);
        res.add((T) node.value);
    }

    public List<T> levelOrder(){
        ArrayList<T> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<T> cur = queue.poll();
            res.add(cur.value);

            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }

        return res;
    }
}
```

👉 核心思想：

- 小于当前节点，去左子树
- 大于当前节点，去右子树
- 删除有两个子节点的节点时，用右子树最小节点替换

---

## 6️⃣ 树的遍历

树的遍历是树结构最重要的基础能力。

---

### 🚀 前序遍历

顺序：

```text
根 → 左 → 右
```

代码：

```java
public void preorder(Node root) {
    if (root == null) return;

    System.out.println(root.val);
    preorder(root.left);
    preorder(root.right);
}
```

👉 适合场景：

- 复制一棵树
- 序列化树
- 表达式树前缀表达式

---

### 🚀 中序遍历

顺序：

```text
左 → 根 → 右
```

代码：

```java
public void inorder(Node root) {
    if (root == null) return;

    inorder(root.left);
    System.out.println(root.val);
    inorder(root.right);
}
```

👉 关键：

- 二叉搜索树的中序遍历结果是有序的

---

### 🚀 后序遍历

顺序：

```text
左 → 右 → 根
```

代码：

```java
public void postorder(Node root) {
    if (root == null) return;

    postorder(root.left);
    postorder(root.right);
    System.out.println(root.val);
}
```

👉 适合场景：

- 删除树
- 计算目录大小
- 自底向上处理问题

---

### 🚀 层序遍历

顺序：

```text
从上到下，从左到右
```

代码：

```java
import java.util.LinkedList;
import java.util.Queue;

public void levelOrder(Node root) {
    if (root == null) return;

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        Node cur = queue.poll();
        System.out.println(cur.val);

        if (cur.left != null) queue.offer(cur.left);
        if (cur.right != null) queue.offer(cur.right);
    }
}
```

👉 核心思想：

- 层序遍历依赖队列
- BFS 本质就是一层一层扩散

---

## 7️⃣ 核心问题深度解析

### 🎯 问题1：树为什么适合搜索？

👉 因为树可以通过分支快速缩小搜索范围。

以二叉搜索树为例：

```text
      8
     / \
    4   12
   / \  / \
  2  6 10 14
```

查找 10：

```text
10 > 8，去右边
10 < 12，去左边
找到 10
```

不需要遍历所有节点。

---

### 🎯 问题2：二叉搜索树为什么可能退化？

如果插入数据本身是有序的：

```text
1, 2, 3, 4, 5
```

普通 BST 会变成：

```text
1
 \
  2
   \
    3
     \
      4
       \
        5
```

这时树退化成链表，查询复杂度从 O(log n) 变成 O(n)。

---

### 🎯 问题3：为什么需要平衡二叉树？

👉 为了防止树退化成链表。

平衡树通过旋转调整结构，让树高度保持在 O(log n)。

这样查找、插入、删除都能维持较稳定的性能。

---

### 🎯 问题4：为什么 BST 中序遍历是有序的？

因为 BST 满足：

```text
左子树 < 根节点 < 右子树
```

中序遍历刚好是：

```text
左 → 根 → 右
```

所以遍历结果天然有序。

---

### 🎯 问题5：递归处理树问题的核心是什么？

树天然适合递归，因为每一棵树都可以拆成：

```text
根节点 + 左子树 + 右子树
```

递归思考模板：

```text
1. 当前节点要做什么？
2. 左子树返回什么？
3. 右子树返回什么？
4. 当前节点如何合并结果？
```

---

## 8️⃣ 实战案例

### 🧩 二叉树最大深度

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }
}
```

👉 核心思想：

- 当前树高度 = 左右子树最大高度 + 1
- 典型后序递归思想

---

### 🧩 翻转二叉树

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
```

👉 核心思想：

- 每个节点都交换左右子树
- 递归处理所有节点

---

### 🧩 验证二叉搜索树

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean check(TreeNode node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        return check(node.left, min, node.val)
            && check(node.right, node.val, max);
    }
}
```

👉 核心思想：

- 不能只判断当前节点和左右孩子
- 要用上下界约束整棵子树

---

### 🧩 层序遍历

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }

            res.add(level);
        }

        return res;
    }
}
```

👉 核心思想：

- 用队列保存当前层节点
- 每轮处理固定数量节点，形成一层结果

---

## 9️⃣ 高频技巧总结

### 🚀 技巧1：递归

树问题最常用技巧。

适合：

- 求高度
- 求节点数量
- 判断是否对称
- 翻转二叉树
- 路径问题

---

### 🚀 技巧2：DFS

深度优先搜索。

常见形式：

- 前序 DFS
- 中序 DFS
- 后序 DFS

👉 重点：

- 前序偏“自顶向下”
- 后序偏“自底向上”

---

### 🚀 技巧3：BFS

广度优先搜索。

适合：

- 层序遍历
- 最短路径
- 每一层单独处理的问题

---

### 🚀 技巧4：上下界约束

常用于验证二叉搜索树。

错误写法通常只判断：

```text
left.val < root.val < right.val
```

正确思路是：

```text
整棵左子树都要小于 root
整棵右子树都要大于 root
```

---

### 🚀 技巧5：路径回溯

适合路径类问题：

- 根节点到叶子节点路径
- 路径总和
- 所有路径收集

核心模板：

```java
path.add(root.val);

// 递归处理左右子树

dfs(root.left);
dfs(root.right);

path.remove(path.size() - 1);
```

---

## 🔟 总结

👉 一句话总结树：

> 树是一种分层的非线性结构，核心价值是表达层级关系，并通过分支降低搜索和组织数据的成本。

---

## 1️⃣1️⃣ 你必须真正掌握的点

✔ 树为什么是非线性结构

✔ 高度、深度、层数的区别

✔ 二叉树、完全二叉树、二叉搜索树的区别

✔ 前序、中序、后序、层序遍历

✔ 为什么 BST 可能退化成链表

✔ 为什么需要平衡树

✔ BST 删除节点的三种情况

✔ 为什么验证 BST 需要上下界

✔ 递归处理树问题的核心模板

---