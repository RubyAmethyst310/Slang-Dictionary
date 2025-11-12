package main.ui;

import javax.swing.*;
import java.awt.*;

import main.controller.DictionaryController;

public class QuizPanel extends JPanel {

    public QuizPanel(MainMenu mainMenu, DictionaryController controller) {
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
                + "<h2>Click the button below for choosing the type of quiz</h2>"
                + "</div></html>", SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnQuizBySlang = new JButton("Quiz By Slang");
        btnQuizBySlang.setFocusable(false);
        btnQuizBySlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuizBySlang.addActionListener(e -> mainMenu.showQuizBySlangPanel());

        JButton btnQuizByDefinition = new JButton("Quiz By Definition");
        btnQuizByDefinition.setFocusable(false);
        btnQuizByDefinition.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuizByDefinition.addActionListener(e -> mainMenu.showQuizByDefinitionPanel());

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        Dimension btnSize = new Dimension(180, 40);
        btnQuizBySlang.setPreferredSize(btnSize);
        btnQuizByDefinition.setPreferredSize(btnSize);
        buttonRow.add(btnQuizBySlang);
        buttonRow.add(btnQuizByDefinition);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonRow);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}
