package virtualbreadboard;

import java.awt.Point;
import javax.swing.JOptionPane;

public class Snapper {

    public boolean pins[][];
    /**
     * primary constructor for the Snapper class
     */
    public Snapper() {
        pins = new boolean[1000][500];
        for (int i = 25; i <= 485; i += 24) {
            for (int j = 38; j <= 783; j += 24) {
                pins[j][i] = false;
            }
        }
    }

    /**
     * A method which will return an X position on the bread board which is
     * closet to an actual point on the breadboard
     *
     * @param x the original X position
     * @return the X position which was closest
     */
    public int snapToX(int x) {
        int helper = x;
        //checks if the user clicked outside the bounds of the breadboard, and returns the closest point
        if (helper > 783) {
            return 783;
        } else if (helper < 38) {
            return 38;
        }
        //loops through each point to find which one the spot the user clicked on is closest to
        for (int i = 0; i < 32; i++) {
            if (helper <= 50 + (24 * i)) {
                helper = 38 + (24 * i);
                i = 100;
            }
        }
        //returns the closest point
        return helper;
    }
    /**
     * used to snap wire x locations to the their proper spot on the bread board
     * @param x the original x position
     * @param y the y position
     * @return the new x position
     */
    public int wSnapToX(int x, int y) {
        int helper = x;
        int temp = 0;
        //if the user clicked near the top row of points
        if (y < 120) {
            //checks if the user clicked outside the bounds of the breadboard, and returns the closest point
            if (helper < 87) {
                return 86;
            } else if (helper > 757) {
                return 782;
            }
            for (int i = 0; i < 29; i++) {

                if (helper <= 99 + (24 * i)) {
                    helper = 86 + (24 * i);
                    temp = 100;
                    i = 100;
                }
            }
            //if the user clicked near the bottom row of points
        } else if (y > 420) {
            //checks if the user clicked outside the bounds of the breadboard, and returns the closest point
            if (helper < 87) {
                return 86;
            } else if (helper > 757) {
                return 782;
            }
            for (int i = 0; i < 29; i++) {

                if (helper <= 99 + (24 * i)) {
                    helper = 86 + (24 * i);
                    i = 101;
                    temp = 101;
                }
            }
            //if the user clicked near the two middle rows of points
        } else {
            //checks if the user clicked outside the bounds of the breadboard, and returns the closest point
            if (helper > 783) {
                return 782;
            } else if (helper < 38) {
                return 38;
            }
            for (int i = 0; i < 32; i++) {
                if (helper <= 50 + (24 * i)) {
                    helper = 38 + (24 * i);
                    i = 100;
                }
            }
        }
        //since there aren't always points in the breadboard, if the program snapped to somewhere where it thought there was one, it snapps to the closest real point
        if (temp == 100) {//for top row
            if (helper == 206) {
                helper -= 24;
            } else if (helper == 350) {
                helper -= 24;
            } else if (helper == 494) {
                helper -= 24;
            } else if (helper == 638) {
                helper -= 24;
            }
        } else if (temp == 101) {//for bottom row
            if (helper == 207) {
                helper -= 24;
            } else if (helper == 351) {
                helper -= 24;
            } else if (helper == 495) {
                helper -= 24;
            } else if (helper == 639) {
                helper -= 24;
            }
        }
        return helper;

    }
    /**
     * used to snap the components to their proper y position
     * @param y the original y position
     * @return the new y position
     */
    public int snapToY(int y) {
        int helper = y;
        //if the user clicks to low, it snaps to the lowest point
        if(y > 396) {
            return 288 + (4 * 24);
        }
        //loops through all the points in the top middle row, to find the point the user clicked nearest to
        if (y < 250) {
            for (int i = 0; i < 5; i++) {
                if (helper <= 133 + (24 * i)) {
                    helper = 121 + (24 * i);
                    i = 100;
                }
            }
            //loops through all the points in the bottom middle row to find the point the user clicked nearest to
        } else {
            for (int i = 0; i < 5; i++) {
                if (helper <= 300 + (24 * i)) {
                    helper = 289 + (24 * i);
                    i = 100;
                }
            }
        }
        //returns the point
        return helper;
    }
    /**
     * used to snap wires to their proper y position
     * @param y the original y position
     * @return the new y position
     */
    public int wSnapToY(int y) {
        int helper = y;
        //loops through all the points in the top middle row, to find the point the user clicked nearest to
        if (y < 250 && y > 110) {
            for (int i = 0; i < 5; i++) {
                if (helper <= 133 + (24 * i)) {
                    helper = 121 + (24 * i);
                    i = 100;
                }
            }
            //loops through all the points in the bottom middle row to find the point the user clicked nearest to
        } else if (y > 250 && y < 420) {
            for (int i = 0; i < 5; i++) {
                if(y > 387) {
                    return 386;
                }
                if (helper <= 300 + (24 * i)) {
                    helper = 288 + (24 * i);
                    i = 100;
                }
            }
            //if the user clicked near the top row, it finds the proper y position
        } else if (y < 120) {
            if (y < 38) {
                return 26;
            } else {
                return 50;
            }
            //if the user clicked near the bottom row, it finds the proper y position
        } else {
            if (y < 469) {
                return 458;
            } else {
                return 484;
            }
        }
        //returns the proper y position
        return helper;
    }
    /**
     * the method which snaps a logical chip to the correct x-position
     * @param x the original x position
     * @return the corrected x position
     */
    public int cSnapToX(int x) {
        int helper = x;
        //checks if the user clicked outside the bounds of the breadboard, and returns the closest point
        if (helper > 783) {
            return 783;
        } else if (helper < 38) {
            return 38;
        }
        //loops through each point to find which one the spot the user clicked on is closest to
        for (int i = 0; i < 32; i++) {
            if (helper <= 50 + (24 * i)) {
                helper = 38 + (24 * i);
                i = 100;
            }
        }
        //returns the closest point
        return helper;
    }
    /**
     * returns the proper y position for any logical chip
     * @return the proper y position for any logical chip
     */
    public int cSnapToY() {
        return 217;
    }
    
    public boolean pinUsed(int x, int y) {
        if(pins[x][y] == false) {
            pins[x][y] = true;
            return false;
        }
        return true;
    }
    
    public boolean cPinUsed(int x, int y) {
        if(x > 38 + (24 * 25)) {
            JOptionPane.showMessageDialog(null, "You cant place a chip here as it will be off the edge");
            return true;
        }
        for (int i = 0; i < 7; i++) {
            if(pins[x + (24 * i)][217] == true) {
                JOptionPane.showMessageDialog(null, "A pin that the chip would need to connect to is already being used");
                return true;
            }
        }
        for (int i = 0; i < 7; i++) {
            if(pins[x + (24 * i)][288]) {
                JOptionPane.showMessageDialog(null, "A pin that the chip would need to connect to is already being used");
                return true;
            }
        }
        for (int i = 0; i < 7; i++) {
            pins[x + (24 * i)][288] = true;
        }
        for (int i = 0; i < 7; i++) {
        pins[x + (24 * i)][217] = true;
        }
        return false;
    }
    
    public boolean ledPinUsed(int x, int y) {
        if(pins[x][y] == false && pins[x + 24][y] == false) {
            pins[x][y] = true;
            pins[x + 24][y] = true;
            return false;
        }
        return true;
    }
    
    public void resetPin(int x, int y) {
        pins[x][y] = false;
    }
    
    public void resetLEDPin(Point x) {
        pins[(int)x.getX()][(int)x.getY()] = false;
        pins[(int)x.getX() + 24][(int)x.getY()] = false;
    }
    
    public void resetChipPin(Point x) {
        pins[(int)x.getX()][(int)x.getY()] = false;
        for (int i = 0; i < 7; i++) {
            pins[(int)x.getX() + (24 * i)][(int)x.getY()] = false;
        }
        for (int i = 0; i < 7; i++) {
            pins[(int)x.getX() + (24 * i)][288] = false;
        }
    }
    
    public void resetWirePin(Point one, Point two) {
        pins[(int)one.getX()][(int)one.getY()] = false;
        pins[(int)two.getX()][(int)two.getY()] = false;
    }
    
    public void clearAllPins() {
        for (int i = 25; i <= 485; i += 24) {
            for (int j = 38; j <= 783; j += 24) {
                pins[j][i] = false;
            }
        }
    }
}
