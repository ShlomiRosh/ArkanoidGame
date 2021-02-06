package sprite;

import biuoop.DrawSurface;
import game.BackgroundCreator;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlockExplosion implements Sprite {

    private double x, y, dx, dy, gravity;
    private Color color;
    private Image image;
    private int size;
    private Block piss;

    public BlockExplosion(Block block) {
        this.x = block.getCollisionRectangle().getUpperLeft().getX() + block.getCollisionRectangle().getWidth() / 2;
        this.y = block.getCollisionRectangle().getUpperLeft().getY() + block.getCollisionRectangle().getHeight() / 2;
        this.dx = (Math.random() * 30 - 15);
        this.dy = (Math.random() * 30 - 15);
        this.size = (int) (Math.random() * 15 + 2);
        this.image = block.getImage();
        this.color = block.getColor();
        this.gravity = 0.8;
        piss = new Block(new Rectangle(new Point(x, y), size, size), color, 2);
    }

    public void explosion() {
        x += dx;
        y += dy;
        dy += gravity;
        piss = new Block(new Rectangle(new Point(x, y), size, size), color, 2);


    }


    @Override
    public void drawOn(DrawSurface d) {
        if (this.color != null) {
            d.setColor(color);
            d.fillRectangle((int) piss.getCollisionRectangle().getUpperLeft().getX(), (int) piss.getCollisionRectangle().getUpperLeft().getY(),
                    size, size);
        }
    }

    @Override
    public void timePassed() {
        explosion();
    }

    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
