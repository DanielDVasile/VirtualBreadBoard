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
import javax.swing.JComponent;

/**
 *
 * @author trsmi8659
 */
public class Wire extends JComponent {

    Point powerPin;
    Point groundPin;
    int x;
    boolean state;
    boolean groundWire;
    boolean powerWire;
    int y;

    /**
     * primary constructor
     *
     * @param startx the x position for the first point
     * @param starty the y position for the first point
     * @param endx the x position for the second point
     * @param endy the y position for the second point
     */
    public Wire(int startx, int starty, int endx, int endy) {
        if (starty == 26 || starty == 458 || endy == 26 || endy == 458) {//if the wire is connected to the ground rows
            //sets the wire to be a grounding wire
            groundWire = true;
            if(starty == 26 || starty == 458) {//if the first point is conected to the ground
                powerPin = new Point(endx, endy);
                groundPin = new Point(startx, starty);//the ground co-ordinates are set to the groundPin
            } else {
                powerPin = new Point(startx, starty);
                groundPin = new Point(endx, endy);//the ground co-ordinates are set to the groundPin
            }
        } else if(starty == 26 + 24 || starty == 458 + 24 || endy == 26 + 24 || endy == 458 + 24) {//if the wire is connected to the power rows
            //sets the wire to be a power wire
            powerWire = true;
            if(starty == 26 + 24 || starty == 458 + 24) {//if the first point is conected to the power
                powerPin = new Point(startx, starty);//the power co-ordinates are set to the powerPin
                groundPin = new Point(endx, endy);
            } else {
                groundPin = new Point(startx, starty);
                powerPin = new Point(endx, endy);//the power co-ordinates are set to the powerPin
            }
        } else {//otherwise it is a connecting wire
            groundWire = false;
            powerWire = false;
            powerPin = new Point(startx, starty);
            groundPin = new Point(endx, endy);
            //the wire is not powered
            state = false;
        }
        setSize(1000, 1000);
    }
    /**
     * returns whether or not the wire is connected to the power row
     * @return whether or not the wire is connected to the power row
     */
    public boolean isPowerWire() {
        return powerWire;
    }

    /**
     * returns whether or not this wire is connected to the ground.
     *
     * @return whether or not this wire is connected to the ground.
     */
    public boolean isGroundWire() {
        return groundWire;
    }

    /**
     * resets the state of the wire
     */
    public void resetState() {
        state = false;
    }

    /**
     * sets the state of the wire
     *
     * @param state the new state
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * returns the state of the wire
     *
     * @return the state of the wire
     */
    public boolean isPowered() {
        return state;
    }

    /**
     * returns the location of the power pin
     *
     * @return the location of the power pin
     */
    public Point getPowerPin() {
        return powerPin;
    }

    /**
     * returns the position of the ground pin
     *
     * @return the position of the ground pin
     */
    public Point getGroundPin() {
        return groundPin;
    }

    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        paint(g);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g2.setColor(Color.white);
        g2.drawLine((int) powerPin.getX(), (int) powerPin.getY(), ((int) groundPin.getX()), ((int) groundPin.getY()));
    }
}
