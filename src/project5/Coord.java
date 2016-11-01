package project5;

/**
  * Provides a 2D coordinate object.
  *
  * @author James Osborne
  * @version 1.0 
  * File: Coord.java
  * Created:  25 Oct 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     25 Oct 2016 – JAO – Created constructors, x, y, accessors, and mutators.
  * 
  * Description: This provides an easy, all-in-one data type to help
  * track coordinates in 1D or 2D situations.
  */
public class Coord {
    private int x;
    private int y;
    
    /**
      * Default constructor for Coord object.
      */
    public Coord() {
        this(-1, -1);
    }
    
    /**
      * Constructor for Coord object which initializes x and y coordinates.
      * 
      * @param x X coordinate.
      * @param y Y coordinate.
      */
    public Coord(int numX, int numY) {
        x = numX;
        y = numY;
    }
    
    /**
      * Accessor for x attribute.
      * 
      * @return x attribute.
      */
    public int getX() {
        return x;
    }
    
    /**
      * Accessor for y attribute.
      * 
      * @return y attribute.
      */
    public int getY() {
        return y;
    }
    
    /**
      * Mutator for x attribute.
      * 
      * @param num Value for x.
      */
    public void setX(int num) {
        x = num;
    }
    
    /**
      * Mutator for y attribute.
      * 
      * @param num Value for y.
      */
    public void setY(int num) {
        y = num;
    }
}