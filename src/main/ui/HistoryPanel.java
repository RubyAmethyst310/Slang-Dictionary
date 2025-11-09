package main.ui;

import main.controller.DictionaryController;
import main.model.SlangEntry;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        List<String> historyWords = controller.getHistory();

        if (historyWords.isEmpty()) {
            JLabel emptyLabel = new JLabel("No search history yet.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            centerPanel.add(Box.createVerticalGlue());
            centerPanel.add(emptyLabel);
            centerPanel.add(Box.createVerticalGlue());
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
                row.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                centerPanel.add(row);
                index++;
            }

        }

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }
}
