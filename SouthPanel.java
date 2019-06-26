import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SouthPanel extends JPanel {
    private IconForButton playIcon;
    private IconForButton stopIcon;
    private IconForButton nextIcon;
    private IconForButton previousIcon;
    private static MyJSlider volumeIcon;
    int songNameIsAlive=0;
    private Label songName,songArtist;
    JButton jButton;
    public SouthPanel() {
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(500,100));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        previousIcon=new IconForButton("icons/previous.png");
        this.add(previousIcon);

        playIcon=new IconForButton("icons/play.png");
        this.add(playIcon);

        nextIcon=new IconForButton("icons/next.png");
        this.add(nextIcon);
        stopIcon=new IconForButton("icons/stop.png");
        this.add(stopIcon);

        volumeIcon=new MyJSlider(1,100,50);
        volumeIcon.setPreferredSize(new Dimension(80,20));
        VolumeListener volumeListener=new VolumeListener();
        volumeIcon.addMouseListener(volumeListener);
        this.add(volumeIcon);

        MyJSlider movingBarIcon=new MyJSlider(1,100,50);
        movingBarIcon.setPreferredSize(new Dimension(900,20));
        this.add(movingBarIcon);
    }
    public void removeSongNAme()
    {
        if(songNameIsAlive==1) {
            this.remove(songName);
            this.remove(jButton);
            this.remove(songArtist);
        }
        songNameIsAlive=0;
    }
    public void addSongNameAndImage(Song song){

        songNameIsAlive=1;
        Image temp=new ImageIcon(song.getImageData()).getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        jButton=new JButton();
        jButton.setBorderPainted(false);
        jButton.setFocusPainted(false);
        jButton.setIcon(new ImageIcon(temp));
        this.add(jButton);
        songArtist=new Label(song.getArtistName());
        songArtist.setBackground(Color.WHITE);
        this.add(songArtist,FlowLayout.LEFT);
        songName=new Label(song.getName());
        songName.setBackground(Color.WHITE);
        this.add(songName,FlowLayout.LEFT);
//        jButton=new JButton("123");
//       this.add(jButton,FlowLayout.LEFT);
        this.setVisible(false);
        this.setVisible(true);
    }

    public IconForButton getStopIcon() {
        return stopIcon;
    }

    public IconForButton getPlayIcon() {
        return playIcon;
    }

    public IconForButton getNextIcon() {
        return nextIcon;
    }

    public IconForButton getPreviousIcon() {
        return previousIcon;
    }

    private static class VolumeListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked");
            Audio.setMasterOutputVolume(((float)volumeIcon.getValue()/100));
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Audio.setMasterOutputVolume(((float)volumeIcon.getValue()/100));
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
