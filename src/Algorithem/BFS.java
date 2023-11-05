package Algorithem;

import Model.Rules;
import Model.State;

import java.util.*;

public class BFS {
    private List<State> startStates;
    private List<State> states;

    public BFS(List<State> startStates, List<State> states) {
        this.startStates = startStates;
        this.states = states;
    }

    public List<State> search() {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        for (State startState : startStates) {
            queue.add(startState);
            visited.add(startState);
        }

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            states.add(currentState);

            // Print the current state
            System.out.println("Current state: " + currentState);

            // Print the board
            currentState.printBoard();

            int currentRow = currentState.getStartRow();
            int currentCol = currentState.getStartCol();

            List<State> neighbors = getNeighbors(currentRow, currentCol);

            for (State neighbor : neighbors) {
                if (Rules.isValidMove(neighbor.getStartRow(), neighbor.getStartCol(), State.row, State.col) && Rules.isAvailable(State.board[neighbor.getStartRow()][neighbor.getStartCol()]) ) {
                    neighbor.setParentState(currentState);
                    queue.add(neighbor);
                    visited.add(neighbor);
                    State.board[neighbor.getStartRow()][neighbor.getStartCol()] -= 1;
                }
            }
        }
        return states;
    }

    private List<State> getNeighbors(int row, int col) {
        List<State> neighbors = new ArrayList<>();

        // Check all four directions (up, down, left, right)
        int[] rowOffsets = {-1, 0, 0, 1};
        int[] colOffsets = {0, -1, 1, 0};

        for (int i = 0; i < 4; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];

            // Check if the move to the neighboring state is valid and if the neighboring state is available
            if (Rules.isValidMove(newRow, newCol, State.row, State.col) && Rules.isAvailable(State.board[newRow][newCol])) {
                // Create a new State object for the neighboring state
                State neighbor = new State(newRow, newCol);
                neighbor.setParentState(neighbor);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

}
