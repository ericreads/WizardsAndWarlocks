import java.awt.*;
public class FloatingText {
	private String text;
	private int x;
	private int y;
	private int lifetime;
	private int countdown;
	private double deathRatio;
	private boolean dead;
	private Font font;
	private Color color;
	
	public FloatingText(String text, int x, int y, int lifetime, Color color, Font font)
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
		this.lifetime = lifetime;
		countdown = lifetime;
		deathRatio = 1.0f;
		this.font = font;
		dead = false;
	}
	
	public void update(int deltaTime)
	{
		countdown -= deltaTime;
		deathRatio = countdown/(double)lifetime;
		if(countdown < 0)
			dead = true;
	}
	
	public void draw(Graphics2D g)
	{
		if(!dead)
		{
			g.setFont(font);
			g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(deathRatio*255)));
			g.drawString(text, x, (int)(y+(-lifetime+countdown)/10));
		}
	}
	public boolean getDead() { return dead; }
}
