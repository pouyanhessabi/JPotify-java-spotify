import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * The Responder class manage south panel
 * @author omid mahyar and pouyan hesabi *
 * @version    1.0  (1398/04/06)
 */
public class SouthPanel extends JPanel {
    private IconForButton playIcon;
    private IconForButton stopIcon;
    private IconForButton nextIcon;
    private IconForButton previousIcon;
    private static VolumeJSlider volumeIcon;
    private int songNameIsAlive=0;
    private int movingbarAlive=0;
    JLabel totalTime;
    JLabel songTime=new JLabel("0:00");
    Timer timer;
    private Label songName,songArtist;
    MovingBarJslider movingBarIcon;
    JButton jButton;
    String timeValue;
    int counter2,counter,movingBarsize,firstTime=0;
    IconForButton addTofavorite =new IconForButton("icons/heart.png");
//    JButton addTofavorite=new JButton("+ favorite");
    public SouthPanel() {
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(500,100));
        this.setLayout(new FlowLayout());
        this.add(addTofavorite);
        previousIcon=new IconForButton("icons/previous.png");
        this.add(previousIcon);
        playIcon=new IconForButton("icons/play.png");
        this.add(playIcon);
        nextIcon=new IconForButton("icons/next.png");
        this.add(nextIcon);
        stopIcon=new IconForButton("icons/stop.png");
        this.add(stopIcon);
        volumeIcon=new VolumeJSlider(1,100,50);
        volumeIcon.setPreferredSize(new Dimension(80,20));
        VolumeListener volumeListener=new VolumeListener();
        volumeIcon.addMouseListener(volumeListener);
        this.add(volumeIcon);
    }
    /**
     * stopd moving bar when stop button clicked
     */
    public void stopMovingBar()
    {
        timer.stop();
        movingBarIcon.setValue(0);
    }
    /**
     * stopd moving bar when pause button clicked
     */
    public void stopMovingBar2()
    {
        timer.stop();
    }
    /**
     * creating moving bar and add to south panel
     * @param size a integer who size of moving bar
     */
    public void addMovingBar(int size) {
        int min=size/60;
        int sec=size%60;
        String jlabelText=min+":"+sec;
        totalTime=new JLabel(jlabelText);
        totalTime.setForeground(Color.WHITE);
        System.out.println(jlabelText);
        movingBarIcon = new MovingBarJslider(1, size, 1);
        movingBarIcon.setPreferredSize(new Dimension(700, 20));
        songTime.setForeground(Color.WHITE);
        this.add(songTime);
        this.add(movingBarIcon);
        this.add(totalTime);
            movingbarAlive = 1;
    }
    /**
     * resume moving bar move when resume button clicked
     */
    public void resumeMovingBar()
    {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                counter2++;
                int min=counter2/60;
                int sec=counter2%60;
                if(counter2<10)
                    timeValue="00:0"+counter2;
                else if(counter2<60)
                    timeValue="00:"+counter2;
                else if(counter2<70)
                    timeValue="0"+min+":"+"0"+sec;
                else if(counter2<600)
                    timeValue="0"+min+":"+sec;
                else
                    timeValue=min+":"+sec;

                songTime.setText(timeValue);
                movingBarIcon.setValue(counter2);
                System.out.println(counter2);
                if (counter2>movingBarsize) {
                    timer.stop();
                }
            }
        };
        timer = new Timer(1000, listener);
        timer.start();
    }
    /**
     * manage moving bar move
     * @param start value of stating moving bar
     * @param size a integer who size of moving bar
     */
    public void moveMovingBar(int start,int size)
    {
        counter=start;
        firstTime=0;
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                movingBarsize=size;
                counter++;
                int min=counter/60;
                int sec=counter%60;
                if(counter<10)
                    timeValue="00:0"+counter;
                else if(counter<60)
                    timeValue="00:"+counter;
                else if(counter<70)
                    timeValue="0"+min+":"+"0"+sec;
                else if(counter<600)
                    timeValue="0"+min+":"+sec;
                else
                    timeValue=min+":"+sec;

                songTime.setText(timeValue);
                movingBarIcon.setValue(counter);
                if (counter>size) {
                    timer.stop();
                }
            }
        };
        timer = new Timer(1000, listener);
        timer.start();
    }
    /**
     * paused moving bar when pause button clicked
     */
    public void pauseMovingBar()
    {
        if(firstTime==0)
            counter2=counter;
        timer.stop();
        firstTime++;
    }
    /**
     * removing moving bar wheen new song played
     */
    public void removeMovingBar()
    {
        if(movingbarAlive==1)
        {
            timer.stop();
            this.remove(totalTime);
            this.remove(movingBarIcon);
            this.remove(songTime);
        }
    }
    /**
     * removing artwork wheen new song played
     */
    public void removeArtwork()
    {
        if(songNameIsAlive==1) {
            this.remove(songName);
            this.remove(jButton);
            this.remove(songArtist);
        }
        songNameIsAlive=0;
    }
    /**
     * adding moving bar wheen new song played
     * @param song a Song who adding artwork
     */
    public void addArtwork(Song song){

        songNameIsAlive=1;
        Image temp=new ImageIcon(song.getImageData()).getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        jButton=new JButton();
        jButton.setBorderPainted(false);
        jButton.setFocusPainted(false);
        jButton.setIcon(new ImageIcon(temp));
        jButton.setBackground(Color.GREEN);
        this.add(jButton);
        songArtist=new Label(song.getArtistName());
        songArtist.setForeground(Color.GREEN);
        this.add(songArtist,FlowLayout.LEFT);
        songName=new Label(song.getName());
        songName.setForeground(Color.WHITE);
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
