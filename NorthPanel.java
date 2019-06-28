import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {
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
}
