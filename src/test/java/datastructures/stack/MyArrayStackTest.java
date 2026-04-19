package datastructures.stack;

import datastructures.stack.examples.MyArrayStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyArrayStackTest {

    @Test
    void testPushPop() {
        MyArrayStack stack = new MyArrayStack(2);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3,stack.pop());
        assertEquals(2,stack.pop());
        assertEquals(1,stack.pop());
    }

    @Test
    void testPeek() {
        MyArrayStack stack = new MyArrayStack(2);

        stack.push(10);

        assertEquals(10, stack.peek());
    }

    @Test
    void testEmpty() {
        MyArrayStack stack = new MyArrayStack(2);
        assertTrue(stack.isEmpty());
    }
}
