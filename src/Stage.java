// Import required modules
import java.awt.*;
import java.util.*;

public class Stage 
{
	private Obstacle[][] obstacles; 
	
	private int width; // represents width in # of nodes
	private int height; // represents height in # of nodes
	private static int DIMENSION = 50; 

	public Stage(float initialPercent) 
	{
		this.width = 1280 / DIMENSION;
		this.height = (720 - DIMENSION) / DIMENSION;

		// Instantiate obstacles
		obstacles = new Obstacle[width][height]; 

		// Create new obstacles for each array element
		for (int i = 0; i < obstacles.length; i++) 
		{
			for (int j = 0; j < obstacles[i].length; j++) 
			{
				obstacles[i][j] = new Obstacle(i * DIMENSION, j * DIMENSION, DIMENSION);
			}
		}
		
		// Randomize the blocks with the starting obstacle percent
		randomize(initialPercent);
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public int getDimension()
	{
		return DIMENSION;
	}

	// Method to randomize the blocks
	public void randomize(float obstaclePercent) 
	{
		// Randomize the obstacles based on the obstacle percent
		for (int i = 0; i < obstacles.length; i++) {
			for (int j = 0; j < obstacles[i].length; j++) {
				if(i > 10 && i < 14 && j > 5 && j < 9)
					obstacles[i][j].disable();
				else 
				{
					if (Math.random() < obstaclePercent && i != 0 && i != obstacles.length-1 && j != 0 && j != obstacles[i].length-1)
						obstacles[i][j].enable();
					else
						obstacles[i][j].disable();
				}
			}
		}
	}

	public void draw(Graphics2D g) 
	{
		// Draw every obstacle in the array
		for (int i = 0; i < obstacles.length; i++) 
		{
			for (int j = 0; j < obstacles[i].length; j++) 
			{
				obstacles[i][j].draw(g);
			}
		}
	}

	// Method which gets the obstacles
	public Obstacle[][] getObstacles() 
	{
		return obstacles;
	}
	
	// Method to get node that Object is on
	public Rectangle getNode(int x, int y)
	{
		return obstacles[x / DIMENSION][y / DIMENSION].getBounds();
	}
}