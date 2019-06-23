import java.util.ArrayList;

public class Album {
    private String name;
    private ArrayList<Song>songs=new ArrayList<>();
    public Album(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public void setSong(Song song)
    {
        songs.add(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
