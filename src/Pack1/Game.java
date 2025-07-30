package Pack1;

//EP4 ( awt = Abstract Window Toolkit )
import java.awt.Canvas;// see line5 below - YOU MUST be EXPLICIT about dependencies
import java.awt.Dimension;

import javax.swing.JFrame;//requires java.desktop in module.info <-
//

public class Game extends Canvas implements Runnable{
	//EP4???
	private static final long serialVersionUID = 1L;//???
	
	//Start with Screen Size
	public static int screenwidth = 300;
	// 16 - 9 Aspect Ratio for height
	public static int screenheight = (screenwidth/16)*9;
	public static int scale = 3;// Make Window BIGGER
	
	//EP2_THREADS -> sub-process >> So we can do multiple things at once 
	private Thread thread1;//Create Thread Object
	private JFrame frame;// EP4 _ Import JFrame and Canvas ^ line 5&6
	private boolean running = false;
	
	public Game()//Ep4- Constructor// Functions inside constructor get EXECUTED before object creation
	{//Dimension class from java.awt
		Dimension size1 = new Dimension(screenwidth*scale, screenheight*scale);
		//setPreferredSize() - (Canvas Class <-extend Component) This function "applies" the Dimension Object ???
		setPreferredSize(size1);//This IS what frame.pack() refers to
		
		frame = new JFrame();//runs when Game() constructor is called
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
		thread1 = new Thread(this, "Display");
		thread1.start();
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
		while(running == true)//Keep game running while threads are joined
		{
			System.out.println("Running...");
			
		}
		
	}
	
	//EP4
	public static void main(String[] args)//The Eject Seat
	{
		Game stanleyGame = new Game();
		//Allows us to access stuff in Game(Class)
		
		
		stanleyGame.frame.setResizable(false);//No resizing = No Graphics Errors
		stanleyGame.frame.setTitle("Realm Of The Dead Gods");
		stanleyGame.frame.add(stanleyGame);
		//add() function in frame adds the component into our frame ( fill with instance of "this" game. We can do this as Canvas is Superclass )
		//frame.add( Canvas ) Canvas<-Component
		stanleyGame.frame.pack();
		//pack() takes in size1 ? No, from setPreferredSize(size1) line29
		stanleyGame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Unless...Game will still run but window will just close
		stanleyGame.frame.setLocationRelativeTo(null);
		stanleyGame.frame.setVisible(true);
		stanleyGame.start();
		
	}
	

}
