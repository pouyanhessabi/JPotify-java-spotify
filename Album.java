import java.io.Serializable;
import java.util.ArrayList;
/**
 * The Responder class keeping creating song
 * @author omid mahyar and pouyan hesabi *
 * @version    1.0  (1398/04/01)
 */
public class Album implements Serializable {
    private String name;
    private ArrayList<Song>songs=new ArrayList<>();
    public Album(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
    /**
     * Addingn a song to album
     * @param song a Song for adding too album
     */
    public void setSong(Song song)
    {
        songs.add(song);
    }
    /**
     * Removing a song to album
     * @param song a Song for removing from album
     */
    public void removeSong(Song song)
    {
        songs.remove(song);
    }
    public void setNewName(String s)
    {
        name=s;
    }
    /**
     * replacing a song in album
     * @param song a Song for replacing in album
     */
    public void replace(Song song)
    {
        if(!song.equals(songs.get(0)))
        {
                songs.remove(song);
                songs.add(0,song);
        }
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }

}
