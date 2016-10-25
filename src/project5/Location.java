package project5;

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
public class Location {
    
    private Object type;
    private Coord coord;
    
    public Location() {
        this(null, -1, -1);
    }
    
    /**
     *
     * @param square
     * @param x
     * @param y
     */
    public Location(Object square, int x, int y) {
        type = square;
        coord = new Coord(x, y);
    }
    
    public Object getType() {
        return type;
    }
    
    public Coord getCoord() {
        return coord;
    }
    
    public void setType(Object t) {
        type = t;
    }
    
    public void setCoord(Coord c) {
        coord = c;
    }
}