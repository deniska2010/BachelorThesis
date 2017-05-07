package ru.ifmo.ctddev.datasets;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVReader {

    public static List<String[]> read(String csvFile, String separator) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            List<String[]> records = new ArrayList<>();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] ss = line.split(separator);
                records.add(ss);
            }

//            System.out.println("read file " + csvFile + " successful");
//            System.out.println(records.size() + " records found.");

            br.close();
            return records;
        }

    }
}