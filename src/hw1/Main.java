package hw1;

import hw1.interfaces.Pretender;
import hw1.obstacle.Fence;
import hw1.obstacle.Obstacle;
import hw1.obstacle.RunningTrack;
import hw1.thing.Cat;
import hw1.thing.Man;
import hw1.thing.Robot;

public class Main {

    public static void main(String[] args) {

        Cat cat = new Cat(1, 15);
        Man man = new Man(2, 100);
        Robot robot = new Robot(10, 1000);
        Pretender[] pretenders = {cat, man, robot};

        Obstacle[] obstacles = new Obstacle[6];
        obstacles[0] = new RunningTrack(15);
        obstacles[1] = new Fence(1);
        obstacles[2] = new RunningTrack(100);
        obstacles[3] = new Fence(2);
        obstacles[4] = new RunningTrack(1000);
        obstacles[5] = new Fence(10);

        for (Pretender p: pretenders) {
            for (Obstacle o: obstacles) {
                if (!o.pass(p)) {
                    break;
                }
            }
        }
    }
}
