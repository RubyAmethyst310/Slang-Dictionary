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
                List<String> meanings = new ArrayList<>();
                for (String s : definitionSplit) {
                    s = s.trim();
                    if(!s.isEmpty()) meanings.add(s);
                }
                slangFile.put(slang, meanings);
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
}
