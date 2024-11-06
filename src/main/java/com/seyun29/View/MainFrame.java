package com.seyun29.View;

import com.seyun29.Controller.Controller;
import com.seyun29.Model.BoardModel;
import com.seyun29.View.Panel.CanvasPanel;
import com.seyun29.View.Panel.MenuPanel;
import com.seyun29.View.Panel.PropertyPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    //Model
    BoardModel boardModel = new BoardModel();
    //Controller
    Controller controller = new Controller(boardModel);
    //Views
    PropertyPanel propertyPanel = new PropertyPanel(controller);
    MenuPanel menuPanel = new MenuPanel(controller);
    CanvasPanel canvasPanel = new CanvasPanel(controller);

    public MainFrame() {
        //Dependency Injection //FIXME: use Observer logic instead, apply design patterns...
        controller.setCanvasPanel(canvasPanel);
        controller.setPropertyPanel(propertyPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100, 700); // Set the frame size
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Set preferred sizes
        canvasPanel.setPreferredSize(new Dimension(700,700));
        propertyPanel.setPreferredSize(new Dimension(250,700));
        menuPanel.setPreferredSize(new Dimension(150,700));

        this.add(canvasPanel, BorderLayout.CENTER);
        this.add(propertyPanel, BorderLayout.EAST);
        this.add(menuPanel, BorderLayout.WEST);
        this.pack();

        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}