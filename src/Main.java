import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Algorithem.BFS;
import Algorithem.DFS;
import Model.Move;
import Model.Rules;
import Model.State;

public class Main {
    // Hey
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<State> gameStates = new LinkedHashSet<>();
        List<State> Points = new ArrayList<>() ;


        try {
            BufferedReader reader = new BufferedReader(new FileReader("Ameer2/Ameer/Board.txt"));
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
                State state = new State(row, col, startRow, startCol, board);
                gameStates.add(state);
                Points.add(state);
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
                        DFS dfs = new DFS(new ArrayList<>(gameStates), new ArrayList<>());
                        List<State> dfsStates = dfs.search();
                        gameStates.addAll(dfsStates);
                        //System.out.println();
                        break;
                    case "bfs":
                        BFS bfs = new BFS(new ArrayList<>(gameStates), new ArrayList<>());
                        List<State> bfsStates = bfs.search();
                        gameStates.addAll(bfsStates);
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
