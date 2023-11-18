package Algorithem;

import Model.Rules;
import Model.State;

import java.util.*;

public class UCS {
    private List<State> startStates;
    private List<State> states;
    private State state;

    public UCS(State state, List<State> startStates, List<State> states) {
        this.state = state;
        this.startStates = startStates;
        this.states = states;
    }

    public List<State> search() {
        PriorityQueue<State> queue = new PriorityQueue<>();
        Map<State, Integer> visited = new HashMap<>(); // Change this line

        for (State startState : startStates) {
            queue.add(startState);
            visited.put(startState, 0); // Add this line
        }

        while (!queue.isEmpty()) {
            State currentState = queue.poll();

            states.add(currentState);

            System.out.println("Current state: " + currentState);
            currentState.printBoard();
            System.out.println("Cost for current state: " + visited.get(currentState));
            int currentRow = currentState.getStartRow();
            int currentCol = currentState.getStartCol();

            List<State> neighbors = getNeighbors(currentRow, currentCol);

            for (State neighbor : neighbors) {
                if (Rules.isValidMove(neighbor.getStartRow(), neighbor.getStartCol(), state.row, state.col) && Rules.isAvailable(state.board[neighbor.getStartRow()][neighbor.getStartCol()]) ) {
                    neighbor.setParentState(currentState);
                    queue.add(neighbor);
                    visited.put(neighbor, visited.get(currentState) + 1); // Change this line
                    state.board[neighbor.getStartRow()][neighbor.getStartCol()] -= 1;
                }
            }
        }
        return states;
    }


    private List<State> getNeighbors(int row, int col) {
        List<State> neighbors = new ArrayList<>();

        int[] rowOffsets = {-1, 0, 0, 1};
        int[] colOffsets = {0, -1, 1, 0};

        for (int i = 0; i < 4; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];

            if (Rules.isValidMove(newRow, newCol, state.row, state.col) && Rules.isAvailable(state.board[newRow][newCol])) {
                State neighbor = new State(newRow, newCol);
                neighbor.setParentState(neighbor);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }
}
