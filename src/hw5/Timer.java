package hw5;

public class Timer {

    private long startTime;
    private long deltaTime;

    public Timer() {
        startTime = 0;
        deltaTime = 0;
    }

    public void start() {
        deltaTime = 0;
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        deltaTime = System.currentTimeMillis() - startTime;
    }

    @Override
    public String toString() {
        return "duration: " + deltaTime + "ms";
    }
}
