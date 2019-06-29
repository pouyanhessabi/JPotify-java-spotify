import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * The Responder class mange playing mp3 file
 * @author omid mahyar and pouyan hesabi *
 * @version    1.0  (1398/04/02)
 */
public class MusicPlayer {

    FileInputStream fileInputStream;
    private long pausePosition;
    private long total;
    Player player;
    /**
     * playing a song
     * @param path a string from song who represent path of mp3 file
     */
    public synchronized void play(String path) throws FileNotFoundException {
        new Thread(() -> {
            try {

                try {
                    fileInputStream = new FileInputStream(path);
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
    /**
     * pause a song and change pausePosition
     */
    public void pause()
    {
        try {
            pausePosition=fileInputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.close();
    }
    /**
     * stop a song
     */
    public void stop()
    {
        player.close();
    }
    /**
     * resume a song
     * @param path a string from song who represent path of mp3 file
     */
    public void resume(String path) throws FileNotFoundException {
        new Thread(){
            public void run() {
                try {
                    try {
                        fileInputStream = new FileInputStream(path);
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
    public void resumeForMovingbar(double percent,String path)
    {
        new Thread(){
            public void run() {
                try {
                    try {
                        fileInputStream = new FileInputStream(path);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        player = new Player(fileInputStream);
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                    try {
                        long movingBarposition=(long)(total*percent);
                        fileInputStream.skip(movingBarposition);
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
