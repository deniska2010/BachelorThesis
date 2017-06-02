package ru.ifmo.ctddev.ml;

import ru.ifmo.ctddev.datasets.AnswerReader;
import ru.ifmo.ctddev.datasets.CSVReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ARFFConverter4Classes {
    public static void convert(List<File> fileList, String data) throws IOException {
        String targetDir = "src/main/resources";
        String answers = "/answers";
        String dARFF = ".arff";


        BufferedWriter writer = new BufferedWriter(new FileWriter(
                targetDir + data + dARFF, false));

        writer.write("%features,targetClass");
        writer.newLine();
        writer.write("@RELATION veeroute");
        writer.newLine();

        boolean firstTime = true;


        for (File file : fileList) {
            List<String[]> featuresCSV = CSVReader.read(file.getAbsolutePath(), ",");

            if (firstTime) {
                featuresCSV.stream().forEach(f -> {
                    try {
                        writer.write("@ATTRIBUTE " + f[0] + " NUMERIC");
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                writer.write("@ATTRIBUTE class {norway,austria,gulfstream,delLine}");
                writer.newLine();
                writer.write("@DATA");
                writer.newLine();
                firstTime = false;
            }
            List<String> featuresDouble = featuresCSV.stream()
                    .map(f -> f[1]).collect(Collectors.toList());

            String answer = AnswerReader.read(targetDir + answers + "/" + file.getName());
            featuresDouble.add(answer);


            writer.write(String.join(",", featuresDouble));
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

}
