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

                JPanel mainPanel = new JPanel(new GridLayout(filteredItemsByType.keySet().size(), 1));
                for (String type : filteredItemsByType.keySet()) {
                    List<Item> items = filteredItemsByType.get(type);
                    if (!items.isEmpty()) {
                        JPanel typePanel = new JPanel(new BorderLayout());
                        typePanel.add(new JLabel(type + ":"), BorderLayout.NORTH);

                        JPanel itemsPanel = new JPanel(new GridLayout(items.size(), 1));
                        for (Item item : items) {
                            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            JLabel titleLabel = new JLabel(item.getTitle());
                            JButton deleteButton = new JButton("Delete");
                            deleteButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    laterList.removeItem(item);
                                    itemsPanel.remove(itemPanel);
                                    itemsPanel.revalidate();
                                    itemsPanel.repaint();
                                }
                            });
                            itemPanel.add(titleLabel);
                            itemPanel.add(deleteButton);
                            itemsPanel.add(itemPanel);
                        }
                        typePanel.add(new JScrollPane(itemsPanel), BorderLayout.CENTER);

                        mainPanel.add(typePanel);
                    }
                }

                JScrollPane scrollPane = new JScrollPane(mainPanel);
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
