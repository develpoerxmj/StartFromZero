package com.xmj.startfromzero.sort;


import java.util.NoSuchElementException;

//自定义单链表
public class LinkedList<E> {

    private static class Node<E>{

        E item;

        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<E> last;
    private Node<E> first;
    private int size;

    public int getSize() {
        return size;
    }

    /**
     * 尾部添加
     * @param e
     */
    public void add(E e){
        Node<E> node = new Node<>(e, null);
        Node<E> l = last;
        last = node;
        if (l == null){
            first = node;
        }else {
            l.next = node;
        }
        size++;
    }

    /**
     * 移除头节点
     * @return
     */
    public E remove(){
        Node<E> f = first;
        if (f == null){
            throw new NoSuchElementException();
        }
        first = f.next;
        if (first == null){
            last = null;
        }
        size--;
        return f.item;
    }

    /**
     * 添加列表
     * @param list
     */
    public void addAll(LinkedList<E> list){
        while (list.size > 0){
            add(list.remove());
        }
        size += list.size;
    }
}
