// Import required modules
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Weapon extends Object{
	protected int damagePerParticle;
	private BufferedImage sprite;
	protected SpellManager spellManager;
	protected Rectangle bounds;
	protected double weaponAngle;
	
	public Weapon(int x, int y, int damagePerParticle, BufferedImage sprite, String name, String description, BufferedImage icon)
	{
		super(name, description, icon, 500);
		bounds = new Rectangle(x, y, 50, 50);
		this.damagePerParticle = damagePerParticle;
		this.sprite = sprite;
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
	}
	public abstract void press();
	public abstract void release();
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
		gg.drawImage(sprite, bounds.x, bounds.y-25, bounds.width, bounds.height, null);
		gg.dispose();
	}
}