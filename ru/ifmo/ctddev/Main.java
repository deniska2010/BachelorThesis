package ru.ifmo.ctddev;

import ru.ifmo.ctddev.datasets.*;
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

    private static final String RESOURCES_PATH = "/Users/disoni/Documents/Bachelor/src/main/resources/features";
    private static final String answers = "/answers";
    private static final String targetDir = "src/main/resources";

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
            for (int i = 0; i < listFiles.length; i++) {
                String answer = AnswerReader.read(targetDir + answers + "/" + listFiles[i].getName());
                if (answer.equals("norway")) {
                    dataN.add(listFiles[i]);
                } else {
                    data.add(listFiles[i]);
                }
            }
/*
            FSM = new FileSetManager(RESOURCES_NORWAY_PATH);


            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                dataN.add(listFiles[i]);
            }
            */
            Collections.shuffle(data);
            Collections.shuffle(dataN);
            int countU = (int) (data.size() * 0.75);
            int countN = (int) (dataN.size() * 0.75);
            trainU = data.subList(0, countU);
            testU = data.subList(countU + 1, data.size() - 1);
            trainN = dataN.subList(0, countN);
            testN = dataN.subList(countN + 1, dataN.size() - 1);
            train.addAll(trainU);
            train.addAll(trainN);
            test.addAll(testN);
            test.addAll(testU);
            Collections.shuffle(train);
            Collections.shuffle(test);
            /*
            for (File json : data) {
                FeatureManager fm = new FeatureManager();
                ArrayList<Feature> featureList = fm.JsonParse(json);
                int index = json.getName().indexOf(".");
                String name = json.getName().substring(0, index);
                CSVWriter csvWriter = new CSVWriter();
                csvWriter.write(name, featureList);
                AnswerWriter answerWriter = new AnswerWriter();
                answerWriter.write(name, "NOTnorway");
            }
            for (File json : dataN) {
                FeatureManager fm = new FeatureManager();
                ArrayList<Feature> featureList = fm.JsonParse(json);
                int index = json.getName().indexOf(".");
                String name = json.getName().substring(0, index);
                CSVWriter csvWriter = new CSVWriter();
                csvWriter.write(name, featureList);
                AnswerWriter answerWriter = new AnswerWriter();
                answerWriter.write(name, "norway");
            }
            */
            ARFFConverter2Classes arffConverter2classes = new ARFFConverter2Classes();
            arffConverter2classes.convert(train,"/train");
            ARFFConverter2Classes arffConverter2classes2 = new ARFFConverter2Classes();
            arffConverter2classes2.convert(test,"/test");
            BufferedReader reader = new BufferedReader(
                    new FileReader("src/main/resources/train.arff"));
            BufferedReader reader2 = new BufferedReader(
                    new FileReader("src/main/resources/test.arff"));
            Instances datas = new Instances(reader);
            reader.close();
            datas.setClassIndex(datas.numAttributes() - 1);
            String[] options = {"-U"};
            J48 tree = new J48();         // new instance of tree
            tree.setOptions(options);     // set the options
            tree.buildClassifier(datas);   // build classifier
            System.out.print(tree.binarySplitsTipText());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}