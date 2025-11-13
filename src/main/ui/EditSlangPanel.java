package main.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import main.controller.DictionaryController;
import main.model.SlangEntry;

public class EditSlangPanel extends JPanel {
    private final JPanel meaningListPanel;
    private final List<JTextField> meaningFields = new ArrayList<>();

    public EditSlangPanel(MainMenu mainMenu, DictionaryController controller) {
        setLayout(new BorderLayout());

        JButton btnBack = new JButton("← Back");
        btnBack.setFocusable(false);
        btnBack.addActionListener(e -> mainMenu.showManagePanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        // center top
        JPanel wordRow = new JPanel(new FlowLayout());
        wordRow.add(new JLabel("Enter a slang word: "));
        JTextField textFieldInput = new JTextField(20);
        JButton btnEdit = new JButton("Edit");
        wordRow.add(textFieldInput);
        wordRow.add(btnEdit);

        gbc.gridy = 0;
        centerPanel.add(wordRow, gbc);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        gbc.gridy++;
        centerPanel.add(errorLabel, gbc);

        meaningListPanel = new JPanel();
        meaningListPanel.setLayout(new BoxLayout(meaningListPanel, BoxLayout.Y_AXIS));
        meaningListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane meaningScroll = new JScrollPane(meaningListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        meaningScroll.setPreferredSize(new Dimension(400, 180));
        meaningScroll.setBorder(BorderFactory.createTitledBorder("Meanings"));

        gbc.gridy++;
        centerPanel.add(meaningScroll, gbc);

        JButton btnAddMeaning = new JButton("+ Add Meaning");
        btnAddMeaning.setFocusable(false);

        gbc.gridy++;
        centerPanel.add(btnAddMeaning, gbc);

        JButton btnSubmit = new JButton("Save Change");
        btnSubmit.setFocusable(false);

        gbc.gridy++;
        centerPanel.add(btnSubmit, gbc);

        add(centerPanel, BorderLayout.CENTER);

        btnEdit.addActionListener(e -> {
            String word = textFieldInput.getText().trim();
            if (word.isEmpty()) {
                errorLabel.setText("Please enter a slang word");
                clearMeaningList();
                return;
            }

            SlangEntry entry = controller.getSlangEntry(word);
            if (entry == null){
                errorLabel.setText("This slang word does not exist");
                clearMeaningList();
                return;
            }

            errorLabel.setText("");
            clearMeaningList();

            for(String m : entry.getDefinitions()){
                addMeaningField(m);
            }
        });

        btnAddMeaning.addActionListener(e -> {addMeaningField("");});

        btnSubmit.addActionListener(e -> {
            String word = textFieldInput.getText().trim();
            if (word.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a word");
                return;
            }

            List<String> meanings = new ArrayList<>();
            for (JTextField tf : meaningFields) {
                String m = tf.getText().trim();
                if (!m.isEmpty()) meanings.add(m);
            }

            if (meanings.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter at least one meaning");
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure to save changes?",
                    "Confirm Edit",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice != JOptionPane.YES_OPTION) return;

            controller.addSlangEntry(word, meanings, true);
            controller.saveSlangData();

            JOptionPane.showMessageDialog(this, "Slang word updated successfully!");
            textFieldInput.setText("");
            clearMeaningList();
        });
    }

    private void addMeaningField(String meaning) {
        JTextField meaningField = new JTextField(meaning);
        meaningField.setPreferredSize(new Dimension(350, 30));
        meaningField.setMaximumSize(new Dimension(350, 30));
        meaningField.setMinimumSize(new Dimension(350, 30));
        meaningField.setBackground(new Color(255, 255, 230));
        meaningFields.add(meaningField);

        JPanel fieldRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        fieldRow.setMaximumSize(new Dimension(350, 35));
        fieldRow.add(meaningField);

        meaningListPanel.add(fieldRow);
        meaningListPanel.add(Box.createVerticalStrut(5));
        meaningListPanel.revalidate();
        meaningListPanel.repaint();
    }

    private void clearMeaningList() {
        meaningListPanel.removeAll();
        meaningFields.clear();
        meaningListPanel.revalidate();
        meaningListPanel.repaint();
    }
}
