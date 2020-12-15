package model;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class Model {

    private List<Node> nodes;
    Database db = new Database(this);

    public Model(){
        this.nodes = new LinkedList<>();
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            db.load();
        } catch (SQLException e) {
        }
    };


    public void addNode(Node node){
        nodes.add(node);
        try {
            db.save();
        } catch (SQLException e) {
        }
    }

    public void removeNode(int index){
        Node removed = nodes.remove(index);
        try {
            db.remove(removed.getId());
        } catch (SQLException e) {
        }
    }

    public boolean contains(Node node){
        return nodes.contains(node);
    }

    public List<Node> getNodes(){
        return Collections.unmodifiableList(nodes);
    }

    public void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Node[] nodeArr = nodes.toArray(new Node[nodes.size()]);

        oos.writeObject(nodeArr);

        oos.close();
    }

    public void loadFromFile(File file) throws  IOException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);


        try {
            Node[] nodeArr = (Node[]) ois.readObject();
            nodes.clear();
            nodes.addAll(Arrays.asList(nodeArr));


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
    }

}
