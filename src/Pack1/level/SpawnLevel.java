package Pack1.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Pack1.graphics.Screen;
import Pack1.level.tile.Tile;

public class SpawnLevel extends Level{
	
	private String name = "Nexus";
	private static final int TILESIZE = 16;
	
	public SpawnLevel(String path) 
	{
		super(path);
	}
	
	//clashes with GetTile & render ?
	protected void loadLevel(String path)
	{
		try {
			BufferedImage image= ImageIO.read(SpawnLevel.class.getResource(path));
			width  = image.getWidth();
			height = image.getHeight();
			tiles = new int[width*height];
			image.getRGB(0, 0,width,height,tiles,0,width);
		}//					  Load into here ^
		catch(IOException e) 
			{
			e.printStackTrace();
			System.out.println("Exception ! Level File not here Bruh");
		}
			
	}//need a method to convert pixels into tiles ?
	
	//Grass = 0xFF00
	//FLOWER = 0xFFFF00
	//ROCK = 0x7f7f00
	protected void generateLevel()
	{
		System.out.println("Tiles: "+tiles[0]);
	}	
}//END