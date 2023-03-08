package chap05_recursive;

import java.awt.Point;

public class EightQueensRecursive {

    public static void main(String[] args) {
        Point[] queens = new Point[8];
        int[] count = {0};
        solve(queens, 0, count);  // Start solving the board from the first row.
    }

    public static void solve(Point[] queens, int row, int[] count) {
        if (row == 8) {  // If all rows are assigned, we have a solution.
        	count[0]++; // Increment the solution counter
            System.out.println("Solution " + count[0] + ":\n");
        	printQueens(queens);
        } else {
            for (int col = 0; col < 8; col++) {  // Try all possible columns.
                if (isSafe(queens, row, col)) {  // Check if placing a queen at (row, col) is safe.
                    queens[row] = new Point(col, row);  // Place a queen at (row, col).
                    solve(queens, row + 1, count);  // Recursively solve the rest of the board.
                    queens[row] = null;  // Remove the queen at (row, col) and try the next column.
                }
            }
        }
    }

    public static boolean isSafe(Point[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {  // Check all previously assigned rows.
            if (queens[i].x == col || queens[i].y == row || 
                queens[i].x - queens[i].y == col - row || 
                queens[i].x + queens[i].y == col + row) {
                return false;  // If there is a conflict, the placement is unsafe.
            }
        }
        return true;  // Otherwise, the placement is safe.
    }

    public static void printQueens(Point[] queens) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (queens[i].x == j && queens[i].y == i) {
                    System.out.print("Q ");  // Print a queen if there is one in the square.
                } else {
                    System.out.print(". ");  // Print a dot otherwise.
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

