
// Import required modules
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Enemy {
	private double x;
	private double y;

	private double speed; // Pixels Enemy moves unit time (frame * deltaTime)
	private double velocityX; // Directional vector representing movement along x axis
	private double velocityY; // Directional vector representing movement along y axis

	private double playerX;
	private double playerY;

	private static int width = 45;
	private static int height = 45;

	private double health;
	private boolean dead;
	private double collisionDuration;

	private Rectangle position;
	private Stage stage;
	private Obstacle[][] nodes; // Grid of 50 x 50 tiles, used in path finding methods

	private Obstacle start;
	private Obstacle goal;
	private Obstacle current;

	private BufferedImage[] idle;
	private BufferedImage[] front_walk;
	private BufferedImage[] back_walk;
	private BufferedImage[] left_walk;
	private BufferedImage[] right_walk;

	private ArrayList<Obstacle> open = new ArrayList<>(); // Nodes being evaluated
	private ArrayList<Obstacle> path = new ArrayList<>(); // Nodes that form shortest path from Enemy to Player

	private boolean reached = false;
	private Player player;
	private HUD hud;

	private double frames = 0;
	private int animationFrames = 0;

	public Enemy(int x, int y, Player player, Stage stage, HUD hud, BufferedImage[] idle, BufferedImage[] front_walk,
			BufferedImage[] back_walk, BufferedImage[] left_walk, BufferedImage[] right_walk) {
		this.x = x;
		this.y = y;

		health = 5;
		dead = false;

		speed = Math.random() * 0.1 + 0.1;
		velocityX = 0;
		velocityY = 0;

		playerX = player.getX();
		playerY = player.getY();

		// Rectangle class stores Enemy's position
		position = new Rectangle(x, y, width, height);

		this.player = player;
		this.stage = stage;
		this.hud = hud;

		// Initialize nodes array
		this.nodes = new Obstacle[stage.getObstacles().length][stage.getObstacles()[0].length];

		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				nodes[i][j] = stage.getObstacles()[i][j];
			}
		}
		
		this.idle = idle;
		this.front_walk = front_walk;
		this.back_walk = back_walk;
		this.left_walk = left_walk;
		this.right_walk = right_walk; 

	}

	public Rectangle getPosition() {
		return this.position;
	}

	public boolean getDead() {
		return dead;
	}

	public void takeDamage(int damage) {
		health -= damage;
		if (!(health <= 0))
			hud.displayMoney("-" + Integer.toString(damage), (int) x, (int) y, new Color(232, 69, 55));
	}

	public void draw(Graphics2D g) {
		if (velocityX < 0) {
			g.drawImage(left_walk[(animationFrames % 64) / 8], (int) x, (int) y, width, height, null);
		}
		if (velocityX > 0) {
			g.drawImage(right_walk[(animationFrames % 64) / 8], (int) x, (int) y, width, height, null);
		}
		if (velocityY < 0) {
			g.drawImage(back_walk[(animationFrames % 64) / 8], (int) x, (int) y, width, height, null);
		}
		if (velocityY > 0) {
			g.drawImage(front_walk[(animationFrames % 64) / 8], (int) x, (int) y, width, height, null);
		}
	}

	public void update(int deltaTime) {
		if (health <= 0) {
			if (!dead) {
				SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney() + 50);
				hud.displayMoney("$50", (int) x, (int) y, new Color(108, 190, 48));
			}
			dead = true;
		}

		if (!dead) {
			// If Enemy intersects Player, Player loses 0.5 health
			if (player.intersects(this)) {
				collisionDuration += deltaTime;

				if (collisionDuration > 175) {
					player.takeDamage(5);
					collisionDuration = 0;
				}
			} else {
				collisionDuration = 101;
			}

			if (frames > 750) {
				playerX = player.getX();
				playerY = player.getY();
				frames = 0;
			}

			// Execute A* path finding when Enemy is on-screen
			if ((this.x > 0 && this.x + width < 1264) && (this.y > 0 && this.y + height < 691)) {
				// Instantiate nodes and assign G, H, and F Costs
				this.setNodes();

				// If pathFound returns true, move according to path
				if (this.pathFound()) {
					// Determine x and y coordinates of next node in path
					int pathX = path.get(0).getX() * stage.getDimension();
					int pathY = path.get(0).getY() * stage.getDimension();

					// Move left or right accordingly
					if (this.x > pathX) {
						velocityX = -speed;
					} else if (this.x < pathX + stage.getDimension()) {
						velocityX = speed;
					}

					// Move up or down accordingly
					if (this.y > pathY) {
						velocityY = -speed;
					} else if (this.y < pathY + stage.getDimension()) {
						velocityY = speed;
					}

					if (this.x >= pathX && this.x + width <= pathX + stage.getDimension() + 2) {
						velocityX = 0;
					}
					if (this.y >= pathY && this.y + height <= pathY + stage.getDimension() + 2) {
						velocityY = 0;
					}
				}
			} else {
				// Move Enemy up, down, left, or right according to position off-screen
				if (this.x <= 40) {
					velocityX = speed;
				} else if (this.x + width >= 1265) {
					velocityX = -speed;
				}

				if (this.y <= 40) {
					velocityY = speed;
				} else if (this.y + height >= 691) {
					velocityY = -speed;
				}
			}

			if (player.getPosition().equals(this.position)) {
				velocityX = 0;
				velocityY = 0;
			}

			// Update x and y coordinates
			x += velocityX * deltaTime;
			y += velocityY * deltaTime;

			position.setLocation((int) x, (int) y);

			// Detect potential collision, relocate path accordingly
			for (int i = 0; i < nodes.length; i++) {
				for (int j = 0; j < nodes[i].length; j++) {
					if (nodes[i][j].getEnabled() && nodes[i][j].getBounds().intersects(position)) {
						// To push the player out of the object take the rectangle created from the
						// intersection of the player and the object,
						// then move them along the shortest dimension of that rectangle to get them
						// out.
						if (nodes[i][j].getBounds().intersection(position).width < nodes[i][j].getBounds()
								.intersection(position).height) {
							if (position.x < nodes[i][j].getBounds().intersection(position).x) {
								x -= nodes[i][j].getBounds().intersection(position).width;
							} else {
								x += nodes[i][j].getBounds().intersection(position).width;
							}
						} else {
							if (position.y < nodes[i][j].getBounds().intersection(position).y) {
								y -= nodes[i][j].getBounds().intersection(position).height;
							} else {
								y += nodes[i][j].getBounds().intersection(position).height;
							}
						}
					}
				}
			}
			position.setLocation((int) x, (int) y);
		}
		frames += deltaTime;
		animationFrames++;
	}

	private void setNodes() {
		// Reset node attributes
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				nodes[i][j].setOpen(false);
				nodes[i][j].setChecked(false);
				// nodes[i][j].path = false;
			}
		}

		open.clear();
		path.clear();

		reached = false;

		// Assign start, current, and goal nodes
		start = nodes[(int) x / stage.getDimension()][(int) y / stage.getDimension()]; // Represents node of this
																						// Enemy's position
		goal = nodes[(int) playerX / stage.getDimension()][(int) playerY / stage.getDimension()]; // Represents node of
																									// Player's position

		current = start; // Represents node being evaluated
		open.add(current);

		// Assign G, H, and F Cost
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
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

	// Search for shortest path from current position to Player's position
	private boolean pathFound() {
		while (!reached) {
			current.setChecked(true);
			open.remove(current);

			// Open nodes above, below, and to the right and left of current to be evaluated
			if (current.getY() - 1 >= 0) {
				openNode(nodes[current.getX()][current.getY() - 1]);
			}
			if (current.getY() + 1 < stage.getHeight()) {
				openNode(nodes[current.getX()][current.getY() + 1]);
			}
			if (current.getX() - 1 >= 0) {
				openNode(nodes[current.getX() - 1][current.getY()]);
			}
			if (current.getX() + 1 < stage.getWidth()) {
				openNode(nodes[current.getX() + 1][current.getY()]);
			}

			int index = 0; // Index of node with lowest F Cost
			int fCost = Integer.MAX_VALUE; // Lowest F Cost

			for (int i = 0; i < open.size(); i++) {
				if (open.get(i).getFCost() < fCost) {
					index = i;
					fCost = open.get(i).getFCost();
				} else if (open.get(i).getFCost() == fCost) {
					if (open.get(i).getGCost() < open.get(index).getGCost()) {
						index = i;
					}
				}
			}

			// If there are no suitable paths, break loop
			if (open.size() == 0) {
				break;
			}

			current = open.get(index);

			// If goal node is located, re-trace steps to create path ArrayList
			if (current == goal) {
				reached = true;

				Obstacle node = goal;

				while (node != start) {
					path.add(0, node); // Append node to first index (0) of path
					// node.path = true;
					node = node.parent; // Continue for each nodes 'came-from' node
				}
			}
		}
		return reached;
	}

	private void openNode(Obstacle node) {
		if (!node.getEnabled() && !node.getChecked() && !node.getOpen()) {
			node.setOpen(true);
			node.setParent(current);
			open.add(node);
		}
	}
}
