package com.project.task.util.HashMaps.impl;

import com.project.task.util.HashMaps.HashMap;

import java.util.*;


public class MyHashMap<K, V> implements HashMap<K, V> {
    private class Node {
        private V value;
        private K key;
        private Node next;

        public Node() {
        }

        Node(V value, K key) {
            this.value = value;
            this.key = key;
            next = null;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        void setValue(V value) {
            this.value = value;
        }
    }
    private final int listLength = 1000;
    private ArrayList<Node> list = new ArrayList<>(listLength);

    public MyHashMap() {
        for(int i=0;i<listLength;i++){
            list.add(null);
        }
    }

    public Set<K> keySet() {
        Set<K> set =new TreeSet<>();
        for(Node node:list){
            if(node != null){
                Node currentNode = node;
                while (currentNode.getNext() != null){
                    set.add(currentNode.getKey());
                    currentNode = currentNode.getNext();
                }
            }
        }
        return set;
    }

    public V get(K key) {
        int hash = Objects.hashCode(key);
        if(list.get(hash & (listLength-1)) != null){
            Node currentNode;
            currentNode = list.get(hash & (listLength-1));
            while(currentNode.getNext() != null){
                if(currentNode.getKey().equals(key)){
                    return currentNode.getValue();
                }
                currentNode = currentNode.getNext();
            }
            return null;
        }else {
            return null;
        }
    }

    public void put(K key, V value) {
        int hash = Objects.hashCode(key);
        if(list.get(hash & (listLength-1)) != null){
            Node currentNode;
            currentNode = list.get(hash & (listLength-1));
            while(currentNode.getNext() != null){
                if(currentNode.getKey().equals(key)){
                    currentNode.setValue(value);
                    return;
                }
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(new Node(value,key));
        }else {
            list.set(hash & (listLength - 1), new Node(value, key));
        }
    }
}