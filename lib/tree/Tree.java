package lib.tree;

import lib.Canvas;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Tree {
    public Tree (Canvas canvas, int x, int y, int size) {
        canvas.addShape(this, new Rectangle(x, y, size / 5, size), new Color(0xFF654321));
        canvas.addShape(this, new Ellipse2D.Double( x - (float) size * 0.35, y - size * 0.7, (float) size * 0.9, (float) size * 0.8), Color.GREEN);
    }
}
