package com.seyun29;
import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    MyPanel() {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2)); //stroke of the objects


//        g2d.setPaint(Color.yellow);
        g2d.setColor(Color.red);
        g2d.drawLine(0, 0, 100, 100);
        g2d.setColor(Color.blue);
        g2d.drawRect(0, 0, 100, 100);
//        g2d.fillRect(10, 10, 100, 100);
        g2d.setColor(Color.black);
        g2d.drawOval(0,0,120,100);
//        g2d.drawImage();

    }

}