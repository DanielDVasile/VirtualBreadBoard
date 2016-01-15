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
        groundPin = new Point();
    }
    
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }
    
    abstract public Point getPoisiton();
    
}