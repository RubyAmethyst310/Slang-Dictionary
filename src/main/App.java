package main;

import main.util.FileUtils;
import java.util.*;

import main.controller.DictionaryController;
import main.ui.MainMenu;

public class App {
    public static void main(String[] args) {
        DictionaryController dictController = new DictionaryController();
        dictController.loadSlangData();
        new MainMenu(dictController);
    }
}