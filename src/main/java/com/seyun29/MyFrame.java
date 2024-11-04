package com.seyun29;

import com.seyun29.Model.BoardModel;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    PropertyPanel propertyPanel = new PropertyPanel();
    BoardModel boardModel = new BoardModel(propertyPanel);
    CanvasPanel canvasPanel = new CanvasPanel(boardModel);

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600); // Set the frame size
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        propertyPanel.setBoardModel(boardModel);
        propertyPanel.setCanvasPanel(canvasPanel);

        // Set preferred sizes
        canvasPanel.setPreferredSize(new Dimension((int) (this.getWidth() * 2 / 3), this.getHeight()));
        propertyPanel.setPreferredSize(new Dimension((int) (this.getWidth() * 1 / 3), this.getHeight()));

        this.add(canvasPanel, BorderLayout.WEST);
        this.add(propertyPanel, BorderLayout.EAST);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyFrame::new);
    }
}