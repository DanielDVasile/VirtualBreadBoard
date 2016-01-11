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
public class NOTChip extends Chip{
    
    boolean input[] = new boolean[6];
    boolean output[] = new boolean[6];
    final Image img = new ImageIcon("src//Images//Not.jpg").getImage();
    
    public NOTChip(Point power, Point ground) {
        super();
        setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
//        for(int i = 0; i < 6; i++){
//            input[i] = false;
//            output[i] = false;
//        }
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
    
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)(g);
        g2d.drawImage(img, 0, 0, null);
    }
    
}