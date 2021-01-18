package hw6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Speaker {

    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 9292;
    private ServerSocket host;
    private Socket other;

    public void connect() {
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
            DataOutputStream out = new DataOutputStream(other.getOutputStream());
            Scanner self = new Scanner(System.in)) {

            System.out.println("Connected");

            KeyboardListener keyboardListener = new KeyboardListener(self);
            new Thread(keyboardListener).start();

            DataInputStreamListener otherListener = new DataInputStreamListener(in);
            new Thread(otherListener).start();

            while(true) {
                try {
                    String msg = otherListener.getMsg();

                    if (msg != null) {
                        if (msg.equals("/end")) {
                            System.out.println("Disconnected");
                            return;
                        }
                        System.out.println("Other: " + msg);
                    }

                    msg = keyboardListener.getMsg();
                    if (msg != null) {
                        if (msg.equals("/end")) {
                            System.out.println("You quited chat");
                            out.writeUTF("/end");
                            return;
                        }
                        out.writeUTF(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
