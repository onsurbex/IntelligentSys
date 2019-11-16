/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligentsys;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 *
 * @author onsur
 */
public class BreathFirst {
    public static void main(String[] args) {
        int n = 5;
        int nCars = 1;
        //int seed = (int)System.currentTimeMillis();
        int seed = 2019;
        int[][] maze = Maze.getProblemInstance(n, nCars, seed);
        
        carPosition[] cars;
        cars = lookupCars(maze,n);
        
        ArrayList<Node> explored = new ArrayList();
        System.out.println("llega hasta el arraylist");
        LinkedList<Node> open = new LinkedList();
        open.add(new Node(maze,cars,n));
        Node node;
        LinkedList<Node> solution = null;
        ArrayList<Node> successor = null;
        System.out.println("pre while");
        
        while(!open.isEmpty()){
            node = open.getFirst();
            
            if(isExplored(explored,node))
                if(testGoal(node,n,nCars)){
                    System.out.println("terminando");
                    solution = recoverPath(node);
                    node.show();
                    showSolution(solution);
                    break;
                }
                System.out.println("añadido sucesor");
                node.addSuccessors();
                successor = node.successors;
                for (Node s : successor) {
                       open.addLast(s);  
                }
                explored.add(node);
             
        }
        System.out.println("Failure while doing  breathfirst");
    }
    

    
    public static boolean testGoal(Node node,int n, int nCars){
        int k = 0;
        for (int i = 0; i < nCars; i++) {
            if(node.cars[i].y == n)
                k++; 
        }
        return(k==nCars);
    }
    
    public static boolean isExplored(ArrayList<Node> explored, Node n){
        try{
            System.out.println("se mete en el explored");
            for (Node node : explored) {
                if(n.state.equals(node.state)){
                    System.out.println("true");
                    return true;
            }
                
            }
            System.out.println("false");
            return false;
        } catch (NullPointerException e){
            return false;
        }
    }
    
    
    private static carPosition[] lookupCars(int[][] maze, int n) {
        carPosition[] cars = new carPosition[n];
        int k = 0;
        for(int i = 0; i < maze.length; i++){
            if(maze[0][i] > 0){
                cars[k] = new carPosition(0,i);
                k++;
            }
        }
        return cars;
    }

    private static LinkedList<Node> recoverPath(Node node) {
        LinkedList<Node> sol = new LinkedList();
        while(node.previous != null){
            sol.add(node); 
            node = node.previous;
        }
        return sol;
    }
    
    private static void showSolution(LinkedList<Node> solution){
        for (Node n : solution) {
            if (n.step == null) {
                System.out.println("This is the initial  node");
                continue;
            }
            System.out.println(n.step);
        }
 
    }
}
