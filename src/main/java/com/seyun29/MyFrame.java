package com.seyun29;
import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyPanel panel = new MyPanel();

    MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
//        this.setLayout(null);
        this.setVisible(true);

        this.add(panel);
        this.pack();
    }

//    public void paint(Graphics g){
//        g.setColor(Color.red);
//        g.fillRect(130, 30, 100, 100);
//
//        g.setColor(Color.blue);
//        g.fillRect(130, 200, 100, 100);
//
//        g.setColor(Color.green);
//        g.fillRect(130, 370, 100, 100);
//    }
}
