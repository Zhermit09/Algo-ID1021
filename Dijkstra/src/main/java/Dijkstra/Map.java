package Dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Map {

    public HashTable cities;
    private static final int mod = 541;
    private static int sp = 0;

    public Map() {
        cities = new HashTable(mod);
        load();
    }

    private void load() {
        String fileName = "trains.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI()).toFile()))) {
            String line;
            String[] buffer;

            while ((line = br.readLine()) != null) {
                buffer = line.split(",");
                connect(buffer[0], buffer[1], Integer.parseInt(buffer[2]));
                connect(buffer[1], buffer[0], Integer.parseInt(buffer[2]));
            }
           // System.out.println(" Successfully loaded " + fileName);
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
            System.out.println(e);
        }
    }

    private void connect(String name, String dst, int weight) {
        ArrayList<Link> links = retrieve(name).links;
        for (Link link : links) {
            if ((link.weight == weight) && (link.city.name.equals(dst))) {
                return;
            }
        }
        links.add(new Link(retrieve(dst), weight));
    }

    private City retrieve(String name) {
        City city = cities.find(name);
        if (city == null) {
            city = new City(name, sp++);
            cities.add(city);
        }
        return city;
    }

    public City get(String name) {
        return cities.find(name);
    }
}