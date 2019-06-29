import javax.swing.*;
import java.awt.*;

public class ButtonForLeftPanel extends JButton {

    public ButtonForLeftPanel(String text) {
        this.setText(text);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setSize(80, 30);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }
}
