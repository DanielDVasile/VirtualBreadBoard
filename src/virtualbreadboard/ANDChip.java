//Trevor Smith, Zachary VanderBurgt, Daniel Vasile
//17-12-2015
package virtualbreadboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author trsmi8659
 */
public class ANDChip extends Chip {

    boolean input[][] = new boolean[4][2];
    boolean output[] = new boolean[4];
    boolean isPowered;
    boolean isGrounded;
    Image img;
    boolean pbb;

    public ANDChip(int x, int y) {
        super(x, y);
        try {
            InputStream is = ANDChip.class.getResourceAsStream("Images/and_1.png");
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println(e);
        }
        setSize(new Dimension(img.getWidth(null) -20, img.getHeight(null)));
        for (int i = 0; i < 4; i++) {
            output[i] = false;
            for (int k = 0; k < 2; k++) {
                input[i][k] = false;
            }
        }
        isPowered = false;
        isGrounded = false;
    }

    public ANDChip(boolean p) {
        super();
        setSize(186, 75);
        pbb = p;
    }

    public boolean outputState(boolean input1, boolean input2) {
        return input1 == input2 == true;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) (g);
        if (pbb == false) {
            g2d.drawImage(img, -10, 0, null);
        } else {
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.white);
            g2d.drawRect(0, 0, 24 * 7 + 3, 73);
            g2d.drawString("AND", 150 / 2, 76 / 2);
        }
    }
    /**
     * returns the x position of the logical chip
     * @return the x position of the logical chip
     */
    @Override
    public int getPoisiton() {
        return (int)powerPin.getX();
    }

    /**
     * resets the chip to a state where all input, outputs, ground, and power
     * are false
     */
    public void resetState() {
        for (int i = 0; i < 4; i++) {
            output[i] = false;
            for (int k = 0; k < 2; k++) {
                input[i][k] = false;
            }
        }
        isPowered = false;
        isGrounded = false;
    }

}
