package Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Common.Coordinate;

public class Ship {
    private String name;
    private int length;
    private List<Coordinate> coords = new ArrayList<>();
    private List<Coordinate> hits = new ArrayList<>();

    public final static Map<String, Integer> specifications = Map.of("Carrier", 5, "Battleship", 4, "Cruiser", 3, "Submarine", 3, "Destroyer", 2);

    public Ship(String name, int length){
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public List<Coordinate> getCoordinates() {
        return coords;
    }
    
    public void setCoordinates(List<Coordinate> coords) {
        this.coords = coords;
    }

    public void registerHitAt(Coordinate c){
        if(coords.contains(c) && !hits.contains(c)){
            hits.add(c); 
        }
    }

    public boolean isSunk(){
        if(coords.size() == hits.size()){
            return true;
        }
        return false;
    }
}
