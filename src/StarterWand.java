import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.*;

public class StarterWand extends Weapon {
	public StarterWand(int x, int y, BufferedImage sprite, BufferedImage icon) {
		super(x, y, 2, sprite, "Beginner's Wand", "The wand of a beginner, ok but not remarkable.", icon);
	}

	@Override
	public void press() {
		shoot();
	}

	@Override
	public void release() {
		// Do nothing
	}

	public void shoot() {
		double[] unitVector = Helper.unitVector(bounds.x, bounds.y, GameJPanel.getMouseX(), GameJPanel.getMouseY());
		// Shoot a particle in the direction of the mouse
		spellManager.add(new Spell(null,
				new Rectangle(bounds.x + (int) (unitVector[0] * 40), bounds.y + (int) (unitVector[1] * 40), 10, 10),
				(float) unitVector[0], (float) unitVector[1], damagePerParticle, new Color(232, 69, 55)), true);
	}
}
