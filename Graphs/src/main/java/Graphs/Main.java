package Graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;

class Main {
    static Map map = new Map();
    static ArrayList<String[]> list = new ArrayList<>();

    public static void main(String[] args) {

        load(list);

        for (String[] arr : list) {
            if (!check(arr[0], arr[1], Integer.parseInt(arr[2]))) {
                System.out.println("City: " + arr[0] + " | link: [" + arr[1] + ", " + arr[2] + "] not found");
                City temp = map.get(arr[0]);
                break;
            }

            if (!check(arr[1], arr[0], Integer.parseInt(arr[2]))) {
                System.out.println("City: " + arr[1] + " | link: [" + arr[0] + ", " + arr[2] + "] not found");
                City temp = map.get(arr[1]);
                break;
            }

            System.out.println("Good...");
        }
        System.out.println("Done");
        System.out.println("Collisions :" + HashTable.collisions);


        System.out.println();

    }

    static boolean check(String city, String dst, int weight) {

        for (Link link : map.get(city).links) {
            if (link.weight == weight && link.city.name.equals(dst)) {
                return true;
            }
        }
        return false;
    }

    static void load(ArrayList<String[]> list) {
        String fileName = "trains.csv";

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