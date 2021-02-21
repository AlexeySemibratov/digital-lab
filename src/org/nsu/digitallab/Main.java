package org.nsu.digitallab;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer[][] A = readMatrix(scanner);
        Integer[][] B = readMatrix(scanner);

        MatrixScanner matrixScanner = new MatrixScanner(A);

        matrixScanner.run(B);
    }

    /**
     * Чтение матрицы из консоли
     * @param scanner
     * @return
     */
    public static Integer[][] readMatrix(Scanner scanner){
        int m,n;
        m = scanner.nextInt();
        scanner.skip(" ");
        n = scanner.nextInt();

        Integer[][] matrix = new Integer[m][n];

        for(int i = 0; i<m;i++) {
            for(int j = 0; j<n;j++)
            {
                matrix[i][j]= scanner.nextInt();
                if(j+1 != n) scanner.skip(" ");
            }
        }

        return matrix;
    }

}
