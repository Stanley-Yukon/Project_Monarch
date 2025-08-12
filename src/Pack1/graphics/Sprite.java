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
	//NATURE
	public static Sprite v0id = new Sprite(16,5,1,SpriteSheet.fancy1);//New Static Instance with unique variables
	public static Sprite grass = new Sprite(16,1,0,SpriteSheet.tiles1);
	public static Sprite flower = new Sprite(16,2,0,SpriteSheet.tiles1);
	public static Sprite stones = new Sprite(16,3,0,SpriteSheet.tiles1);
	public static Sprite lightGrass = new Sprite(16,4,0,SpriteSheet.tiles1);
	//ROCK FLOOR
	public static Sprite blackrock = new Sprite(16,1,3,SpriteSheet.fancy1);
	public static Sprite greyrock = new Sprite(16,1,0,SpriteSheet.fancy1);
	//HEDGE
	public static Sprite hedgedark = new Sprite(16,3,0,SpriteSheet.fancy1);
	public static Sprite hedgelight = new Sprite(16,0,1,SpriteSheet.fancy1);
	//SAND
	public static Sprite sand = new Sprite(16,0,2,SpriteSheet.fancy1);
	//CASTLE WALLS;
	public static Sprite castleblack = new Sprite(16,2,2,SpriteSheet.fancy1);
	
	//MANMADE FLOORS
	public static Sprite orangebrickpaved = new Sprite(16,2,1,SpriteSheet.fancy1);
	public static Sprite victorianpaved = new Sprite(16,1,2,SpriteSheet.fancy1);
	public static Sprite woodfloor = new Sprite(16,2,4,SpriteSheet.fancy1);
	
	public static Sprite bluefire = new Sprite(16,0,3,SpriteSheet.tiles1);
	/*small sprites ( not used )
	public static Sprite player0 = new Sprite(16,0,10,SpriteSheet.tiles1);
	public static Sprite player1 = new Sprite(16,1,10,SpriteSheet.tiles1);
	public static Sprite player2 = new Sprite(16,0,11,SpriteSheet.tiles1);
	public static Sprite player3 = new Sprite(16,1,11,SpriteSheet.tiles1);
	small sprites ( not used )*/
	
	//Bigger Sprite -> Math is DIFFERENT !!!  10 -> 5
	public static Sprite player = new Sprite(32,2,5,SpriteSheet.tiles1);
	public static Sprite playerR = new Sprite(32,1,5,SpriteSheet.tiles1);
	public static Sprite playerB = new Sprite(32,0,5,SpriteSheet.tiles1);
	public static Sprite playerL = new Sprite(32,3,5,SpriteSheet.tiles1);	
	
	// PLAYER WALKING SPRITES....Implemented in Player with (IMPT) COUNTER variable
	public static Sprite player_f1 = new Sprite(32,2,6,SpriteSheet.tiles1);
	public static Sprite player_f2 = new Sprite(32,2,7,SpriteSheet.tiles1);
	
	public static Sprite player_r1 = new Sprite(32,1,6,SpriteSheet.tiles1);
	public static Sprite player_r2 = new Sprite(32,1,7,SpriteSheet.tiles1);
	
	public static Sprite player_b1 = new Sprite(32,0,6,SpriteSheet.tiles1);
	public static Sprite player_b2 = new Sprite(32,0,7,SpriteSheet.tiles1);
	
	public static Sprite player_l1 = new Sprite(32,3,6,SpriteSheet.tiles1);
	public static Sprite player_l2 = new Sprite(32,3,7,SpriteSheet.tiles1);
	
	
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
