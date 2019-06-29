import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        UserFrame userFrame = new UserFrame();
        while (true) {
            if (userFrame.isTmpCheck())
                break;
        }
        MainFrame frame = new MainFrame();
        frame.setUserName(userFrame.getText());
        int userAlive=0;
        UserNames userNamesFromFile = null;
        String nameOfLibrary,nameOfPlaylists,nameOfAlbum;
        nameOfLibrary=userFrame.getText()+"library.txt";
        nameOfAlbum=userFrame.getText()+"album.txt";
        nameOfPlaylists=userFrame.getText()+"playlists.txt";

        File file0=new File("UeserNames.txt");
        if(file0.exists())
        {
            ObjectInputStream objectInputStream =
                    null;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream("UeserNames.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                try {
                    userNamesFromFile = (UserNames) objectInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(userNamesFromFile!=null)
        {
            System.out.println("size of   "+userNamesFromFile.getUserNameArraylist().size());
            int i;
            for (i = 0; i <userNamesFromFile.getUserNameArraylist().size() ; i++) {
                frame.userNames.setUserNameArraylist(userNamesFromFile.getUserNameArraylist().get(i));
                if(userNamesFromFile.getUserNameArraylist().get(i).equals(userFrame.getText()))
                {
                    userAlive=1;
                }
            }
            if(userAlive==0)
                frame.userNames.setUserNameArraylist(userFrame.getText());

        }
        else{
            frame.userNames.setUserNameArraylist(userFrame.getText());
        }
        for (int i = 0; i <frame.userNames.getUserNameArraylist().size() ; i++) {
            System.out.println(frame.userNames.getUserNameArraylist().get(i)+"       "+i);

        }
        Album library = null;
        File file=new File(nameOfLibrary);
        if(file.exists()&&(file.length()>122))
        {
            ObjectInputStream objectInputStream =
                    null;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(nameOfLibrary));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                try {
                    library = (Album) objectInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(library!=null)
        {
            for (int i = 0; i <library.getSongs().size() ; i++) {
                frame.library.setSong(library.getSongs().get(i));
                System.out.println("from file    "+frame.library.getSongs().size());
            }
        }
        Albums albums = null;
        File file1=new File(nameOfAlbum);
        System.out.println(file1.length());
        if(file1.exists()&&(file1.length()>122))
        {
            ObjectInputStream objectInputStream =
                    null;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(nameOfAlbum));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                try {
                    albums = (Albums) objectInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(albums!=null)
        {
            for (int i = 0; i <albums.getAlbumArrayList().size() ; i++) {
                frame.albumArrayList.setAlbum(albums.getAlbumArrayList().get(i));
                System.out.println("from file    "+frame.albumArrayList.getAlbumArrayList().size());
            }
        }
        Albums albums1 = null;
        File file2=new File(nameOfPlaylists);
        System.out.println(file2.length());
        if(file2.exists()&&(file2.length()>122))
        {
            ObjectInputStream objectInputStream =
                    null;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(nameOfPlaylists));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                try {
                    albums1 = (Albums) objectInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int favoriteIsAlive=0,sharedPlaylistIsAlive=0;
        if(albums1!=null)
        {
            for (int i = 0; i <albums1.getAlbumArrayList().size() ; i++) {
                frame.playLists.setAlbum(albums1.getAlbumArrayList().get(i));
                if(albums1.getAlbumArrayList().get(i).getName().equals("Favorites"))
                {
                    frame.leftPanel.getFavoriteSongs().addActionListener(frame.actionListenerForPlayList);
                    favoriteIsAlive=1;

                }
                else if(albums1.getAlbumArrayList().get(i).getName().equals("SharedPlaylist"))
                {
                    frame.leftPanel.getSharedPlaylist().addActionListener(frame.actionListenerForPlayList);
                    sharedPlaylistIsAlive=1;
                }
                else{
                    frame.leftPanel.getPlaylists().clear();
                    frame.leftPanel.addLableOfplaylist(frame.playLists.getAlbumArrayList().get(i).getName());
                    frame.leftPanel.getPlaylists().get(frame.leftPanel.getPlaylists().size() - 1).addActionListener(frame.actionListenerForPlayList);

                }
                frame.leftPanel.setVisible(false);
                frame.leftPanel.setVisible(true);
            }
        }
        if(favoriteIsAlive==0)
        {
            frame.creatFavorite();
        }
        if(sharedPlaylistIsAlive==0)
        {

            frame.creatSharedPlayList();
        }
        String myUsername=frame.getUserName();
        Friend friend= new Friend(myUsername,"127.0.0.1",5000,"127.0.0.1",4000);
        while (true) {
            if (frame.isClicked() == true ){
                try {
                    friend.sendData(frame.getSongInfo());
                    frame.setClicked(false);
                } catch (IOException e) {
//                    e.printStackTrace();
                }
            }

        }

    }
}
