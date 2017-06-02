package ru.ifmo.ctddev.features;


public class Feature {
    double value;
    String name;
    String description;

    public Feature(String name, double value, String description) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Feature{\n" +
                "    name='" + name + '\'' +
                ",\n    value=" + value +
                ",\n    description='" + description + '\'' +
                "\n}";
    }

    public String toCSVString() {
        return name + "," + value + "," + description;
    }

    public static String getHeadOfCSV() {
        return "name,value,description";
    }

    public String getName() {
        return name;
    }
    public double getValue() {
        return value;
    }

}