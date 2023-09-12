class Sort {

    public static void main(String[] args) {

        int[] a = {8, 7, 6, 5, 4, 3, 2, 1}; //7,6,5,4, 3,
        mergeSort2(a);
        System.out.println();
    }

    public static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void insertionSort(int[] arr) {
        int i = 1;

        while (i < arr.length) {
            int j = i;

            while ((j > 0) && (arr[j] < arr[j - 1])) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
            i++;
        }

    }

    public static void mergeSort1(int[] A) {
        int[] B = new int[A.length];
        split1(A, B, 0, A.length - 1);

    }

    private static void split1(int[] A, int[] B, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;

            split1(A, B, start, mid);
            split1(A, B, mid + 1, end);

            merge1(A, B, start, end, mid);
        }
    }

    private static void merge1(int[] A, int[] B, int start, int end, int mid) {

        System.arraycopy(A, start, B, start, end - start + 1);

        merge(A, B, start, end, mid);

    }

    public static void mergeSort2(int[] A) {
        int[] B = new int[A.length];
        System.arraycopy(A, 0, B, 0, A.length);
        split2(A, B, 0, A.length - 1);

    }

    private static void split2(int[] A, int[] B, int start, int end) {

        if (start < end) {

            int mid = (start + end) / 2;

            split2(B, A, start, mid);
            split2(B, A, mid + 1, end);

            merge2(A, B, start, end, mid);
        }
    }

    private static void merge2(int[] A, int[] B, int start, int end, int mid) {

        merge(A, B, start, end, mid);
    }

    private static void merge(int[] A, int[] B, int start, int end, int mid) {
        int i = start;
        int j = mid + 1;

        for (int k = start; k <= end; k++) {
            if (i > mid) {
                A[k] = B[j];
                j++;
            } else if (j > end || B[i] < B[j]) {
                A[k] = B[i];
                i++;
            } else {
                A[k] = B[j];
                j++;
            }
        }
    }
}