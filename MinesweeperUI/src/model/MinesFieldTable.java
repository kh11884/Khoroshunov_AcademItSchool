package model;

public class MinesFieldTable {
    private int[][] minesField;

    public MinesFieldTable(int minesQuantity, int column, int row) {
        minesField = new int[column][row];
        int minesRest = minesQuantity;

        do {
            int randomRow = (int) Math.round(Math.random() * (row - 1));
            int randomColumn = (int) Math.round(Math.random() * (column - 1));

            if (minesField[randomColumn][randomRow] != 0) {
                continue;
            }
            minesField[randomRow][randomColumn] = 9;
            minesRest--;
        } while (minesRest > 0);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (minesField[i][j] != 9) {
                    minesField[i][j] = getNearMinesQuantity(i, j);
                }
            }
        }
    }

    private int getNearMinesQuantity(int row, int column) {
        int countNearMines = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i < 0 || j < 0 || i >= minesField.length || j >= minesField[0].length) {
                    continue;
                }
                if (minesField[i][j] == 9) {
                    countNearMines++;
                }
            }
        }
        return countNearMines;
    }

    public int getCellValue(int row, int column) {
        return minesField[row][column];
    }
}
