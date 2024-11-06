package com.seyun29.View.Panel;

import com.seyun29.Controller.Controller;
import com.seyun29.Model.Shape.Ellipse;
import com.seyun29.Model.Shape.Line;
import com.seyun29.Model.Shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static com.seyun29.GlobalProperties.WINDOW_HEIGHT;
import static com.seyun29.GlobalProperties.WINDOW_WIDTH;

@Getter
@Setter
public class CanvasPanel extends JPanel {
    private final Controller controller;

    public CanvasPanel(Controller controller) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        this.controller = controller;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                controller.onMousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                controller.onMouseReleased(e);
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                controller.onMouseMoved(e);
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                controller.onMouseDragged(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        controller.drawAllShapes((Graphics2D) g);
    }
}