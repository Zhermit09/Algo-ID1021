import java.util.Arrays;
import java.util.Random;


class QuickSort_Test {

    static int run = 10_000;
    static int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
    static int[][][] data1 = new int[sizes.length][run][];
    //static int[][][] data2 = new int[sizes.length][run][];
    static QSortList[][] data3 = new QSortList[sizes.length][run];
    static long[][] results1 = new long[sizes.length][run];
    //static long[][] results2 = new long[sizes.length][run];
    static long[][] results3 = new long[sizes.length][run];
    static int[] dummy = new int[run];

    public static void main(String[] args) {
        run();

    }

    static void CheckSort() {
        Random rnd = new Random();
        int[] arr1;
        int[] arr2;
        int i = 0;

        while (true) {
            arr1 = CreateUnsorted(rnd.nextInt(99999));
            arr2 = new int[arr1.length];
            System.arraycopy(arr1, 0, arr2, 0, arr1.length);
            QuickSort.qSort(arr1);
            Arrays.sort(arr2);

            if (!Arrays.equals(arr1, arr2)) {
                break;
            }
            if (i > 200) {
                System.out.println("Good...");
                i = 0;
            }
            i++;
        }
        System.out.println("You fucked up!");
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr1));
    }

    static int[] CreateUnsorted(int n) {
        int[] array = new int[n];
        Random rnd = new Random();
        int nxt = rnd.nextInt();

        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt = rnd.nextInt();
        }
        return array;

    }

    static void prep(int i) {
        Random rnd = new Random();
        int nxt;

            for (int j = 0; j < run; j++) {
                data1[i][j] = new int[sizes[i]];
                //data2[i][j] = new int[sizes[i]];
                data3[i][j] = new QSortList();

                for (int k = 0; k < sizes[i]; k++) {
                    nxt = rnd.nextInt();
                    data1[i][j][k] = nxt;
                    //data2[i][j][k] = nxt;

                }

                for (int k = sizes[i] - 1; k >= 0; k--) {
                    data3[i][j].add(new QSortList.Node(data1[i][j][k]));

                }

            }
    }

    static void run() {

        for (int i = 0; i < sizes.length; i++) {

            int runs = run;
            while (runs > 0) {
                bench(CreateUnsorted(sizes[i]), CreateUnsorted(sizes[i]), new QSortList(), Enum.ARRAY);
                runs--;
            }

            runs = run;
            while (runs > 0) {
                bench(CreateUnsorted(sizes[i]), CreateUnsorted(sizes[i]), new QSortList(), Enum.LINKED);
                runs--;
            }
        }

        for (int i = 0; i < sizes.length; i++) {

            prep(i);

            int runs = run;
            while (runs > 0) {
                runs--;
                results1[i][runs] = bench(data1[i][runs], dummy, new QSortList(), Enum.ARRAY);
            }

            runs = run;
            while (runs > 0) {
                runs--;
                results3[i][runs] = bench(dummy, dummy, data3[i][runs], Enum.LINKED);
            }

            data1[i] = null;
            data3[i] = null;

        }

        System.out.println("Array Quick Sort");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("#%10d", sizes[i]);
            data(results1[i]);
        }

        System.out.println("\n\nLinked List Quick Sort");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("#%10d", sizes[i]);
            data(results3[i]);
        }

    }

    static long bench(int[] d1, int[] d2, QSortList d3, Enum e) {
        long t0, t1;

        if (e == Enum.ARRAY) {


            t0 = System.nanoTime();
            QuickSort.qSort(d1);
            t1 = System.nanoTime();

            return t1 - t0;


        } else if (e == Enum.JAVA) {


            t0 = System.nanoTime();
            Arrays.sort(d2);
            t1 = System.nanoTime();

            return t1 - t0;

        } else if (e == Enum.LINKED) {


            t0 = System.nanoTime();
            QuickSort.qSort(d3);
            t1 = System.nanoTime();

            return t1 - t0;

        }

        return Long.MIN_VALUE;
    }

    static void data(long[] data) {
        long sum = 0;
        double avg;

        Arrays.sort(data);

        for (long item : data) {
            sum += item;
        }
        avg = (sum / (double) data.length);

        System.out.printf("%15d%15d%15.3f%15d\n",
                data[0],
                data[data.length - 1],
                avg,
                data[(int) (data.length / 2.0 + 0.5)]);
    }

    enum Enum {
        ARRAY, LINKED, JAVA
    }
}