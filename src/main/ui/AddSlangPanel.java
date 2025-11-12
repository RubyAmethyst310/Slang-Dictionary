package main.ui;

import main.controller.DictionaryController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddSlangPanel extends JPanel {
    private final JPanel meaningListPanel;
    private final List<JTextField> meaningFields = new ArrayList<>();

    public AddSlangPanel(MainMenu mainMenu, DictionaryController controller) {
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
        wordRow.add(new JLabel("Enter new slang word: "));
        JTextField textFieldInput = new JTextField(20);
        wordRow.add(textFieldInput);

        gbc.gridy = 0;
        centerPanel.add(wordRow, gbc);

        meaningListPanel = new JPanel();
        meaningListPanel.setLayout(new BoxLayout(meaningListPanel, BoxLayout.Y_AXIS));
        meaningListPanel.setBackground(new Color(255, 255, 230));
        meaningListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane meaningScroll = new JScrollPane(meaningListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        meaningScroll.setPreferredSize(new Dimension(400, 180));
        meaningScroll.setBorder(BorderFactory.createTitledBorder("Meanings"));

        gbc.gridy++;
        centerPanel.add(meaningScroll, gbc);

        addMeaningField();

        JButton btnAddMeaning = new JButton("+ Add Meaning");
        btnAddMeaning.setFocusable(false);
        btnAddMeaning.addActionListener(e -> {
            addMeaningField();
        });

        gbc.gridy++;
        centerPanel.add(btnAddMeaning, gbc);

        JButton btnSubmit = new JButton("Add New Slang");
        btnSubmit.setFocusable(false);

        gbc.gridy++;
        centerPanel.add(btnSubmit, gbc);

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

            boolean overWrite = false;
            if (controller.getSlangEntry(word) != null) {
                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "Slang already exists! Overwrite?",
                        "Duplicate slang detected!",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );
                if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                    return;
                }
                overWrite = (choice == JOptionPane.YES_OPTION);
            }

            String addedWord = controller.addSlangEntry(word, meanings, overWrite);
            controller.saveSlangData();

            String msg;
            if (overWrite) {
                msg = "Slang word updated successfully!";
            } else if (addedWord.equals(word)) {
                msg = "New slang added successfully!";
            } else {
                msg = "Slang existed, saved as: " + addedWord;
            }

            JOptionPane.showMessageDialog(this, msg);
            textFieldInput.setText("");
            meaningListPanel.removeAll();
            meaningFields.clear();
            addMeaningField();
            revalidate();
            repaint();
        });

        add(centerPanel, BorderLayout.CENTER);
    }

    private void addMeaningField() {
        JTextField meaningField = new JTextField();
        meaningField.setPreferredSize(new Dimension(350, 30));
        meaningField.setMaximumSize(new Dimension(Integer.MAX_VALUE, meaningField.getPreferredSize().height));
        meaningField.setBackground(new Color(255, 255, 230));
        meaningFields.add(meaningField);

        JPanel fieldRow = new JPanel(new BorderLayout());
        fieldRow.add(meaningField,  BorderLayout.CENTER);

        meaningListPanel.add(fieldRow);
        meaningListPanel.add(Box.createVerticalStrut(5));
        meaningListPanel.revalidate();
        meaningListPanel.repaint();
    }
}
