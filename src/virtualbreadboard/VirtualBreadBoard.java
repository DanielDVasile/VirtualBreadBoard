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
        menu = new MainMenu(this);
        bMenu = new BreadBoardMenu(this);
        cMenu = new CreditMenu(this);
        sMenu = new SchematicsMenu(this);
        menu.setVisible(true);
}
    public void switchFrame(int a) {
        menu.setVisible(false);
        bMenu.setVisible(false);
        sMenu.setVisible(false);
        cMenu.setVisible(false);
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
