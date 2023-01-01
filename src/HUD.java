import java.awt.*;
import java.awt.event.*;
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
	private BufferedImage pause1, pause2, pause3;
	private BufferedImage home1, home2, home3;
	private BufferedImage play1;
	
	private boolean homePressed = false; 
	private boolean pausePressed = false;
	private int frames = 0; 
	
	public HUD(Player player, int curLevel)
	{
		this.player = player;
		this.curLevel = curLevel;
	
		
		floatingText = new ArrayList<FloatingText>();
		
		try
		{
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);
			
			coin = ImageIO.read(getClass().getResourceAsStream("/icons/coin.png"));
			
			healthBarEmpty = ImageIO.read(getClass().getResourceAsStream("/ui/health_bar.png"));
			healthBarLeft = ImageIO.read(getClass().getResourceAsStream("/ui/health_bar_left.png"));
			healthBarRight = ImageIO.read(getClass().getResourceAsStream("/ui/health_bar_right.png"));
			segment = ImageIO.read(getClass().getResourceAsStream("/ui/health_segment.png"));
			
			play1 = ImageIO.read(getClass().getResourceAsStream("/ui/play1.png"));
			
			pause1 = ImageIO.read(getClass().getResourceAsStream("/ui/pause1.png"));
			pause2 = ImageIO.read(getClass().getResourceAsStream("/ui/pause2.png"));
			pause3 = ImageIO.read(getClass().getResourceAsStream("/ui/pause3.png"));
			
			home1 = ImageIO.read(getClass().getResourceAsStream("/ui/home1.png"));
			home2 = ImageIO.read(getClass().getResourceAsStream("/ui/home2.png"));
			home3 = ImageIO.read(getClass().getResourceAsStream("/ui/home3.png"));
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
	
	public void mouseReleased(MouseEvent e)
	{
		if (GameJPanel.getMouseX() > 1193 && GameJPanel.getMouseX() < 1238 
				&& GameJPanel.getMouseY() > 10 && GameJPanel.getMouseY() < 55)
		{
			homePressed = true; 
		}
//		else if (GameJPanel.getMouseX() < 0 || GameJPanel.getMouseX() > 1265 || GameJPanel.getMouseY() < 0
//				|| GameJPanel.getMouseY() > 688)
//		{
//			pausePressed = true;
//		}
		else if (GameJPanel.getMouseX() > 1135 && GameJPanel.getMouseX() < 1180 
				&& GameJPanel.getMouseY() > 10 && GameJPanel.getMouseY() < 55)
		{
			pausePressed = true;
		}
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
		
		if (homePressed || pausePressed)
		{
			frames++;
		}
		
		if (frames > 15)
		{
			frames = 0;
			
			if (homePressed)
			{
				homePressed = false;
				
				// After button animation has completed, return to main menu 
				SaveManager.getInstance().saveVals();
				GameScreenManager.getInstance().clearScreens();
				GameScreenManager.getInstance().addScreen(new MainMenu());
			}
			else if (pausePressed)
			{
				pausePressed = false;
				
				SaveManager.getInstance().saveVals();
				GameScreenManager.getInstance().addScreen(new PauseScreen());
			}
		}
	}
	
	public void draw(Graphics2D g)
	{
		//Ensure the bar is always 200 pixels no matter the player's health
		int segmentWidth = 200/player.getMaxHealth();
		
		// Draw health bar
		g.drawImage(healthBarEmpty, 18, 12, null);
		
		if (player.getHealth() > 0.5)
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
		
		g.drawImage(coin, 18, 55, 25, 25, null); 
		g.drawString(money, 50, 75); // right align text
		
		// Display wave number
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 20F));
		g.drawString("Wave : " + curLevel, 555, 35);
		
		// Display home and pause buttons
		if (!pausePressed)
		{
			g.drawImage(pause1, 1135, 10, 45, 45, null);
		}
		else
		{
			if (frames < 5)
			{
				g.drawImage(pause2, 1135, 10, 45, 45, null);
			}
			else if (frames < 10)
			{
				g.drawImage(pause3, 1135, 10, 45, 45, null);
			}
			else
			{
				g.drawImage(play1, 1135, 10, 45, 45, null);
			}
		}
		
		if (!homePressed)
		{
			g.drawImage(home1, 1193, 10, 45, 45, null);
		}
		else 
		{
			if (frames < 5)
			{
				g.drawImage(home2, 1193, 10, 45, 45, null);
			}
			else if (frames < 10)
			{
				g.drawImage(home3, 1193, 10, 45, 45, null);
			}
			else
			{
				g.drawImage(home1, 1193, 10, 45, 45, null);
			}
		}
	}
}
