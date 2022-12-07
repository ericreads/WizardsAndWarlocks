
//Import Required Modules
import java.awt.*;
import java.awt.event.*;

public abstract class GameScreen {
	//Boolean to store if the variables are initialized
	private boolean isInitialized = false;
	//Method to call when you want to initialize the method
	public void runInitialize()
	{
		initialize();
		isInitialized = true;
	}
	//Method will be called when the screen is created
	public abstract void initialize();
	//Method will be called every frame of the update class
	public abstract void  update(int deltaTime);
	//Method to call when you want to update 
	public void runUpdate(int deltaTime)
	{
		if(isInitialized)
			update(deltaTime);
	}
	//Method will be called in the paint method
	public abstract void draw(Graphics2D g);
	//Method to call when you want to draw
	public void runDraw(Graphics2D g)
	{
		if(isInitialized)
			draw(g);
	}
	//Method will be called when a key is pressed
	public abstract void keyPressed(KeyEvent e);
	//Method will be called when a key is released
	public abstract void keyReleased(KeyEvent e);
	//Method will be called when a key is typed
	public abstract void keyTyped(KeyEvent e);
	//Method will be called when the mouse is clicked
	public abstract void mouseClicked(MouseEvent e);
	
}
