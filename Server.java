import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private String currentlyPlaying = "";

    public Server() {
        new Thread(new ServerThread()).start();
    }

    public String getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(String currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    class ServerThread implements Runnable {

        @Override
        public void run() {
            Socket socket = null;
            ServerSocket server = null;
            try {
                server = new ServerSocket(1111);
                while (true) {
                    socket = server.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    setCurrentlyPlaying(in.readUTF());
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}