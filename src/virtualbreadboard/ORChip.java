//Trevor Smith, Zachary VanderBurgt
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
public class ORChip extends Chip{
    
    boolean input[][] = new boolean[4][2];
    boolean output[] = new boolean[4];
    Image img;
    boolean pbb;
    
    public ORChip(int x, int y) {
        super(x,y);
        try {
                InputStream is = ORChip.class.getResourceAsStream("Images//Or.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
        }
        setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
        for(int i = 0; i < 4; i++){
            output[i] = false;
            for(int k = 0; k < 2; k++){
                input[i][k] = false;
            }
        }
    }
    public ORChip(boolean p){
        super();
        setSize(186,75);
        pbb = p;
    }
    
    public boolean outputState(boolean input1, boolean input2){
        return input1==input2 != false;
    }

    public boolean[][] getInput() {
        return input;
    }

    public boolean[] getOutput() {
        return output;
    }
    
    public void setInput(boolean[][] input) {
        this.input = input;
    }

    public void setOutput(boolean[] output) {
        this.output = output;
    }
    
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)(g);
        if (pbb == false) {
            g2d.drawImage(img, 0, 0, null);
        } else {
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.white);
            g2d.drawRect(0, 0, 24*7+3, 73);
            g2d.drawString("OR", 150/2, 76/2);
        }
    }

    @Override
    public Point getPoisiton() {
        return powerPin;
    }
    
    
}