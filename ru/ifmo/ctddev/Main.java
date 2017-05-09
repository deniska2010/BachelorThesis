package ru.ifmo.ctddev;

import ru.ifmo.ctddev.datasets.*;
import ru.ifmo.ctddev.features.Feature;
import ru.ifmo.ctddev.features.FeatureManager;
import ru.ifmo.ctddev.ml.ARFFConverter2Classes;
import ru.ifmo.ctddev.ml.ARFFConverter6CLasses;
import ru.ifmo.ctddev.ml.DatasetInformation;
import weka.core.converters.ConverterUtils.DataSource;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.nio.file.*;
import java.nio.charset.*;

import weka.core.Instances;
import weka.classifiers.Evaluation;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;


public class Main {

    private static final String RESOURCES_PATH = "/Users/disoni/Documents/Bachelor/src/main/resources/features/norway";
    private static final String answers = "/answers";
    private static final String targetDir = "src/main/resources";
    private static String FILE_NAME = "delLine_all.json";

    public static void main(String[] args) {

        File[] listFiles;
        FileSetManager FSM;
        ArrayList<File> data2 = new ArrayList<File>();
        ArrayList<File> data4 = new ArrayList<File>();
        ArrayList<File> data8 = new ArrayList<File>();
        ArrayList<File> data10 = new ArrayList<File>();
        ArrayList<File> data6 = new ArrayList<File>();
        ArrayList<File> data0 = new ArrayList<File>();
        List<File> train = new ArrayList<>();
        List<File> test = new ArrayList<>();


        try {
            FSM = new FileSetManager(RESOURCES_PATH);
            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                String answer = listFiles[i].getName();
                if (answer.contains("_2_")) {
                    data2.add(listFiles[i]);
                    AnswerWriter answerWriter = new AnswerWriter();
                    answerWriter.write(answer, "2");
                } else {
                    if (answer.contains("_4_")) {
                        data4.add(listFiles[i]);
                        AnswerWriter answerWriter = new AnswerWriter();
                        answerWriter.write(answer, "4");
                    } else {
                        if (answer.contains("_6_")) {
                            data6.add(listFiles[i]);
                            AnswerWriter answerWriter = new AnswerWriter();
                            answerWriter.write(answer, "6");
                        } else {
                            if (answer.contains("_8_")) {
                                data8.add(listFiles[i]);
                                AnswerWriter answerWriter = new AnswerWriter();
                                answerWriter.write(answer, "8");
                            } else {
                                if (answer.contains("_10_")) {
                                    data10.add(listFiles[i]);
                                    AnswerWriter answerWriter = new AnswerWriter();
                                    answerWriter.write(answer, "1");
                                } else {
                                    if (answer.contains("C") || answer.contains("R") || answer.contains("RC")) {
                                        data0.add(listFiles[i]);
                                        AnswerWriter answerWriter = new AnswerWriter();
                                        answerWriter.write(answer, "0");
                                    }
                                }
                            }
                        }
                    }
                }
            }
/*
            FSM = new FileSetManager(RESOURCES_NORWAY_PATH);


            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                dataN.add(listFiles[i]);
            }
            */
            int count2 = (int) (data2.size() * 0.5);
            int count4 = (int) (data4.size() * 0.5);
            int count6 = (int) (data6.size() * 0.5);
            int count8 = (int) (data8.size() * 0.5);
            int count10 = (int) (data10.size() * 0.5);
            int count0 = (int) (data0.size() * 0.5);
            train.addAll(data2.subList(0, count2));
            test.addAll(data2.subList(count2 + 1, data2.size() - 1));
            train.addAll(data4.subList(0, count4));
            test.addAll(data4.subList(count4 + 1, data4.size() - 1));
            train.addAll(data6.subList(0, count6));
            test.addAll(data6.subList(count6 + 1, data6.size() - 1));
            train.addAll(data8.subList(0, count8));
            test.addAll(data8.subList(count8 + 1, data8.size() - 1));
            train.addAll(data10.subList(0, count10));
            test.addAll(data10.subList(count10 + 1, data10.size() - 1));
            train.addAll(data0.subList(0, count0));
            test.addAll(data0.subList(count0 + 1, data0.size() - 1));

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
            System.out.print("ura");
            String json = "";
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
            for(String line: lines){
                json += line;
            }
            System.out.print("ura");
            FeatureManager fm = new FeatureManager();
            ArrayList<Feature> featureList = fm.JsonParse(json);
            int index = FILE_NAME.indexOf(".");
            String name = FILE_NAME.substring(0, index);
            CSVWriter csvWriter = new CSVWriter();
            csvWriter.write(name, featureList);
            /*
            ARFFConverter6CLasses arffConverter6CLasses = new ARFFConverter6CLasses();
            arffConverter6CLasses.convert(train, "/train");
            ARFFConverter6CLasses arffConverter6CLasses2 = new ARFFConverter6CLasses();
            arffConverter6CLasses2.convert(test, "/test");

            DataSource source = new DataSource("src/main/resources/train.arff");
            Instances data = source.getDataSet();
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }
            DataSource source1 = new DataSource("src/main/resources/test.arff");
            Instances data1 = source1.getDataSet();
            if (data1.classIndex() == -1) {
                data1.setClassIndex(data1.numAttributes() - 1);
            }
            Classifier cls = new J48();
            cls.buildClassifier(data);
            // evaluate classifier and print some statistics
            Evaluation eval = new Evaluation(data);
            eval.evaluateModel(cls, data1);
            System.out.println(eval.toSummaryString("\nResults\n======\n", false));
            */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}