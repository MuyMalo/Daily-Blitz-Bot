package img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Determine {
	
	/**
	 * @param valueFolder
	 * @param value - RGB value to count (white = -1, black = -16777216, magenta = -65281
	 * @return
	 * @throws IOException
	 */
	public static int[] countPix(File valueFolder, int value) throws IOException {
		
		File[] directoryListing = valueFolder.listFiles();
		int countArr[] = new int[22*30];
		
		for (File child : directoryListing) {
			BufferedImage img = ImageIO.read(child);
			
			int width = img.getWidth();
			int height = img.getHeight();
			
			int imgArr[] = img.getRGB(0, 0, width, height, null, 0, width);
			
			for (int i = 0; i < imgArr.length; i++) {
				if (imgArr[i] == value) {
					countArr[i]++;
				}
		    }
		}
		return countArr;
	}
	
	/**
	 * @param img
	 * @param value - RGB value to count
	 * @return - number of pixels with RGB value in image
	 * @throws IOException
	 */
	public static int countPix(BufferedImage img, int value) throws IOException {
		
		int count = 0;
		
		int width = img.getWidth();
		int height = img.getHeight();
		
		int imgArr[] = img.getRGB(0, 0, width, height, null, 0, width);
		
		for (int i = 0; i < imgArr.length; i++) {
			if (imgArr[i] == value) {
				count++;
			}
	    }
		return count;
	}
	
	/**
	 * @param img
	 * @return
	 * @throws IOException
	 */
	public static char mostSimilarValue(BufferedImage img) throws IOException {
		File[] directoryListing = new File("resources\\Cards\\Value\\Each").listFiles();
		int numDiffPix = img.getHeight() * img.getWidth();
		char mostSimVal = 'A';
		
		for (File child : directoryListing) {
			BufferedImage getDiffImg = Compare.getDifference(img, ImageIO.read(child));
			int countDiffPix = countPix(getDiffImg, -65281);
			if(countDiffPix < numDiffPix) {
				numDiffPix = countDiffPix;
				mostSimVal = child.getName().charAt(0);
			}
		}
		return mostSimVal;
	}
	
	/**
	 * @param img
	 * @return
	 * @throws IOException
	 */
	public static char mostSimilarSuit(BufferedImage img) throws IOException {
		File[] directoryListing = new File("resources\\Cards\\Suit\\Each").listFiles();
		int numDiffPix = img.getHeight() * img.getWidth();
		char mostSimSuit = 'C';
		
		for (File child : directoryListing) {
			BufferedImage getDiffImg = Compare.getDifference(img, ImageIO.read(child));
			int countDiffPix = countPix(getDiffImg, -65281);
			if(countDiffPix < numDiffPix) {
				numDiffPix = countDiffPix;
				mostSimSuit = child.getName().charAt(0);
			}
		}
		return mostSimSuit;
	}
	
	/**
	 * @param valueFolder
	 * @throws IOException
	 */
	public static void valueHeatmap(File valueFolder) throws IOException {
		
		File[] directoryListing = valueFolder.listFiles();
		int numBlackPix[] = countPix(valueFolder, -16777216);
		
		BufferedImage img = new BufferedImage(22, 30,BufferedImage.TYPE_INT_RGB);
		int width = img.getWidth();
		int height = img.getHeight();
		
		int i = 0;
		
		//iterate through each row and column of img
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){				  
				//Get pixel value and extract ARGB value
				int p = img.getRGB(x,y);
				int a = (p>>24)&0xff;
				
				int numFiles = directoryListing.length;
				double scale = 255/numFiles;
				int heat = (int) (numBlackPix[i++]*scale);
				
				p = (a<<24) | (heat<<16) | (heat<<8) | heat;
				img.setRGB(x, y, p);
			}
		}		
		//Write image
		try{
			ImageIO.write(img, "png", new File(valueFolder.toString() + "\\heatmap.png"));
			}catch(IOException e){
			System.out.println(e);
			}
	}

}
