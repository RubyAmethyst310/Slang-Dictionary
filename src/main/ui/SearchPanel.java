package main.ui;

import main.controller.DictionaryController;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    public SearchPanel(MainMenu mainMenu, DictionaryController controller) {
        setLayout(new BorderLayout());

        JButton btnBack = new JButton("← Back");
        btnBack.setFocusable(false);

        btnBack.addActionListener(e -> mainMenu.showMainMenuPanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("<html><div style='text-align:center;'>"
                + "<h2>Click the button below for choosing the way to search</h2>"
                + "</div></html>", SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnSearchBySlang = new JButton("Search By Slang");
        btnSearchBySlang.setFocusable(false);
        btnSearchBySlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearchBySlang.addActionListener(e -> mainMenu.showSearchBySlangPanel());

        JButton btnSearchByDefinition = new JButton("Search By Definition");
        btnSearchByDefinition.setFocusable(false);
        btnSearchByDefinition.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearchByDefinition.addActionListener(e -> mainMenu.showSearchByDefinitionPanel());

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        Dimension btnSize = new Dimension(180, 40);
        btnSearchBySlang.setPreferredSize(btnSize);
        btnSearchByDefinition.setPreferredSize(btnSize);
        buttonRow.add(btnSearchBySlang);
        buttonRow.add(btnSearchByDefinition);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonRow);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}
