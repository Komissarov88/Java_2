package hw6;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DataInputStreamListener implements Runnable {

    private final BlockingQueue<String> messages;
    private final DataInputStream in;

    public DataInputStreamListener(DataInputStream in) {
        this.in = in;
        messages = new LinkedBlockingQueue<>();
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
        }
    }

    public String getMsg() {
        return messages.poll();
    }
}
