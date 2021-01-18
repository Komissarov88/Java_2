package hw6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static final int PORT = 9292;

    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(PORT);
            Socket client = server.accept();
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            Scanner self = new Scanner(System.in)) {

            System.out.println("Client connected");

            KeyboardListener keyboardListener = new KeyboardListener(self);
            Thread kbdT = new Thread(keyboardListener);
            kbdT.start();

            DataInputStreamListener clientListener = new DataInputStreamListener(in);
            Thread clientT = new Thread(clientListener);
            clientT.start();

            // Listen to server
            while(true) {
                try {
                    String msg = clientListener.getMsg();

                    if (msg != null) {
                        if (msg.equals("/end")) {
                            System.out.println("Client disconnected");
                            break;
                        }
                        System.out.println("Client: " + msg);
                    }

                    msg = keyboardListener.getMsg();
                    if (msg != null) {
                        if (msg.equals("/end")) {
                            System.out.println("You stopped server");
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
