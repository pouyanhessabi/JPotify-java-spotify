import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

public class MainFrame extends JFrame {
    MusicPlayer musicPlayer = new MusicPlayer();
    private RightPanel rightPanel = new RightPanel();
    SouthPanel southPanel = new SouthPanel();
    JLabel jLabel;
    private int counter;
    Song song;
    int i = 0;
    String pathSong;
    int counter1 = 0;
    Album album = new Album("library");
    ActionListenerForButtonInCenterPanel actionListenerForButtonInCenterPanel = new ActionListenerForButtonInCenterPanel();
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
        SongsButten songsButten = new SongsButten();
        leftPanel.songs.addActionListener(songsButten);
        southPanel.getPlayIcon().addActionListener(new ActionListenerForPlay());
        southPanel.getStopIcon().addActionListener(new ActionListenerForStopButten());

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
//                System.out.println(song.getArtistName());
                album.setSong(song);
                centerPanel.setButten(song);

//                Image tem p=new ImageIcon(song.getImageData()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
//                JButton jButton1=new JButton();
//                jButton1.setBorderPainted(false);
//                jButton1.setFocusPainted(false);
//                jButton1.setIcon(new ImageIcon(temp));
//                centerPanel.add(jButton1,FlowLayout.LEFT);
            }

        }
    }

    private class SongsButten implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            centerPanel.setVisible(false);
            centerPanel.jLabel.setText(" ");
//            for (; i <album.getSongs().size() ; i++) {

//                JButton jButton1=new JButton();
//                jButton1.setBorderPainted(false);
//                jButton1.setFocusPainted(false);
//                jButton1.setIcon(new ImageIcon(temp));
//                centerPanel.add(jButton1,FlowLayout.LEFT);
//            }
            centerPanel.setVisible(true);
            for (int j = 0; j < centerPanel.jButtonsForSong.size(); j++) {
                centerPanel.jButtonsForSong.get(j).addActionListener(actionListenerForButtonInCenterPanel);
            }
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
    private class ActionListenerForButtonInCenterPanel implements ActionListener {
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            if (counter1 != 0){
                counter=0;
                musicPlayer.player.close();
            }
            int j=0;
            for ( ; j < album.getSongs().size(); j++) {
                if (album.getSongs().get(j).getName().equals(e.getActionCommand())) {
                    System.out.println(e.getActionCommand() + "  " + album.getSongs().get(j).getName() + "  " + j + "   " + album.getSongs().size());
                    pathSong = album.getSongs().get(j).getPath();
                    southPanel.getPlayIcon().setIcon(new ImageIcon("icons/pause.png"));
                    counter = 1;
                    break;
                }
            }
                try {
                    musicPlayer.play(album.getSongs().get(j).getPath());
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
