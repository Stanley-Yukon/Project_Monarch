package Pack1.graphics;

import java.util.Random;

//The Screen Class is meant to be used to manipulate FRAME ?
//Used in conjunction with int[] pixels in Game

public class Screen 
{
	private int width, height;

	public int[] pixels;
	public int[] tiles = new int[8*8];
	
	private Random rr = new Random();
	
	public Screen(int height, int width)
	{
		this.height = height;
		this.width = width;
		pixels = new int[width*height];
		
		for(int i=0 ; i<64; i++)
		{
			tiles[i] = rr.nextInt(0xffffff);
		}
	}
	
	public void render(int xOff, int yOff)
	{		
		for(int y=0; y<height; y++)
		{
			int yy = y+yOff;
			//if(yy>= height || yy < 0)break; //Prevent OUT-OF-BOUNDS
			for(int x=0; x<width; x++)
			{
				int xx = x+xOff;
				//if(xx >= width || xx < 0)break; // break kills the loop ?
				
				//Tiles are 16x16 size///////////////////////
				int tileIndex = ((xx>>4) % 7) + ((yy/16) % 7)*8;//- X/32 first 32 pixels in x-direction must be same colour
				//- pixels 0 to 32 are in one Tile, 33 to 64 in T2...
				// (OPT_TECHS) >>4 is /16
				/////////////////////////////////////////////
				
				pixels[x+(y*width)] = tiles[tileIndex];
				//access x = 20, y = 30, 
				//Multiply y*width to get "row" you want
			}
		}
	}
	
	public void clear()
	{
		for(int i=0; i< pixels.length; i++ ) {
			pixels[i] = 0;
		}
	}

}
