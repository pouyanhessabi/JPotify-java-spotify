import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * The Responder class  manage main frame
 *
 * @author omid mahyar and pouyan hesabi *
 * @version 1.0  (1398/04/04)
 */
public class MainFrame extends JFrame {
    private String userName;
    private boolean usernameExist;
    UserNames userNames = new UserNames();
    MusicPlayer musicPlayer = new MusicPlayer();
    private RightPanel rightPanel = new RightPanel();
    public SouthPanel southPanel = new SouthPanel();
    LeftPanel leftPanel = new LeftPanel();
    private NorthPanel northPanel = new NorthPanel();
    private int counter, clickAlbums = 0;
    Song song;
    JButton addSongToPlayList, removePLaylist, renamePLaylist, removeSongFromPlayList;
    int movingBarSize = 0;
    private String pathSong;
    private int counter1 = 0;
    String playlistName;
    Album library = new Album("library");
    Album favorite = new Album("Favorites");
    Album sharedPlaylist = new Album("SharedPlaylist");
    Albums albumArrayList = new Albums();
    Albums playLists = new Albums();
    ActionListenerForSongButtonInCenterPanel actionListenerForButtonInCenterPanel1 = new ActionListenerForSongButtonInCenterPanel(1);
    ActionListenerForSongButtonInCenterPanel actionListenerForButtonInCenterPanel2 = new ActionListenerForSongButtonInCenterPanel(2);
    ActionListenerForAlbumButtonIncenterpanel actionListenerForAlbumButtonIncenterpanel = new ActionListenerForAlbumButtonIncenterpanel();
    ActionListenerForPlayList actionListenerForPlayList = new ActionListenerForPlayList();
    private CenterPanel centerPanel = new CenterPanel();
    ActionListenerForNextAndPrevious actionListenerForNextAndPrevious1;
    ActionListenerForNextAndPrevious actionListenerForNextAndPrevious2;
    Album recentlyAlbum;
    private volatile String songInfo = null;
    private volatile boolean clicked = false;

    public MainFrame() {
        counter = 1;
        counter = 0;
        this.setTitle("Binarify");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 1300);
        this.add(southPanel, BorderLayout.PAGE_END);
        this.setIconImage(new ImageIcon("icons/spotifyIcon.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        this.add(northPanel, BorderLayout.NORTH);
        this.setVisible(true);
        northPanel.searchButton.addActionListener(new ActionListenerForSearch());
        AcrionlistenerForAddTolibrary AcrionlistenerForAddTolibrary = new AcrionlistenerForAddTolibrary();
        leftPanel.getAddToLibraryIcon().addActionListener(AcrionlistenerForAddTolibrary);
        ActionListenerForSongsButten actionListenerForSongsButten = new ActionListenerForSongsButten(1);
        leftPanel.getSongs().addActionListener(actionListenerForSongsButten);
        southPanel.getPlayIcon().addActionListener(new ActionListenerForPlay());
        southPanel.getStopIcon().addActionListener(new ActionListenerForStopButten());
        leftPanel.getAlbums().addActionListener(new ActionListenerForAlbumButton());
        leftPanel.getAddPlaylistIcon().addActionListener(new ActionListenerForAddPlaylistButten());
        leftPanel.getHome().addActionListener(new ActionListenerForHome());
        southPanel.getAddTofavorite().addActionListener(new ActionListenerForFavoriteButten());
        rightPanel.getRefreshButton().addActionListener(new ActionListenerForRefreshingRight());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(userName + "library.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ObjectOutputStream objectOutputStream = null;
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
                FileOutputStream fileOutputStream1 = null;
                try {
                    fileOutputStream1 = new FileOutputStream(userName + "album.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ObjectOutputStream objectOutputStream1 = null;
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
                FileOutputStream fileOutputStream2 = null;
                try {
                    fileOutputStream2 = new FileOutputStream(userName + "playlists.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ObjectOutputStream objectOutputStream2 = null;
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
                FileOutputStream fileOutputStream3 = null;
                try {
                    fileOutputStream3 = new FileOutputStream("UeserNames.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ObjectOutputStream objectOutputStream3 = null;
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
                e.getWindow().dispose();
            }
        });
        JScrollPane leftScrollPane = new JScrollPane(leftPanel);
        leftScrollPane.setPreferredSize(new Dimension(200, 700));
        leftScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(leftScrollPane, BorderLayout.WEST);
        JScrollPane rightScrollPane = new JScrollPane(rightPanel);
        rightScrollPane.setPreferredSize(new Dimension(200, 700));
        rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(rightScrollPane, BorderLayout.EAST);
        JScrollPane centerScrollPane = new JScrollPane(centerPanel);
        centerScrollPane.setPreferredSize(new Dimension(750, 3000));
        centerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        centerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(centerScrollPane, BorderLayout.CENTER);
        this.pack();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        northPanel.addNameToNorthPanel(userName);
    }

    public void setUsernameExist(boolean usernameExist) {
        this.usernameExist = usernameExist;
    }

    public synchronized String getSongInfo() {
        return songInfo;
    }

    public synchronized void setSongInfo(String songInfo) {
        this.songInfo = songInfo;
    }

    public synchronized boolean isClicked() {
        return clicked;
    }

    public synchronized void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    /**
     * creating favarote play list
     */
    public void creatFavorite() {
        playLists.setAlbum(favorite);
        leftPanel.getFavoriteSongs().addActionListener(new ActionListenerForPlayList());
    }

    /**
     * creating shard play list
     */
    public void creatSharedPlayList() {
        playLists.setAlbum(sharedPlaylist);
        leftPanel.getSharedPlaylist().addActionListener(new ActionListenerForPlayList());
    }

    /**
     * The Responder class  action listtenr for add to library button
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/04)
     */
    private class AcrionlistenerForAddTolibrary implements ActionListener {
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
        }
    }

    /**
     * The Responder class  actionListener for songs Butten in left panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/04)
     */
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
            if (pathSong != null) {
                for (int j = 0; j < library.getSongs().size(); j++) {
                    if (library.getSongs().get(j).getPath().equals(pathSong)) {
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
            actionListenerForNextAndPrevious1 = new ActionListenerForNextAndPrevious(1, "library", 1);
            southPanel.getNextIcon().addActionListener(actionListenerForNextAndPrevious1);
            southPanel.getPreviousIcon().removeActionListener(actionListenerForNextAndPrevious2);
            actionListenerForNextAndPrevious2 = new ActionListenerForNextAndPrevious(1, "library", -1);
            southPanel.getPreviousIcon().addActionListener(actionListenerForNextAndPrevious2);
        }
    }

    /**
     * The Responder class  actionListener for play butten in sotht panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/05)
     */
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
                    southPanel.resumeMovingBar();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            } else {
                southPanel.getPlayIcon().setIcon(new ImageIcon("icons/play.png"));
                southPanel.pauseMovingBar();
                musicPlayer.pause();
            }
            counter++;
        }
    }

    /**
     * The Responder class  actionListener for album Butten in left panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/04)
     */
    private class ActionListenerForAlbumButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (recentlyAlbum != null) {
                for (int k = 0; k < albumArrayList.getAlbumArrayList().size(); k++) {
                    if (albumArrayList.getAlbumArrayList().get(k).equals(recentlyAlbum)) {
                        albumArrayList.replace(albumArrayList.getAlbumArrayList().get(k));
                        break;
                    }
                }
            }
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.jButtonsForAlbum.clear();
            centerPanel.jLabel.setText(" ");
            for (int i4 = 0; i4 < albumArrayList.getAlbumArrayList().size(); i4++) {
                centerPanel.creatAlbumButten(albumArrayList.getAlbumArrayList().get(i4));
                centerPanel.jButtonsForAlbum.get(i4).addActionListener(actionListenerForAlbumButtonIncenterpanel);
            }
            for (int i5 = 0; i5 < albumArrayList.getAlbumArrayList().size(); i5++) {
                centerPanel.addAlbumButton(i5);
            }
            centerPanel.setVisible(true);
        }
    }

    /**
     * The Responder class  actionListener for chose albums who created  and open them in center panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/06)
     */
    private class ActionListenerForAlbumButtonIncenterpanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String albumName;
            clickAlbums++;
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.jLabel.setText(" ");
            albumName = e.getActionCommand();
            southPanel.getNextIcon().removeActionListener(actionListenerForNextAndPrevious1);
            actionListenerForNextAndPrevious1 = new ActionListenerForNextAndPrevious(2, albumName, 1);
            southPanel.getNextIcon().addActionListener(actionListenerForNextAndPrevious1);
            southPanel.getPreviousIcon().removeActionListener(actionListenerForNextAndPrevious2);
            actionListenerForNextAndPrevious2 = new ActionListenerForNextAndPrevious(2, albumName, -1);
            southPanel.getPreviousIcon().addActionListener(actionListenerForNextAndPrevious2);
            int j;
            for (j = 0; j < albumArrayList.getAlbumArrayList().size(); j++) {
                if (albumArrayList.getAlbumArrayList().get(j).getName().equals(e.getActionCommand())) {
                    recentlyAlbum = albumArrayList.getAlbumArrayList().get(j);
                    centerPanel.jButtonsSongForAlbum.clear();
                    int i6;
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

    /**
     * The Responder class  actionListener for choose songs and play them in center panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/06)
     */
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
                        pathSong = library.getSongs().get(j).getPath();
                        movingBarSize = library.getSongs().get(j).getTimeOfSong();
                        southPanel.removeMovingBar();
                        southPanel.addMovingBar(movingBarSize);
                        southPanel.movingBarIcon.addMouseListener(new addMouseListener());
                        southPanel.removeArtwork();
                        southPanel.addArtwork(library.getSongs().get(j));
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
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        southPanel.moveMovingBar(0, movingBarSize);
                    }
                });
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
            } else if (status == 3) {
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

    /**
     * The Responder class  actionListener for stop music
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/07)
     */
    private class ActionListenerForStopButten implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            southPanel.getPlayIcon().setIcon(new ImageIcon("icons/play.png"));
            musicPlayer.stop();
            southPanel.stopMovingBar();
            counter = 0;
        }
    }

    /**
     * The Responder class  actionListener  for add new play list in left panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/07)
     */
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

    /**
     * The Responder class  actionListener for albums who created in center panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/06)
     */
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
            actionListenerForNextAndPrevious1 = new ActionListenerForNextAndPrevious(3, playlistName, 1);
            southPanel.getNextIcon().addActionListener(actionListenerForNextAndPrevious1);
            southPanel.getPreviousIcon().removeActionListener(actionListenerForNextAndPrevious2);
            actionListenerForNextAndPrevious2 = new ActionListenerForNextAndPrevious(3, playlistName, -1);
            southPanel.getPreviousIcon().addActionListener(actionListenerForNextAndPrevious2);
            addSongToPlayList = new JButton("+");
            centerPanel.add(addSongToPlayList);
            removeSongFromPlayList = new JButton("-");
            removeSongFromPlayList.addActionListener(new ActionListenerForRemoveFromPlaylistButton());
            JLabel lableplaylistName = new JLabel(e.getActionCommand());
            if ((!playlistName.equals(playLists.getAlbumArrayList().get(0).getName())) && (!playlistName.equals(playLists.getAlbumArrayList().get(1).getName()))) {
                removePLaylist = new JButton("remove");
                renamePLaylist = new JButton("rename");
                centerPanel.add(removePLaylist, FlowLayout.LEFT);
                centerPanel.add(renamePLaylist, FlowLayout.LEFT);
                removePLaylist.addActionListener(new ActionListenerForRemovePlaylistButtenInCenter());
                renamePLaylist.addActionListener(new ActionListenerForRenamePlaylistButtenInCenter1());
            }
            centerPanel.add(lableplaylistName, FlowLayout.LEFT);
            centerPanel.add(removeSongFromPlayList);
            addSongToPlayList.addActionListener(new ActionListenerForSongsButten(2));
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);
        }
    }

    /**
     * The Responder class  actionListener for remove a play list
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/07)
     */
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

    /**
     * The Responder class  actionListener for add a play list in centeral panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/06)
     */
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

    /**
     * The Responder class  actionListener for remove play list in center panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/06)
     */
    private class ActionListenerForRemovePlaylistButtenInCenter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            leftPanel.setVisible(false);
            playLists.removeAlbum(playlistName);
            for (int j = 0; j < leftPanel.getPlaylists().size(); j++) {
                leftPanel.remove(leftPanel.getPlaylists().get(j));
            }
            leftPanel.clearPlaylist();
            for (int j = 2; j < playLists.getAlbumArrayList().size(); j++) {
                leftPanel.addLableOfplaylist(playLists.getAlbumArrayList().get(j).getName());
            }
            for (int i = 0; i < leftPanel.getPlaylists().size(); i++) {
                leftPanel.getPlaylists().get(i).addActionListener(actionListenerForPlayList);
            }
            leftPanel.setVisible(true);
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);

        }
    }

    /**
     * The Responder class  actionListener for rename play list in center panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/08)
     */
    private class ActionListenerForRenamePlaylistButtenInCenter1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll();
            JLabel jLabel = new JLabel("enter yor new name");
            centerPanel.add(jLabel, FlowLayout.LEFT);
            centerPanel.textfildForNewNameOfPlayList();
//            JTextField newNameField=new JTextField();
//            newNameField.setPreferredSize(new Dimension(200,40));
//            centerPanel.add(newNameField);
            JButton rename = new JButton("rename playlist");
            centerPanel.add(rename);
            rename.addActionListener(new ActionListenerForRenamePlaylistButtenInCenter2());
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);

        }
    }

    /**
     * The Responder class  actionListener for enter new name fromn text fieild
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/06)
     */
    private class ActionListenerForRenamePlaylistButtenInCenter2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = centerPanel.playlistNewName.getText();
            centerPanel.playlistNewName.setText("");
            leftPanel.setVisible(false);
            for (int i1 = 0; i1 < playLists.getAlbumArrayList().size(); i1++) {
                if (playLists.getAlbumArrayList().get(i1).getName().equals(playlistName)) {
                    playLists.getAlbumArrayList().get(i1).setNewName(name);
                    break;
                }
            }
            for (int j = 0; j < leftPanel.getPlaylists().size(); j++) {
                leftPanel.remove(leftPanel.getPlaylists().get(j));
            }
            leftPanel.clearPlaylist();
            for (int j = 2; j < playLists.getAlbumArrayList().size(); j++) {
                leftPanel.addLableOfplaylist(playLists.getAlbumArrayList().get(j).getName());
            }
            for (int i = 0; i < leftPanel.getPlaylists().size(); i++) {
                leftPanel.getPlaylists().get(i).addActionListener(actionListenerForPlayList);
            }
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);
            leftPanel.setVisible(true);
        }
    }

    /**
     * The Responder class  actionListener for newxt and previous in sotht panel
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/06)
     */
    private class ActionListenerForNextAndPrevious implements ActionListener {
        int status;
        String albumName;
        int previousOrNext;

        public ActionListenerForNextAndPrevious(int status, String albumName, int previousOrNext) {
            this.status = status;
            this.albumName = albumName;
            this.previousOrNext = previousOrNext;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (status == 1) {
                int j = 0, size = 0;
                if (previousOrNext == 1) {
                    j = 0;
                    size = library.getSongs().size() - 1;
                } else if (previousOrNext == -1) {
                    j = 1;
                    size = library.getSongs().size();
                }
                for (; j < size; j++) {
                    if (library.getSongs().get(j).getPath().equals(pathSong)) {
                        musicPlayer.stop();
                        pathSong = library.getSongs().get(j + previousOrNext).getPath();
                        movingBarSize = library.getSongs().get(j + previousOrNext).getTimeOfSong();
                        southPanel.removeMovingBar();
                        southPanel.addMovingBar(movingBarSize);
                        southPanel.movingBarIcon.addMouseListener(new addMouseListener());
                        southPanel.removeArtwork();
                        southPanel.addArtwork(library.getSongs().get(j + previousOrNext));
                        try {

                            musicPlayer.play(pathSong);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                southPanel.moveMovingBar(0, movingBarSize);
                            }
                        });
                        break;
                    }
                }
            } else if (status == 2) {
                for (int j = 0; j < albumArrayList.getAlbumArrayList().size(); j++) {
                    if (albumArrayList.getAlbumArrayList().get(j).getName().equals(albumName)) {
                        int i = 0, size = 0;
                        if (previousOrNext == 1) {
                            i = 0;
                            size = albumArrayList.getAlbumArrayList().get(j).getSongs().size() - 1;

                        } else if (previousOrNext == -1) {
                            i = 1;
                            size = albumArrayList.getAlbumArrayList().get(j).getSongs().size();
                        }
                        for (; i < size; i++) {
                            if (albumArrayList.getAlbumArrayList().get(j).getSongs().get(i).getPath().equals(pathSong)) {
                                musicPlayer.stop();
                                southPanel.removeArtwork();
                                movingBarSize = albumArrayList.getAlbumArrayList().get(j).getSongs().get(i + previousOrNext).getTimeOfSong();
                                southPanel.removeMovingBar();
                                southPanel.addMovingBar(movingBarSize);
                                southPanel.movingBarIcon.addMouseListener(new addMouseListener());
                                southPanel.addArtwork(albumArrayList.getAlbumArrayList().get(j).getSongs().get(i + previousOrNext));
                                pathSong = albumArrayList.getAlbumArrayList().get(j).getSongs().get(i + previousOrNext).getPath();

                                try {
                                    musicPlayer.play(pathSong);
                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        southPanel.moveMovingBar(0, movingBarSize);
                                    }
                                });
                                break;
                            }

                        }
                        break;
                    }
                }
            } else if (status == 3) {
                for (int j = 0; j < playLists.getAlbumArrayList().size(); j++) {
                    if (playLists.getAlbumArrayList().get(j).getName().equals(albumName)) {
                        int i = 0, size = 0;
                        if (previousOrNext == 1) {
                            i = 0;
                            size = playLists.getAlbumArrayList().get(j).getSongs().size() - 1;
                        } else if (previousOrNext == -1) {
                            i = 1;
                            size = playLists.getAlbumArrayList().get(j).getSongs().size();


                        }
                        for (; i < size; i++) {
                            if (playLists.getAlbumArrayList().get(j).getSongs().get(i).getPath().equals(pathSong)) {
                                musicPlayer.stop();
                                southPanel.removeArtwork();
                                movingBarSize = playLists.getAlbumArrayList().get(j).getSongs().get(i + previousOrNext).getTimeOfSong();
                                southPanel.removeMovingBar();
                                southPanel.addMovingBar(movingBarSize);
                                southPanel.movingBarIcon.addMouseListener(new addMouseListener());
                                southPanel.addArtwork(playLists.getAlbumArrayList().get(j).getSongs().get(i + previousOrNext));
                                pathSong = playLists.getAlbumArrayList().get(j).getSongs().get(i + previousOrNext).getPath();
                                try {

                                    musicPlayer.play(pathSong);
                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        southPanel.moveMovingBar(0, movingBarSize);
                                    }
                                });
                                break;
                            }

                        }
                    }
                }
            }
        }
    }

    /**
     * The Responder class  actionListener for home bottun
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/08)
     */
    private class ActionListenerForHome implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.add(new JLabel(new ImageIcon("icons/hd.png")));
            centerPanel.setVisible(true);
        }
    }

    /**
     * The Responder class  actionListener for search in library
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/07)
     */
    private class ActionListenerForSearch implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = northPanel.searchArea.getText();
            String searchText1 = searchText + ".mp3";
            int isAlive = 0;
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.jButtonsForSong.clear();
            int j = 0;
            for (int i = 0; i < library.getSongs().size(); i++) {
                if ((searchText1.equals(library.getSongs().get(i).getName())) || (searchText.equals(library.getSongs().get(i).getArtistName())) || (searchText.equals(library.getSongs().get(i).getAlbumName()))) {
                    centerPanel.creatButten(library.getSongs().get(i));
                    centerPanel.addButton(j);
                    isAlive = 1;
                    j++;
                }
            }
            for (int i = 0; i < j; i++) {
                centerPanel.jButtonsForSong.get(i).addActionListener(new ActionListenerForSongButtonInCenterPanel(1));
            }
            if (isAlive == 0) {
                JLabel jLabel = new JLabel(" No result");
                centerPanel.add(jLabel);
            }
            northPanel.searchArea.setText("");
            centerPanel.setVisible(true);

        }
    }

    /**
     * The Responder class  actionListener for add too favorite
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/08)
     */
    private class ActionListenerForFavoriteButten implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < playLists.getAlbumArrayList().size(); i++) {
                if (playLists.getAlbumArrayList().get(i).getName().equals("Favorites")) {
                    for (int j = 0; j < library.getSongs().size(); j++) {
                        if (library.getSongs().get(j).getPath().equals(pathSong)) {
                            playLists.getAlbumArrayList().get(i).setSong(library.getSongs().get(j));
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * The Responder class  actionListener for refresh friend activity
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/07)
     */
    private class ActionListenerForRefreshingRight implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rightPanel.removeAll();
            rightPanel.setVisible(false);
            rightPanel.add(rightPanel.getRefreshButton());
            JLabel[] labels = new JLabel[50];
            for (int i = 0; i < Friend.getAllData().size(); i++) {
                labels[i] = new JLabel(Friend.getAllData().get(i));
                labels[i].setForeground(Color.WHITE);
                labels[i].setBackground(Color.BLACK);
                rightPanel.add(labels[i]);
            }
            rightPanel.setVisible(true);
        }
    }

    public int getMinimum() {
        return southPanel.movingBarIcon.getModel().getMinimum();
    }

    public int getMaximum() {
        return southPanel.movingBarIcon.getModel().getMaximum();
    }

    /**
     * The Responder class  actionListener for remove seek move bar
     *
     * @author omid mahyar and pouyan hesabi *
     * @version 1.0  (1398/04/07)
     */
    private class addMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Point p = e.getPoint();
            double percent = p.x / ((double) southPanel.movingBarIcon.getWidth());
            musicPlayer.stop();
            musicPlayer.resumeForMovingbar(percent, pathSong);
            int range = getMaximum() - getMinimum();
            double newVal = range * percent;
            int result = (int) (getMinimum() + newVal);
            southPanel.stopMovingBar2();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    southPanel.moveMovingBar(result, movingBarSize);
                }
            });

        }
    }

}
