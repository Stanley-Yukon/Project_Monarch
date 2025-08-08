package Pack1.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Pack1.graphics.Screen;
import Pack1.level.tile.Tile;

public class SpawnLevel extends Level{
	
	private String name = "Nexus";
	private static final int TILESIZE = 16;
	
	private int[] lvlpixels;
	
	public SpawnLevel(String path) 
	{
		super(path);
		
		loadLevel(path);
		generateLevel();
	
	}
	
	//clashes with GetTile & render ?
	protected void loadLevel(String path)
	{
		try {
			BufferedImage image= ImageIO.read(SpawnLevel.class.getResource(path));
			width  = image.getWidth();
			height = image.getHeight();
			//1.
			tilesTRUE = new Tile[width*height];//TOTAL NUMBER of TILES
			
			//2.
			lvlpixels = new int[width*height];
			image.getRGB(0, 0,width,height,lvlpixels,0,width);
		}
		catch(IOException e) 
			{
			e.printStackTrace();
			System.out.println("Exception ! Level File not here Bruh");
		}
			
	}//need a method to convert pixels into tiles ?
	
	//Grass = 0xFF00
	//FLOWER = 0xFFFF00
	//ROCK = 0x7f7f00
	protected void generateLevel()//convert pixels INT array into tiles
	{//COULD return Tile or Tile[] instead ???
		// OUT OF BOUNDS CHECK ^ 1.HAVE ">=" OR WILL FAIL AT ARRAY EDGE
		for(int i=0; i<lvlpixels.length; i++)
		{
			if(lvlpixels[i]==0xff00ff00)//if a particular pixel is equal to a colour ^, file tile index of tile array with grass tile;
			{tilesTRUE[i]= Tile.grass;}//not new GrassTile()	
			
			else if(lvlpixels[i]==0xffffff00)//if a particular pixel is equal to a colour ^, file tile index of tile array with grass tile;
			{tilesTRUE[i]= Tile.flower;}
			
			else if(lvlpixels[i]==0xff7f7f00)//if a particular pixel is equal to a colour ^, file tile index of tile array with grass tile;
			{tilesTRUE[i]= Tile.stones;}
						
			else tilesTRUE[i] = Tile.v0id;
			
		}
	}
	
	public void render(int xScroll, int yScroll, Screen screen)
	{
		//EP34 -Setting Offset ( look at Screen )
		screen.setOffset(xScroll, yScroll);
		
		//EP34 -Setting Offset ( look at Screen )
		
		//EP31* - Corner Pins //EP39 CAN'T MAKE CORNER PINS NEGATIVE ???
		int x0 = (xScroll>>4);//Divide to get a pixel level not Tile level
		int x1 = (xScroll+(screen.getWidth()+16))>>4;
		//								   	 ^ TILE SIZE !!!
		int y0 = (yScroll>>4);
		int y1 = (yScroll+(screen.getHeight()+16))>>4;
		// EP31 - Corner Pins
		
		//EP32 - Deciding Which Tile to get ? --> SEE EP35
		
		for( int y=y0; y<y1; y++)//while y is BETWEEN y0 <-> y1 PINSS
		{
			for( int x=x0; x<x1; x++)//while x is BETWEEN x0 <-> x1 PINSS
			{
				//getTile(x,y).render(x, y, screen);//THIS is in "Tile" precision as >>4 in EP31*
				
				if (x < 0 || y < 0 || x >= width || y >= height) {
				    Tile.v0id.render(x, y, screen);
				    continue;
				}
				
				Tile t = tilesTRUE[x + (y * width)];
				if (t != null) t.render(x, y, screen);
				else Tile.v0id.render(x, y, screen);
				
			}//VERY IMPORTANT !!! -> MAKE THESE BIGG AGAIN !
		}//		YOU CAN DO THIS IN Tile.render();
	}
	
}//END