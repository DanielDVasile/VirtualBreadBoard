/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualbreadboard;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author zackv
 */
public class Board extends JComponent {

    Image img;
    /**
     * primary constructor
     * @param pbb boolean if the board should be a ppb board or a regular board
     */
    public Board(boolean pbb) {
        if (pbb == false) {
            //loads regular breadboard image
            try {
                InputStream is = Board.class.getResourceAsStream("Images/board.jpg");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            //loads pcb breadboard image
            try {
                InputStream is = Board.class.getResourceAsStream("Images//board-PCB.jpg");
               img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        //sets size of JComponent
        setSize(getDim());
    }
    /**
     * returns the dimension of the image
     * @return the dimension of the image
     */
    public Dimension getDim() {
        return new Dimension(img.getWidth(null), img.getHeight(null));
    }

    public void paintComponents(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }

    /**
     * used to draw the LED each time the frame refreshes
     *
     * @param g The graphics component g
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) (g);
        g2d.drawImage(img, 1, 1, null);
    }
}
