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
	
	public Weapon(int x, int y, int damagePerParticle)
	{
		bounds = new Rectangle(x, y, 50, 10);
		this.damagePerParticle = damagePerParticle;
	}
	public void setSpellManager(SpellManager spellManager)
	{
		this.spellManager = spellManager;
	}
	
	public double getWeaponAngle() {
		return this.weaponAngle; 
	}
	
	public void update(int deltaTime)
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