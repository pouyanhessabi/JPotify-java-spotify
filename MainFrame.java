import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private String userName;
    private boolean usernameExist;
    UserNames userNames=new UserNames();
    MusicPlayer musicPlayer = new MusicPlayer();
    private RightPanel rightPanel = new RightPanel();
    public SouthPanel southPanel = new SouthPanel();
    LeftPanel leftPanel = new LeftPanel();
    private NorthPanel northPanel=new NorthPanel();
    JLabel jLabel;
    private int counter, clickAlbums=0;
    Song song;
    JButton addSongToPlayList,removePLaylist,renamePLaylist,removeSongFromPlayList;
    int movingBarSize=0, i6 = 0;
    private String pathSong;
    private int counter1 = 0;
    String playlistName;
    Album library = new Album("library");
    Album favorite = new Album("Favorites");
    Album sharedPlaylist = new Album("SharedPlaylist");
    Albums albumArrayList=new Albums();
    Albums playLists=new Albums();
    ActionListenerForSongButtonInCenterPanel actionListenerForButtonInCenterPanel1 = new ActionListenerForSongButtonInCenterPanel(1);
    ActionListenerForSongButtonInCenterPanel actionListenerForButtonInCenterPanel2 = new ActionListenerForSongButtonInCenterPanel(2);
    ActionListenerForAlbumButtonIncenterpanel actionListenerForAlbumButtonIncenterpanel = new ActionListenerForAlbumButtonIncenterpanel();
    ActionListenerForPlayList actionListenerForPlayList = new ActionListenerForPlayList();
    private CenterPanel centerPanel = new CenterPanel();
    ActionListenerForNextAndPrevious actionListenerForNextAndPrevious1;
    ActionListenerForNextAndPrevious actionListenerForNextAndPrevious2;
    Album recentlyAlbum;
    public MainFrame() {
        counter = 1;
        counter = 0;
        this.setTitle("Binarify");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 700);
        this.add(southPanel, BorderLayout.PAGE_END);
        this.add(rightPanel, BorderLayout.EAST);
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.add(northPanel,BorderLayout.NORTH);
        this.setVisible(true);
        northPanel.searchButton.addActionListener(new ActionListenerForSearch());
        MyListener myListener = new MyListener();
        leftPanel.getAddToLibraryIcon().addActionListener(myListener);
        ActionListenerForSongsButten actionListenerForSongsButten = new ActionListenerForSongsButten(1);
        leftPanel.getSongs().addActionListener(actionListenerForSongsButten);
        southPanel.getPlayIcon().addActionListener(new ActionListenerForPlay());
        southPanel.getStopIcon().addActionListener(new ActionListenerForStopButten());
        leftPanel.getAlbums().addActionListener(new ActionListenerForAlbumButton());
        leftPanel.getAddPlaylistIcon().addActionListener(new ActionListenerForAddPlaylistButten());
        leftPanel.getHome().addActionListener(new ActionListenerForHome());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FileOutputStream fileOutputStream= null;
                try {
                    fileOutputStream = new FileOutputStream(userName+"library.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ObjectOutputStream objectOutputStream= null;
                try {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    objectOutputStream.writeObject(library);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    objectOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                FileOutputStream fileOutputStream1= null;
                try {
                    fileOutputStream1 = new FileOutputStream(userName+"album.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ObjectOutputStream objectOutputStream1= null;
                try {
                    objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    objectOutputStream1.writeObject(albumArrayList);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    objectOutputStream1.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                FileOutputStream fileOutputStream2= null;
                try {
                    fileOutputStream2 = new FileOutputStream(userName+"playlists.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ObjectOutputStream objectOutputStream2= null;
                try {
                    objectOutputStream2 = new ObjectOutputStream(fileOutputStream2);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    objectOutputStream2.writeObject(playLists);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    objectOutputStream2.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                FileOutputStream fileOutputStream3= null;
                try {
                    fileOutputStream3 = new FileOutputStream("UeserNames.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ObjectOutputStream objectOutputStream3= null;
                try {
                    objectOutputStream3 = new ObjectOutputStream(fileOutputStream3);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    objectOutputStream3.writeObject(userNames);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    objectOutputStream3.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
        JScrollPane scrollPane=new JScrollPane(leftPanel);
        scrollPane.setPreferredSize(new Dimension(200,700));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane,BorderLayout.WEST);
        this.pack();
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
        northPanel.addNameToNorthPanel(userName);
    }
    public void aadUserNameToNorthPanel()
    {

    }
    public void setUsernameExist(boolean usernameExist) {
        this.usernameExist = usernameExist;
    }
    public void creatFavorite() {
        playLists.setAlbum(favorite);
        leftPanel.getFavoriteSongs().addActionListener(new ActionListenerForPlayList());
    }
    public void creatSharedPlayList() {
        playLists.setAlbum(sharedPlaylist);
        leftPanel.getSharedPlaylist().addActionListener(new ActionListenerForPlayList());
    }
    private class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            String name = "", path = "";
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                path = selectedFile.getAbsolutePath();
                name = selectedFile.getName();
                song = new Song(path, name);
                int noAlbum = 1;
                if (albumArrayList.getAlbumArrayList().size() != 0) {
                    for (int j = 0; j < albumArrayList.getAlbumArrayList().size(); j++) {
                        if (albumArrayList.getAlbumArrayList().get(j).getName().equals(song.getAlbumName())) {
                            albumArrayList.getAlbumArrayList().get(j).setSong(song);
                            noAlbum = 0;
                        }
                    }
                    if (noAlbum == 1) {
                        Album album = new Album(song.getAlbumName());
                        album.setSong(song);
                        albumArrayList.getAlbumArrayList().add(album);
                    }
                } else {
                    Album album = new Album(song.getAlbumName());
                    album.setSong(song);
                    albumArrayList.getAlbumArrayList().add(album);
                }
                library.setSong(song);
            }
            System.out.println(albumArrayList.getAlbumArrayList().size());
//            for (int j = 0; j <albumArrayList.getAlbumArrayList().size() ; j++) {
//                for (int k = 0; k <albumArrayList.getAlbumArrayList().get(j).getSongs().size(); k++) {
//                    System.out.println(albumArrayList.getAlbumArrayList().get(j).getName()+"  "+albumArrayList.getAlbumArrayList().get(j).getSongs().get(k).getName());
//                }
//            }
        }
    }
    private class ActionListenerForSongsButten implements ActionListener {
        int status;

        public ActionListenerForSongsButten(int status) {
            this.status = status;
        }

        public void actionPerformed(ActionEvent event) {
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.jLabel.setText(" ");
            centerPanel.jButtonsForSong.clear();
            if(pathSong!=null)
            {
                for (int j = 0; j <library.getSongs().size() ; j++) {
                    if(library.getSongs().get(j).getPath().equals(pathSong))
                    {
                        System.out.println(library.getSongs().get(j).getPath()+"   "+pathSong);
                        library.replace(library.getSongs().get(j));
                        break;
                    }
                }
            }
            for (int i1 = 0; i1 < library.getSongs().size(); i1++) {
                centerPanel.creatButten(library.getSongs().get(i1));
                if (status == 1)
                    centerPanel.jButtonsForSong.get(i1).addActionListener(actionListenerForButtonInCenterPanel1);
                else if (status == 2)
                    centerPanel.jButtonsForSong.get(i1).addActionListener(actionListenerForButtonInCenterPanel2);

            }
            for (int i2 = 0; i2 < library.getSongs().size(); i2++) {
                centerPanel.addButton(i2);
            }
            centerPanel.setVisible(true);
            southPanel.getNextIcon().removeActionListener(actionListenerForNextAndPrevious1);
            actionListenerForNextAndPrevious1=new ActionListenerForNextAndPrevious(1, "library",1);
            southPanel.getNextIcon().addActionListener(actionListenerForNextAndPrevious1);
            southPanel.getPreviousIcon().removeActionListener(actionListenerForNextAndPrevious2);
            actionListenerForNextAndPrevious2=new ActionListenerForNextAndPrevious(1, "library",-1);
            southPanel.getPreviousIcon().addActionListener(actionListenerForNextAndPrevious2);
        }
    }
    private class ActionListenerForPlay implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (counter == 0) {
                try {
                    musicPlayer.play(pathSong);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                southPanel.getPlayIcon().setIcon(new ImageIcon("icons/pause.png"));

            } else if (counter % 2 == 0) {
                southPanel.getPlayIcon().setIcon(new ImageIcon("icons/pause.png"));
                try {
                    musicPlayer.resume(pathSong);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
//
            else {
                southPanel.getPlayIcon().setIcon(new ImageIcon("icons/play.png"));
                musicPlayer.pause();
            }
            counter++;
        }
    }
    private class ActionListenerForAlbumButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(recentlyAlbum!=null)
            {
                for (int k = 0; k <albumArrayList.getAlbumArrayList().size() ; k++) {
                    if(albumArrayList.getAlbumArrayList().get(k).equals(recentlyAlbum))
                    {
                        System.out.println(albumArrayList.getAlbumArrayList().get(k).getName()+"   "+recentlyAlbum.getName());
                        albumArrayList.replace(albumArrayList.getAlbumArrayList().get(k));
                        break;
                    }
                }
            }
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.jButtonsForAlbum.clear();
            centerPanel.jLabel.setText(" ");
            for (int i4=0; i4 < albumArrayList.getAlbumArrayList().size(); i4++) {
                centerPanel.creatAlbumButten(albumArrayList.getAlbumArrayList().get(i4));
                centerPanel.jButtonsForAlbum.get(i4).addActionListener(actionListenerForAlbumButtonIncenterpanel);
            }
            for (int i5 = 0; i5 < albumArrayList.getAlbumArrayList().size(); i5++) {
                centerPanel.addAlbumButton(i5);
            }
            centerPanel.setVisible(true);
        }
    }
    private class ActionListenerForAlbumButtonIncenterpanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            System.out.println(e.getActionCommand());
            String albumName;
            clickAlbums++;
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.jLabel.setText(" ");
            albumName=e.getActionCommand();
            southPanel.getNextIcon().removeActionListener(actionListenerForNextAndPrevious1);
            actionListenerForNextAndPrevious1=new ActionListenerForNextAndPrevious(2, albumName,1);
            southPanel.getNextIcon().addActionListener(actionListenerForNextAndPrevious1);
            southPanel.getPreviousIcon().removeActionListener(actionListenerForNextAndPrevious2);
            actionListenerForNextAndPrevious2=new ActionListenerForNextAndPrevious(2, albumName,-1);
            southPanel.getPreviousIcon().addActionListener(actionListenerForNextAndPrevious2);
            int j;
            for ( j = 0; j < albumArrayList.getAlbumArrayList().size(); j++) {
                if (albumArrayList.getAlbumArrayList().get(j).getName().equals(e.getActionCommand())) {
                    recentlyAlbum=albumArrayList.getAlbumArrayList().get(j);
                    centerPanel.jButtonsSongForAlbum.clear();
                    for (i6 = 0; i6 < albumArrayList.getAlbumArrayList().get(j).getSongs().size(); i6++) {
                        centerPanel.creatSongButtenForAlbume(albumArrayList.getAlbumArrayList().get(j).getSongs().get(i6));
                        centerPanel.jButtonsSongForAlbum.get(i6).addActionListener(actionListenerForButtonInCenterPanel1);
                    }
                    for (int i2 = 0; i2 < albumArrayList.getAlbumArrayList().get(j).getSongs().size(); i2++) {
                        centerPanel.addSongButtonForAlbum(i2);
                    }
                    centerPanel.setVisible(true);
                    break;
                }
            }
        }
    }
    private class ActionListenerForSongButtonInCenterPanel implements ActionListener {
        int status;
        public ActionListenerForSongButtonInCenterPanel(int status) {
            this.status = status;
        }
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            if (status == 1) {
                if (counter1 != 0) {
                    counter = 0;
                    musicPlayer.player.close();
                }
                int j = 0;
                for (; j < library.getSongs().size(); j++) {
                    if (library.getSongs().get(j).getName().equals(e.getActionCommand())) {
                        System.out.println(e.getActionCommand() + "  " + library.getSongs().get(j).getName() + "  " + j + "   " + library.getSongs().size());
                        pathSong = library.getSongs().get(j).getPath();
                        movingBarSize=library.getSongs().get(j).getTimeOfSong();
                        southPanel.removeMovingBar();
                        //southPanel.addMovingBar(movingBarSize);
                        southPanel.removeSongNAme();
                        southPanel.addSongNameAndImage(library.getSongs().get(j));
                        southPanel.getPlayIcon().setIcon(new ImageIcon("icons/pause.png"));
                        counter = 1;
                        break;
                    }
                }
                try {
                    musicPlayer.play(library.getSongs().get(j).getPath());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
//                southPanel.moveMovingBar(movingBarSize);
                counter1 = 1;
            } else if (status == 2) {
                for (int k = 0; k < playLists.getAlbumArrayList().size(); k++) {
                    if (playLists.getAlbumArrayList().get(k).getName().equals(playlistName)) {
                        int j = 0;
                        for (; j < library.getSongs().size(); j++) {
                            if (library.getSongs().get(j).getName().equals(e.getActionCommand())) {
                                playLists.getAlbumArrayList().get(k).setSong(library.getSongs().get(j));
                            }
                        }
                        break;
                    }
                }
                centerPanel.removeAll();
                centerPanel.setVisible(false);
                centerPanel.setVisible(true);
            }
                else if (status == 3) {
                for (int k = 0; k < playLists.getAlbumArrayList().size(); k++) {
                    if (playLists.getAlbumArrayList().get(k).getName().equals(playlistName)) {
                        int j = 0;
                        for (; j < library.getSongs().size(); j++) {
                            if (library.getSongs().get(j).getName().equals(e.getActionCommand())) {
                                playLists.getAlbumArrayList().get(k).removeSong(library.getSongs().get(j));
                            }
                        }
                        break;
                    }
                }
                centerPanel.removeAll();
                centerPanel.setVisible(false);
                centerPanel.setVisible(true);
            }
            }
        }
    private class ActionListenerForStopButten implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            southPanel.getPlayIcon().setIcon(new ImageIcon("icons/play.png"));
            musicPlayer.stop();
            counter = 0;
        }
    }
    private class ActionListenerForAddPlaylistButtenInCenter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = centerPanel.playlistName.getText();
            centerPanel.playlistName.setText("");
            leftPanel.setVisible(false);
            leftPanel.addLableOfplaylist(name);
            leftPanel.getPlaylists().get(leftPanel.getPlaylists().size() - 1).addActionListener(actionListenerForPlayList);
            Album album = new Album(name);
            playLists.getAlbumArrayList().add(album);
            leftPanel.setVisible(true);
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);

        }
    }
    private class ActionListenerForPlayList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll();
            playlistName = e.getActionCommand();
            centerPanel.jButtonsForSong.clear();
            for (int j = 0; j < playLists.getAlbumArrayList().size(); j++) {
                if (playLists.getAlbumArrayList().get(j).getName().equals(playlistName)) {
                    int k = 0;
                    for (; k < playLists.getAlbumArrayList().get(j).getSongs().size(); k++) {
                        centerPanel.creatButten(playLists.getAlbumArrayList().get(j).getSongs().get(k));
                            centerPanel.jButtonsForSong.get(k).addActionListener(actionListenerForButtonInCenterPanel1);
                    }
                    for (int l = 0; l < k; l++) {
                        centerPanel.addButton(l);
                    }
                }
            }
            southPanel.getNextIcon().removeActionListener(actionListenerForNextAndPrevious1);
            actionListenerForNextAndPrevious1=new ActionListenerForNextAndPrevious(3, playlistName,1);
            southPanel.getNextIcon().addActionListener(actionListenerForNextAndPrevious1);
            southPanel.getPreviousIcon().removeActionListener(actionListenerForNextAndPrevious2);
            actionListenerForNextAndPrevious2=new ActionListenerForNextAndPrevious(3, playlistName,-1);
            southPanel.getPreviousIcon().addActionListener(actionListenerForNextAndPrevious2);
            addSongToPlayList = new JButton("+");
            centerPanel.add(addSongToPlayList);
            removeSongFromPlayList = new JButton("-");
            removeSongFromPlayList.addActionListener(new ActionListenerForRemoveFromPlaylistButton());
            JLabel lableplaylistName = new JLabel(e.getActionCommand());
            if((!playlistName.equals(playLists.getAlbumArrayList().get(0).getName()))&&(!playlistName.equals(playLists.getAlbumArrayList().get(1).getName()))) {
                removePLaylist = new JButton("remove");
                renamePLaylist = new JButton("rename");
                centerPanel.add(removePLaylist, FlowLayout.LEFT);
                centerPanel.add(renamePLaylist, FlowLayout.LEFT);
                removePLaylist.addActionListener(new ActionListenerForRemovePlaylistButtenInCenter());
                renamePLaylist.addActionListener(new ActionListenerForRenamePlaylistButtenInCenter1());
            }
            centerPanel.add(lableplaylistName,FlowLayout.LEFT);
            centerPanel.add(removeSongFromPlayList);
            addSongToPlayList.addActionListener(new ActionListenerForSongsButten(2));
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);
        }
    }
    private class ActionListenerForRemoveFromPlaylistButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll();
            centerPanel.jButtonsForSong.clear();
            for (int j = 0; j < playLists.getAlbumArrayList().size(); j++) {
                if (playLists.getAlbumArrayList().get(j).getName().equals(playlistName)) {
                    int k = 0;
                    for (; k < playLists.getAlbumArrayList().get(j).getSongs().size(); k++) {
                        centerPanel.creatButten(playLists.getAlbumArrayList().get(j).getSongs().get(k));
                        centerPanel.jButtonsForSong.get(k).addActionListener(new ActionListenerForSongButtonInCenterPanel(3));
                    }
                    for (int l = 0; l < k; l++) {
                        centerPanel.addButton(l);
                    }
                }
            }
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);
        }
    }
    private class ActionListenerForAddPlaylistButten implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            JButton addToPlayList = new JButton("add playlist");
            addToPlayList.addActionListener(new ActionListenerForAddPlaylistButtenInCenter());
            centerPanel.add(addToPlayList);
            centerPanel.textfildForNameOfPlayList();
            centerPanel.setVisible(true);

        }
    }
    private class ActionListenerForRemovePlaylistButtenInCenter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            leftPanel.setVisible(false);
            playLists.removeAlbum(playlistName);
            for (int j = 0; j <leftPanel.getPlaylists().size() ; j++) {
                leftPanel.remove(leftPanel.getPlaylists().get(j));
            }
            leftPanel.clearPlaylist();
            for (int j = 2; j <playLists.getAlbumArrayList().size() ; j++) {
                leftPanel.addLableOfplaylist(playLists.getAlbumArrayList().get(j).getName());
            }
            for (int i = 0; i <leftPanel.getPlaylists().size() ; i++) {
                leftPanel.getPlaylists().get(i).addActionListener(actionListenerForPlayList);
            }
            leftPanel.setVisible(true);
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);

        }
    }
    private class ActionListenerForRenamePlaylistButtenInCenter1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll();
            JLabel jLabel=new JLabel("enter yor new name");
            centerPanel.add(jLabel,FlowLayout.LEFT);
            centerPanel.textfildForNewNameOfPlayList();
//            JTextField newNameField=new JTextField();
//            newNameField.setPreferredSize(new Dimension(200,40));
//            centerPanel.add(newNameField);
            JButton rename=new JButton("rename playlist");
            centerPanel.add(rename);
            rename.addActionListener(new ActionListenerForRenamePlaylistButtenInCenter2());
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);

        }
    }
    private class ActionListenerForRenamePlaylistButtenInCenter2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = centerPanel.playlistNewName.getText();
            centerPanel.playlistNewName.setText("");
            leftPanel.setVisible(false);
            for ( int i1 = 0; i1 <playLists.getAlbumArrayList().size() ; i1++) {
                if(playLists.getAlbumArrayList().get(i1).getName().equals(playlistName))
                {
                    playLists.getAlbumArrayList().get(i1).setNewName(name);
                    break;
                }
            }
            for (int j = 0; j <leftPanel.getPlaylists().size() ; j++) {
                leftPanel.remove(leftPanel.getPlaylists().get(j));
            }
            System.out.println(leftPanel.getPlaylists().size());
            leftPanel.clearPlaylist();
            System.out.println(playLists.getAlbumArrayList().size());
            for (int j = 2; j <playLists.getAlbumArrayList().size() ; j++) {
                    leftPanel.addLableOfplaylist(playLists.getAlbumArrayList().get(j).getName());
            }
            for (int i = 0; i <leftPanel.getPlaylists().size() ; i++) {
                leftPanel.getPlaylists().get(i).addActionListener(actionListenerForPlayList);
            }
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);
            leftPanel.setVisible(true);
//            leftPanel.addLableOfplaylist(name);
//            leftPanel.getPlaylists().get(leftPanel.getPlaylists().size() - 1).addActionListener(actionListenerForPlayList);
//            Album album = new Album(name);
//            playLists.getAlbumArrayList().add(album);
//            leftPanel.setVisible(true);
//            centerPanel.removeAll();
//            centerPanel.setVisible(false);
//            centerPanel.setVisible(true);
        }
    }
    private class ActionListenerForNextAndPrevious implements ActionListener {
        int status;
        String albumName;
        int previousOrNext;
        public ActionListenerForNextAndPrevious(int status, String albumName,int previousOrNext) {
            this.status = status;
            this.albumName = albumName;
            this.previousOrNext=previousOrNext;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(previousOrNext);
            if (status == 1) {
                int j = 0,size = 0;
                if(previousOrNext==1) {
                    j=0;
                    size=library.getSongs().size()-1;
                }
                else if(previousOrNext==-1)
                {
                    j=1;
                    size=library.getSongs().size();
                }
                for (; j <size  ; j++) {
                    if (library.getSongs().get(j).getPath().equals(pathSong))
                    {
                        musicPlayer.stop();
                        pathSong = library.getSongs().get(j + previousOrNext).getPath();
                        southPanel.removeSongNAme();
                        southPanel.addSongNameAndImage(library.getSongs().get(j+ previousOrNext));
                        try {
                            musicPlayer.play(pathSong);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    }
                }
            } else if (status == 2) {
                for (int j = 0; j < albumArrayList.getAlbumArrayList().size(); j++) {
                    if (albumArrayList.getAlbumArrayList().get(j).getName().equals(albumName)) {
                        int i=0 ,size = 0;
                        if(previousOrNext==1) {
                            i=0;
                            size=albumArrayList.getAlbumArrayList().get(j).getSongs().size() - 1;

                        }
                        else if(previousOrNext==-1)
                        {
                            i=1;
                            size=albumArrayList.getAlbumArrayList().get(j).getSongs().size();
                        }
                        for (; i < size; i++) {
                            if (albumArrayList.getAlbumArrayList().get(j).getSongs().get(i).getPath().equals(pathSong))
                            {
                                musicPlayer.stop();
                                southPanel.removeSongNAme();
                                southPanel.addSongNameAndImage(albumArrayList.getAlbumArrayList().get(j).getSongs().get(i+previousOrNext));
                                pathSong = albumArrayList.getAlbumArrayList().get(j).getSongs().get(i+previousOrNext).getPath();

                                try {
                                    musicPlayer.play(pathSong);
                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                break;
                            }

                        }
                        break;
                    }
                }
            }
            else if (status == 3) {
                for (int j = 0; j < playLists.getAlbumArrayList().size(); j++) {
                    if (playLists.getAlbumArrayList().get(j).getName().equals(albumName)) {
                        int i = 0,size = 0;
                        if(previousOrNext==1) {
                            i=0;
                            size=playLists.getAlbumArrayList().get(j).getSongs().size() - 1;
                        }
                        else if(previousOrNext==-1)
                        {
                            i=1;
                            size=playLists.getAlbumArrayList().get(j).getSongs().size() ;


                        }
                        for (; i <size ; i++) {
                            if (playLists.getAlbumArrayList().get(j).getSongs().get(i).getPath().equals(pathSong))
                            {
                                musicPlayer.stop();
                                southPanel.removeSongNAme();
                                southPanel.addSongNameAndImage(playLists.getAlbumArrayList().get(j).getSongs().get(i+previousOrNext));
                                pathSong = playLists.getAlbumArrayList().get(j).getSongs().get(i + previousOrNext).getPath();
                                try {
                                    musicPlayer.play(pathSong);
                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                break;
                            }

                        }
                    }
                }
            }
        }
    }
    private class ActionListenerForHome implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.add(new JLabel(new ImageIcon("icons/hd.png")));
            centerPanel.setVisible(true);
        }
    }
    private class ActionListenerForSearch implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText=northPanel.searchArea.getText();
            String searchText1=searchText+".mp3";
            int isAlive=0;
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.jButtonsForSong.clear();
            int j=0;
            for (int i = 0; i <library.getSongs().size() ; i++) {
                System.out.println(searchText+"   "+library.getSongs().get(i).getAlbumName());
                if((searchText1.equals(library.getSongs().get(i).getName()))||(searchText.equals(library.getSongs().get(i).getArtistName()))||(searchText.equals(library.getSongs().get(i).getAlbumName())))
                {
                    centerPanel.creatButten(library.getSongs().get(i));
                    centerPanel.addButton(j);
                    isAlive=1;
                    j++;
                }
            }
            for (int i = 0; i <j ; i++) {
                centerPanel.jButtonsForSong.get(i).addActionListener(new ActionListenerForSongButtonInCenterPanel(1));
            }
            if(isAlive==0)
            {
                JLabel jLabel=new JLabel(" No result");
                centerPanel.add(jLabel);
            }
            northPanel.searchArea.setText("");
            centerPanel.setVisible(true);

        }
    }

}
