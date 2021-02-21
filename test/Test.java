import org.nsu.digitallab.MatrixScanner;

import java.util.Arrays;

public class Test {


    //Шаблоны:

    Integer[][] A1 = new Integer[][]{
                {1,1,0},
                {0,0,0}
        };
    Integer[][] A2 = new Integer[][]{
            {0}
    };
    Integer[][] A3 = new Integer[][]{
            {0,0},
            {1,0}
    };
    Integer[][] A4 = new Integer[][]{
            {1,0,0,1,0,1},
            {1,1,1,0,1,0}
    };
    Integer[][] A5 = new Integer[][]{
            {1,1,1}
    };
    Integer[][] A6 = new Integer[][]{
            {0},
            {0},
    };

    //Матрциы В:

    Integer[][] B1 = new Integer[][]{
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1}
        };
    Integer[][] B2 = new Integer[][]{
            {1, 1, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1}
    };
    Integer[][] B3 = new Integer[][]{
            {0},
            {1},
            {1},
            {0}
    };
    Integer[][] B4 = new Integer[][] {
            {1,1,1,1,1,1,1,1}
    };
    Integer[][] B5 = new Integer[][]{
            {1,1,1,1,1},
            {0,0,0,0,0}
    };
    Integer[][] B6 = new Integer[][]{
            {0,1,0},
            {1,0,1},
            {0,0,1}
    };


    @org.junit.Test
    public void testScannerWithA1(){
        MatrixScanner scanner = new MatrixScanner(A1);
        scanner.run(B1);
        scanner.run(B2);
        scanner.run(B3);
        scanner.run(B4);
    }

    @org.junit.Test
    public void testScannerWithA2(){
        MatrixScanner scanner = new MatrixScanner(A2);
        scanner.run(B3);
        scanner.run(B4);
    }

    @org.junit.Test
    public void testScannerWithA3(){
        MatrixScanner scanner = new MatrixScanner(A3);
        scanner.run(B2);
        scanner.run(B4);
    }

    @org.junit.Test
    public void testScannerWithA4(){
        MatrixScanner scanner = new MatrixScanner(A4);
        scanner.run(B1);
        scanner.run(B5);
    }

    @org.junit.Test
    public void testScannerWithA5(){
        MatrixScanner scanner = new MatrixScanner(A5);
        scanner.run(B4);
        scanner.run(B5);
    }

    @org.junit.Test
    public void testScannerWithA6(){
        MatrixScanner scanner = new MatrixScanner(A6);
        scanner.run(B2);
        scanner.run(B6);
    }

}
