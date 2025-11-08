package main.ui;

import main.controller.DictionaryController;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private DictionaryController controller;

    public MainMenu(DictionaryController controller) {
        this.controller = controller;

        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });;
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Slang Dictionary Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnSearch = new JButton("Search");
        JButton btnManage =  new JButton("Manage Words");
        JButton btnRandom = new JButton("Random");
        JButton btnQuiz = new JButton("Quiz");
        JButton btnHistory = new JButton("History");
        JButton btnReset = new JButton("Reset");

        btnSearch.setFocusable(false);
        btnManage.setFocusable(false);
        btnRandom.setFocusable(false);
        btnQuiz.setFocusable(false);
        btnHistory.setFocusable(false);
        btnReset.setFocusable(false);

        menuPanel.add(btnSearch);
        menuPanel.add(btnManage);
        menuPanel.add(btnRandom);
        menuPanel.add(btnQuiz);
        menuPanel.add(btnHistory);
        menuPanel.add(btnReset);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>"
                + "<h1>Welcome to Smart Slang Dictionary</h1>"
                + "<p>Choose a function on the menu bar to get started!</p>"
                + "</div></html>");

        welcomeLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        centerPanel.add(welcomeLabel);

        // Add to frame
        frame.add(menuPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        // event button
        btnSearch.addActionListener(e -> System.out.println("Open Search Panel"));
        btnManage.addActionListener(e -> System.out.println("Open Manage Words Panel"));
        btnHistory.addActionListener(e -> System.out.println("Open History Panel"));
        btnRandom.addActionListener(e -> System.out.println("Random a slang word"));
        btnReset.addActionListener(e -> System.out.println("Reset dictionary to original"));
        btnQuiz.addActionListener(e -> System.out.println("Open Quiz Panel"));

        // visualize
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
