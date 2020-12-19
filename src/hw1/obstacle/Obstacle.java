package hw1.obstacle;

import hw1.interfaces.Pretender;

public abstract class Obstacle {

    private final int value;

    public Obstacle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public abstract boolean pass(Pretender pretender);
}
