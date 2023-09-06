class SortedArray {
    public static void main(String[] args) {

        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i : arr) {
            binarySearch(arr, i);
        }
    }

    public static boolean searchUnsorted(int[] array, int key) {
        for (int i : array) {
            if (i == key) {
                return true;
            }
            if (i > key) {
                return false;
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] array, int key) {
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int i = (start + end) / 2;
            int item = array[i];

            if (item > key) {
                end = i - 1;
                continue;
            }

            if (item < key) {
                start = i + 1;
                continue;
            }
            return true;
        }
        return false;
    }

}