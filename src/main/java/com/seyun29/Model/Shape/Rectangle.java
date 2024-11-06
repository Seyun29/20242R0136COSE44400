package com.seyun29.Model.Shape;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {
    private final Rectangle2D rectangle2D;

    public Rectangle(Double x1, Double y1, Double width, Double height) {
        super(x1, y1, width, height, null, null, null, null, ShapeType.RECTANGLE);
//        this.setStroke(null);     // FIXME: set Stroke to null
        this.rectangle2D = new Rectangle2D.Double(x1, y1, width, height);
    }

    public Rectangle(Double x1, Double y1, Double width, Double height, Integer stroke, Color color) {
        super(x1, y1, width, height, null, null, stroke, color, ShapeType.RECTANGLE);
//        this.setStroke(null);     // FIXME: set Stroke to null
        this.rectangle2D = new Rectangle2D.Double(x1, y1, width, height);
    }

    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(getStroke()));
        g2d.setColor(getColor());
        this.rectangle2D.setRect(getX1(), getY1(), getWidth(), getHeight()); //apply if updated
        g2d.fill(rectangle2D);
    }

    public boolean contains(Point point) {
        return this.rectangle2D.contains(point);
    }
}
