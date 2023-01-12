
// Import required modules
import java.awt.*;
import java.awt.image.*;

public class SpellSlinger extends Weapon {
	private boolean shooting = false;
	private int shootCounter = 0;
<<<<<<< HEAD
	private int reload = 50;
	
	public SpellSlinger(int x, int y, Image sprite)
	{
		super(x, y, 2, sprite, 50);
=======

	public SpellSlinger(int x, int y, BufferedImage sprite, BufferedImage icon) {
		super(x, y, 2, sprite, "Spell Slinger", "A wand that casts spells quickly.", icon, 1000);
>>>>>>> main
	}

	@Override
	public void update(int deltaTime) {
		super.update(deltaTime);
<<<<<<< HEAD
		//Shoot at a certain interval
		if(getShooting() && shootCounter > 75)
		{
=======
		// Shoot at a certain interval
		if (shooting && shootCounter > 75) {
>>>>>>> main
			shoot();
			shootCounter = 0;
		} else {
			shootCounter += deltaTime;
		}
	}

	@Override
<<<<<<< HEAD
	public void release()
	{
		setShooting(false);
=======
	public void release() {
		shooting = false;
>>>>>>> main
	}

	@Override
<<<<<<< HEAD
	public void press()
	{
		setShooting(true);
=======
	public void press() {
		shooting = true;
>>>>>>> main
		shootCounter = 51;
	}

	public void shoot() {
		double[] unitVector = Helper.unitVector(bounds.x, bounds.y, GameJPanel.getMouseX(), GameJPanel.getMouseY());
<<<<<<< HEAD
		//Shoot a particle in the direction of the mouse
		spellManager.add(new Spell(null, new Rectangle(bounds.x + (int)(unitVector[0]*40), bounds.y + (int)(unitVector[1]*40), 10, 10), (float)unitVector[0], (float)unitVector[1], damagePerParticle), true);
		reload = getReload();
		setReload(reload -= 1);
		if (getReload() == 0 || getReload() == 50)
		{
			setShooting(false);
			setReload(50);
	
		}

		System.out.println(getReload());
=======
		// Shoot a particle in the direction of the mouse
		spellManager.add(new Spell(null,
				new Rectangle(bounds.x + (int) (unitVector[0] * 40), bounds.y + (int) (unitVector[1] * 40), 10, 10),
				(float) unitVector[0], (float) unitVector[1], damagePerParticle, new Color(117, 227, 255)), true);
>>>>>>> main
	}
	
}
