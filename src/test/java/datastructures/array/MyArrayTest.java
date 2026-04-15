package datastructures.array;

import datastructures.array.examples.MyArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyArrayTest {

    @Test
    void testAddAndGet() {
        MyArray arr = new MyArray(2);

        arr.add(10);
        arr.add(20);

        assertEquals(10, arr.get(0));
        assertEquals(20, arr.get(1));
    }

    @Test
    void testResize() {
        MyArray arr = new MyArray(1);

        arr.add(10);
        arr.add(20);
        arr.add(30);

        assertEquals(20,arr.get(1));
    }

    @Test
    void testRemove() {
        MyArray arr = new MyArray(3);

        arr.add(1);
        arr.add(2);
        arr.add(3);

        int removed = arr.remove(1);

        assertEquals(2, removed);
        assertEquals(3, arr.get(1));
    }

    @Test
    void testIndexOutOfBounds() {
        MyArray arr = new MyArray(2);

        arr.add(1);

        assertThrows(RuntimeException.class, () -> {
            arr.get(5);
        });
    }
}
