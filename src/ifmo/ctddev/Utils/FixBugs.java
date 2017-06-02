package ru.ifmo.ctddev.utils;

import ru.ifmo.ctddev.features.Feature;

import ru.ifmo.ctddev.datasets.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FixBugs {

    public static void main(String[] args) {

        String RESOURCES_PATH = "/Users/disoni/Documents/Bachelor/src/main/resources/features/delLine";
        try {
            FileSetManager FSM = new FileSetManager(RESOURCES_PATH);


            File[] listFiles = FSM.getListFiles();
            for (File file : listFiles) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    List<String[]> records = new ArrayList<>();
                    br.readLine();
                    while ((line = br.readLine()) != null) {
                        String[] ss = line.split(",");
                        String s = ss[0].replace("Amount of Locations", "AmountOfLocations");
                        ss[0] = s;
                        records.add(ss);
                    }

                    br.close();

                    BufferedWriter bw = null;
                    File file1 = new File("/Users/disoni/Documents/Bachelor/src/main/resources/features/delLine/" + file.getName());
                    if (!file1.exists()) {
                        file1.createNewFile();
                    }

                    bw = new BufferedWriter(new FileWriter("/Users/disoni/Documents/Bachelor/src/main/resources/features/delLine/" + file.getName(), false));

                    bw.write(Feature.getHeadOfCSV());
                    bw.newLine();
                    for (String[] array : records) {
                        bw.write(String.join(",",array));
                        bw.newLine();
                    }
                    bw.flush();
                    bw.close();

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
