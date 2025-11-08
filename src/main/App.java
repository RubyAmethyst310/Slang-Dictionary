package main;

import main.util.FileUtils;
import java.util.*;

import main.controller.DictionaryController;
import main.ui.MainMenu;

public class App {
    public static void main(String[] args) {
//        HashMap<String, List<String>> slangMap = FileUtils.loadSlangFile("data/slang.txt");
//
//        System.out.println("Tổng số slang: " + slangMap.size());
//        System.out.println("Ví dụ slang: OMG -> " + slangMap.get("OMG"));
//
//        // Ghi thử ra file
//        FileUtils.saveSlangFile("data/slang_output.txt", slangMap);

        DictionaryController dictController = new DictionaryController();
        new MainMenu(dictController);
    }
}