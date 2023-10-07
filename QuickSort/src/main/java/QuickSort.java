class QuickSort {

    public static void main(String[] args) {

        System.out.println();
    }

    public static void qSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int i, int j) {
        if (i < j) {
            int mid = partition(arr, i, j);

            sort(arr, i, mid - 1);
            sort(arr, mid + 1, j);
        }

    }

    public static int partition(int[] arr, int i, int j) {
        int p = i;
        int pivot = arr[i];

        while (i < j) {

            if (arr[j] >= pivot) {
                j--;
            } else if (arr[i] <= pivot) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
        int temp = arr[p];
        arr[p] = arr[j];
        arr[j] = temp;
        return j;
    }

    public static void qSort(QSortList list) {
        if (list.length() > 1) {
            QSortList small = new QSortList();
            QSortList big = new QSortList();

            QSortList.Node pivot = partition(list, small, big);

            qSort(small);
            qSort(big);

            big.add(pivot);
            list.append(small);
            list.append(big);
        }
    }

    public static QSortList.Node partition(QSortList list, QSortList small, QSortList big) {
        QSortList.Node pivot = list.remove();
        int p = pivot.val;
        QSortList.Node curr = list.remove();

        while (curr != null) {

            if (p <= curr.val) {
                big.add(curr);
            } else {
                small.add(curr);
            }
            curr = list.remove();
        }

        return pivot;
    }
}


