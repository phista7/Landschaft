package lib.house;

import lib.Canvas;

import java.awt.*;
import java.util.Random;

public class House {
    private final int x;
    private final int y;
    private final int width;
    private final int floors;
    private final Canvas canvas;
    private final Color wallColor;
    private int curH;
    private int curX;
    private int curY;

    public House(Canvas canvas, int x, int y, int width, int floors, Color wallColor, Color roofColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.floors = floors;
        this.canvas = canvas;
        this.wallColor = wallColor;

        new Roof(this, canvas, roofColor, x, y, width);
        generateFloors();
    }

    private void generateFloors() {
        Random rand = new Random();

        for (int i = 0; i < floors && i < 10; i++) {
            curH = width / 2;
            curX = x - width / 2;
            curY = y + width / 3 + (i * curH);
            generateFloor();
            generateWindow(rand.nextBoolean(), true);
            if (i < floors - 1 && i < 9) {
                generateWindow(rand.nextBoolean(), false);
            } else {
                generateDoor();
            }

        }
    }

    private void generateFloor() {
        canvas.addShape(
                this,
                new Rectangle(
                        curX,
                        curY,
                        width,
                        curH
                ),
                wallColor
        );
    }

    private void generateDoor() {
        canvas.addShape(
                this,
                new Rectangle(
                        curX + width / 4 * 3 - 12,
                        curY + curH / 2 - 8,
                        24,
                        curY - (curY - curH / 2 - 8)
                ),
                Color.BLACK
        );
    }

    private void generateWindow(boolean lightsOn, boolean left) {
        canvas.addShape(
                this,
                new Rectangle(
                        left ? curX + width / 4 - width / 8 : curX + width / 4 * 3 - width / 8,
                        curY + curH / 2 - 8,
                        width / 4,
                        curH / 3
                ),
                lightsOn ? Color.YELLOW : Color.BLACK
        );
    }
}
