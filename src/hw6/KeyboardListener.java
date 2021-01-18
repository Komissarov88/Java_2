package hw6;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class KeyboardListener implements Runnable {

    private final BlockingQueue<String> kbdMessages;
    private final Scanner scanner;

    public KeyboardListener() {
        scanner = new Scanner(System.in);
        kbdMessages = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = scanner.nextLine();
                kbdMessages.add(msg);
                Speaker.resume();
            } catch (IllegalStateException e) {
                break;
            }
        }
    }

    public String getMsg() {
        return kbdMessages.poll();
    }
}
