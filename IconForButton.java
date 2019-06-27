import javax.swing.*;
import java.awt.*;

public class IconForButton extends JButton {
    public IconForButton(String path) {
        Image p1 = new ImageIcon(path).getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(p1));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }
}
