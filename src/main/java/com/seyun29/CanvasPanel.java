package com.seyun29;

import com.seyun29.Model.BoardModel;
import com.seyun29.Model.Ellipse;
import com.seyun29.Model.Line;
import com.seyun29.Model.Rectangle;
import com.seyun29.Model.Shape;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static com.seyun29.Model.GlobalProperties.WINDOW_HEIGHT;
import static com.seyun29.Model.GlobalProperties.WINDOW_WIDTH;

public class CanvasPanel extends JPanel {
    public enum Mode {
        NORMAL,
        DRAW_LINE,
        DRAW_ELLIPSE,
        DRAW_RECT
    }

    @Setter
    private Mode mode = Mode.NORMAL;
    private final BoardModel boardModel;
    private Point startPoint;
    private Line currentLine;
    private Ellipse currentEllipse;
    private Rectangle currentRect;

    CanvasPanel(BoardModel boardModel) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        this.boardModel = boardModel;
        for (int i = 0; i < 3; i++) {
            this.boardModel.addShape(Helper.createRandomShape());
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (mode == Mode.DRAW_LINE) {
                    startPoint = e.getPoint();
                    currentLine = new Line(startPoint.getX(), startPoint.getY(), startPoint.getX(), startPoint.getY(), 1, Helper.randomColor());
                    boardModel.addShape(currentLine);
                } else if (mode == Mode.DRAW_ELLIPSE){
                    startPoint = e.getPoint();
                    currentEllipse = new Ellipse(startPoint.getX(), startPoint.getY(), 0.0d, 0.0d, 1, Helper.randomColor());
                    boardModel.addShape(currentEllipse);
                } else if (mode == Mode.DRAW_RECT) {
                    startPoint = e.getPoint();
                    currentRect = new Rectangle(startPoint.getX(), startPoint.getY(), 0.0d, 0.0d, 1, Helper.randomColor());
                    boardModel.addShape(currentRect);
                } else {
                    Shape pressedShape = boardModel.containsShape(e.getPoint());
                    if (pressedShape != null) {
                        boardModel.setCurrentShape(pressedShape);
                        startPoint = e.getPoint();
                    } else {
                        boardModel.setCurrentShape(null);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (mode != Mode.NORMAL) {
                    currentLine = null;
                    currentEllipse = null;
                    currentRect = null;
                    mode = Mode.NORMAL;
                }
            }

//            @Override
//            public void mouseClicked(MouseEvent e) {
//                //If its a simple "click" instead of "drag", cancel the drawing
////                if (mode != Mode.NORMAL) {
////                    Shape clickedShape = boardModel.containsShape(e.getPoint());
////                    if (clickedShape != null) {
////                        boardModel.setCurrentShape(clickedShape);
////                    } else {
////                        boardModel.setCurrentShape(null);
////                    }
////                }
//            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (mode == Mode.NORMAL) {
                    if (boardModel.containsShape(e.getPoint()) != null) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    } else {
                        setCursor(Cursor.getDefaultCursor());
                    }
                } else {
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (mode == Mode.DRAW_LINE && currentLine != null) {
                    currentLine.setX2((double)e.getX());
                    currentLine.setY2((double)e.getY());
                    boardModel.setCurrentShape(currentLine);
                    repaint();
                } else if (mode == Mode.DRAW_ELLIPSE && currentEllipse != null) {
                    currentEllipse.setWidth((double)Math.abs(e.getX() - startPoint.x));
                    currentEllipse.setHeight((double)Math.abs(e.getY() - startPoint.y));
                    boardModel.setCurrentShape(currentEllipse);
                    repaint();
                } else if (mode == Mode.DRAW_RECT && currentRect != null) {
                    currentRect.setWidth((double)Math.abs(e.getX() - startPoint.x));
                    currentRect.setHeight((double)Math.abs(e.getY() - startPoint.y));
                    boardModel.setCurrentShape(currentRect);
                    repaint();

                } else if (boardModel.getCurrentShape() != null) {
                    //Move the shape
                    Shape currentShape = boardModel.getCurrentShape();
                    int deltaX = e.getX() - startPoint.x;
                    int deltaY = e.getY() - startPoint.y;
                    currentShape.move(deltaX, deltaY);
                    startPoint = e.getPoint();
                    boardModel.setCurrentShape(currentShape);
                    repaint();
                }
            }
        });
    }

//    public void setMode(Mode mode) {
//        this.mode = mode;
//        switch (mode) {
//            case DRAW_LINE:
//            case DRAW_ELLIPSE:
//            case DRAW_RECT:
//                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
//                break;
//            default:
//                setCursor(Cursor.getDefaultCursor());
//                break;
//        }
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boardModel.drawShapes((Graphics2D) g);
    }

    public void refresh() {
        repaint();
    }
}