// Import required modules
import java.awt.*;

public class SpellSprayer extends Weapon {
	public SpellSprayer(int x, int y, Image sprite)
	{
		super(x, y, 2, sprite, 1);
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
			double xa = Math.cos(weaponAngle+(Math.PI*diff)/30);
			double ya = Math.sin(weaponAngle+(Math.PI*diff)/30);
			//Shoot a particle in the direction of the mousea
			spellManager.add(new Spell(null, new Rectangle(bounds.x + (int)(xa*40), bounds.y + (int)(ya*40), 10, 10), (float)xa, (float)ya, damagePerParticle), true);
		}
	}

}