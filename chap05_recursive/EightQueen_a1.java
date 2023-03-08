package chap05_recursive;

import java.awt.Point;

public class EightQueen_a1 {
    
    public static void main(String[] args) {
        Point[] queens = new Point[8];
        solve(queens, 0);
    }
    
    private static void solve(Point[] queens, int row) {
        if (row == 8) {
            printSolution(queens);
            return;
        }
        
        for (int col = 0; col < 8; col++) {
            boolean valid = true;
            
            for (int i = 0; i < row; i++) {
                if (queens[i].x == col || queens[i].y == row ||
                    queens[i].x + queens[i].y == col + row ||
                    queens[i].x - queens[i].y == col - row) {
                    valid = false;
                    break;
                }
            }
            
            if (valid) {
                queens[row] = new Point(col, row);
                solve(queens, row + 1);
            }
        }
    }
    
    private static void printSolution(Point[] queens) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (queens[row].x == col && queens[row].y == row) {
                    System.out.print("Q ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
