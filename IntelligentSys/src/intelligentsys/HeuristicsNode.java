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
public class HeuristicsNode extends Node{
    
    int f; // g + h
    int g; // cost
    int h; // heuristics
    
    HeuristicsNode previous;
    ArrayList<HeuristicsNode> successors;
    
    public HeuristicsNode(int[][] state, carPosition[] car, int n) {
        super(state, car, n);
        this.h = 0;
        this.step = null;
    }
    
    public HeuristicsNode(int[][] state, carPosition[] car, int n, HeuristicsNode previous, String step) {
        super(state, car, n);
        this.h = 0;
        this.step = step;
    }
       
    public HeuristicsNode(int[][] state, carPosition[] car, int n, HeuristicsNode previous, String step, int g) {
        super(state, car, n, previous,step);
        this.g = g;
    }
    
    public void HeuristicsLevels(){ 
        for (carPosition car: this.cars) {
            this.h += this.n - car.y;
        }
        this.f = this.g + this.h;
    }
    
    @Override
    public void addSuccessors(){
        int [][] newState = this.state.clone();
        carPosition[] car = new carPosition[this.cars.length];
        for (int i = 0; i < this.cars.length; i++) {
            car[i] =  new carPosition(this.cars[i].x,this.cars[i].y);
        }
        HeuristicsNode node = null;
        int cost = this.g + 1;
        for(int i = 0; i < n; i++){
            if(car[i].y < 0 && state[car[i].y - 1][car[i].x] != -1){
                // UP
                car[i] = new carPosition(this.cars[i].x,this.cars[i].y);
                newState = this.copyMatrix(this.state);
                newState[car[i].y][car[i].x] = 0;
                car[i].y -= 1;
                newState[car[i].y - 1][car[i].x] = i + 1;
                node = new HeuristicsNode(newState,car,this.n,this, "UP", cost);
                node.HeuristicsLevels();
                this.successors.add(node);
            }
            if(car[i].y > n - 1 && state[car[i].y + 1][car[i].x] != -1) {
                // DOWN
                car[i] = new carPosition(this.cars[i].x,this.cars[i].y);
                newState = this.copyMatrix(this.state);
                newState[car[i].y][car[i].x] = 0;
                car[i].y += 1;
                newState[car[i].y + 1][car[i].x] = i + 1;
                node = new HeuristicsNode(newState,car,this.n,this, "DOWN", cost);
                node.HeuristicsLevels();
                this.successors.add(node);
            }
            if(car[i].x < (n - 1) && state[car[i].y][car[i].x + 1] != -1){
                // RIGHT
                car[i] = new carPosition(this.cars[i].x,this.cars[i].y);
                newState = this.copyMatrix(this.state);
                newState[car[i].y][car[i].x] = 0;
                car[i].x += 1;
                newState[car[i].y][car[i].x + 1] = i + 1;
                node = new HeuristicsNode(newState,car,this.n,this, "RIGHT", cost);
                node.HeuristicsLevels();
                this.successors.add(node);
            } 
            if(car[i].x > 0 && state[car[i].y][car[i].x - 1] != -1){
                //LEFT
                car[i] = new carPosition(this.cars[i].x,this.cars[i].y);
                newState = this.copyMatrix(this.state);
                newState[car[i].y][car[i].x] = 0;
                car[i].x -= 1;
                newState[car[i].y][car[i].x - 1] = i + 1;
                node = new HeuristicsNode(newState,car,this.n,this, "LEFT", cost);
                node.HeuristicsLevels();
                this.successors.add(node);
            } 
        }
    }
}
