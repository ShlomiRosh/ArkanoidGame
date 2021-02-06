/* name: shalomi rosh
   id: 308154418
   oop
*/
package test;

import biuoop.Sleeper;
import biuoop.GUI;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Velocity;
import sprite.Ball;
import java.awt.Color;

/**
 *  method that runs the bouncing ball animation.
 * @author Shlomi Rosh
 */
public class BouncingBallAnimation {
    /**
     * method that runs the bouncing ball animation.
     * @param args Not used here
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 800, 600);
        Sleeper sleeper = new Sleeper();
        java.util.Random rand = new java.util.Random();
        Ball ball = new Ball(new Point(100, 100), 30, Color.BLUE);
        Velocity v = Velocity.fromAngleAndSpeed(35, 30);
        ball.setVelocity(v);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
