import javax.swing.*;
import java.awt.*;

public class IconForButton extends JButton {
    public IconForButton(String path) {
        this.setIcon(new ImageIcon(path));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }
}
