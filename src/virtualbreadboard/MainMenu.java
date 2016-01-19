//Daniel Vasile
//17-12-2015
//The JFrame class which will display the main menu and hold all of it's components
package virtualbreadboard;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class MainMenu extends JFrame{
    
    JButton exit;
    JPanel exitArea;
    JButton breadBoardMenu;
    JButton credits;
    JButton schematicsMenu;
    VirtualBreadBoard main;
    
    /**
     * primary constructor
     */
    public MainMenu(VirtualBreadBoard main) {
        this.main = main;
        //sets JFrame's size, background color, and exit operation.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setBackground(Color.DARK_GRAY);
        //instanitates all nesscary components
        exit = new JButton("Exit");
        credits = new JButton("Credits");
        breadBoardMenu = new JButton("BreadBoard Menu");
        schematicsMenu = new JButton("Schematics Menu");
        exitArea = new JPanel();
        //sets the JPanel's layout and adds all components to it
        exitArea.setLayout(new GridLayout(0, 2));
        exitArea.add(breadBoardMenu);
        exitArea.add(schematicsMenu);
        exitArea.add(credits);
        exitArea.add(exit);
        //adds the Mouselisteners to the buttons
        exit.addMouseListener(exitL);
        breadBoardMenu.addMouseListener(breadBoardL);
        credits.addMouseListener(creditsL);
        schematicsMenu.addMouseListener(schematicsL);
        //adds JPanel to the JFrame
        add(exitArea);
    }
    /**
     * MouseListener which is used to detect if the exit button has been pressed
     */
    public MouseListener exitL = new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
            Point x = e.getPoint();
            if(exit.contains(x)) {
                System.exit(0);
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
    /**
     * MouseListener used to detect if the BreadBoardMenu button has been pressed
     */
    public MouseListener breadBoardL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(breadBoardMenu.contains(e.getPoint())) {
                main.switchFrame(main.BREAD_BOARD_MENU);
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
    /**
     * MouseListener used to detect if the credits button has been pressed
     */
    public MouseListener creditsL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(schematicsMenu.contains(e.getPoint())) {
                main.switchFrame(main.CREDIT_MENU);
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
    /**
     * MouseListener used to detect if the SchematicsMenu button has been pressed
     */
    public MouseListener schematicsL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(credits.contains(e.getPoint())) {
                main.switchFrame(main.SCHEMATICS_MENU);
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
}