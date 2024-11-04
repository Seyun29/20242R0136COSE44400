package com.seyun29.Model;

import java.awt.*;

public class Image extends Shape {
    //FIXME: fixme
    public Image(Double x1, Double y1, Double width, Double height, Integer stroke, Color color) {
        super(x1, y1, width, height, null, null, stroke, color);
    }
    public void draw(Graphics2D g2d) {
        System.out.println("Draw Image Here...");
    }
    public boolean contains(Point point) {
        return false;
    }
}
