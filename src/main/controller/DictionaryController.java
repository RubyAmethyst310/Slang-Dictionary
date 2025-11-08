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

    public DictionaryController() {
        slangDictionary = new SlangDictionary();
        historyManager = new HistoryManager();
        quizManager = new QuizManager(slangDictionary);
    }

    public void loadSlangData() {
        slangDictionary.loadFromMap(FileUtils.loadSlangFile(dataFilePath));
    }

    public void saveSlangData() {
        FileUtils.saveSlangFile(dataFilePath, FileUtils.loadSlangFile(dataFilePath));
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

    public List<String> getHistory() {
        return historyManager.getHistory();
    }
}
