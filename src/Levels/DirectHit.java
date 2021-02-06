package Levels;

import game.BackgroundCreator;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprite.Ball;
import sprite.Block;
import sprite.LevelInformation;
import sprite.Sprite;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import static java.awt.Color.BLACK;
import static java.awt.Color.CYAN;

public class DirectHit implements LevelInformation {

    private Color color;
    private String levelName;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private int numOfBalls;
    private int paddleStart;
    private List<Block> blockList;
    private List<Velocity> ballVelocities = new ArrayList<Velocity>();
    private Sprite background;

    public DirectHit() {

        this.background = DirectHitBackground();
        this.blockList = designBloks();
        this.ballVelocities = initialBallVelocities();
        this.levelName = "Direct Hit";
        this.numberOfBlocksToRemove = blockList.size();
        this.numOfBalls = 1;
        this.paddleSpeed = 15;
        this.paddleWidth = 100;
        this.paddleStart = 350;
        this.color = BLACK;
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = new Velocity(0, 5);
        ballVelocities.add(v);
        return this.ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public int paddleStart() {
        return this.paddleStart;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    @Override
    public Color colorBack() {
        return this.color;
    }

    @Override
    public boolean getPaddle() {
        return false;
    }

    private List<Block> designBloks() {
        this.blockList = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(385, 185), 30, 30), Color.red, 1);
        this.blockList.add(block);
        return this.blockList;
    }

    public Sprite DirectHitBackground() {
        //list of sprites for this level
        List<Sprite> listOfSprites = new ArrayList<Sprite>();
        backgroundColor(listOfSprites);
        targetImage(listOfSprites);
        //creating full background image
        this.background = new BackgroundCreator(listOfSprites);
        return this.background;
    }

    private void backgroundColor(List<Sprite> listOfSprites) {
        Block block = new Block(new Rectangle(new Point(0, 0), 800, 600), Color.BLACK, 1);
        listOfSprites.add(block);

    }

    public void targetImage(List<Sprite> listOfSprites) {
        Line line = new Line(373, 200, 260, 200, Color.BLUE, false);
        Line line1 = new Line(427, 200, 540, 200, Color.BLUE, false);
        Line line3 = new Line(400, 227, 400, 340, Color.BLUE, false);
        Line line2 = new Line(400, 173, 400, 60, Color.BLUE, false);
        listOfSprites.add(line);
        listOfSprites.add(line1);
        listOfSprites.add(line2);
        listOfSprites.add(line3);
        int radius = 50;
        for (int i = 0; i < 4; i++) {
            Ball ball = new Ball(new Point(400, 200), radius, Color.BLUE, false, true, true);
            listOfSprites.add(ball);
            radius += 20;
        }
    }
}




