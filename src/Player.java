// Import required modules
// import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {
	private int x;
	private int y;
	
	private double speed;
	private double velocityX;
	private double velocityY;
	
	private static int width = 40;
	private static int height = 40; 
	
	private Rectangle position;	
	private boolean left, right, up, down;
	private int collisionDuration; // Collision duration, in # of frames
	
	private double health;
	private boolean dead;
	

  	private Obstacle[][] obstacles;
  
	public Player(int x, int y, Stage stage) {
		this.x = x;
		this.y = y;
		
		speed = 0.3;
		velocityX = 0;
		velocityY = 0;
		
		// Rectangle class stores player's position; used for collision detection
		position = new Rectangle(x, y, width, height);
		collisionDuration = 0;
		
		//Store a reference to the obstacles
		obstacles = stage.getObstacles();
		// Initialize movement indicators (left, right, up, and down)
		left = false;
		right = false;
		up = false;
		down = false;
	
		// Initialize full health (5 'hearts')
		health = 5; 
		dead = false;
	}
	
	public Rectangle getPosition()
	{
		return this.position;
	}
	
	public boolean intersects(Enemy enemy)
	{
		if (enemy.getPosition().intersects(this.position))
		{
			return true;
		}
		else
		{
			return false;
		}
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

	public void takeDamage(double damage) 
	{
		collisionDuration++;
		
		if (collisionDuration % 15 == 0) 
		{
			health -= damage;
		}
	}
    
    public void draw(Graphics2D g) 
    {
		// Ellipse temporarily represents player (red if alive, black if dead)
    	if (!dead)
    	{
    		g.setColor(Color.RED);
    		g.fillOval(x, y, width, height);
    		
    	}
    	else
    	{
    		g.setColor(Color.black);
    		g.fillOval(x, y, width, height);
    	}
    	
    	
    	// Draw position to represent player's bounds
    	g.setColor(Color.BLACK);
    	g.drawRect((int)(position.getX()), (int)(position.getY()), (int)(position.getWidth()), (int)(position.getHeight())); 
    }
    
    public void update(int deltaTime) {
    	if (!dead)	
    	{    		
    		
    		if (left)
        	{
        		// Prohibit player from moving off-screen
        		if (x - speed < 0)
        		{
        			velocityX = 0;
        		}
        		else
        		{
        			velocityX = -speed;
        		}
        	}
        	if (right) 
        	{
        		if (x + width + speed > 1262)
        		{
        			velocityX = 0;
        		}
        		else
        		{
        			velocityX = speed;
        		}
        	}
        	if (up) 
        	{
        		if (y - speed < 0)
        		{
        			velocityY = 0;
        		}
        		else
        		{
        			velocityY = -speed;
        		}
        	}
        	if (down) 
        	{
        		if (y + height + speed > 680)
        		{
        			velocityY = 0;
        		}
        		else
        		{
        			velocityY = speed;
        		}
        	}
        	
        	// Set player's position 
        	x += velocityX * deltaTime;
        	y += velocityY * deltaTime;
    	}
    	
    	//Collision Detection
    	for(int i = 0; i < obstacles.length; i++)
    	{
    		for(int j = 0; j < obstacles[i].length; j++)
    		{
    			if(obstacles[i][j].getEnabled())
    			{
    				if(obstacles[i][j].getBounds().intersects(position))
    				{
    					//To push the player out of the object take the rectangle created from the intersection of the player and the object,
    					//then move them along the shortest dimension of that rectangle to get them out.
    					if(obstacles[i][j].getBounds().intersection(position).width < obstacles[i][j].getBounds().intersection(position).height)
    					{
    						if(position.x < obstacles[i][j].getBounds().intersection(position).x)
    							x -= obstacles[i][j].getBounds().intersection(position).width;
    						else
    							x += obstacles[i][j].getBounds().intersection(position).width;
    					} else
    					{
    						if(position.y < obstacles[i][j].getBounds().intersection(position).y)
    							y -= obstacles[i][j].getBounds().intersection(position).height;
    						else
    							y += obstacles[i][j].getBounds().intersection(position).height;
    					}
    				}
    					
    			}
    		}
    		
    		if (this.health <= 0)
        	{
        		dead = true; 
        	}
        	
        	
    	}
    	
    	position.setLocation(x, y);
    }
}
