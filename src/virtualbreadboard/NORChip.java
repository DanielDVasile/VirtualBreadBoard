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
public class NORChip extends Chip{
    
    boolean input[][] = new boolean[4][2];
    boolean output[] = new boolean[4];
    boolean isPowered;
    boolean isGrounded;
    Image img;

    boolean pbb;
    
    public NORChip(int x, int y) {
        super(x,y);
        try {
                InputStream is = NORChip.class.getResourceAsStream("Images/Nor.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
        }
        setSize(new Dimension(img.getWidth(null) - 20, img.getHeight(null)));
        for(int i = 0; i < 4; i++){
            output[i] = false;
            for(int k = 0; k < 2; k++){
                input[i][k] = false;
            }
        }
        isPowered = false;
        isGrounded = false;
    }
    public NORChip(boolean p){
        super();
        setSize(186,75);
        pbb=p;
    }
    
    public boolean outputState(boolean input1, boolean input2){
        return input1==input2==false;
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
            g2d.drawImage(img, -10, 0, null);
        } else {
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.white);
            g2d.drawRect(0, 0, 24*7+3, 73);
            g2d.drawString("NOR", 150/2, 76/2);
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

    @Override
    public void setPowered(boolean state) {
        isPowered = state;
    }

    @Override
    public void setGrounded(boolean state) {
        isGrounded = state;
    }

    @Override
    public void setInputPinState(boolean state, int x, int y) {
        input[x][y] = state;
    }

    @Override
    public boolean getOutput(int pinNum) {
        if(input[pinNum][0] == false && input[pinNum][1] == false) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean isPowered() {
        return isPowered;
    }

    @Override
    public boolean isGrounded() {
        return isGrounded;
    }
    
    
    
}