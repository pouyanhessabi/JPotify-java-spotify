import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CenterPanel extends JPanel {
    JLabel jLabel=new JLabel();
    JButton jButton1;
    ArrayList<JButton>jButtonsForSong=new ArrayList<>();
    ArrayList<JButton>jButtonsForAlbum=new ArrayList<>();
    ArrayList<JButton>jButtonsSongForAlbum=new ArrayList<>();
    public CenterPanel() {
        this.add(jLabel);
        this.setLayout(new FlowLayout());
//        this.add(jButton,BorderLayout.CENTER);
//        JLabel background=new JLabel(new ImageIcon("icons/binary.jpg"));
//        this.add(background);

    }
    public void removeSongButtenForAlbum()
    {
        jButtonsForSong.remove(jButton1);
    }
    public void addSongButtonForAlbum(int i)
    {
        this.add(jButtonsSongForAlbum.get(i));
    }
    public void addAlbumButton(int i)
    {
        this.add(jButtonsForAlbum.get(i));
    }
    public void addButton(int i)
    {
        this.add(jButtonsForSong.get(i));
    }
    public void creatSongButtenForAlbume(Song song)
    {
        Image temp=new ImageIcon(song.getImageData()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        jButton1=new JButton();
        jButton1.setText(song.getName());
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setIcon(new ImageIcon(temp));
        jButtonsSongForAlbum.add(jButton1);
    }
    public void creatButten(Song song)
    {
        Image temp=new ImageIcon(song.getImageData()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        jButton1=new JButton();
        jButton1.setText(song.getName());
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setIcon(new ImageIcon(temp));
        jButtonsForSong.add(jButton1);
    }

    public void creatAlbumButten(Album album) {
        Image temp=new ImageIcon(album.getSongs().get(0).getImageData()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        jButton1=new JButton();
        jButton1.setText(album.getName());
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setIcon(new ImageIcon(temp));
        jButtonsForAlbum.add(jButton1);

    }
}
