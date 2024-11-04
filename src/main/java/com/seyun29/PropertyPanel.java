package com.seyun29;

import com.seyun29.Model.BoardModel;
import com.seyun29.Model.Shape;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.seyun29.Model.GlobalProperties.*;

public class PropertyPanel extends JPanel {
    //TODO: add buttons to apply the property changes
    //TODO: add button to remove the selected shape
    private final JTextField typeField;
    private final JTextField x1Field;
    private final JTextField y1Field;
    private final JTextField x2Field;
    private final JTextField y2Field;
    private final JTextField widthField;
    private final JTextField heightField;
    private final JTextField strokeField;
    private final JTextField colorField;

    @Setter
    private BoardModel boardModel;
    @Setter
    private CanvasPanel canvasPanel;

    public PropertyPanel() {
        this.setBackground(PROPERTY_PANEL_COLOR);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setPreferredSize(new Dimension(300, WINDOW_HEIGHT));

        Font labelFont = new Font("Arial", Font.BOLD, 12);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);

        JPanel typeFieldPanel = createLabeledField("Type", typeField = new JTextField(), labelFont, fieldFont);
        typeField.setEditable(false);
        this.add(typeFieldPanel);
        this.add(createLabeledField("x1", x1Field = new JTextField(), labelFont, fieldFont));
        this.add(createLabeledField("y1", y1Field = new JTextField(), labelFont, fieldFont));
        this.add(createLabeledField("x2", x2Field = new JTextField(), labelFont, fieldFont));
        this.add(createLabeledField("y2", y2Field = new JTextField(), labelFont, fieldFont));
        this.add(createLabeledField("Width", widthField = new JTextField(), labelFont, fieldFont));
        this.add(createLabeledField("Height", heightField = new JTextField(), labelFont, fieldFont));
        this.add(createLabeledField("Stroke", strokeField = new JTextField(), labelFont, fieldFont));
        this.add(createLabeledField("Color", colorField = new JTextField(), labelFont, fieldFont));

        Button applyButton = new Button("Apply Changes");
        applyButton.addActionListener(e -> {
            Shape currentShape = boardModel.getCurrentShape();
            if (currentShape != null) {
                currentShape.setX1(Double.parseDouble(x1Field.getText()));
                currentShape.setY1(Double.parseDouble(y1Field.getText()));
                currentShape.setX2(x2Field.getText().isEmpty() ? null : Double.parseDouble(x2Field.getText()));
                currentShape.setY2(y2Field.getText().isEmpty() ? null : Double.parseDouble(y2Field.getText()));
                currentShape.setWidth(widthField.getText().isEmpty() ? null : Double.parseDouble(widthField.getText()));
                currentShape.setHeight(heightField.getText().isEmpty() ? null : Double.parseDouble(heightField.getText()));
                currentShape.setStroke(Integer.parseInt(strokeField.getText()));
                currentShape.setColor(Color.decode(colorField.getText()));
                canvasPanel.refresh();
            }
        });
        Button removeButton = new Button("Remove this Object");
        removeButton.addActionListener(e -> {
            Shape currentShape = boardModel.getCurrentShape();
            if (currentShape != null) {
                boardModel.removeShape(currentShape);
                canvasPanel.refresh();
            }
        });
        this.add(applyButton);
        this.add(removeButton);
    }

    private JPanel createLabeledField(String labelText, JTextField textField, Font labelFont, Font fieldFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));
        panel.setBackground(PROPERTY_BOX_COLOR);

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(PROPERTY_TEXT_COLOR);
        label.setPreferredSize(new Dimension(50, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.WEST);

        textField.setFont(fieldFont);
        textField.setEditable(true);
        textField.setBackground(PROPERTY_BOX_COLOR);
        textField.setForeground(PROPERTY_TEXT_COLOR);
        textField.setPreferredSize(new Dimension(250, 20));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(textField, BorderLayout.CENTER);

        return panel;
    }

    public void updateProperties(Shape shape) {
        if (shape != null) {
            typeField.setText(shape.getShapeType().toString());
            x1Field.setText(shape.getX1().toString());
            y1Field.setText(shape.getY1().toString());
            x2Field.setText(shape.getX2() != null ? shape.getX2().toString() : "");
            y2Field.setText(shape.getY2() != null ? shape.getY2().toString() : "");
            widthField.setText(shape.getWidth() != null ? shape.getWidth().toString() : "");
            heightField.setText(shape.getHeight() != null ? shape.getHeight().toString() : "");
            strokeField.setText(shape.getStroke().toString());
            colorField.setText(String.format("#%02x%02x%02x", shape.getColor().getRed(), shape.getColor().getGreen(), shape.getColor().getBlue()));
//            this.remove(x2Field.getParent()); //-> Remove the whole panel, if possible
        } else {
            typeField.setText("");
            x1Field.setText("");
            y1Field.setText("");
            x2Field.setText("");
            y2Field.setText("");
            widthField.setText("");
            heightField.setText("");
            strokeField.setText("");
            colorField.setText("");
        }
    }
}