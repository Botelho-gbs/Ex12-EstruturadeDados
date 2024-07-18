public class HashTable<K, V> {
    private static final int INITIAL_SIZE = 16;
    private K[] keys;
    private V[] values;
    private int size;

    public HashTable() {
        keys = (K[]) new Object[INITIAL_SIZE];
        values = (V[]) new Object[INITIAL_SIZE];
        size = 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % keys.length;
    }

    public void put(K key, V value) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
            i = (i + 1) % keys.length;  // Sondagem linear
        }
        keys[i] = key;
        values[i] = value;
        size++;

        if (size >= keys.length / 2) {
            resize(2 * keys.length);  // Redimensionar a tabela hash se estiver meio cheia
        }
    }

    public V get(K key) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return values[i];
            }
            i = (i + 1) % keys.length;  // Sondagem linear
        }
        return null;
    }

    private void resize(int newSize) {
        HashTable<K, V> temp = new HashTable<>();
        temp.keys = (K[]) new Object[newSize];
        temp.values = (V[]) new Object[newSize];
        temp.size = 0;

        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }

        keys = temp.keys;
        values = temp.values;
        size = temp.size;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
