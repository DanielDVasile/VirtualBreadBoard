package virtualbreadboard;

public class Snapper {
	/**
	 * primary constructor for the Snapper class
	 */
	public Snapper() {
		
	}
	/**
	 * A method which will return an X position on the bread board which is closet to an actual point on the breadboard
	 * @param x the original X position
	 * @return the X position which was closest
	 */
	public int snapToX(int x) {
		int helper = x;
		for(int i = 0; i < 32; i++) {
			if(helper <= 50 + (24 * i)) {
				helper = 38 + (24 * i);
				i = 100;
			}
		}
	    return helper;

	}
	
	public int snapToY(int y) {
		int helper = y;
		if(y < 250) {
		for(int i = 0; i < 5; i++) {
			if(helper <= 133 + (24 * i)) {
				helper = 121 + (24 * i);
				i = 100;
			}
		}
		} else {
			for(int i = 0; i < 5; i++) {
				if(helper <= 300 + (24 * i)) {
					helper = 288 + (24 * i);
					i = 100;
				}
			}
		}
		return helper;
	}
	
}
