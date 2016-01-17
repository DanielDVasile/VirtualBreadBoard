//Daniel Vasile, Trevor Smith
//04-01-2016
//The JFrame class which displays the SkematicsMenu, and allows the user to interact with it
package virtualbreadboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class SchematicsMenu extends JFrame {
    
    VirtualBreadBoard main;
    JPanel area;
    JButton back;
    JButton save;
    LED test;
    Point location;
    int height;
    int width;
    Screenshot screenshot;
    
    
    public SchematicsMenu(VirtualBreadBoard main) {
        this.main = main;
        //sets JFrame's size, background color, and exit operation.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setBackground(Color.DARK_GRAY);
        //instanitates all nesscary components
        area = new JPanel();
        test = new LED(0, 0, 0);
        back = new JButton("Back");
        save = new JButton("Save");
        //sets JPanel layout
        area.setLayout(null);
        //sets the buttons locations
        back.setBounds(450, 500, 120, 40);
        save.setBounds(450, 450, 120, 40);        
        test.setSize(300,300); 
        test.setLocation(50,50);
        //adds MouseListeners to buttons
        back.addMouseListener(backL);
        save.addMouseListener(saveL);
        //adds components to the JPanel
        area.add(back);
        area.add(save);
        area.add(test);
        //adds JPanel area to this class
        add(area);
    }    
    
    /**
     * MouseListener used to check if the user clicked on the run button.
     */
    MouseListener backL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(back.contains(e.getPoint())) {
                main.switchFrame(VirtualBreadBoard.MAIN_MENU);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    };
    
    MouseListener saveL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(save.contains(e.getPoint())) {
                try {
                    captureScreen();
                } catch (Exception ex) {
                    System.out.println("ERROR: " + ex.toString());
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    };
    
    public void getScreenshot(Screenshot temp){
        screenshot = temp;
    }
    
    public void captureScreen() throws Exception {
    location = screenshot.getLocation();
    Dimension screensize = new Dimension(screenshot.getWidth(), screenshot.getHeight());
    Rectangle screenRectangle = new Rectangle(location, screensize);
    Robot robot = new Robot();
    BufferedImage image = robot.createScreenCapture(screenRectangle);
    ImageIO.write(image, "png", new File(JOptionPane.showInputDialog("Please name the screenshot")));
    }
    
}
