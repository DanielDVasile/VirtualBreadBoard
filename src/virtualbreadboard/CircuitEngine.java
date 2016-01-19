//Daniel Vasile
//17-02-2016
//A class which will simulate the circuit the user built
package virtualbreadboard;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Daniel
 */
public class CircuitEngine {

    ArrayList<JComponent> components;
    ArrayList<Integer> componentID;
    int numOfLoops;
    boolean topRow[];
    boolean topPowerRow[];
    boolean topGroundRow[];
    boolean bottomRow[];
    boolean bottomPowerRow[];
    boolean bottomGroundRow[];
    boolean groundTopRow[];
    boolean groundBottomRow[];
    boolean resistorGroundTop[];
    boolean resistorGroundBottom[];

    /**
     * primary constructor
     *
     * @param list the ArrayList which contains all the components placed by the
     * user
     * @param list2 the ArrayList which contains all the component's IDs placed
     * by the user
     */
    public CircuitEngine(ArrayList<JComponent> list, ArrayList<Integer> list2) {
        components = list;
        componentID = list2;
        numOfLoops = components.size() * 2;
        topRow = new boolean[800];
        topPowerRow = new boolean[800];
        topGroundRow = new boolean[800];
        bottomRow = new boolean[800];
        bottomPowerRow = new boolean[800];
        bottomGroundRow = new boolean[800];
        groundTopRow = new boolean[800];
        groundBottomRow = new boolean[800];
        resistorGroundTop = new boolean[800];
        resistorGroundBottom = new boolean[800];
        fillArrays();
        simulateCircuit();
    }

    /**
     * simulates the circuit
     */
    final public void simulateCircuit() {
        checkResistors();
        for (int i = 0; i < numOfLoops; i++) {
            checkWires();
            checkChips();
            checkLEDs();
        }
    }

    /**
     * checks and updates the power status of wires
     */
    public void checkWires() {
        for (int i = 0; i < components.size(); i++) {
            if (componentID.get(i) == 2) {
                checkPowerLine(i);
            }
        }
        for (int i = 0; i < componentID.size(); i++) {
            for (int j = 0; j < components.size(); j++) {
                if (componentID.get(j) == 2) {
                    updateWireBoard(j);
                }
            }
        }
    }

    /**
     * checks and updates the power status and output status of logical chips
     */
    public void checkChips() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < components.size(); j++) {

            }
        }
    }

    /**
     * checks the power status of the LEDs
     */
    public void checkLEDs() {
        for (int i = 0; i < components.size(); i++) {
            if (componentID.get(i) == 1) {
                Point LEDPoint = ((LED)components.get(i)).getPosition();
                if (LEDPoint.getY() < 280 && LEDPoint.getY() > 100) {//if the wire is connected to the top part of the breadboard
                    if( resistorGroundTop[((int)LEDPoint.getX())+ 24] == true && topRow[(int)LEDPoint.getX()] == true && groundTopRow[(int)LEDPoint.getX()] == false) {
                        ((LED)components.get(i)).setState(true);
                    }
                } else {//if the wire is connected to the bottom part of the breadboard
                    if( resistorGroundBottom[((int)LEDPoint.getX())+ 24] == true && bottomRow[(int)LEDPoint.getX()] == true && groundBottomRow[(int)LEDPoint.getX()] == false) {
                        ((LED)components.get(i)).setState(true);
                    }
                }
            }
        }
    }

    /**
     * fills each array so there are no null pointer errors
     */
    final public void fillArrays() {
        for (int i = 0; i < 800; i++) {
            topRow[i] = false;
        }
        for (int i = 0; i < 800; i++) {
            topPowerRow[i] = false;
        }
        for (int i = 0; i < 800; i++) {
            topGroundRow[i] = false;
        }
        for (int i = 0; i < 800; i++) {
            bottomRow[i] = false;
        }
        for (int i = 0; i < 800; i++) {
            bottomPowerRow[i] = false;
        }
        for (int i = 0; i < 800; i++) {
            bottomGroundRow[i] = false;
        }
        for (int i = 0; i < 800; i++) {
            groundBottomRow[i] = false;
        }
        for (int i = 0; i < 800; i++) {
            groundTopRow[i] = false;
        }
        for (int i = 0; i < 800; i++) {
            resistorGroundBottom[i] = false;
        }
        for (int i = 0; i < 800; i++) {
            resistorGroundTop[i] = false;
        }
    }

    /**
     * checks if the wire is connected to the powerLine, and if so sets the line
     * the other end is connected to as powered
     *
     * @param i the location of the wire in the arrayList
     */
    public void checkPowerLine(int i) {
        if (((Wire) components.get(i)).getPowerPin().getY() == 50) {//if the power pin is connected to the top power line
            ((Wire) components.get(i)).setState(true);//sets the wire's state to powered
            if (((Wire) components.get(i)).getGroundPin().getY() < 280 && ((Wire) components.get(i)).getGroundPin().getY() > 110) {//if the ground pin is in the top part of the breadboard
                topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to powered
            } else if (((Wire) components.get(i)).getGroundPin().getY() > 250 && ((Wire) components.get(i)).getGroundPin().getY() < 410) {//if the ground in in the bottom part of the breadboard
                topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to powered
            }
        } else if (((Wire) components.get(i)).getGroundPin().getY() == 50) {//if the ground pin is connected to the top power line
            ((Wire) components.get(i)).setState(true);//sets the wire's state to powered
            if (((Wire) components.get(i)).getPowerPin().getY() < 280 && ((Wire) components.get(i)).getPowerPin().getY() > 110) {//if the power pin is in the top part of the breadboard
                topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to powered
            } else if (((Wire) components.get(i)).getPowerPin().getY() > 250 && ((Wire) components.get(i)).getPowerPin().getY() < 410) {//if the power in in the bottom part of the breadboard
                topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to powered
            }
        } else if (((Wire) components.get(i)).getPowerPin().getY() == 482) {//if the power pin is connected to the bottom power line
            ((Wire) components.get(i)).setState(true);//sets the wire's state to powered
            if (((Wire) components.get(i)).getGroundPin().getY() < 280 && ((Wire) components.get(i)).getGroundPin().getY() > 110) {//if the ground pin is in the top part of the breadboard
                topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to powered
            } else if (((Wire) components.get(i)).getGroundPin().getY() > 250 && ((Wire) components.get(i)).getGroundPin().getY() < 410) {//if the ground in in the bottom part of the breadboard
                topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to powered
            }
        } else if (((Wire) components.get(i)).getGroundPin().getY() == 482) {//if the ground pin is connected to the bottom power line
            ((Wire) components.get(i)).setState(true);//sets the wire's state to powered
            if (((Wire) components.get(i)).getPowerPin().getY() < 280 && ((Wire) components.get(i)).getPowerPin().getY() > 110) {//if the power pin is in the top part of the breadboard
                topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to powered
            } else if (((Wire) components.get(i)).getPowerPin().getY() > 250 && ((Wire) components.get(i)).getPowerPin().getY() < 410) {//if the power in in the bottom part of the breadboard
                topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to powered
            }
        }
    }

    /**
     * updates boards state based on the wire
     *
     * @param i the wire's number in the array list
     */
    public void updateWireBoard(int i) {
        if (((Wire) components.get(i)).isPowerWire()) {//if the wire is connected to the power row
            updateWireEnds(i);
        }
        if (!((Wire) components.get(i)).isGroundWire() && !((Wire) components.get(i)).isPowerWire()) {//if the wire is not connected to the ground or power rows
            checkWirePoint1(i);
            checkWirePoint2(i);
        } else {//if the wire is connected to the ground rows
            updateGround(i);
        }
    }

    /**
     * updates the boards state to be powered since the wire is powered
     *
     * @param i the wire's number in the array list
     */
    public void updateWireEnds(int i) {
        if (((Wire) components.get(i)).getGroundPin().getY() < 280 && ((Wire) components.get(i)).getGroundPin().getY() > 110) {//if the ground pin is in the top part of the breadboard
            topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to powered
        } else if (((Wire) components.get(i)).getGroundPin().getY() > 250 && ((Wire) components.get(i)).getGroundPin().getY() < 410) {//if the ground in in the bottom part of the breadboard
            bottomRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to powered
        }
        if (((Wire) components.get(i)).getPowerPin().getY() < 280 && ((Wire) components.get(i)).getPowerPin().getY() > 110) {//if the power pin is in the top part of the breadboard
            topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to powered
        } else if (((Wire) components.get(i)).getPowerPin().getY() > 250 && ((Wire) components.get(i)).getPowerPin().getY() < 410) {//if the power in in the bottom part of the breadboard
            bottomRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to powered
        }
    }

    /**
     * checks if the wire gets powered, and if so powers the other end of the
     * wire
     *
     * @param i the location of the wire in the array
     */
    public void checkWirePoint1(int i) {
        Point wirePoint = ((Wire) components.get(i)).getPowerPin();
        if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {//if the wire is connected to the top part of the breadboard
            if (topRow[(int) wirePoint.getX()] == true) {//if the row the wire is connected to is powered
                ((Wire) components.get(i)).setState(true);//the wire is powered
                updateWireEnds(i);//the ends are updated
            }
        } else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {//if the wire is connected to the bottom part of the breadboard
            if (bottomRow[(int) wirePoint.getX()] == true) {//if the row the wire is connected to is powered
                ((Wire) components.get(i)).setState(true);//the wire is powered
                updateWireEnds(i);//the ends are updated
            }
        }
        if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {//if the wire is connected to the top part of the breadboard
            if (groundTopRow[(int) wirePoint.getX()] == true) {//if the row the wire is connected to is grounded
                if (((Wire) components.get(i)).getGroundPin().getY() < 280 && ((Wire) components.get(i)).getGroundPin().getY() > 110) {//if the ground pin is in the top part of the breadboard
                    groundTopRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to grounded
                } else if (((Wire) components.get(i)).getGroundPin().getY() > 250 && ((Wire) components.get(i)).getGroundPin().getY() < 410) {//if the ground in in the bottom part of the breadboard
                    groundBottomRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to grounded
                }
            }
        } else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {//if the wire is connected to the bottom part of the breadboard
            if (groundBottomRow[(int) wirePoint.getX()] == true) {//if the row the wire is connected to is grounded
                if (((Wire) components.get(i)).getGroundPin().getY() < 280 && ((Wire) components.get(i)).getGroundPin().getY() > 110) {//if the ground pin is in the top part of the breadboard
                    groundTopRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to grounded
                } else if (((Wire) components.get(i)).getGroundPin().getY() > 250 && ((Wire) components.get(i)).getGroundPin().getY() < 410) {//if the ground in in the bottom part of the breadboard
                    groundBottomRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;//sets the row the wire is connected to to grounded
                }
            }
        }
    }

    /**
     * checks if the wire gets powered, and if so powers the other end of the
     * wire
     *
     * @param i the location of the wire in the array
     */
    public void checkWirePoint2(int i) {
        Point wirePoint = ((Wire) components.get(i)).getGroundPin();
        if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {//if the wire is connected to the top part of the breadboard
            if (topRow[(int) wirePoint.getX()] == true) {//if the row the wire is connected to is powered
                ((Wire) components.get(i)).setState(true);//the wire is powered
                updateWireEnds(i);//the ends are updated
            }
        } else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {//if the wire is connected to the bottom part of the breadboard
            if (bottomRow[(int) wirePoint.getX()] == true) {//if the row the wire is connected to is powered
                ((Wire) components.get(i)).setState(true);//the wire is powered
                updateWireEnds(i);//the ends are updated
            }
        }
        if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {//if the wire is connected to the top part of the breadboard
            if (groundTopRow[(int) wirePoint.getX()] == true) {//if the row the wire is connected to is grounded
                if (((Wire) components.get(i)).getPowerPin().getY() < 280 && ((Wire) components.get(i)).getPowerPin().getY() > 110) {//if the ground pin is in the top part of the breadboard
                    groundTopRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to grounded
                } else if (((Wire) components.get(i)).getPowerPin().getY() > 250 && ((Wire) components.get(i)).getPowerPin().getY() < 410) {//if the ground in in the bottom part of the breadboard
                    groundBottomRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to grounded
                }
            }
        } else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {//if the wire is connected to the bottom part of the breadboard
            if (groundBottomRow[(int) wirePoint.getX()] == true) {//if the row the wire is connected to is grounded
                if (((Wire) components.get(i)).getPowerPin().getY() < 280 && ((Wire) components.get(i)).getPowerPin().getY() > 110) {//if the ground pin is in the top part of the breadboard
                    groundTopRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to grounded
                } else if (((Wire) components.get(i)).getPowerPin().getY() > 250 && ((Wire) components.get(i)).getPowerPin().getY() < 410) {//if the ground in in the bottom part of the breadboard
                    groundBottomRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;//sets the row the wire is connected to to grounded
                }
            }
        }
    }

    /**
     * updates the ground array
     *
     * @param i the position of the wire in the array list
     */
    public void updateGround(int i) {
        Point wirePoint = ((Wire) components.get(i)).getPowerPin();
        if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {//if the wire is connected to the top part of the breadboard
            groundTopRow[(int) wirePoint.getX()] = true;//grounds the row the wire is connected to
        } else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {//if the wire is connected to the bottom part of the breadboard
            groundBottomRow[(int) wirePoint.getX()] = true;//grounds the row the wire is conntected to
        }
    }
    /**
     * checks to see which rows are grounded by a resistor, and updates the boolean array of resistor grounded rows
     */
    public void checkResistors() {
        for (int i = 0; i < components.size(); i++) {
            if (componentID.get(i) == 9) {
                Point wirePoint = ((Resistor) components.get(i)).getGroundPin();
                if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {//if the wire is connected to the top part of the breadboard
                    resistorGroundTop[(int)wirePoint.getX()] = true;
                } else {//if the wire is connected to the bottom part of the breadboard
                    resistorGroundBottom[(int)wirePoint.getX()] = true;
                }
            }
        }
    }
}
