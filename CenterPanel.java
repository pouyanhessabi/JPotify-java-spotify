import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CenterPanel extends JPanel {
    JLabel jLabel=new JLabel();
    ArrayList<JButton>jButtonsForSong=new ArrayList<>();
    public CenterPanel() {
        this.add(jLabel);
        this.setLayout(new FlowLayout());
//        this.add(jButton,BorderLayout.CENTER);
//        JLabel background=new JLabel(new ImageIcon("icons/binary.jpg"));
//        this.add(background);

    }
    public void setButten(Song song)
    {
        Image temp=new ImageIcon(song.getImageData()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        JButton jButton1=new JButton();
        jButton1.setText(song.getName());
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setIcon(new ImageIcon(temp));
        this.add(jButton1);
        jButtonsForSong.add(jButton1);
    }
}
