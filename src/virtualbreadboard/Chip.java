/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package virtualbreadboard;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;


/**
 *
 * @author trsmi8659
 */
public abstract class Chip extends JComponent {
    
    Point powerPin;
    Point groundPin;
    
    public Chip(int x, int y){
        powerPin = new Point(x,y);
        groundPin = new Point(x + (6 * 24), y + 20);
    }
    public Chip(){
        
    }
    
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }
    /**
     * returns the x position of the top left pin of the chip
     * @return the x position of the top left pin of the chip
     */
    abstract public int getPoisiton();
    /**
     * sets the chip to powered
     * @param state the state of the power pin (on or off)
     */
    abstract public void setPowered(boolean state);
    /**
     * sets the chip to grounded
     * @param state state of the ground pin (grounded or not)
     */
    abstract public void setGrounded(boolean state);
    /**
     * sets the state of one of the input chips
     * @param state the state of the input chip, on or off
     * @param x the position in the input chip array of the pin which is being powered
     * @param y the position in the input chip array of the pin which is being powered
     */
    abstract public void setInputPinState(boolean state, int x, int y);
    /**
     * returns the output of the logical chip
     * @param pinNum the output pin's number
     * @return the output of the logical chip's pin
     */
    abstract public boolean getOutput(int pinNum);
    /**
     * returns whether the chip is powered or not
     * @return whether the chip is powered or not
     */
    abstract public boolean isPowered();
    /**
     * returns whether the chip is grounded or not
     * @return whether the chip is grounded or not
     */
    abstract public boolean isGrounded();
}