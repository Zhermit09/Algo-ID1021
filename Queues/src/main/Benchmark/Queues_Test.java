import java.util.Arrays;
import java.util.Random;


class Queues_Test {

    static final int samples = 10_000;
    static int run = 10_000;
    static int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

    static int[][] keys = new int[sizes.length][samples];
    static BinaryTree[] data = new BinaryTree[sizes.length];
    static long[][] results = new long[sizes.length][run];
    static long[] dummy = new long[run];

    public static void main(String[] args) {

        prep();
        run();

    }

    static void prep() {
        Random rnd = new Random();
        int nxt1, nxt2;

        for (int i = 0; i < sizes.length; i++) {

            data[i] = new BinaryTree();

            for (int j = 0; j < sizes[i]; j++) {
                nxt1 = rnd.nextInt();
                nxt2 = rnd.nextInt();
                data[i].add(nxt1, nxt2);
            }

            for (int j = 0; j < samples; j++) {
                nxt1 = rnd.nextInt();
                keys[i][j] = nxt1;
            }
        }
    }

    static void run() {


        for (int i = 0; i < sizes.length; i++) {
            bench(keys[i], data[i], dummy);
        }

        System.out.println("Binary Tree Lookup");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(keys[i], data[i], results[i]);
            System.out.printf("#%10d", sizes[i]);
            data(results[i]);
        }

    }

    static void bench(int[] keys, BinaryTree tree, long[] results) {
        long t0, t1;
        int runs = run;

        while (runs > 0) {

            t0 = System.nanoTime();
            for (int i = 0; i < samples; i++) {
                tree.find(keys[i]);
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