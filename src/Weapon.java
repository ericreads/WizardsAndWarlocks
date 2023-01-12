
// Import required modules
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Weapon extends Object {
	protected int damagePerParticle;
	private BufferedImage sprite;
	protected SpellManager spellManager;
	protected Rectangle bounds;
	protected double weaponAngle;
<<<<<<< HEAD
	private boolean shooting = false;
	private int shootCounter = 0;
	private int reload = 50;
	public Weapon(int x, int y, int damagePerParticle, Image sprite, int reload_count)
	{
		bounds = new Rectangle(x, y, 50, 10);
=======

	public Weapon(int x, int y, int damagePerParticle, BufferedImage sprite, String name, String description,
			BufferedImage icon, int cost) {
		super(name, description, icon, cost);
		bounds = new Rectangle(x, y, 50, 50);
>>>>>>> main
		this.damagePerParticle = damagePerParticle;
		this.sprite = sprite;

	}

	public void setSpellManager(SpellManager spellManager) {
		this.spellManager = spellManager;
	}

	public double getWeaponAngle() {
		return this.weaponAngle;
	}

	public void update(int deltaTime) {
		// Find the angle to the mouse
		weaponAngle = Helper.angleTowards(bounds.x, bounds.y, GameJPanel.getMouseX(), GameJPanel.getMouseY());
		//Shoot at a certain interval
		if(getShooting() && shootCounter > 75)
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
	}
	

	public void setShooting(boolean y)
	{
		shooting = y;
	}
	
	public boolean getShooting()
	{
		return shooting;
	}
	
	public void setReload(int x)
	{
		if (x == 50)
		{
			setShooting(false);
		}
		reload = x;
	}
	
	public int getReload()
	{
		return reload;
	}
	
	
	public void press()
	{
		setShooting(true);
		shootCounter = 51;
	}
	public void release()
	{
		setShooting(false);
	}
<<<<<<< HEAD
	//Called by the player to update the position
	public void updatePosition(int x, int y)
	{
=======

	public abstract void press();

	public abstract void release();

	// Called by the player to update the position
	public void updatePosition(int x, int y) {
>>>>>>> main
		bounds.x = x;
		bounds.y = y;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		// Do arcane Graphics2D magic to rotate the weapon
		Graphics2D gg = (Graphics2D) g.create();
		gg.rotate(weaponAngle, (double) bounds.x, (double) bounds.y);
		gg.drawImage(sprite, bounds.x, bounds.y - 25, bounds.width, bounds.height, null);
		gg.dispose();
	}
}