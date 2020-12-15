package model;

import java.awt.*;

public enum Occupancy {


    high(Color.RED),
    medium(Color.ORANGE),
    low(Color.GREEN),
    undefined(Color.GRAY);


    private final Color color;

    private Occupancy(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    }
