// Sudoku Solver using Bit Masking and BackTracking 

//###############################################################################################################################################//


import java.io.*;
import java.util.*;

public class Main {

  public static void display(int[][] arr){
    for (int ii = 0; ii < arr.length; ii++) {
      for (int jj = 0; jj < arr.length; jj++) {
        System.out.print(arr[ii][jj] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void solveSudoku(int[][] board, int[] rows, int[] cols, int[][] grid, int i, int j) {
    if(i == board.length){
        display(board);
        return;
    }
    
    int nextRow = 0;
    int nextCol =0;
    
    if(j == board[0].length-1){
        nextRow = i+1;
        nextCol =0;
    }else{
        nextRow =i;
        nextCol = j+1;
    }
    
    if(board[i][j] != 0){
        solveSudoku(board,rows,cols,grid,nextRow,nextCol);
    }else{
        for(int possibleOption =1 ; possibleOption<=9;possibleOption++){
            if((rows[i] & (1<< possibleOption)) == 0 
                && (cols[j] & (1 << possibleOption)) == 0
                && (grid[i/3][j/3] & (1 << possibleOption)) == 0
                ){
                    board[i][j] = possibleOption;
                    rows[i] ^= (1 << possibleOption);
                    cols[j] ^= (1 << possibleOption);
                    grid[i/3][j/3] ^= (1 << possibleOption);
                    solveSudoku(board,rows,cols,grid,nextRow,nextCol);
                    rows[i] ^= (1 << possibleOption);
                    cols[j] ^= (1 << possibleOption);
                    grid[i/3][j/3] ^= (1 << possibleOption);
                    board[i][j] =0;
                    
                }
        }
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int[][] board = new int[9][9];
    int[] rows = new int[9];
    int[] cols = new int[9];
    int[][] grid = new int[3][3];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int digit = scn.nextInt();
        board[i][j] = digit;
        rows[i] |= (1 << digit);
        cols[j] |= (1 << digit);
        grid[i / 3][j / 3] |= (1 << digit);
      }
    }

    solveSudoku(board, rows, cols, grid, 0, 0);
  }

}





//#######################################################//

Time Complexity -O(N)
Space Complexity -O(N)

//####################################################//





