//Daniel Vasile, Trevor Smith
//04-01-2016
//The JFrame class which displays the SkematicsMenu, and allows the user to interact with it
package virtualbreadboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
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
    JButton back;
    JButton wireButton;
    JButton resistorButton;
    JButton undoButton;
    JButton deleteButton;
    JButton ledButton;
    JButton and;
    JButton nand;
    JButton nor;
    JButton not;
    JButton or;
    JButton xor;
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
    ArrayList<Integer> componentListID = new ArrayList();
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;
    JButton save;
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
        and = new JButton("AND");
        nand = new JButton("NAND");
        nor = new JButton("NOR");
        or = new JButton("OR");
        xor = new JButton("XOR");
        not = new JButton("NOT");
        area = new JPanel();
        back = new JButton("Back");
        ledButton = new JButton("LED");
        wireButton = new JButton("Wire");
        resistorButton = new JButton("Resistor");
        undoButton = new JButton("Undo");
        deleteButton = new JButton("Delete");
        save = new JButton("Save");
        snapper = new Snapper();
        //sets JPanel layout
        area.setLayout(null);
        //sets the JComponent's locations and size
        ledButton.setBounds(850, 10, 100, 30);
        and.setBounds(850, 80 - 20, 100, 30);
        nand.setBounds(850, 130 - 20, 100, 30);
        or.setBounds(850, 180 - 20, 100, 30);
        nor.setBounds(850, 230 - 20, 100, 30);
        xor.setBounds(850, 280 - 20, 100, 30);
        not.setBounds(850, 330 - 20, 100, 30);
        wireButton.setBounds(850, 380 - 20, 100, 30);
        resistorButton.setBounds(850, 430 - 20, 100, 30);
        undoButton.setBounds(850, 480 - 20, 100, 30);
        deleteButton.setBounds(1000, 160, 100, 30);
        back.setBounds(1000, 80 - 20, 100, 30);
        save.setBounds(1000, 130 - 20, 100, 30);
        //adds MouseListeners to compnents
        undoButton.addMouseListener(undoL);
        deleteButton.addMouseListener(deleteL);
        resistorButton.addMouseListener(resistorL);
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
        save.addMouseListener(saveL);
        //adds components to the JPanel
        setup();
        //adds JPanel area to this class
        add(area);
    }

    /**
     * MouseListener used to check if the user clicked on the back button.
     */
    MouseListener backL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (back.contains(e.getPoint())) {//if the user clicked on the back button
                main.switchFrame(VirtualBreadBoard.MAIN_MENU);//goes back to the main menu
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
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (ledButton.contains(e.getPoint())) {// if the user pressed the led button
                if (ledP == false) {//if placing an led boolean is false
                    resetPlacer();//resets all placing booleans
                    ledP = true;//sets led place boolean true
                } else {
                    ledP = false;//sets led place boolean false
                }
            }
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
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (board.contains(e.getPoint())) {//if the user clicked on the board
                if (ledP == true) {//if the user choose to place an LED before hand
                    //snaps to the nearest point that the user clicked by
                    x1 = snapper.snapToX((int) e.getPoint().getX());
                    y1 = snapper.snapToY((int) e.getPoint().getY());
                    //makes sure you cant place an LED off the edge of the breadboard
                    if (x1 >= 783 - 60) {
                        JOptionPane.showMessageDialog(null, "You cannot place an LED here");
                    } else if (!snapper.ledPinUsed(x1, y1)) {//if the pin is not already being used
                        componentList.add(0, new LED());//adds an led to the component list
                        componentListID.add(0, 1);//adds the led's ID to the componentlistID array
                        ((LED) componentList.get(0)).setLocation(snapper.snapToX(x1) + 1, snapper.snapToY(y1) - 11);//sets the visual location of the led
                        area.removeAll();//removes all the components from the JPanel
                        repaint();
                        redrawAll();//adds all components to JPanel in proper order
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "This pin is already being used");
                    }
                } else if (wireP == true) {//if the user choose to place a wire before hand
                    if (!wireStep2) {//if the user has not choosen the first point for the wire yet
                        x1 = snapper.wSnapToX(e.getX(), e.getY());
                        y1 = snapper.wSnapToY(e.getY());
                        if (!snapper.pinUsed(x1, y1)) {//if the pin the user choose is not used
                            wireStep2 = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "This pin is already being used");
                        }
                    } else {
                        x2 = snapper.wSnapToX(e.getX(), e.getY());
                        y2 = snapper.wSnapToY(e.getY());
                        if (!snapper.pinUsed(x2, y2) && (!snapper.connectsInOut(x1, y1, x2, y2) || (new Wire(x1, y1, x2, y2)).isGroundWire() || (new Wire(x1, y1, x2, y2)).isPowerWire())) {//checks if the pin for the second point is being used and if the wire connects a logcial chip's output to its input
                            wireStep2 = false;
                            componentListID.add(0, 2);
                            componentList.add(0, new Wire(x1, y1, x2, y2));
                            ((Wire) componentList.get(0)).setLocation(0, 0);
                            area.removeAll();
                            repaint();
                            redrawAll();
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "This pin is already being used or you tried to connect a chip's out to it's input");
                            //makes the pin originally taken up by the first point of the wire free
                            snapper.resetPin(x1, y1);
                            //makes the pin originally taken up by the second point of the wire free
                            snapper.resetPin(x2, y2);
                            wireStep2 = false;
                        }
                    }
                } else if (andP == true) {//if the user choose to place an AND chip
                    x1 = snapper.cSnapToX((int) e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if (!snapper.cPinUsed(x1, y1, 3)) {//checks that none of the pins the chip would need are used
                        componentList.add(0, new ANDChip(true));
                        componentListID.add(0, 3);
                        ((ANDChip) componentList.get(0)).setLocation(x1 - 10, y1);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
                    }
                } else if (nandP == true) {//if the user choose to place a NAND chip
                    x1 = snapper.cSnapToX((int) e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if (!snapper.cPinUsed(x1, y1, 4)) {
                        componentListID.add(0, 4);
                        componentList.add(0, new NANDChip(true));
                        ((NANDChip) componentList.get(0)).setLocation(x1 - 10, y1);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
                    }
                } else if (norP == true) {//if the user choose to place a NOR chip
                    x1 = snapper.cSnapToX((int) e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if (!snapper.cPinUsed(x1, y1, 5)) {
                        componentList.add(0, new NORChip(true));
                        componentListID.add(0, 5);
                        ((NORChip) componentList.get(0)).setLocation(x1 - 10, y1);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
                    }
                } else if (notP == true) {//if the user choose to place a NOT chip
                    x1 = snapper.cSnapToX((int) e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if (!snapper.cPinUsed(x1, y1, 6)) {
                        componentList.add(0, new NOTChip(true));
                        componentListID.add(0, 6);
                        ((NOTChip) componentList.get(0)).setLocation(x1 - 10, y1);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
                    }
                } else if (orP == true) {//if the user choose to place an OR chip
                    x1 = snapper.cSnapToX((int) e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if (!snapper.cPinUsed(x1, y1, 7)) {
                        componentList.add(0, new ORChip(true));
                        componentListID.add(0, 7);
                        ((ORChip) componentList.get(0)).setLocation(x1 - 10, y1);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
                    }
                } else if (xorP == true) {//if the user choose to place a XOR chip
                    x1 = snapper.cSnapToX((int) e.getPoint().getX());
                    y1 = snapper.cSnapToY();
                    if (!snapper.cPinUsed(x1, y1, 8)) {
                        componentList.add(0, new XORChip(true));
                        componentListID.add(0, 8);
                        ((XORChip) componentList.get(0)).setLocation(x1 - 10, y1);
                        area.removeAll();
                        repaint();
                        redrawAll();
                        repaint();
                    }
                } else if (resistorP == true) {//if the user choose to place a resistor
                    if (!resistorStep2) {
                        //snaps to the resistor's first point to a ground pin
                        x1 = snapper.resistorSnapToX(e.getX(), e.getY());
                        y1 = snapper.resistorSnapToY(e.getY());
                        if (!snapper.pinUsed(x1, y1)) {
                            resistorStep2 = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "This pin is already being used");
                        }
                    } else {
                        //snaps the resistor's second point to a pin on the middle rows of the breadboard
                        x2 = snapper.resistorSnapToX2(e.getX());
                        y2 = snapper.resistorSnapToY2(e.getX(), e.getY());
                        if (!snapper.pinUsed(x2, y2)) {
                            resistorStep2 = false;
                            componentList.add(0, new Resistor(x1, y1, x2, y2));
                            ((Resistor)componentList.get(0)).setPbb();
                            componentListID.add(0, 9);
                            ((Resistor) componentList.get(0)).setLocation(0, 0);
                            area.removeAll();
                            repaint();
                            redrawAll();
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "This pin is already being used");
                            resistorStep2 = false;
                            //makes the pin originally taken up by the first point of the resistor free
                            snapper.resetPin(x1, y1);
                            //makes the pin originally taken up by the second point of the resistor free
                            snapper.resetPin(x2, y2);
                        }
                    }
                }
                //resets the placing boolean for all components
                resetPlacer();
            }
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
     * the MouseListener which checks to see if the user clicked the button to
     * add a wire
     */
    MouseListener wireL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (wireButton.contains(e.getPoint())) {
                if (wireP == false) {
                    resetPlacer();
                    wireP = true;
                } else {
                    wireP = false;
                }
            }
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
     * The mouse listener which checks if the user clicked to add an AND gate on
     * the board
     */
    MouseListener andL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
     * The mouse listener which checks if the user clicked to add an NAND gate
     * on the board
     */
    MouseListener nandL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
     * The mouse listener which checks if the user clicked to add an NOR gate on
     * the board
     */
    MouseListener norL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
     * The mouse listener which checks if the user clicked to add an NOT gate on
     * the board
     */
    MouseListener notL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
     * The mouse listener which checks if the user clicked to add an OR gate on
     * the board
     */
    MouseListener orL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
     * The mouse listener which checks if the user clicked to add an XOR gate on
     * the board
     */
    MouseListener xorL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
     * The mouse listener which checks if the user clicked to add a resistor to
     * the board
     */
    MouseListener resistorL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (resistorButton.contains(e.getPoint())) {
                if (resistorP == false) {
                    resetPlacer();
                    resistorP = true;
                } else {
                    resistorP = false;
                }

            }
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
    MouseListener undoL = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (undoButton.contains(e.getPoint())) {//if the user pressed the undo button
                if (componentList.size() > 0) {//if there are components in the array
                    //resets the pins that the component once used so it can be used again
                    if (componentListID.get(0) == 1) {//if the component is an led
                        snapper.resetLEDPin(((LED) componentList.get(0)).getPosition());//resets the pins based on an LED properties
                    } else if (componentListID.get(0) == 2) {//if the components is a wire
                        snapper.resetWirePin(((Wire) componentList.get(0)).getPowerPin(), ((Wire) componentList.get(0)).getGroundPin());//resets the pins based on an Wire's properties
                    } else if (componentListID.get(0) >= 3 && componentListID.get(0) <= 8) {//if the components is a logical chip
                        snapper.resetChipPin(new Point(((Chip) componentList.get(0)).getPoisiton(), 217));//resets the pins based on a logical chip's properties
                    } else if (componentListID.get(0) == 9) {//otherwise the components is a resistor
                        snapper.resetWirePin(((Resistor) componentList.get(0)).getPowerPin(), ((Resistor) componentList.get(0)).getGroundPin());//resets the pins based on a resistors properties
                    }
                    componentList.remove(0);//removes the latest placed components
                    componentListID.remove(0);//removes the latest added componentID
                    //redraws the JPanel
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
    MouseListener deleteL = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (deleteButton.contains(e.getPoint())) {
                componentList.removeAll(componentList);//removes all components from the component list
                componentListID.removeAll(componentListID);//removes all component IDs from the componentID list
                snapper.clearAllPins();//clears all the pins so the user can reuse them
                //redraws the panel
                area.removeAll();
                repaint();
                redrawAll();
                repaint();
            }
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
     * adds all the base components to the screen
     */
    private void setup() {
        //adds all basic original components back into the JPanel
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
        area.add(save);

    }

    /**
     * resets all the booleans which tell the program which component to place
     * to false
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
        if (wireStep2 == false) {
            wireP = false;
        }
        if (resistorStep2 == false) {
            resistorP = false;
        }
    }

    /**
     * adds in all components to the JPanel in proper order so they are drawn
     * properly
     */
    private void redrawAll() {
        //adds in all components placed by the user
        for (int i = 0; i < componentList.size(); i++) {
            area.add(componentList.get(i));
        }
        //adds in the basic components
        setup();
    }
    /**
     * checks if the user clicked on the save button
     */
    MouseListener saveL = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (save.contains(e.getPoint())) {
                try {
                    //saves the picture of the schematics menu as a PNG
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
    /**
     * lets the SchematicsMenu class get a Screenshot object to take the screen shot with
     * @param temp the Screenshot object
     */
    public void getScreenshot(Screenshot temp) {
        screenshot = temp;
    }
    /**
     * saves the picture of the SchematicsMenu as a png
     * @throws Exception 
     */
    public void captureScreen() throws Exception {
        //gets the location of the screenshot object
        location = screenshot.getLocation();
        //gets the size of the SchematicsMenu
        Dimension screensize = new Dimension(screenshot.getWidth(), screenshot.getHeight());
        //saves the info as a rectangle.
        Rectangle screenRectangle = new Rectangle(location, screensize);
        //instantizates a new robot
        Robot robot = new Robot();
        //takes a screenshot
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //saves the screenshot
        ImageIO.write(image, "png", new File(JOptionPane.showInputDialog("Please name the screenshot")));
    }

}
