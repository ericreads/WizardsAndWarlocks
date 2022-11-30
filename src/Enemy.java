// Import required modules
import java.awt.*;
import java.util.*;

public class Enemy 
{
	private double x;
	private double y;
	
	private double speed; // Pixels Enemy moves unit time (frame * deltaTime)
	private double velocityX; // Directional vector representing movement along x axis
	private double velocityY; // Directional vector representing movement along y axis
	
	private static int width = 40;
	private static int height = 40;

	private double health;
	private boolean dead;
		
	private Rectangle position;
	private Stage stage;
	private Obstacle[][] nodes; // Grid of 50 x 50 tiles, used in pathfinding methods
		
	private Obstacle start; 
	private Obstacle goal;
	private Obstacle current;
	private int steps = 0;

	private ArrayList<Obstacle> open = new ArrayList<>(); // Nodes being evaluated
	private ArrayList<Obstacle> path = new ArrayList<>(); // Nodes that form shortest path from Enemy to Player
	
	private boolean reached = false;

	private Player player;
	
	public Enemy(int x, int y, Player player, Stage stage)
	{
		this.x = x;
		this.y = y;
		
		health = 5;
		dead = false;
		
		speed = Math.random() * 0.1 + 0.1;
		velocityX = 0;
		velocityY = 0;
		
		// Rectangle class stores Enemy's position
		position = new Rectangle(x, y, width, height);
		
		this.player = player;		
		this.stage = stage;
		
		// Initialize nodes array
		this.nodes = new Obstacle[stage.getObstacles().length][stage.getObstacles()[0].length];
		
		for (int i = 0; i < nodes.length; i++) 
		{
			for (int j = 0; j < nodes[i].length; j++) 
			{
				nodes[i][j] = stage.getObstacles()[i][j];
			}
		}
	}

	public Rectangle getPosition()
	{
		return this.position;
	}
	
	public boolean getDead()
	{
		return dead;
	}

	public void takeDamage(int damage)
	{
		health -= damage;
	}

	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLUE);
		g.fillOval((int) x, (int) y, width, height);
	}
	
	public void update(int deltaTime) 
	{
		if (health <= 0)
		{
			dead = true;
		}
		
		if (!dead)
		{
			// If Enemy intersects Player, Player loses 0.5 health
			if (player.intersects(this))
	    	{
	    		player.takeDamage(0.5f);
	    	}
			
			// Execute A* Pathfinding when Enemy is on-screen
			if ((this.x > 0 && this.x + width < 1240) && (this.y > 0 && this.y + height < 680))
			{
				// Instantiate nodes and assign G, H, and F Costs
				this.setNodes();
				
				// Search for shortest path from current position to Player's position
				if (this.pathFound())
				{
					// Determine x and y coordinates of next node in path
					int pathX = path.get(0).getX() * stage.getDimension();
					int pathY = path.get(0).getY() * stage.getDimension();

					// Move left or right accordingly
					if (this.x > pathX)
					{
						velocityX = -speed;
					}
					else if (this.x < pathX + stage.getDimension())
					{
						velocityX = speed;
					}
					
					// Move up or down accordingly
					if (this.y > pathY)
					{
						velocityY = -speed;
					}
					else if (this.y < pathY + stage.getDimension())
					{
						velocityY = speed;
					}
					
					if (this.x >= pathX && this.x + width <= pathX + stage.getDimension() + 2)
					{
						velocityX = 0;
					}
					if (this.y >= pathY && this.y + height <= pathY + stage.getDimension() + 2)
					{
						velocityY = 0;
					}
				}
			}
			else
			{
				// Move Enemy up, down, left, or right according to postion off screen
				if (this.x <= 40)
				{
					velocityX = speed;
				}
				else if (this.x + width >= 1240)
				{
					velocityX = -speed;
				}
				
				if (this.y <= 40)
				{
					velocityY = speed;
				}
				else if (this.y + height >= 681)
				{
					velocityY = -speed;
				}
			}
			
			if (player.getPosition().equals(this.position))
			{
				velocityX = 0;
				velocityY = 0;
			}
			
			// Update x and y coordinates
			x += velocityX * deltaTime;
			y += velocityY * deltaTime;
			
			position.setLocation((int)x,(int) y);

			// Detect potential collision, relocate path accordingly 
			for(int i = 0; i < nodes.length; i++)
	    	{
	    		for(int j = 0; j < nodes[i].length; j++)
	    		{
	    			if(nodes[i][j].getEnabled() && nodes[i][j].getBounds().intersects(position))
	    			{
	    				//To push the player out of the object take the rectangle created from the intersection of the player and the object,
						//then move them along the shortest dimension of that rectangle to get them out.
						if(nodes[i][j].getBounds().intersection(position).width < nodes[i][j].getBounds().intersection(position).height)
						{
							if(position.x < nodes[i][j].getBounds().intersection(position).x)
								x -= nodes[i][j].getBounds().intersection(position).width;
							else
								x += nodes[i][j].getBounds().intersection(position).width;
						} 
						else
						{
							if(position.y < nodes[i][j].getBounds().intersection(position).y)
								y -= nodes[i][j].getBounds().intersection(position).height;
							else
								y += nodes[i][j].getBounds().intersection(position).height;
						}	
	    			}
	    		}
	    	}	
	    	position.setLocation((int)x, (int)y);
		}
	}
	
	private void resetNodes()
	{
		for (int i = 0; i < nodes.length; i++)
		{
			for (int j = 0; j < nodes[i].length; j++)
			{
				nodes[i][j].setOpen(false);
				nodes[i][j].setChecked(false);
				nodes[i][j].path = false;
			}
		}
		open.clear();
		path.clear();
		
		reached = false;
		steps = 0;
	}
	
	private void setNodes()
	{
		this.resetNodes();
		
		start = nodes[(int)x / stage.getDimension()][(int) y / stage.getDimension()];
		current = start;
		goal = nodes[(int)player.getX() / stage.getDimension()][(int)player.getY() / stage.getDimension()];
		
		open.add(current);
		
		// Assign G, H, and F Cost 
		for (int i = 0; i < nodes.length; i++)
		{
			for (int j = 0; j < nodes[i].length; j++)
			{
				int xDistance, yDistance;
				int gCost, hCost;
				
				// Evaluate G Cost (distance from startNode to currentNode)
				xDistance = Math.abs(nodes[i][j].getX() - start.getX());
				yDistance = Math.abs(nodes[i][j].getY() - start.getY());
			
				gCost = xDistance + yDistance;
				nodes[i][j].setGCost(gCost);
				
				// Evaluate H Cost (distance from currentNode to goalNode)
				xDistance = Math.abs(nodes[i][j].getX() - goal.getX());
				yDistance = Math.abs(nodes[i][j].getY() - goal.getY());
				
				hCost = xDistance + yDistance;
				nodes[i][j].setHCost(hCost);
				
				// Set F Cost (H Cost + G Cost; determinant in evaluating shortest path)
				nodes[i][j].setFCost(gCost, hCost);
			}
		}
	}
	
	private void openNode(Obstacle node)
	{
		if (!node.getEnabled() && !node.getChecked() && !node.getOpen())
		{
			node.setOpen(true);
			node.setParent(current);
			open.add(node);
		}
	}
	
	private boolean pathFound()
	{
		while (!reached && steps < 700)
		{
			current.setChecked(true);
			open.remove(current);
			
			if (current.getY() - 1 >= 0)
			{
				openNode(nodes[current.getX()][current.getY() - 1]);
			}
			if (current.getY() + 1 < stage.getHeight())
			{
				openNode(nodes[current.getX()][current.getY() + 1]);
			}
			if (current.getX() - 1 >= 0)
			{
				openNode(nodes[current.getX() - 1][current.getY()]);
			}
			if (current.getX() + 1 < stage.getWidth()) 
			{
				openNode(nodes[current.getX() + 1][current.getY()]);
			}
			
			int index = 0;
			int fCost = 100000;
			
			for (int i = 0 ; i < open.size(); i++)
			{
				if(open.get(i).getFCost() < fCost)
				{
					index = i;
					fCost = open.get(i).getFCost();
				}
				else if (open.get(i).getFCost() == fCost)
				{
					if (open.get(i).getGCost() < open.get(index).getGCost())
					{
						index = i;
					}
				}
			}

			if (open.size() == 0)
			{
				break;
			}
			
			current = open.get(index);
			
			if (current == goal)
			{
				reached = true;
				this.track();
			}
			steps++;
		}
		return reached;
	}
	
	private void track()
	{
		Obstacle node = goal;
		
		while (node != start)
		{
			path.add(0, node);
			node.path = true;
			node = node.parent;
		}
	}
}
	