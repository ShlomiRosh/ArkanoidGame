package test;

import Levels.*;
import biuoop.GUI;
import game.AnimationRunner;
import game.GameFlow;
import sprite.LevelInformation;
import java.util.ArrayList;
import java.util.List;

public class Ass5Game {

    public static void main(String[] args) {
        GUI gui = new GUI("arkoind", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor());
        List<LevelInformation> newLevel = new ArrayList<>();
        if (args.length == 0) {
            LevelInformation levelInformation1 = new DirectHit();
            newLevel.add(levelInformation1);
            LevelInformation levelInformation2 = new WideEasy();
            newLevel.add(levelInformation2);
            LevelInformation levelInformation3 = new Green3();
            newLevel.add(levelInformation3);
            LevelInformation levelInformation4 = new FinalFour();
            newLevel.add(levelInformation4);
            LevelInformation levelInformation5 = new FinalFour();
            newLevel.add(levelInformation5);
            gameFlow.runLevels(newLevel);
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    LevelInformation levelInformation1 = new DirectHit();
                    newLevel.add(levelInformation1);
                } else if (args[i].equals("2")) {
                    LevelInformation levelInformation2 = new WideEasy();
                    newLevel.add(levelInformation2);
                } else if (args[i].equals("3")) {
                    LevelInformation levelInformation3 = new Green3();
                    newLevel.add(levelInformation3);
                } else if (args[i].equals("4")) {
                    LevelInformation levelInformation4 = new FinalFour();
                    newLevel.add(levelInformation4);
                } else if (args[i].equals("5")) {
                    LevelInformation levelInformation5 = new SouthPark();
                    newLevel.add(levelInformation5);
                } else {
                    continue;
                }
            }
            gameFlow.runLevels(newLevel);
        }
    }

}

