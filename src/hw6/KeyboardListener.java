package hw6;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class KeyboardListener implements Runnable {

    private final BlockingQueue<String> kbdMessages;
    Scanner kbd;

    public KeyboardListener(Scanner scanner) {
        kbd = scanner;
        kbdMessages = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = kbd.nextLine();
                kbdMessages.add(msg);
            } catch (IllegalStateException e) {
                break;
            }
        }
    }

    public String getMsg() {
        return kbdMessages.poll();
    }
}
