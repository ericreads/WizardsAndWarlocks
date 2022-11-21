// Import required modules
import javax.swing.*;
import java.awt.*;

public class Enemy 
{
	private int x;
	private int y;
	private float velocityX;
	private float velocityY;
	
	private Rectangle position;
	
	private static int width = 50;
	private static int height = 50;
	
	private Rectangle playerPosition;
	
	public Enemy(int x, int y, Player player)
	{
		this.x = x;
		this.y = y;
		
		this.velocityX = 0;
		this.velocityY = 0;
		
		
		// Rectangle class stores player's position; used for collision detection
		position = new Rectangle(x, y, width, height);
		
		// Store player's position
		this.playerPosition = player.getPosition();
	}
	
	public void paint(Graphics2D g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(x,  y, width, height);
		
		g.setColor(Color.BLACK);
		g.drawRect((int)(position.getX()), (int)(position.getY()), (int)(position.getWidth()), (int)(position.getHeight())); 
	}
	
	public void move() 
	{
		
	}
	
	
	
}
