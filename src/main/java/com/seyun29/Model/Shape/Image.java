package com.seyun29.Model.Shape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image extends Shape {
    private BufferedImage image;

    public Image(Double x1, Double y1, Double width, Double height, Integer stroke, Color color, String imagePath) {
        super(x1, y1, width, height, null, null, stroke, color, ShapeType.IMAGE);
        try {
            this.image = ImageIO.read(new File(imagePath));
            double ratio = (double)(Math.max(image.getWidth(), image.getHeight()) / 300);
            this.setX1(0.0d);
            this.setY1(0.0d);
            this.setWidth((double)image.getWidth() / ratio);
            this.setHeight((double)image.getHeight() / ratio);
        } catch (IOException e) {
            System.out.println("IO Exception occured while reading image file");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        if (image != null) {
            g2d.drawImage(image, getX1().intValue(), getY1().intValue(), getWidth().intValue(), getHeight().intValue(), null);
        }
    }

    public boolean contains(Point point) {
        int x = getX1().intValue();
        int y = getY1().intValue();
        int width = getWidth().intValue();
        int height = getHeight().intValue();
        return point.x >= x && point.x <= x + width && point.y >= y && point.y <= y + height;
    }
}