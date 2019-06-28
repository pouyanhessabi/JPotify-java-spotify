import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;


public class LeftPanel extends JPanel {
    private JButton addToLibraryIcon = new JButton(new ImageIcon("icons/plus.jpg"));
    private ButtonForLeftPanel songs;
    private ButtonForLeftPanel albums;
    private int y = 435, i = 0;
    private ButtonForLeftPanel buttonForLeftPanel;
    private ArrayList<ButtonForLeftPanel> playlists = new ArrayList<>();
    private JButton addPlaylistIcon;
    private ButtonForLeftPanel favoriteSongs;
    private ButtonForLeftPanel SharedPlaylist;

    public LeftPanel() {
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(200, 700));
        this.setLayout(null);

        JLabel homeIcon = new JLabel(new ImageIcon("icons/home.png"));
        homeIcon.setLocation(5, 0);
        homeIcon.setSize(25, 30);
        this.add(homeIcon);

        ButtonForLeftPanel home = new ButtonForLeftPanel("Home");
        home.setLocation(30, 0);
        this.add(home);

        JLabel libraryIcon = new JLabel(new ImageIcon("icons/library.png"));
        libraryIcon.setLocation(5, 45);
        libraryIcon.setSize(25, 30);
        this.add(libraryIcon);

        TextLabel library = new TextLabel("Library");
        library.setLocation(50, 45);
        this.add(library);

        songs = new ButtonForLeftPanel("Songs");
        songs.setLocation(30, 90);
        this.add(songs);

        albums = new ButtonForLeftPanel("Albums");
        albums.setLocation(30, 135);
        this.add(albums);

        ButtonForLeftPanel artists = new ButtonForLeftPanel("Artists");
        artists.setLocation(30, 180);
        this.add(artists);
        addToLibraryIcon.setSize(30, 30);
        addToLibraryIcon.setLocation(5, 225);
        addToLibraryIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(Color.DARK_GRAY);
        addToLibraryIcon.setBorderPainted(false);
        addToLibraryIcon.setFocusPainted(false);
        addToLibraryIcon.setContentAreaFilled(false);
        this.add(addToLibraryIcon);

        TextLabel addToLibrary = new TextLabel("add to Library");
        addToLibrary.setLocation(50, 225);
        this.add(addToLibrary);

        addPlaylistIcon = new JButton(new ImageIcon("icons/plus.jpg"));
        addPlaylistIcon.setSize(30, 30);
        addPlaylistIcon.setLocation(5, 315);
        addPlaylistIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(Color.DARK_GRAY);
        addPlaylistIcon.setBorderPainted(false);
        addPlaylistIcon.setFocusPainted(false);
        addPlaylistIcon.setContentAreaFilled(false);
        this.add(addPlaylistIcon);
        TextLabel addToPlaylist = new TextLabel("add Playlist");
        addToPlaylist.setLocation(50, 315);
        this.add(addToPlaylist);

        JLabel playlistIcon = new JLabel(new ImageIcon("icons/playlist.png"));
        playlistIcon.setLocation(7, 270);
        playlistIcon.setSize(25, 30);
        this.add(playlistIcon);

        TextLabel playlist = new TextLabel("Playlist");
        playlist.setLocation(50, 270);
        this.add(playlist);

        favoriteSongs = new ButtonForLeftPanel("Favorites");
        favoriteSongs.setLocation(30, 405);
        favoriteSongs.setSize(100, 30);
        this.add(favoriteSongs);
        SharedPlaylist = new ButtonForLeftPanel("SharedPlaylist");
        SharedPlaylist.setLocation(30, 360);
        SharedPlaylist.setSize(130, 30);
        this.add(SharedPlaylist);


    }

    public void addLabelOfPlaylist(String name) {
        buttonForLeftPanel = new ButtonForLeftPanel(name);
        buttonForLeftPanel.setLocation(50, y + i * 30);
        this.add(buttonForLeftPanel);
        playlists.add(buttonForLeftPanel);
        i++;
    }

    public JButton getAddToLibraryIcon() {
        return addToLibraryIcon;
    }

    public ButtonForLeftPanel getAlbums() {
        return albums;
    }

    public ButtonForLeftPanel getSongs() {
        return songs;
    }

    public JButton getAddPlaylistIcon() {
        return addPlaylistIcon;
    }

    public ArrayList<ButtonForLeftPanel> getPlaylists() {
        return playlists;
    }

    public ButtonForLeftPanel getFavoriteSongs() {
        return favoriteSongs;
    }

    public ButtonForLeftPanel getSharedPlaylist() {
        return SharedPlaylist;
    }
}