package com.kimaya.sudoku;

import java.util.Scanner;
public class Sudoku {
    


    private int[][] sudokuBoard;


    public Sudoku(int[][] grid) {
        this.sudokuBoard = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.sudokuBoard[i][j] = grid[i][j];
            }
        }
    }


    private boolean checkRowValid(int row, int number) {
        for (int i = 0; i < 9; i++)
            if (sudokuBoard[row][i] == number) {return false;}

        return true;
    }


    private boolean checkColValid(int col, int number) {
        for (int i = 0; i < 9; i++)
            if (sudokuBoard[i][col] == number) {return false;}

        return true;
    }


    private boolean checkBoxValid(int row, int col, int number) {
        int rownum = (row/3) * 3;
//        System.out.println(rownum);

        int colnum = (col/3) * 3;
//        System.out.println(colnum);

        for (int i = rownum; i < rownum + 3; i++)
            for (int j = colnum; j < colnum + 3; j++)
                if (sudokuBoard[i][j] == number)
                    return false;

        return true;
    }


    private boolean isNumberValid(int row, int col, int num) {
        return (checkRowValid(row, num) && checkColValid(col, num) && checkBoxValid(row, col, num));
    }


    public boolean solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (sudokuBoard[row][col] == 0) {

                    for (int tryNum = 1; tryNum < 10 ; tryNum++) {
                        if (isNumberValid(row, col, tryNum)) {
                            sudokuBoard[row][col] = tryNum;

                            if (solve()) {
                                return true;
                            }
                            sudokuBoard[row][col] = 0;

                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + sudokuBoard[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }
    public static void enterSudoku(Scanner scan, int[][] matrix) {
        System.out.println("Input the Sudoku to be solved:");

        for (int i = 0; i < 9; i++) {
//            System.out.println("\n");
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = scan.nextInt();
            }
        }
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] matrix = new int[9][9];
        enterSudoku(scan, matrix);

        Sudoku sudoku = new Sudoku(matrix);

        if (sudoku.solve()) {
            System.out.println("Solved Sudoku: ");
            sudoku.printBoard();
        } else {
            System.out.println("The given Sudoku cannot be cracked. Please check your input values:");
        }
    }




}
