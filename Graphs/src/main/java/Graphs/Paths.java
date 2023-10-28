package Graphs;
public class Paths {

    static Map map = new Map();
    static City[] paths = new City[54];
    static int sp = 0;

    public static void main(String[] args) {

        String[] from = {"Malmö", "Göteborg", "Malmö", "Stockholm", "Stockholm", "Göteborg", "Sundsvall", "Umeå", "Göteborg"};
        String[] to = {"Göteborg", "Stockholm", "Stockholm", "Sundsvall", "Umeå", "Sundsvall", "Umeå", "Göteborg", "Umeå"};


        for (int i = 0; i < 9; i++) {
            bench(from[i], to[i]);
        }
        bench("Malmö", "Kiruna");

    }

    private static void bench(String from, String to) {
        long t0 = System.nanoTime();
        Integer dist = shortest(map.get(from), map.get(to));
        double time = (System.nanoTime() - t0) / 1_000_000.0;
        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }

    private static Integer shortest(City from, City to) {

        for (int i = 0; i < sp; i++) {
            if (paths[i] == from) {
                return null;
            }
        }

        if (from == to) {
            return 0;
        }

        paths[sp++] = from;

        Integer path = null;
        for (Link link : from.links) {

            Integer temp = shortest(link.city, to);

            if (path == null && temp != null) {
                path = temp + link.weight;
                continue;
            }

            if (temp != null && path > temp + link.weight) {
                path = temp + link.weight;
            }

        }
        paths[sp--] = null;
        return path;
    }
}
