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
    
    public Chip(){
        powerPin = new Point();
        groundPin = new Point();
    }
    
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }
    /**
     * used to draw the LED each time the frame refreshes 
     * @param g The graphics component g
     */
    
}