package ru.ifmo.ctddev.datasets;


import java.io.File;
import java.io.FileNotFoundException;


public class FileSetManager {

    public File[] listFiles;

    public FileSetManager (String pathname) throws FileNotFoundException {
        File root = new File(pathname);
        this.listFiles = root.listFiles();
        if (!root.isDirectory()) {
            throw new FileNotFoundException("File " + pathname + " is not a directory");
        }
        if (this.listFiles == null) {
            throw new FileNotFoundException("No files in " + pathname);
        }
    }


   public File[] getListFiles(){
        return listFiles;
    }

}
