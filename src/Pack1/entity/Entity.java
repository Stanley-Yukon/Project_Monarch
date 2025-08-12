package Pack1.entity;

import java.util.Random;

import Pack1.graphics.Screen;
import Pack1.level.Level;

public class Entity {
	
	public int x;
	public int y;
	private boolean removed = false;
	protected Level level;//set by init() at bottom.
	protected final Random random = new Random();
	
	public void update()//-LINK to update() in Game
	{}
	
	public void render(Screen screen)//@OverR in Player
	{}
	
	public void remove()
	{
		//remove from level
		removed = true;
	}
	
	public boolean getRemoved()
	{return removed;}
	
	public void init(Level level)
	{
		this.level = level;
	}

}
