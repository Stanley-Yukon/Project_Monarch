package Pack1.entity.mob;

import Pack1.graphics.Screen;
import Pack1.graphics.Sprite;
import Pack1.input.Keyboard;

public class Player extends Mob {
	
	//NO X & Y - THEY MUST BE INHERITED FROM ENTITY !!!
	
	private Keyboard input;
	
	private String name;
	private int health;
	private int mana;
	
	public Player(Keyboard input)
	{
		this.input = input;
		
	}
	
	public Player(int x, int y, Keyboard input)//SPECIFIC SPAWN POINT FOR LEVELS
	{
		this.x = x;// this -> Takes you to Entity
		this.y = y;
		
		this.input = input;
	}
	
	//public void move()
	
	public void update()//@Override Mob.update ( Don't need call Player.update to update Player )
	{
		int xC=0;
		int yC=0;
		
		if(input.up)yC--;
		if(input.down)yC++;
		if(input.left)xC--;
		if(input.right)xC++;
		
		if(xC != 0 || yC != 0)
		{
			move(xC,yC);//Move() is from Mob Class
		}
		
	}
	
	//All render methods require the SCREEn you will be
	//rendering ONTO and the location ON the Screen (x,y)
	public void render(Screen screen)
	{
		int xx = x-16;
		int yy = y-16;//^ CENTERING ISSUE ??? - ?
		
		screen.renderPlayer(xx, yy, Sprite.player0);
		screen.renderPlayer(xx+16, yy, Sprite.player1);
		screen.renderPlayer(xx, yy+16, Sprite.player2);
		screen.renderPlayer(xx+16, yy+16, Sprite.player3);
	}
	
	
	
	

}
