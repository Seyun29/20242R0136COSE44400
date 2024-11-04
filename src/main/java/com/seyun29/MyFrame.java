package com.seyun29;
import javax.swing.*;

public class MyFrame extends JFrame {
    MyPanel panel = new MyPanel();

    MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
    }
}
