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
            try {
                InputStream is = LED.class.getResourceAsStream("Images//Led White.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            if (x == 1) {
            try {
                InputStream is = LED.class.getResourceAsStream("Images//Led Green.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
            } else if (x == 2) {
                try {
                InputStream is = LED.class.getResourceAsStream("Images//Led Red.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
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
