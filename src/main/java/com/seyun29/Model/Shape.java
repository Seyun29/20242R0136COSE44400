package com.seyun29.Model;

import com.seyun29.Helper;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class Shape {
    public enum ShapeType {
        LINE,
        RECTANGLE,
        ELLIPSE,
        IMAGE,
        TEXT
    }
    //TODO: add TEXT shape
    private Double x1;
    private Double y1;
    private Double width;
    private Double height;
    private Double x2;
    private Double y2;
    private Integer stroke; //new BasicStroke(stroke)
    private Color color;
    private ShapeType shapeType;
    private String text = null;

//    private boolean isClicked;

    public Shape(Double x1, Double y1, Double width, Double height, Double x2, Double y2, Integer stroke, Color color, ShapeType shapeType) {
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
        this.x2 = x2;
        this.y2 = y2;
        this.stroke = stroke == null ? 1 : stroke;
        this.color = color == null? Helper.randomColor() : color;
        this.shapeType = shapeType;
//        this.isClicked = false;
    }

    public void printProperty(){
        System.out.println("type: " + shapeType + ", x1: " + x1 + ", y1: " + y1 + ", width: " + width + ", height: " + height + ", x2: " + x2 + ", y2: " + y2 + ", stroke: " + stroke + ", color: " + color);
    }

    public void move(int deltaX, int deltaY) {
        this.x1 += deltaX;
        this.y1 += deltaY;
    }

    public abstract void draw(Graphics2D g2d);
    public abstract boolean contains(Point point);
//    public abstract void update();
}
