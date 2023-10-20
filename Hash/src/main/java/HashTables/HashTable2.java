package HashTables;

public class HashTable2 {
    int mod;
    HashZip.Node[] arr;

    public HashTable2(int mod) {
        arr = new HashZip.Node[mod];
        this.mod = mod;
    }

    public void add(int idx, HashZip.Node item) {
        int i = idx % mod;

        if (arr[i] == null) {
            arr[i] = item;
            return;
        }

        while (arr[i] != null) {
            i++;
        }
        arr[i] = item;
    }

    public int find(int idx) {

        if (arr[idx % mod] == null) {
            return 0;
        }

        int count = 0;
        for (int i = idx % mod; i < arr.length; i++) {
            count++;
            HashZip.Node item = arr[i];
            if (item == null){
                return count;
            }

            if (item.code == idx) {
                return count;
            }
        }
        return count;
    }
}
