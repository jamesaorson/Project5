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