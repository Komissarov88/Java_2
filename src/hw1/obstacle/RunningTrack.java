package hw1.obstacle;

import hw1.interfaces.Pretender;
import hw1.interfaces.Runnable;

public class RunningTrack extends Obstacle {

    public RunningTrack(int value) {
        super(value);
    }

    public boolean runOn(Runnable object) {
        return object.run(getValue());
    }

    @Override
    public boolean pass(Pretender pretender) {
        return runOn(pretender);
    }
}