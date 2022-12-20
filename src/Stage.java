// Import required modules
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Stage 
{
	private Obstacle[][] obstacles; 
	private BufferedImage[] tiles;
	
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
		
		 tiles = new BufferedImage[20];

        try {
        	tiles[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0000.png")); // obstacle block
        	tiles[1] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0048.png")); // ground
        	tiles[2] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0002.png")); // obstacle front
        	tiles[3] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0026.png")); // obstacle back
        	tiles[4] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0040.png")); // stone brick
        	tiles[5] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0015.png")); // left wall
        	tiles[6] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0013.png")); // right wall
        	tiles[7] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0016.png")); // left corner
        	tiles[8] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0017.png")); // right corner
        	tiles[9] =  ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0049.png")); // detailed ground piece
        	tiles[10] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0072.png")); // detailed ground piece
        	tiles[11] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0052.png")); // left/right wall
        	tiles[12] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0001.png")); // right bottom inner corner
        	tiles[13] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0003.png")); // left bottom inner corner
        	tiles[14] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0025.png")); // right bottom inner corner
        	tiles[15] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0027.png")); // left bottom inner corner

        	
        }
        catch (IOException e) {
        	System.out.println(e.toString());
        }
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

	public void randomize(float obstaclePercent) 
	{
		// Randomize the obstacles based on the obstacle percent
		for (int i = 0; i < obstacles.length; i++) {
			for (int j = 0; j < obstacles[i].length; j++) {

				if (Math.random() < obstaclePercent && i != 0 && i != obstacles.length-1 && j != 0 && j != obstacles[i].length-1)
					obstacles[i][j].enable();
				else
					obstacles[i][j].disable();

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
				if (obstacles[i][j].getEnabled()) 
				{
					if (!obstacles[i - 1][j].getEnabled()) 
					{
						if (!obstacles[i][j + 1].getEnabled()) 
						{
							obstacles[i][j].draw(g, tiles[7]); // left corner
						}
						else 
						{
							if (!obstacles[i + 1][j].getEnabled()) 
							{
								obstacles[i][j].draw(g, tiles[11]);
							}
							else 
							{
								obstacles[i][j].draw(g, tiles[5]); // left wall
							}
						}
					}
					else if (!obstacles[i + 1][j].getEnabled()) 
					{
						if (!obstacles[i][j + 1].getEnabled()) 
						{
							obstacles[i][j].draw(g, tiles[8]); // right corner
						}
						else 
						{
							obstacles[i][j].draw(g, tiles[6]); // right wall
						}
					}
					else 
					{
						if (!obstacles[i][j + 1].getEnabled()) 
						{
							obstacles[i][j].draw(g, tiles[2]);
						}
						else 
						{
							obstacles[i][j].draw(g, tiles[0]);
						}
					}
					// draw inner corner piece overlay
					if (!obstacles[i - 1][j + 1].getEnabled() && (obstacles[i - 1][j].getEnabled() && obstacles[i][j + 1].getEnabled())) 
					{
						obstacles[i][j].draw(g, tiles[13]);
					}
					else if (!obstacles[i + 1][j + 1].getEnabled() && (obstacles[i + 1][j].getEnabled() && obstacles[i][j + 1].getEnabled()))
					{
						obstacles[i][j].draw(g, tiles[12]);
					}
					else if (!obstacles[i - 1][j - 1].getEnabled() && (obstacles[i - 1][j].getEnabled() && obstacles[i][j - 1].getEnabled()))
					{
						obstacles[i][j].draw(g, tiles[15]);
					}
					else if (!obstacles[i + 1][j - 1].getEnabled() && (obstacles[i + 1][j].getEnabled() && obstacles[i][j - 1].getEnabled()))
					{
						obstacles[i][j].draw(g, tiles[14]);
					}
				}
				else {
					
					if (j != 0 && obstacles[i][j - 1].getEnabled()) 
					{
						obstacles[i][j].draw(g, tiles[4]);
					}
					else if (obstacles[i][j].getNoise() < 10) 
					{
						obstacles[i][j].draw(g, tiles[9]);
					}
					else 
					{
						obstacles[i][j].draw(g, tiles[1]);
					}
				}
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