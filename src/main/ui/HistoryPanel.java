package main.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import main.controller.DictionaryController;
import main.model.SlangEntry;

public class HistoryPanel extends JPanel {

    public HistoryPanel(MainMenu mainMenu, DictionaryController controller) {
        setLayout(new BorderLayout());

        // top
        JButton btnBack = new JButton("← Back");
        btnBack.setFocusable(false);

        btnBack.addActionListener(e -> mainMenu.showMainMenuPanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        // center
        JPanel historyBox = new JPanel();
        historyBox.setLayout(new BoxLayout(historyBox, BoxLayout.Y_AXIS));
        historyBox.setBackground(new Color(255, 255, 230));
        historyBox.setBorder(BorderFactory.createLineBorder(new Color(245, 200, 80), 2));

        List<String> historyWords = controller.getHistory();

        if (historyWords.isEmpty()) {
            JLabel emptyLabel = new JLabel("No search history yet.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            historyBox.add(Box.createVerticalStrut(10));
            historyBox.add(emptyLabel);
            historyBox.add(Box.createVerticalStrut(10));
        }
        else {
            int index = 1;
            for (String slang : historyWords) {
                SlangEntry entry = controller.getSlangEntry(slang);
                String meaningText = "";
                if (entry != null) {
                    meaningText = String.join(" | ", entry.getDefinitions());
                }
                String text = index + ". " + slang + " -- " + meaningText;

                JLabel row =  new JLabel(text);
                row.setFont(new Font("Arial", Font.PLAIN, 14));
                row.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
                historyBox.add(row);
                index++;
            }

        }

        JScrollPane scrollPane = new JScrollPane(historyBox);
        scrollPane.getViewport().setBackground(new Color(255, 255, 230));
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add(scrollPane);

        add(wrapper, BorderLayout.CENTER);
    }
}
