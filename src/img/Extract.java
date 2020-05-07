package img;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Extract {
	
	/**
	 * @param img
	 * @throws IOException
	 */
	public static void values(BufferedImage img) throws IOException {
		// Start Points 
		// River -- X: 737, 833, 929, 1025, 1121 -- Y: 297
		// Hands -- X: 722, 775, 1083, 1133  -- Y: Leftmost 474, all the rest 468
		
		//Define and initialize map
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		map.put(737, 297);
		map.put(833, 297);
		map.put(929, 297);
		map.put(1025, 297);
		map.put(1121, 297);
		map.put(722, 474);
		map.put(775, 468);
		map.put(1083, 468);
		map.put(1133, 468);

		int width  = 22;
		int height  = 30;
		
		//get card value from each coordinate in map
		for (Integer key : map.keySet()) {
		
			BufferedImage out = img.getSubimage(key, map.get(key), width, height);
			
			Change.toBlackAndWhite(out, "Value");
		}
	}
	
	/**
	 * @param img
	 * @throws IOException
	 */
	public static void suits(BufferedImage img) throws IOException {
		// Start Points 
		// River -- X: 734, 830, 925, 1021, 1117 -- Y: 350
		// Hands -- X: 730, 772, 1080, 1122  -- Y: 524 Leftmost, 520 for rest
		
		//Define and initialize map
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		map.put(734, 350);
		map.put(830, 350);
		map.put(925, 350);
		map.put(1021, 350);
		map.put(1117, 350);
		map.put(730, 524);
		map.put(772, 520);
		map.put(1080, 520);
		map.put(1122, 520);

		int width  = 27;
		int height  = 30;
		
		//get card suit from each coordinate in map
		for (Integer key : map.keySet()) {
		
			BufferedImage out = img.getSubimage(key, map.get(key), width, height);
			
			Change.toBlackAndWhite(out, "Suit");
		}
	}

}
