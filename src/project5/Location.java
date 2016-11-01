package project5;

/**
  * Provides an object to hold various information about a 2D location.
  *
  * @author James Osborne
  * @version 1.0 
  * File: Location.java
  * Created:  25 Oct 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     25 Oct 2016 – JAO – Created constructors, printCoord(), accessors, and
  *     mutators.
  *     01 Nov 2016 - JAO - Changed type from Object to MazeSolver.MazeType
  * 
  * Description: This provides an easy way to keep track of the information
  * within the 2D maze.
  */
public class Location {
    //Made a general Object so the location could be defined by any means deemed
    //necessary
    private MazeSolver.MazeType type;
    private Coord coord;
    private int distance;
    
    /**
      * Default constructor for Location object.
      */
    public Location() {
        //-1 is my chosen number for an undefined value for x, y, and d.
        this(MazeSolver.MazeType.Undefined, -1, -1, -1);
    }
    
    /**
      * Constructor which initializes the attributes of Location.
      * 
      * @param t Type of location the spot in the maze is.
      * @param x X coordinate for the location.
      * @param y Y coordinate for the location.
      * @param d Distance from the start of the maze.
      */
    public Location(MazeSolver.MazeType t, int x, int y, int d) {
        type = t;
        coord = new Coord(x, y);
        distance = d;
    }
    
    /**
      * Method which prints coord in format <Y X>
      */
    public void printCoord() {
        System.out.print(coord.getY() + " " + coord.getX());
    }
    
    /**
      * Accessor for type attribute.
      * 
      * @return type attribute.
      */
    public MazeSolver.MazeType getType() {
        return type;
    }
    
    /**
      * Accessor for coord attribute.
      * 
      * @return coord attribute.
      */
    public Coord getCoord() {
        return coord;
    }
    
    /**
      * Accessor for x of coord attribute.
      * 
      * @return x of coord attribute.
      */
    public int getX() {
        return coord.getX();
    }
    
    /**
      * Accessor for y of coord attribute.
      * 
      * @return y of coord attribute.
      */
    public int getY() {
        return coord.getY();
    }
    
    /**
      * Accessor for distance attribute.
      * 
      * @return distance attribute.
      */
    public int getDistance() {
        return distance;
    }
    
    /**
      * Accessor for type attribute.
      * 
      * @return type attribute.
      */
    public void setType(MazeSolver.MazeType t) {
        type = t;
    }
    
    /**
      * Mutator for coord attribute.
      * 
      * @param c Value for coord.
      */
    public void setCoord(Coord c) {
        coord = c;
    }
    
    /**
      * Mutator for x of coord attribute.
      * 
      * @param x Value for x of coord.
      */
    public void setX(int x) {
        coord.setX(x);
    }
    
    /**
      * Mutator for y of coord attribute.
      * 
      * @param y Value for y of coord.
      */
    public void setY(int y) {
        coord.setY(y);
    }
    
    /**
      * Mutator for distance attribute.
      * 
      * @param d Value for distance.
      */
    public void setDist(int d) {
        distance = d;
    }
}