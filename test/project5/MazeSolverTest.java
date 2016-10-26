/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5;

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Owner
 */
public class MazeSolverTest {
    
    public MazeSolverTest() {
    }

    /**
     * Test of solve method, of class MazeSolver.
     */
    @Test
    public void testSolve() throws Exception {
        MazeSolver solver = new MazeSolver();
        solver.solve("test.in");
    }
    
}
