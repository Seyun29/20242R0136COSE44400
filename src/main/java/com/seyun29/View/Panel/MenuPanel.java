package com.seyun29.View.Panel;
import com.seyun29.Controller.Controller;
import com.seyun29.Model.CanvasModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import static com.seyun29.GlobalProperties.WINDOW_HEIGHT;

public class MenuPanel extends JPanel {
    private final Controller controller;

    public MenuPanel(Controller controller) {
        this.controller = controller;
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(200, WINDOW_HEIGHT));

        Dimension buttonSize = new Dimension(150, 30); // Set fixed size for buttons

        Button createRandomShape = new Button("Create Random");
        createRandomShape.setPreferredSize(buttonSize);
        //FIXME: fixme -> add design patterns
        createRandomShape.addActionListener((e)-> this.controller.createRandomShape());

        Button drawLineBtn = new Button("Draw Line");
        drawLineBtn.setPreferredSize(buttonSize);
        drawLineBtn.addActionListener((e)-> this.controller.setDrawMode(CanvasModel.Mode.DRAW_LINE));

        Button drawEllipseBtn = new Button("Draw Ellipse");
        drawEllipseBtn.setPreferredSize(buttonSize);
        drawEllipseBtn.addActionListener((e)-> this.controller.setDrawMode(CanvasModel.Mode.DRAW_ELLIPSE));

        Button drawRectBtn = new Button("Draw Rectangle");
        drawRectBtn.setPreferredSize(buttonSize);
        drawRectBtn.addActionListener((e)-> this.controller.setDrawMode(CanvasModel.Mode.DRAW_RECT));

        //enter text textfield
        TextField enterTextField = new TextField("Enter text here!");
        enterTextField.setPreferredSize(new Dimension(150, 30));

        Button addTextBtn = new Button("Add Text");
        addTextBtn.setPreferredSize(buttonSize);
        addTextBtn.addActionListener((e)-> this.controller.drawText(enterTextField.getText()));

        Button addImageBtn = new Button("Add Image");
        addImageBtn.setPreferredSize(buttonSize);
        addImageBtn.addActionListener((e)-> this.controller.drawImage());

        Button clearBtn = new Button("Clear");
        clearBtn.setPreferredSize(buttonSize);
        clearBtn.addActionListener((e)-> this.controller.clearShapes());

        enterTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (enterTextField.getText().equals("Enter text here!")) {
                    enterTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (enterTextField.getText().isEmpty()) {
                    enterTextField.setText("Enter text here!");
                }
            }
        });

        this.add(createRandomShape);
        this.add(drawLineBtn);
        this.add(drawEllipseBtn);
        this.add(drawRectBtn);
        this.add(addImageBtn);
        this.add(enterTextField);
        this.add(addTextBtn);
        this.add(clearBtn);
    }
}