package Pack1.level;
import java.util.Random;

//must define explicit constructor

public class RandomLevel extends Level{
	
	//VERY IMPORTANT - "WHY MAKE STATIC ?"
	private static final Random random = new Random();
	//VERY IMPORTANT - "WHY DOES THIS NOT "LOAD" AT Game() Start ?
	
	public RandomLevel(int width, int height)
	{
		super(width,height);// super - shit is input into S-Class Constructor
		//code in S-Class will still be generated
		createRandomLevel();//!!!!!!!!!!!!!!!!!!!!!!! NIGGA ??? !!!!!!!
	}
	
	protected void createRandomLevel()
	{
		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width ; x++)//for loops cycle index to fill
			{
				tiles[x+(y*width)] = random.nextInt(4);
				//gives ^ 0,1,2,3 (max is 4-1)... (max-1)
				//if random is 0 = grass
				//if random is 1 = darkgrass
				//if random is 2 = flower
				//if random is 3 = weed
			}
		}
	}

}
