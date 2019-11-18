/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intelligentsys;

import java.util.ArrayList;

/**
 *
 * @author onsur
 */
public class Node {
    public int n;
    public int[][] state;
    public carPosition[] cars;
    public Node previous;
    public ArrayList<Node> successors;
    public String step;
    
    public Node(int[][] state, carPosition[] car, int n){
        this.state = state;
        this.cars = car;
        this.n = n;
        this.previous = null;
        this.step = null;
        this.successors = new ArrayList<Node>();
    }
    
    public Node(int[][] state, carPosition[] car, int n, Node previous, String step){
        this.state = state;
        this.cars = new carPosition[car.length];
        for (int i = 0; i < this.cars.length; i++) {
            this.cars[i] = new carPosition(0,0);
        }
        for (int i = 0; i < car.length; i++) {
            this.cars[i].x = car[i].x;
            this.cars[i].y = car[i].y;
        }
        this.n = n;
        this.previous = previous;
        this.step = step;
        this.successors = new ArrayList<Node>();
    }
    
    public void addSuccessors() throws ArrayIndexOutOfBoundsException {
        int [][] newState = this.copyMatrix(this.state);
        carPosition[] car = new carPosition[this.cars.length];
        for (int i = 0; i < this.cars.length; i++) {
            car[i] =  new carPosition(this.cars[i].x,this.cars[i].y);
        }
        Node nodeAux;
        Node parent;
       // try {
            for(int i = 0; i < this.cars.length; i++){
                if(this.cars[i].y > 0 && state[car[i].y - 1][car[i].x] != -1){
                    // UP
                    for (int j = 0; j < this.cars.length; j++) {
                        car[i] = new carPosition(this.cars[i].x,this.cars[i].y);
                    }
                    newState = this.copyMatrix(this.state);
                    newState[car[i].y][car[i].x] = 0;
                    car[i].y -= 1;
                    newState[car[i].y][car[i].x] = i + 1;
                    nodeAux = new Node(newState,car,this.n,this, "UP");
                    this.successors.add(nodeAux);
                }
                if(this.cars[i].y < (n - 1) && state[car[i].y + 1][car[i].x] != -1) {
                    // DOWN
                    for (int j = 0; j < this.cars.length; j++) {
                        car[i] = new carPosition(this.cars[i].x,this.cars[i].y);
                    }
                    newState = this.copyMatrix(this.state);
                    newState[car[i].y][car[i].x] = 0;
                    car[i].y += 1;
                    newState[car[i].y][car[i].x] = i + 1;
                    nodeAux = new Node(newState,car,this.n,this, "DOWN");
                    this.successors.add(nodeAux);
                }
                if(this.cars[i].x < (n - 1) && state[car[i].y][car[i].x + 1] != -1){
                    // RIGHT 
                    for (int j = 0; j < this.cars.length; j++) {
                        car[i] = new carPosition(this.cars[i].x,this.cars[i].y);
                    }
                    newState = this.copyMatrix(this.state);
                    newState[car[i].y][car[i].x] = 0;
                    car[i].x += 1;
                    newState[car[i].y][car[i].x] = i + 1;
                    nodeAux = new Node(newState,car,this.n,this, "RIGHT");
                    this.successors.add(nodeAux);
                } 
                if(this.cars[i].x > 0 && state[car[i].y][car[i].x - 1] != -1){
                    //LEFT
                    for (int j = 0; j < this.cars.length; j++) {
                        car[i] = new carPosition(this.cars[i].x,this.cars[i].y);
                    }
                    newState = this.copyMatrix(this.state);
                    newState[car[i].y][car[i].x] = 0;
                    car[i].x -= 1;
                    newState[car[i].y][car[i].x] = i + 1;
                    nodeAux = new Node(newState,car,this.n,this, "LEFT");
                    this.successors.add(nodeAux);
                } 
            }
//        } catch(ArrayIndexOutOfBoundsException e){
//           System.out.println("Error:" + e.toString());
//        }
    }
    
    public int[][] copyMatrix(int [][] original){
        int [][] newMatrix = new int[original.length][original.length];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                newMatrix[i][j] = original[i][j];
            }
        }
        return newMatrix;
    }
    
    public void show(){
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print( this.state[i][j]+"\t");
            }
            System.out.print("\n");
        }
    }
    
    
}
