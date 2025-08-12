package Pack1.entity.mob;
import java.util.ArrayList;
import java.util.List;

import Pack1.Game;
import Pack1.entity.Entity;
import Pack1.graphics.Sprite;
import Pack1.entity.projectile.Projectile;
import Pack1.entity.projectile.BlueFire;

public abstract class Mob extends Entity{
	
	protected Sprite mobSprite;//cannot be used outside of mobs
	protected int dir = 0;//0- north, 1-east etc. (dir = direction)
	
	protected boolean moving = false;
	protected boolean walking = false;//For animation
	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void move(int xC, int yC)//Controls how pixels fuck with the Screen
	{//x and y variables change...need take two param ( xChange, yChange )
		// x : -1 0 l = left, nothing, right
		// y : -1 0 1 =  up , nothing , down 
		// Only move if no collision;
		if(xC != 0 && yC != 0) {
			move(xC,0);//recursive ? -> No as (x,0) will prevent recursive
			move(0,yC);
		}
		
		if (xC > 0) { dir = 1;}///east
		if (xC < 0) { dir = 3;}//west
		
		if (yC > 0) { dir = 2;}//south
		if (yC < 0) { dir = 0;}//north
		
		if(!getCollision(xC,yC))//Can only move if NO Collisions
		{//^ COLLISION in One Dimension but NOT the other == sliding fail...
			x = x + xC;
			y = y + yC;
		}
		//For ALTERNATE Sliding FIX, FIX Check to be One at a time
		//if(!getCollision(xC,0)) {x = x+xC;}
		//if(!getCollision(0,yC)) {y = y+yC;}
	}
	
	protected void shoot(int x, int y, double dir)
	{
		//dir = dir * (180/Math.PI);
		//System.out.println("Angle is: "+dir);
		Projectile p = new BlueFire(x,y,dir);
		projectiles.add(p);
		level.add(p);
		//Need Array list to hold projectiles -> Done in Level
	}
	
	public void update()
	{}
	
	
	/* OLD VERISON ( FAIL AT SLIDING AND SINGLE-BLOCK-SIDE-COLLIDE )
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
		
	}*/
	private boolean getCollision(int xC,int yC)//Collision DETECTION != Collision handling
	{
		//Algorithm for collision DETECTION -> Look ahead !!!
		//we need future co-ordinates xC, yC to check in front
		boolean solid = false;
		//CHEKCING TILES BUT TILE "CENTER" IS TOP LEFT -> x3
		for(int c =0; c<4; c++)
		{
			int xTile = ((x+xC)+c%2 * 12 -7)>>4; // corner code goes here
			int yTile = ((y+yC)+c/2 * 12 +3)>>4;
			//					  ^ ADD size of collision area
			//  ^ VERY IMPORTANT ^ GUESS & CHECK ^
			if(level.getTile(xTile, yTile).solid() == true)
			{
			//     ^ Returns Tile OBJ with Characteristics
			//   ^ get tile looks at TILES not pixels !!!
			solid = true;
			// ^ level is FROM ENTITY (Class) and MUST BE INITIALIZED
			}
		}
		return solid;
	}
	
	
	//lighting ??? angles ???
	public void render()
	{
		
	}	

}
