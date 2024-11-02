package com.seyun29;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class MyPanel extends JPanel {

    private Rectangle rect;
    private Ellipse2D oval;
//    private Image image;
    private Line2D line;
    private Point clickPoint;

    MyPanel() {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(500, 500));
        this.rect = new Rectangle(0, 0, 100, 100);
        this.oval = new Ellipse2D.Double(0, 0, 120, 100);
//        this.image = new Image();
        this.line = new Line2D.Double(0, 0, 100, 100);
//        this.line = null; -> implement line drawing with mouse feature
        //TODO: implement line drawing with mouse feature
        //TODO: implement rectangle drawing with mouse feature
        //TODO: implement oval drawing with mouse feature
        //TODO: add image to the panel
        //TODO: add text to the panel

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed at: " + e.getX() + ", " + e.getY());
                System.out.println("Mouse pressed at: " + e.getPoint());
                clickPoint = e.getPoint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());
                System.out.println("Mouse clicked at: " + e.getPoint());
                if (rect.contains(e.getPoint())) {
                    System.out.println("Rectangle clicked!");
                } else if (oval.contains(e.getPoint())) {
                    System.out.println("Oval clicked!");
                } else if (line.contains(e.getPoint())) {
                    System.out.println("Line clicked!");
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (rect.contains(e.getPoint()) || oval.contains(e.getPoint())) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                if (rect.contains(e.getPoint())) {
                    int deltaX = e.getX() - clickPoint.x;
                    int deltaY = e.getY() - clickPoint.y;
                    rect.setLocation(rect.x + deltaX, rect.y + deltaY);
                    clickPoint = e.getPoint();
                    repaint();
                } else if (oval.contains(e.getPoint())) {
                    int deltaX = e.getX() - clickPoint.x;
                    int deltaY = e.getY() - clickPoint.y;
                    oval.setFrame(oval.getX() + deltaX, oval.getY() + deltaY, oval.getWidth(), oval.getHeight());
                    clickPoint = e.getPoint();
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2)); //stroke of the objects


//        g2d.setPaint(Color.yellow);
        g2d.setColor(Color.red);
//        g2d.drawLine(0, 0, 100, 100);
        g2d.draw(line);
        g2d.setColor(Color.blue);
//        g2d.drawRect(0, 0, 100, 100);
//        g2d.fillRect(10, 10, 100, 100);
        g2d.draw(rect);
        g2d.setColor(Color.black);
//        g2d.drawOval(0,0,120,100);
        g2d.draw(this.oval);
//        g2d.drawImage();

    }

}