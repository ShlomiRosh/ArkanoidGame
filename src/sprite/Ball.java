package sprite;

import biuoop.DrawSurface;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;

import java.awt.*;
import java.lang.Math;
import java.util.Random;

/**
 * A program that creates a ball with all its various properties.
 * @author Shlomi Rosh
 */
public class Ball implements Sprite {
    private Point point;
    private Color color;
    private Velocity v;
    private int radius;
    private GameEnvironment gameEnvironment;
    private boolean filling = true, circleColor = true, glitter = false;
    int lithColor;
    /**
     * The constructor that holds the ball features center point radius and color.
     * @param center Center ball
     * @param r The radius of the ball
     * @param color The color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.radius = r;
        this.color = color;
    }

    public Ball(Point point, int radius, java.awt.Color color, boolean b, boolean b1, boolean b2) {
        this.point = point;
        this.radius = radius;
        this.color = color;
        this.filling = b;
        this.circleColor = b1;
        this.glitter = b2;
    }

    public Ball(Point point, int radius, Color color, boolean b, boolean b1, boolean b2, int i1) {
        this.point = point;
        this.radius = radius;
        this.color = color;
        this.filling = b;
        this.circleColor = b1;
        this.glitter = b2;
        this.lithColor = i1;
    }

    /**
     * accessor.
     * @return double
     */
    public int getX() {
        return (int) point.getX();
    }
    /**
     * accessor.
     * @return int
     */
    public int getY() {
        return (int) point.getY();
    }
    /**
     * accessor.
     * @return int
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * accessor.
     * @return color
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * draw the ball on the given DrawSurface.
     * @param surface Ball surface
     */
    public void drawOn(DrawSurface surface) {
        if (!glitter) {
            if (filling) {
                if (!circleColor) {
                    surface.setColor(color);
                    surface.drawCircle(getX(), getY(), radius);
                } else {
                    surface.setColor(Color.BLACK);
                    surface.drawCircle(getX(), getY(), radius);
                }
                surface.setColor(color);
                surface.fillCircle(getX(), getY(), radius);

            } else {
                surface.setColor(color);
                surface.drawCircle(getX(), getY(), radius);
            }
        } else {
            Random rand = new Random();
            if (filling) {
                if (lithColor == 1) {
                    Color[] colors = new Color[] { Color.white,new Color(0xFFF2C3),new Color(0xF7FFB4)
                            ,new Color(0xFAFFC4),new Color(0xDFFFB8)};
                    Color randomColor = colors[(int)( Math.random() * 5)];
                    surface.setColor(randomColor);
                } else {
                    surface.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
                }
                if (!circleColor) {
                    surface.drawCircle(getX(), getY(), radius);
                } else {
                    surface.setColor(Color.BLACK);
                    surface.drawCircle(getX(), getY(), radius);
                }
                surface.fillCircle(getX(), getY(), radius);
            } else {
                surface.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
                surface.drawCircle(getX(), getY(), radius);
            }
        }
    }

    /**
     * The timing method.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Defines velocity.
     * @param velocity Variable type velocity
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }
    /**
     * Defines velocity by point.
     * @param dx X point element
     * @param dy The Y element of the point
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * accessor.
     * @return velocty
     */
    public Velocity getVelocity() {
        return this.v;
    }
    /**
     * setting method.
     * setting the ball's environment.
     * @param g - the given environment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }
    /**
     * access method.
     * access to the environment field.
     * @return - the environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }
    /**
     * calculating tha ball's trajectory.
     * his current location and his next location.
     * @return - the ball's course.
     */
    public Line trajectory() {
        Point end = new Point(this.point.getX() + this.getVelocity().getDx(),
                this.point.getY() + this.getVelocity().getDy());
        return new Line(this.point, end);
    }
    /**
     * the function that move's the ball on the screen.
     * according to the ball's surface and velocity.
     */
    public void moveOneStep() {

        Line trajectory = this.trajectory();
        CollisionInfo collision = this.getGameEnvironment().getClosestCollision(trajectory);
        if (collision != null) {
                v = collision.collisionObject().hit(this, collision.collisionPoint(), this.getVelocity());
            if ((Math.abs(this.point.distance(collision.collisionPoint()) - this.getSize()) < 0.000001)
                    || (this.point.distance(collision.collisionPoint()) < this.getSize())) {
                this.setVelocity(v.getDx(), v.getDy());
            }
        } else {
            this.point = this.getVelocity().applyToPoint(this.point);
        }
    }
    /**
     * adding the ball to the sprite list.
     * @param g - the given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}