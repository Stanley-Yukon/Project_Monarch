package Pack1.entity.projectile;

import Pack1.graphics.Screen;
import Pack1.graphics.Sprite;

public class BlueFire extends Projectile 
{
	public BlueFire(int x, int y, double dir)
	{
		super(x,y,dir);
		range = 300;
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
		x = x + (int)nx;
		y = y + (int)ny;
	}
	
	public void render(Screen screen)
	{
		screen.renderProjectile(x,y,this);
	}

}
