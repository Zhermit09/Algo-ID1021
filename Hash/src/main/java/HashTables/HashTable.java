package HashTables;

public class HashTable {

    int mod;
    Bucket[] arr;

    class Bucket {
        private HashZip.Node[] buff;

        Bucket(HashZip.Node item) {
            buff = new HashZip.Node[1];
            buff[0] = item;
        }

        void add(HashZip.Node item) {
            HashZip.Node[] copy = new HashZip.Node[buff.length + 1];
            System.arraycopy(buff, 0, copy, 0, buff.length);
            copy[buff.length] = item;
            buff = copy;
        }
    }

    public HashTable(int mod) {
        arr = new Bucket[mod];
        this.mod = mod;
    }

    public void add(int idx, HashZip.Node item) {
        int i = idx % mod;

        if (arr[i] == null) {
            arr[i] = new Bucket(item);
            return;
        }
        arr[i].add(item);
    }

    public HashZip.Node find(int idx) {
        Bucket bucket = arr[idx % mod];
        if (bucket == null) {
            return null;
        }

        if (bucket.buff.length == 1 && bucket.buff[0].code == idx) {
            return bucket.buff[0];
        }

        for (HashZip.Node item : bucket.buff) {
            if (item.code == idx) {
                return item;
            }
        }
        return null;
    }
}
