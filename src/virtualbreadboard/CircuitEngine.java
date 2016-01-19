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
    boolean bottomRow[];
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
        //loads all components
        components = list;
        componentID = list2;
        numOfLoops = components.size() * 2;
        topRow = new boolean[800];
        bottomRow = new boolean[800];
        groundTopRow = new boolean[800];
        groundBottomRow = new boolean[800];
        resistorGroundTop = new boolean[800];
        resistorGroundBottom = new boolean[800];
        //fills arrays to aviod null pointer execptions
        fillArrays();
        //simulates the circuit
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
                if (componentID.get(j) <= 8 && componentID.get(j) >= 3) {
                    checkChipPower(j);
                    checkChipGround(j);
                    checkChipInput(j);
                    checkChipOutput(j, componentID.get(j));
                }
            }
        }
    }

    /**
     * checks the power status of the LEDs
     */
    public void checkLEDs() {
        for (int i = 0; i < components.size(); i++) {
            if (componentID.get(i) == 1) {
                Point LEDPoint = ((LED) components.get(i)).getPosition();
                if (LEDPoint.getY() < 280 && LEDPoint.getY() > 100) {//if the wire is connected to the top part of the breadboard
                    if (groundTopRow[(int) LEDPoint.getX() + 24] == true) {
                        groundTopRow[(int) LEDPoint.getX()] = true;
                    }
                    if (resistorGroundTop[((int) LEDPoint.getX()) + 24] == true && topRow[(int) LEDPoint.getX()] == true && groundTopRow[(int) LEDPoint.getX()] == false && groundTopRow[(int) LEDPoint.getX() + 24] == false) {
                        ((LED) components.get(i)).setState(true);
                        topRow[(int) LEDPoint.getX() + 24] = true;
                    } else {
                        ((LED) components.get(i)).setState(false);
                    }
                } else {//if the wire is connected to the bottom part of the breadboard
                    if (groundBottomRow[(int) LEDPoint.getX() + 24] == true) {
                        groundBottomRow[(int) LEDPoint.getX()] = true;
                    }
                    if (resistorGroundBottom[((int) LEDPoint.getX()) + 24] == true && bottomRow[(int) LEDPoint.getX()] == true && groundBottomRow[(int) LEDPoint.getX()] == false && groundBottomRow[(int) LEDPoint.getX() + 24] == false) {
                        ((LED) components.get(i)).setState(true);
                        bottomRow[(int) LEDPoint.getX() + 24] = true;
                    } else {
                        ((LED) components.get(i)).setState(true);
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
            bottomRow[i] = false;
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
     * checks to see which rows are grounded by a resistor, and updates the
     * boolean array of resistor grounded rows
     */
    public void checkResistors() {
        for (int i = 0; i < components.size(); i++) {
            if (componentID.get(i) == 9) {
                Point wirePoint = ((Resistor) components.get(i)).getGroundPin();
                if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {//if the wire is connected to the top part of the breadboard
                    resistorGroundTop[(int) wirePoint.getX()] = true;
                } else {//if the wire is connected to the bottom part of the breadboard
                    resistorGroundBottom[(int) wirePoint.getX()] = true;
                }
            }
        }
    }

    /**
     * checks if the chip is powered
     *
     * @param i the location of the chip in the array list
     */
    public void checkChipPower(int i) {
        if (topRow[((Chip) components.get(i)).getPoisiton()]) {//if the row the logical chip's power pin is in is powered
            ((Chip) components.get(i)).setPowered(true);
        } else {//otherwise
            ((Chip) components.get(i)).setPowered(false);
        }
    }

    /**
     * checks if the chip is grounded
     *
     * @param i the location of the chip in the array list
     */
    public void checkChipGround(int i) {
        if (groundBottomRow[((Chip) components.get(i)).getPoisiton() + (24 * 6)]) {//if the row the logical chip's ground pin is in is grounded
            ((Chip) components.get(i)).setGrounded(true);
        } else {//otherwise
            ((Chip) components.get(i)).setGrounded(false);
        }
    }

    /**
     * checks the input pins of the logical chip
     *
     * @param i the location in the array list of the logical chip
     */
    public void checkChipInput(int i) {
        if (componentID.get(i) != 6) {
            regularChipInput(i);
        } else {
            NOTChipInput(i);
        }
    }

    /**
     * sets the input states for all logical chips except the NOT chip
     *
     * @param i the chip's location in the array list
     */
    public void regularChipInput(int i) {
        int counter = 0;
        int x = ((Chip) components.get(i)).getPoisiton() + 24;
        for (int j = 0; j < 2; j++) {//sets the input pin state for the pins on the top row
            ((Chip) components.get(i)).setInputPinState(topRow[x], counter, 0);
            ((Chip) components.get(i)).setInputPinState(topRow[x + 24], counter, 1);
            x = +24 * 3;//moves the input pins to the next pair
            counter++;
        }
        x = ((Chip) components.get(i)).getPoisiton();
        for (int j = 0; j < 2; j++) {//sets the input pin state for the pins on the bottom row
            ((Chip) components.get(i)).setInputPinState(bottomRow[x], counter, 0);
            ((Chip) components.get(i)).setInputPinState(bottomRow[x + 24], counter, 1);
            x = +24 * 3;//moves the input pins to the next pair
            counter++;
        }
    }

    /**
     * sets the input pin states for the NOT Chip
     *
     * @param i the location of the NOT chip in the array list
     */
    public void NOTChipInput(int i) {
        int counter = 0;
        int x = ((Chip) components.get(i)).getPoisiton() + 24;
        for (int j = 0; j < 3; j++) {//sets the input pin state for the pins on the top row
            ((NOTChip) components.get(i)).setNOTInputPinState(topRow[x], counter);
            x = +24;//moves the input pin location to the pin
            counter++;
        }
        x = ((Chip) components.get(i)).getPoisiton();
        for (int j = 0; j < 3; j++) {//sets the input pin state for the pins on the bottom row
            ((NOTChip) components.get(i)).setNOTInputPinState(bottomRow[x], counter);
            ((NOTChip) components.get(i)).setNOTInputPinState(bottomRow[x + 24], counter);
            x = +24;//moves the input pin location to the pin
            counter++;
        }
    }

    /**
     * checks all the outputs for all logical chips, and updates the power
     * boolean arrays
     *
     * @param i the chip's location in the array list
     * @param componentID the chip's ID number
     */
    public void checkChipOutput(int i, int componentID) {
        if (((Chip) components.get(i)).isPowered() && ((Chip) components.get(i)).isGrounded()) {
            int x = ((Chip) components.get(i)).getPoisiton();
            if (componentID == 3) {//checks all the outputs for a AND chip
                System.out.println(((ANDChip) components.get(i)).getOutput(0));
                topRow[x + (24 * 3)] = ((ANDChip) components.get(i)).getOutput(0);
                topRow[x + (24 * 6)] = ((ANDChip) components.get(i)).getOutput(1);
                bottomRow[x + (24 * 2)] = ((ANDChip) components.get(i)).getOutput(2);
                bottomRow[x + (24 * 5)] = ((ANDChip) components.get(i)).getOutput(3);
            } else if (componentID == 4) {//checks all the outputs for a NAND chip
                topRow[x + 24 * 3] = ((NANDChip) components.get(i)).getOutput(0);
                topRow[x + 24 * 6] = ((NANDChip) components.get(i)).getOutput(1);
                bottomRow[x + 24 * 2] = ((NANDChip) components.get(i)).getOutput(2);
                bottomRow[x + 24 * 5] = ((NANDChip) components.get(i)).getOutput(3);
            } else if (componentID == 5) {//checks all the outputs for a NOR chip
                topRow[x + 24 * 3] = ((NORChip) components.get(i)).getOutput(0);
                topRow[x + 24 * 6] = ((NORChip) components.get(i)).getOutput(1);
                bottomRow[x + 24 * 2] = ((NORChip) components.get(i)).getOutput(2);
                bottomRow[x + 24 * 5] = ((NORChip) components.get(i)).getOutput(3);
            } else if (componentID == 6) {//checks all the outputs for a NOT chip
                topRow[x + 24 * 2] = ((NOTChip) components.get(i)).getOutput(0);
                topRow[x + 24 * 4] = ((NOTChip) components.get(i)).getOutput(1);
                topRow[x + 24 * 6] = ((NOTChip) components.get(i)).getOutput(2);
                bottomRow[x + 24 * 1] = ((NOTChip) components.get(i)).getOutput(3);
                bottomRow[x + 24 * 3] = ((NOTChip) components.get(i)).getOutput(4);
                bottomRow[x + 24 * 5] = ((NOTChip) components.get(i)).getOutput(5);
            } else if (componentID == 7) {//checks all the outputs for a OR chip
                topRow[x + 24 * 3] = ((ORChip) components.get(i)).getOutput(0);
                topRow[x + 24 * 6] = ((ORChip) components.get(i)).getOutput(1);
                bottomRow[x + 24 * 2] = ((ORChip) components.get(i)).getOutput(2);
                bottomRow[x + 24 * 5] = ((ORChip) components.get(i)).getOutput(3);
            } else {//checks all the outputs for a XOR chip
                topRow[x + 24 * 3] = ((XORChip) components.get(i)).getOutput(0);
                topRow[x + 24 * 6] = ((XORChip) components.get(i)).getOutput(1);
                bottomRow[x + 24 * 2] = ((XORChip) components.get(i)).getOutput(2);
                bottomRow[x + 24 * 5] = ((XORChip) components.get(i)).getOutput(3);
            }
        }
    }
}
