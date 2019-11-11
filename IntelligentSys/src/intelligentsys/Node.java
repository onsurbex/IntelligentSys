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
    
    public Node(int[][] state, carPosition[] car, int n){
        this.state = state;
        this.cars = car;
        this.n = n;
        this.previous = null;
    }
    
    public Node(int[][] state, carPosition[] car, int n, Node previous){
        this.state = state;
        this.cars = car;
        this.n = n;
        this.previous = previous;
    }
    
    public void addSuccessors(){
        int [][] newState = this.state;
        carPosition[] car = this.cars;
        for(int i = 0; i < n; i++){
            if(car[i].y < (n - 1) && state[car[i].y + 1][car[i].x] != -1){
                // UP
                car = this.cars;
                newState = this.state;
                newState[car[i].y][car[i].x] = 0;
                car[i].y += 1;
                newState[car[i].y + 1][car[i].x] = i + 1;
                this.successors.add(new Node(newState,car,this.n,this));
            }
            if(car[i].y > 0 && state[car[i].y - 1][car[i].x] != -1) {
                // DOWN
                car = this.cars;
                newState = this.state;
                newState[car[i].y][car[i].x] = 0;
                car[i].y -= 1;
                newState[car[i].y - 1][car[i].x] = i + 1;
                this.successors.add(new Node(newState,car,this.n,this));
            }
            if(car[i].x < (n - 1) && state[car[i].y][car[i].x + 1] != -1){
                // RIGHT
                
                newState = this.state;
                newState[car[i].y][car[i].x] = 0;
                car[i].x += 1;
                newState[car[i].y][car[i].x + 1] = i + 1;
                this.successors.add(new Node(newState,car,this.n,this));
            } 
            if(car[i].x > 0 && state[car[i].y][car[i].x - 1] != -1){
                //LEFT
                
                newState = this.state;
                newState[car[i].y][car[i].x] = 0;
                car[i].x -= 1;
                newState[car[i].y][car[i].x - 1] = i + 1;
                this.successors.add(new Node(newState,car,this.n,this));
            } 
        }
    }
}
