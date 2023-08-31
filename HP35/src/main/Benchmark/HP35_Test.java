import java.util.ArrayList;
import java.util.Collections;

class HP35_Test {
    static ArrayList<Long> pushData = new ArrayList<>();
    static ArrayList<Long> popData = new ArrayList<>();

    static int fknSum = 0;

    public static void main(String[] args) {


        StaticStack s1 = new StaticStack(1024);
        DynamicStack s2 = new DynamicStack(4);

        System.out.println("-------Static-Stack-------\n");
        run(s1);
        System.out.println("");

        System.out.println("-------Dynamic-Stack-------\n");
        run(s2);
        System.out.println("");


    }

    static void run(Stack s) {
        int runs = 1000;

        while (runs > 0) {
            bench(s);
            runs--;
        }

        pushData.clear();
        popData.clear();
        runs = 100000000;

        while (runs > 0) {
            bench(s);
            runs--;
        }

        System.out.println("\t*****Push-Data*****");
        data(pushData);

        System.out.println("\t*****Pop-Data******");
        data(popData);

        pushData.clear();
        popData.clear();
    }

    static void bench(Stack stack) {
        long t0, t1;
        int it = 0;

        t0 = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            stack.push(it++);
        }
        t1 = System.nanoTime();
        pushData.add(t1 - t0);


        t0 = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            fknSum += stack.pop();
        }
        t1 = System.nanoTime();
        popData.add(t1 - t0);
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
                "\n\tMedian :\t" + list.get((int) (list.size() / 2.0 + 0.5)) +
                "\n");
    }
}
