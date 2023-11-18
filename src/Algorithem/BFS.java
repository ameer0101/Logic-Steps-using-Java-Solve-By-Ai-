package Algorithem;

import Model.Rules;
import Model.State;

import java.util.*;

public class BFS {
    private List<State> startStates;
    private List<State> states;
    private State state;

    public BFS(State state, List<State> startStates, List<State> states) {  // Modify this line
        this.state = state;  // Add this line
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


            System.out.println("Current state: " + currentState);
            currentState.printBoard();

            int currentRow = currentState.getStartRow();
            int currentCol = currentState.getStartCol();

            List<State> neighbors = getNeighbors(currentRow, currentCol);

            for (State neighbor : neighbors) {
                if (Rules.isValidMove(neighbor.getStartRow(), neighbor.getStartCol(), state.row, state.col) && Rules.isAvailable(state.board[neighbor.getStartRow()][neighbor.getStartCol()]) ) {
                    neighbor.setParentState(currentState);
                    queue.add(neighbor);
                    visited.add(neighbor);
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
