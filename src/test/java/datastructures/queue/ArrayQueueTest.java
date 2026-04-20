package datastructures.queue;

import datastructures.queue.examples.ArrayQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayQueueTest {

    @Test
    void testEnqueue() {

        ArrayQueue queue = new ArrayQueue(2);

        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1,queue.dequeue());
        assertEquals(2,queue.dequeue());
    }

}
