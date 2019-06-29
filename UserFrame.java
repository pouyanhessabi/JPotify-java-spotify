import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


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
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel=new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());

        JLabel label=new JLabel("                 We don't have your name , do you want to tell us?  ''Warning'': We may achieve your files!   ");
        label.setBackground(Color.BLACK);
        label.setForeground(Color.green);
        label.setPreferredSize(new Dimension(400,100));

        button1=new JButton("(1)  Yes,No Problem");
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.green);
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);
        button1.setPreferredSize(new Dimension(200,50));
        button1.addActionListener(new TextListener());


        JButton button0=new JButton("(0) : is always false!");
        button0.setBackground(Color.BLACK);
        button0.setForeground(Color.green);
        button0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button0.setBorderPainted(false);
        button0.setFocusPainted(false);
        button0.setContentAreaFilled(false);
        button0.setPreferredSize(new Dimension(200,50));
        button0.addActionListener(new ListenerForNo());

        textField=new JTextField();
        textField.setPreferredSize(new Dimension(300,50));
        panel.add(label,BorderLayout.PAGE_START);
        panel.add(button1,BorderLayout.WEST);
        panel.add(button0,BorderLayout.EAST);
        panel.add(textField,BorderLayout.SOUTH);
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

    private class ListenerForNo implements ActionListener{

        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            closeTheFrame();
        }
    }
}
