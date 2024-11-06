package com.seyun29.Controller;

import com.seyun29.Helper;
import com.seyun29.Model.CanvasModel;
import com.seyun29.Model.ShapeModel;
import com.seyun29.Model.Shape.*;
import com.seyun29.Model.Shape.Image;
import com.seyun29.Model.Shape.Rectangle;
import com.seyun29.Model.Shape.Shape;
import com.seyun29.View.Panel.CanvasPanel;
import com.seyun29.View.Panel.PropertyPanel;
import lombok.Setter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;

@Setter
public class Controller {
    private final ShapeModel shapeModel;
    private final CanvasModel canvasModel;
    private PropertyPanel propertyPanel; //will be set in MainFrame
    private CanvasPanel canvasPanel; //will be set in MainFrame

    public Controller(ShapeModel shapeModel, CanvasModel canvasModel) {
        this.shapeModel = shapeModel;
        this.canvasModel = canvasModel;
    }

    public void onMousePressed(MouseEvent e){
        //FIXME: apply design patterns... (e.g. use Observer logic instead..)
        CanvasModel.Mode mode = canvasModel.getMode();
        if (mode == CanvasModel.Mode.DRAW_LINE) {
            canvasModel.setStartPoint(e.getPoint());
            Point startPoint = canvasModel.getStartPoint();
            Line currentLine = new Line(startPoint.getX(), startPoint.getY(), startPoint.getX(), startPoint.getY(), 3, Helper.randomColor());
            canvasModel.setCurrentLine(currentLine);
            shapeModel.addShape(currentLine);
            propertyPanel.updateProperties(currentLine);
        } else if (mode == CanvasModel.Mode.DRAW_ELLIPSE){
            canvasModel.setStartPoint(e.getPoint());
            Point startPoint = canvasModel.getStartPoint();
            Ellipse currentEllipse = new Ellipse(startPoint.getX(), startPoint.getY(), 0.0d, 0.0d, 3, Helper.randomColor());
            canvasModel.setCurrentEllipse(currentEllipse);
            shapeModel.addShape(currentEllipse);
            propertyPanel.updateProperties(currentEllipse);
        } else if (mode == CanvasModel.Mode.DRAW_RECT) {
            canvasModel.setStartPoint(e.getPoint());
            Point startPoint = canvasModel.getStartPoint();
            Rectangle currentRect = new Rectangle(startPoint.getX(), startPoint.getY(), 0.0d, 0.0d, 3, Helper.randomColor());
            canvasModel.setCurrentRect(currentRect);
            shapeModel.addShape(currentRect);
            propertyPanel.updateProperties(currentRect);
        } else {
            Shape pressedShape = shapeModel.containsShape(e.getPoint());
            if (pressedShape != null) {
                shapeModel.setCurrentShape(pressedShape);
                propertyPanel.updateProperties(pressedShape);
                canvasModel.setStartPoint(e.getPoint());
            } else {
                shapeModel.setCurrentShape(null);
                propertyPanel.updateProperties(null);
            }
        }
    }

    public void onMouseReleased(MouseEvent e){
        if (canvasModel.getMode() != CanvasModel.Mode.NORMAL) {
            canvasModel.setCurrentLine(null);
            canvasModel.setCurrentEllipse(null);
            canvasModel.setCurrentRect(null);
            canvasModel.setMode(CanvasModel.Mode.NORMAL);
        }
    }

    public void onMouseMoved(MouseEvent e){
        if (canvasModel.getMode() == CanvasModel.Mode.NORMAL) {
            if (shapeModel.containsShape(e.getPoint()) != null) {
                canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                canvasPanel.setCursor(Cursor.getDefaultCursor());
            }
        } else {
            canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    public void onMouseDragged(MouseEvent e){
        //FIXME: apply better design patterns..
        CanvasModel.Mode mode = canvasModel.getMode();
        Point startPoint = canvasModel.getStartPoint();
        Line currentLine = canvasModel.getCurrentLine();
        Ellipse currentEllipse = canvasModel.getCurrentEllipse();
        Rectangle currentRect = canvasModel.getCurrentRect();

        if (mode == CanvasModel.Mode.DRAW_LINE && currentLine != null) {
            currentLine.setX2((double)e.getX());
            currentLine.setY2((double)e.getY());
            shapeModel.setCurrentShape(currentLine);
            propertyPanel.updateProperties(currentLine);
            canvasPanel.repaint();
        } else if (mode == CanvasModel.Mode.DRAW_ELLIPSE && currentEllipse != null) {
            currentEllipse.setWidth((double)Math.abs(e.getX() - startPoint.x));
            currentEllipse.setHeight((double)Math.abs(e.getY() - startPoint.y));
            shapeModel.setCurrentShape(currentEllipse);
            propertyPanel.updateProperties(currentEllipse);
            canvasPanel.repaint();
        } else if (mode == CanvasModel.Mode.DRAW_RECT && currentRect != null) {
            currentRect.setWidth((double)Math.abs(e.getX() - startPoint.x));
            currentRect.setHeight((double)Math.abs(e.getY() - startPoint.y));
            shapeModel.setCurrentShape(currentRect);
            propertyPanel.updateProperties(currentRect);
            canvasPanel.repaint();

        } else if (shapeModel.getCurrentShape() != null) {
            //Move the shape
            Shape currentShape = shapeModel.getCurrentShape();
            int deltaX = e.getX() - startPoint.x;
            int deltaY = e.getY() - startPoint.y;
            currentShape.move(deltaX, deltaY);
            canvasModel.setStartPoint(e.getPoint());
            shapeModel.setCurrentShape(currentShape);
            propertyPanel.updateProperties(currentShape);
            canvasPanel.repaint();
        }
    }

    public void drawAllShapes(Graphics2D g2d) {
        for (Shape shape : shapeModel.getShapes()) {
            shape.draw(g2d);
        }
    }

    public void createRandomShape() {
        Shape shape = Helper.createRandomShape();
        shapeModel.addShape(shape);
        propertyPanel.updateProperties(shape); //FIXME: use Observer logic instead, apply design patterns...
        canvasModel.setMode(CanvasModel.Mode.NORMAL);
        canvasPanel.repaint();
        propertyPanel.updateProperties(shape);
    }

    public void setDrawMode(CanvasModel.Mode mode) {
        canvasModel.setMode(mode);
    }

    public void drawText(String textInput) {
        Text text = Text.createText(textInput);
        shapeModel.addShape(text);
        propertyPanel.updateProperties(text); //FIXME: use Observer logic instead, apply design patterns...
        canvasPanel.repaint();
        propertyPanel.updateProperties(text);
    }

    public void drawImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // Set default directory
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp", "jpeg"));
        int result = fileChooser.showOpenDialog(canvasPanel);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Image image = Helper.createImage(selectedFile.getAbsolutePath());
            shapeModel.addShape(image);
            propertyPanel.updateProperties(image); //FIXME: use Observer logic instead, apply design patterns...
            canvasPanel.repaint();
            propertyPanel.updateProperties(image);
        } else {
            System.out.println("No file selected");
        }
    }

    public void clearShapes() {
        shapeModel.clearShapes();
        canvasPanel.repaint();
        propertyPanel.updateProperties(null);
    }

    public void applyPropertyUpdate(){
        Shape currentShape = shapeModel.getCurrentShape();
        if (currentShape != null) {
            currentShape.setText(propertyPanel.stringField.getText().isEmpty() ? null : propertyPanel.stringField.getText());
            currentShape.setX1(Double.parseDouble(propertyPanel.x1Field.getText()));
            currentShape.setY1(Double.parseDouble(propertyPanel.y1Field.getText()));
            currentShape.setX2(propertyPanel.x2Field.getText().isEmpty() ? null : Double.parseDouble(propertyPanel.x2Field.getText()));
            currentShape.setY2(propertyPanel.y2Field.getText().isEmpty() ? null : Double.parseDouble(propertyPanel.y2Field.getText()));
            currentShape.setWidth(propertyPanel.widthField.getText().isEmpty() ? null : Double.parseDouble(propertyPanel.widthField.getText()));
            currentShape.setHeight(propertyPanel.heightField.getText().isEmpty() ? null : Double.parseDouble(propertyPanel.heightField.getText()));
            currentShape.setStroke(Integer.parseInt(propertyPanel.strokeField.getText()));
            currentShape.setColor(Color.decode(propertyPanel.colorField.getText()));
            canvasPanel.repaint();
        }
    }

    public void applyBringToFront() {
        Shape currentShape = shapeModel.getCurrentShape();
        if (currentShape != null) {
            shapeModel.bringToFront(currentShape);
            canvasPanel.repaint();
        }
    }

    public void applySendToBack(){
        Shape currentShape = shapeModel.getCurrentShape();
        if (currentShape != null) {
            shapeModel.sendToBack(currentShape);
            canvasPanel.repaint();
        }
    }

    public void applyRemoveShape(){
        Shape currentShape = shapeModel.getCurrentShape();
        if (currentShape != null) {
            shapeModel.removeShape(currentShape);
            canvasPanel.repaint();
            propertyPanel.updateProperties(null);
        }
    }
}
