import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;


public class LeftPanel extends JPanel {
    private JButton addToLibraryIcon= new JButton(new ImageIcon("icons/plus.jpg"));
    ButtonForLeftPanel songs;
    public LeftPanel() {
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(200, 100));
        this.setLayout(null);


        JLabel homeIcon = new JLabel(new ImageIcon("icons/home.png"));
        homeIcon.setLocation(5, 0);
        homeIcon.setSize(25, 30);
        this.add(homeIcon);

        ButtonForLeftPanel home=new ButtonForLeftPanel("Home");
        home.setLocation(30, 0);
        this.add(home);

        JLabel libraryIcon = new JLabel(new ImageIcon("icons/library.png"));
        libraryIcon.setLocation(5, 45);
        libraryIcon.setSize(25, 30);
        this.add(libraryIcon);

        TextLabel library=new TextLabel("Library");
        library.setLocation(50,45);
        this.add(library);

        songs=new ButtonForLeftPanel("Songs");
        songs.setLocation(30,90);
        this.add(songs);

        ButtonForLeftPanel albums=new ButtonForLeftPanel("Albums");
        albums.setLocation(30, 135);
        this.add(albums);

        ButtonForLeftPanel artists = new ButtonForLeftPanel("Artists");
        artists.setLocation(30, 180);
        this.add(artists);
        addToLibraryIcon.setSize(30,30);
        addToLibraryIcon.setLocation(5,225);
        addToLibraryIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(Color.DARK_GRAY);
        addToLibraryIcon.setBorderPainted(false);
        addToLibraryIcon.setFocusPainted(false);
        addToLibraryIcon.setContentAreaFilled(false);
        this.add(addToLibraryIcon);

        TextLabel addToLibrary=new TextLabel("add to Library");
        addToLibrary.setLocation(50,225);
        this.add(addToLibrary);

        JLabel playlistIcon=new JLabel(new ImageIcon("icons/playlist.png"));
        playlistIcon.setLocation(7, 270);
        playlistIcon.setSize(25, 30);
        this.add(playlistIcon);

        TextLabel playlist=new TextLabel("Playlist");
        playlist.setLocation(50,270);
        this.add(playlist);

        ButtonForLeftPanel favoriteSongs=new ButtonForLeftPanel("Favorites");
        favoriteSongs.setLocation(30,315);
        favoriteSongs.setSize(100,30);
        this.add(favoriteSongs);

        ButtonForLeftPanel recentlyPlayed=new ButtonForLeftPanel("Recently Played");
        recentlyPlayed.setLocation(30,360);
        recentlyPlayed.setSize(130,30);
        this.add(recentlyPlayed);

        JButton addToPlaylistIcon= new JButton(new ImageIcon("icons/plus.jpg"));
        addToPlaylistIcon.setSize(30,30);
        addToPlaylistIcon.setLocation(5,405);
        addToPlaylistIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(Color.DARK_GRAY);
        addToPlaylistIcon.setBorderPainted(false);
        addToPlaylistIcon.setFocusPainted(false);
        addToPlaylistIcon.setContentAreaFilled(false);
        this.add(addToPlaylistIcon);
        TextLabel addToPlaylist=new TextLabel("add to Playlist");
        addToPlaylist.setLocation(50,405);
        this.add(addToPlaylist);

    }

    public JButton getAddToLibraryIcon() {
        return addToLibraryIcon;
    }
}