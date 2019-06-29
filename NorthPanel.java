import javax.swing.*;
import java.awt.*;
/**
 * The Responder class manage north panel
 * @author omid mahyar and pouyan hesabi *
 * @version    1.0  (1398/04/05)
 */
public class NorthPanel extends JPanel {
    JLabel name=new JLabel();
    JTextField searchArea=new JTextField();
    JButton searchButton=new JButton("Search");
    public NorthPanel() {
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new FlowLayout());
        searchArea.setPreferredSize(new Dimension(150,30));
        searchButton.setPreferredSize(new Dimension(100,30));
        this.add(searchArea);
        this.add(searchButton);
    }
    /**
     * adding client name too panel panel
     * @param s a String who name of client
     */
    public void addNameToNorthPanel(String s)
    {
        name.setText(s);
        name.setForeground(Color.WHITE);
        this.add(name,FlowLayout.LEFT);
    }
}
