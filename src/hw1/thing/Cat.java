package hw1.thing;

import hw1.interfaces.Pretender;

public class Cat implements Pretender {

    private final int MAX_JUMP_HEIGHT;
    private final int MAX_RUN_DISTANCE;
    private static int id = 0;

    public Cat(int maxJump, int maxRun) {
        MAX_JUMP_HEIGHT = maxJump;
        MAX_RUN_DISTANCE = maxRun;
        id++;
    }

    public boolean run(int distance) {
        if (distance > MAX_RUN_DISTANCE) {
            System.out.println("Cat_" + id + " fail to run for " + distance);
            return false;
        } else {
            System.out.println("Cat_" + id + " run for " + distance);
            return true;
        }
    }

    public boolean jump(int height) {
        if (height > MAX_JUMP_HEIGHT) {
            System.out.println("Cat_" + id + " fail to jump for " + height);
            return false;
        } else {
            System.out.println("Cat_" + id + " jump for " + height);
            return true;
        }
    }
}
