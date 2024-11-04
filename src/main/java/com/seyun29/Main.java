package com.seyun29;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //TODO: utilize H2 or SQL to store the shapes,, + Serializable
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame frame = new MyFrame();
                frame.setVisible(true);
            }
        });
    }
}