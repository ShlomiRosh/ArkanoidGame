package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class EndScreen implements Animation {
    private Counter lives;
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private AnimationRunner runner;

    public EndScreen(Counter scoreCounter, Counter live, KeyboardSensor key, AnimationRunner animationRunner) {
        this.stop = false;
        this.score = scoreCounter;
        this.lives = live;
        this.keyboard = key;
        this.runner = animationRunner;
        sound();
    }

    private void sound() {
        if (lives.getValue() == 0) {
            new Sound("file:./sound/wah.wav", 0).playSound();
        } else {
            new Sound("file:./sound/3.wav", 0).playSound();
        }
    }
    @Override
    public void doOneFrame(DrawSurface d) {

        Integer smiley = 0x1F60D;
        if (lives.getValue() == 0) {
            smiley = 0x1F621;
            d.setColor(Color.red);
            d.drawText(230, 120, "You Lost", 80);
            d.setColor(Color.black);
            d.drawText(227, 117, "You Lost", 80);
        } else {
            d.setColor(Color.red);
            d.drawText(230, 120, "You Win!", 80);
            d.setColor(Color.black);
            d.drawText(227, 117, "You Win!", 80);
        }
        d.setColor(Color.YELLOW);
        d.fillCircle(380, 290, 130);
        d.setColor(Color.BLACK);
        d.drawText(230, 400, String.valueOf(Character.toChars(smiley)), 300);
        d.drawText(30, 570, "Final score: " + Integer.toString(score.getValue()), 20);
        d.setColor(Color.BLUE);
        d.drawText(208, 498, "Press space to continue", 32);
        d.setColor(Color.BLACK);
        d.drawText(210, 500, "Press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
        if (this.stop) {
            runner.getGui().close();
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
