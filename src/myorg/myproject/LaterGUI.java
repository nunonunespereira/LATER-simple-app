package myorg.myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LaterGUI extends JFrame {
    private LaterList laterList;
    private JTextField textField;
    private JCheckBox movieCheckBox, tvCheckBox, bookCheckBox, musicCheckBox, gameCheckBox;

    public LaterGUI() {
        this.laterList = new LaterList();
        this.textField = new JTextField(20);

        JButton laterButton = new JButton("Later");
        laterButton.setBackground(Color.BLACK);
        laterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = textField.getText();

                String contentType = "";
                if (movieCheckBox.isSelected()) {
                    contentType = "movie";
                } else if (tvCheckBox.isSelected()) {
                    contentType = "tv";
                } else if (bookCheckBox.isSelected()) {
                    contentType = "book";
                } else if (musicCheckBox.isSelected()) {
                    contentType = "music";
                } else if (gameCheckBox.isSelected()) {
                    contentType = "video game";
                }

                laterList.addItem(title, contentType);
                textField.setText("");
            }
        });

        JButton nowButton = new JButton("Now");
        nowButton.setBackground(Color.BLACK);
        nowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, List<Item>> filteredItemsByType = new HashMap<>();

                List<Item> filteredItems = laterList.getItems();
                if (movieCheckBox.isSelected()) {
                    filteredItemsByType.put("Movies", LaterList.filterItemsByContentType(filteredItems, "movie"));
                }
                if (tvCheckBox.isSelected()) {
                    filteredItemsByType.put("TV Shows", LaterList.filterItemsByContentType(filteredItems, "tv"));
                }
                if (bookCheckBox.isSelected()) {
                    filteredItemsByType.put("Books", LaterList.filterItemsByContentType(filteredItems, "book"));
                }
                if (musicCheckBox.isSelected()) {
                    filteredItemsByType.put("Music", LaterList.filterItemsByContentType(filteredItems, "music"));
                }
                if (gameCheckBox.isSelected()) {
                    filteredItemsByType.put("Video Games", LaterList.filterItemsByContentType(filteredItems, "video game"));
                }

                JTextArea textArea = new JTextArea(10, 20);
                for (String type : filteredItemsByType.keySet()) {
                    List<Item> items = filteredItemsByType.get(type);
                    if (!items.isEmpty()) {
                        textArea.append(type + ":\n");
                        for (Item item : items) {
                            textArea.append(item.getTitle() + "\n");
                        }
                        textArea.append("\n");
                    }
                }

                JScrollPane scrollPane = new JScrollPane(textArea);
                JFrame frame = new JFrame("Later List");
                frame.add(scrollPane);
                frame.pack();
                frame.setVisible(true);
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Remember this ->"));
        panel.add(textField);

        JPanel checkBoxPanel = new JPanel(new GridLayout(5, 1));
        movieCheckBox = new JCheckBox("Movie");
        tvCheckBox = new JCheckBox("TV Show");
        bookCheckBox = new JCheckBox("Book");
        musicCheckBox = new JCheckBox("Music");
        gameCheckBox = new JCheckBox("Video Game");

        checkBoxPanel.add(movieCheckBox);
        checkBoxPanel.add(tvCheckBox);
        checkBoxPanel.add(bookCheckBox);
        checkBoxPanel.add(musicCheckBox);
        checkBoxPanel.add(gameCheckBox);

        panel.add(checkBoxPanel);
        panel.add(laterButton);
        panel.add(nowButton);
        add(panel);

        setTitle("LATER ver_0.3");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
