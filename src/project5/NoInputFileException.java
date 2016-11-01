package project5;

/**
  * Provides exception if an input file is not found or specified.
  *
  * @author James Osborne
  * @version 1.0 
  * File: NoInputFileEsception.java
  * Created:  01 Nov 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     01 Nov 2016 – JAO – Created constructor.
  * 
  * Description: The creates an exception with a custom message
  * for use with MazeSolver.java.
  */
public class NoInputFileException extends Exception {
    public NoInputFileException(String err) {
        super(err);
    }
}
