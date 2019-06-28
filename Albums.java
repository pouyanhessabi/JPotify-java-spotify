import java.io.Serializable;
import java.util.ArrayList;

public class Albums implements Serializable {
    private ArrayList<Album>albumArrayList=new ArrayList<>();
    public void setAlbum(Album a)
    {
        albumArrayList.add(a);
    }
    public void replace(Album album)
    {
        if(!album.equals(albumArrayList.get(0)))
        {
            albumArrayList.remove(album);
            albumArrayList.add(0,album);
        }
    }
    public void removeAlbum(String name)
    {
        for (int i = 0; i <albumArrayList.size() ; i++) {
            if(albumArrayList.get(i).getName().equals(name))
            {
                albumArrayList.remove(albumArrayList.get(i));
                break;
            }
        }
    }
    public ArrayList<Album> getAlbumArrayList() {
        return albumArrayList;
    }
}
