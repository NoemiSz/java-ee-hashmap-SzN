package com.codecool.hashmap;

public class Main {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.add("Key", 5);
        System.out.println(hashMap.toString());

        hashMap.add(3,"value");
        System.out.println(hashMap.toString());
        hashMap.add(4,"new");
        System.out.println(hashMap.toString());
        hashMap.add(4,"old");
        System.out.println(hashMap.toString());
        System.out.println(hashMap.getValue(3));

        hashMap.getValue(5);
        hashMap.remove(4);
        System.out.println(hashMap.toString());
        hashMap.clearAll();
        System.out.println(hashMap.toString());
    }
}
