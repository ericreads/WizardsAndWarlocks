// Import required modules
import javax.swing.*;
import java.awt.*;

public class Enemy 
{
	private int x;
	private int y;
	
	private double speed;
	private double velocityX;
	private double velocityY;
	
	private Rectangle position;
	
	private static int width = 40;
	private static int height = 40;
	
	private Player player;
	
	public Enemy(int x, int y, Player player)
	{
		this.x = x;
		this.y = y;
		
		speed = 0.15;
		velocityX = 0;
		velocityY = 0;
		
		// Rectangle class stores Enemy's position
		position = new Rectangle(x, y, width, height);
		
		this.player = player;		
	}
	
	public Rectangle getPosition()
	{
		return this.position;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(x,  y, width, height);
		
		g.setColor(Color.BLACK);
		g.drawRect((int)(position.getX()), (int)(position.getY()), (int)(position.getWidth()), (int)(position.getHeight())); 
	}
	
	public void move(int deltaTime) 
	{
		// Determine individual x and y displacements
		double displacementX = player.getPosition().getX() - this.x;
		double displacementY = player.getPosition().getY() - this.y;
		
		// Angle between Player, Enemy, and Player's y coordinate
		double angle;
		
		if (displacementY != 0)
		{
			angle = Math.atan(displacementX / displacementY);
		}
		else
		{
			angle = Math.toRadians(90); // For 0 y displacement; prevents division by 0 errors
		}
		
		// Enemy's displacement remains the same each frame (0.15 pixels)
		// Using x and y displacement and angle, calculate Enemy's x and y velocity 
		velocityX = speed * Math.abs(Math.sin(angle)) * normalize((int)(displacementX));
		velocityY = speed * Math.cos(Math.sin(angle)) * normalize((int)(displacementY));
		
		// Update x and y coordinates
		x += velocityX * deltaTime;
		y += velocityY * deltaTime;
		
		position.setLocation(x, y);
		
		
	}
	
	public void update(int deltaTime)
	{
		// This method will be used when Enemy deaths are implemented
		
		this.move(deltaTime);
	}
	
	// Returns 'unit vector' (-1, 1, or 0, representing direction of vector)
	private static int normalize(int vector) 
	{
		if (vector == 0) 
		{
			return 0;
		}
		else
		{
			return vector / Math.abs(vector);
		}
	}
}
