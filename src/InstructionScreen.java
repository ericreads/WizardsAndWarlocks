// Import required modules
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;

public class InstructionScreen extends GameScreen 
{

	private int frames = 0;
	private int stage;
	
	private Color brown; 
	private Font dogicaPixelBold;
	private Font dogicaPixel; 
	
	private BufferedImage background; 
	private BufferedImage textBackground; 
	
	private TextBox textBox;
	
	public InstructionScreen(int stage)
	{
		this.stage = stage; 
		brown = new Color(63, 38, 49);
		
		try 
		{
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);
			
			is = getClass().getResourceAsStream("/fonts/dogicapixel.ttf");
			dogicaPixel = Font.createFont(Font.TRUETYPE_FONT, is);
			
			background = ImageIO.read(getClass().getResourceAsStream("ui/background.png")); 
			textBackground = ImageIO.read(getClass().getResourceAsStream("ui/text_background.png")); 
		} 
		catch (FontFormatException | IOException e) 
		{
			System.out.println(e.toString());
		}
	}
	
	@Override
	public void initialize() 
	{
		textBox = new TextBox(null);
	}

	@Override
	public void update(int deltaTime) 
	{
		frames++;
	}

	@Override
	public void draw(Graphics2D g) 
	{
		g.setColor(new Color(35, 35, 35, 100));
		g.fillRect(0, 0, 1280, 680);
		
		if (stage == 0)
		{
			String dialogue[] = {"50 years ago, The Empire was a prosperous land, where those born with magic could freely",
					"pratice it, until now. A new King takes to the throne, threatening to hunt down and kill",
					"all commoners with magical abilities, including you.", " ",
					"You have been caught by the King's Guards, awaiting your invetible punishment in the dungeon",
					"palace. Fight your way through the palace with your sorceries and restore peace to the empire!", " " };
			
			g.drawImage(background, 0, 0, null);
			g.drawImage(textBackground, 100, 179, null);
			
			g.setColor(brown);
						
			g.setFont(dogicaPixel.deriveFont(Font.PLAIN, 14F));
			
			for (int i = 0; i < dialogue.length; i++) 
			{
				g.drawString(dialogue[i], 125, 219 + (30 * i));
			}
			
			g.setFont(dogicaPixelBold.deriveFont(Font.PLAIN, 15F));
			g.drawString("Press space to begin the tutorial.", 129, 434);
		}
		else if (stage == 1)
		{			
			String[] text = { "Use WASD or arrow keys to move.", " ", "Move your cursor to aim and click the",
					"left mouse button to cast a spell.", " ", "Press space to continue." };
			
			textBox.setText(text);
			textBox.draw(g);
		}
		else if (stage == 2)
		{
			String[] text = { "Change your equipped inventory ", "item using the number keys.", " ",
					"You can purchase items from the shop.", " ", "Press space to continue." };
	
			textBox.setText(text);
			textBox.draw(g);
		}
		else if (stage == 4)
		{
			String[] text = { "You died!", " ", "The King's Guards defeated you this",
					"time, but the fight is not over yet!", " ", "Press space to try again" };

			textBox.setText(text);
			textBox.draw(g);
			
		}
		else if (stage == 5)
		{
			String[] text = { "Congrats! You completed the tutorial.", " ", "You are now ready to enter the dungeon",
					"and face the King's Guards.", " ", "Press space to begin gameplay." };

			textBox.setText(text);
			textBox.draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (stage < 3)
			{
				stage++;
			}
			
			if (stage == 3)
			{
				GameScreenManager.getInstance().clearScreen();
			}
			else if (stage == 4)
			{
				GameScreenManager.getInstance().clearScreen();
				GameScreenManager.getInstance().addScreen(new TutorialGameplayScreen());
			}
			else if (stage == 5)
			{
				GameScreenManager.getInstance().clearScreens();
				GameScreenManager.getInstance().addScreen(new GameplayScreen());
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			GameScreenManager.getInstance().clearScreens();
			GameScreenManager.getInstance().addScreen(new MainMenu());
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
	}

}
