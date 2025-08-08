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
	private Sprite playersprite;
	private int animate_COUNTER = 0;
	//^ THIS CAN FUCKING CRASH if you leave it overnight
	// Int has MAX RANGE... 2^32
	private boolean walking = false;
	
	public Player(Keyboard input)
	{
		this.input = input;
		playersprite = Sprite.player;
	}
	
	public Player(int x, int y, Keyboard input)//SPECIFIC SPAWN POINT FOR LEVELS
	{
		this.x = x;// this -> Takes you to Entity
		this.y = y;
		
		this.input = input;
		playersprite = Sprite.player;
		
	}
	
	//public void move() - ???
	
	public void update()//@Override Mob.update ( Don't need call Player.update to update Player )
	{
		int xC=0;
		int yC=0;
		
		//CHECKS on int size
		if(animate_COUNTER < 2000)
			{animate_COUNTER++;}
		else animate_COUNTER = 0;
		//CHECKS on int size
		
		if(input.up)yC--;
		if(input.down)yC++;
		if(input.left)xC--;
		if(input.right)xC++;
		
		if(xC != 0 || yC != 0)
		{
			move(xC,yC);//Move() is from Mob Class
			walking = true;
		}
		else {walking=false;}//ALL ANIMATION is done in RENDER
	}
	
	//All render methods require the SCREEn you will be
	//rendering ONTO and the location ON the Screen (x,y)
	public void render(Screen screen)
	{
		int xx = x-16;
		int yy = y-16;//^ CENTERING ISSUE ??? - ?
		
		/*
		 * We have to Quad Render as we HAVE SMALL SPRITES
		 * Make Bigger Sprites in Sprites Page Lmao
		 * 			METHOD BELOW IS REDUNDANT 
		 *
		screen.renderPlayer(xx, yy, Sprite.player0);
		screen.renderPlayer(xx+16, yy, Sprite.player1);
		screen.renderPlayer(xx, yy+16, Sprite.player2);
		screen.renderPlayer(xx+16, yy+16, Sprite.player3);
		*/
		if(dir == 0) {
			playersprite = Sprite.playerB;
			if(walking) {//FIRST CHECK ( Idle = Idle animation )
				if(animate_COUNTER%20 > 10) {//2nd CHECK (half the time, pick 1 of 2 walk(s))
					playersprite = Sprite.player_b1;
				}
				else {playersprite = Sprite.player_b2;}
			}
		}
		if(dir == 1) {
			playersprite = Sprite.playerR;
			if(walking) {
				if(animate_COUNTER%20 > 10) {
					playersprite = Sprite.player_r1;
				}
				else {playersprite = Sprite.player_r2;}
			}
		}
		
		if(dir == 2) {
			playersprite = Sprite.player;
			if(walking) {
				if(animate_COUNTER%20 > 10) {
					playersprite = Sprite.player_f1;
				}
				else {playersprite = Sprite.player_f2;}
			}
		}
		if(dir == 3) {
			playersprite = Sprite.playerL;
			if(walking) {
				if(animate_COUNTER%20 > 10) {
					playersprite = Sprite.player_l1;
				}
				else {playersprite = Sprite.player_l2;}
			}
		}
		
		screen.renderPlayer(xx, yy, playersprite);
		//Two Animation ideas ( face direction & walking(itself) )
	}
	
	
	
	

}
