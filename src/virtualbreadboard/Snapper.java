package virtualbreadboard;

import java.awt.Point;
import javax.swing.JOptionPane;

public class Snapper {

    public boolean pins[][];
    public boolean chipOutputPins[][];

    /**
     * primary constructor for the Snapper class
     */
    public Snapper() {
        pins = new boolean[1000][600];
        chipOutputPins = new boolean[1000][600];
        for (int i = 25; i <= 485; i += 24) {
            for (int j = 38; j <= 783; j += 24) {
                pins[j][i] = false;
            }
        }
        for (int i = 25; i <= 485; i += 24) {
            for (int j = 38; j <= 783; j += 24) {
                chipOutputPins[j][i] = false;
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
     *
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
                return 758;
            }
            for (int i = 0; i < 29; i++) {

                if (helper <= 99 + (24 * i)) {
                    helper = 86 + (24 * i);
                    i = 100;
                    temp = i;
                }
            }
            //if the user clicked near the bottom row of points
        } else if (y > 420) {
            //checks if the user clicked outside the bounds of the breadboard, and returns the closest point
            if (helper < 87) {
                return 86;
            } else if (helper > 757) {
                return 758;
            }
            for (int i = 0; i < 29; i++) {

                if (helper <= 99 + (24 * i)) {
                    helper = 86 + (24 * i);
                    i = 100;
                    temp = 100;
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
        if(temp == 100) {
        if (helper == 206) {
            helper -= 24;
        } else if (helper == 350) {
            helper -= 24;
        } else if (helper == 494) {
            helper -= 24;
        } else if (helper == 638) {
            helper -= 24;
        }
        }
        return helper;

    }

    /**
     * used to snap the components to their proper y position
     *
     * @param y the original y position
     * @return the new y position
     */
    public int snapToY(int y) {
        int helper = y;
        //if the user clicks to low, it snaps to the lowest point
        if (y > 396) {
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
     *
     * @param y the original y position
     * @return the new y position
     */
    public int wSnapToY(int y) {
        int helper = y;
        //loops through all the points in the top middle row, to find the point the user clicked nearest to
        if (y < 250 && y > 110) {
            if (y > 217) {
                return 217;
            }
            for (int i = 0; i < 5; i++) {
                if (helper <= 133 + (24 * i)) {
                    helper = 121 + (24 * i);
                    i = 100;
                }
            }
            //loops through all the points in the bottom middle row to find the point the user clicked nearest to
        } else if (y > 250 && y < 420) {
            for (int i = 0; i < 5; i++) {
                if (y > 387) {
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
                return 482;
            }
        }
        //returns the proper y position
        return helper;
    }

    /**
     * the method which snaps a logical chip to the correct x-position
     *
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
     *
     * @return the proper y position for any logical chip
     */
    public int cSnapToY() {
        return 217;
    }

    public boolean pinUsed(int x, int y) {
        if (pins[x][y] == false) {
            pins[x][y] = true;
            return false;
        }
        return true;
    }

    public boolean cPinUsed(int x, int y, int ID) {
        //if the chip is too close to the right edge
        if (x > 38 + (24 * 25)) {
            JOptionPane.showMessageDialog(null, "You cant place a chip here as it will be off the edge");
            return true;
        }
        //if the chip would overlap with another component (on the top middle row)
        for (int i = 0; i < 7; i++) {
            if (pins[x + (24 * i)][217] == true) {
                System.out.println(i + " " + "Hello");
                JOptionPane.showMessageDialog(null, "A pin that the chip would need to connect to is already being used");
                return true;
            }
        }
        //if the chip would overlap with another component (on the bottom middle row)
        for (int i = 0; i < 7; i++) {
            if (pins[x + (24 * i)][288]) {
                System.out.println(i + " " + "Hi");
                JOptionPane.showMessageDialog(null, "A pin that the chip would need to connect to is already being used");
                return true;
            }
        }
        //sets the pins the chip is using to true (used)
        for (int i = 0; i < 7; i++) {
            pins[x + (24 * i)][288] = true;
        }
        //sets the pins the chip is using to true (used)
        for (int i = 0; i < 7; i++) {
            pins[x + (24 * i)][217] = true;
        }
        //checks which pins will be the output pins for the chip
        if (ID == 3) {//and chip
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 2)][288] = true;
                chipOutputPins[x + (24 * 5)][288] = true;
            }
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 3)][217] = true;
                chipOutputPins[x + (24 * 6)][217] = true;
            }
        } else if (ID == 4) {//nand chip
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 2)][288] = true;
                chipOutputPins[x + (24 * 5)][288] = true;
            }
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 3)][217] = true;
                chipOutputPins[x + (24 * 6)][217] = true;
            }
        } else if (ID == 5) {//nor chip
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 2)][288] = true;
                chipOutputPins[x + (24 * 5)][288] = true;
            }
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 3)][217] = true;
                chipOutputPins[x + (24 * 6)][217] = true;
            }
        } else if (ID == 6) {//not chip
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 1)][288] = true;
                chipOutputPins[x + (24 * 3)][288] = true;
                chipOutputPins[x + (24 * 5)][288] = true;
            }
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 2)][217] = true;
                chipOutputPins[x + (24 * 4)][217] = true;
                chipOutputPins[x + (24 * 6)][217] = true;
            }
        } else if (ID == 7) {//or chip
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 2)][288] = true;
                chipOutputPins[x + (24 * 5)][288] = true;
            }
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 3)][217] = true;
                chipOutputPins[x + (24 * 6)][217] = true;
            }
        } else if (ID == 8) {//xor chip
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 2)][288] = true;
                chipOutputPins[x + (24 * 5)][288] = true;
            }
            //sets the location of the chips output pins
            for (int i = 0; i < 7; i++) {
                chipOutputPins[x + (24 * 3)][217] = true;
                chipOutputPins[x + (24 * 6)][217] = true;
            }
        }
        return false;
    }

    public boolean ledPinUsed(int x, int y) {
        if (pins[x][y] == false && pins[x + 24][y] == false) {
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
        pins[(int) x.getX()][(int) x.getY()] = false;
        pins[(int) x.getX() + 24][(int) x.getY()] = false;
    }

    public void resetChipPin(Point x) {
        pins[(int) x.getX()][(int) x.getY()] = false;
        for (int i = 0; i < 7; i++) {
            pins[(int) x.getX() + (24 * i)][(int) x.getY()] = false;
        }
        for (int i = 0; i < 7; i++) {
            pins[(int) x.getX() + (24 * i)][288] = false;
        }
        for (int i = 0; i < 7; i++) {
            chipOutputPins[(int) x.getX() + (24 * i)][(int) x.getY()] = false;
        }
        for (int i = 0; i < 7; i++) {
            chipOutputPins[(int) x.getX() + (24 * i)][288] = false;
        }
    }

    public void resetWirePin(Point one, Point two) {
        pins[(int) one.getX()][(int) one.getY()] = false;
        pins[(int) two.getX()][(int) two.getY()] = false;
    }

    public void clearAllPins() {
        for (int i = 25; i <= 485; i += 24) {
            for (int j = 38; j <= 783; j += 24) {
                pins[j][i] = false;
            }
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 600; j++) {
                chipOutputPins[i][j] = false;
            }
        }
    }

    public boolean connectsInOut(int x1, int y1, int x2, int y2) {
        if (y1 < 250 && y2 < 250) {
            if (chipOutputPins[x1][217] == true) {
                return (x1 - 24) == x2 || (x1 - 48) == x2;
            } else if (chipOutputPins[x2][217] == true) {
                return (x2 - 24) == x1 || (x2 - 48) == x1;
            } else {
                return false;
            }
        } else if (y1 < 250 && y2 > 250) {
            return false;
        } else if (y1 > 250 && y2 < 250) {
            return false;
        } else {
            if (chipOutputPins[x1][288] == true) {
                return (x1 - 24) == x2 || (x1 - 48) == x2;
            } else if (chipOutputPins[x2][288] == true) {
                return (x2 - 24) == x1 || (x2 - 48) == x1;
            } else {
                return false;
            }
        }
    }

    public int resistorSnapToX(int x, int y) {
        int helper = x;
        //if the user clicked near the top row of points
        if (y < 300) {
            //checks if the user clicked outside the bounds of the breadboard, and returns the closest point
            if (helper < 87) {
                return 86;
            } else if (helper > 757) {
                return 758;
            }
            for (int i = 0; i < 29; i++) {

                if (helper <= 99 + (24 * i)) {
                    helper = 86 + (24 * i);
                    i = 100;
                }
            }
            //if the user clicked near the bottom row of points
        } else if (y > 300) {
            //checks if the user clicked outside the bounds of the breadboard, and returns the closest point
            if (helper < 87) {
                return 86;
            } else if (helper > 757) {
                return 758;
            }
            for (int i = 0; i < 29; i++) {

                if (helper <= 99 + (24 * i)) {
                    helper = 86 + (24 * i);
                    i = 101;
                }
            }
            //if the user clicked near the two middle rows of points
        }
        //since there aren't always points in the breadboard, if the program snapped to somewhere where it thought there was one, it snapps to the closest real point
        if (helper == 206) {
            helper -= 24;
        } else if (helper == 350) {
            helper -= 24;
        } else if (helper == 494) {
            helper -= 24;
        } else if (helper == 638) {
            helper -= 24;
        }
        return helper;
    }

    public int resistorSnapToY( int y) {
        if(y < 300) {
            return 26;
        } else {
            return 458;
        }
    }
    
    public int resistorSnapToY2( int x, int y) {
        int helper = y;
        //loops through all the points in the top middle row, to find the point the user clicked nearest to
        if(y < 110) {
            return 217;
        } else if(y > 420) {
            return 386;
        }
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
                if (helper <= 300 + (24 * i)) {
                    helper = 288 + (24 * i);
                    i = 100;
                }
            }
            //if the user clicked near the top row, it finds the proper y position
        }
        return helper;
    }
    
    public int resistorSnapToX2(int x) {
        int helper = x;
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
        return helper;
    }
}
