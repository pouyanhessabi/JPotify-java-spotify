import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SouthPanel extends JPanel {
    private IconForButton playIcon;
    private IconForButton stopIcon;

    public SouthPanel() {
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(500,100));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        IconForButton previousIcon=new IconForButton("icons/previous.png");
        this.add(previousIcon);

        playIcon=new IconForButton("icons/play.png");
        this.add(playIcon);

        IconForButton nextIcon=new IconForButton("icons/next.png");
        this.add(nextIcon);
        stopIcon=new IconForButton("icons/stop.png");
        this.add(stopIcon);

        JSlider volumeIcon=new JSlider();
        volumeIcon.setPreferredSize(new Dimension(60,20));
        this.add(volumeIcon);

        JSlider movingBarIcon=new JSlider();
        movingBarIcon.setPreferredSize(new Dimension(900,20));
        this.add(movingBarIcon);
    }

    public IconForButton getStopIcon() {
        return stopIcon;
    }

    public IconForButton getPlayIcon() {
        return playIcon;
    }
}
