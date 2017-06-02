package ru.ifmo.ctddev.datasets;

import ru.ifmo.ctddev.features.Feature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class AnswerWriter {

    public static void write(String filename, String value) {
        BufferedWriter bw = null;
        try {
            File file = new File("src/main/resources/answers/" + filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            bw = new BufferedWriter(new FileWriter("src/main/resources/answers/" + filename,false));

            bw.write(value);
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
