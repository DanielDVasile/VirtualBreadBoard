//Trevor Smith, Daniel Vasile
//17-12-2015
//The class which simulates a logical XOR chip
package virtualbreadboard;

import java.awt.Graphics;
import java.awt.Point;


public class ANDChip extends Chip{
    
    boolean input[][] = new boolean[4][2];
    boolean output[] = new boolean[4];
    
    public ANDChip(Point power, Point ground) {
        super(power, ground);
        for(int i = 0; i < 4; i++){
            output[i] = false;
            for(int k = 0; k < 4; k++){
                input[i][k] = false;
            }
        }
    }
    
    public boolean outputState(boolean input1, boolean input2){
        return input1 == input2 == true;
    }

    public boolean[][] getInput() {
        return input;
    }

    public boolean[] getOutput() {
        return output;
    }

    public void setInput(boolean[][] input) {
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