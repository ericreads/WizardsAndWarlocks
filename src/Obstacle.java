// Import required modules
import java.awt.*;

// Obstacle class represents in-game obstacles and nodes for path finding
public class Obstacle 
{
	public Obstacle parent = null; // 'Came from' node
	
	private int x;
	private int y; 
	private int dimension;
	
	private Rectangle bounds; // Rectangle stores position and size to be used in collision detection
	private boolean enabled; // Represents if 'node' is walkable/un-walkable
	
	private int gCost; // Distance from startNode to this node
	private int hCost; // Distance from this node to goalNode
	private int fCost; // gCost + hCost; used in evaluating shortest path

	boolean path = false;
	
	private boolean open = false;
	private boolean checked = true;
	
	// Create an obstacle with the specified position, width, and height
	public Obstacle(int x, int y, int dimension) 
	{
		this.dimension = dimension; 
		
		bounds = new Rectangle(x, y, dimension, dimension);
		enabled = false;
		
		// x and y represent indices this Obstacle in list in Stage class; NOT x and y coordinates of Obstacle
		this.x = (x / dimension); 
		this.y = (y / dimension);
	}
	
	// Method to enable the obstacle
	public void enable() 
	{
		enabled = true;
	}

	// Method to disable the obstacle
	public void disable() 
	{
		enabled = false;
	}

	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	// Method to get if the obstacle is enabled
	public boolean getEnabled() 
	{
		return enabled;
	}
	
	public boolean getOpen()
	{
		return open;
	}
	
	public boolean getChecked()
	{
		return checked;
	}

	// Method to get the bounds of the obstacle
	public Rectangle getBounds() 
	{
		return bounds;
	}
	
	// Method to get gCost
	public int getGCost()
	{
		return this.gCost;
	}
	
	public int getHCost()
	{
		return this.hCost;
	}

	// Method to get fCost
	public int getFCost()
	{
		return this.fCost;
	}

	// Method to get parent
	public Obstacle getParent()
	{
		return this.parent;
	}

	// Method to set gCost
	public void setGCost(int gCost)
	{
		this.gCost = gCost;
	}
	
	// Method to set hCost
	public void setHCost(int hCost)
	{
		this.hCost = hCost;
	}
	
	// Method to set fCost
	public void setFCost(int gCost, int hCost)
	{
		this.fCost = gCost + hCost;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public void setOpen(boolean open)
	{
		this.open = open;
	}
	
	public void setParent(Obstacle parent)
	{
		this.parent = parent;
	}
	
	public void draw(Graphics2D g) 
	{
		if (enabled) 
		{
			g.setColor(Color.RED);
			g.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.getWidth(), (int) bounds.getHeight());
			
		}
		if (path)
		{
			g.setColor(Color.ORANGE);
			g.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.getWidth(), (int) bounds.getHeight());
		}
	}
}