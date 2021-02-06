package game;

import biuoop.DrawSurface;
import sprite.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shlomi Rosh.
 */
public class BackgroundCreator implements Sprite {

    private List<Sprite> backgroundFeatures = new ArrayList<Sprite>();

    /**
     * the constructor.
     * setting the fields.
     * @param l - the list of sprite to run
     */
    public BackgroundCreator(List l) {
        this.backgroundFeatures.addAll(l);
    }


    /**
     * Draws on all sprites wanted in background.
     * @param canvas - the drawSurface
     */
    public void drawOn(DrawSurface canvas) {
        for (int i = 0; i < backgroundFeatures.size(); i++) {
            backgroundFeatures.get(i).drawOn(canvas);
        }
    }

    /**
     * Sprite implement.
     */
    public void timePassed() {

    }
}
