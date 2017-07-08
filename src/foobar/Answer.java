package foobar;

import java.util.*;

public class Answer {    
    private static Queue<Location> pathTaken;
    private static Location[][] locations;
    private static ArrayList<Location> walls;
    private static Location startingLocation;
    private static Location endingLocation;
    private static int rows;
    private static int columns;
    private static int result = Integer.MAX_VALUE;
    
    public static int answer(int[][] maze) {
        pathTaken = new LinkedList<Location>();
        rows = maze.length;
        columns = maze[0].length;
        startingLocation = new Location(2, 0, 0, 1);
        endingLocation = new Location(3, columns - 1, rows - 1, -1);

        locations = new Location[rows][columns];
        walls = new ArrayList<Location>();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                switch (maze[i][j]) {
                    case 0:
                        locations[i][j] = new Location(0, j, i, -1);
                        break;
                    case 1:
                        locations[i][j] = new Location(1, j, i, -1);
                        walls.add(new Location(1, j, i, -1));
                        break;
                }
            }
        }
        
        locations[0][0] = startingLocation;
        locations[rows - 1][columns - 1] = endingLocation;

        for (Location wall : walls) {
            boolean found = false;
            Location[][] brokenWallLocations = new Location[rows][columns];
            
            pathTaken.add(startingLocation);
            
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    brokenWallLocations[i][j] = new Location(locations[i][j]);
                }
            }
            
            brokenWallLocations[wall.y][wall.x] = new Location(0, wall.y, wall.x, -1);
            
            while (!pathTaken.isEmpty()) {
                Location currLocation = pathTaken.remove();

                if (currLocation.type == 3) {
                    found = true;
                    endingLocation = currLocation;
                    break;
                }

                int currDist = currLocation.distance;
                int x = currLocation.x;
                int y = currLocation.y;

                if (x > 0) {
                    setAdjacentDistances(brokenWallLocations[y][x - 1], currDist);
                }
                if (x < columns - 1) {
                    setAdjacentDistances(brokenWallLocations[y][x + 1], currDist);
                }
                if (y > 0) {
                    try {
                        setAdjacentDistances(brokenWallLocations[y - 1][x], currDist);
                    }
                    catch(Exception e) {
                        throw e;
                    }
                }
                if (y < rows - 1) {
                    setAdjacentDistances(brokenWallLocations[y + 1][x], currDist);
                }
            }

            if (found) {
                System.out.println("Distance traveled = " + endingLocation.distance);
                
                if (result > endingLocation.distance) {
                    result = endingLocation.distance;
                    System.out.println("Faster route: " + result);
                }
            }
            else {
                System.out.println("Maze is unsolvable");
            }
            
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    locations[i][j].distance = -1;
                }
            }
        }
        
        return result;
    }
    
    private static void setAdjacentDistances(Location location, int adjDist) {
        if (location.distance == -1 && location.type != 1) {
            location.distance = adjDist + 1;            
            pathTaken.add(location);
        }
    }
    
    public static class Location {
        public int type;
        public int x;
        public int y;
        public int distance;
    
        public Location() { }
        public Location(Location l) {
            type = l.type;
            x = l.x;
            y = l.y;
            distance = l.distance;
        }
        public Location(int t, int i, int j, int dist) {
            type = t;
            x = i;
            y = j;
            distance = dist;
        }
    }
    
    public static void main(String[] args) {
        int[][] maze = {{0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0}};
        System.out.println("Ending distance: " + answer(maze));
    }
}