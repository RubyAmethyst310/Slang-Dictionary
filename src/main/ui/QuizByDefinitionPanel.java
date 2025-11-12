package main.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import main.controller.DictionaryController;
import main.model.Quiz;

public class QuizByDefinitionPanel extends JPanel {
    private JLabel definitionLabel;
    private JButton[] answerButtons = new JButton[4];
    private Quiz currentQuiz;

    private final DictionaryController controller;

    public QuizByDefinitionPanel(MainMenu mainMenu, DictionaryController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JButton btnBack = new JButton("← Back");
        btnBack.setFocusable(false);
        btnBack.addActionListener(e -> mainMenu.showQuizPanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        definitionLabel = new JLabel("", SwingConstants.CENTER);
        definitionLabel.setFont(new Font("Arial", Font.BOLD, 32));
        definitionLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        add(definitionLabel, BorderLayout.CENTER);

        JPanel answerPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        answerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        for (int i = 0; i < answerButtons.length; i++) {
            JButton btn = new JButton();
            btn.setFont(new Font("Arial", Font.PLAIN, 20));
            btn.setFocusable(false);
            int index = i;
            btn.addActionListener(e -> {
                checkAnswer(answerButtons[index].getText());
            });
            answerButtons[i] = btn;
            answerPanel.add(btn);
        }

        add(answerPanel, BorderLayout.SOUTH);
        loadQuiz();
    }

    private void loadQuiz() {
        currentQuiz = controller.generateQuiz(false);
        if (currentQuiz == null) return;

        definitionLabel.setText("<html><center>" + currentQuiz.getQuestion() + "</center></html>");
        List<String> options = currentQuiz.getOptions();
        for (int i = 0; i < options.size(); i++) {
            answerButtons[i].setText(options.get(i));
        }
    }

    private void checkAnswer(String selected) {
        boolean correct = selected.equals(currentQuiz.getAnswer());

        if (correct) {
            JOptionPane.showMessageDialog(
                    this,
                    "Correct!",
                    "Result",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else {
            JOptionPane.showMessageDialog(
                    this,
                    "Wrong!\nCorrect answer is:\n" + currentQuiz.getAnswer(),
                    "Result",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        loadQuiz();
    }
}
