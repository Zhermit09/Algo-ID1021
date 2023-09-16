import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


class Sort_Test {

    static final int samples = 100;
    static int run = 1_000;
    static int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
    static int[][] data = new int[sizes.length][];
    static long[][] results = new long[sizes.length][];
    static long[] dummy = new long[run];

    public static void main(String[] args) {
        prep();
        run();

    }

    static void prep() {
        for (int i = 0; i < sizes.length; i++) {
            int[] temp = CreateUnsorted(sizes[i]);

            for (int j = 0; j < temp.length / 2; j++) {
                int t = temp[j];
                temp[j] = temp[temp.length - j - 1];
                temp[temp.length - j - 1] = t;
            }
            data[i] = temp;
            results[i] = new long[run];
        }
    }

    static void run() {


        for (int i = 0; i < sizes.length; i++) {
            bench(data[i], dummy);
        }

        System.out.println("\nSort");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(data[i], results[i]);
            System.out.printf("#%10d", sizes[i]);
            data(results[i]);
        }
    }

    static void bench(int[] arr, long[] results) {
        long t0, t1;
        int runs = run;
        while (runs > 0) {

            t0 = System.nanoTime();
            for (int i = 0; i < samples; i++) {
                Sort.insertionSort(arr);
            }
            t1 = System.nanoTime();
            results[run - runs] = t1 - t0;

            runs--;
        }
    }

    static void data(long[] data) {
        long sum = 0;
        double avg = 0;

        Arrays.sort(data);

        for (long item : data) {
            sum += item;
        }
        avg = (sum / (double) data.length);

        System.out.printf("%15d%15d%15.3f%15d\n",
                data[0],
                data[data.length-1],
                avg,
                data[(int) (data.length / 2.0 + 0.5)]);
    }

    static void CheckSort() {
        Random rnd = new Random();
        int[] arr1;
        int[] arr2;
        while (true) {
            arr1 = CreateUnsorted(rnd.nextInt(99999));
            arr2 = new int[arr1.length];
            System.arraycopy(arr1, 0, arr2, 0, arr1.length);
            Sort.insertionSort(arr1);
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
        int nxt = rnd.nextInt(10);

        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt += rnd.nextInt(10) + 1;
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