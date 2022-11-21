// Import required modules
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Class PlayerScreen is to test Player Class
public class PlayerScreen extends GameScreen
{
	private Player player;
	
	@Override
	public void initialize() 
	{
		player = new Player(600, 320);
	}
	
	@Override
	public void update(int deltaTime) 
	{
		player.move(deltaTime);
	}
	
	@Override
	public void draw(Graphics2D g) 
	{
		player.paint(g);
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		player.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
}
