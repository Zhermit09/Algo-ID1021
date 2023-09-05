import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


class Bench {
    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(10);

        for (int i = 0; i < n ; i++) {
            array[i] = nxt;
            nxt += rnd.nextInt(10) + 1 ;
        }
        return array;
    }


    private static int[] keys(int loop, int n) {
        Random rnd = new Random();
        int[] indx = new int[loop];
        for (int i = 0; i < loop ; i++) {
            indx[i] = rnd.nextInt(n*5);
        }
        return indx;
    }


    public static void main(String[] arg) {

        int[] sizes = {100,200,300,400,500,600,700,800,900,1_000,1_500,2_000,2_500,3_000,5_000,10_000};

        System.out.printf("# searching through an array of length n, time in ns\n");
        System.out.printf("#%7s%15s\n", "n", "linear");
        for ( int n : sizes) {

            int loop = 10000;

            int[] array = CreateUnsorted(n);
            int[] indx = keys(loop, n);

            System.out.printf("%8d", n);

            int k = 1000;

            double min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                for (int j = 0; j < indx.length ; j++) {
                    SortedArray.searchUnsorted(array,indx[j]);
                }
                long t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("%15.3f\n" , min/loop);
        }
    }
    static int[] CreateUnsorted(int n) {
        int[] array = new int[n];
        ArrayList<Integer> list = new ArrayList<>();

        for (int i : sorted(n)) {
            list.add(i);
        }

        Collections.shuffle(list);

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;

    }
}
