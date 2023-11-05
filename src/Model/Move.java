package Model;

public class Move {
        public static void moveUp(State state) {
            int newStartRow = state.startRow - 1;
            if (Rules.isValidMove(newStartRow, state.startCol, state.row, state.col)&&Rules.isAvailable(state.board[newStartRow][state.startCol])) {
                state.startRow = newStartRow;
                state.board[state.startRow][state.startCol] -= 1;
                state.printBoard();
            } else {
                System.out.println("Invalid move.");
            }
        }

        public static void moveDown(State state) {
            int newStartRow = state.startRow + 1;
            if (Rules.isValidMove(newStartRow, state.startCol, state.row, state.col)&&Rules.isAvailable(state.board[newStartRow][state.startCol])) {
                state.startRow = newStartRow;
                state.board[state.startRow][state.startCol] -= 1;
                state.printBoard();
            } else {
                System.out.println("Invalid move.");
            }
        }
        public static void moveLeft(State state) {
            int newStartCol = state.startCol - 1;
            if (Rules.isValidMove(state.startRow, newStartCol, state.row, state.col)&& Rules.isAvailable(state.board[state.startRow][newStartCol])) {
                state.startCol = newStartCol;
                state.board[state.startRow][state.startCol] -= 1;
                state.printBoard();
            } else {
                System.out.println("Invalid move.");
            }
        }



        public static void moveRight(State state) {
            int newStartCol = state.startCol + 1;
            if (Rules.isValidMove(state.startRow, newStartCol, state.row, state.col) && Rules.isAvailable(state.board[state.startRow][newStartCol])) {
                state.startCol = newStartCol;
                state.board[state.startRow][state.startCol] -= 1;
                state.printBoard();
            } else {
                System.out.println("Invalid move.");
            }
        }
    }
