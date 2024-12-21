public class Sudoku16x16 {
    private static final int GRID_SIZE = 16;
    private static final int SUBSECTION_SIZE = 4;
    private static final char EMPTY_CELL = '.';
    private char[][] board;
    
    // Valid characters for the puzzle (1-9 and A-G)
    private static final char[] VALID_CHARS = {
        '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F', 'G'
    };

    public Sudoku16x16() {
        board = new char[][]{
            {'.', 'G', '.', '.', 'F', '8', '9', '6', '4', 'B', 'D', '5', '.', '.', '3', '.'},
            {'6', 'C', '.', '.', '.', '.', '4', 'E', '2', '7', '.', '.', '.', '.', '5', '9'},
            {'.', '.', 'D', '.', '.', 'G', '7', 'F', 'E', '.', '.', '6', '.', '.', '.', '.'},
            {'.', '.', '4', '3', 'A', '.', '.', '.', '.', '.', '6', '1', 'B', '.', '.', '.'},
            {'7', '.', '.', '5', '8', 'F', '.', '.', '.', '.', 'B', 'E', '9', '.', '.', 'G'},
            {'8', '.', '.', '9', '.', '.', '4', 'D', '.', '.', '3', '.', '.', '.', '.', '2'},
            {'C', '1', '3', '.', '.', '.', '6', '.', '.', 'G', '.', '.', '.', 'F', '4', '5'},
            {'9', 'D', 'B', '.', '.', 'G', '.', '.', '.', '.', 'F', '.', '.', '7', 'A', '6'},
            {'G', 'B', 'A', '.', '.', '2', '.', '.', '.', '.', '7', '.', '.', '5', '6', 'D'},
            {'5', '6', 'F', '.', '.', '.', 'A', '.', '.', '2', '.', '.', '.', '8', '7', '4'},
            {'D', '.', '.', '6', '.', '.', '9', '5', '.', '.', 'G', '.', '.', '.', '.', 'F'},
            {'3', '.', '.', 'C', 'B', '5', '.', '.', '.', '.', 'A', '4', 'G', '.', '.', '1'},
            {'.', '.', '9', '6', 'G', '.', '.', '.', '.', '.', '7', '2', 'C', '.', '.', '.'},
            {'.', '.', '.', 'G', '.', '.', 'B', 'D', 'C', '5', '.', '.', '.', '.', '.', '.'},
            {'4', '3', '.', '.', '.', '.', '8', '2', 'G', 'F', '.', '.', '.', '.', '1', '7'},
            {'.', '8', '.', '.', '5', '9', 'E', 'A', '1', '3', '2', 'D', '.', '.', 'G', '.'}
        };
    }

    private boolean isValidPlacement(char number, int row, int col) {
        return !isNumberInRow(number, row) &&
               !isNumberInColumn(number, col) &&
               !isNumberInBox(number, row, col);
    }

    private boolean isNumberInRow(char number, int row) {
        for (int col = 0; col < GRID_SIZE; col++) {
            if (board[row][col] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumn(char number, int col) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (board[row][col] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInBox(char number, int row, int col) {
        int boxRow = row - row % SUBSECTION_SIZE;
        int boxCol = col - col % SUBSECTION_SIZE;

        for (int i = boxRow; i < boxRow + SUBSECTION_SIZE; i++) {
            for (int j = boxCol; j < boxCol + SUBSECTION_SIZE; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean solve() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    for (char value : VALID_CHARS) {
                        if (isValidPlacement(value, row, col)) {
                            board[row][col] = value;
                            if (solve()) {
                                return true;
                            }
                            board[row][col] = EMPTY_CELL;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % SUBSECTION_SIZE == 0 && row != 0) {
                System.out.println("-".repeat(GRID_SIZE * 2 + SUBSECTION_SIZE));
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % SUBSECTION_SIZE == 0 && col != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Sudoku16x16 sudoku = new Sudoku16x16();
        System.out.println("Original 16x16 Sudoku:");
        sudoku.printBoard();
        
        if (sudoku.solve()) {
            System.out.println("\nSolved 16x16 Sudoku:");
            sudoku.printBoard();
        } else {
            System.out.println("\nNo solution exists");
        }
    }
}

