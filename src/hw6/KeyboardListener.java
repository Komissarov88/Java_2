package hw6;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class KeyboardListener implements Runnable {

    private final BlockingQueue<String> kbdMessages;
    private final Scanner scanner;
    private final Speaker owner;

    public KeyboardListener(Speaker owner) {
        scanner = new Scanner(System.in);
        kbdMessages = new LinkedBlockingQueue<>();
        this.owner = owner;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = scanner.nextLine();
                kbdMessages.add(msg);
                owner.resume();
            } catch (IllegalStateException e) {
                break;
            }
        }
    }

    public String getMsg() {
        return kbdMessages.poll();
    }
}
