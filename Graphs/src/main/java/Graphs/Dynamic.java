package Graphs;

public class Dynamic {

    static Map map = new Map();
    static City[] paths = new City[133];
    static int sp = 0;
    static Integer max = null;
    static String[] cities = {"Mora", "Sveg", "Falköping", "Boden", "Gällivare", "Sundsvall", "Uppsala", "Sala", "Storvik", "Östersund", "Emmaboda", "Uddevalla", "Helsingborg",
            "Hallsberg", "Skövde", "Norrköping", "Alvesta", "Lund", "Hässleholm", "Fagersta", "Katrineholm", "Värnamo", "Södertälje", "Strömstad", "Herrljunga", "Umeå", "Arboga",
            "Jönköping", "Ånge", "Eskilstuna", "Gävle", "Kiruna", "Luleå", "Örebro", "Västerås", "Åstorp", "Göteborg", "Mjölby", "Nässjö", "Borlänge", "Kalmar", "Stockholm", "Kristianstad",
            "Varberg", "Malmö", "Linköping", "Trollhättan", "Avesta", "Frövi", "Karlskrona", "Ludvika", "Halmstad"};


    public static void main(String[] args) {

        for (int i = 0; i < 52; i++) {
            bench("Malmö", cities[i]);
        }


    }

    private static void bench(String from, String to) {
        max = null;
        long t0 = System.nanoTime();
        Integer dist = shortest(map.get(from), map.get(to), 0);
        double time = (System.nanoTime() - t0) / 1_000_000.0;
        String msg = "Malmö to " + to + " : " + dist + " min (" + time + " ms)";
        System.out.println(msg);
    }

    private static Integer shortest(City from, City to, Integer time) {

        if (max != null && max < time) {
            return null;
        }

        for (int i = 0; i < sp; i++) {
            if (paths[i] == from) {
                return null;
            }
        }

        if (from == to) {
            if (max == null || max > time) {
                max = time;
            }
            return 0;
        }

        paths[sp++] = from;

        Integer path = null;
        for (Link link : from.links) {

            Integer temp = shortest(link.city, to, time + link.weight);

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
