package hw6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Speaker {

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

            KeyboardListener keyboardListener = new KeyboardListener();
            Thread t = new Thread(keyboardListener);
            t.setDaemon(true);
            t.start();

            DataInputStreamListener otherListener = new DataInputStreamListener(in);
            new Thread(otherListener).start();

            while(true) {
                try {
                    String msg = otherListener.getMsg();

                    if (msg != null) {
                        if (msg.equals("/end")) {
                            System.out.println("Disconnected");
                            break;
                        }
                        System.out.println("Other: " + msg);
                    }

                    msg = keyboardListener.getMsg();
                    if (msg != null) {
                        if (msg.equals("/end")) {
                            System.out.println("You quited chat");
                            out.writeUTF("/end");
                            break;
                        }
                        out.writeUTF(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
