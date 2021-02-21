package org.nsu.digitallab;

import java.util.Arrays;

/**
 * @author Alexey Semibratov (a.semibratov@g.nsu.ru)
 */

public class MatrixScanner {

    int aRowLen, aColLen;
    int bRowLen, bColLen;

    private Integer[][] A;
    private Integer[][] B;

    /**
     * Инициализация сканнера на основе шаблона.
     * @param A матрица-шаблон
     */

    public MatrixScanner(Integer[][] A){
        this.A = A;
        this.aRowLen = A.length;
        this.aColLen = A[0].length;
    }

    /**
     * Начать поиск в матрице B на основе шаблона {@link MatrixScanner#A}
     * @param B матрица для поиска
     * @return символьную матрицу - результат поиска
     */

    public Character[][] run(Integer[][] B){
        Character[][] result;

        this.B = B;
        this.bRowLen = B.length;
        this.bColLen = B[0].length;

        if(!areCorrectParameters()) {
            result = convertB();
            printMatrix(result);
            return result;
        }

        //Текущая строка матрицы А
        int aRow;
        //Смещение в строке поиска
        int currentOffset;
        //Буффер для текущей строки матрицы В
        int bRowTemp;

        //Итерация по всем строкам матрицы В
        for(int bRow = 0;bRow<B.length;bRow++){

            aRow = 0;
            bRowTemp = bRow;
            currentOffset = 0;


            //Выполняем поиск шаблона А в полосе [bRow, bRow + кол-во строк в шаблоне]
            while(currentOffset + aColLen <= bColLen) {
                //Получаем индекс строки - первого вхождения строки матрицы А в текущую строку матрицы В
                currentOffset = getOccurrenceIndex(A[aRow], B[bRow], currentOffset);
                //Если вхождение есть, проверяем шаблон (за исключением уже первой строки)
                if (currentOffset >= 0) {
                    aRow++;
                    bRow++;
                    while (aRow < aRowLen && bRow < bRowLen) {
                        //Пока шаблон подтверждается, увеличиваем индексы строчек матриц А и В
                        if (!validRowInRange(A[aRow], B[bRow], currentOffset)) break;
                        aRow++;
                        bRow++;
                    }
                    //Вышли из цикла и имеем два варианта:
                    //1. Шаблон полноостью совпал
                    if (aRow == aRowLen) {
                        //Меняем значения в матрице В, что подтвердить нахождение шаблона
                        markBlock(bRowTemp,bRowTemp + aRowLen,currentOffset,currentOffset + aColLen);
                        // Сдвинуться на длину найденного блока
                        currentOffset += aColLen;
                    }
                    //2. Шаблон не совпал
                    else {
                        //Тогда стоит сдвинуться на единицу в столбце и продолжить поиск
                        currentOffset++;
                    }
                    //В любом случае сбрасываем индекс в шаблоне на начало
                    // Также возвращаем индекс строки B, так как могут быть еще шаблоны в оставшейся полосе
                    aRow = 0;
                    bRow = bRowTemp;
                }
                else {
                    //Если вхождения нет, то завершаем цикл
                    if(currentOffset==-1)
                    {
                        break;
                    }
                    currentOffset++;
                }
            }
            //Возвращаем индекс текущей строки в В обратно, чтобы на следующей
            //итерации цикла начать проверять уже следующую полосу строк
            bRow = bRowTemp;
        }

        result = convertB();
        printMatrix(result);
        return result;
    }

    /**
     * Проверяет, что длина/ширина матрицы-шаблона А не превосходит соответствующих
     * параметров матрицы B.
     * @return
     */
    public boolean areCorrectParameters() {
        return (aRowLen>0 && aRowLen<=bRowLen && aColLen>0 && aColLen<=bColLen);
    }

    /**
     * Проверят, содержится ли rowA в rowB в строгом диапазоне [start, start + rowA.length]
     * @param rowA строка матрицы А
     * @param rowB строка матрицы B
     * @param start индекс
     * @return true - если содержися, false - иначе
     */
    private boolean validRowInRange(Integer[] rowA, Integer[] rowB, int start){
        return getOccurrenceIndex(rowA,rowB,start) == start ? true:false;
    }

    /**
     * @param rowA - строка из матрицы А
     * @param rowB - строка из матрицы В
     * @param offset - смещение в строке rowB
     * @return индекс, с которого начинается вхождение строки rowA в строку rowB,
     * или -1, если вхождения нет.
     */

    private int getOccurrenceIndex (Integer[] rowA, Integer[] rowB, int offset) {
        if(rowB.length - offset < rowA.length) return -1;

        int index_a;
        int start;

        for (int index_b = offset; index_b < rowB.length; index_b++) {

            index_a = 0;
            start = index_b;

            while(index_a<rowA.length && (start+index_a)<rowB.length) {
                if(rowA[index_a] != rowB[index_b]) break;
                else {
                    index_a++;
                    index_b++;
                }
            }

            if(index_a == rowA.length) return index_b - index_a;

            index_b=start;

        }
        return -1;
    }

    /**
     * Заменяет значения в матрице {@link MatrixScanner#B} в диапазоне [i,i1)x[j,j1) следующим образом:
     * 1 -> 2;
     * 0 -> 3.
     */

    private void markBlock(int i,int i1, int j, int j1){
        for(int m = i;m<i1;m++){
            for(int n = j;n<j1;n++) {
                if (B[m][n] == 1) B[m][n] = 2;
                else B[m][n] = 3;
            }
        }
    }

    private Character[][] convertB(){
        Character[][] result = new Character[bRowLen][bColLen];
        for(int i=0;i<bRowLen;i++){
            result[i] = Arrays.stream(B[i]).map(x->(x==3?'*':Character.forDigit(x,10))).toArray(Character[]::new);
        }
        return result;
    }

    private void printMatrix(Character[][] matrix) {
        for(int i=0;i<matrix.length;i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
    }

}
