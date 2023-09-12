import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


class Sort_Test {

    public static void main(String[] args) {
        Random rnd = new Random();
        int[] arr1;
        int[] arr2;
        while (true) {
            arr1 = CreateUnsorted(rnd.nextInt(999999));
            arr2 = new int[arr1.length];
            System.arraycopy(arr1, 0, arr2, 0, arr1.length);
            Sort.mergeSort2(arr1);
            Arrays.sort(arr2);

            if (!Arrays.equals(arr1, arr2)) {
                break;
            }
            System.out.println("Good...");
        }
        System.out.println("You fucked up!");
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr1));

    }

    static int[] CreateSorted(int n) {
        int[] array = new int[n];

        Random rnd = new Random();
        int nxt = rnd.nextInt();

        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt += rnd.nextInt() + 1;
        }
        return array;
    }

    static int[] CreateUnsorted(int n) {
        int[] array = new int[n];
        ArrayList<Integer> list = new ArrayList<>();

        for (int i : CreateSorted(n)) {
            list.add(i);
        }

        Collections.shuffle(list);

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;

    }


}