
//Import Required Modules
import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class GameScreen {

	//Method will be called when the screen is created
	public abstract void initialize();
	//Method will be called every frame of the update class
	public abstract void  update(int deltaTime);
	//Method will be called in the paint method
	public abstract void draw(Graphics2D g);
	//Method will be called when a key is pressed
	public abstract void keyPressed(KeyEvent e);
	//Method will be called when a key is released
	public abstract void keyReleased(KeyEvent e);
	//Method will be called when a key is typed
	public abstract void keyTyped(KeyEvent e);
	
}
