package main.model;

import java.util.*;

public class QuizManager {
    private final SlangDictionary slangDictionary;
    private final Random rand = new Random();

    public QuizManager(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;
    }

    public Quiz quizBySlang() {
        SlangEntry correct = slangDictionary.getRandomSlangEntry();
        if (correct == null) {
            return null;
        }

        Set<String> options = new HashSet<>();
        options.add(String.join(" | ",correct.getDefinitions()));

        while (options.size() < 4) {
            SlangEntry randomEntry = slangDictionary.getRandomSlangEntry();
            if (randomEntry != null) {
                String fake = String.join(" | ", randomEntry.getDefinitions());
                if(!fake.equals(String.join(" | ",correct.getDefinitions()))) {
                    options.add(fake);
                }
            }
        }

        List<String> answerList = new ArrayList<>(options);
        Collections.shuffle(answerList);

        return new Quiz(
                correct.getWord(),
                answerList,
                String.join(" | ", correct.getDefinitions())
        );
    }

    public Quiz quizByDefinitions() {
        SlangEntry correct = slangDictionary.getRandomSlangEntry();
        if (correct == null) {
            return null;
        }
        Set<String> options = new HashSet<>();
        options.add(correct.getWord());

        while (options.size() < 4) {
            SlangEntry randomEntry = slangDictionary.getRandomSlangEntry();
            if (randomEntry != null) {
                options.add(randomEntry.getWord());
            }
        }

        List<String> answerList = new ArrayList<>(options);
        Collections.shuffle(answerList);

        return new Quiz(
                String.join(" | ", correct.getDefinitions()),
                answerList,
                correct.getWord()
        );
    }
}
