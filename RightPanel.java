import javax.swing.*;
import java.awt.*;
/**
 * The Responder class manage south panel
 *
 * @author omid mahyar and pouyan hesabi *
 * @version 1.0  (1398/04/09)
 */
public class RightPanel extends JPanel {
    private JButton refreshButton;

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public RightPanel() {

        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(250, 700));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        refreshButton = new JButton("Refresh");

        this.add(refreshButton);

    }
}
