import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import java.awt.*;
public class FloatingText {
	private String text;
	private int x;
	private int y;
	private int lifetime;
	private int countdown;
	private double deathRatio;
	private boolean dead;
	private Font dogicaPixelBold;
	private Color color;
	private BufferedImage coin;
	
	public FloatingText(String text, int x, int y, int lifetime, Color color)
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
		this.lifetime = lifetime;
		
		countdown = lifetime;
		deathRatio = 1.0f;
		dead = false;
		
		try 
		{
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);
			
			coin = ImageIO.read(getClass().getResourceAsStream("/icons/coin.png"));
		} 
		catch (FontFormatException | IOException e) {
			System.out.println(e.toString());
		} 
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
			g.setFont(dogicaPixelBold);
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 12F));
			g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(deathRatio*255)));
			
			if (text.charAt(0) == '$')
			{
				g.drawString(text.substring(1), x, (int) (y + (-lifetime + countdown) / 10));
				
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)deathRatio));
				g.drawImage(coin, x - 19, (y + (-lifetime + countdown) / 10) - 11, 15, 15, null);
				
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
			}
			else
			{
				g.drawString(text, x, (int) (y + (-lifetime + countdown) / 10));
			}
		}
	}
	public boolean getDead() { return dead; }
}
