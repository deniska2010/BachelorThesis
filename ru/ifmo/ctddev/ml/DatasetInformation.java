package ru.ifmo.ctddev.ml;

import ru.ifmo.ctddev.features.Feature;

import java.util.ArrayList;

public class DatasetInformation {
    String name;
    String value;

    public DatasetInformation(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue(){
        return value;
    }
}
