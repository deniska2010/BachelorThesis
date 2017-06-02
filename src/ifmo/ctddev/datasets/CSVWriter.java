package ru.ifmo.ctddev.datasets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import ru.ifmo.ctddev.features.Feature;


public class CSVWriter {

    public static void write(String filename, List<Feature> records) {
        BufferedWriter bw = null;
        try {
            File file = new File("/Users/disoni/Documents/Bachelor/src/main/resources/features/gulfstream/" + filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            bw = new BufferedWriter(new FileWriter("/Users/disoni/Documents/Bachelor/src/main/resources/features/gulfstream/" + filename,false));

            bw.write(Feature.getHeadOfCSV());
            bw.newLine();
            for (Feature feature : records) {
                bw.write(feature.toCSVString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}