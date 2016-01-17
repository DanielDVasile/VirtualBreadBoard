//Daniel Vasile, Trevor Smith
//04-01-2016
//The JFrame class which displays the SkematicsMenu, and allows the user to interact with it
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
    
    JPanel area;
    Graphics2D drawer;
    VirtualBreadBoard main;
    JButton run;
    JButton back;
    JButton wireButton;
    JButton resistorButton;
    JButton undoButton;
    JButton deleteButton;
    JButton ledButton;
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
        board = new Board(true);
        setSize(1300, 562);
        setBackground(Color.DARK_GRAY);
        //instanitates all nesscary components
        and = new ANDChip(true);
        nand = new NANDChip(true);
        nor = new NORChip(true);
        or = new ORChip(true);
        xor = new XORChip(true);
        not = new NOTChip(true);
        area = new JPanel();
        run = new JButton("Run");
        back = new JButton("Back");
        ledButton = new JButton("LED");
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
        ledButton.setBounds(850, 0,100,30);
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
        ledButton.addMouseListener(ledL);
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
            }if(back.contains(e.getPoint())) {
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
    /**
     * MouseListener used to check if the user clicked on the LED button.
     */
    MouseListener ledL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (ledButton.contains(e.getPoint())) {
                System.out.println("2");
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
                    componentList.add(0, new LED());
                    ((LED) componentList.get(0)).setBounds(snapper.snapToX(e.getX()) + 1, snapper.snapToY(e.getY()) - 11, 100, 100);
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (wireP == true) {
                    if (!wireStep2) {
                        x1 = snapper.wSnapToX(e.getX(), e.getY());
                        y1 = snapper.wSnapToY(e.getY());
                        wireStep2 = true;
                    } else {
                        wireStep2 = false;
                        x2 = snapper.wSnapToX(e.getX(), e.getY());
                        y2 = snapper.wSnapToY(e.getY());
                        componentList.add(0, new Wire(x1, y1, x2, y2));
                        ((Wire) componentList.get(0)).setLocation(0+2, 0+3);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
                    }
                } else if (andP == true){
                    componentList.add(0, new ANDChip(true));
                    ((ANDChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (nandP == true){
                    componentList.add(0, new NANDChip(true));
                    ((NANDChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (norP == true){
                    componentList.add(0, new NORChip(true));
                    ((NORChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (notP == true){
                    componentList.add(0, new NOTChip(true));
                    ((NOTChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (orP == true){
                    componentList.add(0, new ORChip(true));
                    ((ORChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (xorP == true){
                    componentList.add(0, new XORChip(true));
                    ((XORChip) componentList.get(0)).setLocation(snapper.cSnapToX(e.getX()) - 24, snapper.cSnapToY());
                    area.removeAll();
                    repaint();
                    redrawAll();
                    repaint();
                } else if (resistorP == true) {
                    if (!resistorStep2) {
                        x1 = snapper.wSnapToX(e.getX(), e.getY());
                        y1 = snapper.wSnapToY(e.getY());
                        resistorStep2 = true;
                    } else {
                        resistorStep2 = false;
                        x2 = snapper.wSnapToX(e.getX(), e.getY());
                        y2 = snapper.wSnapToY(e.getY());
                        Resistor temp = new Resistor(x1, y1, x2, y2);
                        temp.setPbb();
                        componentList.add(0, temp);
                        ((Resistor) componentList.get(0)).setLocation(0, 0);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
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
     * adds all the base compnents to the screen
     */
    private void setup() {
        area.add(run);
        area.add(back);
        area.add(wireButton);
        area.add(ledButton);
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
