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
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // center top
        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>"
                + "<p>Here is today's random word</p>"
                + "<p>Click the 'Random button' for a new one!</p>"
                + "</div></html>");

        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // center
        SlangEntry randWord = controller.randomSlangEntry();

        wordLabel = new JLabel(randWord.getWord(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 36));
        wordLabel.setForeground(new Color(0, 128, 0)); // dark green
        wordLabel.setAlignmentX(CENTER_ALIGNMENT);
        centerPanel.add(wordLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        String meaningText = String.join(" | ", randWord.getDefinitions());

        meaningLabel = new JLabel(meaningText, SwingConstants.CENTER);
        meaningLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        meaningLabel.setForeground(new Color(0, 191, 255));
        meaningLabel.setAlignmentX(CENTER_ALIGNMENT);
        centerPanel.add(meaningLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton btnRandom = new JButton("Random");
        btnRandom.setAlignmentX(CENTER_ALIGNMENT);
        btnRandom.addActionListener(e -> {
            SlangEntry newWord = controller.randomSlangEntry();
            wordLabel.setText(newWord.getWord());
            meaningLabel.setText(String.join(" | ", newWord.getDefinitions()));
        });
        centerPanel.add(btnRandom);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add(centerPanel, new GridBagConstraints());

        add(wrapper, BorderLayout.CENTER);
    }
}
