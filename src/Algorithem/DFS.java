package Algorithem;

import Model.Rules;
import Model.State;

import java.util.*;

public class DFS {

     final List<State> startStates;
     final List<State> states;

    public DFS( List<State> startStates, List<State> states) {
        this.startStates = startStates;
        this.states = states;
    }

    public List<State> search() {
        Stack<State> stack = new Stack<>();
        Set<State> visited = new HashSet<>();

        for (State startState : startStates) {
            stack.push(startState);
            visited.add(startState);
        }

        while (!stack.isEmpty()) {
            State currentState = stack.pop();
            states.add(currentState);

            // Print the current state
            System.out.println("Current state: " + currentState);
            currentState.printBoard();

            int currentRow = currentState.getStartRow();
            int currentCol = currentState.getStartCol();

            List<State> neighbors = getNeighbors(currentRow, currentCol);

            for (State neighbor : neighbors) {
                if (Rules.isValidMove(neighbor.getStartRow(), neighbor.getStartCol(), State.row, State.col) && Rules.isAvailable(State.board[neighbor.getStartRow()][neighbor.getStartCol()]) ) {
                    neighbor.setParentState(currentState);
                    stack.push(neighbor);
                    visited.add(neighbor);
                    State.board[neighbor.getStartRow()][neighbor.getStartCol()] -= 1;
                }
            }
        }
        return states;
    }



    private List<State> getNeighbors(int row, int col) {
        List<State> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (Rules.isValidMove(newRow, newCol, State.row, State.col) && Rules.isAvailable(State.board[newRow][newCol])) {
                State neighbor = new State(newRow, newCol);
                State copiedNeighbor = new State(neighbor);
                neighbors.add(copiedNeighbor);
            }
        }
        return neighbors;
    }

}
