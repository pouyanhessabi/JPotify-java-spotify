import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class Song {
    private String path;
    private String name;
    private String artistName;
    private Mp3File mp3file;
    private  ID3v2 id3v2Tag;
    private byte[] imageData;
    private String picType;
    public Song(String path,String name)
    {
        this.name=name;
        this.path=path;
        creatMp3File();
        setArtistName();
        songPic();
//        setName();
//        print();
    }
    public void creatMp3File() {
        {
            try {
//                System.out.println(path);
                mp3file = new Mp3File(path);
                id3v2Tag = mp3file.getId3v2Tag();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
    }

    public void songPic()
    {
        if (mp3file.hasId3v2Tag()) {
            imageData = id3v2Tag.getAlbumImage();
            if (imageData != null) {
                picType = id3v2Tag.getAlbumImageMimeType().substring(6);
//                FileOutputStream fileOutputStream = null;
//                try {
//                    fileOutputStream = new FileOutputStream("album-artwork." + picType);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    fileOutputStream.write(imageData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }

}
    public void setArtistName() {
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            artistName = id3v2Tag.getAlbumArtist();
        }
    }

    public String getArtistName() {
        return artistName;
    }

    public String getPath() {
        return path;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getPicType() {
        return picType;
    }

    public String getName() {
        return name;
    }

    public void print()
    {
        System.out.println(artistName);
    }

}
