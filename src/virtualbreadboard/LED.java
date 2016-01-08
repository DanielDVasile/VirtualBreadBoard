//Daniel Vasile
//16-12-2015
//The class for the LEDs in the program.
package virtualbreadboard;

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
    Color colorOn;
    Color colorOff;
    Image img;

    /**
     * primary constructor
     */
    public LED(int color) {
        powerPin = new Point();
        groundPin = new Point();
        isPowered = false;
        colorOff = Color.DARK_GRAY;
        setColor(color);
        setSize(getDim());
    }

    /**
     * method used to set the LED's color
     *
     * @param c the color of the LED as an int (1 = green, 2 = red, rest = blue)
     */
    private void setColor(int x) {
        if (x == 0){
            colorOn = Color.WHITE;
            img = new ImageIcon("src//Images//Led White.jpg").getImage();
        } else if (x == 1) {
            colorOn = Color.GREEN;
            img = new ImageIcon("src//Images//Led Green.jpg").getImage();
        } else if (x == 2) {
            colorOn = Color.RED;
            img = new ImageIcon("src//Images//Led Red.jpg").getImage();
        } else {
            colorOn = Color.BLUE;
            img = new ImageIcon("src//Images//Led Blue.jpg").getImage();
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
        g2d.drawImage(img, 0, 0, null);
    }


    private Dimension getDim() {
        return new Dimension(img.getWidth(null), img.getHeight(null));
    }
    
    
}
