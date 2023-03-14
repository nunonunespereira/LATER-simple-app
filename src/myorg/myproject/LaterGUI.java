package myorg.myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaterGUI extends JFrame {
    private LaterList laterList;
    private JTextField textField;

    public LaterGUI() {
        this.laterList = new LaterList();
        this.textField = new JTextField(20);

        JButton laterButton = new JButton("Later");
        laterButton.setBackground(Color.BLACK);
        laterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = textField.getText();
                laterList.addItem(title);
                textField.setText("");
            }
        });

        JButton nowButton = new JButton("Now");
        nowButton.setBackground(Color.BLACK);
        nowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Later List");
                JTextArea textArea = new JTextArea(10, 20);
                for (Item item : laterList.getItems()) {
                    textArea.append(item.getTitle() + "\n");
                }
                frame.add(textArea);
                frame.pack();
                frame.setVisible(true);
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Remember this ->"));
        panel.add(textField);
        panel.add(laterButton);
        panel.add(nowButton);
        add(panel);

        setTitle("LATER ver_0.1");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
