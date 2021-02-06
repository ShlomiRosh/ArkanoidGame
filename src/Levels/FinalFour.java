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
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import static java.awt.Color.BLUE;

public class FinalFour implements LevelInformation {

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

    public FinalFour() {
        this.background = DirectHitBackground();
        this.blockList = designBloks();
        this.ballVelocities = initialBallVelocities();
        this.levelName = "Final Four";
        this.numberOfBlocksToRemove = blockList.size();
        this.numOfBalls = 3;
        this.paddleSpeed = 15;
        this.paddleWidth = 100;
        this.paddleStart = 350;
        this.color = BLUE;
    }

    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        int speed = 5;
        ballsVelocity.add(Velocity.fromAngleAndSpeed(40, speed));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(0.0D, speed));
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
        Random r = new Random();
        int k = 1;
        for (int i = 4; i < 10; i++) {
            Color c = new Color(r.nextInt(0xffffff));
            int hits = 1;
            for (int j = 1; j < 16; j++) {
                if (k > 0) { hits = 2; }
                if (j == 1) {
                    Block block = new Block(new Rectangle(new Point(j * 20, i * 25), 45, 25),
                            c, hits);
                    this.blockList.add(block);
                } else {
                    Block block = new Block(new Rectangle(new Point((j - 1) * 51 + 14, i * 25), 51,
                            25), c, hits);
                    this.blockList.add(block);
                }
            }
            k--;
        }
        return this.blockList;
    }

    private void backgroundColor(List<Sprite> listOfSprites) {
        Image image ;
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\אני\\רקעים\\4.jpg");
        image = imageIcon.getImage();
        Block block = new Block(new Rectangle(new Point(0, 0), 800, 600), image, 1);
        listOfSprites.add(block);
    }

    public void Image(List<Sprite> listOfSprites) {

        int x1 = 80, y1 = 350, x2 = 79, y2 = 360;
        for (int k = 0; k < 12; k++) {
            for (int i = 0; i < 25; i++) {
                Line line = new Line(x1, y1 , x2, y2, Color.WHITE,true);
                listOfSprites.add(line);
                x1 = x2; y1 = y2 + 4; x2 -= 1; y2 += 10;
            }
            x1 += 32; y1 = 350; x2 += 32; y2 = 360;
        }
        int x11 = 510, y11 = 400, x22 = 509, y22 = 410;
        for (int k = 0; k < 12; k++) {
            for (int i = 0; i < 25; i++) {
                Line line = new Line(x11, y11 , x22, y22, Color.WHITE,true);
                listOfSprites.add(line);
                x11 = x22; y11 = y22 + 4; x22 -= 1; y22 += 10;
            }
            x11 += 32; y11 = 400; x22 += 32; y22 = 410;
        }
        Ball ball = new Ball(new Point(150, 340), 30, Color.gray, true, false, false);
        Ball ball1 = new Ball(new Point(100, 350), 30, Color.gray, true, false, false);
        Ball ball2 = new Ball(new Point(120, 360), 30, Color.gray, true, false, false);
        Ball ball3 = new Ball(new Point(90, 390), 30, new Color(0x82827D), true, false, false);
        Ball ball4 = new Ball(new Point(80, 380), 30, new Color(0x767671), true, false, false);
        Ball ball5 = new Ball(new Point(550, 400), 30, new Color(0x767671), true, false, false);
        Ball ball6 = new Ball(new Point(570, 410), 30, new Color(0x969691), true, false, false);
        Ball ball7 = new Ball(new Point(590, 390) , 30, new Color(0x999994), true, false, false);
        Ball ball8 = new Ball(new Point(520, 380), 30, new Color(0x91918C), true, false, false);
        Ball ball9 = new Ball(new Point(500, 390), 30, new Color(0xA5A59F), true, false, false);
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
    }
}
