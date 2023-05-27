package Structures;


//
//ChatGPT version
//


public class HashTable<K, V> {
    private final int SIZE = 10;
    private Entry<K, V>[] table;

    public HashTable() {
        table = new Entry[SIZE];
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> entry = new Entry<>(key, value);

        if (table[index] == null) {
            table[index] = entry;
        } else {
            // Tratamento de colisões, por exemplo, sondagem linear
            int newIndex = (index + 1) % SIZE;
            while (newIndex != index && table[newIndex] != null) {
                newIndex = (newIndex + 1) % SIZE;
            }

            if (newIndex == index) {
                throw new IllegalStateException("A hashtable está cheia.");
            } else {
                table[newIndex] = entry;
            }
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        if (entry != null && entry.getKey().equals(key)) {
            return entry.getValue();
        }

        // Tratamento de colisões, por exemplo, sondagem linear
        int newIndex = (index + 1) % SIZE;
        while (newIndex != index) {
            entry = table[newIndex];
            if (entry != null && entry.getKey().equals(key)) {
                return entry.getValue();
            }
            newIndex = (newIndex + 1) % SIZE;
        }

        return null; // A chave não existe na hashtable
    }

    public void remove(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        if (entry != null && entry.getKey().equals(key)) {
            table[index] = null;
        }

        // Tratamento de colisões, por exemplo, sondagem linear
        int newIndex = (index + 1) % SIZE;
//        while (newIndex != index) {
//            entry = table[newIndex];
//            if (entry != null && entry.getKey().equals(key)) {
//                table[newIndex] = null;
//                return;
//            }
//            newIndex = (newIndex + 1) % SIZE;
//        }
    }

    private int getIndex(K key) {
        // Lógica para calcular o índice com base na chave
        // Você pode usar uma função de hash para isso
        return key.hashCode() % SIZE;
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
}
