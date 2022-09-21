public class SudokuSolve {
    //size of sudoku
    int size=9;
    //Method to return a solved sudoku
    public int[][] solveMethod(int[][] grid) {
        if(solveSudoku(grid,0,0)) {
            return grid;
        } else {
            return null;
        }
    }
    //Method to check if the cell is safe to enter a number num
    public boolean isSafe(int[][] grid, int row, int col, int num) {
        for(int i=0;i<9;i++) {
            if (grid[i][col] == num) return false; //Is num already exist in same col?
        }
        for(int i=0;i<9;i++) {
            if (grid[row][i] == num) return false; //Is num already exist in same row?
        }

        //Checking if num is already exist in same box
        int startRow=row-row%3, startCol=col-col%3;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(grid[i+startRow][j+startCol] == num) return false;
            }
        }
        return true;
    }

    //Method to solve sudoku
    public boolean solveSudoku(int[][] grid, int row, int col) {
        if(row==size-1 && col==size) return true; //return true at very last index
        if(col==size) {     //if it is last column
            row++;          //next row
            col=0;          //first column of next row
        }
        //checking for the next column if the current column is not empty
        if(grid[row][col] != 0) {
            return solveSudoku(grid,row,col+1);
        }
        //check for every number from 1 to 10 if the number is already exist or not
        for(int i=1;i<10;i++) {
            if(isSafe(grid,row,col,i)) {
                grid[row][col] = i;
                //If number is safe then check for next number using recursion
                if (solveSudoku(grid, row, col + 1)){
                    return true;
                }
            }
            grid[row][col]=0; //if assumption is wrong then assign again 0 to it and check for next number.
        }
        return false;
    }
}