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
        int n = 10;
        int nCars = 1;
        //int seed = (int)System.currentTimeMillis();
        int seed = 6;
        int[][] maze = Maze.getProblemInstance(n, nCars, seed);
        
        carPosition[] cars;
        cars = lookupCars(maze,nCars);
        
        ArrayList<Node> explored = new ArrayList();
        System.out.println("llega hasta el arraylist");
        LinkedList<Node> open = new LinkedList();
        open.add(new Node(maze,cars,n));
        Node node;
        LinkedList<Node> solution = null;
        ArrayList<Node> successor = null;
        System.out.println("pre while");
        //node.s
        while(!open.isEmpty()){
            node = open.removeFirst();
            //node.show();
            if(!isExplored(explored,node)){
                if(testGoal(node,n,nCars)){
                    System.out.println("terminando");
                    solution = recoverPath(node);
                    node.show();
                    showSolution(solution);
                    System.exit(0);
                }
                node.addSuccessors();            
                successor = node.successors;
                for (Node s : successor) {
                       open.addLast(s);  
                }
                explored.add(node);
            }
        }
        System.out.println("Failure while doing  breathfirst");
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
            //System.out.println("se mete en el explored");
            for (Node node : explored) {
                if(n.state.equals(node.state)){
                    System.out.println("isExplored: true");
                    return true;
                }
            }
            
            System.out.println("isExplored: false");
            return false;
        } catch (NullPointerException e){   
            System.out.println("Erro: " + e);
            return true;
        }
    }
    
    
    private static carPosition[] lookupCars(int[][] maze, int n) {
        carPosition[] cars = new carPosition[n];
        int k = 0;
        for(int i = 0; i < maze.length; i++){
            if(maze[0][i] > 0){
                cars[k] = new carPosition(i,0);
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
