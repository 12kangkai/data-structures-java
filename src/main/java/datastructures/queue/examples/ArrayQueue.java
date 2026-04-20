package datastructures.queue.examples;

public class ArrayQueue {

    private int[] data;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        data = new int[capacity];
    }

    public void enqueue(int val){
        if (tail == data.length){
            throw new RuntimeException("Queue full");
        }
        data[tail++] = val;
    }

    public int dequeue(){
        if (head == tail){
            throw new RuntimeException("Queue empty");
        }
        return data[head++];
    }

}
