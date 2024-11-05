package com.seyun29.Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Text extends Shape {

    public Text(Double x1, Double y1, String text) {
        super(x1, y1, null, null, null, null, 3, null, ShapeType.TEXT);
        this.setText(text);
    }

    public Text(Double x1, Double y1, String text, Integer stroke, Color color) {
        super(x1, y1, null, null, null, null, stroke, color, ShapeType.TEXT);
        this.setText(text);
    }

    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(getStroke()));
        g2d.setColor(getColor());
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString(this.getText(), getX1().intValue(), getY1().intValue());
    }

    public boolean contains(Point point) {
        // Create a temporary image to get the Graphics object
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) tempImage.getGraphics();
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics = g2d.getFontMetrics();

        int textWidth = metrics.stringWidth(this.getText());
        int textHeight = metrics.getHeight();
        int x = getX1().intValue();
        int y = getY1().intValue() - textHeight;

        return point.x >= x && point.x <= x + textWidth && point.y >= y && point.y <= y + textHeight;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        this.setX1(this.getX1() + deltaX);
        this.setY1(this.getY1() + deltaY);
    }
}
