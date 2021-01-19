package hw6;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class DataInputStreamListener implements Runnable {

    private final BlockingDeque<String> messages;
    private final DataInputStream in;
    private final Speaker owner;

    public DataInputStreamListener(DataInputStream in, Speaker owner) {
        this.in = in;
        messages = new LinkedBlockingDeque<>();
        this.owner = owner;
    }

    @Override
    public void run() {
        while(true) {
            String msg;
            try {
                msg = in.readUTF();
            } catch (IOException e) {
                break;
            }
            messages.add(msg);
            owner.resume();
        }
    }

    public String getMsg() {
        return messages.poll();
    }
}
