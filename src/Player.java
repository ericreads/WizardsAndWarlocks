// Import required modules
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {
	
	private int x;
	private int y;
	private float velocityX;
	private float velocityY;
	
	private static int width = 50;
	private static int height = 50; 
	
	private Rectangle position; 
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private int health;
	private boolean isDead;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.velocityX = 0;
		this.velocityY = 0;
		
		// Rectangle class stores player's position; used for collision detection
		position = new Rectangle(x, y, width, height);
		
		// Initialize movement indicators (left, right, up, and down)
		left = false;
		right = false;
		up = false;
		down = false;
	
		health = 5; 
		isDead = false;
	}
	
	public Rectangle getPosition()
	{
		return this.position;
	}
	
	// KeyEvent input is passed from GameScreenManager Class when key is pressed
	public void keyPressed(KeyEvent e) 
	{
		// W, A, S, D or Arrow Keys are used to control Player movement
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) 
        {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) 
        {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) 
        {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) 
        {
            down = true;
        }
    }

	// KeyEvent input is pressed from GameScreenManager Class when key is released
    public void keyReleased(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) 
        {
            left = false;
            velocityX = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) 
        {
            right = false;
            velocityX = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) 
        {
            up = false;
            velocityY = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) 
        {
            down = false;
            velocityY = 0;
        }
    }

	public void takeDamage(int damage)
	{
		health -= damage;

		if (health <= 0)
		{
			isDead = true;
		}
	}
    
    public void paint(Graphics2D g) 
    {
		// Ellipse temporarily represents player
    	g.setColor(Color.RED);
    	g.fillOval(x, y, width, height);
    	
    	// Draw position to represent player's bounds
    	g.setColor(Color.BLACK);
    	g.drawRect((int)(position.getX()), (int)(position.getY()), (int)(position.getWidth()), (int)(position.getHeight())); 
    }
    
    public void move(int deltaTime) {
    	if (left)
    	{
    		// Prevent player from moving off-screen
    		if (x - 0.4 < 0)
    		{
    			velocityX = 0;
    		}
    		else
    		{
    			velocityX = -0.4f;
    		}
    	}
    	if (right) 
    	{
    		if (x + width + 0.4 > 1262)
    		{
    			velocityX = 0;
    		}
    		else
    		{
    			velocityX = 0.4f;
    		}
    	}
    	if (up) 
    	{
    		if (y - 0.4 < 0)
    		{
    			velocityY = 0;
    		}
    		else
    		{
    			velocityY = -0.4f;
    		}
    	}
    	if (down) 
    	{
    		if (y + height + 0.4 > 680)
    		{
    			velocityY = 0;
    		}
    		else
    		{
    			velocityY = 0.4f;
    		}
    	}
    	
    	// Set player's position 
    	x += velocityX * deltaTime;
    	y += velocityY * deltaTime;
    	
    	position.setLocation(x, y);
    }
}
