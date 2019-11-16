/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligentsys;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author onsur
 */
public class UniformCost {
    public static void main(String[] args) {
        int n = 5;
        int nCars = 1;
        //int seed = (int)System.currentTimeMillis();
        int seed = 693436947;
        int[][] maze = Maze.getProblemInstance(n, nCars, seed);
        
        carPosition[] cars;
        cars = lookupCars(maze);
        
        Comparator<HeuristicsNode> nodeComparator = new Comparator<HeuristicsNode>() {
            @Override
            public int compare(HeuristicsNode h1, HeuristicsNode h2) {
                return h1.g - h2.g;
            }
        }; 
        
        ArrayList<HeuristicsNode> explored = null;
        PriorityQueue<HeuristicsNode> open = null;
        open.add(new HeuristicsNode(maze,cars,n));
        HeuristicsNode node;
        LinkedList<HeuristicsNode> solution = null;
        ArrayList<HeuristicsNode> successor = null;
        
        while(!open.isEmpty()){
            node = open.poll();
            if(isExplored(explored,node)){
                if(testGoal(node,n,nCars)){
                    solution = recoverPath(node);
                    node.show();
                    showSolution(solution);
                }
                node.addSuccessors();
                successor = node.successors;
                for (HeuristicsNode s : successor) {
                       open.add(s);  
                }
                explored.add(node);
            }  
        }
        System.out.println("Failure while doing  A star");
    }
    

    
    public static boolean testGoal(HeuristicsNode node,int n, int nCars){
        int k = 0;
        for (int i = 0; i < nCars; i++) {
            if(node.cars[i].y == n - 1)
                k++; 
        }
        return(k==nCars);
    }
    
    public static boolean isExplored(ArrayList<HeuristicsNode> explored, HeuristicsNode n){
        try{
            for (HeuristicsNode node : explored) {
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

    private static LinkedList<HeuristicsNode> recoverPath(HeuristicsNode node) {
        LinkedList<HeuristicsNode> sol = new LinkedList();
        while(node.previous != null){
            sol.add(node); 
            node = node.previous;
        }
        return sol;
    }
    
    private static void showSolution(LinkedList<HeuristicsNode> solution){
        for (HeuristicsNode n : solution) {
            if (n.step == null) {
                System.out.println("This is the initial  node");
                continue;
            }
            System.out.println(n.step);
        }
 
    }
}
