import lib.Canvas;
import lib.house.House;
import lib.tree.Tree;

import java.awt.*;
import java.util.Random;

public class Main {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        Canvas canvas = new Canvas("LandschaftJ", 1000, 700, new Color(0x0099FF));
        int j = 1;
        int citySize = rand.nextInt(20);
        for (int i = 0; i < citySize; i++) {
            new House(
                    canvas,
                    rand.nextInt(1, 10) * 100 + j*50,
                    j * 100,
                    100,
                    rand.nextInt(1, 10),
                    Color.GRAY,
                    new Color(rand.nextInt(128, 256), rand.nextInt(128), rand.nextInt(128))
            );
            if (i == citySize / 2) {
                j++;
            }
        }
        canvas.draw();
    }
}