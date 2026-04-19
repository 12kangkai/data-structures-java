package datastructures.stack.examples;

public class MyArrayStack {
    private int[] data;
    private int size;

    public MyArrayStack(int capacity) {
        data = new int[Math.max(1,capacity)];
    }

    public void push(int val){
        if (size == data.length){
            resize();
        }
        data[size++] = val;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        return data[--size];
    }

    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        return data[size-1];
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        int newCap = data.length + data.length >> 1;
        if (newCap == data.length){
            newCap = data.length + 1;
        }
        int[] newArr = new int[newCap];
        System.arraycopy(data, 0, newArr, 0, size);
        data = newArr;
    }


}
