package hw6;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class DataInputStreamListener implements Runnable {

    private final BlockingDeque<String> messages;
    private final DataInputStream in;

    public DataInputStreamListener(DataInputStream in) {
        this.in = in;
        messages = new LinkedBlockingDeque<>();
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
            Speaker.resume();
        }
    }

    public String getMsg() {
        return messages.poll();
    }
}
