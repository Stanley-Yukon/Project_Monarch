package Pack1.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//key Listener is an INTERFACE
// Listens to key strokes based on a COMPONENT ( Canvas in out case )

public class Keyboard implements KeyListener{
	
	//Boolen Array that stores all Phys Keyboard keys
	private boolean[] keys = new boolean[120];
	public boolean up, down, left,right;
	
	public void update()
	{
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Aut
		
	}
	
	

}
