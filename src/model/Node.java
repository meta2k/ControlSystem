package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Node implements Serializable {

    private static int nextId = 0;

    private final int id;
    private final String name;
    private final int x;
    private final int y;
    private final Map<Node, Integer> adjecent;

    private Status status;
    private Occupancy occu;



    public Node(String name, int x, int y, Status status, Occupancy occu) {
        this.id = nextId++;
        this.name = name;
        this.x = x;
        this.y = y;
        this.adjecent = new HashMap<>();
        this.status = status;
        this.occu = occu;
    }

    public void addAdjecent(Node node, int distance)throws IllegalArgumentException{
        if(node == null || distance < 0){
            throw new IllegalArgumentException();
        }
       adjecent.put(node, distance);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y &&
                name.equals(node.name);
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public Map<Node, Integer> getAdjecent(){
        return Collections.unmodifiableMap(adjecent);
    }



    public void setStatus(Status status) {
        this.status = status;
    }

    public Occupancy getOccu() {
        return occu;
    }

    public void setOccu(Occupancy occu) {
        this.occu = occu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x, y);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
