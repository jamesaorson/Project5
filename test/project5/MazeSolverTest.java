/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5;

import java.io.FileNotFoundException;
import org.junit.Test;

/**
 * @author James Osborne
 * File: MazeSolverTest.java
 * Created:  25 Oct 2016
 */
public class MazeSolverTest {
    
    public MazeSolverTest() {
    }

    /**
     * Test of solve method, of class MazeSolver.
     */
    @Test
    public void testSolve() throws FileNotFoundException {
        MazeSolver solver = new MazeSolver();
        solver.solve(null);
    }
}
