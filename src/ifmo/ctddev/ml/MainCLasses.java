package ru.ifmo.ctddev.ml;

import ru.ifmo.ctddev.datasets.AnswerWriter;
import ru.ifmo.ctddev.datasets.FileSetManager;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainCLasses {

    private final String RESOURCES_NORWAY_PATH = "/Users/disoni/Documents/Bachelor/src/main/resources/features/norway";
    private static final String RESOURCES_PATH_AUSTRIA = "/Users/disoni/Documents/Bachelor/src/main/resources/features/austria";
    private static final String RESOURCES_PATH_GULFSTREAM = "/Users/disoni/Documents/Bachelor/src/main/resources/features/gulfstream";
    private static final String RESOURCES_PATH_DELLINE = "/Users/disoni/Documents/Bachelor/src/main/resources/features/delLine";

    public void learn() {

        try {
            List<File> train = new ArrayList<>();
            List<File> test = new ArrayList<>();

            FileSetManager FSM;
            File[] listFiles;
            ArrayList<File> dataG = new ArrayList<File>();
            ArrayList<File> dataN = new ArrayList<File>();
            ArrayList<File> dataA = new ArrayList<File>();
            ArrayList<File> dataD = new ArrayList<File>();

            FSM = new FileSetManager(RESOURCES_NORWAY_PATH);
            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                dataN.add(listFiles[i]);
                String answer = listFiles[i].getName();
                AnswerWriter answerWriter = new AnswerWriter();
                answerWriter.write(answer, "norway");
            }

            FSM = new FileSetManager(RESOURCES_PATH_AUSTRIA);
            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                dataA.add(listFiles[i]);
                String answer = listFiles[i].getName();
                AnswerWriter answerWriter = new AnswerWriter();
                answerWriter.write(answer, "austria");
            }
            FSM = new FileSetManager(RESOURCES_PATH_GULFSTREAM);
            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                dataG.add(listFiles[i]);
                String answer = listFiles[i].getName();
                AnswerWriter answerWriter = new AnswerWriter();
                answerWriter.write(answer, "gulfstream");
            }
            FSM = new FileSetManager(RESOURCES_PATH_DELLINE);
            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                dataD.add(listFiles[i]);
                String answer = listFiles[i].getName();
                AnswerWriter answerWriter = new AnswerWriter();
                answerWriter.write(answer, "delLine");
            }

            Collections.shuffle(dataA);
            Collections.shuffle(dataN);
            Collections.shuffle(dataD);
            Collections.shuffle(dataG);
            //int countN = (int) (dataN.size() * 0.5);
            int countA = (int) (dataA.size() * 0.5);
            train.addAll(dataA.subList(0, countA));
            test.addAll(dataA.subList(countA, dataA.size()));
            train.addAll(dataN.subList(0, 5));
            test.addAll(dataN.subList(5, 10));
            int countD = (int) (dataD.size() * 0.5);
            int countG = (int) (dataG.size() * 0.5);
            train.addAll(dataD.subList(0, countD));
            test.addAll(dataD.subList(countD, dataD.size()));
            train.addAll(dataG.subList(0, countG));
            test.addAll(dataG.subList(countG, dataG.size()));


            Collections.shuffle(train);
            Collections.shuffle(test);


            ARFFConverter4Classes arffConverter2Classes = new ARFFConverter4Classes();
            arffConverter2Classes.convert(train, "/train");
            ARFFConverter4Classes arffConverter2Classes1 = new ARFFConverter4Classes();
            arffConverter2Classes1.convert(test, "/test");


            ConverterUtils.DataSource source = new ConverterUtils.DataSource("src/main/resources/train.arff");
            Instances data = source.getDataSet();
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }
            ConverterUtils.DataSource source1 = new ConverterUtils.DataSource("src/main/resources/test.arff");
            Instances data1 = source1.getDataSet();
            if (data1.classIndex() == -1) {
                data1.setClassIndex(data1.numAttributes() - 1);
            }
            J48 cls = new J48();
            cls.buildClassifier(data);
            // evaluate classifier and print some statistics
            Evaluation eval = new Evaluation(data);
            eval.evaluateModel(cls, data1);
            System.out.println(cls.toString());
            System.out.print(eval.toMatrixString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
