/*
 * Trevor Smith
 * 16-12-2015
 * Class for a wire object
 */
package virtualbreadboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import javax.swing.JComponent;

/**
 *
 * @author trsmi8659
 */
public class Wire extends JComponent{
    
    Point powerPin;
    Point groundPin;
    int x;
    int y;
    
    public Wire(int startx, int starty, int endx, int endy){
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
        g2.setColor(Color.red);
        g2.drawLine((int)powerPin.getX(), (int)powerPin.getY(), ((int)groundPin.getX()), ((int)groundPin.getY()));
    }
}