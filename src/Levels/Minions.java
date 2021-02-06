package Levels;

import game.BackgroundCreator;
import geometry.Velocity;
import sprite.Block;
import sprite.LevelInformation;
import sprite.Sprite;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.GREEN;

public class Minions implements LevelInformation {

    private Color color;
    private String levelName;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private int numOfBalls;
    private int paddleStart;
    private java.util.List<Block> blockList;
    private java.util.List<Velocity> ballVelocities = new ArrayList<Velocity>();
    private Sprite background;
    private boolean icon;

    public Minions() {
        this.background = DirectHitBackground();
        this.blockList = designBloks();
        this.ballVelocities = initialBallVelocities();
        this.levelName = "Minions";
        this.numberOfBlocksToRemove = blockList.size();
        this.numOfBalls = 2;
        this.paddleSpeed = 20;
        this.paddleWidth = 150;
        this.paddleStart = 300;
        this.color = GREEN;
        this.icon = true;
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {
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
    public java.util.List<Block> blocks() {
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
        return this.icon;
    }

    private java.util.List<Block> designBloks() {
        this.blockList = new ArrayList<>();
        for (int i = 2; i < 11; i++) {
            Image image;
            ImageIcon imageIcon = new ImageIcon("backgrounds/blue-rectangle-th-1-png-hi.png");
            image = imageIcon.getImage();
            Block block = new Block(new geometry.Rectangle(new geometry.Point(i * 62, 100), 60, 47),
                    image, 1);
            this.blockList.add(block);
        }
        for (int i = 2; i < 11; i++) {
            Image image;
            ImageIcon imageIcon = new ImageIcon("backgrounds/orange-button,-square-png-image-2869.png");
            image = imageIcon.getImage();
            Block block = new Block(new geometry.Rectangle(new geometry.Point(i * 62, 150), 60, 60),
                    image, 1);
            this.blockList.add(block);
        }
        for (int i = 2; i < 11; i++) {
            Image image;
            ImageIcon imageIcon = new ImageIcon("backgrounds/a947bd4c2806.png");
            image = imageIcon.getImage();
            Block block = new Block(new geometry.Rectangle(new geometry.Point(i * 62, 212), 60, 60),
                    image, 1);
            this.blockList.add(block);
        }
        for (int i = 2; i < 11; i++) {
            Image image;
            ImageIcon imageIcon = new ImageIcon("backgrounds/button-23968_1280.png");
            image = imageIcon.getImage();
            Block block = new Block(new geometry.Rectangle(new geometry.Point(i * 62, 274), 60, 30),
                    image, 2);
            this.blockList.add(block);
        }
        return this.blockList;
    }

    public Sprite DirectHitBackground() {
        //list of sprites for this level
        java.util.List<Sprite> listOfSprites = new ArrayList<Sprite>();
        backgroundColor(listOfSprites);
        //creating full background image
        this.background = new BackgroundCreator(listOfSprites);
        return this.background;
    }

    private void backgroundColor(java.util.List<Sprite> listOfSprites) {

        Image imagee;
        ImageIcon imageIconn = new ImageIcon("backgrounds/giphy9.gif");
        imagee = imageIconn.getImage();
        Block block1 = new Block(new geometry.Rectangle(new geometry.Point(0, 0), 800, 600), imagee, 1);
        listOfSprites.add(block1);

        Image image;
        ImageIcon imageIcon = new ImageIcon("backgrounds/minun.gif");
        image = imageIcon.getImage();
        Block block = new Block(new geometry.Rectangle(new geometry.Point(0, 400), 800, 600), image, 1);
        listOfSprites.add(block);

        Image image1;
        ImageIcon imageIcon1 = new ImageIcon("backgrounds/output_A5FqVX.gif");
        image1 = imageIcon1.getImage();
        Block block2 = new Block(new geometry.Rectangle(new geometry.Point(600, 400), 800, 600), image1, 1);
        listOfSprites.add(block2);

        Image image2;
        ImageIcon imageIcon2 = new ImageIcon("backgrounds/giphy.gif");
        image2 = imageIcon2.getImage();
        Block block3 = new Block(new geometry.Rectangle(new geometry.Point(100, 0), 800, 600), image2, 1);
        listOfSprites.add(block3);
    }
}

