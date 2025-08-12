package Pack1.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

//THIS CLASS JUST LOADS ALL SPRITES IN A SHEET - NEED INDIVIDUAL SPRITES

public class SpriteSheet {
	
	private String path;
	public final int SIZE;//SIZE of the spritesheet DOES NOT CHANGE
	public int[] pixels;
	
	public static SpriteSheet tiles1 = new SpriteSheet("/textures/worms.png",256);
	public static SpriteSheet fancy1 = new SpriteSheet("/textures/Fletching.png",96);
	
	public SpriteSheet(String path, int size)
	{
		this.path = path;
		this.SIZE = size;
		this.pixels = new int[SIZE*SIZE];
		load();//CALL UPON Construction to create image;
	}
	
	private void load()//Load Image// !- We don't want to deal with BufferedImages AS images, we want them to be pixels[]
	{
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			
			image.getRGB(0,0,w,h,pixels,0,w);//image.getRGB(0,0,w,h,rgbArray,offset,scansize);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
