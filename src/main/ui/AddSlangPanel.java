package main.ui;

import main.controller.DictionaryController;

import javax.swing.*;
import java.awt.*;

public class AddSlangPanel extends JPanel {

    public AddSlangPanel(MainMenu mainMenu, DictionaryController controller) {
        setLayout(new BorderLayout());

        JButton btnBack = new JButton("← Back");
        btnBack.setFocusable(false);

        btnBack.addActionListener(e -> mainMenu.showManagePanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("<html><div style='text-align:center;'>"
                + "<h2>Click the button below to reset the Slang Dictionary.</h2>"
                + "<p>Make sure that you really want to reset.</p>"
                + "</div></html>", SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnReset = new JButton("Reset");
        btnReset.setFocusable(false);
        btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnReset.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure to reset the Slang Dictionary?",
                    "Reset Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                controller.resetData();
                JOptionPane.showMessageDialog(
                        this,
                        "Reset successfull",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btnReset);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}
