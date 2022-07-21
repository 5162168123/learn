package cn.meng.jdk.demo.collection.homework;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListMade<E> {
    private Object[] elementData;
    private int DEFAULT_LENGTH = 10;
    private int size;

    public ArrayListMade(){
        this.elementData = new Object[DEFAULT_LENGTH];
    }

    public ArrayListMade(int cap){
        if(cap >= 0 && cap <= 10){
            this.elementData = new Object[DEFAULT_LENGTH];
        }else if(cap == 0){
            this.elementData = new Object[DEFAULT_LENGTH];
        }else{
            throw new RuntimeException("out of index");
        }
    }

    public boolean add(E e){
        ensureCapacity(size+1);
        elementData[size++] = e;
        return true;
    }
    public E get(int index){
        indexCheck(index);
        return (E) elementData[index];
    }

    public boolean set(int index,E element){
        indexCheck(index);
        elementData[index] = element;
        return true;
    }

    public boolean add(int index,E element){
        indexCheck(index);
        ensureCapacity(size+1);
        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index] = element;
        size++;
        return true;
    }
    public E remove(int index) {
        Object element = elementData[index];
        indexCheck(index);
        System.arraycopy(elementData,index+1,elementData,index,size-index-1);
        size--;
        return (E) element;
    }

    private void indexCheck(int index) {
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("index: " + index + "invalid");
        }
    }


    private void ensureCapacity(int minSize) {
        calculateCapacity(elementData,minSize);
    }

    private void calculateCapacity(Object[] elementData, int minSize) {
        int oldSize = elementData.length;
        if(minSize - elementData.length > 0){
            grow(oldSize+(oldSize >> 1));
        }
    }

    private void grow(int newSize) {
        elementData = Arrays.copyOf(elementData, newSize);
    }

    public static void main(String[] args) {
        ArrayListMade<Integer> arrayListMade = new ArrayListMade();
        int a = 1;
        int b  = 2;
        for (int i = 0; i < 10; i++) {
            arrayListMade.add(i);
        }
        arrayListMade.add(10);
        arrayListMade.add(10);
//        arrayListMade.set(1,5);
//        arrayListMade.add(1, 6);
        System.out.println(arrayListMade.toString());
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(elementData[i]+",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
