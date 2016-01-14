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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BreadBoardMenu extends JFrame{
    
    JPanel area;
    Graphics2D drawer;
    VirtualBreadBoard main;
    JButton run;
    JButton back;
    JButton wireButton;
    JButton resistorButton;
    JButton undoButton;
    JButton deleteButton;
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
    boolean resistorP = false;
    boolean resistorStep2 = false;
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
        setSize(1300, 562);
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
        resistorButton = new JButton("Resistor");
        undoButton = new JButton("Undo");
        deleteButton = new JButton("Delete");
        snapper = new Snapper();
        //sets JPanel layout
        area.setLayout(null);
        //sets the JComponent's locations and size
        wireButton.setBounds(1050, 250, 100, 30);
        undoButton.setBounds(1050, 300, 100, 30);
        deleteButton.setBounds(1050, 350, 100, 30);
        resistorButton.setBounds(1150, 250, 100, 30);
        run.setBounds(1150, 300, 100, 30);
        back.setBounds(1150, 350, 100, 30);
        ledGreen.setBounds(850, 0, ledGreen.getWidth(), ledGreen.getHeight());
        ledRed.setBounds(875, 0, ledGreen.getWidth(), ledGreen.getHeight());
        ledBlue.setBounds(900, 0, ledGreen.getWidth(), ledGreen.getHeight());
        and.setLocation(850,50);
        nand.setLocation(850,150);
        nor.setLocation(850,350);
        or.setLocation(850,250);
        xor.setLocation(1050, 50);
        not.setLocation(1050, 150);
        //adds MouseListeners to compnents
        undoButton.addMouseListener(undoL);
        deleteButton.addMouseListener(deleteL);
        resistorButton.addMouseListener(resistorL);
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
                    resetPlacer();
                    ledP = true;
                } else {
                    ledP = false;
                }

            }
            if (ledRed.contains(e.getPoint())) {
                if (ledP == false) {
                    resetPlacer();
                    ledP = true;
                } else {
                    ledP = false;
                }
            }
            if (ledBlue.contains(e.getPoint())) {
                if (ledP == false) {
                    resetPlacer();
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
                    x1 = snapper.snapToX((int)e.getPoint().getX());
                    y1 = snapper.snapToY((int)e.getPoint().getY());
                    if(!snapper.pinUsed(x1, y1)) {
                    componentList.add(0, new LED(0));
                    ((LED) componentList.get(0)).setLocation(snapper.snapToX(x1) + 1, snapper.snapToY(y1) - 11);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "This pin is already being used");
                    }
                } else if (wireP == true) {
                    if (!wireStep2) {
                        x1 = snapper.wSnapToX(e.getX(), e.getY());
                        y1 = snapper.wSnapToY(e.getY());
                        if(!snapper.pinUsed(x1, y1)) {
                            wireStep2 = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "This pin is already being used");
                        }
                    } else {
                            x2 = snapper.wSnapToX(e.getX(), e.getY());
                            y2 = snapper.wSnapToY(e.getY());
                            if(!snapper.pinUsed(x2, y2)) {
                            wireStep2 = false;
                            componentList.add(0, new Wire(x1, y1, x2, y2));
                            ((Wire) componentList.get(0)).setLocation(0, 0);
                            area.removeAll();
                            repaint();
                            redrawAll();
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "This pin is already being used");
                            snapper.resetPin(x1,y1);
                            snapper.resetPin(x2,y2);
                        }
                    }
                } else if (andP == true){
                    x1 = snapper.cSnapToX((int)e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if(!snapper.cPinUsed(x1, y1)) {
                    componentList.add(0, new ANDChip());
                    ((ANDChip) componentList.get(0)).setLocation(x1 - 23,y1);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "This pin is already being used");
                    }
                } else if (nandP == true){
                    x1 = snapper.cSnapToX((int)e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if(!snapper.cPinUsed(x1, y1)) {
                    componentList.add(0, new NANDChip());
                    ((NANDChip) componentList.get(0)).setLocation(x1,y1);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "This pin is already being used");
                    }
                } else if (norP == true){
                    x1 = snapper.cSnapToX((int)e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if(!snapper.cPinUsed(x1, y1)) {
                    componentList.add(0, new NORChip());
                    ((NORChip) componentList.get(0)).setLocation(x1,y1);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "This pin is already being used");
                    }
                } else if (notP == true){
                    x1 = snapper.cSnapToX((int)e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if(!snapper.cPinUsed(x1, y1)) {
                    componentList.add(0, new NOTChip());
                    ((NOTChip) componentList.get(0)).setLocation(x1,y1);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "This pin is already being used");
                    }
                } else if (orP == true){
                    x1 = snapper.cSnapToX((int)e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if(!snapper.cPinUsed(x1, y1)) {
                    componentList.add(0, new ORChip());
                    ((ORChip) componentList.get(0)).setLocation(x1,y1);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "This pin is already being used");
                    }
                } else if (xorP == true){
                    x1 = snapper.cSnapToX((int)e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if(!snapper.pinUsed(x1, y1)) {
                    componentList.add(0, new XORChip());
                    ((XORChip) componentList.get(0)).setLocation(x1,y1);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "This pin is already being used");
                    }
                } else if (resistorP == true) {
                    if (!resistorStep2) {
                        x1 = snapper.wSnapToX(e.getX(), e.getY());
                        y1 = snapper.wSnapToY(e.getY());
                        if(!snapper.pinUsed(x1, y1)) {
                        resistorStep2 = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "This pin is already being used");
                        }
                    } else {
                        x2 = snapper.wSnapToX(e.getX(), e.getY());
                        y2 = snapper.wSnapToY(e.getY());
                        if(snapper.pinUsed(x2, y2)) {
                        resistorStep2 = false;
                        componentList.add(0, new Resistor(x1, y1, x2, y2));
                        ((Resistor) componentList.get(0)).setLocation(0, 0);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "This pin is already being used");
                            resistorStep2 = false;
                        }
                    }
                }
                resetPlacer();
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
     * the MouseListener which checks to see if the user clicked the button to add a wire
     */
    MouseListener wireL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (wireButton.contains(e.getPoint())) {
                if(wireP == false) {
                    resetPlacer();
                    wireP = true;
                } else {
                    wireP = false;
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
     * The mouse listener which checks if the user clicked to add an AND gate on the board
     */
    MouseListener andL = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            if (and.contains(e.getPoint())) {
                if (andP == false) {
                    resetPlacer();
                    andP = true;
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
                    resetPlacer();
                    nandP = true;
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
                    resetPlacer();
                    norP = true;
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
                    resetPlacer();
                    notP = true;
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
                    resetPlacer();
                    orP = true;
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
                    resetPlacer();
                    xorP = true;
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
    /**
     * The mouse listener which checks if the user clicked to add a resistor to the board
     */
    MouseListener resistorL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(resistorButton.contains(e.getPoint())) {
                if(resistorP == false) {
                    resetPlacer();
                    resistorP = true;
                } else {
                    resistorP = false;
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
     * The mouse listener which checks if the user clicked the undo button
     */
    MouseListener undoL = new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
            if(undoButton.contains(e.getPoint())) {
                if (componentList.size()>0) {
                    componentList.remove(0);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else {
                    System.out.println("Empty array");
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
     * The mouse listener which checks if the user clicked the delete button
     */
    MouseListener deleteL = new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
            if(deleteButton.contains(e.getPoint())) {
                componentList.removeAll(componentList);
                area.removeAll();
                repaint();
                redrawAll();
                repaint();
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
        area.add(resistorButton);
        area.add(undoButton);
        area.add(deleteButton);
    }
    /**
     * resets all the booleans which tell the program which component to place to false
     */
    private void resetPlacer() {
                    nandP = false;
                    norP = false;
                    notP = false;
                    orP = false;
                    xorP = false;
                    ledP = false;
                    andP = false;
                    xorP = false;
                    if(wireStep2 == false) {
                        wireP = false;
                    }
                    if(resistorStep2 == false) {
                        resistorP = false;
                    }
    }
    private void redrawAll() {
            for (int i = 0; i < componentList.size(); i++) {
                area.add(componentList.get(i));
            }
            setup();
        }
}
