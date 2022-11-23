// Import required modules
import java.awt.*;

public class Enemy 
{
	private int x;
	private int y;
	
	private double speed;
	private double velocityX;
	private double velocityY;
	
	private double health;
	private boolean dead;
	
	private Rectangle position;
	private Obstacle[][] obstacles;
	
	private static int width = 40;
	private static int height = 40;
	
	private Player player;
	
	public Enemy(int x, int y, Player player, Stage stage)
	{
		this.x = x;
		this.y = y;
		
		health = 5;
		dead = false;
		
		speed = Math.random() * 0.2;
		velocityX = 0;
		velocityY = 0;
		
		// Rectangle class stores Enemy's position
		position = new Rectangle(x, y, width, height);
		
		this.player = player;		
		this.obstacles = stage.getObstacles();
	}
	
	public void takeDamage(double damage) 
	{
		health -= damage;
	}
	
	public Rectangle getPosition()
	{
		return this.position;
	}
	
	public boolean getDead()
	{
		return dead;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(x,  y, width, height);
	}
	
	public void update(int deltaTime) 
	{
		if (health <= 0)
		{
			dead = true;
		}
		
		if (!dead)
		{
			if (player.intersects(this))
	    	{
	    		player.takeDamage(0.5f);
	    	}
			
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
	    	}
	    	
	    	position.setLocation(x, y);
		}
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
