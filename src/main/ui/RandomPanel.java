package main.ui;

import main.controller.DictionaryController;
import main.model.SlangEntry;

import javax.swing.*;
import java.awt.*;

public class RandomPanel extends JPanel {
    private JLabel wordLabel;
    private JLabel meaningLabel;

    public RandomPanel(MainMenu mainMenu, DictionaryController controller) {
        setLayout(new BorderLayout());

        // top
        JButton btnBack = new JButton("← Back");
        btnBack.setFocusable(false);
        btnBack.addActionListener(e -> mainMenu.showMainMenuPanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        // center
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        // center top
        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>"
                + "<p>Here is today's random word</p>"
                + "<p>Click the 'Random button' for a new one!</p>"
                + "</div></html>");

        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel, gbc);

        // center
        SlangEntry randWord = controller.randomSlangEntry();

        wordLabel = new JLabel(randWord.getWord(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 36));
        wordLabel.setForeground(new Color(0, 128, 0)); // dark green
        wordLabel.setAlignmentX(CENTER_ALIGNMENT);
        gbc.gridy++;
        centerPanel.add(wordLabel, gbc);

        String meaningText = String.join(" | ", randWord.getDefinitions());

        meaningLabel = new JLabel(
                "<html><div style='width: 400px; text-align: center;'>"
                        + meaningText +
                        "</div></html>"
        );
        meaningLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        meaningLabel.setForeground(new Color(0, 191, 255));
        meaningLabel.setAlignmentX(CENTER_ALIGNMENT);
        gbc.gridy++;
        centerPanel.add(meaningLabel, gbc);

        JButton btnRandom = new JButton("Random");
        btnRandom.setAlignmentX(CENTER_ALIGNMENT);
        btnRandom.addActionListener(e -> {
            SlangEntry newWord = controller.randomSlangEntry();
            wordLabel.setText(newWord.getWord());
            meaningLabel.setText(
                    "<html><div style='width: 500px; text-align: center;'>"
                            + String.join(" | ", newWord.getDefinitions())
                            + "</div></html>"
            );
        });
        gbc.gridy++;
        centerPanel.add(btnRandom, gbc);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add(centerPanel, new GridBagConstraints());

        add(wrapper, BorderLayout.CENTER);
    }
}
