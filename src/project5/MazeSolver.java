package project5;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.LinkedList;

/**
  * This class implements a solver for 2D mazes parsed from text files.
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
  *     26 Oct 2016 - JAO - Created distanceSearch() and backtracking().
  *     Succeeded in getting correct path printed out. Only question is whether
  *     or not this is the most efficient method for printing out the shortest
  *     path, or if I have more loops than necessary.
  *     01 Nove 2016 - JAO - Moved MazeType into the MazeSolver class.
  * 
  * Description: This solver implements a BFS (Breadth First Search) algorithm
  * for solving the maze. After parsing the input file, the BFS begins at the
  * startingLocation, running either until all viable paths have been searched,
  * or the end of the maze has been found. This solver also prints the shortest
  * path taken after it is finished solving.
  */
public class MazeSolver {
    //Enum for the types of spaces within the maze. No access identifier so it
    //is visible throughout the project, most specifically for Location.java.
    enum MazeType {
        Wall,
        Open,
        Start,
        Finish,
        Undefined;
    }
    
    //Will be used to implement the BFS (Breadth First Search).
    private Queue<Location> pathTaken;
    //2D array holding the maze's layout.
    private Location[][] locations;
    private Location startingLocation;
    private Location endingLocation;
    private int rows;
    private int columns;
    //File that the maze is parsed from.
    private File file;
    
    /**
      * Default constructor for MazeSolver object.
      */
    public MazeSolver() {
        this(null);
    }
    
    /**
      * Constructor which specifies the file to be parsed.
      *
      * @param filename File to be parsed as the maze.
      */
    public MazeSolver(String filename) {
        pathTaken = new LinkedList<Location>();
        locations = new Location[0][0];
        rows = 0;
        columns = 0;
        
        //Sets file as long as an explicit string is provided.
        if (filename != null) {
            file = new File(filename);
        }
    }
    
    /**
      * Solves the maze through use of a BFS (Breadth First Search).
      *
      * @param filename File to be parsed as the maze. Can set to null if maze 
      * was specified in constructor.
      * @throws If file was not found in the working directory.
      */
    public void solve(String filename) throws NoInputFileException {        
        parse(filename);
        
        //Tracks whether or not the endingLocation is found.
        boolean found = false;

        //Search through until queue is empty.
        while (!pathTaken.isEmpty()) {
            Location currLocation = pathTaken.remove();

            //Break out of while loop early if endingLocation is found.
            if (currLocation == endingLocation) {
                found = true;
                break;
            }

            int currDist = currLocation.getDistance();
            int x = currLocation.getX();
            int y = currLocation.getY();

            //Sets distances of adjacent spots, but first makes sure that the
            //spot to set is not past the bounds of the array.
            if (x > 0) {
                setAdjacentDistances(locations[y][x - 1], currDist);
            }
            if (x < columns - 1) {
                setAdjacentDistances(locations[y][x + 1], currDist);
            }
            if (y > 0) {
                setAdjacentDistances(locations[y - 1][x], currDist);
            }
            if (y < rows - 1) {
                setAdjacentDistances(locations[y + 1][x], currDist);
            }
        }

        //Print out either the path taken from the start to end, or notify the
        //user that the maze was unsolvable.
        if (found) {
            printPath(endingLocation);
            System.out.println("Distance traveled = " 
                               + endingLocation.getDistance());
        }
        else {
            System.out.println("Maze is unsolvable");
        }
    }
    
    /**
      * Parses the maze file.
      *
      * @param filename File to be parsed as the maze. Can set to null if maze 
      * was specified in constructor.
      * @throws If file was not found by scanner in the working directory.
      */
    private void parse(String filename) throws NoInputFileException {
        Scanner fileInput;
        
        //Sets file if one was specified
        if (filename != null) {
            file = new File(filename);
        }
        
        if (file == null) {
            throw new NoInputFileException("No input file was specified");
        }
        
        try {
            fileInput = new Scanner(file);
        }
        
        catch(FileNotFoundException e) {
            throw new NoInputFileException("File was not in working directory"
                                           + "or specified name is incorrect");
        }

        rows = fileInput.nextInt();
        columns = fileInput.nextInt();
        //Skips whitespace left at end of first line.
        fileInput.nextLine();

        locations = new Location[rows][columns];

        String row;

        for (int i = 0; i < rows; ++i) {
            row = fileInput.nextLine();

            //Parses through the line, setting each square to its type,
            //position, and undefined distance (-1).
            for (int j = 0; j < columns; ++j) {
                switch (row.charAt(j)) {
                    case 'X':
                        locations[i][j] = new Location(MazeType.Wall,
                                                       j, i, -1);

                        break;
                    case '.':
                        locations[i][j] = new Location(MazeType.Open,
                                                       j, i, -1);

                        break;
                    case 'S':
                        locations[i][j] = new Location(MazeType.Start,
                                                       j, i, 0);

                        //Adds starting location into the queue.
                        pathTaken.add(locations[i][j]);
                        startingLocation = locations[i][j];
                        break;
                    case 'T':
                        locations[i][j] = new Location(MazeType.Finish,
                                                       j, i, -1);

                        endingLocation = locations[i][j];
                        break;
                }
            }
        }
    }
    
    /**
      * Sets distance of the location, specified by x and y,
      * as long as location is not already specified or is a wall.
      *
      * @param location Location to set distance of
      * @param adjDist Distance of the adjacent location
      * @throws If file was not found by scanner in the working directory.
      */
    private void setAdjacentDistances(Location location, int adjDist) {
        //Only sets a distance if the location was not defined before and
        //is also not a wall.
        if (location.getDistance() == -1 && 
            location.getType() != MazeType.Wall) {
            
            location.setDist(adjDist + 1);
            //Put the location onto the queue to continue the BFS.
            pathTaken.add(location);
        }
    }
    
    /**
      * Prints out the path from startingLocation to specified location.
      *
      * @param currLocation Initial location to print the path to.
      */
    private void printPath(Location currLocation) {
        //Ending condition of recursion. If we have found the startingLocation,
        //the printing can start.
        if (currLocation == startingLocation) {
            System.out.print("<");
            currLocation.printCoord();
            System.out.print(">\n");
        }
        else {
            Location nextLocation = null;
            int x = currLocation.getX();
            int y = currLocation.getY();
            int nextDist = currLocation.getDistance() - 1;
            
            //Searches for nextLocation in path back to the start.
            //Similar to process of setting distances. Search in each direction,
            //making sure not to run off ends of the array.
            if (nextLocation == null && x > 0) {
                nextLocation = backtracking(locations[y][x - 1], nextDist);
            }
            if (nextLocation == null && x < columns - 1) {
                nextLocation = backtracking(locations[y][x + 1], nextDist);
            }
            if (nextLocation == null && y > 0) {
                nextLocation = backtracking(locations[y - 1][x], nextDist);
            }
            if (nextLocation == null && y < rows - 1) {
                nextLocation = backtracking(locations[y + 1][x], nextDist);
            }
            
            //Recursively call printPath based on the determined nextLocation.
            printPath(nextLocation);
            
            System.out.print("<");
            currLocation.printCoord();
            System.out.print(">\n");
        }
    }
    
    /**
      * Returns next location for the path from finish to start.
      *
      * @param location Location being checked for the path.
      * @param nextDist Distance of next step in solution.
      * 
      * @return location which holds the next distance, and null
      * if one is not found.
      */
    private Location backtracking(Location location, int nextDist) {
        //If the location has the correct distance, return it, otherwise,
        //return null to notify that it was not the next one.
        if (location.getDistance() == nextDist) {
            return location;
        } 
        else {
            return null;
        }
    }
}