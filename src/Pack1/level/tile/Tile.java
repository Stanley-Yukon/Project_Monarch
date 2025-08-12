package Pack1.level.tile;

//Tile <- GrassTile ( Class Hierarchy )

import Pack1.graphics.Screen;
import Pack1.graphics.Sprite;

//What are tiles ? - they are X by X pixel sections. 
// Each tile has 1.Position & 
//				 2. Sprite

// RANDOM POINTS !!!
// Use TestGrid in res/folder to DIAGNOSE
// Tiles RENDER THEMSELVES. Level "calls" Tile to be Rendered

public class Tile {
	
	public int x,y;
	public Sprite sprite;
	
	//No Default Constructor. MUST HAVE SPRITE
	public Tile(Sprite sprite)
	{
		this.sprite = sprite;
	}
	
	//NATURAL WORLD
	public static Tile v0id = new VoidTile(Sprite.v0id);
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new GrassTile(Sprite.flower);
	public static Tile lightGrass = new GrassTile(Sprite.lightGrass);
	public static Tile stones = new RockTile(Sprite.stones);
	public static Tile darkhedge = new HedgeTile(Sprite.hedgedark);
	public static Tile lighthedge = new HedgeTile(Sprite.hedgelight);
	//NATURAL FLOOR
	public static Tile sand = new SandTile(Sprite.sand);	
	public static Tile blackrock = new RockTile(Sprite.blackrock);
	//CASTLE
	public static Tile castlewall = new WallTile(Sprite.castleblack);
	//MANMADE FLOOR
	public static Tile orangepaved = new PavedFloorTile(Sprite.orangebrickpaved);
	public static Tile victorianpaved = new PavedFloorTile(Sprite.victorianpaved);
	
	
	//SET Tile object to a new set of "Tile" Rules
	
	public void render(int x, int y, Screen screen)//We need position + Actual Screen which will do the render work
	{}
	
	public boolean solid()//returns whether tile is CROSSABLE
	{return false;}//average tile is NOT SOLID

}
