package lib;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Canvas {
    private final JFrame frame;
    private final CanvasContent canvasContent;
    private Graphics2D canvasGraphics;
    private final Color backgroundColor;
    private Image canvasImage;
    private final List<Object> elements;
    private final Map<Object, List<ShapeAndColor>> shapesAndColorsMap;

    public Canvas(String title, int width, int height, Color backgroundColor) {
        frame = new JFrame(title);
        canvasContent = new CanvasContent();

        frame.setContentPane(canvasContent);

        canvasContent.setPreferredSize(new Dimension(width, height));

        this.backgroundColor = backgroundColor;

        frame.pack();

        elements = new ArrayList<>();
        shapesAndColorsMap = new HashMap<>();

        setVisible(true);
    }

    public void setVisible(boolean visible) {
        if (canvasGraphics == null) {
            Dimension size = canvasContent.getSize();
            canvasImage = canvasContent.createImage(size.width, size.height);

            canvasGraphics = (Graphics2D) canvasImage.getGraphics();
            canvasGraphics.setColor(backgroundColor);
            canvasGraphics.fillRect(0, 0, size.width, size.height);
            canvasGraphics.setColor(Color.BLACK);
        }

        frame.setVisible(visible);
    }

    public void addShape(Object element, Shape shape, Color color) {
        if (!elements.contains(element)) {
            elements.add(element);

            List<ShapeAndColor> shapeAndColors = new ArrayList<>();
            shapeAndColors.add(new ShapeAndColor(shape, color));

            shapesAndColorsMap.put(element, shapeAndColors);
        } else {
            List<ShapeAndColor> shapeAndColors = shapesAndColorsMap.get(element);
            shapeAndColors.add(new ShapeAndColor(shape, color));

            shapesAndColorsMap.put(element, shapeAndColors);
        }
    }

    public void unregisterElement(Object element) {
        elements.remove(element);
        shapesAndColorsMap.remove(element);
    }

    public void draw() {
        Color originalColor = canvasGraphics.getColor();
        Dimension size = canvasContent.getSize();

        canvasGraphics.setColor(backgroundColor);
        canvasGraphics.fillRect(0, 0, size.width, size.height);
        canvasGraphics.setColor(originalColor);

        for (Object element : elements) {
            for (ShapeAndColor shapeAndColor : shapesAndColorsMap.get(element)) {
                shapeAndColor.draw(canvasGraphics);
            }
        }
    }

    private class CanvasContent extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }

    private record ShapeAndColor(Shape shape, Color color) {
        public void draw(Graphics2D g) {
            g.setColor(color);
            g.fill(shape);
        }
    }
}
