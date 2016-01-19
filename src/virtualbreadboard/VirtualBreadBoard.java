//Daniel Vasile
//16-Dec-2015
//Discription

package virtualbreadboard;

public class VirtualBreadBoard {
    
    public static void main(String[] args) {
        VirtualBreadBoard run =  new VirtualBreadBoard();
    }
    
    final static int BREAD_BOARD_MENU = 1;
    final static int MAIN_MENU = 2;
    final static int CREDIT_MENU = 3;
    final static int SCHEMATICS_MENU = 4;
    MainMenu menu;
    BreadBoardMenu bMenu;
    CreditMenu cMenu;
    SchematicsMenu sMenu;
    /**
     * primary constructor
     */
    public VirtualBreadBoard() {
        //instantizates all needed objects and sets the main menu visible
        menu = new MainMenu(this);
        bMenu = new BreadBoardMenu(this);
        cMenu = new CreditMenu(this);
        sMenu = new SchematicsMenu(this);
        Screenshot screenshot = new Screenshot(sMenu);
        sMenu.getScreenshot(screenshot);
        menu.setVisible(true);
}
    /**
     * switches between JFrames menus
     * @param a the ID of the JFrame menu
     */
    public void switchFrame(int a) {
        //sets everything to invisible
        menu.setVisible(false);
        bMenu.setVisible(false);
        sMenu.setVisible(false);
        cMenu.setVisible(false);
        //sets the selected frame to visible
        if(a == 1) {
            bMenu.setVisible(true);
            bMenu.repaint();
        } else if(a == 2) {
            menu.setVisible(true);
        } else if (a == 3) {
            cMenu.setVisible(true);
        } else if (a == 4) {
            sMenu.setVisible(true);
            sMenu.repaint();
        }
    }
}
