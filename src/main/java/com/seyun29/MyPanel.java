package com.seyun29;
import com.seyun29.Model.BoardModel;
import com.seyun29.Model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static com.seyun29.Model.GlobalProperties.WINDOW_HEIGHT;
import static com.seyun29.Model.GlobalProperties.WINDOW_WIDTH;

public class MyPanel extends JPanel {
    private enum Mode {
        NORMAL,
        DRAW_LINE,
        DRAW_ELLIPSE,
        DRAW_RECT
    }
    private Mode mode = Mode.NORMAL;
    private final BoardModel boardModel;
    private Point startPoint;

    MyPanel() {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        this.boardModel = new BoardModel();
        for (int i = 0; i < 10; i++) {
            this.boardModel.addShape(Helper.createRandomShape());
        }
        //TODO: implement line drawing with mouse feature
        //TODO: implement rectangle drawing with mouse feature
        //TODO: implement oval drawing with mouse feature
        //TODO: add image to the panel
        //TODO: add text to the panel

        Button drawLineBtn = new Button("Draw YOUR Line");
        drawLineBtn.addActionListener((e)->{
//            boardModel.addShape(Helper.createRandomShape());
            this.mode = Mode.DRAW_LINE;
            repaint();
        });
        this.add(drawLineBtn);
        Button clearBtn = new Button("Clear");
        clearBtn.addActionListener((e)->{
            boardModel.clearShapes();
            repaint();
        });
        this.add(clearBtn); //TODO: implement clear button

//        this.add(createRectBtn);
//        this.add(createEllipseBtn);
//        this.add(createLineBtn);
//        this.add(createImageBtn);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Shape pressedShape = boardModel.containsShape(e.getPoint());
                if (pressedShape != null) {
                    boardModel.setCurrentShape(pressedShape);
                    startPoint = e.getPoint();
                } else boardModel.setCurrentShape(null);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Shape clickedShape = boardModel.containsShape(e.getPoint());
                if (clickedShape != null) {
                    //show properties on dashboard
                    clickedShape.setClicked(true);
                    boardModel.setCurrentShape(clickedShape);
//                    clickedShape.printProperty();
                    startPoint = e.getPoint();
                } else boardModel.setCurrentShape(null);
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (boardModel.containsShape(e.getPoint()) != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(Cursor.getDefaultCursor());
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                //FIXME: fix this
                if (boardModel.getCurrentShape() != null) {
                    Shape currentShape = boardModel.getCurrentShape();
                    int deltaX = e.getX() - startPoint.x;
                    int deltaY = e.getY() - startPoint.y;
                    currentShape.move(deltaX, deltaY);
                    startPoint = e.getPoint();
                    repaint();
                }
//                if (rect.contains(e.getPoint())) {
//                    int deltaX = e.getX() - startPoint.x;
//                    int deltaY = e.getY() - startPoint.y;
//                    rect.setFrame(rect.getX() + deltaX, rect.getY() + deltaY, rect.getWidth(), rect.getHeight());
//                    startPoint = e.getPoint();
//                    repaint();
//                } else if (oval.contains(e.getPoint())) {
//                    int deltaX = e.getX() - startPoint.x;
//                    int deltaY = e.getY() - startPoint.y;
//                    oval.setFrame(oval.getX() + deltaX, oval.getY() + deltaY, oval.getWidth(), oval.getHeight());
//                    startPoint = e.getPoint();
//                    repaint();
//                }
            }
        });
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        boardModel.drawShapes((Graphics2D) g);
    }

}