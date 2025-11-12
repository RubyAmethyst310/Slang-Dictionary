package main.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import main.controller.DictionaryController;
import main.model.SlangEntry;

public class SearchByDefinitionPanel extends JPanel {
    private JPanel resultContainer;

    public SearchByDefinitionPanel(MainMenu mainMenu, DictionaryController controller) {
        setLayout(new BorderLayout());

        // top
        JButton btnBack = new JButton("← Back");
        btnBack.setFocusable(false);
        btnBack.addActionListener(e -> mainMenu.showSearchPanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        // center
        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        // search line
        JPanel searchRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel labelInput = new JLabel("Enter a definition: ");
        JTextField textFieldInput = new JTextField(20);
        JButton btnSearch =  new JButton("Search");

        searchRowPanel.add(labelInput);
        searchRowPanel.add(textFieldInput);
        searchRowPanel.add(btnSearch);

        searchRowPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(searchRowPanel);

        resultContainer = new JPanel(new BorderLayout());
        resultContainer.setPreferredSize(new Dimension(500, 250));

        resultContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(resultContainer);

        centerWrapper.add(centerPanel, BorderLayout.CENTER);
        add(centerWrapper, BorderLayout.CENTER);

        btnSearch.addActionListener(e -> {
            String input = textFieldInput.getText().trim();
            resultContainer.removeAll();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a definition.");
                resultContainer.revalidate();
                resultContainer.repaint();
                return;
            }

            List<SlangEntry> results = controller.searchDefinition(input);

            JComponent resultBox = buildResultBox(results);
            resultContainer.add(resultBox, BorderLayout.CENTER);

            resultContainer.revalidate();
            resultContainer.repaint();
        });
    }

    private JComponent buildResultBox(List<SlangEntry> results) {
        JPanel resultListPanel = new JPanel();
        resultListPanel.setLayout(new BoxLayout(resultListPanel, BoxLayout.Y_AXIS));
        resultListPanel.setBackground(new Color(255, 255, 230));
        resultListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (results.isEmpty()) {
            JLabel emptyLabel = new JLabel("No results found.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 20));
            emptyLabel.setForeground(Color.RED);
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
            wrapper.setBackground(new Color(255, 255, 230));
            wrapper.add(emptyLabel);
            return wrapper;
        }

        int index = 1;
        for (SlangEntry entry : results) {
            String meaningText = String.join(" | ", entry.getDefinitions());
            String text = index + ". " + entry.getWord() + " -- " + meaningText;

            JLabel row =  new JLabel(text);
            row.setFont(new Font("Arial", Font.PLAIN, 14));
            row.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            resultListPanel.add(row);
            index++;
        }

        JScrollPane scrollPane = new JScrollPane(resultListPanel);
        scrollPane.getViewport().setBackground(new Color(255, 255, 230));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(245, 200, 80), 2));

        return scrollPane;
    }
}
