package ru.ifmo.ctddev.ml;

import ru.ifmo.ctddev.datasets.AnswerWriter;
import ru.ifmo.ctddev.datasets.FileSetManager;
import weka.classifiers.Classifier;
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

public class UsualOrNorwayClasses {

    private final String RESOURCES_NOT_NORWAY_PATH = "/Users/disoni/Documents/Bachelor/src/main/resources/features/NOTnorway";
    private static final String RESOURCES_PATH = "/Users/disoni/Documents/Bachelor/src/main/resources/features/norway";

    public void learn() {

        try {
            List<File> train = new ArrayList<>();
            List<File> test = new ArrayList<>();

            FileSetManager FSM;
            File[] listFiles;
            ArrayList<File> dataU = new ArrayList<File>();
            ArrayList<File> dataN = new ArrayList<File>();

            FSM = new FileSetManager(RESOURCES_PATH);
            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                dataN.add(listFiles[i]);
                String answer = listFiles[i].getName();
                AnswerWriter answerWriter = new AnswerWriter();
                answerWriter.write(answer, "norway");
            }

            FSM = new FileSetManager(RESOURCES_NOT_NORWAY_PATH);
            listFiles = FSM.getListFiles();
            for (int i = 0; i < listFiles.length; i++) {
                dataU.add(listFiles[i]);
                String answer = listFiles[i].getName();
                AnswerWriter answerWriter = new AnswerWriter();
                answerWriter.write(answer, "NOTnorway");
            }

            int countN = (int) (dataN.size() * 0.5);
            int countU = (int) (dataU.size() * 0.5);
            train.addAll(dataN.subList(0, countN));
            test.addAll(dataN.subList(countN + 1, dataN.size() - 1));
            train.addAll(dataU.subList(0, countU));
            test.addAll(dataU.subList(countU + 1, dataU.size() - 1));


            Collections.shuffle(train);
            Collections.shuffle(test);


            ARFFConverter2Classes arffConverter2Classes = new ARFFConverter2Classes();
            arffConverter2Classes.convert(train, "/train");
            ARFFConverter2Classes arffConverter2Classes1 = new ARFFConverter2Classes();
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
