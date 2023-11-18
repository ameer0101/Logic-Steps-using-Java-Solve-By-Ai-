import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Algorithem.BFS;
import Algorithem.DFS;
import Algorithem.UCS;
import Model.Move;
import Model.Rules;
import Model.State;

public class Main {
    // Hey
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<State> gameStates = new ArrayList<>();
        List<State> goalState = new ArrayList<>();


        try {
            BufferedReader reader = new BufferedReader(new FileReader("Board2.txt"));
            int row = Integer.parseInt(reader.readLine());
            int col = Integer.parseInt(reader.readLine());

            String line;
            int rowIndex = 0;
            int[][] board = new int[row][col];

            int numStartPoints = Integer.parseInt(reader.readLine());
            for (int i = 0; i < numStartPoints; i++) {
                String[] startPoint = reader.readLine().split(" ");
                int startRow = Integer.parseInt(startPoint[0]);
                int startCol = Integer.parseInt(startPoint[1]);
                int cost = Integer.parseInt(startPoint[2]);
                State state = new State(row, col, startRow, startCol, board,cost);
                gameStates.add(state);
            }

            while((line = reader.readLine()) != null) {
                String[] values = line.split(" ");
                if (values.length != col) {
                    throw new IllegalArgumentException("Invalid number of values on line: " + line);
                }
                for (int i = 0; i < values.length; i++) {
                    board[rowIndex][i] = Integer.parseInt(values[i]);
                }
                rowIndex++;
            }



            while (Rules.isfinal(board,row,col)) {

                for (State currentState : gameStates) {
                    System.out.println("Player location: (" + currentState.startRow + ", " + currentState.startCol + ")");
                    if (Rules.isDead(currentState)) {

                        System.out.printf("You Lose !");
                        System.exit(0);
                    }
                }


                System.out.println("Enter the direction to move :");
                String direction = scanner.next();

                switch (direction.toLowerCase()) {
//                    case "w":
//                        Move.moveUp(state);
//                        break;
//                    case "s":
//                        Move.moveDown(state);
//                        break;
//                    case "a":
//                        Move.moveLeft(state);
//                        break;
//                    case "d":
//                        Move.moveRight(state);
//                        break;
                    case "n":
                        System.out.println("Number of states in the queue: " + gameStates.size());
                        break;
                    case "dfs":
                        DFS dfs = new DFS(gameStates.get(0),new ArrayList<>(gameStates), new ArrayList<>());
                        List<State> dfsStates = dfs.search();
                        gameStates.addAll(dfsStates);

                        break;
                    case "bfs":
                        BFS bfs = new BFS(gameStates.get(0), new ArrayList<>(gameStates), new ArrayList<>());
                        List<State> bfsStates = bfs.search();
                        gameStates.addAll(bfsStates);
                        break;
                    case "ucs":
                        UCS ucs = new UCS(gameStates.get(0),new ArrayList<>(gameStates), new ArrayList<>());
                        List<State> ucsStates = ucs.search();
                        gameStates.addAll(ucsStates);

                        break;




                    default:
                        System.out.println("Invalid direction. Try again.");
                }

            }
            System.out.println("You Win !");
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }
}
