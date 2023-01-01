import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Spell {

	private Image sprite;
	private Rectangle bounds;
	private float vx, vy;
	private int damage;
	private boolean isDead = false;
	private Color colour; 
	
	public Spell(Image sprite, Rectangle bounds, float vx, float vy, int damage, Color colour)
	{
		this.bounds = bounds;
		this.sprite = sprite;
		this.vx = vx;
		this.vy = vy;
		this.damage = damage;
		this.colour = colour; 
	}
	
	public void draw(Graphics2D g) 
	{
		g.setColor(colour);
		if(!isDead)
			g.drawOval(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public boolean getIsDead()
	{
		return isDead;
	}

	public void kill()
	{
		isDead = true;
	}

	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public int getDamage()
	{
		return damage;
	}

	public void update(int deltaTime)
	{
		bounds.x += vx * deltaTime;
		bounds.y += vy * deltaTime;
		if(bounds.x > 1290 || bounds.x < -10 || bounds.y > 730 || bounds.y < -10)
			isDead = true;
	}
	
	
}
