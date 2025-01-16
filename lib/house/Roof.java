package lib.house;

import java.awt.*;

public class Roof {
    public Roof (Object o, lib.Canvas canvas, Color color, int x, int y, int width) {
        int[] xPointsRoof = {x, (int) (x + (width / 1.5)), (int) (x - (width / 1.5))};
        int[] yPointsRoof = {y, y + (width / 3), y + (width / 3)};
        canvas.addShape(o, new Polygon(xPointsRoof, yPointsRoof, 3), color);
    }
}
