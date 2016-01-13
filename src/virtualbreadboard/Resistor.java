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
    
    public Resistor(int startx, int starty, int endx, int endy){
        powerPin = new Point (startx, starty);
        groundPin = new Point (endx, endy);
        setSize(1000,1000);
    }

    public Point getPowerPin() {
        return powerPin;
    }

    public Point getGroundPin() {
        return groundPin;
    }
    
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        paint(g);
    }
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(6));
        g2.setColor(Color.magenta);
        g2.drawLine((int)powerPin.getX(), (int)powerPin.getY(), ((int)groundPin.getX()), ((int)groundPin.getY()));
    }
    
}
