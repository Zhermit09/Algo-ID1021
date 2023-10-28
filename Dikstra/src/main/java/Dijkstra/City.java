package Dijkstra;

import java.util.ArrayList;

public class City {
    String name;
    Integer id;
    ArrayList<Link> links;

    public City(String name, int id) {
        this.name = name;
        links = new ArrayList<>();
        this.id = id;
    }

    public void addLink(City city, int weight) {
        links.add(new Link(city, weight));
    }
}
