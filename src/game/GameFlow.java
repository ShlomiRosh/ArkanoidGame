package game;

import Levels.*;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import sprite.LevelInformation;

import java.util.ArrayList;
import java.util.List;

public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor key;
    private Counter livesCounter = new Counter();
    private Counter scoreCounter = new Counter();
    /**
     * Controls the Flow of the game.
     *  @param ar animation Runner.
     * @param ks the keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        key = ks;
        livesCounter.increase(7);
    }

    /**
     * Method that will run through the levels.
     *
     * @param levels current level.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, animationRunner, key, this.livesCounter, this.scoreCounter);
            level.initialize();
            while ((level.getBlockCounter().getValue() > 0) && (level.getLive().getValue() > 0)) {
                level.playOneTurn();
            }
        }
        animationRunner.run(new EndScreen(scoreCounter, livesCounter, key, animationRunner));
    }

    public static void main(String[] args) {
        GUI gui = new GUI("arkoind", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor());
        List<LevelInformation> newLevel = new ArrayList<>();

        LevelInformation levelInformation3 = new Green3();
        newLevel.add(levelInformation3);

        LevelInformation levelInformation4 = new FinalFour();
        newLevel.add(levelInformation4);

        LevelInformation levelInformation5 = new SouthPark();
        newLevel.add(levelInformation5);

        LevelInformation levelInformation1 = new DirectHit();
        newLevel.add(levelInformation1);

        LevelInformation levelInformation2 = new WideEasy();
        newLevel.add(levelInformation2);

        LevelInformation levelInformation11 = new Minions();
        newLevel.add(levelInformation11);








        gameFlow.runLevels(newLevel);
    }
}
