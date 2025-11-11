package main.model;

import java.util.*;

public class SlangDictionary {
    private static final Random rand = new Random();
    private HashMap<String, SlangEntry> slangDictionary;

    public SlangDictionary() {
        this.slangDictionary = new HashMap<>();
    }

    public void loadFromMap(HashMap<String, List<String>> map) {
        slangDictionary.clear();
        for (String key : map.keySet()) {
            slangDictionary.put(key, new SlangEntry(key, map.get(key)));
        }
    }

    public HashMap<String, List<String>> exportToMap() {
        HashMap<String, List<String>> map = new HashMap<>();
        for (var entry : slangDictionary.values()) {
            map.put(entry.getWord(), entry.getDefinitions());
        }
        return map;
    }

    public SlangEntry searchByWord(String word) {
        return slangDictionary.get(word);
    }

    public List<SlangEntry> searchByDefinition(String keyWord) {
        List<SlangEntry> result = new ArrayList<>();
        for (SlangEntry entry : slangDictionary.values()) {
            for (String definition : entry.getDefinitions()) {
                if (definition.toLowerCase().contains(keyWord.toLowerCase())) {
                    result.add(entry);
                    break;
                }
            }
        }
        return result;
    }

    public String addSlangEntry(String word, List<String> meanings, boolean overwrite) {
        if (!slangDictionary.containsKey(word)) {
            slangDictionary.put(word, new SlangEntry(word, meanings));
            return word;
        }

        if (overwrite) {
            slangDictionary.put(word, new SlangEntry(word, meanings));
            return word;
        }

        int count = 1;
        String newWord;
        do {
            newWord = word + "(" + count + ")";
            count++;
        } while (slangDictionary.containsKey(newWord));

        slangDictionary.put(newWord, new SlangEntry(newWord, meanings));
        return newWord;
    }

    public boolean editSlang(String word, List<String> newMeanings) {
        if  (!slangDictionary.containsKey(word)) {
            return false;
        }
        slangDictionary.get(word).setDefinitions(newMeanings);
        return true;
    }

    public boolean deleteSlang(String word) {
        if (!slangDictionary.containsKey(word)) {
            return false;
        }
        slangDictionary.remove(word);
        return true;
    }

    public SlangEntry getRandomSlangEntry() {
        if (slangDictionary.isEmpty()) return null;
        List<SlangEntry> list = new ArrayList<>(slangDictionary.values());
        return list.get(rand.nextInt(list.size()));
    }

    public int slangDictionarySize() {
        return slangDictionary.size();
    }
}
