package hw6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 9292;

    public static void main(String[] args) {
        try(Socket server = new Socket(IP_ADDRESS, PORT);
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            Scanner self = new Scanner(System.in)) {

            System.out.println("Connected to server");

            KeyboardListener keyboardListener = new KeyboardListener(self);
            Thread kbdT = new Thread(keyboardListener);
            kbdT.start();

            DataInputStreamListener serverListener = new DataInputStreamListener(in);
            Thread serverT = new Thread(serverListener);
            serverT.start();

            // Listen to server
            while(true) {
                try {
                    String msg = serverListener.getMsg();

                    if (msg != null) {
                        if (msg.equals("/end")) {
                            System.out.println("Server disconnected you");
                            break;
                        }
                        System.out.println("Server: " + msg);
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
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
