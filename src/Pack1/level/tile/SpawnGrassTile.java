package Pack1.level.tile;

import Pack1.graphics.Screen;
import Pack1.graphics.Sprite;

public class SpawnGrassTile extends Tile{
	
	public SpawnGrassTile(Sprite sprite)
	{super(sprite);}
	
	public void render(int x, int y, Screen screen)//We need position + Actual Screen which will do the render work
	{
		screen.renderTile(x<<4, y<<4, this);//these are in pixel format
	}//			
	

}
