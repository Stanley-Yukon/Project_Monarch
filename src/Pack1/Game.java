package Pack1;

//EP4 ( awt = Abstract Window Toolkit )
import java.awt.Canvas;// see line5 below - YOU MUST be EXPLICIT about dependencies
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;//requires java.desktop in module.info <-
//

import Pack1.graphics.Screen;

public class Game extends Canvas implements Runnable{
	//EP4???
	private static final long serialVersionUID = 1L;//???
	
	//Start with Screen Size
	public static int screenwidth = 300;
	// 16 - 9 Aspect Ratio for height
	public static int screenheight = (screenwidth/16)*9;
	public static int scale = 3;// Make Window BIGGER
	public static String title = "Realm Of The Dead Gods";
	
	// ! Game(s) have Frame & Thread
	
	//IMPORTANT CLASS
	private BufferedImage view = new BufferedImage(screenwidth, screenheight,BufferedImage.TYPE_INT_RGB);
	//
	//Problem is we can't manipulate image ^^^, how to write data ?
	//Make Array of pixels = {RASTER}?
	//Raster is a GRID, a rectangular array of pixels
	private int[] pixels = ((DataBufferInt)view.getRaster().getDataBuffer()).getData();
	//Manipulate DataBuffer which Manipulates Raster - See Screen(Class)
	
	//EP2_THREADS -> sub-process >> So we can do multiple things at once 
	private Thread thread1;//Create Thread Object
	private JFrame frame;// EP4 _ Import JFrame and Canvas ^ line 5&6
	private boolean running = false;
	
	private Screen screen1;
	
	public Game()//Ep4- Constructor// Functions inside constructor get EXECUTED before object creation
	{//Dimension class from java.awt
		Dimension size1 = new Dimension(screenwidth*scale, screenheight*scale);
		//setPreferredSize() - (Canvas Class <-extend Component) This function "applies" the Dimension Object ???
		setPreferredSize(size1);//This IS what frame.pack() refers to
		
		screen1 = new Screen(screenheight, screenwidth);
		frame = new JFrame();//runs when Game() constructor is called
		//Frame has Dimension
	}
	
	//synchronized means -> preventing
	// ((Thread Interference)) Thread1 is visible to many things
	// ((Memory inconsistency))
	
	public synchronized void start() //function to CREATE new thread
	{//		 Runnable allows "this" in line25 "this" refers to Game(Class) Instance(OBJ)
		//synchronized means -> preventing
		// ((Thread Interference)) thread1 OBJ will be visible to other things 
		// ((Memory inconsistency))
		running = true;
		thread1 = new Thread(this, "Display");//ep6 - auto runs start()
		
		thread1.start();//Threads will always call this.run() on themselves
		
		//running = true; !- Effects of before or after new Thread()????
		//why must declaration of running = true or false be at start ?
	}
	
	public synchronized void stop()// - For join() and running == stop
	{
		running = false;//actually causes game loop to stop in run()
		try{
			thread1.join();//EXAMPLE -Music keeps playing despite closing tab
		   }
		catch(InterruptedException e){
			e.printStackTrace();}
	}
	
	//EP3 - boolean running and run()
	public void run()
	{
		long lastTime = System.nanoTime();// 1. retrieve CURRENT time
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int numFrames = 0;//How Many Frames per SECOND ?
		int numUpdates = 0;//How Many Updates per SECOND ?
		long OneSecTimer = System.currentTimeMillis();
		
		while(running == true)//Keep game running while threads are joined
		{
			double now = System.nanoTime(); // 2. After getting to while LOOP
			delta += (now - lastTime)/ns;
			lastTime = (long)now;
			//LOCKING THE update() TO 60 FPS !!! ( ns is a "converter" 10^9 nano a sec, we want 60 a sec now...
			while(delta >= 1)// TRUE INNER LOOP
			{
				update();//Tick Method
				numUpdates++;
				delta--;
			}
			render();
			numFrames++;
			
			if(System.currentTimeMillis() - OneSecTimer > 1000)
			{
				OneSecTimer += 1000;//prepare for next loop 1 sec later
				frame.setTitle(title + "  |  " + numUpdates + " ups, " + numFrames + " fps");
				numUpdates = 0;
				numFrames = 0;
			}
			
		}
		stop();
	}
	
	int x=0;
	int y=0;
	
	public void update()
	{
		x++;
		y++;
		
	}
	
	//EP5
	public void render()
	{//A buffer is a temporary storage space ( Ready-List )
		BufferStrategy bs = getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(3);//Number ??? 2- double buffering // 3- triple buffering
		}
		//EP10 - CLear Screen
		screen1.clear();
		
		//EP9 - Rendering Pixels
		screen1.render(x,y);
		
		for(int i=0;i<pixels.length;i++)
		{
			pixels[i] = screen1.pixels[i];
		}
		//EP9 - ! You must clear old pixels..How? ep10
		
		Graphics g = bs.getDrawGraphics();//Links ((g)) and buffer
		//////////////
		//DISPLAY GRAPHICS HERE
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());//Top left is 0,0
		g.drawImage(view, 0, 0, getWidth(), getHeight(),null);
		
		
		//TO HERE 
		/////////////
		
		g.dispose();
		bs.show();
		//^ important combo
	}
	
	//EP4
	public static void main(String[] args)//The Eject Seat
	{
		Game stanleyGame = new Game();
		//Allows us to access stuff in Game(Class)
		
		
		stanleyGame.frame.setResizable(false);//No resizing = No Graphics Errors
		stanleyGame.frame.setTitle(title);
		stanleyGame.frame.add(stanleyGame);
		//add() function in frame adds the component into our frame ( fill with instance of "this" game. We can do this as Canvas is Superclass )
		//frame.add( Canvas ) Canvas<-Component
		stanleyGame.frame.pack();
		//pack() takes in size1 ? No, from setPreferredSize(size1) line29
		stanleyGame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Unless...Game will still run but window will just close
		stanleyGame.frame.setLocationRelativeTo(null);
		stanleyGame.frame.setVisible(true);
		stanleyGame.createBufferStrategy(3);//Buffer Strategy is called on CANVAS, not Frame
		//you can only createBufferStrategy() AFTER setVisible(), 
		
		stanleyGame.start();
		
	}
	

}
