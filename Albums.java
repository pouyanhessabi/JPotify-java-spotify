import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Responder class keeping creating Album for better serialize
 *
 * @author omid mahyar and pouyan hesabi *
 * @version 1.0  (1398/04/05)
 */
public class Albums implements Serializable {
    private ArrayList<Album> albumArrayList = new ArrayList<>();

    public void setAlbum(Album a) {
        albumArrayList.add(a);
    }

    /**
     * replacing a album in albums
     *
     * @param album a Album for replacing when album cliced
     */
    public void replace(Album album) {
        if (!album.equals(albumArrayList.get(0))) {
            albumArrayList.remove(album);
            albumArrayList.add(0, album);
        }
    }

    /**
     * removing a album from albums
     *
     * @param name a string  for founding album
     */
    public void removeAlbum(String name) {
        for (int i = 0; i < albumArrayList.size(); i++) {
            if (albumArrayList.get(i).getName().equals(name)) {
                albumArrayList.remove(albumArrayList.get(i));
                break;
            }
        }
    }

    public ArrayList<Album> getAlbumArrayList() {
        return albumArrayList;
    }
}
