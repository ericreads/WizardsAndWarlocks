
// Import required modules
import java.awt.*;
import java.awt.image.*;

public class SpellSlinger extends Weapon {
	private boolean shooting = false;
	private int shootCounter = 0;

	public SpellSlinger(int x, int y, BufferedImage sprite, BufferedImage icon) {
		super(x, y, 2, sprite, "Spell Slinger", "A wand that casts spells quickly.", icon, 1000);
	}

	@Override
	public void update(int deltaTime) {
		super.update(deltaTime);
		// Shoot at a certain interval
		if (shooting && shootCounter > 75) {
			shoot();
			shootCounter = 0;
		} else {
			shootCounter += deltaTime;
		}
	}

	@Override
	public void release() {
		shooting = false;
	}

	@Override
	public void press() {
		shooting = true;
		shootCounter = 51;
	}

	public void shoot() {
		double[] unitVector = Helper.unitVector(bounds.x, bounds.y, GameJPanel.getMouseX(), GameJPanel.getMouseY());
		// Shoot a particle in the direction of the mouse
		spellManager.add(new Spell(null,
				new Rectangle(bounds.x + (int) (unitVector[0] * 40), bounds.y + (int) (unitVector[1] * 40), 10, 10),
				(float) unitVector[0], (float) unitVector[1], damagePerParticle, new Color(117, 227, 255)), true);
	}
}
