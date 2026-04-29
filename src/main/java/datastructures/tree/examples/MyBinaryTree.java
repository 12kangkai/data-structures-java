package datastructures.tree.examples;

import java.util.*;

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





































