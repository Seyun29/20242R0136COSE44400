package com.seyun29.Controller;

import com.seyun29.Helper;
import com.seyun29.Model.BoardModel;
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
    private final BoardModel boardModel;
    private PropertyPanel propertyPanel;
    private CanvasPanel canvasPanel; //will be set in MainFrame

    public Controller(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public void onMousePressed(MouseEvent e){
        CanvasPanel.Mode mode = canvasPanel.getMode();
        if (mode == CanvasPanel.Mode.DRAW_LINE) {
            canvasPanel.setStartPoint(e.getPoint());
            Point startPoint = canvasPanel.getStartPoint();
            Line currentLine = new Line(startPoint.getX(), startPoint.getY(), startPoint.getX(), startPoint.getY(), 1, Helper.randomColor());
            canvasPanel.setCurrentLine(currentLine);
            boardModel.addShape(currentLine);
            propertyPanel.updateProperties(currentLine); //FIXME: use Observer logic instead, apply design patterns...
        } else if (mode == CanvasPanel.Mode.DRAW_ELLIPSE){
            canvasPanel.setStartPoint(e.getPoint());
            Point startPoint = canvasPanel.getStartPoint();
            Ellipse currentEllipse = new Ellipse(startPoint.getX(), startPoint.getY(), 0.0d, 0.0d, 1, Helper.randomColor());
            canvasPanel.setCurrentEllipse(currentEllipse);
            boardModel.addShape(currentEllipse);
            propertyPanel.updateProperties(currentEllipse); //FIXME: use Observer logic instead, apply design patterns...
        } else if (mode == CanvasPanel.Mode.DRAW_RECT) {
            canvasPanel.setStartPoint(e.getPoint());
            Point startPoint = canvasPanel.getStartPoint();
            Rectangle currentRect = new Rectangle(startPoint.getX(), startPoint.getY(), 0.0d, 0.0d, 1, Helper.randomColor());
            canvasPanel.setCurrentRect(currentRect);
            boardModel.addShape(currentRect);
            propertyPanel.updateProperties(currentRect); //FIXME: use Observer logic instead, apply design patterns...
        } else {
            Shape pressedShape = boardModel.containsShape(e.getPoint());
            if (pressedShape != null) {
                boardModel.setCurrentShape(pressedShape);
                propertyPanel.updateProperties(pressedShape); //FIXME: use Observer logic instead, apply design patterns...
                canvasPanel.setStartPoint(e.getPoint());
            } else {
                boardModel.setCurrentShape(null);
                propertyPanel.updateProperties(null);
            }
        }
    }

    public void onMouseReleased(MouseEvent e){
        if (canvasPanel.getMode() != CanvasPanel.Mode.NORMAL) {
            canvasPanel.setCurrentLine(null);
            canvasPanel.setCurrentEllipse(null);
            canvasPanel.setCurrentRect(null);
            canvasPanel.setMode(CanvasPanel.Mode.NORMAL);
        }
    }

    public void onMouseMoved(MouseEvent e){
        if (canvasPanel.getMode() == CanvasPanel.Mode.NORMAL) {
            if (boardModel.containsShape(e.getPoint()) != null) {
                canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                canvasPanel.setCursor(Cursor.getDefaultCursor());
            }
        } else {
            canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    public void onMouseDragged(MouseEvent e){
        CanvasPanel.Mode mode = canvasPanel.getMode();
        Point startPoint = canvasPanel.getStartPoint();
        Line currentLine = canvasPanel.getCurrentLine();
        Ellipse currentEllipse = canvasPanel.getCurrentEllipse();
        Rectangle currentRect = canvasPanel.getCurrentRect();

        if (mode == CanvasPanel.Mode.DRAW_LINE && currentLine != null) {
            currentLine.setX2((double)e.getX());
            currentLine.setY2((double)e.getY());
            boardModel.setCurrentShape(currentLine);
            propertyPanel.updateProperties(currentLine); //FIXME: use Observer logic instead, apply design patterns...
            canvasPanel.repaint();
        } else if (mode == CanvasPanel.Mode.DRAW_ELLIPSE && currentEllipse != null) {
            currentEllipse.setWidth((double)Math.abs(e.getX() - startPoint.x));
            currentEllipse.setHeight((double)Math.abs(e.getY() - startPoint.y));
            boardModel.setCurrentShape(currentEllipse);
            propertyPanel.updateProperties(currentEllipse); //FIXME: use Observer logic instead, apply design patterns...
            canvasPanel.repaint();
        } else if (mode == CanvasPanel.Mode.DRAW_RECT && currentRect != null) {
            currentRect.setWidth((double)Math.abs(e.getX() - startPoint.x));
            currentRect.setHeight((double)Math.abs(e.getY() - startPoint.y));
            boardModel.setCurrentShape(currentRect);
            propertyPanel.updateProperties(currentRect); //FIXME: use Observer logic instead, apply design patterns...
            canvasPanel.repaint();

        } else if (boardModel.getCurrentShape() != null) {
            //Move the shape
            Shape currentShape = boardModel.getCurrentShape();
            int deltaX = e.getX() - startPoint.x;
            int deltaY = e.getY() - startPoint.y;
            currentShape.move(deltaX, deltaY);
            canvasPanel.setStartPoint(e.getPoint());
            boardModel.setCurrentShape(currentShape);
            propertyPanel.updateProperties(currentShape); //FIXME: use Observer logic instead, apply design patterns...
            canvasPanel.repaint();
        }
    }

    public void drawShapes(Graphics2D g2d) {
        for (Shape shape : boardModel.getShapes()) {
            shape.draw(g2d);
        }
    }

    public void createRandomShape() {
        Shape shape = Helper.createRandomShape();
        boardModel.addShape(shape);
        propertyPanel.updateProperties(shape); //FIXME: use Observer logic instead, apply design patterns...
        canvasPanel.setMode(CanvasPanel.Mode.NORMAL);
        canvasPanel.repaint();
        propertyPanel.updateProperties(shape);
    }

    public void setDrawMode(CanvasPanel.Mode mode) {
        canvasPanel.setMode(mode);
    }

    public void addText(String textInput) {
        Text text = Text.createText(textInput);
        boardModel.addShape(text);
        propertyPanel.updateProperties(text); //FIXME: use Observer logic instead, apply design patterns...
        canvasPanel.repaint();
        propertyPanel.updateProperties(text);
    }

    public void addImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // Set default directory
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp", "jpeg"));
        int result = fileChooser.showOpenDialog(canvasPanel);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Image image = Helper.createImage(selectedFile.getAbsolutePath());
            boardModel.addShape(image);
            propertyPanel.updateProperties(image); //FIXME: use Observer logic instead, apply design patterns...
            canvasPanel.repaint();
            propertyPanel.updateProperties(image);
        } else {
            System.out.println("No file selected");
        }
    }

    public void clearShapes() {
        boardModel.clearShapes();
        canvasPanel.repaint();
        propertyPanel.updateProperties(null);
    }

    public void applyPropertyUpdate(){
        Shape currentShape = boardModel.getCurrentShape();
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
        Shape currentShape = boardModel.getCurrentShape();
        if (currentShape != null) {
            boardModel.bringToFront(currentShape);
            canvasPanel.repaint();
        }
    }

    public void applySendToBack(){
        Shape currentShape = boardModel.getCurrentShape();
        if (currentShape != null) {
            boardModel.sendToBack(currentShape);
            canvasPanel.repaint();
        }
    }

    public void applyRemoveShape(){
        Shape currentShape = boardModel.getCurrentShape();
        if (currentShape != null) {
            boardModel.removeShape(currentShape);
            canvasPanel.repaint();
            propertyPanel.updateProperties(null);
        }
    }
}
