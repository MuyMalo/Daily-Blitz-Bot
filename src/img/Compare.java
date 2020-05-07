package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Compare {
	
	/**
	 * @param img1 - image getting highlighted
	 * @param img2 - image being compared to img1
	 * @param val - what to save as
	 * @throws IOException
	 */
	public static void getDifference(BufferedImage img1, BufferedImage img2, String val) throws IOException {
	    // convert images to 1D pixel arrays...
	    final int w = img1.getWidth(),
	              h = img1.getHeight(),
	              highlight = Color.MAGENTA.getRGB();
	    final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
	    final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
	    
	    // compare img1 to img2, pixel by pixel. If different, highlight img1's pixel...
	    for (int i = 0; i < p1.length; i++) {
	        if (p1[i] != p2[i]) {
	            p1[i] = highlight;
	        }
	    }
	    // save img1's pixels to a new BufferedImage, and return it...
	    // (May require TYPE_INT_ARGB)
	    final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    out.setRGB(0, 0, w, h, p1, 0, w);
	    ImageIO.write(out, "png", new File("resources\\Cards\\Value\\getDiff" + val + ".png"));	
	}
	
	public static BufferedImage getDifference(BufferedImage img1, BufferedImage img2) throws IOException {
	    // convert images to 1D pixel arrays...
	    final int w = img1.getWidth(),
	              h = img1.getHeight(),
	              highlight = Color.MAGENTA.getRGB();
	    final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
	    final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
	    
	    // compare img1 to img2, pixel by pixel. If different, highlight img1's pixel...
	    for (int i = 0; i < p1.length; i++) {
	        if (p1[i] != p2[i]) {
	            p1[i] = highlight;
	        }
	    }
	    // save img1's pixels to a new BufferedImage, and return it...
	    final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    out.setRGB(0, 0, w, h, p1, 0, w);
	    return out;
	}
	
	/**
	 * @param valueFolder - folder with images in it
	 * @param num - index of column of image to add to columns array
	 * @return arrays of each column at index of num in each image in valueFolder
	 * @throws IOException
	 */
	public static int[][] getColumns(File valueFolder, int num) throws IOException {
		//make array of files in valueFolder
		File[] directoryListing = valueFolder.listFiles();
		int columns[][] = new int[directoryListing.length][1];
		
		if (directoryListing != null) {
			for (int i = 0; i < directoryListing.length; i++) {
				int pixArr2d[][] = Change.to2dArray(directoryListing[i]);
				columns[i] = pixArr2d[num];
			}
		}
		return columns;
	}
	
	public static void printColumns(File valueFolder, int num) throws IOException {
		//make array of files in valueFolder
		File[] directoryListing = valueFolder.listFiles();
		int columns[][] = new int[directoryListing.length][1];
		
		if (directoryListing != null) {
			for (int i = 0; i < directoryListing.length; i++) {
				int pixArr2d[][] = Change.to2dArray(directoryListing[i]);
				columns[i] = pixArr2d[num];
				System.out.println(Arrays.toString(columns[i]));
			}
		}
	}
	
	/**
	 * @param valueFolder
	 * @return linked list of all columns that return true when passed into isColUnique
	 * @throws IOException
	 */
	public static LinkedList<Integer> uniqueForAllImg(File valueFolder) throws IOException {
		
		LinkedList<Integer> uniqueColumns = new LinkedList<Integer>(); 
		
		//compare each column to the same column in every other image for every image in valueFolder
		for (int i = 0; i < 22; i++) {
			int columns[][] = getColumns(valueFolder, i);
			//if unique, add to uniqueColumns list
			if (isColUnique(columns)) {
				uniqueColumns.add(i);
			}
		}
		System.out.println(uniqueColumns.toString());
		return uniqueColumns;
	}
	
	/**
	 * @param columns
	 * @return true if all columns are unique, false otherwise
	 * 
	 * for every array in columns array, test to see if any of the other arrays are equal
	 * 
	 */
	public static boolean isColUnique(int[][] columns) {
		
		for (int i = 0; i < columns.length - 1; i++) {
			for (int j = i + 1; j < columns.length; j++) {
				if (Arrays.equals(columns[i], columns[j])) {
					return false;
				} 
			}
		}
		return true;
	}
}
