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

    public Board(boolean pbb) {

        if (pbb == false) {
            try {
                InputStream is = Board.class.getResourceAsStream("Images/board.jpg");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            try {
                InputStream is = Board.class.getResourceAsStream("Images//board-PCB.jpg");
               img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        setSize(getDim());
    }

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
