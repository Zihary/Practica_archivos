import java.io.*;

public class Main {
    public static void main(String[] args) {
        String inputFileA = "a.mat";
        String inputFileB = "b.mat";
        String outputFileC = "c.mat";

        // Leer las matrices
        double[][] matrizA = leerMatriz(inputFileA);
        double[][] matrizB = leerMatriz(inputFileB);

        // Checar si se pueden multiplicar
        if (matrizA[0].length != matrizB.length) {
            System.out.println("Matrices no se pueden multiplicar");
            return;
        }

        // Hacer la multiplicacion de matrices
        double[][] resultMatriz = multiplicarMatrices(matrizA, matrizB);

        // Escribir el resultado en el archivo c
        writeMatriz(outputFileC, resultMatriz);

        System.out.println("Matriz A:");
        imprimeMatriz(matrizA);

        System.out.println("Matriz B:");
        imprimeMatriz(matrizB);

        System.out.println("Resultado Matriz C:");
        imprimeMatriz(resultMatriz);
    }

    public static double[][] leerMatriz(String fileName) {
        try (DataInputStream le = new DataInputStream(new FileInputStream(fileName))) {
            int rows = le.readByte();
            int cols = le.readByte();
            double[][] matriz = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matriz[i][j] = le.readDouble();
                }
            }
            return matriz;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeMatriz(String fileName, double[][] matriz) {
        try (DataOutputStream ma = new DataOutputStream(new FileOutputStream(fileName))) {
            int rows = matriz.length;
            int cols = matriz[0].length;
            ma.writeByte(rows);
            ma.writeByte(cols);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    ma.writeDouble(matriz[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double[][] multiplicarMatrices(double[][] matrizA, double[][] matrizB) {
        int rowsA = matrizA.length;
        int colsA = matrizA[0].length;
        int colsB = matrizB[0].length;
        double[][] result = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                double sum = 0.0;
                for (int k = 0; k < colsA; k++) {
                    sum += matrizA[i][k] * matrizB[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    public static void imprimeMatriz(double[][] matriz) {
        for (double[] row : matriz) {
            for (double val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
