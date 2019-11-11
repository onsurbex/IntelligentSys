/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligentsys;

import java.util.ArrayList;
import java.util.Random;

/**
  * This method generates a new problem instance.
  * Cells with value 0 means empty cells. Cells with value -1 are walls.
  * Cells with value 1..n are occupied by the i-th car.
  *
  * @param n size of the maze
  * @param nCars number of cars
  * @param seed for the random generator
  *
  * @return a maze (problem instance)
  */
public class Maze{
    public static int [][] getProblemInstance(int n, int nCars, int seed){
        int[][] maze = new int[n][n];
        Random gen = new Random(seed);

        // number of walls
        int nWalls = (int) (n*(n-2) * 0.2);

        // placing walls
        for(int i = 0; i < nWalls; i++)
            maze[ gen.nextInt(n-2) + 1 ][ gen.nextInt(n)] = -1;

        // placing cars, labelled as 1, 2, ..., nCars
        if (nCars > n){
            System.out.println("\n** Error **, number of cars must be <= dimension of maze!!\n");
            System.exit(0);
        }

        ArrayList<Integer> list = new ArrayList<Integer>(n);
        for(int i=0;i<n;i++)
            list.add(i);

        int pos;
        for(int c=0;c<nCars;c++){
            pos = list.remove( gen.nextInt(list.size()));
            maze[0][pos] = c+1;}

        return maze;
    }
}