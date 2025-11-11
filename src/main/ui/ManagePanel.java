package main.ui;

import main.controller.DictionaryController;

import javax.swing.*;
import java.awt.*;

public class ManagePanel extends JPanel {

    public ManagePanel(MainMenu mainMenu, DictionaryController controller) {
        setLayout(new BorderLayout());

        JButton btnBack = new JButton("← Back");
        btnBack.setFocusable(false);

        btnBack.addActionListener(e -> mainMenu.showMainMenuPanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


        JButton btnAddSlang = new JButton("Add new Slang");
        btnAddSlang.setFocusable(false);
        btnAddSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddSlang.addActionListener(e -> mainMenu.showAddSlangPanel());

        JButton btnEditSlang = new JButton("Edit Slang");
        btnEditSlang.setFocusable(false);
        btnEditSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditSlang.addActionListener(e -> mainMenu.showEditSlangPanel());

        JButton btnDeleteSlang = new JButton("Delete Slang");
        btnDeleteSlang.setFocusable(false);
        btnDeleteSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDeleteSlang.addActionListener(e -> mainMenu.showDeleteSlangPanel());

        Dimension btnSize = new Dimension(200, 45);
        btnAddSlang.setMaximumSize(btnSize);
        btnEditSlang.setMaximumSize(btnSize);
        btnDeleteSlang.setMaximumSize(btnSize);
        btnAddSlang.setPreferredSize(btnSize);
        btnEditSlang.setPreferredSize(btnSize);
        btnDeleteSlang.setPreferredSize(btnSize);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(btnAddSlang);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btnEditSlang);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btnDeleteSlang);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}
