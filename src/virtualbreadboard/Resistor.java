//Daniel Vasile
//12-01-2015
//The resistor class which draws a resistor on the board and is used to logicial processes for simulating the breadboard
package virtualbreadboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author Daniel
 */
public class Resistor extends JComponent{
    
    Point powerPin;
    Point groundPin;
    int x;
    int y;
    boolean pbb = false;
    /**
     * primary constructor
     * @param startx the x co-ordinate for the first point
     * @param starty the y co-ordinate for the first point
     * @param endx the x co-ordinate for the second point
     * @param endy the y co-ordinate for the second point
     */
    public Resistor(int startx, int starty, int endx, int endy){
        powerPin = new Point (startx, starty);
        groundPin = new Point (endx, endy);
        setSize(1000,1000);
    }
    /**
     * if the resistor is made on a pbb board call method
     */
    public void setPbb(){
        pbb = true;
    }
    /**
     * returns the location of the power pin
     * @return the location of the power pin
     */
    public Point getPowerPin() {
        return powerPin;
    }
    /**
     * returns the location of the ground pin
     * @return the location of the ground pin
     */
    public Point getGroundPin() {
        return groundPin;
    }
    
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        paint(g);
    }
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if (pbb == false) {
            //draws a light gray line between the two points of the resistor
            g2.setStroke(new BasicStroke(6));
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine((int)powerPin.getX(), (int)powerPin.getY(), ((int)groundPin.getX()), ((int)groundPin.getY()));
        } else {
            //draws a light gray line between the two points of the resistor
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.LIGHT_GRAY);
            int[] xPoints = {((int)powerPin.getX()),((int)groundPin.getX()),((int)groundPin.getX()),((int)powerPin.getX())};
            int[] yPoints = {((int)powerPin.getY()),((int)groundPin.getY()),((int)groundPin.getY())+10,((int)powerPin.getY())+10};
            g2.drawPolygon(xPoints, yPoints, 4);
            
        }
        
    }
    
}
