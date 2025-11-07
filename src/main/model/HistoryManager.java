package main.model;

import java.util.List;
import java.util.LinkedList;

public class HistoryManager {
    private LinkedList<String> history;
    private final int MAX_HISTORY_SIZE = 100;

    public HistoryManager() {
        history = new LinkedList<>();
    }

    public void addHistory(String slang) {
        history.addFirst(slang);
        if (history.size() > MAX_HISTORY_SIZE) {
            history.removeLast();
        }
    }

    public List<String> getHistory() {
        return history;
    }

    public void clearHistory() {
        history.clear();
    }
}
