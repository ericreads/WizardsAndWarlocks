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
		
		 tiles = new BufferedImage[30];

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
        	tiles[9] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0049.png")); // detailed ground piece
        	tiles[10] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0072.png")); // detailed ground piece
        	tiles[11] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0052.png")); // left/right wall
        	tiles[12] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0001.png")); // right bottom inner corner
        	tiles[13] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0003.png")); // left bottom inner corner
        	tiles[14] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0025.png")); // right top inner corner
        	tiles[15] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0027.png")); // left top inner corner
        	tiles[16] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0006.png")); // single block font
        	tiles[17] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0004.png")); // single block
        	tiles[18] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0005.png")); // backing piece
        	tiles[19] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0007.png")); // right outer corner
        	tiles[20] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0008.png")); // left outer corner
        	tiles[21] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0059.png")); // right outer wall
        	tiles[22] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0057.png")); // left outer wall
        	tiles[23] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0058.png")); // left/right outer wall
        	tiles[24] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0051.png")); // right shadow
        	tiles[25] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0053.png")); // right corner shadow
        	tiles[26] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0054.png")); // right corner shadow
        	tiles[27] = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0055.png")); // inner corner shadow
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
				if (obstacles[i][j].getEnabled()) 
				{
					if (!obstacles[i - 1][j].getEnabled()) 
					{
						if (!obstacles[i][j + 1].getEnabled()) 
						{
							if (!obstacles[i + 1][j].getEnabled())
							{
								if (!obstacles[i][j - 1].getEnabled()) 
								{
									obstacles[i][j].draw(g, tiles[17]);
								}
								else 
								{
									obstacles[i][j].draw(g, tiles[16]);
								}
							}
							else 
							{
								obstacles[i][j].draw(g, tiles[7]); // left corner
							}
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
					// draw back piece
					if (!obstacles[i][j - 1].getEnabled()) 
					{
						obstacles[i][j].draw(g, tiles[18]);
					}
					// draw inner corner piece overlay
					if (!obstacles[i - 1][j + 1].getEnabled() && 
							(obstacles[i - 1][j].getEnabled() && obstacles[i][j + 1].getEnabled())) 
					{
						obstacles[i][j].draw(g, tiles[13]);
					}
					if (!obstacles[i + 1][j + 1].getEnabled() && 
							(obstacles[i + 1][j].getEnabled() && obstacles[i][j + 1].getEnabled()))
					{
						obstacles[i][j].draw(g, tiles[12]);
					}
					if (!obstacles[i - 1][j - 1].getEnabled() && 
							(obstacles[i - 1][j].getEnabled() && obstacles[i][j - 1].getEnabled()))
					{
						obstacles[i][j].draw(g, tiles[15]);
					}
					if (!obstacles[i + 1][j - 1].getEnabled() && 
							(obstacles[i + 1][j].getEnabled() && obstacles[i][j - 1].getEnabled()))
					{
						obstacles[i][j].draw(g, tiles[14]);
					}
					
					// draw outer corner piece overlay
					if (!obstacles[i + 1][j].getEnabled() && !obstacles[i][j - 1].getEnabled())
					{
						obstacles[i][j].draw(g, tiles[19]);
					}
					if (!obstacles[i - 1][j].getEnabled() && !obstacles[i][j - 1].getEnabled())
					{
						obstacles[i][j].draw(g, tiles[20]);
					}
				}
				else {
					if ((i != 0 && j != 0) && (obstacles[i - 1][j].getEnabled() && obstacles[i][j - 1].getEnabled()))
					{
						obstacles[i][j].draw(g, tiles[27]);
					}
					else if ((i != 0 && j != 0) && obstacles[i - 1][j - 1].getEnabled()
							&& (!obstacles[i - 1][j].getEnabled() && !obstacles[i][j - 1].getEnabled()))
					{
						obstacles[i][j].draw(g, tiles[25]);
					}
					// draw front wall piece
					else if (j != 0 && obstacles[i][j - 1].getEnabled()) 
					{
						if (!obstacles[i - 1][j - 1].getEnabled() && (!obstacles[i + 1][j - 1].getEnabled()))
						{
							obstacles[i][j].draw(g, tiles[23]);
						}
						else if ((!obstacles[i - 1][j - 1].getEnabled()))
						{
							obstacles[i][j].draw(g, tiles[22]);
						}
						else if (!obstacles[i + 1][j - 1].getEnabled())
						{
							obstacles[i][j].draw(g, tiles[21]);
						}
						
						else 
						{
							obstacles[i][j].draw(g, tiles[4]);
						}
					}
					else if (i != 0 && obstacles[i - 1][j].getEnabled())
					{
						if (!obstacles[i - 1][j - 1].getEnabled())
						{
							obstacles[i][j].draw(g, tiles[26]);
						}
						else
						{
							obstacles[i][j].draw(g, tiles[24]);
						}
					}
					
					else if (obstacles[i][j].getNoise() < 15) 
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