package com.seyun29.Model;

import com.seyun29.Model.Shape.Shape;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class ShapeModel {
    //Current Objects on Board
    private ArrayList<Shape> Shapes = new ArrayList<>();
    @Setter
    private Shape currentShape = null;

    public ShapeModel() {}

    public void addShape(Shape shape) {
        Shapes.add(shape);
        setCurrentShape(shape);
    }

    public void removeShape(Shape shape) {
        Shapes.remove(shape);
        currentShape = null;
    }

    public void clearShapes() {
        Shapes.clear();
        currentShape = null;
    }

    public void bringToFront(Shape shape) {
        Shapes.remove(shape);
        Shapes.add(shape);
    }

    public void sendToBack(Shape shape) {
        Shapes.remove(shape);
        Shapes.add(0, shape);
    }

    public Shape containsShape(Point point) {
        //always pick the one with the biggest zindex
        for(int i=Shapes.size()-1; i>=0; i--) {
            if(Shapes.get(i).contains(point)) {
                return Shapes.get(i);
            }
        }
        return null;
    }

}
