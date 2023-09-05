import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;



class SortedArray_Test {

    static long dummySum = 0;
    static long t0, t1;
    static int it = 0;
    public static void main(String[] arg) {

        int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000, 5000, 10_000, 100_000, 500_000, 1_000_000};

        System.out.println("#\t\tN\t\tTime");
        for (int n : sizes) {

            int loop = 10000;

            int[] array = sorted(n);
            int[] indx = keys(loop, n);

            System.out.printf("%8d", n);

            int k = 1000;

            double min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                linear(array, indx);
                long t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("%8.0f", (min / loop));

            min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                binary(array, indx);
                long t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("%8.0f\n", (min / loop));
        }
    }

    private static void linear(int[] array, int[] indx) {
        for (int i = 0; i < indx.length; i++) {

            //Linear.search(array, indx[i]);
        }
    }


    private static void binary(int[] array, int[] indx) {
        for (int i = 0; i < indx.length; i++) {
            //Binary.search(array, indx[i]);
        }
    }


    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(10);

        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt += rnd.nextInt(10) + 1;
        }
        return array;
    }


    private static int[] keys(int loop, int n) {
        Random rnd = new Random();
        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++) {
            indx[i] = rnd.nextInt(n * 5);
        }
        return indx;
    }

    static void run(Stack s) {
        int runs = 10_000;

        while (runs > 0) {
            bench(s);
            runs--;
        }

        //pushData.clear();
        //popData.clear();
        runs = 10_000_000;

        while (runs > 0) {
            bench(s);
            runs--;
        }

        System.out.println("\t*****Push-Data*****");
        //data(pushData);

        System.out.println("\t*****Pop-Data******");
        //data(popData);

        //pushData.clear();
        //popData.clear();
    }

    static void bench(Stack stack) {

        //System.gc();
        t0 = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            //stack.push(it);
        }
        t1 = System.nanoTime();
        //pushData.add(t1 - t0);


        t0 = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            //dummySum += stack.pop();
        }
        t1 = System.nanoTime();
        //popData.add(t1 - t0);

        it++;
    }


    static void data(ArrayList<Long> list) {
        double avg = 0;
        Collections.sort(list);

        for (Long item : list) {
            avg += item;
        }
        avg /= list.size();

        System.out.println("\tSamples:\t" + list.size() +
                "\n\tFastest:\t" + list.get(0) +
                "\n\tSlowest:\t" + list.get(list.size() - 1) +
                "\n\tAverage:\t" + avg +
                "\n\tMedian :\t" + list.get((int)(list.size() / 2.0 + 0.5)) +
                "\n");
    }

}