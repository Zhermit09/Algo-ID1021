package Graphs;

public class Naive {

    static Map map = new Map();

    public static void main(String[] args) {

        String[] from = {"Malmö", "Göteborg", "Malmö", "Stockholm", "Stockholm", "Göteborg", "Sundsvall", "Umeå", "Göteborg"};
        String[] to = {"Göteborg", "Stockholm", "Stockholm", "Sundsvall", "Umeå", "Sundsvall", "Umeå", "Göteborg", "Umeå"};

        Integer[] max = {300, 300, 300, 400, 550, 550, 300, 710, 710};


        for (int i = 0; i < 9; i++) {
            bench(from[i],to[i],max[i]);
        }

    }

    private static void bench(String from, String to, Integer max) {
        long t0 = System.nanoTime();
        Integer dist = shortest(map.get(from), map.get(to), max);
        double time = (System.nanoTime() - t0) / 1_000_000.0;
        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }

    private static Integer shortest(City from, City to, Integer max) {
        if (max < 0) {
            return null;
        }
        if (from == to) {
            return 0;
        }

        Integer path = null;
        for (Link link : from.links) {
            Integer temp = shortest(link.city, to, max - link.weight);

            if (path == null && temp != null) {
                path = temp + link.weight;
                continue;
            }

            if (temp != null && path > temp + link.weight) {
                path = temp + link.weight;
            }
        }
        return path;
    }
}
