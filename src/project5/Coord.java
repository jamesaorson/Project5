package project5;

/**
  * What does it do?
  *
  * @author James Osborne
  * @version 1.0 
  * File: Coord.java
  * Created:  25 Oct 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     25 Oct 2016 – JAO – Created constructors, x, y, accessors, and mutators.
  * 
  * Description: 
  */
public class Coord {
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