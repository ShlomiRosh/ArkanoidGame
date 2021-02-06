package test;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprite.Ball;
import sprite.Block;
import java.awt.Color;

public class testBallBlocks {
    public static void main(String[] args) {
        GUI gui = new GUI(" ", 800, 600);
        Sleeper sleeper = new Sleeper();
        Ball b1 = new Ball(new Point(150,150),3, Color.black);
        Block w = new Block(new Rectangle(new Point(0,0), 800, 25), Color.GRAY, 1);
        Block t = new Block(new Rectangle(new Point(0,0), 25, 600), Color.GRAY, 1);
        Block y = new Block(new Rectangle(new Point(775,0), 25, 600), Color.GRAY, 1);
        Block u = new Block(new Rectangle(new Point(0,575), 800, 25), Color.GRAY, 1);
        Block i = new Block(new Rectangle(new Point(75,75), 175, 25), Color.blue, 1);
        Block o = new Block(new Rectangle(new Point(75,300), 175, 25), Color.green, 1);
        Block p = new Block(new Rectangle(new Point(75,500), 175, 25), Color.black, 1);
        Block a = new Block(new Rectangle(new Point(325,150), 175, 25), Color.yellow, 1);
        Block s = new Block(new Rectangle(new Point(325,250), 175, 25), Color.RED, 1);
        Block f = new Block(new Rectangle(new Point(325,350), 175, 25), Color.CYAN,1);
        Block g = new Block(new Rectangle(new Point(325,450), 175, 25), Color.ORANGE,1);
        Block h = new Block(new Rectangle(new Point(550,200), 175, 25), Color.magenta,1);
        Block j = new Block(new Rectangle(new Point(550,400), 175, 25), Color.PINK,1);
        GameEnvironment gameEnvironment = new GameEnvironment();
        gameEnvironment.addCollidable(t);
        gameEnvironment.addCollidable(w);
        gameEnvironment.addCollidable(y);
        gameEnvironment.addCollidable(u);
        gameEnvironment.addCollidable(i);
        gameEnvironment.addCollidable(o);
        gameEnvironment.addCollidable(p);
        gameEnvironment.addCollidable(a);
        gameEnvironment.addCollidable(s);
        gameEnvironment.addCollidable(f);
        gameEnvironment.addCollidable(g);
        gameEnvironment.addCollidable(h);
        gameEnvironment.addCollidable(j);
        b1.setGameEnvironment(gameEnvironment);
        Velocity v = Velocity.fromAngleAndSpeed(100, 7);
        b1.setVelocity(v);

        while (true) {


            DrawSurface d = gui.getDrawSurface();

            b1.drawOn(d);
            w.drawOn(d);
            t.drawOn(d);
            y.drawOn(d);
            u.drawOn(d);
            i.drawOn(d);
            o.drawOn(d);
            p.drawOn(d);
            a.drawOn(d);
            s.drawOn(d);
            f.drawOn(d);
            g.drawOn(d);
            h.drawOn(d);
            j.drawOn(d);
            gui.show(d);

            b1.moveOneStep();

            sleeper.sleepFor(60);   // wait for 50 milliseconds.
        }

    }
}