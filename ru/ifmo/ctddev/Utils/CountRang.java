package ru.ifmo.ctddev.Utils;


import java.util.ArrayList;

public class CountRang {

    public int countLong(int n, int m, long[][] a) {

        double EPS = 1E-9;

        int rank = Math.max(n, m);
        ArrayList<Boolean> line_used = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            line_used.add(false);
        }
        for (int i = 0; i < m; ++i) {
            int j;
            for (j = 0; j < n; ++j)
                if (!line_used.get(j) && Math.abs(a[j][i]) > EPS)
                    break;
            if (j == n)
                --rank;
            else {
                line_used.set(j, true);
                ;
                for (int p = i + 1; p < m; ++p)
                    a[j][p] /= a[j][i];
                for (int k = 0; k < n; ++k)
                    if (k != j && Math.abs(a[k][i]) > EPS)
                        for (int p = i + 1; p < m; ++p)
                            a[k][p] -= a[j][p] * a[k][i];
            }
        }


        return rank;
    }

    public int countInteger(int n, int m, int[][] a) {

        double EPS = 1E-9;

        int rank = Math.max(n, m);
        ArrayList<Boolean> line_used = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            line_used.add(false);
        }
        for (int i = 0; i < m; ++i) {
            int j;
            for (j = 0; j < n; ++j)
                if (!line_used.get(j) && Math.abs(a[j][i]) > EPS)
                    break;
            if (j == n)
                --rank;
            else {
                line_used.set(j, true);
                ;
                for (int p = i + 1; p < m; ++p)
                    a[j][p] /= a[j][i];
                for (int k = 0; k < n; ++k)
                    if (k != j && Math.abs(a[k][i]) > EPS)
                        for (int p = i + 1; p < m; ++p)
                            a[k][p] -= a[j][p] * a[k][i];
            }
        }


        return rank;
    }


}
