package ru.ifmo.ctddev;

import ru.ifmo.ctddev.datasets.FileSetManager;
import ru.ifmo.ctddev.features.FeatureManager;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static final String RESOURCES_PATH = "/Users/disoni/Thesis/src/main/resources/datasets";

    public static void main(String[] args) {

        File[] listFiles;
        FileSetManager FSM;
        ArrayList<File> data = new ArrayList<File>();


        try {
            FSM = new FileSetManager(RESOURCES_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        listFiles = FSM.getListFiles();
        for (int i = 0; i < listFiles.length - 1; i++) {
            data.add(listFiles[i + 1]);
        }
        Collections.shuffle(data);

        int count = (int) (data.size() * 0.65);
        List<File> train = data.subList(0, count);
        List<File> test =  data.subList(count + 1, data.size() - 1);


        for (File json : train) {
            FeatureManager fm = new FeatureManager();
            try {
                fm.JsonParse(json);
                break;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}