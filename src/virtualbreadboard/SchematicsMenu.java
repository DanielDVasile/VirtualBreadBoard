//Daniel Vasile
//04-01-2016
//The JFrame class which displays the SkematicsMenu, and allows the user to interact with it
package virtualbreadboard;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


public class SchematicsMenu extends JFrame{
    
    VirtualBreadBoard main;
    JPanel area;
    JButton back;
    LED test;
    
    public SchematicsMenu(VirtualBreadBoard main) {
        this.main = main;
        test = new LED(1);
        //sets JFrame's size, background color, and exit operation.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setBackground(Color.DARK_GRAY);
        //instanitates all nesscary components
        area = new JPanel();
        back = new JButton("Back");
        //sets JPanel layout
        area.setLayout(null);
        //sets the buttons locations
        back.setBounds(450, 500, 120, 40);
        test.setSize(300,300); 
        test.setLocation(50,50);
        //adds MouseListeners to buttons
        back.addMouseListener(backL);
        //adds components to the JPanel
        area.add(back);
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
                main.switchFrame(main.MAIN_MENU);
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
