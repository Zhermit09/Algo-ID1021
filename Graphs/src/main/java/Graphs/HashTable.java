package Graphs;

public class HashTable {

    static int mod;
    static int collisions = 0;
    Bucket[] arr;

    static class Bucket {
        public City[] buff;

        Bucket(City item) {
            buff = new City[1];
            buff[0] = item;
        }

        void add(City item) {
            City[] copy = new City[buff.length + 1];
            System.arraycopy(buff, 0, copy, 0, buff.length);
            copy[buff.length] = item;
            buff = copy;
            collisions++;
        }
    }

    public HashTable(int mod) {
        this.mod = mod;
        arr = new Bucket[mod];
    }

    public static Integer hash(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = ((hash * 31) % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public void add(City city) {
        int i = hash(city.name);

        if (arr[i] == null) {
            arr[i] = new Bucket(city);
            return;
        }
        arr[i].add(city);
    }

    public City find(String name) {
        Bucket bucket = arr[hash(name)];

        if (bucket == null) {
            return null;
        }

        if (bucket.buff.length == 1 && bucket.buff[0].name.equals(name)) {
            return bucket.buff[0];
        }

        for (City item : bucket.buff) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }
}
