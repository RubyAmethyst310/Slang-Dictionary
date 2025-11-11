package main.ui;

import main.controller.DictionaryController;
import main.model.SlangEntry;

import javax.swing.*;
import java.awt.*;

public class SearchBySlangPanel extends JPanel {
    private JLabel wordLabel;
    private JLabel meaningLabel;

    public SearchBySlangPanel(MainMenu mainMenu, DictionaryController controller) {
        setLayout(new BorderLayout());

        // top
        JButton btnBack = new JButton("← Back");
        btnBack.setFocusable(false);
        btnBack.addActionListener(e -> mainMenu.showSearchPanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        // center
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        // center top
        JPanel searchRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel labelInput = new JLabel("Enter a slang word: ");
        JTextField textFieldInput = new JTextField(20);
        JButton btnSearch =  new JButton("Search");

        searchRowPanel.add(labelInput);
        searchRowPanel.add(textFieldInput);
        searchRowPanel.add(btnSearch);

        gbc.gridy = 0;
        centerPanel.add(searchRowPanel, gbc);

        // slang word result
        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 36));
        wordLabel.setForeground(new Color(0, 128, 0)); // dark green
        gbc.gridy++;
        centerPanel.add(wordLabel, gbc);

        // meanings result
        meaningLabel = new JLabel("", SwingConstants.CENTER);
        meaningLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        meaningLabel.setForeground(new Color(0, 191, 255));
        gbc.gridy++;
        centerPanel.add(meaningLabel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        btnSearch.addActionListener(e -> {
            String input = textFieldInput.getText().trim();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a word.");
                return;
            }

            SlangEntry result = controller.getSlangEntry(input);

            if (result == null) {
                wordLabel.setText("");
                meaningLabel.setText("<html><div style='text-align:center;color:red;'>"
                        + "This slangword is not in the dictionary!<br>"
                        + "Please try another one."
                        + "</div></html>");
                return;
            }
            controller.addToHistory(result.getWord());

            wordLabel.setText(result.getWord());
            meaningLabel.setText("<html><div style='width: 400px; text-align:center;'>"
                    + String.join(" | ", result.getDefinitions())
                    + "</div></html>");
        });
    }
}
