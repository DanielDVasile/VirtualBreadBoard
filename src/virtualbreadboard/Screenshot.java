/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualbreadboard;

import java.awt.Point;
import javax.swing.JFrame;

/**
 *
 * @author Maria
 */
public class Screenshot {
    /*Point location;
    int height;
    int width;*/
    JFrame frame;
    
    public Screenshot (JFrame Menu){
        frame = Menu;
        /*location = new Point();
        height = 0;
        width = 0;*/
    }
    
    public Point getLocation(){
        Point location = frame.getLocation();
        return location;
    }
    
    public int getHeight(){
        int height = frame.getHeight();
        return height;
    }
    
    public int getWidth(){
        int width = frame.getWidth();
        return width;
    }
    
    /*@Override
    public String toString(){
        String output = "Height: " + height + "Width: " + width + "Location: " + location;
        return output;
    }*/
}
