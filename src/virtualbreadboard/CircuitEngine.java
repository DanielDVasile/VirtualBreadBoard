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
    
    public CircuitEngine(ArrayList<JComponent> list, ArrayList<Integer> list2) {
        components = list;
        componentID = list2;
        numOfLoops = components.size() * 2;
    }
    
    
}
