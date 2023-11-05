package Model;

public class Rules {
    public static boolean isValidMove(int newRow, int newCol, int row, int col) {
        return newRow >= 0 && newRow < row && newCol >= 0 && newCol < col  ;
    }
    public static boolean isAvailable(int place){
        if (place != 1 && place != 0) {
            return true;
        }
        else return false;
    }


    public static boolean isfinal(int [][]board,int col,int row)    {
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col; j++) {
                if (board[i][j] != 0 && board[i][j] != 1) {

                    return true;

                }

            }
        }
        return false;
    }

    public static boolean isDead(State state) {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // right, left, down, up
        for (int[] direction : directions) {
            int newRow = state.startRow + direction[0];
            int newCol = state.startCol + direction[1];
            if (isValidMove(newRow, newCol, state.row, state.col) && isAvailable(state.board[newRow][newCol]))
            {
                return false;
            }
        }
        return true; // No valid moves found
    }

}
