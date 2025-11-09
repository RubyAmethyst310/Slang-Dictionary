package main.util;

import java.io.*;
import java.util.*;

public class FileUtils {
    public static HashMap<String, List<String>> loadSlangFile(String filePath) {
        HashMap<String, List<String>> slangFile = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (!line.contains("`")) continue;

                String[] split = line.split("`", 2);
                String slang =  split[0].trim();
                String definition = split[1].trim();

                String[] definitionSplit = definition.split("\\|");
                List<String> newMeanings = new ArrayList<>();
                for (String s : definitionSplit) {
                    s = s.trim();
                    if(!s.isEmpty()) newMeanings.add(s);
                }

                if (slangFile.containsKey(slang)) {
                    List<String> oldMeanings = slangFile.get(slang);
                    for (String meaning : newMeanings) {
                        if(!oldMeanings.contains(meaning)) oldMeanings.add(meaning);
                    }
                }
                else {
                    slangFile.put(slang, newMeanings);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return slangFile;
    }

    public static void saveSlangFile(String filePath, HashMap<String, List<String>> slangFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            bw.write("Slang`Meaning");
            bw.newLine();

            Set<Map.Entry<String, List<String>>> entries = slangFile.entrySet();

            for (Map.Entry<String, List<String>> entry : entries) {
                String slang = entry.getKey();
                List<String> meanings = entry.getValue();
                String defs = String.join("| ", meanings);
                bw.write(slang + "`" + defs);
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendSlang(String filePath, String slang, List<String> meanings){
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)))){
            if(meanings.isEmpty() || meanings == null){
                bw.write(slang + "`");
            }
            else {
                bw.write(slang + "`" + String.join("| ", meanings));
            }
            bw.newLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetToOriginal(String originalFilePath, String workingFilePath) {
        try (InputStream in = new FileInputStream(originalFilePath);
             OutputStream out = new FileOutputStream(workingFilePath)) {

            in.transferTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readHistoryFile(String historyPath) {
        List<String> history = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(historyPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    history.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return history;
    }

    public static void saveHistoryFile(String historyPath, List<String> history) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(historyPath))) {
            for (String slang : history) {
                bw.write(slang);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendHistory(String historyPath, String slang) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(historyPath, true))) {
            bw.write(slang);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
