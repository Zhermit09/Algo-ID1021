package Dijkstra;

public class Path {

    public City city;
    public City prev;
    public Integer time;
    public Integer idx;

    public Path(City city, City prev, Integer time){
        this.city = city;
        this.prev = prev;
        this.time = time;
        this.idx = null;
    }
}
