package com.seyun29;

import com.seyun29.Model.BoardModel;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    PropertyPanel propertyPanel = new PropertyPanel();
    BoardModel boardModel = new BoardModel(propertyPanel);
    CanvasPanel canvasPanel = new CanvasPanel(boardModel);
    LeftPanel leftPanel = new LeftPanel();

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100, 700); // Set the frame size
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        propertyPanel.setBoardModel(boardModel);
        propertyPanel.setCanvasPanel(canvasPanel);

        leftPanel.setCanvasPanel(canvasPanel);
        leftPanel.setBoardModel(boardModel);
        leftPanel.setPropertyPanel(propertyPanel);

        // Set preferred sizes
        canvasPanel.setPreferredSize(new Dimension(700,700));
        propertyPanel.setPreferredSize(new Dimension(250,700));
        leftPanel.setPreferredSize(new Dimension(150,700));

        this.add(canvasPanel, BorderLayout.CENTER);
        this.add(propertyPanel, BorderLayout.EAST);
        this.add(leftPanel, BorderLayout.WEST);
        this.pack();

        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyFrame::new);
    }
}