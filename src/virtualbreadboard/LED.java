//Daniel Vasile, Zachary VanderBurgt
//16-12-2015
//The class for the LEDs in the program.
package virtualbreadboard;

import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
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
     * @param c the color ID for the led
     * @param x the x position of the power pin
     * @param y the y position of the power pin
     */
    public LED(int c, int x, int y) {
        powerPin = new Point(x,y);
        groundPin = new Point(x + 24,y);
        isPowered = false;
        color = c;
        setColor(color);
        setSize(getDim());
    }
    /**
     * the secondary constructor, used if LED is going to be placed on the Schematics Menu
     */
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
            //sets the led's image as the unpowered led
            try {
                InputStream is = LED.class.getResourceAsStream("Images/Led White.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
            //sets the led's image as the green led
        } else {
            if (x == 1) {
            try {
                InputStream is = LED.class.getResourceAsStream("Images//Led Green.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
            //sets the led's image as the red led
            } else if (x == 2) {
                try {
                InputStream is = LED.class.getResourceAsStream("Images//Led Red.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
            //sets the led's image as the blue led
            } else {
                try {
                InputStream is = LED.class.getResourceAsStream("Images//Led Blue.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
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
            //draws the led as the image
            g2d.drawImage(img, 0, 0, null);
        } else {
            //draws a white circle
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.white);
            g2d.drawOval(0, 0, 24, 24);
        }        
    }

    /**
     * returns the Dimension of the led's image
     * @return the Dimension of the led's image
     */
    private Dimension getDim() {
        return new Dimension(img.getWidth(null), img.getHeight(null));
    }
    /**
     * sets the state of the led
     * @param bool the state of the led
     */
    public void setState(boolean bool){
        isPowered = bool;
        setColor(color);
    }
    /**
     * returns the position of the led
     * @return the position of the led
     */
    public Point getPosition() {
        return powerPin;
    }
    /**
     * resets the state of the led
     */
    public void resetState() {
        isPowered = false;
        setColor(color);
    }
    
}
