package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;


public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillCircle(380, 300, 90);
        d.setColor(Color.WHITE);
        d.fillCircle(380, 300, 88);
        d.setColor(Color.BLUE);
        d.fillCircle(380, 300, 80);
        d.setColor(Color.GRAY);
        d.fillCircle(380, 300, 76);
        d.setColor(Color.BLACK);
        d.fillCircle(380, 300, 74);
        d.setColor(Color.GRAY);
        d.fillRectangle(343, 260, 35, 85);
        d.setColor(Color.GRAY);
        d.fillRectangle(385, 260, 35, 85);
        d.setColor(Color.BLUE);
        d.fillRectangle(345, 262, 33, 83);
        d.setColor(Color.BLUE);
        d.fillRectangle(387, 262, 33, 83);
        d.setColor(Color.BLUE);
        d.drawText(208, 498, "Press space to continue", 32);
        d.setColor(Color.BLACK);
        d.drawText(210, 500, "Press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    public boolean shouldStop() { return this.stop; }
}
