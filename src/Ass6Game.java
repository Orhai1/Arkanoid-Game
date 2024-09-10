// 209533041 Or Haibi

import Animation.AnimationRunner;
import GameSet.GameFlow;
import Interface.LevelInformation;
import Levels.LevelOne;
import Levels.LevelThree;
import Levels.LevelTwo;
import Remover.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass6Game.
 */
public class Ass6Game {
    private static final int ZERO = 0;
    private static final int SECONDS = 60;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * The entry point of application.
     * starting the game
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //create counter that save all the game
        Counter counter = new Counter();
        //game levels
        List<LevelInformation> levels = new ArrayList<>();
        for (String str : args) {
            switch (str) {
                case "1" -> levels.add(new LevelOne());
                case "2" -> levels.add(new LevelTwo());
                case "3" -> levels.add(new LevelThree());
            }
        }
        //if there is no levels
        if (args.length == ZERO) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
        }
        //create the whole game
        GUI gui = new GUI("Arknoid", WIDTH, HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui, SECONDS);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();

        //create the game flow
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui,
                counter);
        //running the levels
        gameFlow.runLevels(levels);
        gui.close();
    }
}
