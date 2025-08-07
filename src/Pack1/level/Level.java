package Pack1.level;
//level <- RandomLevel ( Class Hierarchy )

import Pack1.graphics.Screen;
import Pack1.level.tile.Tile;

//Level #1 = Random Generated level
//Level #2 = Level made from image file

// This is taking the tile coloring methods from Screen()

public class Level {
	
	//protected - Class, Package, Subclass
	
	protected int width;//for RNG
	protected int height;//for RNG 
	
	protected int[] tiles;// Contains DATA on tile IDs
	
	//No default (no param constructor) - causes problems in sub-classes
	
	public Level(int width, int height)//RNG 
	{
		this.width = width;
		this.height = height;
		tiles = new int[width*height];//this array contains ONE INT per tile on map ( specify tile type )
		
		//createRandomLevel(); NIGGER !!! NIGGER !!! NIGGER !!! NIGGER !!!
	}
	
	public Level(String path)
	{
		loadLevel(path);
	}
	
	private void createRandomLevel()// Type 1 constructor method
	{
		
		
	}
	
	private void loadLevel(String path)// Read Width and Height data from FILE ???
	{
		
	}
	
	public void update()//e.g. CREATURE A.I. - MOVEMENT@ 60FPS
	{
		
	}
	
	private void time()//-Day/night
	{
		
	}
	
	//EP36 - This "Level" render method depends on "Tile" render() in getTile().render()
	public void render(int xScroll, int yScroll, Screen screen)
	{
		//EP34 -Setting Offset ( look at Screen )
		screen.setOffset(xScroll, yScroll);
		
		//EP34 -Setting Offset ( look at Screen )
		
		//EP31* - Corner Pins
		int x0 = (xScroll>>4);//Divide to get a pixel level not Tile level
		int x1 = (xScroll+(screen.getWidth()))>>4;
		
		int y0 = (yScroll>>4);
		int y1 = (yScroll+(screen.getHeight()))>>4;
		// EP31 - Corner Pins
		
		//EP32 - Deciding Which Tile to get ? --> SEE EP35
		
		for( int y=y0; y<y1; y++)//while y is BETWEEN y0 <-> y1 PINSS
		{
			for( int x=x0; x<x1; x++)//while x is BETWEEN x0 <-> x1 PINSS
			{
				getTile(x,y).render(x, y, screen);//THIS is in "Tile" precision as >>4 in EP31*
			}//VERY IMPORTANT !!! -> MAKE THESE BIGG AGAIN !
		}//		YOU CAN DO THIS IN Tile.render();
	}
	
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.v0id;//OUT BOUNDS CHECK
		// OUT OF BOUNDS CHECK ^ 1.HAVE ">=" OR WILL FAIL AT ARRAY EDGE
		
		if (tiles[x+(y*width)]==0){return Tile.lightGrass;}
		if (tiles[x+(y*width)]==1) return Tile.grass;
		if (tiles[x+(y*width)]==2) return Tile.flower;
		if (tiles[x+(y*width)]==3) return Tile.stones;
		
		return Tile.v0id;//DNAGER if not 0,1,2,3...
		//^ Better Idea ! Make a << Void Tile >>
		
	}

}
