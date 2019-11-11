/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligentsys;

/**
 *
 * @author onsur
 */
public class DepthFirst {
    public static void main(String[] args) {
        int n = 5;
        int nCars = 1;
        //int seed = (int)System.currentTimeMillis();
        int seed = 693436947;
        int[][] maze = Maze.getProblemInstance(n, nCars, seed);    
    }
}
