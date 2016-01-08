//Trevor Smith, Daniel Vasile
//17-12-2015
//The class which simulates a logical XOR chip
package virtualbreadboard;

import java.awt.Graphics;
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trsmi8659
 */
public class NOTChip extends Chip{
    
    boolean input[] = new boolean[6];
    boolean output[] = new boolean[6];
    
    public NOTChip(Point power, Point ground) {
        super(power, ground);
        for(int i = 0; i < 6; i++){
            input[i] = false;
            output[i] = false;
        }
    }
    
    public boolean outputState(boolean input){
        return input != true;
    }

    public boolean[] getInput() {
        return input;
    }

    public boolean[] getOutput() {
        return output;
    }
    
    public void setInput(boolean[] input) {
        this.input = input;
    }

    public void setOutput(boolean[] output) {
        this.output = output;
    }
    /**
     * used to draw the Logical Chip each time the frame refreshes 
     * @param g The graphics component g
     */
    public void paintComponents(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }
    /**
     * used to draw the Logical Chip each time the frame refreshes 
     * @param g The graphics component g
     */
    public void paint(Graphics g) {
        g.fillOval(0, 0, 15, 15);
    }
    
    
}