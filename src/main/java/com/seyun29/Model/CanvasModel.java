package com.seyun29.Model;

import com.seyun29.Model.Shape.Ellipse;
import com.seyun29.Model.Shape.Line;
import com.seyun29.Model.Shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class CanvasModel {
    public enum Mode {
        NORMAL,
        DRAW_LINE,
        DRAW_ELLIPSE,
        DRAW_RECT
    }
    private Point startPoint;
    private Line currentLine = null;
    private Ellipse currentEllipse = null;
    private Rectangle currentRect = null;
    private Mode mode = Mode.NORMAL;
}
