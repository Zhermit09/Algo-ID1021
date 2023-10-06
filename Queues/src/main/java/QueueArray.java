public class QueueArray<T> {
    int first;
    int last;
    private T[] arr;

    public QueueArray() {
        first = last = 0;
        arr = (T[]) (new Object[4]);
    }

    public boolean empty(){
        return first == last;
    }

    public T get(int i){
        return arr[i];
    }
    public void add(T item) {

        if(item == null){
            return;
        }

        arr[last] = item;
        last++;

        if (last >= arr.length) {
            last %= arr.length;
        }

        if (first == last) {
            extend();
        }
    }

    public T remove() {
        int temp = first;

        if (last == first) {
            return null;
        }

        first++;

        if (first >= arr.length) {
            first %= arr.length;
        }

        return arr[temp];
    }

    private void extend() {
        T[] temp = (T[]) (new Object[arr.length * 2]);
        /*for (int i = f; i < arr.length; i++) {
            temp[i - f] = arr[i];
        }*/
        System.arraycopy(arr, first, temp, 0, arr.length - first);
        /*for (int i = 0; i < f; i++) {
            temp[i + arr.length - f] = arr[i];
        }*/
        System.arraycopy(arr, 0, temp, arr.length - first, first);

        first = 0;
        last = arr.length;
        arr = temp;
    }


}