package game;

import biuoop.DrawSurface;
import geometry.Point;
import sprite.Sprite;

import java.awt.*;
/**
 * Created by Shlomi Rosh.
 */
public class BackgroundPhotos implements Sprite{

    private Image image;
    private Point Location;

    public  BackgroundPhotos (Image image, geometry.Point point) {
        this.image = image;
        this.Location = point;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int) this.Location.getX(), (int) this.Location.getY(), this.image);
    }

    @Override
    public void timePassed() {
    }

    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }


}
