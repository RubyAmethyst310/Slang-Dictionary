package main.controller;

import java.util.List;

import main.model.*;
import main.util.FileUtils;

public class DictionaryController {
    private SlangDictionary slangDictionary;
    private HistoryManager historyManager;
    private QuizManager quizManager;
    private final String dataFilePath = "data/slang.txt";
    private final String backupFilePath = "data/slang_backup.txt";
    private final String historyFilePath = "data/slang_history.txt";

    public DictionaryController() {
        slangDictionary = new SlangDictionary();
        historyManager = new HistoryManager(historyFilePath);
        quizManager = new QuizManager(slangDictionary);
    }

    public void loadSlangData() {
        slangDictionary.loadFromMap(FileUtils.loadSlangFile(dataFilePath));
    }

    public void saveSlangData() {
        FileUtils.saveSlangFile(dataFilePath, slangDictionary.exportToMap());
    }

    public SlangEntry searchSlangEntry(String word) {
        SlangEntry result = slangDictionary.searchByWord(word);
        if (result != null) historyManager.addHistory(word);
        return result;
    }

    public List<SlangEntry> searchDefinition(String keyword) {
        return slangDictionary.searchByDefinition(keyword);
    }

    public String addSlangEntry(String word, List<String> meanings, boolean overwrite) {
        String added = slangDictionary.addSlangEntry(word, meanings, overwrite);
        saveSlangData();
        return added;
    }

    public boolean editSlangEntry(String word, List<String> meanings) {
        boolean ok = slangDictionary.editSlang(word, meanings);
        if (ok) saveSlangData();
        return ok;
    }

    public boolean deleteSlangEntry(String word) {
        boolean ok = slangDictionary.deleteSlang(word);
        if (ok) saveSlangData();
        return ok;
    }

    public void resetData(){
        FileUtils.resetToOriginal(backupFilePath, dataFilePath);
        loadSlangData();
    }

    public Quiz generateQuiz(boolean quizBySlang) {
        return quizBySlang ? quizManager.quizBySlang() : quizManager.quizByDefinitions();
    }

    public SlangEntry getSlangEntry(String word) {
        return slangDictionary.searchByWord(word);
    }

    public List<String> getHistory() {
        return historyManager.getHistory();
    }

    public void clearHistory() {
        historyManager.clearHistory();
    }
}
