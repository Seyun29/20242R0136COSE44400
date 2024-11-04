package com.seyun29.Model;

import com.seyun29.PropertyPanel;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class BoardModel {
    //Current Objects on Board
    private ArrayList<Shape> Shapes = new ArrayList<>();
    private Shape currentShape = null; //TODO: implement single selection feature!! (set currentShape to null when clicked outside of the shape)
    private PropertyPanel propertyPanel;

    public BoardModel(PropertyPanel propertyPanel) {
        this.propertyPanel = propertyPanel;
    }

    public void setCurrentShape(Shape shape) {
        currentShape = shape;
        propertyPanel.updateProperties(shape); //FIXME: use Observer logic instead, apply design patterns...
    }

    public void addShape(Shape shape) {
        Shapes.add(shape);
        currentShape = shape;
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

    public void drawShapes(Graphics2D g2d){
        for(Shape shape : Shapes){
            shape.draw(g2d);
        }
    }

}
