import java.io.Serializable;
import java.util.ArrayList;

public class Albums implements Serializable {
    private ArrayList<Album>albumArrayList=new ArrayList<>();
    public void setAlbum(Album a)
    {
        albumArrayList.add(a);
    }

    public ArrayList<Album> getAlbumArrayList() {
        return albumArrayList;
    }
}
