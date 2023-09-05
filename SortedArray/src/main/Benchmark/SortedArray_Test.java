import java.util.*;


class SortedArray_Test {


    static final int samples = 10_000;
    static int[][] data = new int[16][];
    static int[][] keys = new int[16][];
    static ArrayList<Long>[] results = new ArrayList[16];

    public static void main(String[] arg) {

        prep();
        run();

    }

    static void prep() {
        int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600};
        //{100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000, 5000, 10_000, 10, 10, 10};
        Random rnd = new Random();
        int nxt = rnd.nextInt(sizes[0]);

        for (int i = 0; i < sizes.length; i++) {
            data[i] = CreateUnsorted(sizes[i]);
            keys[i] = new int[samples];
            for (int j = 0; j < samples; j++) {
                keys[i][j] = data[i][nxt];
                nxt = rnd.nextInt(sizes[i]);
            }
        }

        for (int i = 0; i < results.length; i++) {
            results[i] = new ArrayList<Long>(samples);
        }
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

    static void run() {

        for (int i = 0; i < keys.length; i++) {

        }

        for (ArrayList<Long> result : results) {
            result.clear();
        }

        for (int i = 0; i < keys.length; i++) {
            bench(keys[i], i);
        }
        data();
    }

    static void bench(int[] array, int i) {
        long t0, t1;
        int runs = 1_000;


        while (runs > 0) {

            t0 = System.nanoTime();
            for (int j = 0; j < array.length; j++) {
                SortedArray.searchUnsorted(data[i], array[i]);
            }
            t1 = System.nanoTime();

            results[i].add(t1 - t0);
            runs--;
        }
    }


    static void data() {
        long sum = 0;
        double avg = 0;
        int i = 0;

        System.out.printf("#%10s%15s%15s%20s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (ArrayList<Long> list : results) {
            Collections.sort(list);

            for (Long item : list) {
                sum += item;
            }
            avg = (sum / (double) list.size());

            System.out.printf("#%10d%15.3f%15.3f%15.3f%15.3f\n",//#%10d%15.3f%15.3f%15.3f%15.3f
                    data[i].length,
                    list.get(0) / (double) samples,
                    list.get(list.size() - 1) / (double) samples,
                    avg / (double) samples,
                    list.get((int) (list.size() / 2.0 + 0.5)) / (double) samples);
            i++;
            sum = 0;
        }
        System.out.println("");
    }

}