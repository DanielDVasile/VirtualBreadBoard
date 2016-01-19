//Trevor Smith, Zachary VanderBurgt
//17-12-2015

package virtualbreadboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
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
    boolean isPowered;
    boolean isGrounded;
    Image img;
    boolean pbb;
    
    
    public NOTChip(int x, int y) {
        super(x,y);
        try {
                InputStream is = NOTChip.class.getResourceAsStream("Images/Not.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
        }
        setSize(new Dimension(img.getWidth(null) - 20, img.getHeight(null)));
        for(int i = 0; i < 6; i++){
            input[i] = false;
            output[i] = false;
        }
        isPowered = false;
        isGrounded = false;
    }
    public NOTChip(boolean p){
        super();
        setSize(186,75);
        pbb = p;
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
        if (pbb == false) {
            g2d.drawImage(img, -10, 0, null);
        } else {
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.white);
            g2d.drawRect(0, 0, 24*7+3, 73);
            g2d.drawString("NOT", 150/2, 76/2);
        }
    }
    /**
     * returns the x position of the logical chip
     * @return the x position of the logical chip
     */
    @Override
    public int getPoisiton() {
        return (int)powerPin.getX();
    }
    /**
     * resets the chip to a state where all input, outputs, ground, and power
     * are false
     */
    public void resetState() {
        for (int i = 0; i < 6; i++) {
            input[i] = false;
            output[i] = false;
        }
        isPowered = false;
        isGrounded = false;
    }
    
}