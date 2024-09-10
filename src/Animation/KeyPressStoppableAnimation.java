// 209533041 Or Haibi
package Animation;
import Interface.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String key;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface surface) {
        if (this.keyboardSensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(surface);
    }


    @Override
    public boolean shouldStop() {
        return this.animation.shouldStop();
    }
}

