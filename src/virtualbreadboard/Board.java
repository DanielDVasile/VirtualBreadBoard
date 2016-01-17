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
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author zackv
 */
public class Board extends JComponent{
    Image img;
    public Board(boolean pbb) {
        if (pbb == false) {
            img = new ImageIcon("src//Images//board.jpg").getImage();
        } else {
            img = new ImageIcon("src//Images//board-PCB.jpg").getImage();
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
     * @param g The graphics component g
     */
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)(g);
        g2d.drawImage(img, 1, 1, null);
    }
}
