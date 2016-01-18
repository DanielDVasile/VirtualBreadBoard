//Daniel Vasile
//17-02-2016
//A class which will simulate the circuit the user built
package virtualbreadboard;

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
    
    /**
     * primary constructor
     * @param list the ArrayList which contains all the components placed by the user
     * @param list2 the ArrayList which contains all the components's IDs placed by the user
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
        fillArrays();
        simulateCircuit();
    }
    /**
     * simulates the circuit
     */
    final public void simulateCircuit() {
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

            }
        }
    }
    /**
     * checks and updates the power status and output status of logical chips
     */
    public void checkChips() {

    }
    /**
     * checks the power status of the LEDs
     */
    public void checkLEDs() {

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
    }
}
