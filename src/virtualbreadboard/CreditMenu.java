//Daniel Vasile
//04-01-2016
//A JFrame which displays the credits of who worked on this project
package virtualbreadboard;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class CreditMenu extends JFrame {

    VirtualBreadBoard main;
    JPanel area;
    JButton back;
    JLabel daniel;
    JLabel trevor;
    JLabel zack;
    JLabel intro;
    /**
     * primary constructor
     * @param main main class to switch between frames
     */
    public CreditMenu(VirtualBreadBoard main) {
        this.main = main;
        //sets JFrame's size, background color, and exit operation.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setBackground(Color.DARK_GRAY);
        //instanitates all nesscary components
        area = new JPanel();
        back = new JButton("Back");
        intro = new JLabel("");
        daniel = new JLabel("");
        trevor = new JLabel("");
        zack = new JLabel("");
        //set the JLabels' texts, sizes, and locations
        intro.setFont(null);
        intro.setText("<html>This program was created by Daniel, Zack, <br> and Trevor, in their grade 12 year, 2015/2016<br>Special thanks goes to:</html>");
        intro.setSize(300, 300);
        intro.setLocation(140, -100);

        daniel.setText("<html>Daniel  <br>-Lead Programmer</html>");
        daniel.setSize(300, 40);
        daniel.setLocation(200, 150);

        trevor.setText("<html>Trevor  <br>-Executive Project Manager</html>");
        trevor.setSize(300, 40);
        trevor.setLocation(200, 250);

        zack.setText("<html>Zack  <br>-Graphics Lead</html>");
        zack.setSize(300, 40);
        zack.setLocation(200, 350);
        //sets JPanel layout
        area.setLayout(null);
        //sets the buttons locations
        back.setBounds(450, 500, 120, 40);
        //adds MouseListeners to buttons
        back.addMouseListener(backL);
        //adds components to the JPanel
        area.add(back);
        area.add(intro);
        area.add(daniel);
        area.add(trevor);
        area.add(zack);
        //adds JPanel area to this class
        add(area);
    }

    /**
     * MouseListener used to check if the user clicked on the back button.
     */
    MouseListener backL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (back.contains(e.getPoint())) {
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
