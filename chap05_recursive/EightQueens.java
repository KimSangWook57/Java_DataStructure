package chap05_recursive;

public class EightQueens {
    // The board is represented as a 2D array of integers, where 0 means an empty square and 1 means a queen.
    private static int[][] board = new int[8][8];

    public static void main(String[] args) {
        // Place the first queen in the top-left corner of the board
        board[0][0] = 1;

        // Try to place the remaining queens in the other rows of the board
        if (placeQueens(1)) {
            // If a solution was found, print the board
            printBoard();
        } else {
            System.out.println("No solution found.");
        }
    }

    public static boolean placeQueens(int row) {
        // If we have placed all queens, return true to indicate that a solution was found
        if (row == 8) {
            return true;
        }

        // Try to place a queen in each column of the current row
        for (int col = 0; col < 8; col++) {
            if (isValid(board, row, col)) {
                // If it's a valid move, place the queen and try to place the next queen in the next row
                board[row][col] = 1;
                if (placeQueens(row + 1)) {
                    return true;
                }
                // If placing the next queen in the next row didn't lead to a solution, backtrack by removing the current queen
                board[row][col] = 0;
            }
        }

        // If we have tried all columns and couldn't place a queen in any of them, return false to indicate failure
        return false;
    }

    public static boolean isValid(int[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                // There is already a queen in this column, so it's not a valid move
                return false;
            }
        }

        // Check diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                // There is already a queen on this diagonal, so it's not a valid move
                return false;
            }
        }

        // Check other diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
            if (board[i][j] == 1) {
                // There is already a queen on this diagonal, so it's not a valid move
                return false;
            }
        }

        // If we have checked all possible attacking positions and found no existing queens, it's a valid move
        return true;
    }

    public static void printBoard() {
        // Print the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
