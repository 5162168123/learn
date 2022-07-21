package cn.meng.jdk.demo.collection.homework;

import java.util.ArrayList;

public class LinkedListMade<E> {


    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedListMade(){

    }

    public boolean add(E element){
        Node<E> newNode = new Node<E>(last,element,null);
        Node<E> l = last;
        last = newNode;
        if(first == null){
            first = newNode;
        }else{
            l.next = newNode;
        }
        size++;
        return true;
    }
    public boolean add(int index,E element){
        positionCheck(index);
        Node<E> newNode = new Node<E>(null,element,node(index));
        if(index == 0){
            first = newNode;
        }if(index == (size)){
            add(element);
        }else{
            Node<E> nodeP = node(index).prev;
            newNode.prev = nodeP;
            nodeP.next = newNode;
            node(index).prev = newNode;
        }
        size++;
        return true;
    }

    private void positionCheck(int index) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
    }

    public E removeFirst(){
        if(first == null){
            throw new RuntimeException("first node is null");
        }
        E element = first.element;
        if(size == 1){
            first = null;
            last = null;
        }else{
            Node<E> f = first;
            first = f.next;
            first.prev = null;
            f.next =null;
        }
        size--;
        return element;
    }
    public E removeLast(){
        if(last == null){
            throw new RuntimeException("last node is null");
        }
        E element = last.element;
        if(size == 1){
            first = null;
            last = null;
        }else{
            Node<E> l = last;
            last = l.prev;
            last.next = null;
            l.prev = null;

        }
        size--;
        return element;
    }
    public E remove(int index){
        indexCheck(index);

        if(index == 0){
            return removeFirst();
        }else if(index ==(size-1)){
            return removeLast();
        }else{
            Node<E> node = node(index);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
            return node.element;
        }

    }
    public E set(int index,E element){
        indexCheck(index);
        Node<E> oldNode = node(index);
        E oldElement = oldNode.element;
        oldNode.element = element;
        return  oldElement;
    }
    public E get(int index){
        indexCheck(index);

        return node(index).element;
    }
    public E getFirst(){
        return first.element;
    }
    public E getLast(){
        return last.element;
    }


    private void indexCheck(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<E> node(int i) {
        Node<E> now;
        if (i == 0){
            now = first;
            return now;
        }
        if (i == (size -1)) {
            now = last;
            return now;
        }

        if(i < (size >> 1)){
            now = first;
            for (int j = 0; j < i; j++) {
                now = now.next;
            }
            return now;
        }else{
            now = last;
            for (int j = size-1; j > i ; j--) {
                now = now.prev;
            }
            return now;
        }
    }

    private class Node<E>{
        Node<E> prev;
        Node<E> next;
        E element;
        public Node(Node<E> prev,E element, Node<E> next){
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

    }
    public int getSize(){
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Node<E> now = first;
        for (int i = 0; i < size-1; i++) {
            stringBuilder.append(now.element.toString()+",");
            now = now.next;
        }
        stringBuilder.append(now.element+"]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedListMade<Integer> list = new LinkedListMade<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.set(1,66);
        list.add(1,77);
        System.out.println(list.toString());
        list.removeLast();
        System.out.println(list.toString());
        list.removeFirst();
        System.out.println(list.toString());
        list.remove(0);
        System.out.println(list.toString());
        ArrayList<String> a = new ArrayList<>();
        a.forEach(i ->System.out.println(i));


    }

}
