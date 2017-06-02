package ru.ifmo.ctddev.datasets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by disoni on 08.05.17.
 */
public class AnswerReader {

    public static String read(String csvFile ) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = "";
            line = br.readLine();
            br.close();
            return line;
        }

    }
}
