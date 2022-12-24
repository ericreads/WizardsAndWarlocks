
// Import required modules
import java.awt.*;
import java.awt.image.*;

public class SpellSprayer extends Weapon {
	public SpellSprayer(int x, int y, BufferedImage sprite, BufferedImage icon) {
		super(x, y, 1, sprite, "Spell Sprayer",
				"Shoots multiple low damage spells that can do \nmassive damage at close range.", icon);
	}

	@Override
	public void press() {
		shoot();
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	public void shoot() {
		for (int i = 0; i < 5; i++) {
			int diff = i - 2;
			double xa = Math.cos(weaponAngle + (Math.PI * diff) / 30);
			double ya = Math.sin(weaponAngle + (Math.PI * diff) / 30);
			// Shoot a particle in the direction of the mouse
			spellManager.add(new Spell(null, new Rectangle(bounds.x + (int) (xa * 40), bounds.y + (int) (ya * 40), 10, 10),
							(float) xa, (float) ya, damagePerParticle, new Color(209, 118, 208)), true);
		}
	}

}