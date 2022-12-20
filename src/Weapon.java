// Import required modules
import java.awt.*;
import java.awt.event.*;

public class Weapon {
	private int damagePerParticle;
	private Image sprite;
	private SpellManager spellManager;
	private Rectangle bounds;
	private double weaponAngle;
	private boolean shooting = false;
	private int shootCounter = 0;
	private int reload = 50;
	private int reload_count;
	private long reload_time;
	
	public Weapon(int x, int y, int damagePerParticle, int reload_count)
	{
		bounds = new Rectangle(x, y, 50, 10);
		this.damagePerParticle = damagePerParticle;
		this.reload_count = reload_count;

	}
	public void setSpellManager(SpellManager spellManager)
	{
		this.spellManager = spellManager;
	}
	public void update(int deltaTime) throws InterruptedException
	{
		//Find the angle to the mouse
		weaponAngle = Helper.angleTowards(bounds.x, bounds.y, GameJPanel.getMouseX(), GameJPanel.getMouseY());
		//Shoot at a certain interval
		if(shooting && shootCounter > 75)
		{
			shoot();
			shootCounter = 0;
		}
		else
		{
			shootCounter += deltaTime;
		}
	}
	

	public void shoot() 
	{
		double[] unitVector = Helper.unitVector(bounds.x, bounds.y, GameJPanel.getMouseX(), GameJPanel.getMouseY());
		//Shoot a particle in the direction of the mouse
		spellManager.add(new Spell(null, new Rectangle(bounds.x + (int)(unitVector[0]*40), bounds.y + (int)(unitVector[1]*40), 10, 10), (float)unitVector[0], (float)unitVector[1], damagePerParticle), true);
		setReload(reload -= 1);
		if (getReload() == 0)
		{
			shooting = false;
			setReload(50);
			long start = System.currentTimeMillis();
			System.out.println(start);
			if (start / 1000 < 3)
			{
				shooting = false;
			}
	
		}
		System.out.println(getReload());
	}
	
	public void setReload(int x)
	{
		if (x == 50)
		{
			shooting = false;
		}
		reload = x;
	}
	
	public int getReload()
	{
		return reload;
	}
	
	
	public void press()
	{
		shooting = true;
		shootCounter = 51;
	}
	public void release()
	{
		shooting = false;
	}
	//Called by the player to update the position
	public void updatePosition(int x, int y)
	{
		bounds.x = x;
		bounds.y = y;
	}
	public void draw(Graphics2D g)
	{
		g.setColor(Color.black);
		//Do arcane Graphics2D magic to rotate the weapon
		Graphics2D gg = (Graphics2D)g.create();
		gg.rotate(weaponAngle, (double)bounds.x, (double)bounds.y);
		gg.fill(bounds);
		gg.dispose();
	}
}