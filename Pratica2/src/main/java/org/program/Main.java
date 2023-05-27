package org.program;

import org.Structures.HashTable;


public class Main {
    public static void main(String[] args) {
        HashTable<String,String> table = new HashTable<>(0.7);
        table.put("1","1");
        table.put("2","2123");
        table.put("3","asd123124");
        table.put("4","1vvxa");
        table.put("5","2iojqwd");
        table.put("6","asd1oepm");
        table.put("7","1cpao");
        table.put("8","2qpej");
        table.put("9","asdr1o3k1");
        table.put("10","1cvxnm");
        table.put("11","2p1oj4");
        table.put("12","asdvpmaeomrf");

        for(int m = 13;m<100;m++){
            table.put(Integer.toString(m),"apsodkqw");
        }

        System.out.println(table.get("12"));
    }
}