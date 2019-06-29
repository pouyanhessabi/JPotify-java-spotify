import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The Responder class manage centeral panel
 *
 * @author omid mahyar and pouyan hesabi *
 * @version 1.0  (1398/04/05)
 */
public class CenterPanel extends JPanel {
    JLabel jLabel = new JLabel();
    JButton jButton1;
    TextField playlistName = new TextField();
    TextField playlistNewName = new TextField();
    ArrayList<JButton> jButtonsForSong = new ArrayList<>();
    ArrayList<JButton> jButtonsForAlbum = new ArrayList<>();
    ArrayList<JButton> jButtonsSongForAlbum = new ArrayList<>();

    public CenterPanel() {
        this.setLayout(new FlowLayout());
        playlistName.setPreferredSize(new Dimension(200, 40));
        this.add(jLabel);
        playlistNewName.setPreferredSize(new Dimension(200, 40));
        this.setPreferredSize(new Dimension(750, 3000));
        this.setBackground(Color.BLACK);

//        this.add(jButton,BorderLayout.CENTER);
//        JLabel background=new JLabel(new ImageIcon("icons/binary.jpg"));
//        this.add(background);

    }

    /**
     * adding song button for album in central panel
     *
     * @param i a integer who number of button
     */
    public void addSongButtonForAlbum(int i) {
        this.add(jButtonsSongForAlbum.get(i));
    }

    /**
     * adding album  button in central panel
     *
     * @param i a integer who number of button
     */
    public void addAlbumButton(int i) {
        this.add(jButtonsForAlbum.get(i));
    }

    /**
     * adding song button in central panel
     *
     * @param i a integer who number of button
     */
    public void addButton(int i) {
        this.add(jButtonsForSong.get(i));
    }

    /**
     * adding textField for enter playlist name in central panel
     */
    public void textfildForNameOfPlayList() {
        this.add(playlistName, FlowLayout.LEFT);
    }

    /**
     * adding textField for enter new playlist name in central panel
     */
    public void textfildForNewNameOfPlayList() {
        this.add(playlistNewName, FlowLayout.LEFT);
    }

    /**
     * creating song button for album in central panel
     *
     * @param song a Song who create button
     */
    public void creatSongButtenForAlbume(Song song) {
        Image temp = new ImageIcon(song.getImageData()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        jButton1 = new JButton();
        jButton1.setText(song.getName());
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setIcon(new ImageIcon(temp));
        jButtonsSongForAlbum.add(jButton1);
    }

    /**
     * creating song button in central panel
     *
     * @param song a Song who create button
     */
    public void creatButten(Song song) {
        Image temp = new ImageIcon(song.getImageData()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        jButton1 = new JButton();
        jButton1.setText(song.getName());
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setIcon(new ImageIcon(temp));
        jButtonsForSong.add(jButton1);
    }

    /**
     * creating Album button in central panel
     *
     * @param album a Album who create button
     */
    public void creatAlbumButten(Album album) {
        Image temp = new ImageIcon(album.getSongs().get(0).getImageData()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        jButton1 = new JButton();
        jButton1.setText(album.getName());
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setIcon(new ImageIcon(temp));
        jButtonsForAlbum.add(jButton1);

    }
}
