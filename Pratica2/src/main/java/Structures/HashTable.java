package Structures;


//
//ChatGPT version
//


import java.awt.image.Kernel;

public class HashTable<K, V> {
    private int size = 100;
    private int quantity =0;
    private Double LOADFACTOR;
    private Entry<K, V>[] table;

    public HashTable(Double LOADFACTOR) {
        table = new Entry[size];
        this.LOADFACTOR = LOADFACTOR;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> entry = new Entry<>(key, value);

        if (table[index] == null) {
            table[index] = entry;
            quantity++;
            reallocateIfLoadFactor();
        } else {
            int count =0;
            do{
                count++;
                index = quadraticProbing(index,count);
            }
            while(table[index] != null);
            if (count>=size){
                //nao tem na tabela
            }
            table[index] = entry;
            quantity++;
            reallocateIfLoadFactor();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        int count = 0;

        while (table[index] != null && count < size) {
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
            }

            count++;
            index = quadraticProbing(index, count);
        }


        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        if (entry != null && entry.getKey().equals(key)) {
            table[index] = null;
            quantity--;
        }

        int newIndex = (index + 1) % size;
        while (newIndex != index) {
            entry = table[newIndex];
            if (entry != null && entry.getKey().equals(key)) {
               table[newIndex] = null;
               return;
            }
            newIndex = (newIndex + 1) % size;
        }
    }

    private int getIndex(K key) {
        int hash = 0;

        if (key != null) {
            String strKey = key.toString();
            int prime = 31;

            for (int i = 0; i < strKey.length(); i++) {
                hash = (prime * hash + strKey.charAt(i)) % size;
            }
        }

        return hash;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }



    private void reallocateIfLoadFactor(){
        if(quantity/size >= LOADFACTOR){
            Entry<K,V>[] newTable = new Entry[size*2];
            for(int i =0;i<size;i++){
                if(table[i] != null){
                    newTable[i] = table[i];
                }
            }
            table = newTable;
            size = size*2;
        }
    }
    private int quadraticProbing(int index, int attempt){
        return (index + attempt *attempt)%size;
    }
}
