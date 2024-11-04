package com.seyun29.Model;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Line extends Shape {
    private final Line2D line2D;

    public Line(Double x1, Double y1, Double x2, Double y2) {
        super(x1, y1, null, null, x2, y2, 3, null, ShapeType.LINE);
        this.line2D = new Line2D.Double(x1, y1, x2, y2);
    }

    public Line(Double x1, Double y1, Double x2, Double y2, Integer stroke, Color color) {
        super(x1, y1, null, null, x2, y2, stroke, color, ShapeType.LINE);
        this.line2D = new Line2D.Double(x1, y1, x2, y2);
    }

    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(getStroke()));
        g2d.setColor(getColor());
        this.line2D.setLine(getX1(), getY1(), getX2(), getY2());
        g2d.draw(this.line2D);
    }

    public boolean contains(Point point) {
        //FIXME: fix me
//        System.out.println(this.line2D.ptSegDist(point));
        return this.line2D.ptSegDist(point) <= 5.0;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        this.setX1(this.getX1() + deltaX);
        this.setY1(this.getY1() + deltaY);
        this.setX2(this.getX2() + deltaX);
        this.setY2(this.getY2() + deltaY);
    }
}
