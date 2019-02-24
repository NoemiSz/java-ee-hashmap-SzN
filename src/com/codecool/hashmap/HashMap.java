package com.codecool.hashmap;

import java.util.Arrays;
import java.util.LinkedList;

class HashMap<K, V> {

    private int bucketSize = 16;
    // This holds all the data. Its a primitive array where every element is a Linked List.
    // They Linked List holds elements of type KeyValue
    private LinkedList<KeyValue>[] elements = new LinkedList[bucketSize];


    public void add(K key, V value) {
        // find out which position of the primitive array to use:

        int position = getHash(key);
        KeyValue keyValue = new KeyValue(key, value);

        try{
            LinkedList <KeyValue> list = getListAtPositon(position);
            try{
                for(KeyValue kv: list) {
                    if(kv.getKey() != key) {
                        list.add(keyValue);
                        elements[position] = list;
                    }else{
                        throw new IllegalArgumentException("Key already exists!");
                    }
                }
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        catch (NullPointerException e){
            LinkedList <KeyValue> list = new LinkedList<>();
            list.add(keyValue);
            elements[position] = list;
        }
        // If the key already exists throw an error.
        // Make a new instance of the KeyValue class, fill it with the key, value parameters, then add it to the list.
//        resizeIfNeeded();
    }

    public V getValue(K key) {
        // 1. Calculate the hash of the key. This defines which element to get from the "elements" array
        // 2. Find in the List in this position the KeyValue element that has this key, then return its value.
        //    If none of the items in the list has this key throw error.
        int position = getHash(key);
        LinkedList <KeyValue> list = getListAtPositon(position);
        try {
            if(list != null) {
                for (KeyValue kv : list) {
                    if (kv.getKey() == key) {

                        return (V) kv.getValue();
                    }
                }
            }
            else {
                throw new IllegalArgumentException("key doesn't exist!");
            }
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void remove(K key){
        int position = getHash(key);
        LinkedList <KeyValue> list = getListAtPositon(position);
        list.removeIf(keyValue -> keyValue.getKey() == key);
    }

    public void clearAll(){
        for(LinkedList kv: elements){
            if(kv != null && !kv.isEmpty()){
                kv.remove();
            }
        }
//        elements = new LinkedList[bucketSize];
    }

    private LinkedList getListAtPositon(int position){
        return elements[position];
    }

    private int getHash(K key) {

        // This function converts somehow the key to an integer between 0 and bucketSize
        // In C# GetHashCode(), in Java hashCode() is a function of Object, so all non-primitive types
        // can easily be converted to an integer.
        int position = key.hashCode() % elements.length;
        return position;
    }

    private void resizeIfNeeded(){
    // If it holds more elements than bucketSize * 2, destroy and recreate it
    // with the double size of the elements array.
    // if it holds less elements than bucketSize / 2, destroy and recreate it
    // with half size of the elements array.
}

    @Override
    public String toString() {
        return "HashMap{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}