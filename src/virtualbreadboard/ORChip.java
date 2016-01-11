//Trevor Smith
//17-12-2015

package virtualbreadboard;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trsmi8659
 */
public class ORChip extends Chip{
    
    boolean input[][] = new boolean[4][2];
    boolean output[] = new boolean[4];
    final Image img = new ImageIcon("src//Images//Or.jpg").getImage();
    
    public ORChip() {
        super();
        setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
//        for(int i = 0; i < 4; i++){
//            output[i] = false;
//            for(int k = 0; k < 4; k++){
//                input[i][k] = false;
//            }
//        }
    }
    
    public boolean outputState(boolean input1, boolean input2){
        return input1==input2 != false;
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
    
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)(g);
        g2d.drawImage(img, 0, 0, null);
    }
    
    
}