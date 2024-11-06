package com.seyun29;


import com.seyun29.View.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //TODO: utilize H2 or SQL to store the shapes,, + Serializable
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}