package datastructures.array.examples;

import java.util.ArrayList;

public class MyArray {

    private int[] data;
    private int size;

    public MyArray(int capacity) {
        data = new int[capacity];
    }

    public void add(int val){
        if (size == data.length) resize();
        data[size++] = val;
    }

    public int get(int index){
        check(index);
        return data[index];
    }

    public void set(int index, int val){
        check(index);
        data[index] = val;
    }

    public int remove(int index){
        check(index);
        int res = data[index];

        for(int i = index; i < size - 1; i++){
            data[i] = data[i + 1];
        }

        size--;
        return res;
    }

    private void resize(){
        int newCap = data.length + (data.length >> 1);
        if (newCap - data.length == 0){
            newCap = data.length + 1;
        }
        int[] newArr = new int[newCap];

        System.arraycopy(data,0,newArr,0, data.length);
        data = newArr;
    }

    private void check(int index){
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException();
    }
}
