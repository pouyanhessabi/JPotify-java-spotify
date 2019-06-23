import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class MusicPlayer {
    FileInputStream fileInputStream;
    private long pausePosition;
    private long total;
    Player player;
    public synchronized void play(String name) throws FileNotFoundException {
        new Thread(() -> {
            try {
                try {
                    fileInputStream = new FileInputStream(name);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    player = new Player(fileInputStream);
                    try {
                        total=fileInputStream.available();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();

    }
    public void pause()
    {
        try {
            pausePosition=fileInputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("a");
        player.close();
        System.out.println("b");
    }
    public void stop()
    {
        player.close();
    }
    public void resume(String name) throws FileNotFoundException {
        new Thread(){
            public void run() {
                try {
                    try {
                        fileInputStream = new FileInputStream(name);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        player = new Player(fileInputStream);
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                    try {
                        fileInputStream.skip(total-pausePosition);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        } .start();
    }
}
