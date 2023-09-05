class SortedArray {
    public static void main(String[] args) {

        System.out.println("Yeet");
    }

    public static boolean search_unsorted(int[] array, int key) {
        for (int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }
}