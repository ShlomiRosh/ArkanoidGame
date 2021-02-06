package Levels;

import game.BackgroundCreator;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprite.*;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import static java.awt.Color.GREEN;

public class Green3 implements LevelInformation {

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

    public Green3() {
        this.background = DirectHitBackground();
        this.blockList = designBloks();
        this.ballVelocities = initialBallVelocities();
        this.levelName = "Green 3";
        this.numberOfBlocksToRemove = blockList.size();
        this.numOfBalls = 10;
        this.paddleSpeed = 20;
        this.paddleWidth = 300;
        this.paddleStart = 350;
        this.color = GREEN;
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
        Random r = new Random();
        int k = 1;
        for (int i = 4; i < 10; i++) {
            Color c = new Color(r.nextInt(0xffffff));
            int hits = 1;
            for (int j = i; j < 19; j++) {
                if (k > 0) { hits = 2; }
                Block block = new Block(new Rectangle(new Point(j * 41, i * 25), 41, 25), c, hits
                        , true, true, false);
                this.blockList.add(block);
            }
            k--;
        }
        return this.blockList;
    }

    public Sprite DirectHitBackground() {
        //list of sprites for this level
        List<Sprite> listOfSprites = new ArrayList<Sprite>();
        backgroundColor(listOfSprites);
        Images(listOfSprites);
        //creating full background image
        this.background = new BackgroundCreator(listOfSprites);
        return this.background;
    }

    private void backgroundColor(List<Sprite> listOfSprites) {
        Image image ;
        ImageIcon imageIcon = new ImageIcon("backgrounds/3.jpg");
        image = imageIcon.getImage();
        Block block = new Block(new Rectangle(new Point(0, 0), 800, 600), image, 1);
        listOfSprites.add(block);
    }

    public void Images(List<Sprite> listOfSprites) {
        Block block = new Block(new Rectangle(new Point(70,430), 112, 200), new Color(0x33352F),
                1, true, false, false);
        listOfSprites.add(block);
        for (int i = 15; i < 20; i++) {
            for (int j = 60, k = 1; j < 65; j++, k++) {
                Block block7 = new Block(new Rectangle(new Point( j + 19 * k, i * 30), 15, 25),
                        Color.WHITE, 1, true, false, true, 1);
                listOfSprites.add(block7);
            }
        }

        Block block2 = new Block(new Rectangle(new Point(115, 380), 20, 50), new Color(0x484745)
                , 1, true, false, false);
        listOfSprites.add(block2);
        Block block3 = new Block(new Rectangle(new Point(122, 200), 5, 180), new Color(0x484745)
                , 1, true, false, false);
        listOfSprites.add(block3);
        int radius = 15;
        for (int i = 0; i < 3; i++) {
            java.awt.Color color = null;
            boolean boll = false;
            if (i == 2) {
                boll = true;
                color = new Color(0xFFFDF5);
            } else if (i == 1) {
                color = new Color(0xFF130A);
            } else {
                color = new Color(0xE1C38A);
            }

            Ball ball = new Ball(new Point(124, 198), radius, color, true, false, boll);
            listOfSprites.add(ball);
            radius -= 4;
        }
    }
}
