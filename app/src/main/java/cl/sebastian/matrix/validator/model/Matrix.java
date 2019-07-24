package cl.sebastian.matrix.validator.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Matrix {

    int[][] data;
    int fila1;
    int fila2;
    int fila3;
    int columna1;
    int columna2;
    int columna3;
    int diagonal1;
    int diagonal2;
    boolean magic = false;

    public Matrix() {
        this.data = null;
        initData();
    }

    public Matrix(int[][] data) {
        this.data = data;
        initData();
    }

    public Matrix(String line) {
        this.data = new int[3][3];
        if (StringUtils.isNotBlank(line)) {
            String str = StringUtils.trimToEmpty(StringUtils.remove(line, "[]"));
            String[] arreglo = StringUtils.split(str, ";");
            for (int i = 0; i < arreglo.length; i++) {
                String fila = StringUtils.trimToEmpty(arreglo[i]);
                String[] values = StringUtils.split(fila, ",");
                for (int j = 0; j < values.length; j++) {
                    this.data[i][j] = NumberUtils.toInt(values[j]);
                }
            }
        }
        initData();
    }

    private void initData() {
        try {
            this.magic = false;
            if (data != null) {
                this.fila1 = (data[0][0] + data[0][1] + data[0][2]);
                this.fila2 = (data[1][0] + data[1][1] + data[1][2]);
                this.fila3 = (data[2][0] + data[2][1] + data[2][2]);

                this.columna1 = (data[0][0] + data[1][0] + data[2][0]);
                this.columna2 = (data[0][1] + data[1][1] + data[2][1]);
                this.columna3 = (data[0][2] + data[1][2] + data[2][2]);

                this.diagonal1 = (data[0][0] + data[1][1] + data[2][2]);
                this.diagonal2 = (data[0][2] + data[1][1] + data[2][0]);

                if ((fila1 == fila2) && (fila1 == fila3)) {
                    if ((columna1 == columna2) && (columna1 == columna3)) {
                        if (diagonal1 == diagonal2) {
                            this.magic = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            this.magic = false;
        }
    }

    public boolean isMagic() {
        return magic;
    }

    public int[][] getData() {
        return data;
    }

    public int getFila1() {
        return fila1;
    }

    public int getFila2() {
        return fila2;
    }

    public int getFila3() {
        return fila3;
    }

    public int getColumna1() {
        return columna1;
    }

    public int getColumna2() {
        return columna2;
    }

    public int getColumna3() {
        return columna3;
    }

    public int getDiagonal1() {
        return diagonal1;
    }

    public int getDiagonal2() {
        return diagonal2;
    }

    public String getString() {
        return String.format("[%d,%d,%d;%d,%d,%d;%d,%d,%d]", 
                data[0][0], data[0][1], data[0][2], 
                data[1][0], data[1][1], data[1][2], 
                data[2][0], data[2][1], data[2][2]);
    }
}
