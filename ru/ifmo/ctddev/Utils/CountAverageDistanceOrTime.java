package ru.ifmo.ctddev.Utils;

import java.util.ArrayList;


public class CountAverageDistanceOrTime {

    public long count(int n, long[][] a) {

        long result = 0;
        for (int i = 0; i < n - 1; i++) {
            result += a[i][i + 1];
        }

        return result/(n-1);
    }

}
