/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligentsys;

import static intelligentsys.BreathFirst.isExplored;
import static intelligentsys.BreathFirst.testGoal;
import java.util.ArrayList;
import java.util.LinkedList;

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
        
         carPosition[] cars;
        cars = lookupCars(maze);
        
        ArrayList<Node> explored = null;
        LinkedList<Node> open = null;
        open.add(new Node(maze,cars,n));
        Node node;
        LinkedList<Node> solution = null;
        ArrayList<Node> successor = null;
        
        
         while(!open.isEmpty()){
            node = open.getLast();
            if(isExplored(explored,node)){
                if(testGoal(node,n,nCars)){
                    solution = recoverPath(node, solution);
                    node.show();
                    showSolution(solution);
                }
                node.addSuccessors();
                successor = node.successors;
                for (Node s : successor) {
                       open.addFirst(s); 
                    
                }
                explored.add(node);
            }  
        }
        System.out.println("Failure while doing  depthfirst");
    }
    

   public static boolean testGoal(Node node,int n, int nCars){
        int k = 0;
        for (int i = 0; i < nCars; i++) {
            if(node.cars[i].y == n - 1)
                k++; 
        }
        return(k==nCars);
    }
    
    public static boolean isExplored(ArrayList<Node> explored, Node n){
        try{
            for (Node node : explored) {
                if(n.state.equals(node.state))
                    return true;
            }
            return false;
        } catch (NullPointerException e){
            return false;
        }
    }
    
    
    private static carPosition[] lookupCars(int[][] maze) {
        carPosition[] cars = null;
        int k = 0;
        for(int i = 0; i < maze.length; i++){
            if(maze[0][i] > 0){
                cars[k] = new carPosition(0,i);
                k++;
            }
        }
        return cars;
    }

    private static LinkedList<Node> recoverPath(Node node, LinkedList<Node> sol) {
        while(node != null){
            sol.add(node);
            recoverPath(node.previous, sol);
        }
        return sol;
    }
    
    private static void showSolution(LinkedList<Node> solution){
        for (Node n : solution) {
            if (n.step == null) {
                System.out.println("This is the initial  node");
                break;
            }
            System.out.println(n.step);
        }
 
    }
}
