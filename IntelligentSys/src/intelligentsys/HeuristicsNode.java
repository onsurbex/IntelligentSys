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
public class HeuristicsNode extends Node{
    
    int f;
    int g;
    int h;
    
    public HeuristicsNode(int[][] state, carPosition[] car, int n) {
        super(state, car, n);
    }
    
    public HeuristicsNode(int[][] state, carPosition[] car, int n, Node previous) {
        super(state, car, n, previous);
    }
    
    
}
