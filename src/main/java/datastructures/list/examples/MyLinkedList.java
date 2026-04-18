package datastructures.list.examples;

public class MyLinkedList {

    private static class Node{
        int val;
        Node next;

        public Node(int val) {
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
