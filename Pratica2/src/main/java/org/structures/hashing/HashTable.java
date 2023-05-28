package org.structures.hashing;


//
//ChatGPT version
//not anymore :)

@SuppressWarnings("unchecked")
public class HashTable<K, V> {
    private int size = 10;
    private int quantity = 0;
    private final Double LOADFACTOR;
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
            collisionPutByLinkedList(table[index],entry);
            //quadraticPut(entry,index);
        }
    }

    public V get(K key) {
        int index = getIndex(key);

        Entry<K, V> entry = collisionGetByLinkedList(key,table[index]);
        if(entry!=null) return entry.value;
        return null;

        //existe a chance de ser null ! tratar.
        //return quadraticGet(key, index);
    }

    public void remove(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        if (entry != null && entry.key.equals(key)) {
            table[index] = null;
            quantity--;
        }

        int newIndex = (index + 1) % size;
        while (newIndex != index) {
            entry = table[newIndex];
            if (entry != null && entry.key.equals(key)) {
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
        private final K key;
        private final V value;
        private Entry<K,V> nextEntry;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            nextEntry = null;
        }

        public Entry<K, V> getNextEntry() {
            return nextEntry;
        }

        public void setNextEntry(Entry<K, V> nextEntry) {
            this.nextEntry = nextEntry;
        }
    }


//erro, adaptar para os metodos quadratico e linked list
    private void reallocateIfLoadFactor(){
        if((double)quantity/size >= LOADFACTOR){
            size *= 2;

            Entry<K, V>[] newTable = new Entry[size];

            for(int i = 0; i < size/2; i++){
                if(table[i] != null){
                    reallocate(table[i], newTable);
                }
            }
            table = newTable;
        }
    }

    private void reallocate(Entry<K,V> element, Entry<K, V>[] newTable){
        Entry<K, V> aux;

        if(newTable[getIndex(element.key)] != null){
            aux = newTable[getIndex(element.key)];
            while(aux.getNextEntry() != null){
                aux = aux.getNextEntry();
            }
            aux.setNextEntry(element);
        }
        else{
            newTable[getIndex(element.key)] = element;
        }

        if (element.nextEntry != null) {
            aux = element.nextEntry;
            element.nextEntry = null;
            reallocate(aux, newTable);
        }

        quantity++;
    }


    private int quadraticProbing(int index, int attempt){
        return (index + attempt *attempt)%size;
    }
    private void quadraticPut(Entry<K, V> entry,int index){
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
    private V quadraticGet(K key, int index){
        int count = 0;
        while (table[index] != null && count < size) {
            if (table[index].key.equals(key)) {
                return table[index].value;
            }
            count++;
            index = quadraticProbing(index, count);
        }
        return null;
    }

    //trata caso de repeticao de chaves
    private void collisionPutByLinkedList(Entry<K, V> pos, Entry<K, V> entry){
        if(pos.getNextEntry()!= null){
            collisionPutByLinkedList(pos.getNextEntry(),entry);
        }
        pos.setNextEntry(entry);
//        while(!pos.getNextEntry().equals(null)){
//            pos = pos.getNextEntry();
//        }
//        pos.setNextEntry(entry);
    }
    private Entry<K,V> collisionGetByLinkedList(K key,Entry<K, V> pos){
        if(pos.key.equals(key))
            return pos;
        if(pos.getNextEntry() != null){
            return collisionGetByLinkedList(key, pos.getNextEntry());
        }
        return null;
        //if(entry.getNextEntry())
//        while(entry != null){
//            if(entry.getKey().equals(key)) break;
//            entry = pos.getNextEntry();
//        }
//        return entry;
    }
}
