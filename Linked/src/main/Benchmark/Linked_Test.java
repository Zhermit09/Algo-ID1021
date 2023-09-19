import java.util.Arrays;


class Linked_Test {

    static final int samples = 1_000;
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

            data[i] = new int[sizes[i]]; //100
            results[i] = new long[run];
        }
    }

    static void run() {


        for (int i = 0; i < sizes.length; i++) {
            bench(data[i], dummy, sizes[i]);
        }

        System.out.println("B = varying, A = 100");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(data[i], results[i], sizes[i]);
            System.out.printf("#%10d", sizes[i]);
            data(results[i]);
        }
    }

    static void bench(int[] B, long[] results, int n) {
        long t0, t1;
        int runs = run;

        LinkedList temp;


        while (runs > 0) {

            t0 = System.nanoTime();
            for (int i = 0; i < samples; i++) {
               temp = new LinkedList(n);
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