import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    MusicPlayer musicPlayer = new MusicPlayer();
    private RightPanel rightPanel = new RightPanel();
    public SouthPanel southPanel = new SouthPanel();
    LeftPanel leftPanel = new LeftPanel();
    JLabel jLabel;
    private int counter;
    Song song;
    JButton addSongToPlayList;
    int i = 0, i6 = 0;
    String pathSong;
    int counter1 = 0;
    String playlistName;
    Album library = new Album("library");
//    ArrayList<Album> albumArrayList = new ArrayList<>();
    Albums albumArrayList=new Albums();
//    ArrayList<Album> playLists = new ArrayList<>();
    Albums playLists=new Albums();

    ActionListenerForSongButtonInCenterPanel actionListenerForButtonInCenterPanel1 = new ActionListenerForSongButtonInCenterPanel(1);
    ActionListenerForSongButtonInCenterPanel actionListenerForButtonInCenterPanel2 = new ActionListenerForSongButtonInCenterPanel(2);
    ActionListenerForAlbumButtonIncenterpanel actionListenerForAlbumButtonIncenterpanel = new ActionListenerForAlbumButtonIncenterpanel();
    ActionListenerForPlayList actionListenerForPlayList = new ActionListenerForPlayList();
    private CenterPanel centerPanel = new CenterPanel();
    ActionListenerForNextAndPrevious actionListenerForNextAndPrevious;
    public MainFrame() {
        counter = 1;
        counter = 0;
        this.setTitle("Binarify");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 700);
        this.add(southPanel, BorderLayout.PAGE_END);
        this.add(rightPanel, BorderLayout.EAST);
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.setVisible(true);
        MyListener myListener = new MyListener();
        leftPanel.getAddToLibraryIcon().addActionListener(myListener);
        ActionListenerForSongsButten actionListenerForSongsButten = new ActionListenerForSongsButten(1);
        leftPanel.getSongs().addActionListener(actionListenerForSongsButten);
        southPanel.getPlayIcon().addActionListener(new ActionListenerForPlay());
        southPanel.getStopIcon().addActionListener(new ActionListenerForStopButten());
        leftPanel.getAlbums().addActionListener(new ActionListenerForAlbumButton());
        leftPanel.getAddPlaylistIcon().addActionListener(new ActionListenerForAddPlaylistButten());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FileOutputStream fileOutputStream= null;
                try {
                    fileOutputStream = new FileOutputStream("library.txt");
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
                    fileOutputStream1 = new FileOutputStream("album.txt");
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
                    fileOutputStream2 = new FileOutputStream("playlists.txt");
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
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(leftPanel);
        this.add(scrollPane,BorderLayout.WEST);
        this.pack();
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
            southPanel.getNextIcon().removeActionListener(actionListenerForNextAndPrevious);
            actionListenerForNextAndPrevious=new ActionListenerForNextAndPrevious(1, "library",1);
            southPanel.getNextIcon().addActionListener(actionListenerForNextAndPrevious);
            southPanel.getPreviousIcon().removeActionListener(actionListenerForNextAndPrevious);
            actionListenerForNextAndPrevious=new ActionListenerForNextAndPrevious(1, "library",-1);
            southPanel.getPreviousIcon().addActionListener(actionListenerForNextAndPrevious);
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
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.jButtonsSongForAlbum.clear();
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
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            System.out.println(albumArrayList.getAlbumArrayList().size());
            centerPanel.jLabel.setText(" ");
            albumName=e.getActionCommand();
            southPanel.getNextIcon().removeActionListener(actionListenerForNextAndPrevious);
            actionListenerForNextAndPrevious=new ActionListenerForNextAndPrevious(2,albumName,1);
            southPanel.getPreviousIcon().removeActionListener(actionListenerForNextAndPrevious);
            actionListenerForNextAndPrevious=new ActionListenerForNextAndPrevious(2, "library",-1);
            southPanel.getPreviousIcon().addActionListener(actionListenerForNextAndPrevious);
            southPanel.getNextIcon().addActionListener(actionListenerForNextAndPrevious);
            for (int j = 0; j < albumArrayList.getAlbumArrayList().size(); j++) {
                if (albumArrayList.getAlbumArrayList().get(j).getName().equals(e.getActionCommand())) {

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
            String name = centerPanel.textField.getText();
            System.out.println(name);
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
            southPanel.getNextIcon().removeActionListener(actionListenerForNextAndPrevious);
            actionListenerForNextAndPrevious=new ActionListenerForNextAndPrevious(3, playlistName,1);
            southPanel.getNextIcon().addActionListener(actionListenerForNextAndPrevious);
            southPanel.getPreviousIcon().removeActionListener(actionListenerForNextAndPrevious);
            actionListenerForNextAndPrevious=new ActionListenerForNextAndPrevious(3, "library",-1);
            southPanel.getPreviousIcon().addActionListener(actionListenerForNextAndPrevious);
            addSongToPlayList = new JButton("+");
            JLabel lableplaylistName = new JLabel(e.getActionCommand());
            centerPanel.add(lableplaylistName);
            centerPanel.add(addSongToPlayList);
            addSongToPlayList.addActionListener(new ActionListenerForSongsButten(2));
            centerPanel.setVisible(false);
            centerPanel.setVisible(true);
        }
    }
    private class ActionListenerForButtonInCenterPanelForAddTooLibrary implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

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
                        System.out.println(library.getSongs().get(j).getPath()+"     "+pathSong+"  "+j);
                        musicPlayer.stop();
                        pathSong = library.getSongs().get(j + previousOrNext).getPath();
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
                        int i = 0,size = 0;
                        if(previousOrNext==1) {
                            j=0;
                            size=albumArrayList.getAlbumArrayList().get(j).getSongs().size() - 1;
                        }
                        else if(previousOrNext==-1)
                        {
                            j=1;
                            size=albumArrayList.getAlbumArrayList().get(j).getSongs().size();
                        }
                        for (; i < size; i++) {
                            if (albumArrayList.getAlbumArrayList().get(j).getSongs().get(i).getPath().equals(pathSong))
                            {
                                musicPlayer.stop();
                                pathSong = albumArrayList.getAlbumArrayList().get(j).getSongs().get(i+previousOrNext).getPath();
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
            else if (status == 3) {
                for (int j = 0; j < playLists.getAlbumArrayList().size(); j++) {
                    if (playLists.getAlbumArrayList().get(j).getName().equals(albumName)) {
                        int i = 0,size = 0;
                        if(previousOrNext==1) {
                            j=0;
                            size=playLists.getAlbumArrayList().get(j).getSongs().size() - 1;
                        }
                        else if(previousOrNext==-1)
                        {
                            j=1;
                            size=playLists.getAlbumArrayList().get(j).getSongs().size() - 1;
                        }
                        for (; i <size ; i++) {
                            if (playLists.getAlbumArrayList().get(j).getSongs().get(i).getPath().equals(pathSong))
                            {
                                musicPlayer.stop();
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
}
