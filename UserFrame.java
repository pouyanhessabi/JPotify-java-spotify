import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
/**
 * The Responder class  get user name from client
 * @author omid mahyar and pouyan hesabi *
 * @version    1.0  (1398/04/04)
 */

public class UserFrame extends JFrame {
    private JTextField textField;
    private String text;
    private volatile boolean tmpCheck;

    public void setTmpCheck(boolean tmpCheck) {
        this.tmpCheck = tmpCheck;
    }

    public boolean isTmpCheck() {
        return tmpCheck;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JButton getButton1() {
        return button1;
    }

    private JButton button1;

    public JTextField getTextField() {
        return textField;
    }


    public UserFrame() {
        this.setTitle("Welcome to the Binarify");
        setTmpCheck(false);
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel=new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setSize(100,100);
        JLabel label=new JLabel("                                                                                    Enter your name   ");
        label.setBackground(Color.BLACK);
        label.setForeground(Color.green);
        label.setPreferredSize(new Dimension(600,30));
        button1=new JButton("Sing in");
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.green);
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);
        button1.setPreferredSize(new Dimension(600,30));
        button1.addActionListener(new TextListener());
        textField=new JTextField();
        textField.setPreferredSize(new Dimension(200,50));
        panel.add(label);
        panel.add(textField);
        panel.add(button1);
        this.add(panel);
        this.setVisible(true);

    }

    public void closeTheFrame() {
        this.setVisible(false);
    }
    private class TextListener implements ActionListener {
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            if (e.getSource() == getButton1()) {
                setText(getTextField().getText());
                setTmpCheck(true);
                closeTheFrame();
            }
        }
    }
}
