import java.util.*;


class SortedArray_Test {
    static final int samples = 10_000;
    static int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000, 5000, 10_000, 20_000};//, 50_000, 100_000};
    static int[][] data1 = new int[sizes.length][];
    static int[][] data2 = new int[sizes.length][];
    static int[][] keys = new int[sizes.length][];
    static ArrayList<Long>[] linResults = new ArrayList[sizes.length];
    static ArrayList<Long>[] binResults = new ArrayList[sizes.length];
    static ArrayList<Long>[] advanceResults = new ArrayList[sizes.length];


    public static void main(String[] arg) {

        prep();
        run();
    }

    static void prep() {

        Random rnd = new Random();

        for (int i = 0; i < sizes.length; i++) {
            data1[i] = CreateSorted(sizes[i]);
            data2[i] = CreateSorted(sizes[i]);
            keys[i] = new int[samples];
            for (int j = 0; j < samples; j++) {
                keys[i][j] = rnd.nextInt(sizes[i] * 5);
            }
        }

        for (int i = 0; i < linResults.length; i++) {
            linResults[i] = new ArrayList<Long>(samples);
            binResults[i] = new ArrayList<Long>(samples);
            advanceResults[i] = new ArrayList<Long>(samples);
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

    static void clear() {
        for (ArrayList<Long> result : linResults) {
            result.clear();
        }
        for (ArrayList<Long> result : binResults) {
            result.clear();
        }
        for (ArrayList<Long> result : advanceResults) {
            result.clear();
        }
    }

    static void run() {


        for (int i = 0; i < sizes.length; i++) {
            //bench(keys[i], i,Inst.Lin);
            //bench(keys[i], i,Inst.Bin);
            bench(keys[i], i, Inst.LinDupe);
            bench(keys[i], i, Inst.BinDupe);
            bench(keys[i], i, Inst.AdvanceDupe);
        }

        clear();

        /*
        System.out.println("Linear");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(keys[i], i,Inst.Lin);
            data(linResults[i], i, samples);
        }*/

        /*System.out.println("\nBinary");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(keys[i], i,Inst.Bin);
            data(binResults[i], i,samples);
        }*/


        System.out.println("\nLinear Dupe");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(keys[i], i, Inst.LinDupe);
            data(linResults[i], i, 1);
        }


        System.out.println("\nBinary Dupe");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(keys[i], i, Inst.BinDupe);
            data(binResults[i], i, 1);
        }

        System.out.println("\nAdvance Dupe");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < sizes.length; i++) {
            bench(keys[i], i, Inst.AdvanceDupe);
            data(advanceResults[i], i, 1);
        }
    }

    static void bench(int[] array, int i, Inst sw) {
        long t0, t1;
        int runs = 1_000;

        while (runs > 0) {

            switch (sw) {
                case Lin:
                    t0 = System.nanoTime();
                    for (int k : array) {
                        SortedArray.searchUnsorted(data1[i], k);
                    }
                    t1 = System.nanoTime();

                    linResults[i].add(t1 - t0);
                case Bin:
                    t0 = System.nanoTime();
                    for (int k : array) {
                        SortedArray.binarySearch(data1[i], k);
                    }
                    t1 = System.nanoTime();

                    binResults[i].add(t1 - t0);
                case LinDupe:
                    t0 = System.nanoTime();
                    for (int item : data1[i]) {
                        SortedArray.searchUnsorted(data2[i], item);
                    }
                    t1 = System.nanoTime();

                    linResults[i].add(t1 - t0);
                case BinDupe:
                    t0 = System.nanoTime();
                    for (int item : data1[i]) {
                        SortedArray.binarySearch(data2[i], item);
                    }
                    t1 = System.nanoTime();

                    binResults[i].add(t1 - t0);
                case AdvanceDupe:
                    int x = 0;
                    int y = 0;

                    t0 = System.nanoTime();
                    while (x < data1[i].length && y < data2[i].length) {
                        int item1 = data1[i][x];
                        int item2 = data2[i][y];

                        if (item1 < item2) {
                            x++;
                            continue;
                        }
                        if (item1 > item2) {
                            y++;
                            continue;
                        }
                        x++;
                        y++;
                    }
                    t1 = System.nanoTime();

                    advanceResults[i].add(t1 - t0);
            }
            runs--;
        }
    }


    static void data(ArrayList<Long> list, int i, int div) {
        long sum = 0;
        double avg = 0;

        Collections.sort(list);

        for (Long item : list) {
            sum += item;
        }
        avg = (sum / (double) list.size());

        System.out.printf("#%10d%15.3f%15.3f%15.3f%15.3f\n",
                sizes[i],
                list.get(0) / (double) div,
                list.get(list.size() - 1) / (double) div,
                avg / (double) div,
                list.get((int) (list.size() / 2.0 + 0.5)) / (double) div);
    }

    enum Inst {
        Lin,
        Bin,
        LinDupe,
        BinDupe,
        AdvanceDupe

    }
}