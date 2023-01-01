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
  
  	private BufferedImage front_idle, back_idle, left_idle, right_idle;
  	private BufferedImage front_walk_00, front_walk_01, front_walk_02, front_walk_03, front_walk_04, front_walk_05, front_walk_06, front_walk_07;
  	private BufferedImage back_walk_00, back_walk_01, back_walk_02, back_walk_03, back_walk_04, back_walk_05, back_walk_06, back_walk_07;
  	private BufferedImage left_walk_00, left_walk_01, left_walk_02, left_walk_03, left_walk_04, left_walk_05, left_walk_06, left_walk_07;
  	private BufferedImage right_walk_00, right_walk_01, right_walk_02, right_walk_03, right_walk_04, right_walk_05, right_walk_06, right_walk_07;
  	
  	private BufferedImage starterWandSprite;
	private BufferedImage spellSlingerSprite;
	private BufferedImage spellSprayerSprite;
	private BufferedImage starterWandIcon;
	private BufferedImage spellSlingerIcon;
	private BufferedImage spellSprayerIcon;

  	private int frames = 0; 
  	
	public Player(int x, int y, Stage stage) {
		this.x = x;
		this.y = y;
		
		startX = x;
		startY = y;
		
		speed = 0.3;
		velocityX = 0;
		velocityY = 0;
		
		// Rectangle class stores player's position; used for collision detection
		position = new Rectangle(x, y, width, height);
		
		//Store a reference to the obstacles
		obstacles = stage.getObstacles();
		// Initialize movement indicators (left, right, up, and down)
		left = false;
		right = false;
		up = false;
		down = false;
	
		// Initialize full health
		maxHealth = 10;
		health = 10;
		dead = false;
		
		// Load sprites
		try {
			// Player idle
			front_idle = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_idle.png"));		
			back_idle = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_idle.png"));		
			left_idle = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_idle.png"));		
			right_idle = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_idle.png"));		
			
			// Player running forward
			front_walk_00 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_walk1.png"));		
			front_walk_01 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_walk2.png"));
			front_walk_02 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_walk3.png"));
			front_walk_03 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_walk4.png"));
			front_walk_04 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_walk5.png"));
			front_walk_05 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_walk6.png"));
			front_walk_06 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_walk7.png"));
			front_walk_07 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_front_walk8.png"));
			
			// Player running back
			back_walk_00 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_walk1.png"));		
			back_walk_01 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_walk2.png"));
			back_walk_02 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_walk3.png"));
			back_walk_03 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_walk4.png"));
			back_walk_04 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_walk5.png"));
			back_walk_05 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_walk6.png"));
			back_walk_06 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_walk7.png"));
			back_walk_07 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_back_walk8.png"));
			
			// Player running left
			left_walk_00 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_walk1.png"));		
			left_walk_01 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_walk2.png"));
			left_walk_02 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_walk3.png"));
			left_walk_03 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_walk4.png"));
			left_walk_04 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_walk5.png"));
			left_walk_05 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_walk6.png"));
			left_walk_06 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_walk7.png"));
			left_walk_07 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side02_walk8.png"));
			
			// Player running right
			right_walk_00 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_walk1.png"));		
			right_walk_01 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_walk2.png"));
			right_walk_02 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_walk3.png"));
			right_walk_03 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_walk4.png"));
			right_walk_04 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_walk5.png"));
			right_walk_05 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_walk6.png"));
			right_walk_06 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_walk7.png"));
			right_walk_07 = ImageIO.read(getClass().getResourceAsStream("/wizard_sprites/elf_side01_walk8.png"));
			
			//Wand Sprites
			starterWandSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0127(rotated).png"));
			spellSlingerSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0130(rotated).png"));
			spellSprayerSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0129(rotated).png"));
			
			//Wand Icons
			starterWandIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0127.png"));
			spellSlingerIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0130.png"));
			spellSprayerIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0129.png"));
		} 
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		weapons.add(new StarterWand(x, y, starterWandSprite, starterWandIcon));
		if(SaveManager.getInstance().hasSpellSlinger())
			weapons.add(new SpellSlinger(x, y, spellSlingerSprite, spellSlingerIcon));
		if(SaveManager.getInstance().hasSpellSprayer())
			weapons.add(new SpellSprayer(x, y, spellSprayerSprite, spellSprayerIcon));
		inventory = new Inventory(weapons);
		weapon = inventory.getWeapon();
	}
	public void setSpellManager(SpellManager spellManager)
	{
		for(Weapon w : inventory.getWeapons())
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
    //Called on the start of the next wave
    public void reset()
    {
    	x = startX;
    	y = startY;
    	position.setLocation((int)startX, (int)startY);
    }
    //Called when the mouse is pressed
    public void mousePressed(MouseEvent e)
    {
    	// Ensure that MouseEvent is not home button being pressed
    	// Prevents player from casting spell while trying to return to main menu
		if (e.getButton() == MouseEvent.BUTTON1 /* && (GameJPanel.getMouseX() < 1193 || GameJPanel.getMouseX() > 1238)
				&& (GameJPanel.getMouseY() < 10 || GameJPanel.getMouseY() > 55) */)
    		weapon.press();
    }
    //Called when the mouse is released
    public void mouseReleased(MouseEvent e)
    {
    	if(e.getButton() == MouseEvent.BUTTON1 /* &&  (GameJPanel.getMouseX() < 1193 || GameJPanel.getMouseX() > 1238)
				&& (GameJPanel.getMouseY() < 10 || GameJPanel.getMouseY() > 55) */)
    		weapon.release();
    }

	public void takeDamage(double damage) 
	{
		health -= damage;
	}
    
    public void draw(Graphics2D g) 
    {
    	if (velocityX == 0 && velocityY == 0)
		{
			// Idle state
			switch (lastPosition)
			{
			case "left":
				g.drawImage(left_idle, (int) x, (int) y, width, height, null);
				break;
			case "right":
				g.drawImage(right_idle, (int) x, (int) y, width, height, null);
				break;
			case "up":
				g.drawImage(back_idle, (int) x, (int) y, width, height, null);
				break;
			case "down":
				g.drawImage(front_idle, (int) x, (int) y, width, height, null);
				break;
			}    		
		}
		else
		{
			if (left)
			{
				if (frames % 64 < 8)
				{
					g.drawImage(left_walk_00, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 16)
				{
					g.drawImage(left_walk_01, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 24)
				{
					g.drawImage(left_walk_02, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 32)
				{
					g.drawImage(left_walk_03, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 40)
				{
					g.drawImage(left_walk_04, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 48)
				{
					g.drawImage(left_walk_05, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 56)
				{
					g.drawImage(left_walk_06, (int) x, (int) y, width, height, null);
				}
				else 
				{
					g.drawImage(left_walk_07, (int) x, (int) y, width, height, null);
				}
			}
			if (right)
			{
				if (frames % 64 < 8)
				{
					g.drawImage(right_walk_00, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 16)
				{
					g.drawImage(right_walk_01, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 24)
				{
					g.drawImage(right_walk_02, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 32)
				{
					g.drawImage(right_walk_03, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 40)
				{
					g.drawImage(right_walk_04, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 48)
				{
					g.drawImage(right_walk_05, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 56)
				{
					g.drawImage(right_walk_06, (int) x, (int) y, width, height, null);
				}
				else 
				{
					g.drawImage(right_walk_07, (int) x, (int) y, width, height, null);
				}
			}
			if (up)
			{
				if (frames % 64 < 8)
				{
					g.drawImage(back_walk_00, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 16)
				{
					g.drawImage(back_walk_01, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 24)
				{
					g.drawImage(back_walk_02, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 32)
				{
					g.drawImage(back_walk_03, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 40)
				{
					g.drawImage(back_walk_04, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 48)
				{
					g.drawImage(back_walk_05, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 56)
				{
					g.drawImage(back_walk_06, (int) x, (int) y, width, height, null);
				}
				else 
				{
					g.drawImage(back_walk_07, (int) x, (int) y, width, height, null);
				}
			}
			if (down)
			{
				if (frames % 64 < 8)
				{
					g.drawImage(front_walk_00, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 16)
				{
					g.drawImage(front_walk_01, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 24)
				{
					g.drawImage(front_walk_02, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 32)
				{
					g.drawImage(front_walk_03, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 40)
				{
					g.drawImage(front_walk_04, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 48)
				{
					g.drawImage(front_walk_05, (int) x, (int) y, width, height, null);
				}
				else if (frames % 64 < 56)
				{
					g.drawImage(front_walk_06, (int) x, (int) y, width, height, null);
				}
				else 
				{
					g.drawImage(front_walk_07, (int) x, (int) y, width, height, null);
				}
			}
			
		}
		weapon.draw(g);
        inventory.draw(g);
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
    		deathCounter += deltaTime;
    		if(deathCounter > 1000)
    		{
    			GameScreenManager.getInstance().clearScreens();
    			GameScreenManager.getInstance().addScreen(new MainMenu());
    		}
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
    			if(!dead)
    				SaveManager.getInstance().saveVals();
        		dead = true; 
        	}
        	
        	
    	}
    	position.setLocation((int) x, (int) y);
    	frames++;
    	weapon.updatePosition((int)x+20, (int)y+20);
    	weapon.update(deltaTime);
    	
    	frames++;
    }
    public double getHealth() 
    { 
    	return health; 
    }
    
    public int getMaxHealth() 
    { 
    	return maxHealth; 
    }
}
