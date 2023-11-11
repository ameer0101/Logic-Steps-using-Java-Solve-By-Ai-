package Model;

import java.util.*;

public class State implements Comparable<State> {
    public static int row;
    public static int col;
    public static int[][] board;
    public int startRow;
    public int startCol;
    private State parentState;
    int pathcost;

    public State(int row, int col, int startRow, int startCol, int board[][]) {
        this.row = row;
        this.col = col;
        this.startRow = startRow;
        this.startCol = startCol;
        this.board = board;

    }
    public void printBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public State(int startRow, int startCol) {
        this.startRow = startRow;
        this.startCol = startCol;
    }

    public State(State state) {
        this(state.getStartRow(), state.getStartCol());
    }


    public int getStartRow() {return startRow;}
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartCol() {
        return startCol;
    }
    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }


    public State getParentState() {
        return parentState;
    }
    public void setParentState(State parentState) {
        this.parentState = parentState;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        State state = (State) obj;
        return startRow == state.startRow && startCol == state.startCol;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "State{" +
                "startRow=" + startRow +
                ", startCol=" + startCol +
                '}';
    }

////////////////////////////////////////
    public int getPathCost() {return pathcost;}

    @Override
    public int compareTo(State other) {
        return Integer.compare(this.getPathCost(), other.getPathCost());
    }




}


