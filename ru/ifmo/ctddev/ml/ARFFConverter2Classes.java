package ru.ifmo.ctddev.ml;

import ru.ifmo.ctddev.datasets.CSVReader;
import ru.ifmo.ctddev.features.Feature;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ARFFConverter2Classes {

    public static void convert() throws IOException {
        String targetDir = "/Users/disoni/Thesis/src/main/resources";
        String features = "/features";
        String train = "/train5";
        String dARFF = ".arff";

        File dir = new File(targetDir + features);

        File[] directoryListing = dir.listFiles();
        if (directoryListing == null) {
            System.err.println("not a directory to list files");
            return;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(
                targetDir + train + dARFF, false));

        writer.write("%features,targetClass");
        writer.newLine();
        writer.write("@RELATION pdp");
        writer.newLine();

        boolean firstTime = true;


        for (File file : directoryListing) {
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

                writer.write("@ATTRIBUTE class {norway,not}");
                writer.newLine();
                writer.write("@DATA");
                writer.newLine();
                firstTime = false;
            }

            List<String> featuresDouble = featuresCSV.stream()
                    .map(f -> f[1]).collect(Collectors.toList());

            if(file.getName().contains("norway")){
                featuresDouble.add("norway");
            }
            else{
                featuresDouble.add("not");
            }


            writer.write(String.join(",", featuresDouble));
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}