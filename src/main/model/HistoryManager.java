package main.model;

import java.util.List;
import java.util.LinkedList;

import main.util.FileUtils;

public class HistoryManager {
    private LinkedList<String> history;
    private final int MAX_HISTORY_SIZE = 100;
    private final String historyFilePath;

    public HistoryManager(String historyFilePath) {
        this.historyFilePath = historyFilePath;
        this.history = new LinkedList<>(FileUtils.readHistoryFile(historyFilePath));
    }

    public void addHistory(String slang) {
        history.addFirst(slang);
        FileUtils.appendHistory(historyFilePath, slang);

        if (history.size() > MAX_HISTORY_SIZE) {
            history.removeLast();
            FileUtils.saveHistoryFile(historyFilePath, history);
        }
    }

    public List<String> getHistory() {
        return history;
    }

    public void clearHistory() {
        history.clear();
        FileUtils.saveHistoryFile(historyFilePath, history);
    }
}
