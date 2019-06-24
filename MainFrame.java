import sun.font.FontRunIterator;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    MusicPlayer musicPlayer = new MusicPlayer();
    private RightPanel rightPanel = new RightPanel();
    SouthPanel southPanel = new SouthPanel();
    JLabel jLabel;
    private int counter;
    Song song;
    int i = 0,i1=0,i4=0, i6 = 0;
    String pathSong;
    int counter1 = 0;
    Album library = new Album("library");
    Album[] tempOfLibraryArray=new Album[100];
    ArrayList<Album>albumArrayList=new ArrayList<>();
    Album tempOfLibrary=new Album("temp");
    ActionListenerForSongButtonInCenterPanel actionListenerForButtonInCenterPanel = new ActionListenerForSongButtonInCenterPanel();
    ActionListenerForAlbumButtonIncenterpanel actionListenerForAlbumButtonIncenterpanel = new ActionListenerForAlbumButtonIncenterpanel();
    private CenterPanel centerPanel = new CenterPanel();
    public MainFrame() {
        counter = 1;
        counter=0;

        this.setTitle("Binarify");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 700);
        LeftPanel leftPanel = new LeftPanel();
        this.add(leftPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.PAGE_END);
        this.add(rightPanel, BorderLayout.EAST);
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.setVisible(true);
        MyListener myListener = new MyListener();
        leftPanel.getAddToLibraryIcon().addActionListener(myListener);
        ActionListenerForSongsButten actionListenerForSongsButten=new ActionListenerForSongsButten();
        leftPanel.getSongs().addActionListener(actionListenerForSongsButten);
        southPanel.getPlayIcon().addActionListener(new ActionListenerForPlay());
        southPanel.getStopIcon().addActionListener(new ActionListenerForStopButten());
        leftPanel.getAlbums().addActionListener(new ActionListenerForAlbumButton());

        //Pjadid
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(leftPanel);
        this.add(scrollPane,BorderLayout.WEST);
        this.pack();
        //Pjadid


    }
//    public void copyLibrary()
    {
        if(tempOfLibrary.getSongs().size()!=0)
        {
            for (int j = 0; j <tempOfLibrary.getSongs().size() ; j++) {
                tempOfLibrary.removeSong(tempOfLibrary.getSongs().get(i));
            }
        }
        for (int j = 0; j <library.getSongs().size() ; j++) {
            tempOfLibrary.setSong(library.getSongs().get(j));
        }
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
                int noAlbum=1;
                if(albumArrayList.size()!=0)
                {
                    for (int j = 0; j <albumArrayList.size() ; j++) {
                        if(albumArrayList.get(j).getName().equals(song.getAlbumName()))
                        {
                            albumArrayList.get(j).setSong(song);
                            noAlbum=0;
                        }
                    }
                    if(noAlbum==1)
                    {
                        Album album=new Album(song.getAlbumName());
                        album.setSong(song);
                        albumArrayList.add(album);
                    }
                }
                else
                {
                    Album album=new Album(song.getAlbumName());
                    album.setSong(song);
                    albumArrayList.add(album);
                }
                library.setSong(song);
            }
            System.out.println(albumArrayList.size());
//            for (int j = 0; j <albumArrayList.size() ; j++) {
//                for (int k = 0; k <albumArrayList.get(j).getSongs().size(); k++) {
//                    System.out.println(albumArrayList.get(j).getName()+"  "+albumArrayList.get(j).getSongs().get(k).getName());
//                }
//            }
        }
    }

    private class ActionListenerForSongsButten implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            centerPanel.jLabel.setText(" ");
            for (; i1 <library.getSongs().size() ; i1++) {
                centerPanel.creatButten(library.getSongs().get(i1));
                centerPanel.jButtonsForSong.get(i1).addActionListener(actionListenerForButtonInCenterPanel);
            }
            for (int i2=0; i2 <library.getSongs().size() ; i2++) {
                centerPanel.addButton(i2);
            }
            centerPanel.setVisible(true);
        }
    }
    private class ActionListenerForPlay implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(counter);
            if(counter==0)
            {
                try {
                    musicPlayer.play(pathSong);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                southPanel.getPlayIcon().setIcon(new ImageIcon("icons/pause.png"));

            }
            else if (counter % 2 == 0) {
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
            centerPanel.jLabel.setText(" ");
            for (; i4 <albumArrayList.size() ; i4++) {
                centerPanel.creatAlbumButten(albumArrayList.get(i4));
                centerPanel.jButtonsForAlbum.get(i4).addActionListener(actionListenerForAlbumButtonIncenterpanel);
            }
            for (int i5=0; i5 <albumArrayList.size() ; i5++) {
                centerPanel.addAlbumButton(i5);
            }
            centerPanel.setVisible(true);
        }
    }

    private class ActionListenerForAlbumButtonIncenterpanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            System.out.println(e.getActionCommand());
            centerPanel.removeAll();
            centerPanel.setVisible(false);
            System.out.println(albumArrayList.size());
            centerPanel.jLabel.setText(" ");
            for (int j = 0; j < albumArrayList.size(); j++) {
                System.out.println(albumArrayList.get(j).getName()+"   "+e.getActionCommand()+"  j= "+j);
                if (albumArrayList.get(j).getName().equals(e.getActionCommand())) {
                    centerPanel.jButtonsSongForAlbum.clear();

                    for (i6=0; i6 < albumArrayList.get(j).getSongs().size(); i6++) {
                        System.out.println("OKKKKK");
                        centerPanel.creatSongButtenForAlbume(albumArrayList.get(j).getSongs().get(i6));
                        centerPanel.jButtonsSongForAlbum.get(i6).addActionListener(actionListenerForButtonInCenterPanel);
                    }
                    for (int i2 = 0; i2 < albumArrayList.get(j).getSongs().size(); i2++) {
                        centerPanel.addSongButtonForAlbum(i2);
                    }
                    centerPanel.setVisible(true);
                    break;
                }
            }
        }
    }
    private class ActionListenerForSongButtonInCenterPanel implements ActionListener {
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            if (counter1 != 0){
                counter=0;
                musicPlayer.player.close();
            }
            int j=0;
            for ( ; j < library.getSongs().size(); j++) {
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
        }
    }

    private class ActionListenerForStopButten implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            southPanel.getPlayIcon().setIcon(new ImageIcon("icons/play.png"));
            musicPlayer.stop();
            counter=0;
        }
    }
    private class ActionListenerFornext implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
        }
}
