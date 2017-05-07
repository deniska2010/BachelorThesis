package ru.ifmo.ctddev;

import ru.ifmo.ctddev.datasets.CSVWriter;
import ru.ifmo.ctddev.datasets.FileSetManager;
import ru.ifmo.ctddev.features.Feature;
import ru.ifmo.ctddev.features.FeatureManager;
import ru.ifmo.ctddev.ml.ARFFConverter2Classes;
import ru.ifmo.ctddev.ml.DatasetInformation;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import weka.classifiers.trees.J48;
import weka.core.Instances;


public class Main {

    private static final String RESOURCES_PATH = "/Users/disoni/Thesis/src/main/resources/datasets/not";
    private static final String RESOURCES_NORWAY_PATH = "/Users/disoni/Thesis/src/main/resources/datasets/norway";

    public static void main(String[] args) {

        File[] listFiles;
        FileSetManager FSM;
        ArrayList<File> data = new ArrayList<File>();
        ArrayList<File> dataN = new ArrayList<File>();
        List<File> trainU;
        List<File> testU;
        List<File> trainN;
        List<File> testN;
        List<File> train = new ArrayList<File>();
        List<File> test = new ArrayList<File>();


        try {

            FSM = new FileSetManager(RESOURCES_PATH);


            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length - 1; i++) {
                data.add(listFiles[i + 1]);
            }

            FSM = new FileSetManager(RESOURCES_NORWAY_PATH);


            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                dataN.add(listFiles[i]);
            }
            Collections.shuffle(data);
            Collections.shuffle(dataN);
            int countU = (int) (data.size() * 0.5);
            int countN = (int) (dataN.size() * 0.5);
            trainU = data.subList(0, countU);
            testU = data.subList(countU + 1, data.size() - 1);
            trainN = dataN.subList(0, countN);
            testN = dataN.subList(countN + 1, dataN.size() - 1);
            train.addAll(trainU);
            train.addAll(trainN);
            test.addAll(testN);
            test.addAll(testU);
            for (File json : train) {
                FeatureManager fm = new FeatureManager();
                ArrayList<Feature> featureList = fm.JsonParse(json);
                int index = json.getName().indexOf(".");
                String name = json.getName().substring(0, index);
                String value = "not";
                if (json.getAbsolutePath().contains("norway")) {
                    value = "norway";
                }
                CSVWriter csvWriter = new CSVWriter();
                csvWriter.write(name +"_"+ value, featureList);
            }
            ARFFConverter2Classes arffConverter2classes = new ARFFConverter2Classes();
            arffConverter2classes.convert();
            BufferedReader reader = new BufferedReader(
                    new FileReader("/Users/disoni/Thesis/src/main/resources/train.arff"));
            Instances datas = new Instances(reader);
            reader.close();
            datas.setClassIndex(datas.numAttributes() - 1);

            String[] options = {"-U"};
            J48 tree = new J48();         // new instance of tree
            tree.setOptions(options);     // set the options
            tree.buildClassifier(datas);   // build classifier

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}