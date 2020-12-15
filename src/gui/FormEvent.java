package gui;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private String name;
    private int xcoord;
    private int ycoord;
    private String status;
    private String occu;

    public FormEvent(Object source) {
        super(source);
    }


    public FormEvent(Object source, String name, int xcoord, int ycoord, String status, String occu) {
        super(source);
        this.name = name;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.status = status;
        this.occu = occu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public String getStatus() {
        return status;
    }

    public String getOccu() {
        return occu;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


