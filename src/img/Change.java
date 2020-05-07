package img;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Change {
	
	static int count = 0;
	
	public static void resetCount() {
		count = 0;
	}
	
	/**
	 * @param numImg
	 * @return 2D array with RGB values of each pixel in numImg
	 */
	public static int[][] to2dArray(BufferedImage numImg) {
		
		int w = numImg.getWidth(),
			h = numImg.getHeight();
		int pixArr2d[][] = new int[w][h];
		
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				//get RGB value of each pixel and add to 2d array
		    	pixArr2d[i][j] = numImg.getRGB(i, j);
			}
			//print each column of image as array
			//System.out.println(Arrays.toString(pixArr2d[i]));
		}		
		return pixArr2d;
	}
	
	/**
	 * @param file
	 * @return 2D array with RGB values of each pixel in file
	 * @throws IOException
	 */
	public static int[][] to2dArray(File file) throws IOException {
		
		BufferedImage numImg = ImageIO.read(file);
		
		int w = numImg.getWidth(),
			h = numImg.getHeight();
		int pixArr2d[][] = new int[w][h];
		
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				//get RGB value of each pixel and add to 2d array
		    	pixArr2d[i][j] = numImg.getRGB(i, j);
		    	//if RGB value is white, change array value to 1, otherwise 0
		    	if(pixArr2d[i][j] == -1) {
					pixArr2d[i][j] = 1;
				} else {
					pixArr2d[i][j] = 0;
				}
			}
			//print each column of image as array
			//System.out.println(Arrays.toString(pixArr2d[i]));
		}		
		return pixArr2d;
	}
	
	/**
	 * @param img
	 * @param suitOrValue
	 */
	public static void toBlackAndWhite(BufferedImage img, String suitOrValue) {
		
		int width = img.getWidth();
		int height = img.getHeight();
		
		//iterate through each row and column of img
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){				  
				//Get pixel value and extract ARGB value
				int p = img.getRGB(x,y);
				int a = (p>>24)&0xff;
				int r = (p>>16)&0xff;
				int g = (p>>8)&0xff;
				int b = p&0xff;
				//Find average value
				int avg = (r+g+b)/3;
				  
				int white = 255;
				int black = 0;
				//if avg below 80, set to black, else set to white
				if (avg < 190) {
				//Set new pixel value
					p = (a<<24) | (black<<16) | (black<<8) | black;
					img.setRGB(x, y, p);
				} else {
					//Set new pixel value
					p = (a<<24) | (white<<16) | (white<<8) | white;
					img.setRGB(x, y, p);
				}
			}
		}		
		//Write image
		try{
			ImageIO.write(img, "png", new File("resources\\Cards\\" + suitOrValue + "\\BW\\" +  + count++ + ".png"));
			}catch(IOException e){
			System.out.println(e);
			}
	}
	
	/**
	 * @param image
	 * @param savePath
	 * @throws IOException
	 */
	public static void rotateRight(BufferedImage image, String savePath) throws IOException {		
		//converting image to Graphics object
		Graphics g = image.getGraphics();

		// Rotation information
		double rotationRequired = Math.toRadians (12);
		double locationX = 7;
		double locationY = 15;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(op.filter(image, null), 3, -1, null);
		g2d.dispose();
		
		//saving Graphics to savePath
		RenderedImage rendImage = image;
	    ImageIO.write(rendImage, "png", new File(savePath));

	}
	
	/**
	 * @param image
	 * @param savePath
	 * @throws IOException
	 */
	public static void rotateLeft(BufferedImage image, String savePath) throws IOException {		
		//converting image to Graphics object
		Graphics g = image.getGraphics();

		// Rotation information
		double rotationRequired = Math.toRadians (336);
		double locationX = 7;
		double locationY = 15;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(op.filter(image, null), 0, 1, null);
		g2d.dispose();
		
		//saving Graphics to savePath
		RenderedImage rendImage = image;
	    ImageIO.write(rendImage, "png", new File(savePath));
	}
}
