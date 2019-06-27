import java.io.Serializable;
import java.util.ArrayList;

public class Albums implements Serializable {
    private ArrayList<Album> albumArrayList = new ArrayList<>();

    public void setAlbum(Album a) {
        albumArrayList.add(a);
    }

    public void replace(Album album) {
        if (!album.equals(albumArrayList.get(0))) {
            albumArrayList.remove(album);
            albumArrayList.add(0, album);
        }
    }

    public ArrayList<Album> getAlbumArrayList() {
        return albumArrayList;
    }
}
