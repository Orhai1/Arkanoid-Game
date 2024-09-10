// 209533041 Or Haibi
package Remover;
/**
 * Counter class.
 */
public class Counter {
    private int count;
    /**
     * Counter constructor.
     * Creates a new Counter with an initial count of 0.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * increase.
     * Increase the count by the given number.
     *
     * @param number the number to increase the count by
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decrease.
     * Decrease the count by the given number.
     *
     * @param number the number to decrease the count by
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * Get Value.
     * Get the current count of the game.
     *
     * @return the current count
     */
    public int getValue() {
        return this.count;
    }
}
