package chap05_recursive;

import java.awt.Point;
import java.util.Stack;

public class EightQueensIterative {

    public static void main(String[] args) {
        Point[] queens = new Point[8];
        Stack<Point[]> stack = new Stack<>();
        stack.push(queens);  // Push the initial state of the board onto the stack.

        while (!stack.isEmpty()) {  // Continue until there are no more states to examine.
            queens = stack.pop();  // Pop the next state off the stack.
            int row = findUnassignedRow(queens);  // Find the first row without a queen.
            if (row == 8) {  // If all rows are assigned, we have a solution.
                printQueens(queens);
            } else {
                for (int col = 0; col < 8; col++) {  // Try all possible columns.
                    if (isSafe(queens, row, col)) {  // Check if placing a queen at (row, col) is safe.
                        Point[] newQueens = queens.clone();  // Create a new board with the new queen.
                        newQueens[row] = new Point(col, row);
                        stack.push(newQueens);  // Push the new board onto the stack.
                    }
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

    public static int findUnassignedRow(Point[] queens) {
        for (int i = 0; i < 8; i++) {
            if (queens[i] == null) {
                return i;  // Return the first unassigned row.
            }
        }
        return 8;  // If all rows are assigned, return 8 to indicate a solution.
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
