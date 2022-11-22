//Import required modules
import javax.swing.*;
import java.awt.*;
public class Obstacle {
	//A rectangle to store pos, size and help with collision
	private Rectangle bounds;
	private boolean enabled;
	
	//Create an obstacle with the specified position, and width/height
	public Obstacle(int x, int y, int d)
	{
		bounds = new Rectangle(x, y, d, d);
		enabled = false;
	}
	//Method to enable the obstacle
	public void enable()
	{
		enabled = true;
	}
	//Method to disable the obstacle
	public void disable()
	{
		enabled = false;
	}
	//Method to get if the obstacle is enabled
	public boolean getEnabled()
	{
		return enabled;
	}
	//Method to get the bounds of the obstacle
	public Rectangle getBounds()
	{
		return bounds;
	}
	public void draw(Graphics2D g)
	{
		//Draw a temp rectangle
		if(enabled)
		{
			g.setColor(Color.RED);
			g.fillRect((int)bounds.x, (int)bounds.y, (int)bounds.getWidth(), (int)bounds.getHeight());
		}
	}
}
