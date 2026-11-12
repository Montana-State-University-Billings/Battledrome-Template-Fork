package Optional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import Common.Coordinate;

public class Ship {
	private String name;
    private int length;
    private Set<Coordinate> coordinates = new HashSet<>();
    private List<Coordinate> hits = new ArrayList<>();
    public static Map<String, Integer> specifications = Map.of("Carrier", 5, "Battleship", 4, "Destroyer", 3, "Submarine", 3, "Patrol Boat", 2);

    public Ship(String name, int length){
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }
    public int getHitCount() {
        return hits.size();
    }
    public int getLength() {
        return length;
    }
    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Set<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
    public void registerHit(Coordinate hit){
        hits.add(hit);
    }
    public Coordinate getLastHit(){
        return hits.getLast();
    }
    public boolean isSunk(){
        return hits.size() == length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.length, this.coordinates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return ((this.getLength() == ship.getLength()) && (this.getName().equals(ship.getName())) && (this.coordinates.equals(ship.getCoordinates())));
    }
}
