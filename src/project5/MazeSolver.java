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
  * File: <filename>
  * Created:  <current date>
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     XX month XXXX – JAO – 
  * 
  * Description: 
  */
public class MazeSolver {
    private Queue<Integer> moves;
    private static Location[] locations;
    
    public MazeSolver() {
        moves = new LinkedList<Integer>();
        locations = new Location[0];
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileInput = new Scanner(System.in);
        File file = new File(fileInput.nextLine());
        
        fileInput = new Scanner(file);
        
        int rows;
        int columns;
        
        rows = fileInput.nextInt();
        columns = fileInput.nextInt();
        
        locations = new Location[rows * columns];
        
        String wallType;
        Coord spot;
        
        fileInput.useDelimiter("");
        
        for (int i = 0; i < rows * columns; ++i) {
            wallType = fileInput.next();
            
            switch (wallType) {
                case "X":
                    locations[i].setType(MazeSquare.wall);
                    break;
                case ".":
                    locations[i].setType(MazeSquare.open);
                    break;
                case "S":
                    locations[i].setType(MazeSquare.start);
                    break;
                case "T":
                    locations[i].setType(MazeSquare.finish);
                    break;
                default:
                    locations[i].setType(MazeSquare.undefined);
                    break;
            }
            
            spot = new Coord(i / 6, i % 6);
        }
        
        for (int i = 0; i < rows * columns; ++i) {
            System.out.println(locations[i].getCoord().getX()+ " " + locations[i].getCoord().getY());
        }
    }
}