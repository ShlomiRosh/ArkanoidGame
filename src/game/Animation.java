package game;

import biuoop.DrawSurface;
/**
 * Created by Shlomi Rosh.
 */
public interface Animation {

    /**
     * Draws the objects to the surface.
     * @param d The surface to be drawn.
     */
    void doOneFrame(DrawSurface d);
    /**
     * Boolean whether to stop or not.
     * @return true or false.
     */
    boolean shouldStop();
}