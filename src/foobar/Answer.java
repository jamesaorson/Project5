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
            
            brokenWallLocations[wall.getY()][wall.getX()] = new Location(0, wall.getY(), wall.getX(), -1);
            
            while (!pathTaken.isEmpty()) {
                Location currLocation = pathTaken.remove();

                if (currLocation.getType() == 3) {
                    found = true;
                    endingLocation = currLocation;
                    break;
                }

                int currDist = currLocation.getDistance();
                int x = currLocation.getX();
                int y = currLocation.getY();

                if (x > 0) {
                    setAdjacentDistances(brokenWallLocations[y][x - 1], currDist);
                }
                if (x < columns - 1) {
                    setAdjacentDistances(brokenWallLocations[y][x + 1], currDist);
                }
                if (y > 0) {
                    setAdjacentDistances(brokenWallLocations[y - 1][x], currDist);
                }
                if (y < rows - 1) {
                    setAdjacentDistances(brokenWallLocations[y + 1][x], currDist);
                }
            }

            if (found) {
                System.out.println("Distance traveled = " + endingLocation.getDistance());
                
                if (result > endingLocation.getDistance()) {
                    result = endingLocation.getDistance();
                    System.out.println("Faster route: " + result);
                }
            }
            else {
                System.out.println("Maze is unsolvable");
            }
            
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    locations[i][j].setDist(-1);
                }
            }
        }
        
        return result;
    }
    
    private static void setAdjacentDistances(Location location, int adjDist) {
        if (location.getDistance() == -1 && location.getType() != 1) {
            location.setDist(adjDist + 1);
            
            pathTaken.add(location);
        }
    }
    
    public static class Coord {
        private int x;
        private int y;

        public Coord() {
            this(-1, -1);
        }

        public Coord(int numX, int numY) {
            x = numX;
            y = numY;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int num) {
            x = num;
        }

        public void setY(int num) {
            y = num;
        }
    }
    
    public static class Location {
        private int type;
        private Coord coord;
        private int distance;
    
        public Location() { }
        public Location(Location l) {
            type = l.getType();
            coord = new Coord(l.getX(), l.getY());
            distance = l.getDistance();
        }
    
        public Location(int t, int x, int y, int d) {
            type = t;
            coord = new Coord(x, y);
            distance = d;
        }
    
        public void printCoord() {
            System.out.print(coord.getY() + " " + coord.getX());
        }

        public int getType() {
            return type;
        }

        public Coord getCoord() {
            return coord;
        }

        public int getX() {
            return coord.getX();
        }

        public int getY() {
            return coord.getY();
        }

        public int getDistance() {
            return distance;
        }

        public void setType(int t) {
            type = t;
        }

        public void setCoord(Coord c) {
            coord = c;
        }

        public void setX(int x) {
            coord.setX(x);
        }

        public void setY(int y) {
            coord.setY(y);
        }

        public void setDist(int d) {
            distance = d;
        }
    }
    
    public static void main(String[] args) {
        int[][] maze = {{0, 1, 0},
                        {1, 0, 1},
                        {0, 0, 0}};
        System.out.println("Ending distance: " + answer(maze));
    }
}