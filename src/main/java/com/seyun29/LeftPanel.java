package com.seyun29;

import com.seyun29.Model.BoardModel;
import com.seyun29.Model.Image;
import com.seyun29.Model.Text;
import lombok.Setter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;

import com.seyun29.Model.Shape;

import static com.seyun29.Model.GlobalProperties.WINDOW_HEIGHT;

public class LeftPanel extends JPanel {
    @Setter
    private CanvasPanel canvasPanel;
    @Setter
    private BoardModel boardModel;
    @Setter
    private PropertyPanel propertyPanel;

    LeftPanel() {
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(200, WINDOW_HEIGHT));

        Dimension buttonSize = new Dimension(150, 30); // Set fixed size for buttons

        Button createRandomShape = new Button("Create Random");
        createRandomShape.setPreferredSize(buttonSize);

        Button drawLineBtn = new Button("Draw Line");
        drawLineBtn.setPreferredSize(buttonSize);
        drawLineBtn.addActionListener((e)->{
            canvasPanel.setMode(CanvasPanel.Mode.DRAW_LINE);
        });

        Button drawEllipseBtn = new Button("Draw Ellipse");
        drawEllipseBtn.setPreferredSize(buttonSize);
        drawEllipseBtn.addActionListener((e)->{
            canvasPanel.setMode(CanvasPanel.Mode.DRAW_ELLIPSE);
        });

        Button drawRectBtn = new Button("Draw Rectangle");
        drawRectBtn.setPreferredSize(buttonSize);
        drawRectBtn.addActionListener((e)->{
            canvasPanel.setMode(CanvasPanel.Mode.DRAW_RECT);
        });

        //enter text textfield
        TextField enterTextField = new TextField("Enter text here!");
        enterTextField.setPreferredSize(new Dimension(150, 30));
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

        Button addTextBtn = new Button("Add Text");
        addTextBtn.setPreferredSize(buttonSize);
        addTextBtn.addActionListener((e)->{
            Text text = Helper.createText(enterTextField.getText());
            boardModel.addShape(text);
//            canvasPanel.setMode(CanvasPanel.Mode.NORMAL);
            canvasPanel.refresh();
            propertyPanel.updateProperties(text);
        });

        Button addImageBtn = new Button("Add Image");
        addImageBtn.setPreferredSize(buttonSize);
        addImageBtn.addActionListener((e)-> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // Set default directory
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp", "jpeg"));
                    int result = fileChooser.showOpenDialog(this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        Image image = Helper.createImage(selectedFile.getAbsolutePath());
                        boardModel.addShape(image);
//                        canvasPanel.setMode(CanvasPanel.Mode.NORMAL);
                        canvasPanel.refresh();
                        propertyPanel.updateProperties(image);
                    } else {
                        System.out.println("No file selected");
                    }
                }
        );

        Button clearBtn = new Button("Clear");
        clearBtn.setPreferredSize(buttonSize);

        //FIXME: fixme -> add design patterns
        createRandomShape.addActionListener((e)->{
            Shape shape = Helper.createRandomShape();
            boardModel.addShape(shape);
            canvasPanel.setMode(CanvasPanel.Mode.DRAW_LINE);
            canvasPanel.refresh();
            propertyPanel.updateProperties(shape);
        });

        clearBtn.addActionListener((e)->{
            boardModel.clearShapes();
            canvasPanel.refresh();
            propertyPanel.updateProperties(null);
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