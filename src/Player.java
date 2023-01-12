
// Import required modules
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Player {
	private double x;
	private double y;
	private double startX;
	private double startY;

	private double speed;
	private double velocityX;
	private double velocityY;

	private static int width = 45;
	private static int height = 45;

	private Rectangle position;
	private double playerAngle;
	private boolean left, right, up, down;
	private String lastPosition = "down";
	private int deathCounter = 0;

	private double health;
	private int maxHealth;
	private boolean dead;
	private Weapon weapon;
	private Inventory inventory;
	private Obstacle[][] obstacles;

	private BufferedImage[] idle;
	private BufferedImage[] front_walk;
	private BufferedImage[] back_walk;
	private BufferedImage[] left_walk;
	private BufferedImage[] right_walk;

	private BufferedImage starterWandSprite;
	private BufferedImage spellSlingerSprite;
	private BufferedImage spellSprayerSprite;
	private BufferedImage starterWandIcon;
	private BufferedImage spellSlingerIcon;
	private BufferedImage spellSprayerIcon;

	private int animationFrames = 0;

	public Player(int x, int y, Stage stage) 
	{
		SaveManager.getInstance().refreshVals();
		this.x = x;
		this.y = y;

		startX = x;
		startY = y;

		speed = 0.3;
		velocityX = 0;
		velocityY = 0;

		// Rectangle class stores player's position; used for collision detection
		position = new Rectangle(x, y, width, height);

		// Store a reference to the obstacles
		obstacles = stage.getObstacles();
		// Initialize movement indicators (left, right, up, and down)
		left = false;
		right = false;
		up = false;
		down = false;

		// Initialize full health
		maxHealth = SaveManager.getInstance().getPlayerHealth();
		health = maxHealth;
		dead = false;

		// Load sprites
		idle = new BufferedImage[4];
		front_walk = new BufferedImage[8];
		back_walk = new BufferedImage[8];
		left_walk = new BufferedImage[8];
		right_walk = new BufferedImage[8];

		try 
		{
			// Player idle
			idle[0] = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_idle.png"));
			idle[1] = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_idle.png"));
			idle[2] = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_idle.png")); // left
			idle[3] = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_idle.png")); // right

			// Player running forward
			for (int i = 0; i < 8; i++) 
			{
				front_walk[i] = ImageIO
						.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_walk" + (i + 1) + ".png"));
			}

			// Player running back
			for (int i = 0; i < 8; i++) 
			{
				back_walk[i] = ImageIO
						.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_walk" + (i + 1) + ".png"));
			}

			// Player running left
			for (int i = 0; i < 8; i++) 
			{
				left_walk[i] = ImageIO
						.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_walk" + (i + 1) + ".png"));
			}
			
			// Player running right
			for (int i = 0; i < 8; i++) 
			{
				right_walk[i] = ImageIO
						.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_walk" + (i + 1) + ".png"));
			}

			// Wand Sprites
			starterWandSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0127(rotated).png"));
			spellSlingerSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0130(rotated).png"));
			spellSprayerSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0129(rotated).png"));

			// Wand Icons
			starterWandIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0127.png"));
			spellSlingerIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0130.png"));
			spellSprayerIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0129.png"));
		} 
		catch (IOException e) 
		{
			System.out.println(e.toString());
		}
		
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		weapons.add(new StarterWand(x, y, starterWandSprite, starterWandIcon));
		
		if (SaveManager.getInstance().hasSpellSlinger())
			weapons.add(new SpellSlinger(x, y, spellSlingerSprite, spellSlingerIcon));
		
		if (SaveManager.getInstance().hasSpellSprayer())
			weapons.add(new SpellSprayer(x, y, spellSprayerSprite, spellSprayerIcon));
		
		inventory = new Inventory(weapons);
		weapon = inventory.getWeapon();
	}

	public void setSpellManager(SpellManager spellManager) 
	{
		for (Weapon w : inventory.getWeapons()) 
		{
			w.setSpellManager(spellManager);
		}
	}

	public Rectangle getPosition()
	{
		return this.position;
	}

	public double getX() 
	{
		return this.x;
	}

	public double getY() 
	{
		return this.y;
	}

	public boolean intersects(Enemy enemy) 
	{
		if (enemy.getPosition().intersects(this.position)) 
		{
			return true;
		} else {
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
		inventory.keyPressed(e);
	}

	// KeyEvent input is pressed from GameScreenManager Class when key is released
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) 
		{
			left = false;
			velocityX = 0;
			lastPosition = "left";
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) 
		{
			right = false;
			velocityX = 0;
			lastPosition = "right";
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) 
		{
			up = false;
			velocityY = 0;
			lastPosition = "up";
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) 
		{
			down = false;
			velocityY = 0;
			lastPosition = "down";
		}
		inventory.keyPressed(e);
	}

	// Called on the start of the next wave
	public void reset() 
	{
		x = startX;
		y = startY;
		position.setLocation((int) startX, (int) startY);
	}

	// Called when the mouse is pressed
	public void mousePressed(MouseEvent e) 
	{
		// Ensure that MouseEvent is not home button being pressed
		// Prevents player from casting spell while trying to return to main menu
		if (e.getButton() == MouseEvent.BUTTON1) 
		{
			weapon.press();
		}
	}

	// Called when the mouse is released
	public void mouseReleased(MouseEvent e) 
	{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			weapon.release();
		}
	}

	public void takeDamage(double damage) 
	{
		health -= damage;
	}

	public void draw(Graphics2D g) {
		if (velocityX == 0 && velocityY == 0) 
		{
			// Idle state
			switch (lastPosition) 
			{
			case "left":
				g.drawImage(idle[2], (int) x, (int) y, width, height, null);
				break;
			case "right":
				g.drawImage(idle[3], (int) x, (int) y, width, height, null);
				break;
			case "up":
				g.drawImage(idle[1], (int) x, (int) y, width, height, null);
				break;
			case "down":
				g.drawImage(idle[0], (int) x, (int) y, width, height, null);
				break;
			}
		} 
		else 
		{
			if (left) 
			{
				g.drawImage(left_walk[(animationFrames % 64) / 8], (int) x, (int) y, width, height, null);
			}
			if (right) 
			{
				g.drawImage(right_walk[(animationFrames % 64) / 8], (int) x, (int) y, width, height, null);
			}
			if (up) 
			{
				g.drawImage(back_walk[(animationFrames % 64) / 8], (int) x, (int) y, width, height, null);
			}
			if (down) 
			{
				g.drawImage(front_walk[(animationFrames % 64) / 8], (int) x, (int) y, width, height, null);
			}
		}
		weapon.draw(g);
		inventory.draw(g);
	}

	public void update(int deltaTime) 
	{
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
				if (x + width + speed > 1245) 
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
				if (y + height + speed > 665) 
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
			weapon = inventory.getWeapon();
		} 
		else 
		{
			SaveManager.getInstance().saveVals();
			deathCounter += deltaTime;
			
			if (deathCounter > 1000) 
			{
				GameScreenManager.getInstance().addScreen(new DeathScreen());
			}
		}

		// Collision Detection
		for (int i = 0; i < obstacles.length; i++) 
		{
			for (int j = 0; j < obstacles[i].length; j++) 
			{
				if (obstacles[i][j].getEnabled()) 
				{
					if (obstacles[i][j].getBounds().intersects(position))
					{
						// To push the player out of the object take the rectangle created from the
						// intersection of the player and the object,
						// then move them along the shortest dimension of that rectangle to get them
						// out.
						if (obstacles[i][j].getBounds().intersection(position).width < obstacles[i][j].getBounds()
								.intersection(position).height) 
						{
							if (position.x < obstacles[i][j].getBounds().intersection(position).x)
							{
								x -= obstacles[i][j].getBounds().intersection(position).width;
							}	
							else
							{
								x += obstacles[i][j].getBounds().intersection(position).width;
						
							}
						}
						else 
						{
							if (position.y < obstacles[i][j].getBounds().intersection(position).y)
							{
								y -= obstacles[i][j].getBounds().intersection(position).height;
							}
							else
							{
								y += obstacles[i][j].getBounds().intersection(position).height;
						
							}
						}
					}

				}
			}

			if (this.health <= 0 && !dead) 
			{
				SaveManager.getInstance().saveVals();
				dead = true;
			}
		}
		position.setLocation((int) x, (int) y);
		weapon.updatePosition((int) x + 20, (int) y + 20);
		weapon.update(deltaTime);
		
		animationFrames++;

	}

	public double getHealth() 
	{
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
}
