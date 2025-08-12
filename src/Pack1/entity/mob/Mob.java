package Pack1.entity.mob;
import Pack1.entity.Entity;
import Pack1.graphics.Sprite;

public abstract class Mob extends Entity{
	
	protected Sprite mobSprite;//cannot be used outside of mobs
	protected int dir = 0;//0- north, 1-east etc. (dir = direction)
	
	protected boolean walking = false;//For animation
	
	public void move(int xC, int yC)//Controls how pixels fuck with the Screen
	{//x and y variables change...need take two param ( xChange, yChange )
		// x : -1 0 l = left, nothing, right
		// y : -1 0 1 =  up , nothing , down 
		// Only move if no collision;
		if (xC > 0) { dir = 1;}///east
		if (xC < 0) { dir = 3;}//west
		
		if (yC > 0) { dir = 2;}//south
		if (yC < 0) { dir = 0;}//north
		
		if(!getCollision(xC,yC))//Can only move if NO Collisions
		{
			x = x + xC;
			y = y + yC;
		}
		
	}
	
	public void update()
	{
		
	}
	
	private boolean getCollision(int xC,int yC)//Collision DETECTION != Collision handling
	{
		//Algorithm for collision DETECTION -> Look ahead !!!
		//we need future co-ordinates xC, yC to check in front
		boolean solid = false;
		if(level.getTile((x+xC)>>4, (y+yC)>>4).solid() == true){
			//     ^ Returns Tile OBJ with Characteristics
			//   ^ get tile looks at TILES not pixels !!!
			solid = true;
			return true;
			//level is FROM ENTITY (Class) and MUST BE INITIALIZED
		}
		return false;
		
	}
	
	//lighting ??? angles ???
	public void render()
	{
		
	}
	
	

}
