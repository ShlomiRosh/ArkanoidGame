package Levels;

import game.BackgroundCreator;
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
import java.util.List;
import java.util.ArrayList;
import static java.awt.Color.BLUE;

public class SouthPark implements LevelInformation {

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

    public SouthPark() {
        this.background = DirectHitBackground();
        this.blockList = designBloks();
        this.ballVelocities = initialBallVelocities();
        this.levelName = "South Park";
        this.numberOfBlocksToRemove = blockList.size();
        this.numOfBalls = 2;
        this.paddleSpeed = 10;
        this.paddleWidth = 150;
        this.paddleStart = 325;
        this.color = BLUE;
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        int speed = 5;
        ballsVelocity.add(Velocity.fromAngleAndSpeed(40, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(-40, speed));
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

    public Sprite DirectHitBackground() {
        //list of sprites for this level
        List<Sprite> listOfSprites = new ArrayList<Sprite>();
        backgroundColor(listOfSprites);
        Image(listOfSprites);
        //creating full background image
        this.background = new BackgroundCreator(listOfSprites);
        return this.background;
    }

    private List<Block> designBloks() {
        this.blockList = new ArrayList<>();
        int xBrick = 80;
        int yBrick = 200 ;
        int height = 12, with = 14;
        for (int n = 1; n < 10; n++) {
            for (int m = 0; m <= n - 1; m++) {
                Block block = new Block(new Rectangle(new Point(xBrick - (with * (n - 1)) / 2 + m * with,
                        yBrick + height * (n - 1)), with, height), Color.GREEN, 1);
                this.blockList.add(block);
            }
        }
        int xBrick1 = 80;
        int yBrick1 = 310 ;
        int height1 = 12, with1 = 14;
        for (int n = 1; n < 10; n++) {
            for (int m = 0; m <= n - 1; m++) {
                Block block = new Block(new Rectangle(new Point(xBrick1 - (with1 * (n - 1)) / 2 + m * with1,
                        yBrick1 + height1 * (n - 1)), with1, height1), Color.GREEN, 1);
                this.blockList.add(block);
            }
        }
        for (int i = 21; i < 25; i++) {
            for (int j = 60, k = 1; j < 62; j++, k++) {
                Block block7 = new Block(new Rectangle(new Point( j + 13 * k, i * 20), 15, 25),
                        new Color(0x924900), 1);
                this.blockList.add(block7);
            }
        }
        for (int i = 36; i < 38; i++) {
            for (int j = 24, k = 1; j < 31; j++, k++) {
                Block block7 = new Block(new Rectangle(new Point( j + 13 * k, i * 14), 15, 15),
                        new Color(0x924900), 1);
                this.blockList.add(block7);
            }
        }

        return this.blockList;
    }

    private void backgroundColor(List<Sprite> listOfSprites) {
        Image image ;
        ImageIcon imageIcon = new ImageIcon("backgrounds/1111A.png");
        image = imageIcon.getImage();
        Block block = new Block(new Rectangle(new Point(0, 0), 800, 600), image, 1);
        listOfSprites.add(block);
    }

    public void Image(List<Sprite> listOfSprites) {

        Ball ball = new Ball(new Point(109, 115), 45, Color.gray, true, false, true, 1);
        Ball ball1 = new Ball(new Point(169, 171), 3, Color.WHITE, true, false, true,1);
        Ball ball2 = new Ball(new Point(101, 240), 4, Color.gray, true, false, true, 1);
        Ball ball3 = new Ball(new Point(222, 222), 3, new Color(0x82827D), true, false, true, 1);
        Ball ball4 = new Ball(new Point(320, 188), 6, new Color(0x767671), true, false, true);
        Ball ball5 = new Ball(new Point(325, 200), 6, new Color(0x767671), true, false, true);
        Ball ball6 = new Ball(new Point(295, 190), 4, new Color(0x969691), true, false, true);
        Ball ball7 = new Ball(new Point(293, 198) , 4, new Color(0x999994), true, false, true);
        Ball ball8 = new Ball(new Point(346, 176), 4, new Color(0x91918C), true, false, true);
        Ball ball9 = new Ball(new Point(351, 182), 4, new Color(0xA5A59F), true, false, true);
        listOfSprites.add(ball);
        listOfSprites.add(ball1);
        listOfSprites.add(ball2);
        listOfSprites.add(ball3);
        listOfSprites.add(ball4);
        listOfSprites.add(ball5);
        listOfSprites.add(ball6);
        listOfSprites.add(ball7);
        listOfSprites.add(ball8);
        listOfSprites.add(ball9);

        Block block7 = new Block(new Rectangle(new Point( 504, 326), 12, 9),
                Color.WHITE, 1, true, false, true, 1);
        listOfSprites.add(block7);
    }
}
