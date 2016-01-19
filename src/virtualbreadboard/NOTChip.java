//Trevor Smith, Zachary VanderBurgt
//17-12-2015

package virtualbreadboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

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
    
    /**
     * primary constructor
     * @param x the x position of the top left pin
     * @param y the y position of the top left pin
     */
    public NOTChip(int x, int y) {
        super(x,y);
        //loads the chips image
        try {
                InputStream is = NOTChip.class.getResourceAsStream("Images/Not.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
        }
        //sets the JComponent's size
        setSize(new Dimension(img.getWidth(null) - 20, img.getHeight(null)));
        //sets variables to their basic state
        for(int i = 0; i < 6; i++){
            input[i] = false;
            output[i] = false;
        }
        isPowered = false;
        isGrounded = false;
    }
    /**
     * secondary constructor, used in the Schematics menu
     * @param p whether or not the chip is in the schematics menu
     */
    public NOTChip(boolean p){
        super();
        setSize(186,75);
        pbb = p;
    }
    
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)(g);
        if (pbb == false) {
            //draws the chips image
            g2d.drawImage(img, -10, 0, null);
        } else {
            //draws a white square with the chip's name in the middle
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

    @Override
    public void setPowered(boolean state) {
        isPowered = state;
    }

    @Override
    public void setGrounded(boolean state) {
        isGrounded = state;
    }

    @Override
    public void setInputPinState(boolean state, int x, int y) {
    }
    /**
     * sets the state of one of the input pins on the NOT Chip
     * @param state the state of the pin
     * @param x the location of the pin in the array
     */
    public void setNOTInputPinState(boolean state, int x) {
        input[x] = state;
    }

    @Override
    public boolean getOutput(int pinNum) {
        return !input[pinNum];
    }
    
    @Override
    public boolean isPowered() {
        return isPowered;
    }

    @Override
    public boolean isGrounded() {
        return isGrounded;
    }
    
}