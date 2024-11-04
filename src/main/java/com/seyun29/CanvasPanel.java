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

public class CanvasPanel extends JPanel {
    private enum Mode {
        NORMAL,
        DRAW_LINE,
        DRAW_ELLIPSE,
        DRAW_RECT
    }
    private Mode mode = Mode.NORMAL;
    private final BoardModel boardModel;
    private Point startPoint;

    CanvasPanel(BoardModel boardModel) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        this.boardModel = boardModel;
        for (int i = 0; i < 10; i++) {
            this.boardModel.addShape(Helper.createRandomShape());
        }
        //TODO: implement line drawing with mouse feature
        //TODO: implement rectangle drawing with mouse feature
        //TODO: implement oval drawing with mouse feature
        //TODO: add image to the panel
        //TODO: add text to the panel

        Button drawLineBtn = new Button("Draw YOUR Line");
        //FIXME: fixme
        drawLineBtn.addActionListener((e)->{
            boardModel.addShape(Helper.createRandomShape());
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
                    boardModel.setCurrentShape(clickedShape);
                    clickedShape.printProperty();
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
//                FIXME: fix this
                if (boardModel.getCurrentShape() != null) {
                    Shape currentShape = boardModel.getCurrentShape();
                    int deltaX = e.getX() - startPoint.x;
                    int deltaY = e.getY() - startPoint.y;
                    currentShape.move(deltaX, deltaY);
                    startPoint = e.getPoint();
                    boardModel.setCurrentShape(currentShape); //FIXME: fix this,, use observer pattern
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        boardModel.drawShapes((Graphics2D) g);
    }

    public void refresh() {
        repaint();
    }
}