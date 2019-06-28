import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.tritonus.share.sampled.file.TAudioFileFormat;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.io.*;
public class Song implements Serializable {
    private String path;
    private String name;
    private String AlbumName;
    private String artistName;
    private transient Mp3File mp3file;
    private int timeOfSong;
    private  transient ID3v2 id3v2Tag;
    private byte[] imageData;
    private String picType;
    public Song(String path,String name)
    {
        this.name=name;
        this.path=path;
        creatMp3File();
        setArtistName();
        songPic();
        setAlbumName();
        setSongTime();
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
    public void setSongTime()  {
        File file=new File(path);
        Long microseconds;
        AudioFileFormat fileFormat = null;
        try {
            fileFormat = AudioSystem.getAudioFileFormat(file);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
            String key = "duration";
            microseconds = (Long) properties.get(key);
            timeOfSong=(int)(microseconds/1000000);
        } else {
            try {
                throw new UnsupportedAudioFileException();
            } catch (UnsupportedAudioFileException e) {
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
            }
        }

}
    public void setArtistName() {
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            artistName = id3v2Tag.getAlbumArtist();
            if(artistName==null)
                artistName="NO ARTIST NAME";
        }
    }
    public void setAlbumName()
    {
        if(mp3file.hasId3v2Tag())
        {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            AlbumName=id3v2Tag.getAlbum();
            if(AlbumName==null)
            AlbumName="NO ALBUM NAME";
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

    public String getAlbumName() {
        return AlbumName;
    }

    public int getTimeOfSong() {
        return timeOfSong;
    }

    public void print()
    {
        System.out.println(artistName);
    }

}
