/* name: shalomi rosh
   id: 308154418
   oop
*/
package game;

import biuoop.DrawSurface;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Velocity;
import geometry.Rectangle;

import sprite.*;

import javax.swing.*;

/**
 * Game class creates an entire game environment, including.
 * all necessary elements.
 */
public class GameLevel implements Animation {

    private int liveCount;
    private BlockRemover remover;
    private BallRemover ballRemover;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scors;
    private Counter live;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private Sound sound = null;

    public GameLevel(LevelInformation level, AnimationRunner ar, KeyboardSensor key,
                     Counter livesCounter, Counter scoreCounter) {
        this.level = level;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.remover = new BlockRemover(this, blockCounter);
        this.ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, ballCounter);
        this.scors = scoreCounter;
        this.live = livesCounter;
        this.runner = ar;
        this.keyboard = key;
        this.liveCount = live.getValue();
    }

    public Counter getBlockCounter() {
        return blockCounter;
    }

    public Counter getLive() {
        return live;
    }

    /**
     * add collidables.Collidable method adds a given.
     * collidable object to the game environment.
     * @param c - a sent collidable.
     */
    public void addCollidable(Callable c) {
        environment.addCollidable(c);
    }
    /**
     * addSprite adds a sprite to the list of sprites.
     * belonging to the Game.
     * @param s a sent sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * this method initializes a new Game.
     * it creates all necessary elements, such as keyBoard sensor.
     * gui, and the game balls.
     */
    public void initialize() {
        addSprite(this.level.getBackground());
        LevelIndicator levelIndicator = new LevelIndicator(this.level.levelName());
        block();
        border();
        levelIndicator.addToGame(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scors);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.live);
        livesIndicator.addToGame(this);
        music();
    }

    private void music() {
        if (level.levelName().equals("Minions")) {
            sound = new Sound("122.wav", 0);
            sound.playSound();
        }
    }

    /**
     * This method creates the boundaries of the screen.
     */
    public void border() {
        /**This will create the borders of the screen **/
        Block u = new Block(new Rectangle(new Point(0, 0), 800, 20),  new Color(0x484745), 1
                , true, false, false);
        Block r = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.GRAY, 1
                , true, false, false);
        Block l = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.GRAY, 1
                , true, false, false);
        Block d = new Block(new Rectangle(new Point(20, 599), 758, 15),
                this.level.colorBack(), 5, true, false, false);
        Block uu = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.GRAY, 1
                , true, false, false);
        /**This will add all the border blocks to the game.**/

        uu.addToGame(this);
        r.addToGame(this);
        l.addToGame(this);
        d.addToGame(this);
        u.addToGame(this);
        /**Add the bottom black as the death-region.**/
        d.addHitListener(ballRemover);
    }

    public Paddle paddle() {
        if (level.getPaddle()) {
            Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(this.level.paddleStart(), 570),
                    this.level.paddleWidth(), 20), this.level.paddleSpeed(), this.level.paddleWidth(), 2);
            paddle.addToGame(this);
            return paddle;
        }
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(this.level.paddleStart(), 570),
                this.level.paddleWidth(), 20), this.level.paddleSpeed(), this.level.paddleWidth());
        paddle.addToGame(this);
        return paddle;
    }

    public void ball() {
        // Creates multiple bouncing balls.
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
                Ball ball = new Ball(new Point(level.paddleStart() + level.paddleWidth() / 2, 564),
                        6, Color.WHITE, true, true, false);
                List<Velocity> vList = this.level.initialBallVelocities();
                //Velocity v = Velocity.fromAngleAndSpeed(vList.get(i).getDx(), vList.get(i).getDy());
                ball.setVelocity(vList.get(i));
                ball.addToGame(this);
                this.ballCounter.increase(1);
                ball.setGameEnvironment(environment);
        }
    }

    public void block() {
        for (int i = 0; i < this.level.blocks().size(); i++) {
            Block block = this.level.blocks().get(i);
            block.addHitListener(new ScoreTrackingListener(this.scors));
            block.addToGame(this);
            this.blockCounter.increase(1);
            block.addHitListener(remover);
        }
    }

    public void removeCollidable(Callable c) {
        this.environment.removeColliadable(c);
    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    public void playOneTurn() {
        Paddle p = paddle();
        ball();
        //if (this.live.getValue() == liveCount) {
            this.runner.run(new CountdownAnimation(3, 3, sprites));
        //}
        this.running = true;
        this.runner.setFramesPerSecond(650);
        this.runner.run(this);
        while (this.blockCounter.getValue() > 0 && this.ballCounter.getValue() > 0) {
        }
        if (this.ballCounter.getValue() == 0) {
            live.decrease(1);
        }
        if (this.blockCounter.getValue() == 0) {
            if (sound != null)
            sound.shouldStop();
        }
        p.removeFromGame(this);
    }

    public boolean shouldStop() {
        return !this.running;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if ((this.blockCounter.getValue() == 0) || (this.ballCounter.getValue() == 0)) {
            if (this.blockCounter.getValue() == 0) {
                this.scors.increase(100);
            }
            this.running = false;
        }
    }
}