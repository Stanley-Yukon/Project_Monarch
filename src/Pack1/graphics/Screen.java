package Pack1.graphics;

import java.util.Random;

import Pack1.level.tile.Tile;

//The Screen Class is meant to be used to manipulate FRAME ?
//Used in conjunction with int[] pixels in Game

public class Screen 
{
	private int width, height;
	public int getWidth()
	{return width;}
	public int getHeight()
	{return height;}
	
	private final int MAP_SIZE = 64;
	private final int MAP_MASK = MAP_SIZE-1;

	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
	public int xOffset, yOffset;
	
	private Random rr = new Random();
	
	public Screen(int height, int width)
	{
		this.height = height;
		this.width = width;
		pixels = new int[width*height];
		
		for(int i=0 ; i<MAP_SIZE*MAP_SIZE; i++)
		{
			tiles[i] = rr.nextInt(0xffffff);
		}
		tiles[0] = 0;
		xOffset=0;
		yOffset=0;
	}
	
	/*
	public void render(int xOff, int yOff)
	{		
		for(int y=0; y<height; y++)
		{
			int yy = y + yOff;
			//if(yy>= height || yy < 0)break; //Prevent OUT-OF-BOUNDS
			for(int x=0; x<width; x++)
			{
				int xx = x + xOff;
				//if(xx >= width || xx < 0)break; // break kills the loop ?
				
				//Tiles are 32x32 size///////////////////////
				int tileIndex = ((xx>>4)&MAP_MASK) + ((yy>>4)&MAP_MASK)*MAP_SIZE;//- X/32 first 32 pixels in x-direction must be same colour
				//- pixels 0 to 32 are in one Tile, 33 to 64 in T2...
				// 1. REPLACE 32 With 16, 2. MUST USE >> NOT /16 OR ELSE VERTICAL LINES....
				/////////////////////////////////////////////
				//pixels[x + (y*width)]=tiles[tileIndex]; - OLD
				pixels[x+(y*width)] = Sprite.blackworm.pixels[(x&15)+(y&15)*Sprite.blackworm.SIZE];
				//access x = 20, y = 30, 
				//Multiply y*width to get "row" you want
			}
		}
	}
	 	^^^	OLD RENDER METHOD*/
	
	/*
	public void render(int xOff, int yOff)
	{		
		for(int y=0; y<height; y++)
		{
			int yp = y + yOff;
			if(yp<0 || yp>=height)continue;//Black Buffer
			for(int x=0; x<width; x++)
			{
				int xp = x + xOff;
				if(xp<0 || xp>=width)continue;//Black Screen
				pixels[(xp)+( (yp)*width )] = Sprite.grass.pixels[(x&15)+(y&15)*Sprite.grass.SIZE];
			}
		}
	}
	 	^^^ 2nd OLD RENDER METHOD */
	
	public void clear()
	{
		for(int i=0; i< pixels.length; i++ ) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile)//We can run into problems if we use Sprite Instead of Tile i.e when Sprites become animated
	{//Won't have to update in Tile's render method if we change its sprite
		xp = xp-xOffset;// MINUS as when player->right . map ->left
		yp = yp-yOffset;//same here ^
			
		for( int y=0; y<tile.sprite.SIZE; y++)//rolls from 0-15
		{
			int y_ABS = y + yp;//yp changes based on Offset
			for( int x=0; x<tile.sprite.SIZE; x++)
			{
				int x_ABS = x + xp;//yp changes based on Offset
				if(x_ABS < -(tile.sprite.SIZE)|| x_ABS >= width || y_ABS < 0 || y_ABS >= height)break;
				if(x_ABS < 0) {x_ABS= 0;}//LOL !
				//1. ^ BLACKOUT on 4 CONDITIONS ( All locations in 1 file )
				//2. used to be x_ABS < 0 but NOW, We fix left side ((shuttering))
				//2. We shift by -(a WHOLE tile), not -just -(1)
				//2. Literally Say "if x_ABS < 0, turn to zero, LMAO !!!
				
				
				pixels[x_ABS+ (y_ABS*width) ] = tile.sprite.pixels[x + (y*tile.sprite.SIZE)];
				// ^ Which screen pixels ? get ? Which Sprite pixels ???
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset)//SETTER FUNCTION
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
