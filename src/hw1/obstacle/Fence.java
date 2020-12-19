package hw1.obstacle;

import hw1.interfaces.Jumpable;
import hw1.interfaces.Pretender;

public class Fence extends Obstacle{

    public Fence(int value) {
        super(value);
    }

    public boolean jumpOver(Jumpable object) {
        return object.jump(getValue());
    }

    @Override
    public boolean pass(Pretender pretender) {
        return jumpOver(pretender);
    }
}
