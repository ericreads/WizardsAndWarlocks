// Import required modules
import java.awt.*;

public class SpellSlinger extends Weapon {
	private boolean shooting = false;
	private int shootCounter = 0;
	private int reload = 50;
	
	public SpellSlinger(int x, int y, Image sprite)
	{
		super(x, y, 2, sprite, 50);
	}
	
	@Override
	public void update(int deltaTime)
	{
		super.update(deltaTime);
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
	@Override
	public void release()
	{
		setShooting(false);
	}
	@Override
	public void press()
	{
		setShooting(true);
		shootCounter = 51;
	}
	public void shoot()
	{
		double[] unitVector = Helper.unitVector(bounds.x, bounds.y, GameJPanel.getMouseX(), GameJPanel.getMouseY());
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
	}
	
}
