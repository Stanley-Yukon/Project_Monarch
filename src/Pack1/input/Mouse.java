package Pack1.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener
{
	//Negative -1 as it makes printing mouse location more definite
	private static int mouseX = -1;
	private static int mouseY = -1;
	
	private static int mouseB = -1;
	
	public static int getX() {return mouseX;}
	public static int getY() {return mouseY;}
	public static int getB() {return mouseB;}

	//АЛТ + SHIFT + S -> I for auto fill
	public void mouseDragged(MouseEvent e)
	{//FOR DRAGGING PROJECTILE FUNCTIONALITY
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{	
		mouseX = e.getX();
		mouseY = e.getY();	
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void mousePressed(MouseEvent e)
	{mouseB = e.getButton();}
	public void mouseReleased(MouseEvent e)
	{mouseB = -1;}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}
	public static int getButton()
	{return mouseB;}
}
