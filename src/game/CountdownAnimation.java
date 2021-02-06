package game;
import sprite.*;
import biuoop.DrawSurface;
import java.awt.*;


public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int countDown;
    private boolean stop = false;
    private SpriteCollection gameScreen;
    private double initialTime = System.currentTimeMillis();

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.countDown = countFrom;
    }
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.BLACK);
        d.drawText(360, 400, getNumber(), 48);
        d.setColor(Color.RED);
        d.drawText(360, 400, getNumber(), 45);
        if (countDown < 1) {
            stop = true;
        }
    }
    public boolean shouldStop() {
        return this.stop;
    }

    private String getNumber() {
        double currentTime = System.currentTimeMillis();
        if ((currentTime - initialTime) > (numOfSeconds / (countFrom + 1)) * 1500) {
            countDown--;
            initialTime = System.currentTimeMillis();
        }
        if (countDown == 0) {
            return ("GO");
        }
        return ("  " + countDown + "");
    }

}
