import HashTables.*;
import java.util.Arrays;


class Hash_Test {

    static int run = 1;
    static int samples = 1;
    static long[] results = new long[9675];
    static int length = 1;
    static HashZip reader = new HashZip("postnummer.csv");
    static Zip3 zip = new Zip3("postnummer.csv");

    public static void main(String[] args) {

        run();
    }



    static void run() {

        for (int i = 0; i < length; i++) {
            int runs = run;

            /*while (runs > 0) {
                runs--;
                if(runs % 100 == 0){
                    System.out.println("Warm Up Run: " + (run - runs));
                }
            }*/

            for (Zip3.Node n: zip.data) {
                if (n == null) {
                    continue;
                }
                bench(n.code);
            }

        }
        System.out.println("\nWarm Up Done\n");
        for (int i = 0; i < length; i++) {
            int runs = run;

           /* while (runs > 0) {
                runs--;
                 = bench("111 15");
                if(runs % 100 == 0){
                    System.out.println("Bench Run: " + (run - runs));
                }
            }*/

            int j = 0;
            for (Zip3.Node n: zip.data) {
                if (n == null) {
                    continue;
                }
                results[j] = bench(n.code);
                j++;
            }
        }

        System.out.println("\nHash Table 2.0 Depth");
        System.out.printf("#%10s%15s%15s%15s%15s\n", "N", "Fastest", "Slowest", "Average", "Median");
        for (int i = 0; i < length; i++) {
            System.out.printf("#%10d", 0);
            data(results);
        }

    }

    static int bench(String key) {
        long t0, t1;

        /*t0 = System.nanoTime();
        for (int i = 0; i < samples; i++) {
            reader.find(key);
        }
        t1 = System.nanoTime();
        return t1 - t0;
        */
        return reader.find(key);

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