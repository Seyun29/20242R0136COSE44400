package com.seyun29.Model.Shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {
    private final Ellipse2D ellipse2D;

    public Ellipse(Double x1, Double y1, Double width, Double height) {
        super(x1, y1, width, height, null, null, null, null, ShapeType.ELLIPSE);
//        this.setStroke(null);
        this.ellipse2D = new Ellipse2D.Double(x1, y1, width, height);
    }

    public Ellipse(Double x1, Double y1, Double width, Double height, Integer stroke, Color color) {
        super(x1, y1, width, height, null, null, stroke, color, ShapeType.ELLIPSE);
//        this.setStroke(null);
        this.ellipse2D = new Ellipse2D.Double(x1, y1, width, height);
    }

    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(getStroke()));
        g2d.setColor(getColor());
        this.ellipse2D.setFrame(getX1(), getY1(), getWidth(), getHeight()); //apply if updated
        g2d.fill(this.ellipse2D);
    }
    public boolean contains(Point point) {
        return this.ellipse2D.contains(point);
    }
}
