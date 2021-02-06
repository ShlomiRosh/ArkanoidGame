/* name: shalomi rosh
   id: 308154418
   oop
*/
package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sound;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * This class will create the object Block.
 * @author shlomi rosh.
 *
 */
public class Block implements Callable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private Color color = null;
    private Integer hits;
    private boolean filling = true, circleColor = true, glitter = false;
    int lithColor;
    private Image image = null;
    /**
     * The Constructor for the block.
     * @param rectangle receives the rectangle.
     * @param color receives the color for the block.
     * @param hits receives the number of hits on block.
     */
    public Block(Rectangle rectangle, Color color, Integer hits) {
        this.rectangle = rectangle;
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    public Block(Rectangle rectangle, Color color, Integer hits, boolean b, boolean b1, boolean b2) {
        this.rectangle = rectangle;
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.filling = b;
        this.circleColor = b1;
        this.glitter = b2;
    }

    public Block(Rectangle rectangle, Color color, Integer hits, boolean b, boolean b1, boolean b2, int i1) {
        this.rectangle = rectangle;
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.filling = b;
        this.circleColor = b1;
        this.glitter = b2;
        this.lithColor = i1;
    }

    public Block(Rectangle rectangle, Image image, Integer hits) {
        this.rectangle = rectangle;
        this.image = image;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }


    /**
     * @return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     *
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @return the new velocity.
     * @param collisionPoint is the point of Collision.
     * @param currentVelocity is the current Velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        sound();

        if (hits > 0) {
            hits--;
        }
        int dx, dy;
        if (checkIfHitOnSide(collisionPoint)) {
            dx = -1;
            dy = 1;
        } else {
            dx = 1;
            dy = -1;
        }
        this.notifyHit(hitter);
        return new Velocity(currentVelocity.getDx() * dx, currentVelocity.getDy() * dy);

    }

    private void sound() {
        if (hits > 1 || this.rectangle.getCollisionRectangle().getWidth() > 750
                || this.rectangle.getCollisionRectangle().getHeight() > 550
                || this.rectangle.getCollisionRectangle().getUpperLeft().getY() > 590 ) {
            if (this.rectangle.getCollisionRectangle().getUpperLeft().getY() > 590) {

            } else if(hits > 1 || this.rectangle.getCollisionRectangle().getWidth() > 750
                    || this.rectangle.getCollisionRectangle().getHeight() > 550) {
                //C:\Users\User\IdeaProjects\אני\sound\wah.wav
                new Sound("WoodWhack.wav", 0).playSound();
            }
        } else if (hits == 1) {
            
            new Sound("1.wav", 0).playSound();
        }

    }

    /**
     * see if the side of the block was hit.
     * @param collisionPoint receives the point.
     * @return either true or false.
     */
    public boolean checkIfHitOnSide(Point collisionPoint) {
        if ((Line.checkPointOnLine(getCollisionRectangle().getUpLine(), collisionPoint))
                || (Line.checkPointOnLine(getCollisionRectangle().getBottomLine(), collisionPoint))) {
            return false;
        }
        return true;
    }
    /**
     * This method will send the parameters of the block
     * to be drawn.
     * @param surface is the object received that will be drawn.
     */
    public void drawOn(DrawSurface surface) {

        if (color == null) {
            surface.drawImage((int) this.getCollisionRectangle().getUpperLeft().getX()
                    ,(int) this.getCollisionRectangle().getUpperLeft().getY(), image);
        } else {
            if (!glitter) {
                if (filling) {
                    surface.setColor(this.color);
                    surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                            (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
                    if (!circleColor) {
                        surface.setColor(color);
                        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                                (int) this.rectangle.getUpperLeft().getY(),
                                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
                    } else {
                        surface.setColor(Color.BLACK);
                        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                                (int) this.rectangle.getUpperLeft().getY(),
                                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
                    }
                } else {
                    surface.setColor(color);
                    surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                            (int) this.rectangle.getUpperLeft().getY(),
                            (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
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
                    surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                            (int) this.rectangle.getUpperLeft().getY(),
                            (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
                    if (!circleColor) {
                        surface.setColor(color);
                        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                                (int) this.rectangle.getUpperLeft().getY(),
                                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
                    } else {
                        surface.setColor(Color.BLACK);
                        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                                (int) this.rectangle.getUpperLeft().getY(),
                                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
                    }
                } else {
                    surface.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
                    surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                            (int) this.rectangle.getUpperLeft().getY(),
                            (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
                }
            }
        }
        /*
        surface.setColor(Color.WHITE);
        if (hits != 0 ) {
            surface.drawText(x, y, Integer.toString(hits), 12);
        } else {
            surface.drawText(x, y, "X", 14);
        }
        */
    }
    /**
     * The timing method.
     */
    @Override
    public void timePassed() {

    }
    /**
     * Will add the block to the List of Sprites
     * and the List of Collidables.
     * @param g is the game that the block will be sent to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    public Integer getHitPoints() {
        return hits;
    }

    public Color getColor() {
        return color;
    }

    public Image getImage() { return image; }

}
