package Pack1.level;
//level <- RandomLevel ( Class Hierarchy )

import java.util.ArrayList;
import java.util.List;

import Pack1.graphics.Screen;
import Pack1.level.tile.Tile;
import Pack1.entity.Entity;

//Level #1 = Random Generated level
//Level #2 = Level made from image file

// This is taking the tile coloring methods from Screen()

public class Level {
	//protected - Class, Package, Subclass
	protected int width;//for RNG
	protected int height;//for RNG 
	
	protected int[] tiles;// Contains COLOR DATA on CURRENT tile IDs
	protected int[] tilesInt;// Contains PIXEL hex codes
	protected int tile_size;
	
	//Array of all Entities ( Dynamic as it should be as big as number of entities )
	protected List<Entity> entities = new ArrayList<Entity>();
	
	// STATIC LEVELS !!!  IN THE FUTURE
	//private static Level spawn = new SpawnLevel("/textures/Spawn.png");
	//
	//No default (no param constructor) - causes problems in sub-classes
	
	public Level(int width, int height)//RNG 
	{
		this.width = width;
		this.height = height;
		tilesInt = new int[width*height];//this array contains ONE INT per tile on map ( specify tile type )
		
		generateLevel();
	}
	
	public Level(String path)
	{
		loadLevel(path);
		generateLevel();
	}
	
	private void generateLevel()
	{
		for(int y=0; y<64; y++)
		{
			for(int x=0; x<64; x++)
			{
				getTile(x,y);
			}
		}
		tile_size =16;		
	}
	
	
	private void createRandomLevel()// Type 1 constructor method
	{}
	protected void loadLevel(String path)// Read Width and Height data from FILE ???
	{}
	
	public void update()//e.g. CREATURE A.I. - MOVEMENT@ 60FPS
	{
		for (int i = 0; i<entities.size(); i++)
		{
			entities.get(i).update(); //Call update on ALL Entities
		}
	}
	
	private void time()//-Day/night
	{}
	
	//EP36 - This "Level" render method depends on "Tile" render() in getTile().render()
	public void render(int xScroll, int yScroll, Screen screen)
	{
		//EP34 -Setting Offset ( look at Screen )
		screen.setOffset(xScroll, yScroll);
		
		int x0 = (xScroll>>4);//Divide to get a pixel level not Tile level
		int x1 = (xScroll+(screen.getWidth()+16))>>4;
		//								   	 ^ TILE SIZE !!!
		int y0 = (yScroll>>4);
		int y1 = (yScroll+(screen.getHeight()+16))>>4;
		
		for( int y=y0; y<y1; y++)//while y is BETWEEN y0 <-> y1 PINSS
		{
			for( int x=x0; x<x1; x++)//while x is BETWEEN x0 <-> x1 PINSS
			{
				getTile(x,y).render(x, y, screen);
			}//GetTile now ACCESSES - CURRENT tileset
		}
		
		for (int i = 0; i<entities.size(); i++)
		{
			entities.get(i).render(screen); //Call update on ALL Entities
		}
	}
	
	public void add(Entity e)
	{entities.add(e);}//add end
	
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.v0id;//OUT BOUNDS CHECK
		// OUT OF BOUNDS CHECK ^ 1.HAVE ">=" OR WILL FAIL AT ARRAY EDGE
		
		if (tiles[x+(y*width)]==0xff00ff00) return Tile.grass;
		if (tiles[x+(y*width)]==0xffffff00) return Tile.flower;
		
		if (tiles[x+(y*width)]==0xff7f7f7f) return Tile.castlewall;
		if (tiles[x+(y*width)]==0xffffA500) return Tile.orangepaved;
		
		if (tiles[x+(y*width)]==0xff505050) return Tile.blackrock;
		if (tiles[x+(y*width)]==0xffffe4b5) return Tile.sand;
		if (tiles[x+(y*width)]==0xffdeb887) return Tile.stones;
		
		if (tiles[x+(y*width)]==0xff006400) return Tile.darkhedge;
		if (tiles[x+(y*width)]==0xff228b22) return Tile.lighthedge;
		
		return Tile.v0id;//DNAGER if not 0,1,2,3...
		//^ Better Idea ! Make a << Void Tile >>
	}

}
