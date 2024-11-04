package com.seyun29;

//import java.awt.*;
import com.seyun29.Model.*;
import com.seyun29.Model.Rectangle;
import com.seyun29.Model.Shape;

import java.awt.*;
import java.util.Random;

import static com.seyun29.Model.GlobalProperties.*;

public class Helper {
    public static Color randomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static Shape createRandomShape() {
        Random random = new Random();
        int type = random.nextInt(3);
        switch(type){
            case 0:
                //Double x1, Double y1, Double width, Double height, Double x2, Double y2, Integer stroke, Color color
//                return new Rectangle(random.nextDouble(500), random.nextDouble(500), random.nextDouble(200), random.nextDouble(200));
                return new Rectangle(random.nextDouble()*WINDOW_WIDTH, random.nextDouble()*WINDOW_HEIGHT, random.nextDouble()*SHAPE_WIDTH, random.nextDouble()*SHAPE_HEIGHT);
            case 1:
//              return new Ellipse(random.nextDouble()*500, random.nextDouble()*500, random.nextDouble()*120, random.nextDouble()*100);
                return new Ellipse(random.nextDouble()*WINDOW_WIDTH, random.nextDouble()*WINDOW_HEIGHT, random.nextDouble()*SHAPE_WIDTH, random.nextDouble()*SHAPE_HEIGHT);
            case 2:
//              return new Line(random.nextDouble()*500, random.nextDouble()*500, random.nextDouble()*500, random.nextDouble()*500);
                return new Line(random.nextDouble()*WINDOW_WIDTH, random.nextDouble()*WINDOW_HEIGHT, random.nextDouble()*WINDOW_WIDTH, random.nextDouble()*WINDOW_HEIGHT);
            default:
                return null;
        }
    }
}
