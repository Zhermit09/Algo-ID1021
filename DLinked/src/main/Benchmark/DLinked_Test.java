import java.util.Arrays;
import java.util.Random;


class DLinked_Test {

    static final int samples = 1_000;
    static int run = 1_000;
    static int[] sizes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    static int[][] random = new int[run][];
    static LinkedList.Node[][] ref = new LinkedList.Node[sizes.length][];
    static DLinkedList.Node[][] refD = new DLinkedList.Node[sizes.length][];
    static LinkedList[] data = new LinkedList[sizes.length];
    static DLinkedList[] dataD = new DLinkedList[sizes.length];
    static long[][] results = new long[sizes.length][];
    static long[][] resultsD = new long[sizes.length][];
    static long[] dummy = new long[run];

    public static void main(String[] args) {

        prep();
        run();

    }

    static void prep() {
        for (int i = 0; i < sizes.length; i++) {

            data[i] = new LinkedList(0);
            dataD[i] = new DLinkedList(0);
            random[i] = new int[run];
            ref[i] = new LinkedList.Node[sizes[i]];
            refD[i] = new DLinkedList.Node[sizes[i]];
            results[i] = new long[run];
            resultsD[i] = new long[run];

            for (int j = 0; j < sizes[i]; j++) {
                LinkedList.Node t = new LinkedList.Node(sizes[i] - i, null);
                DLinkedList.Node tD = new DLinkedList.Node(sizes[i] - i, null, null);

                data[i].add(t);
                dataD[i].add(tD);

                ref[i][j] = t;
                refD[i][j] = tD;
            }

            Random rnd = new Random();
            int nxt = rnd.nextInt(sizes[i]);

            for (int j = 0; j < run; j++) {
                random[i][j] = nxt;
                nxt = rnd.nextInt(sizes[i]);
            }
        }
    }

    static void run() {


        for (int i = 0; i < sizes.length; i++) {
            bench(data[i], ref[i], random[i], dummy, sizes[i]);
            bench(dataD[i], refD[i], random[i], dummy, sizes[i]);
        }

        System.out.println("List");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(data[i], ref[i], random[i], results[i], sizes[i]);
            System.out.printf("#%10d", sizes[i]);
            data(results[i]);
        }

        System.out.println("DoubleList");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(dataD[i], refD[i], random[i], resultsD[i], sizes[i]);
            System.out.printf("#%10d", sizes[i]);
            data(resultsD[i]);
        }
    }

    static void bench(LinkedList list, LinkedList.Node[] ref, int[] random, long[] results, int n) {
        long t0, t1;
        int runs = run;

        int[] temp;


        while (runs > 0) {

            t0 = System.nanoTime();
            for (int i = 0; i < samples; i++) {
                list.remove(ref[random[i]]);
                list.add(ref[random[i]]);
            }
            t1 = System.nanoTime();
            results[run - runs] = t1 - t0;

            runs--;
        }
    }

    static void bench(DLinkedList list, DLinkedList.Node[] ref, int[] random, long[] results, int n) {
        long t0, t1;
        int runs = run;

        int[] temp;


        while (runs > 0) {

            t0 = System.nanoTime();
            for (int i = 0; i < samples; i++) {
                list.remove(ref[random[i]]);
                list.add(ref[random[i]]);
            }
            t1 = System.nanoTime();
            results[run - runs] = t1 - t0;

            runs--;
        }
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
}