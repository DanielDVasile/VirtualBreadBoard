//Daniel Vasile
//04-1-2016
//The class which will display all the Graphics needed for the user to drag and drop items onto a breadboard
package virtualbreadboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BreadBoardMenu extends JFrame{
    
    JPanel area;
    Graphics2D drawer;
    VirtualBreadBoard main;
    JButton run;
    JButton back;
    JButton wireTest;
    LED ledGreen;
    LED ledBlue;
    LED ledRed;
    Board board;
    Snapper snapper;
    boolean ledP = false;
    boolean wireP = false;
    boolean wireStep2 = false;
    ArrayList<JComponent> componentList = new ArrayList();
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    /**
     * primary constructor
     */
    public BreadBoardMenu(VirtualBreadBoard main) {
        this.main = main;
        ledGreen = new LED(1);
        ledRed = new LED(2);
        ledBlue = new LED(3);
        //sets JFrame's size, background color, and exit operation.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new Board();
        setSize(1200, 562);
        setBackground(Color.DARK_GRAY);
        //instanitates all nesscary components
        area = new JPanel();
        run = new JButton("Run");
        back = new JButton("Back");
        wireTest = new JButton("Wire");
        snapper = new Snapper();
        //sets JPanel layout
        area.setLayout(null);
        //sets the buttons locations and size
        run.setBounds(1050, 400, 100, 30);
        back.setBounds(1050, 450, 100, 30);
        wireTest.setBounds(1050, 300, 100, 30);
        ledGreen.setBounds(850, 0, ledGreen.getWidth(), ledGreen.getHeight());
        ledRed.setBounds(875, 0, ledGreen.getWidth(), ledGreen.getHeight());
        ledBlue.setBounds(900, 0, ledGreen.getWidth(), ledGreen.getHeight());
        //adds MouseListeners to compnents
        run.addMouseListener(runL);
        back.addMouseListener(backL);
        wireTest.addMouseListener(wireL);
        ledGreen.addMouseListener(ledL);
        ledRed.addMouseListener(ledL);
        ledBlue.addMouseListener(ledL);
        board.addMouseListener(boardL);
        //adds components to the JPanel
        setup();
        //adds JPanel area to this class
        add(area);
    }

    /**
     * MouseListener used to check if the user clicked on the run button.
     */
    MouseListener runL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (run.contains(e.getPoint())) {
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
     * MouseListener used to check if the user clicked on the back button.
     */
    MouseListener backL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (run.contains(e.getPoint())) {
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
    /**
     * MouseListener used to check if the user clicked on the LED button.
     */
    MouseListener ledL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (ledGreen.contains(e.getPoint())) {
                if (ledP == false) {
                    ledP = true;
                } else {
                    ledP = false;
                }

            }
            if (ledRed.contains(e.getPoint())) {
                if (ledP == false) {
                    ledP = true;
                } else {
                    ledP = false;
                }
            }
            if (ledBlue.contains(e.getPoint())) {
                if (ledP == false) {
                    ledP = true;
                } else {
                    ledP = false;
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
    /**
     * MouseListener used to check if the user clicked on the Board button.
     */
    MouseListener boardL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (board.contains(e.getPoint())) {
                if (ledP == true) {
                    ledP = false;
                    componentList.add(0, new LED(0));
                    ((LED) componentList.get(0)).setBounds(snapper.snapToX(e.getX()) + 1, snapper.snapToY(e.getY()) - 11, 100, 100);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (wireP == true) {
                    if (!wireStep2) {
                        x1 = snapper.wSnapToX(e.getX(), e.getY());
                        System.out.println(snapper.wSnapToX(e.getX(), e.getY()));
                        y1 = snapper.wSnapToY(e.getY());
                        wireStep2 = true;
                    } else {
                        wireStep2 = false;
                        x2 = snapper.wSnapToX(e.getX(), e.getY());
                        y2 = snapper.wSnapToY(e.getY());
                        componentList.add(0, new Wire(x1, y1, x2, y2));
                        ((Wire) componentList.get(0)).setLocation(0, 0);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
                        wireP = false;
                    }
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

        private void redrawAll() {
            for (int i = 0; i < componentList.size(); i++) {
                area.add(componentList.get(i));
            }
            setup();
            System.out.println("hello");
        }

        
    };

    MouseListener wireL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (wireTest.contains(e.getPoint())) {
                ledP = false;
                wireP = true;
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

    private Dimension getDim() {
        if (board == null) {
            return new Dimension(100, 100);
        } else {
            return new Dimension(board.getDim());
        }

    }
    
    private void setup() {
        area.add(run);
        area.add(back);
        area.add(wireTest);
        area.add(ledGreen);
        area.add(ledRed);
        area.add(ledBlue);
        area.add(board);
    }
}
