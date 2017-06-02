package ru.ifmo.ctddev;

import ru.ifmo.ctddev.datasets.*;
import ru.ifmo.ctddev.features.*;
import ru.ifmo.ctddev.ml.ARFFConverter6CLasses;
import ru.ifmo.ctddev.ml.MainCLasses;
import ru.ifmo.ctddev.ml.NorwayClasses;
import ru.ifmo.ctddev.ml.UsualOrNorwayClasses;
import ru.ifmo.ctddev.utils.ExcelParser;
import weka.core.converters.ConverterUtils.DataSource;


import java.io.*;
import java.util.*;

import weka.core.Instances;
import weka.classifiers.Evaluation;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;


public class Main {

   // private static final String targetDir = "src/main/resources/datasets/gulfstream";

    public static void main(String[] args) {


        try {


            // ExcelParser.parse("testfile.xlsx");
            /*
            FileSetManager FSM;
            FSM = new FileSetManager(targetDir);


            File[] listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                FeatureManager fm = new FeatureManager();
                ArrayList<Feature> featureList = fm.JsonParse(listFiles[i]);
                int index = listFiles[i].getName().indexOf(".");
                String name = listFiles[i].getName().substring(0, index);
                CSVWriter csvWriter = new CSVWriter();
                csvWriter.write(name, featureList);
            }
            */
/*
            NorwayClasses norwayClasses = new NorwayClasses();
            norwayClasses.learn();
            */
            /*
            UsualOrNorwayClasses usualOrNorwayClasses = new UsualOrNorwayClasses();
            usualOrNorwayClasses.learn();
            */
            MainCLasses mainCLasses = new MainCLasses();
            mainCLasses.learn();


        } /*catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } */ catch (Exception e) {
            e.printStackTrace();
        }
    }
}