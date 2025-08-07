package Pack1.graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//THE UNIVERSAL SPRITE CLASS  - CREATE A SPRITE IN THE SPRITE CLASS
//								SPRITES ARE STATIC ONCE DEFINED - line31
//								^ This is the actual only important thing

public class Sprite {
	
	public final int SIZE;//SIZE OF SPRITE - AGREED TO BE 16 ? SOME MONSTERS MAY BE 32
	private int x,y;
	public int[] pixels;
	
	private SpriteSheet sheet;//We HAVE MULTIPLE SpriteSheets ( Monsters, Heros, plants etc. )
	
	public Sprite(int size, int x, int y, SpriteSheet type)
	{
		SIZE = size;
		this.x=x*SIZE;//5th Sprite across, therefore x = 5, actual location is 5*16 = 80
		this.y=y*SIZE;//2nd Sprite down, therefore y = 2, actual location is 2*16 = 32
		pixels = new int[SIZE*SIZE];
		sheet = type;
		load();
	}
	
	public Sprite(int size, int color)
	{
		SIZE =size;
		pixels = new int[SIZE*SIZE];
		
		//setColor() Method
		for(int i=0; i<SIZE*SIZE; i++)
		{
			pixels[i] = color;
		}		
	}
	
	public static Sprite v0id = new Sprite(16,0);//New Static Instance with unique variables
	public static Sprite grass = new Sprite(16,1,0,SpriteSheet.tiles1);
	public static Sprite flower = new Sprite(16,2,0,SpriteSheet.tiles1);
	public static Sprite stones = new Sprite(16,3,0,SpriteSheet.tiles1);
	public static Sprite lightGrass = new Sprite(16,4,0,SpriteSheet.tiles1);
	
	private void load()
	{
		for(int y = 0; y< SIZE; y++)
		{
			for(int x = 0; x< SIZE; x++)
			{
				pixels[x+y*SIZE]  = sheet.pixels[(x+this.x) + (y+this.y)*sheet.SIZE];//Scanning from SpriteSheet
			}
			
		}
	}
	
	
	
	

}
