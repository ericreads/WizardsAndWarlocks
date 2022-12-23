import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class HUD {
	private Player player;
	private int curLevel;
	private Font dogicaPixelBold;
	private ArrayList<FloatingText> floatingText;
	
	private BufferedImage healthBarEmpty, healthBarLeft, healthBarRight, segment;
	private BufferedImage coin; 
	
	public HUD(Player player, int curLevel)
	{
		this.player = player;
		this.curLevel = curLevel;
	
		
		floatingText = new ArrayList<FloatingText>();
		
		try
		{
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);
			
			healthBarEmpty = ImageIO.read(getClass().getResourceAsStream("/ui/health_bar.png"));
			healthBarLeft = ImageIO.read(getClass().getResourceAsStream("/ui/health_bar_left.png"));
			healthBarRight = ImageIO.read(getClass().getResourceAsStream("/ui/health_bar_right.png"));
			segment = ImageIO.read(getClass().getResourceAsStream("/ui/health_segment.png"));
			
			coin = ImageIO.read(getClass().getResourceAsStream("/icons/coin.png"));
		}
		catch (FontFormatException | IOException e)
		{
			System.out.println(e.toString());
		}
		
		
	}
	//Set the wave number
	public void setLevel(int level)
	{
		curLevel = level;
	}
	
	public void displayMoney(String text, int x, int y, Color color)
	{
		floatingText.add(new FloatingText(text, x + (int)(Math.random() * 40), y, 1000, color));
	}
	
	public void update(int deltaTime)
	{
		for(FloatingText text : floatingText)
		{
			text.update(deltaTime);
		}
		for(int i = 0; i < floatingText.size(); i++)
		{
			if(floatingText.get(i).getDead())
			{
				floatingText.remove(i);
				i--;
			}
		}
	}
	
	public void draw(Graphics2D g)
	{
		//Ensure the bar is always 200 pixels no matter the player's health
		int segmentWidth = 200/player.getMaxHealth();
		
		// Draw health bar
		g.drawImage(healthBarEmpty, 18, 12, null);
		
		if (player.getHealth() > 1)
		{
			g.drawImage(healthBarLeft, 18, 12, null);
			
			for (int i = 0; i < player.getHealth() * segmentWidth - 24; i++) {
				g.drawImage(segment, 30 + i, 12, null);
			}

			g.drawImage(healthBarRight, (int)(player.getHealth() * segmentWidth + 6), 12, null);
		}
	
		for(FloatingText text : floatingText)
		{
			text.draw(g);
		}
		
		// Display the wave information
		g.setColor(new Color(63, 38, 49));
		g.setFont(dogicaPixelBold);
		
		// Display money
		String money = String.valueOf(SaveManager.getInstance().getMoney());
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 18F));
		
		g.drawImage(coin, 1130, 12, 25, 25, null); 
		g.drawString(money, 1162 + 10 * (4 - money.length()), 33); // right align text
		
		// Display wave number
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 20F));
		g.drawString("Wave : " + curLevel, 555, 35);
		
	}
}
