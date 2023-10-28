package Graphs;


import java.util.ArrayList;

public class City {

    String name;
    ArrayList<Link> links;

    public City(String name) {
        this.name = name;
        links = new ArrayList<>();
    }

    public void addLink(City city, int weight) {
        links.add(new Link(city, weight));
    }
}
