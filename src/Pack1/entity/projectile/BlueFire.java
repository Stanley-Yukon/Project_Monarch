package Pack1.entity.projectile;

import Pack1.graphics.Screen;
import Pack1.graphics.Sprite;

public class BlueFire extends Projectile 
{
	public BlueFire(int x, int y, double dir)
	{
		super(x,y,dir);
		range = 100;//Still being rendered ??? -> rendered in Screen.renderProjectile <- Level.render()
		speed = 5;
		damage = 10000;
		rateOfFire = 15;
		sprite = Sprite.bluefire;
		
		//We cannot animate DIAGONALLY
		//We animate on X, THEN Y. There is a Ratio between them
		// cos for X, sin for Y
		nx = (Math.cos(angle))*speed;
		//     ^ THis is small need * speed
		ny = (Math.sin(angle))*speed;
	}
	
	public void update()
	{
		move();
	}
	
	protected void move()
	{
		x = x + nx;
		y = y + ny;
		if( distance() > range )remove();
	}
	
	public void render(Screen screen)
	{
		screen.renderProjectile((int)x,(int)y,this);
		//screen.renderProjectile((int)x-12,(int)y,this);
		// 	 MODIFY TO IMPROVE CENTERING ^
	}
	
	protected double distance()
	{
		double distance = 0;
		//Using Pythagoras Theorm to calculate distance from spawn
		distance = Math.sqrt( (spawnX - x)*(spawnX - x ) + (spawnY - y)*(spawnY - y));
		return distance;
	}

}
