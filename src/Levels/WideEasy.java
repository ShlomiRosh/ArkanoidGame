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
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class WideEasy implements LevelInformation {

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

    public WideEasy() {
        this.background = DirectHitBackground();
        this.blockList = designBloks();
        this.ballVelocities = initialBallVelocities();
        this.levelName = "Wide Easy";
        this.numberOfBlocksToRemove = blockList.size();
        this.numOfBalls = 10;
        this.paddleSpeed = 2;
        this.paddleWidth = 600;
        this.paddleStart = 100;
        this.color = new Color(0xBF8428);
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        List<Velocity> ballsVelocity = new ArrayList<>();
        int speed = 8;
        ballsVelocity.add(Velocity.fromAngleAndSpeed(50, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(40, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(30, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(20, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(10, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(-10, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(-20, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(-30, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(-40, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(-50, speed));
        return ballsVelocity;
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
        for (int i = 1; i < 16; i++) {
            java.awt.Color color = null;
            if (i < 3) {
                color = new Color(0xE30803);
            } else if (i >= 3 && i < 5) {
                color = new Color(0xFF8C06);
            } else if (i >= 4 && i < 7) {
                color = new Color(0xFFF200);
            } else if (i >= 6 && i < 10) {
                color = new Color(0x5FFF00);
            } else if (i >= 9 && i < 12) {
                color = new Color(0x0018FF);
            } else if (i >= 11 && i < 14) {
                color = new Color(0xFF18FE);
            } else {
                color = new Color(0x00FFE2);
            }
            if (i == 1) {
                Block block = new Block(new Rectangle(new Point(i * 20, 250), 47, 20), color, 1);
                this.blockList.add(block);
            } else {
                Block block = new Block(new Rectangle(new Point((i - 1) * 51 + 16, 250), 51, 20),
                        color, 1);
                this.blockList.add(block);
            }
        }
        return this.blockList;
    }

    public Sprite DirectHitBackground() {
        //list of sprites for this level
        List<Sprite> listOfSprites = new ArrayList<Sprite>();
        backgroundColor(listOfSprites);
        sunImage(listOfSprites);
        //creating full background image
        this.background = new BackgroundCreator(listOfSprites);
        return this.background;
    }

    private void backgroundColor(List<Sprite> listOfSprites) {
        Image image ;
        ImageIcon imageIcon = new ImageIcon("backgrounds/1.jpg");
        image = imageIcon.getImage();
        Block block = new Block(new Rectangle(new Point(0, 0), 800, 600), image, 1);
        listOfSprites.add(block);
    }

    public void sunImage(List<Sprite> listOfSprites) {
        Random rand = new Random();
        double x = 100;
        double y = 200;
        for (int i = 0; i < 500; i++) {
            Line line = new Line(100, 100, x, y, Color.ORANGE, true);
            x = rand.nextInt(800) + 100;
            y = rand.nextInt(170) + 100;
            listOfSprites.add(line);
        }

        int radius = 55;
        for (int i = 0; i < 3; i++) {
            java.awt.Color color = null;
            if (i == 2) {
                color = new Color(0xFFF11E);
            } else if (i == 1) {
                color = new Color(0xFFD30F);
            } else {
                color = new Color(0xE1C38A);
            }
            Ball ball = new Ball(new Point(100, 100), radius, color, true, false, false);
            listOfSprites.add(ball);
            radius -= 8;
        }
    }
}
