package game;

import geometry.Point;
import geometry.Rectangle;
import sprite.Ball;
import sprite.Block;
import sprite.BlockExplosion;
import sprite.HitListener;

import javax.swing.*;
import java.awt.*;

// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {

            int rend = (int) (Math.random() * 10 + 5);
            for (int i = 0; i < rend; i++) {
                if (beingHit.getColor() != null) {
                    BlockExplosion blockExplosion = new BlockExplosion(new Block(new Rectangle
                            (new Point(beingHit.getCollisionRectangle().getUpperLeft().getX(),
                                    beingHit.getCollisionRectangle().getUpperLeft().getY()),
                                    beingHit.getCollisionRectangle().getWidth(), beingHit.getCollisionRectangle().getHeight())
                            , beingHit.getColor(), 1));
                    blockExplosion.addToGame(game);
                    blockExplosion.timePassed();
                }
            }

            if (beingHit.getColor() == null) {
                Image image;
                ImageIcon imageIcon = new ImageIcon("backgrounds/output_5FfNen.gif");
                image = imageIcon.getImage();
                    BackgroundPhotos photos = new BackgroundPhotos(image, new Point(beingHit.getCollisionRectangle().getUpperLeft().getX()
                            , beingHit.getCollisionRectangle().getUpperLeft().getY()));
                    photos.addToGame(game);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                photos.removeFromGame(game);
                                // your code here
                            }
                        },
                        400
                );
            }

            beingHit.removeFromGame(game);
            remainingBlocks.decrease(1);
        }
    }
}