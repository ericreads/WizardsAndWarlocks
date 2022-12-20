import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.*;
public class StarterWand extends Weapon {
	public StarterWand(int x, int y, BufferedImage sprite)
	{
		super(x, y, 5, sprite, "Starting Wand", "The wand of a beginner, ok but not remarkable.", sprite);
	}
	@Override
	public void press() {
		shoot();
	}

	@Override
	public void release() {
		//Do nothing
	}
	public void shoot()
	{
		double[] unitVector = Helper.unitVector(bounds.x, bounds.y, GameJPanel.getMouseX(), GameJPanel.getMouseY());
		//Shoot a particle in the direction of the mouse
		spellManager.add(new Spell(null, new Rectangle(bounds.x + (int)(unitVector[0]*40), bounds.y + (int)(unitVector[1]*40), 10, 10), (float)unitVector[0], (float)unitVector[1], damagePerParticle), true);
	}
}
