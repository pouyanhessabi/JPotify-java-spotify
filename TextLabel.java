import javax.swing.*;
import java.awt.*;

public class TextLabel extends JLabel {
    public TextLabel(String text) {
        this.setText(text);
        this.setForeground(Color.WHITE);
        this.setSize(80,30);
    }
}
