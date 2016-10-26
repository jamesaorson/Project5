package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

/**
  * What does it do?
  *
  * @author James Osborne
  * @version 1.0 
  * File: MazeSolver.java
  * Created:  25 Oct 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     25 Oct 2016 – JAO – Created default constructor, moves, locations, and
  *     solve method. Solve method reads input from the file, initializes the
  *     locations variable, and prints out locations demonstrating parsing
  *     has been done correctly.
  * 
  * Description: 
  */
public class MazeSolver {
    private Queue<Integer> moves;
    private Location[][] locations;
    
    public MazeSolver() {
        moves = new LinkedList<Integer>();
        locations = new Location[0][0];
    }
    
    public void solve(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner fileInput = new Scanner(file);
        
        int rows;
        int columns;
        
        rows = fileInput.nextInt();
        columns = fileInput.nextInt();
        fileInput.nextLine();
        
        locations = new Location[rows][columns];
        
        String wallType;
        
        for (int i = 0; i < rows; ++i) {
            
            wallType = fileInput.nextLine();
            
            for (int j = 0; j < columns; ++j) {
                
                switch (wallType.charAt(j)) {
                    case 'X':
                        locations[i][j] = new Location(MazeSquare.wall, i, j);
                        break;
                    case '.':
                        locations[i][j] = new Location(MazeSquare.open, i, j);
                        break;
                    case 'S':
                        locations[i][j] = new Location(MazeSquare.start, i, j);
                        break;
                    case 'T':
                        locations[i][j] = new Location(MazeSquare.finish, i, j);
                        break;
                    /*default:
                        locations[i][j] = new Location(MazeSquare.undefined, i, j);
                        break;*/
                }
            }
        }
        
        
        
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                System.out.print("<");
                locations[i][j].printCoord();
                System.out.print(">\n");
            }
        }
    }
}