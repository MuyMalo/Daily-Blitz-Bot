package img;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Testing {

	public static void main(String[] args) throws Exception {		
		
		//testMostSimilarValue();
		//testMostSimilarSuit();
		testExtractVal(47);
		
		
		
	}
	
	public static void testExtractVal(int val) throws IOException {
		BufferedImage img = ImageIO.read(new File("resources\\Screenshots\\Screenshot (" + val + ").png"));
		Extract.values(img);
	}
	
	public static void testExtractSuit(String path) throws IOException {
		BufferedImage img = ImageIO.read(new File(path));
		Extract.suits(img);
	}
	
	public static void testGetDiff() throws IOException {
		String val = "7";
		
		BufferedImage img1 = ImageIO.read(new File("resources\\Cards\\Value\\A\\gs14.png"));
		
		File diff = new File("resources\\Cards\\Value\\getDiff" + val + ".png");
						
		for(int i = 0; i < 250; i++) {
			
			File file = new File("resources\\Cards\\Value\\A\\gs" + i + ".png");
			
			if (diff.exists() && file.exists()) {
				BufferedImage imgDiff = ImageIO.read(diff);
				BufferedImage img2 = ImageIO.read(file);
				Compare.getDifference(imgDiff, img2, val);
			} else if (file.exists()) {
				BufferedImage img2 = ImageIO.read(file);
				Compare.getDifference(img1, img2, val);
			}
			
		}
	}
	
	public static void testMostSimilarValue() throws IOException {
		char value[] = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
		
		for(char i : value) {
			File[] directoryListing = new File("resources\\Cards\\Value\\" + i).listFiles();
			int numFiles = directoryListing.length;
			int numCorrect = 0;
			
			for(File file : directoryListing) {
				BufferedImage img = ImageIO.read(file);
				char mostSimValue = Determine.mostSimilarValue(img);
				
				System.out.print(mostSimValue + " ");
				if(i == mostSimValue) {
					numCorrect++;
				}
				
			}
			System.out.println(" ");
			System.out.println(numCorrect + "/" + numFiles + " correct values");
		}
	}
	
	public static void testMostSimilarSuit() throws IOException {
		char value[] = {'C', 'D', 'H', 'S'};
		
		for(char i : value) {
			File[] directoryListing = new File("resources\\Cards\\Suit\\" + i).listFiles();
			int numFiles = directoryListing.length;
			int numCorrect = 0;
			
			for(File file : directoryListing) {
				BufferedImage img = ImageIO.read(file);
				char mostSimValue = Determine.mostSimilarSuit(img);
				
				System.out.print(mostSimValue + " ");
				if(i == mostSimValue) {
					numCorrect++;
				}
				
			}
			System.out.println(" ");
			System.out.println(numCorrect + "/" + numFiles + " correct values");
		}
	}
	
	public static BufferedImage takeScreenShot() throws HeadlessException, AWTException {
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		return image;
	}
	
}
