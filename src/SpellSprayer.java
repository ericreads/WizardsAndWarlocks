// Import required modules
import java.awt.*;

public class SpellSprayer extends Weapon {
	public SpellSprayer(int x, int y, int damagePerParticle, Image sprite)
	{
		super(x, y, damagePerParticle, sprite);
	}
	@Override
	public void press() {
		shoot();
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}
	public void shoot()
	{
		for(int i = 0; i < 5; i++)
		{
			int diff = i-2;
			double[] unitVector = Helper.unitVector(bounds.x + diff*5, bounds.y + diff*5, GameJPanel.getMouseX(), GameJPanel.getMouseY());
			//Shoot a particle in the direction of the mouse
			spellManager.add(new Spell(null, new Rectangle(bounds.x + (int)(unitVector[0]*40), bounds.y + (int)(unitVector[1]*40), 10, 10), (float)unitVector[0], (float)unitVector[1], damagePerParticle), true);
		}
	}

}
