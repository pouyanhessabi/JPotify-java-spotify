import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private JButton refreshButton;

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public RightPanel() {

        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(250,700));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        refreshButton=new JButton("Refresh");

        this.add(refreshButton);

    }
}
