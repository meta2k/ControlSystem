package model;

import java.awt.*;

public enum Status {


    working(Color.GREEN),
    offline(Color.GRAY),
    defect(Color.RED),
    unknown(Color.BLACK);

    private final Color color;

    private Status(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }
}
