import HashTables.*;
import java.util.Arrays;


class Hash_Test {

    static int run = 1_000;
    static int samples = 1_000;
    static long[] results = new long[run];
    static long[] results2 = new long[run];
    static int length = 1;
    static HashZip reader = new HashZip("postnummer.csv");

    public static void main(String[] args) {

        run();
    }




    static PrioList[] prep(int size) {
        PrioList[] copy = new PrioList[samples];

        for (int i = 0; i < samples; i++) {
            copy[i] = new PrioList();
            for (int k = 0; k < size; k++) {
               // copy[i].add(data[k]);
            }
        }
        return copy;
    }


    static void run() {

        for (int i = 0; i < length; i++) {
            int runs = run;

            while (runs > 0) {
                runs--;
                bench("111 15");
                bench("984 99");
                if(runs % 100 == 0){
                    System.out.println("Warm Up Run: " + (run - runs));
                }
            }
        }
        System.out.println("\nWarm Up Done\n");
        for (int i = 0; i < length; i++) {
            int runs = run;

            while (runs > 0) {
                runs--;
                results[runs] = bench("111 15");
                results2[runs] = bench("984 99");
                if(runs % 100 == 0){
                    System.out.println("Bench Run: " + (run - runs));
                }
            }
        }

        System.out.println("\nHash Table 2.0 of \"111 15\"");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < length; i++) {
            System.out.printf("#%10d", 0);
            data(results);
        }

        System.out.println("\nHash Table 2.0 of \"984 99\"");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < length; i++) {
            System.out.printf("#%10d", 0);
            data(results2);
        }

    }

    static long bench(String key) {
        long t0, t1;

        t0 = System.nanoTime();
        for (int i = 0; i < samples; i++) {
            reader.find(key);
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

}