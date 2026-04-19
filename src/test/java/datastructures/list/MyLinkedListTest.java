package datastructures.list;

import datastructures.list.examples.MyLinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    @Test
    void testAddLast() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testAddFirst() {
        MyLinkedList list = new MyLinkedList();

        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testAddAtMiddle() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(1);
        list.addLast(3);
        list.add(1, 2);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testAddAtHeadByIndex() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(2);
        list.addLast(3);
        list.add(0, 1);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testAddAtTailByIndex() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(1);
        list.addLast(2);
        list.add(2, 3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testGet() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testSet() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        list.set(1, 99);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(99, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testRemoveFirst() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        int removed = list.removeFirst();

        assertEquals(1, removed);
        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testRemoveMiddle() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        int removed = list.remove(1);

        assertEquals(2, removed);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(4, list.get(2));
    }

    @Test
    void testRemoveTail() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        int removed = list.remove(2);

        assertEquals(3, removed);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testRemoveOnlyOneElement() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(100);

        int removed = list.removeFirst();

        assertEquals(100, removed);
        assertEquals(0, list.size());
        assertThrows(RuntimeException.class, () -> list.get(0));
    }

    @Test
    void testRemoveHeadByIndex() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(5);
        list.addLast(6);
        list.addLast(7);

        int removed = list.remove(0);

        assertEquals(5, removed);
        assertEquals(2, list.size());
        assertEquals(6, list.get(0));
        assertEquals(7, list.get(1));
    }

    @Test
    void testGetIndexOutOfBounds() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(1);

        assertThrows(RuntimeException.class, () -> list.get(-1));
        assertThrows(RuntimeException.class, () -> list.get(1));
    }

    @Test
    void testSetIndexOutOfBounds() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(1);

        assertThrows(RuntimeException.class, () -> list.set(-1, 10));
        assertThrows(RuntimeException.class, () -> list.set(1, 10));
    }

    @Test
    void testAddIndexOutOfBounds() {
        MyLinkedList list = new MyLinkedList();

        assertThrows(RuntimeException.class, () -> list.add(-1, 10));
        assertThrows(RuntimeException.class, () -> list.add(1, 10));
    }

    @Test
    void testRemoveIndexOutOfBounds() {
        MyLinkedList list = new MyLinkedList();

        assertThrows(RuntimeException.class, () -> list.remove(0));

        list.addLast(1);
        assertThrows(RuntimeException.class, () -> list.remove(-1));
        assertThrows(RuntimeException.class, () -> list.remove(1));
    }

    @Test
    void testRemoveFirstOnEmptyList() {
        MyLinkedList list = new MyLinkedList();

        assertThrows(RuntimeException.class, list::removeFirst);
    }

    @Test
    void testMixedOperations() {
        MyLinkedList list = new MyLinkedList();

        list.addLast(2);
        list.addFirst(1);
        list.addLast(4);
        list.add(2, 3);   // 1,2,3,4
        list.set(3, 40);  // 1,2,3,40
        int removed = list.remove(1); // remove 2 => 1,3,40

        assertEquals(2, removed);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(40, list.get(2));
    }
}