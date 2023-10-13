import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;


class Heap_Test {

    static int run = 10_000;
    static int samples = 1_00;
    static int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
    static long[][] addResults = new long[sizes.length][run];
    static long[][] remResults = new long[sizes.length][run];
    static int[] data;
    static int[] rnd;

    public static void main(String[] args) {
        data = CreateUnsorted(sizes[sizes.length - 1]);
        rnd = CreateUnsorted(sizes[sizes.length - 1]);

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

    static PrioList[] prep(int size) {
        PrioList[] copy = new PrioList[samples];

        for (int i = 0; i < samples; i++) {
            copy[i] = new PrioList();
            for (int k = 0; k < size; k++) {
                copy[i].add(data[k]);
            }
        }
        return copy;
    }


    static void run() {

        for (int i = 0; i < sizes.length; i++) {
            int runs = run;

            while (runs > 0) {
                runs--;

                bench(prep(sizes[0]));
                bench2(prep(sizes[0]));
            }
            System.out.println("Warm Up Run: " + (i + 1));

        }
        System.gc();
        System.out.println("\nWarm Up Done\n");
        for (int i = 0; i < sizes.length; i++) {
            int runs = run;

            while (runs > 0) {
                runs--;
                addResults[i][runs] = bench(prep(sizes[i]));
                remResults[i][runs] = bench2(prep(sizes[i]));
            }
            System.out.println("Bench Run: " + (i + 1));
        }

        System.out.println("List2 Add");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("#%10d", sizes[i]);
            data(addResults[i]);
        }

        System.out.println("\nList2 Remove");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("#%10d", sizes[i]);
            data(remResults[i]);
        }

    }

    static long bench(PrioList[] arr) {
        long t0, t1;

        t0 = System.nanoTime();
        for (int i = 0; i < samples; i++) {
            arr[i].add2(rnd[i]);
        }
        t1 = System.nanoTime();
        return t1 - t0;

    }

    static long bench2(PrioList[] arr) {
        long t0, t1;

        t0 = System.nanoTime();
        for (int i = 0; i < samples; i++) {
            arr[i].remove2();
        }
        t1 = System.nanoTime();
        return t1 - t0;

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