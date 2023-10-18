package HashTables;

public class HashTable2 {
    int mod;
    HashZip.Node[] arr;

    public HashTable2(int mod) {
        arr = new HashZip.Node[2 * mod];
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

    public HashZip.Node find(int idx) {

        if (arr[idx % mod] == null) {
            return null;
        }

        for (int i = idx % mod; i < arr.length; i++) {
            HashZip.Node item = arr[i];
            if (item == null){
                return null;
            }

            if (item.code == idx) {
                return item;
            }
        }
        return null;
    }
}
