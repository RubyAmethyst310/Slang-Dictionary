package main.ui;

import javax.swing.*;
import java.awt.*;

import main.controller.DictionaryController;
import main.model.SlangEntry;

public class DeleteSlangPanel extends JPanel {

    public DeleteSlangPanel(MainMenu mainMenu, DictionaryController controller) {
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
        JPanel deleteRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel labelInput = new JLabel("Enter a slang to delete: ");
        JTextField textFieldInput = new JTextField(20);
        JButton btnDelete =  new JButton("Delete");

        deleteRowPanel.add(labelInput);
        deleteRowPanel.add(textFieldInput);
        deleteRowPanel.add(btnDelete);

        gbc.gridy = 0;
        centerPanel.add(deleteRowPanel, gbc);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        gbc.gridy = 1;
        centerPanel.add(errorLabel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        btnDelete.addActionListener(e -> {
            String word = textFieldInput.getText().trim();
            if (word.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a word");
                return;
            }

            SlangEntry result = controller.getSlangEntry(word);

            if (result == null) {
                errorLabel.setText("This slang word is not in the dictionary! Please try another one.");
                return;
            }

            errorLabel.setText("");

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure to delete the Slang?",
                    "Delete Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteSlangEntry(word);
                JOptionPane.showMessageDialog(
                        this,
                        "Delete successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}
