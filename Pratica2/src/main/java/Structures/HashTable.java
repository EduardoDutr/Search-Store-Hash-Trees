package Structures;

public class HashTable<K, V> {
    private int size = 5;
    private int quantity =0;
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
        if(entry!=null) return entry.getValue();
        return null;

        //existe a chance de ser null ! tratar.
        //return quadraticGet(key, index);
    }

    public void remove(K key) {
        int index = getIndex(key);
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.getNextEntry();
                } else {
                    prev.setNextEntry(current.getNextEntry());
                }
                quantity--;
                return;
            }
            prev = current;
            current = current.getNextEntry();
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
        private Entry<K,V> nextEntry;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            nextEntry = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Entry<K, V> getNextEntry() {
            return nextEntry;
        }

        public void setNextEntry(Entry<K, V> nextEntry) {
            this.nextEntry = nextEntry;
        }
    }

    private void reallocateIfLoadFactor() {
        if ((double) quantity / size >= LOADFACTOR) {
            size = size * 2;
            Entry<K, V>[] newTable = new Entry[size];

            for (Entry<K, V> entry : table) {
                reallocate(newTable, entry);
            }

            table = newTable;
        }
    }

    private void reallocate(Entry<K, V>[] newTable, Entry<K, V> entry) {
        if (entry != null) {
            Entry<K, V> aux;
            if (entry.getNextEntry() != null) {
                reallocate(newTable, entry.getNextEntry());
            }

            int newIndex = getIndex(entry.key);

            if (newTable[newIndex] != null) {
                aux = newTable[newIndex];
                while (aux.getNextEntry() != null) {
                    aux = aux.getNextEntry();
                }
                aux.setNextEntry(entry);
                entry.setNextEntry(null);
            } else {
                newTable[newIndex] = entry;
                entry.setNextEntry(null);
            }

            quantity++;
        }
    }



    private int quadraticProbing(int index, int attempt){
        return (index + attempt *attempt)%size;
    }
    private void quadraticPut(Entry entry,int index){
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
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
            }
            count++;
            index = quadraticProbing(index, count);
        }
        return null;
    }

    //trata caso de repeticao de chaves
    private void collisionPutByLinkedList(Entry pos, Entry entry){
        Entry entryAux = pos;
        if(entryAux.getNextEntry()!= null){
            collisionPutByLinkedList(entryAux.getNextEntry(),entry);
        }
        entryAux.setNextEntry(entry);
    }
    private Entry<K,V> collisionGetByLinkedList(K key,Entry pos){
        Entry entry = pos;
        if(entry==null) return null;
        if(entry.getKey().equals(key)) return entry;
        if(entry.getNextEntry() != null){
            return collisionGetByLinkedList(key,entry.getNextEntry());
        }
        return null;
    }
}














