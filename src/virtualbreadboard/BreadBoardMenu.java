//Daniel Vasile, Zachary VanderBurgt
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
    JButton wireButton;
    LED ledGreen;
    LED ledBlue;
    LED ledRed;
    ANDChip and;
    NANDChip nand;
    NORChip nor;
    NOTChip not;
    ORChip or;
    XORChip xor;
    Board board;
    Snapper snapper;
    boolean ledP = false;
    boolean wireP = false;
    boolean wireStep2 = false;
    boolean andP = false;
    boolean nandP = false;
    boolean norP = false;
    boolean notP = false;
    boolean orP = false;
    boolean xorP = false;
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
        //sets JFrame's size, background color, and exit operation.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new Board();
        setSize(1500, 562);
        setBackground(Color.DARK_GRAY);
        //instanitates all nesscary components
        ledGreen = new LED(1);
        ledGreen.setPower(true);
        ledRed = new LED(2);
        ledRed.setPower(true);
        ledBlue = new LED(3);
        ledBlue.setPower(true);
        and = new ANDChip();
        nand = new NANDChip();
        nor = new NORChip();
        or = new ORChip();
        xor = new XORChip();
        not = new NOTChip();
        area = new JPanel();
        run = new JButton("Run");
        back = new JButton("Back");
        wireButton = new JButton("Wire");
        snapper = new Snapper();
        //sets JPanel layout
        area.setLayout(null);
        //sets the JComponent's locations and size
        run.setBounds(1380, 400, 100, 30);
        back.setBounds(1380, 450, 100, 30);
        wireButton.setBounds(1380, 300, 100, 30);
        ledGreen.setBounds(850, 0, ledGreen.getWidth(), ledGreen.getHeight());
        ledRed.setBounds(875, 0, ledGreen.getWidth(), ledGreen.getHeight());
        ledBlue.setBounds(900, 0, ledGreen.getWidth(), ledGreen.getHeight());
        and.setBounds(850,50,and.getWidth(),and.getHeight());
        nand.setLocation(850,150);
        nor.setLocation(1050,50);
        or.setLocation(1050,150);
        xor.setLocation(1250, 50);
        not.setLocation(1250, 150);
        //adds MouseListeners to compnents
        run.addMouseListener(runL);
        back.addMouseListener(backL);
        wireButton.addMouseListener(wireL);
        ledGreen.addMouseListener(ledL);
        ledRed.addMouseListener(ledL);
        ledBlue.addMouseListener(ledL);
        board.addMouseListener(boardL);
        and.addMouseListener(andL);
        nand.addMouseListener(nandL);
        nor.addMouseListener(norL);
        or.addMouseListener(orL);
        xor.addMouseListener(xorL);
        not.addMouseListener(notL);
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
                    andP = false;
                    wireP = false;
                    wireStep2 = false;
                } else {
                    ledP = false;
                }

            }
            if (ledRed.contains(e.getPoint())) {
                if (ledP == false) {
                    ledP = true;
                    andP = false;
                    wireP = false;
                    wireStep2 = false;
                } else {
                    ledP = false;
                }
            }
            if (ledBlue.contains(e.getPoint())) {
                if (ledP == false) {
                    ledP = true;
                    andP = false;
                    wireP = false;
                    wireStep2 = false;
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
                } else if (andP == true){
                    andP = false;
                    componentList.add(0, new ANDChip());
                    ((ANDChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (nandP == true){
                    nandP = false;
                    componentList.add(0, new NANDChip());
                    ((NANDChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (norP == true){
                    norP = false;
                    componentList.add(0, new NORChip());
                    ((NORChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (notP == true){
                    notP = false;
                    componentList.add(0, new NOTChip());
                    ((NOTChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (orP == true){
                    orP = false;
                    componentList.add(0, new ORChip());
                    ((ORChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (xorP == true){
                    xorP = false;
                    componentList.add(0, new XORChip());
                    ((XORChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
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
    /**
     * the MouseListener which checks to see if the user clicked the button to add a wire
     */
    MouseListener wireL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (wireButton.contains(e.getPoint())) {
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
    /**
     * The mouse listener which checks if the user clicked to add an AND gate on the board
     */
    MouseListener andL = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            if (and.contains(e.getPoint())) {
                if (andP == false) {
                    ledP = false;
                    andP = true;
                    wireP = false;
                    wireStep2 = false;
                } else {
                    andP = false;
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
     * The mouse listener which checks if the user clicked to add an NAND gate on the board
     */
    MouseListener nandL = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            if (nand.contains(e.getPoint())) {
                if (nandP == false) {
                    nandP = true;
                    norP = false;
                    notP = false;
                    orP = false;
                    xorP = false;
                    ledP = false;
                    andP = false;
                    wireP = false;
                    wireStep2 = false;
                } else {
                    nandP = false;
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
     * The mouse listener which checks if the user clicked to add an NOR gate on the board
     */
    MouseListener norL = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            if (nor.contains(e.getPoint())) {
                if (norP == false) {
                    nandP = false;
                    norP = true;
                    notP = false;
                    orP = false;
                    xorP = false;
                    ledP = false;
                    andP = false;
                    wireP = false;
                    wireStep2 = false;
                } else {
                    norP = false;
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
     * The mouse listener which checks if the user clicked to add an NOT gate on the board
     */
    MouseListener notL = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            if (not.contains(e.getPoint())) {
                if (notP == false) {
                    nandP = false;
                    norP = false;
                    notP = true;
                    orP = false;
                    xorP = false;
                    ledP = false;
                    andP = false;
                    wireP = false;
                    wireStep2 = false;
                } else {
                    notP = false;
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
     * The mouse listener which checks if the user clicked to add an OR gate on the board
     */
    MouseListener orL = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            if (or.contains(e.getPoint())) {
                if (orP == false) {
                    nandP = false;
                    norP = false;
                    notP = false;
                    orP = true;
                    xorP = false;
                    ledP = false;
                    andP = false;
                    wireP = false;
                    wireStep2 = false;
                } else {
                    orP = false;
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
     * The mouse listener which checks if the user clicked to add an XOR gate on the board
     */
    MouseListener xorL = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            if (xor.contains(e.getPoint())) {
                if (xorP == false) {
                    nandP = false;
                    norP = false;
                    notP = false;
                    orP = false;
                    xorP = true;
                    ledP = false;
                    andP = false;
                    wireP = false;
                    wireStep2 = false;
                } else {
                    xorP = false;
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
    private Dimension getDim() {
        if (board == null) {
            return new Dimension(100, 100);
        } else {
            return new Dimension(board.getDim());
        }

    }
    /**
     * adds all the base compnents to the screen yo
     */
    private void setup() {
        area.add(run);
        area.add(back);
        area.add(wireButton);
        area.add(ledGreen);
        area.add(ledRed);
        area.add(ledBlue);
        area.add(board);
        area.add(and);
        area.add(nand);
        area.add(nor);
        area.add(not);
        area.add(or);
        area.add(xor);
    }
}
