//Trevor Smith, Daniel Vasile
//05-01-2016
//The abstract class which is the parent class for all the logcial chip class in the program

package virtualbreadboard;

import java.awt.Point;
import javax.swing.JComponent;

public abstract class Chip extends JComponent{
    Point powerPin;
    Point groundPin;
    boolean isPowered = false;
    boolean isGrounded = false;

    public Chip(Point power, Point ground){
        powerPin = power;
        groundPin = ground;
    }
    
    public Point getPowerPin() {
        return powerPin;
    }

    public Point getGroundPin() {
        return groundPin;
    }
    
    public void setPower(boolean power){
        isPowered = power;
    }
    
    public void setGround(boolean ground){
        isGrounded = ground;
    }
}