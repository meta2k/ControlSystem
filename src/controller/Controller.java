package controller;

import gui.FormEvent;
import model.Model;
import model.Node;
import model.Occupancy;
import model.Status;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Controller {

    private final Model model = new Model();

    public Controller() {
    }

    public void removeNode(int index){
        model.removeNode(index);
    }

    public void addNode(FormEvent e) {
        System.out.println("node added");
        String name = e.getName();
        int x = e.getXcoord();
        int y = e.getYcoord();
        String statusStr = e.getStatus();
        String occuStr = e.getOccu();

        Status status;
        Occupancy occu;

        if (statusStr.equals(Status.working.toString())) {
            status = Status.working;
        } else if (statusStr.equals(Status.defect.toString())) {
            status = Status.defect;
        } else if (statusStr.equals(Status.offline.toString())) {
            status = Status.offline;
        } else {
            status = Status.unknown;
        }

        if (occuStr.equals(Occupancy.high.toString())) {
            occu = Occupancy.high;
        } else if (occuStr.equals(Occupancy.medium.toString())) {
            occu = Occupancy.medium;
        } else if (occuStr.equals(Occupancy.low.toString())) {
            occu = Occupancy.low;
        } else {
            occu = Occupancy.undefined;
        }

        Node node = new Node(name, x, y, status, occu);
        model.addNode(node);
    }


    public List<Node> getNodes() {
        return model.getNodes();
    }

    public void saveToFile(File file) throws IOException{
            model.saveToFile(file);
    }


    public void loadFromFile(File file) throws IOException{
        model.loadFromFile(file);
    }
}
