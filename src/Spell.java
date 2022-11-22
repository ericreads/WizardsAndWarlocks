import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Spell {

	private Image sprite;
	private Rectangle bounds;
	private int vx, vy, damage;
	
	public Spell(Image sprite, Rectangle bounds, int vx, int vy)
	{
		this.bounds = bounds;
		this.sprite = sprite;
		this.vx = vx;
		this.vy = vy;
		this.damage = damage;
	}
	
	public void draw(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public void update(int deltaTime)
	{
		bounds.x += vx * deltaTime;
		bounds.y += vy * deltaTime;
	}
	
}
