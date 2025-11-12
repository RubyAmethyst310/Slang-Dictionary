package main.ui;

import main.controller.DictionaryController;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private DictionaryController controller;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JFrame frame;

    public MainMenu(DictionaryController controller) {
        this.controller = controller;
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    public void createAndShowGUI() {
        frame = new JFrame("Slang Dictionary Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnSearch = new JButton("Search");
        JButton btnManage = new JButton("Manage Words");
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

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>"
                + "<h1>Welcome to Smart Slang Dictionary</h1>"
                + "<p>Choose a function on the menu bar to get started!</p>"
                + "</div></html>");

        welcomeLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        mainPanel.add(welcomeLabel);

        // Add to frame
        frame.add(menuPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // event button
        btnQuiz.addActionListener(e -> System.out.println("Open Quiz Panel"));

        btnManage.addActionListener(e -> showManagePanel());
        btnSearch.addActionListener(e -> showSearchPanel());
        btnRandom.addActionListener(e -> showRandomPanel());
        btnHistory.addActionListener(e -> showHistoryPanel());
        btnReset.addActionListener(e -> showResetPanel());

        // visualize
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showResetPanel() {
        frame.getContentPane().removeAll(); //
        frame.add(new ResetPanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showHistoryPanel() {
        frame.getContentPane().removeAll(); //
        frame.add(new HistoryPanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showRandomPanel() {
        frame.getContentPane().removeAll();
        frame.add(new RandomPanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showSearchPanel() {
        frame.getContentPane().removeAll();
        frame.add(new SearchPanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showSearchBySlangPanel() {
        frame.getContentPane().removeAll();
        frame.add(new SearchBySlangPanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showSearchByDefinitionPanel() {
        frame.getContentPane().removeAll();
        frame.add(new SearchByDefinitionPanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showManagePanel() {
        frame.getContentPane().removeAll();
        frame.add(new ManagePanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showAddSlangPanel() {
        frame.getContentPane().removeAll();
        frame.add(new AddSlangPanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showDeleteSlangPanel() {
        frame.getContentPane().removeAll();
        frame.add(new DeleteSlangPanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showEditSlangPanel() {
        frame.getContentPane().removeAll();
        frame.add(new EditSlangPanel(this, controller), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showMainMenuPanel() {
        frame.getContentPane().removeAll();
        frame.add(menuPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
