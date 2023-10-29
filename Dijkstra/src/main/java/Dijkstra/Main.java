package Dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

class Main {
    static Map map = new Map();
    static Path[] explored;
    static PrioQueue queue;
    static String[] cities = {"Mora", "Graz", "Göttingen", "Nynäshamn", "Ystad", "Edinburgh", "Valencia", "Taranto", "Bremen", "Odense", "Birmingham", "Genova", "Sveg", "Dijon", "Kassel",
            "Verona", "Falköping", "Zürich", "Boden", "Gällivare", "Sundsvall", "Halden", "Uppsala", "Budapest", "Sala", "Storvik", "Paris", "Rom", "Gdansk", "Pescara", "Östersund", "Liege",
            "Frankfurt", "Venedig", "Stuttgart", "Emmaboda", "Uddevalla", "Wien", "Helsingborg", "Amsterdam", "Helsingör", "Montpellier", "Hallsberg", "Skövde", "Norrköping", "Luxenburg",
            "Mannheim", "Geneve", "Köln", "Alvesta", "Preston", "Foggia", "Lund", "Hamburg", "Zaragoza", "Karlstad", "Hässleholm", "Metz", "Bari", "Fagersta", "Katrineholm", "Värnamo",
            "Södertälje", "Trondheim", "Strömstad", "Narvik", "Herrljunga", "Madrid", "Manchester", "Hannover", "Umeå", "Köpenhamn", "Warsawa", "Lyon", "London", "Nice", "Prag", "Arboga",
            "Berlin", "Seinäjoki", "Jönköping", "Helsingfors", "Florens", "Ånge", "Eskilstuna", "Milano", "Gävle", "Kiruna", "Luleå", "Dresden", "Örebro", "Saarbrucken", "München",
            "Barcelona", "Västerås", "Åstorp", "Fredrikshamn", "Marseille", "Neapel", "Göteborg", "Mjölby", "Bryssel", "Nässjö", "Liverpool", "Åbo", "Borlänge", "Glasgow", "Turin",
            "Kalmar", "Stockholm", "Vasa", "Kristianstad", "Leipzig", "Varberg", "Nürnberg", "Tammerfors", "Malmö", "Leeds", "Brindisi", "Linköping", "Trollhättan", "Innsbruck", "Basel",
            "Avesta", "Świnoujście", "Frövi", "Oslo", "Karlskrona", "Bukarest", "Ludvika", "Halmstad", "Bologna", "Lille"};


    static String[] to = {"Graz", "Edinburgh", "Świnoujście", "Barcelona", "Zaragoza", "Metz", "Liege", "London", "Nice", "Prag", "Arboga", "Florens"};

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 1; i <= 12; i++) {
           // bench(cities[random.nextInt(133)], to[i]);
            System.out.print("( , )");
        }



    }

    private static Integer bench(String from, String to) {

        long t0 = System.nanoTime();
        Path path = dijkstra(from, to);
        double time = (System.nanoTime() - t0) / 1_000_000.0;

        Integer t = path.time;
        int count = 0;
        for (int i = 0; i < explored.length; i++) {
            if (explored[i] != null) {
                count++;
            }
        }

        if (path != null)
            System.out.println("Malmö to " + to + " : " + path.time + " min");
        else System.out.println(from + " to " + to + " : " + null + " min");
        while (path.prev != null) {
            System.out.print(path.city.name + " -> ");
            path = explored[path.prev.id];
        }
        System.out.println(path.city.name);
        System.out.println("__________________________________________________________________________________________________________________________________________________________________________________________________________________________________\n");
        return t;
    }



    public static Path dijkstra(String from, String to) {
        explored = new Path[133];
        queue = new PrioQueue(8);

        queue.add(new Path(map.get(from), null, 0));
        return shortestRoute(map.get(to));
    }


    static Path shortestRoute(City to) {
        while (queue.arr[0] != null) {
            Path path = queue.remove();


            if (path.city == to) {
                return path;
            }

            explored[path.city.id] = path;


            for (Link link : path.city.links) {

                if (link.city.name.equals("Hallsberg")) {
                    //System.out.println();
                }

                if (explored[link.city.id] != null) {
                    Integer i = explored[link.city.id].idx;
                    if (i != null && queue.arr[i].time > path.time + link.weight) {

                        if (queue.arr[i].city.name.equals(path.city.name)) {
                            System.out.print("");
                        }

                        queue.arr[i].prev = path.city;
                        queue.arr[i].time = path.time + link.weight;
                        queue.update(i);
                    }
                    continue;
                }
                Path temp = new Path(link.city, path.city, path.time + link.weight);
                explored[link.city.id] = temp;
                queue.add(temp);
            }

        }
        return null;
    }


    static void load(ArrayList<String[]> list) {
        String fileName = "europe.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(Main.class.getClassLoader().getResource(fileName).toURI()).toFile()))) {
            String line;
            String[] buffer;

            while ((line = br.readLine()) != null) {
                buffer = line.split(",");
                list.add(buffer);
            }
            //System.out.println(" Successfully loaded " + fileName);
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
            System.out.println(e);
        }
    }
}