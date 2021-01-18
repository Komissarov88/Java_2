package hw6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Speaker {

    private final Object monitor = new Object();

    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 9292;
    private ServerSocket host;
    private Socket other;

    public Speaker() {
        connect();
    }

    private void connect() {
        try {
            other = new Socket(IP_ADDRESS, PORT);
        } catch (IOException e) {
            startServer();
            waitClient();
        }
        loop();
        close();
    }

    private void startServer() {
        try {
            host = new ServerSocket(PORT);
            System.out.println("Server started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitClient() {
        try {
            other = host.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            other.close();
            if (host != null) {
                host.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loop() {
        try(DataInputStream in = new DataInputStream(other.getInputStream());
            DataOutputStream out = new DataOutputStream(other.getOutputStream())) {

            System.out.println("Connected");

            KeyboardListener keyboardListener = new KeyboardListener(this);
            Thread t = new Thread(keyboardListener);
            t.setDaemon(true);
            t.start();

            DataInputStreamListener otherListener = new DataInputStreamListener(in, this);
            new Thread(otherListener).start();

            try {
                mainLoop:
                while(true) {
                    String msg;

                    while ((msg = otherListener.getMsg()) != null) {
                        if (msg.equals("/end")) {
                            System.out.println("Disconnected");
                            break mainLoop;
                        }
                        System.out.println("Other: " + msg);
                    }

                    while ((msg = keyboardListener.getMsg()) != null) {
                        if (msg.equals("/end")) {
                            System.out.println("You quited chat");
                            out.writeUTF("/end");
                            break mainLoop;
                        }
                        out.writeUTF(msg);
                    }
                    pause();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pause() {
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resume() {
        synchronized (monitor) {
            monitor.notify();
        }
    }
}
