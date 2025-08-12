package Pack1.entity.projectile;

import Pack1.entity.Entity;
import Pack1.graphics.Sprite;

public class Projectile extends Entity{
	
	protected final int spawnX;
	protected final int spawnY;
	
	protected double angle;
	protected Sprite sprite;
	
	protected double nx,ny;// Change each tick n =
	protected double speed, rateOfFire, damage, range;
	
	public Projectile(int x, int y, double dir)
	{
		this.x = x;
		this.y = y;
		
		spawnX = x;
		spawnY = y;
		angle = dir;
	}
	public Sprite getSprite()
	{
		return sprite;
	}
	
	protected void move()
	{
		
	}

}
