//Daniel Vasile, Zachary VanderBurgt
//16-12-2015
//The class for the LEDs in the program.
package virtualbreadboard;

import java.awt.BasicStroke;
import java.awt.Point;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class LED extends JComponent  {

    Point powerPin;
    Point groundPin;
    boolean isPowered;
    Image img;
    int color;
    boolean pbb = false;
    /**
     * primary constructor
     */
    public LED(int c, int x, int y) {
        powerPin = new Point(x,y);
        groundPin = new Point(x + 24,y);
        isPowered = false;
        color = c;
        setColor(color);
        setSize(getDim());
    }
    public LED(){
        pbb= true;
        setSize(24,24);
    }

    /**
     * method used to set the LED's color
     *
     * @param c the color of the LED as an int (1 = green, 2 = red, rest = blue)
     */
    private void setColor(int x) {
        if (isPowered == false){
            img = new ImageIcon("src//Images//Led White.png").getImage();
        } else {
            if (x == 1) {
            img = new ImageIcon("src//Images//Led Green.png").getImage();
            } else if (x == 2) {
                img = new ImageIcon("src//Images//Led Red.png").getImage();
            } else {
                img = new ImageIcon("src//Images//Led Blue.png").getImage();
            }
        }
    }
    /**
     * used to draw the LED each time the frame refreshes 
     * @param g The graphics component g
     */
    public void paintComponents(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }
    /**
     * used to draw the LED each time the frame refreshes 
     * @param g The graphics component g
     */
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)(g);
        if (pbb == false) {
            g2d.drawImage(img, 0, 0, null);
        } else {
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.white);
            g2d.drawOval(0, 0, 24, 24);
        }        
    }


    private Dimension getDim() {
        return new Dimension(img.getWidth(null), img.getHeight(null));
    }
    
    public void setPower(boolean bool){
        isPowered = bool;
        setColor(color);
    }
    
    public Point getPosition() {
        return powerPin;
    }
    
}
